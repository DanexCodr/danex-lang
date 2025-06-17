// File: src/danex/AstBuildingVisitor.java
package danex;

import danex.ast.*;         // AST node classes: Annotation, Param, ExprStmt, ResourceDecl, etc.
import danex.*;             // AstBuilder, RuntimeError, etc.
import danex.grammar.*;     // ANTLR-generated parser/lexer classes
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;

/**
 * Visitor that walks the ANTLR parse tree and builds AST Decl nodes via AstBuilder.
 */
public class AstBuildingVisitor extends DanexParserBaseVisitor<Object> {
    private final AstBuilder builder;

    public AstBuildingVisitor(AstBuilder builder) {
        this.builder = builder;
    }

/**
 * In AstBuildingVisitor (or wherever you need it), add:
 */
private boolean detectAssignmentTo(Stmt stmt, String resultName, String methodName) {
    // Scanner will walk the AST subtree (Stmt, Expr, Decl) looking for an AssignStmt
    // whose target matches either resultName or methodName.
    class Scanner implements Stmt.Visitor<Boolean>, Expr.Visitor<Boolean>, Decl.Visitor<Boolean> {
        // ---- Expr visitor methods ----
        @Override
        public Boolean visitBinaryExpr(BinaryExpr be) {
            // Recurse into left and right
            return scanExpr(be.left) || scanExpr(be.right);
        }
        @Override
        public Boolean visitUnaryExpr(UnaryExpr ue) {
            return scanExpr(ue.right);
        }
        @Override
        public Boolean visitLiteralExpr(LiteralExpr le) {
            return false;
        }
        @Override
        public Boolean visitGroupingExpr(GroupingExpr ge) {
            return scanExpr(ge.expression);
        }
        @Override
        public Boolean visitVariableExpr(VariableExpr ve) {
            return false;
        }
        @Override
        public Boolean visitAssignExpr(AssignExpr ae) {
            // In expressions, assignment might also appear, but we treat as Expr; 
            // actual assignment to resultName/methodName is handled in AssignStmt visitor.
            // Here we descend into the value expression:
            return scanExpr(ae.value);
        }
        @Override
        public Boolean visitCallExpr(CallExpr ce) {
            // Recurse into callee and arguments
            if (scanExpr(ce.callee)) return true;
            for (Expr arg : ce.arguments) {
                if (scanExpr(arg)) return true;
            }
            return false;
        }
        @Override
        public Boolean visitLambdaExpr(LambdaExpr le) {
            // If your language supports nested functions, you may or may not want to scan inside;
            // but typically result assignment detection is per-method, so skip inside lambda:
            return false;
        }
        @Override
        public Boolean visitDoExpr(DoExpr de) {
            // Descend into the body statements
            for (Stmt s : de.body) {
                if (scanStmt(s)) return true;
            }
            return false;
        }
        @Override
        public Boolean visitTryExpr(TryExpr te) {
            // Descend into try/catch/finally blocks
            if (te.tryBlock != null) {
                for (Stmt s : te.tryBlock) {
                    if (scanStmt(s)) return true;
                }
            }
            if (te.catchBlock != null) {
                for (Stmt s : te.catchBlock) {
                    if (scanStmt(s)) return true;
                }
            }
            if (te.finallyBlock != null) {
                for (Stmt s : te.finallyBlock) {
                    if (scanStmt(s)) return true;
                }
            }
            return false;
        }
        @Override
        public Boolean visitAwaitExpr(AwaitExpr ae) {
            // Likely skip or descend if needed; here assume skip
            return false;
        }
        @Override
        public Boolean visitNullCoalesceExpr(NullCoalesceExpr ne) {
            return scanExpr(ne.left) || scanExpr(ne.right);
        }
        @Override
        public Boolean visitComparatorExpr(ComparatorExpr ce) {
            return scanExpr(ce.left) || scanExpr(ce.right);
        }

        // ---- Stmt visitor methods ----
        @Override
        public Boolean visitExprStmt(ExprStmt es) {
            // An expression statement: check inside expression
            return scanExpr(es.expression);
        }
        @Override
        public Boolean visitAssignStmt(AssignStmt stmt) {
            // Check if the assignment target matches resultName or methodName:
            Expr tgt = stmt.target;
            if (tgt instanceof VariableExpr) {
                String var = ((VariableExpr) tgt).name;
                if ((resultName != null && var.equals(resultName)) || var.equals(methodName)) {
                    return true;
                }
            }
            // Also descend into the value expression:
            return scanExpr(stmt.value);
        }
        @Override
        public Boolean visitBlockStmt(BlockStmt bs) {
            for (Stmt s : bs.statements) {
                if (scanStmt(s)) return true;
            }
            return false;
        }
        @Override
        public Boolean visitIfStmt(IfStmt ifs) {
            if (scanExpr(ifs.condition)) return true;
            if (scanStmt(ifs.thenBranch)) return true;
            if (ifs.elseBranch != null && scanStmt(ifs.elseBranch)) return true;
            return false;
        }
        @Override
        public Boolean visitWhileStmt(WhileStmt ws) {
            if (scanExpr(ws.condition)) return scanStmt(ws.body);
            return false;
        }
        @Override
        public Boolean visitDoWhileStmt(DoWhileStmt dws) {
            if (scanStmt(dws.body)) return true;
            return scanExpr(dws.condition);
        }
        @Override
        public Boolean visitForStmt(ForStmt fs) {
            if (fs.init != null && scanStmt(fs.init)) return true;
            if (fs.condition != null && scanExpr(fs.condition)) return true;
            if (fs.update != null && scanExpr(fs.update)) return true;
            return scanStmt(fs.body);
        }
        @Override
        public Boolean visitThrowStmt(ThrowStmt ts) {
            return scanExpr(ts.exception);
        }
        @Override
        public Boolean visitExitStmt(ExitStmt es) {
            return false;
        }
        @Override
        public Boolean visitTryStmt(TryStmt ts) {
            if (ts.tryBlock != null) {
                for (Stmt s : ts.tryBlock) {
                    if (scanStmt(s)) return true;
                }
            }
            if (ts.catchBlock != null) {
                for (Stmt s : ts.catchBlock) {
                    if (scanStmt(s)) return true;
                }
            }
            if (ts.finallyBlock != null) {
                for (Stmt s : ts.finallyBlock) {
                    if (scanStmt(s)) return true;
                }
            }
            return false;
        }
        // Note: classDecl and methodDecl in a statement context are Decl nodes handled elsewhere,
        // but since we implement Decl.Visitor<Boolean>, we must provide these methods:
        @Override
        public Boolean visitClassDecl(ClassDecl cd) {
            // We typically do not scan inside nested class declarations for method-return detection here
            return false;
        }
        @Override
        public Boolean visitMethodDecl(MethodDecl md) {
            // Not descending into nested method here
            return false;
        }
        @Override
        public Boolean visitImportDecl(ImportDecl id) {
            return false;
        }
        @Override
        public Boolean visitAnnotation(Annotation a) {
            return false;
        }
        @Override
        public Boolean visitParam(Param p) {
            return false;
        }
        @Override
        public Boolean visitResourceDecl(ResourceDecl rd) {
            return false;
        }

        // ---- Helpers to dispatch ----
        private Boolean scanExpr(Expr e) {
            return (e != null) && e.accept(this);
        }
        private Boolean scanStmt(Stmt s) {
            return (s != null) && s.accept(this);
        }
    }

    Scanner scanner = new Scanner();
    return scanner.scanStmt(stmt);
        }
    
    /**
     * Top-level: collect declarations (ImportDecl, ClassDecl, MethodDecl).
     * Grammar: compilationUnit: statement* EOF;
     * statement: classDecl | methodDecl | topLevelMethodDecl | importStmt;
     */
    @Override
    public Object visitCompilationUnit(DanexParser.CompilationUnitContext ctx) {
        List<Decl> decls = new ArrayList<>();
        for (var stmtCtx : ctx.statement()) {
            Object result = visit(stmtCtx);
            if (result instanceof Decl) {
                decls.add((Decl) result);
            } else {
                throw new RuntimeException("Expected declaration at top-level, got: " + result);
            }
        }
        return decls;
    }

    // -------------------
    // Import statements
    // -------------------
    @Override
    public Object visitImportStmt(DanexParser.ImportStmtContext ctx) {
        String moduleName = ctx.IDENTIFIER(0).getText();
        String alias = ctx.IDENTIFIER().size() > 1 ? ctx.IDENTIFIER(1).getText() : null;
        ImportDecl importNode = new ImportDecl(moduleName, alias);
        return builder.visitImportDecl(importNode);
    }

    // -------------------
    // Class declarations
    // -------------------
    @Override
    public Object visitClassDecl(DanexParser.ClassDeclContext ctx) {
        // Annotations
        List<Annotation> annotations = new ArrayList<>();
        for (var annCtx : ctx.annotation()) {
            Object annObj = visit(annCtx);
            if (annObj instanceof Annotation) {
                annotations.add((Annotation) annObj);
            }
        }
        // Modifiers
        List<String> modifiers = new ArrayList<>();
        for (var m : ctx.modifier()) {
            modifiers.add(m.getText());
        }
        // Name
        String name = ctx.IDENTIFIER().getText();

        // Members: classBodyMember: methodDecl | classDecl
        List<Decl> members = new ArrayList<>();
        for (var memberCtx : ctx.classBody().classBodyMember()) {
            Object memberObj = visit(memberCtx);
            if (memberObj instanceof Decl) {
                members.add((Decl) memberObj);
            } else {
                throw new RuntimeException("Expected Decl inside class body, got: " + memberObj);
            }
        }

        ClassDecl classNode = new ClassDecl(name, annotations, modifiers, members);
        return builder.visitClassDecl(classNode);
    }

    @Override
    public Object visitClassBodyMember(DanexParser.ClassBodyMemberContext ctx) {
        if (ctx.methodDecl() != null) {
            return visit(ctx.methodDecl());
        } else if (ctx.classDecl() != null) {
            return visit(ctx.classDecl());
        } else {
            throw new RuntimeException("Unknown classBodyMember: " + ctx.getText());
        }
    }

    // -------------------
    // Method declarations
    // -------------------
@Override
public Object visitMethodDecl(DanexParser.MethodDeclContext ctx) {
    return buildMethod(ctx);
}

@Override
public Object visitTopLevelMethodDecl(DanexParser.TopLevelMethodDeclContext ctx) {
    return buildTopLevelMethod(ctx);
}

/** Helper for MethodDeclContext. */
private Decl buildMethod(DanexParser.MethodDeclContext ctx) {
    // 1. Annotations & modifiers
    List<Annotation> annotations = buildAnnotations(ctx.annotation());
    List<String> modifiers = buildModifiers(ctx.modifier());

    // 2. Name
    if (ctx.IDENTIFIER() == null) {
        throw new RuntimeException("MethodDecl missing name");
    }
    String methodName = ctx.IDENTIFIER().getText();

    // 3. resultDecl parsing under grammar: (IDENT (IDENT)?)?
    String resultType = null;
    String resultName = null;
    if (ctx.resultDecl() != null) {
        List<org.antlr.v4.runtime.tree.TerminalNode> ids = ctx.resultDecl().IDENTIFIER();
        if (ids.size() == 2) {
            // explicit type + name
            resultType = ids.get(0).getText();
            resultName = ids.get(1).getText();
        } else if (ids.size() == 1) {
            // name-only, type inferred
            resultName = ids.get(0).getText();
            resultType = null;
        } else {
            throw new RuntimeException("Invalid result declaration syntax");
        }
    }
    // 4. Params (typed required by grammar)
    List<Param> params = buildParams(ctx.paramList());

    // 5. Check conflict: param name cannot equal resultName
    if (resultName != null) {
        for (Param p : params) {
            if (p.name.equals(resultName)) {
                throw new RuntimeException("Parameter '" + p.name +
                    "' conflicts with result variable name in method '" + methodName + "'");
            }
        }
    }
    // Additionally: parameter cannot equal methodName if you want to forbid that, but usually only resultName matters.

    // 6. Build body (block or arrow form)
    Stmt bodyStmt = buildMethodBody(methodName, resultName, ctx.methodBody());

    // 7. Semantic check: if resultName declared, ensure at least one assignment occurs
    if (resultName != null) {
        boolean assigned = detectAssignmentTo(bodyStmt, resultName, methodName);
        if (!assigned) {
            throw new RuntimeException("Method '" + methodName +
                "' declares result '" + resultName + "' but no assignment found in body");
        }
    }
    // If resultName == null: it's a void method or uses assignment-to-methodName for implicit return; 
    // you could optionally check that if assignment-to-methodName appears, treat as return, otherwise void.

    // 8. Build MethodDecl AST and pass to builder
    MethodDecl methodNode = new MethodDecl(methodName, resultType, resultName,
                                           annotations, modifiers, params, bodyStmt);
    System.out.println("[DEBUG] Built method: " + methodName);
    return builder.visitMethodDecl(methodNode);
}

/** Helper for TopLevelMethodDeclContext: same logic as buildMethod */
private Decl buildTopLevelMethod(DanexParser.TopLevelMethodDeclContext ctx) {
    // 1. Annotations & modifiers
    List<Annotation> annotations = buildAnnotations(ctx.annotation());
    List<String> modifiers = buildModifiers(ctx.modifier());

    // 2. Name
    if (ctx.IDENTIFIER() == null) {
        throw new RuntimeException("TopLevelMethodDecl missing name");
    }
    String methodName = ctx.IDENTIFIER().getText();

    // 3. resultDecl parsing
    String resultType = null;
    String resultName = null;
    if (ctx.resultDecl() != null) {
        List<org.antlr.v4.runtime.tree.TerminalNode> ids = ctx.resultDecl().IDENTIFIER();
        if (ids.size() == 2) {
            resultType = ids.get(0).getText();
            resultName = ids.get(1).getText();
        } else if (ids.size() == 1) {
            resultName = ids.get(0).getText();
            resultType = null;
        } else {
            throw new RuntimeException("Invalid result declaration syntax");
        }
    }
    // 4. Params
    List<Param> params = buildParams(ctx.paramList());

    // 5. Conflict check
    if (resultName != null) {
        for (Param p : params) {
            if (p.name.equals(resultName)) {
                throw new RuntimeException("Parameter '" + p.name +
                    "' conflicts with result variable name in top-level method '" + methodName + "'");
            }
        }
    }

    // 6. Build body
    Stmt bodyStmt = buildMethodBody(methodName, resultName, ctx.methodBody());

    // 7. Semantic check
    if (resultName != null) {
        boolean assigned = detectAssignmentTo(bodyStmt, resultName, methodName);
        if (!assigned) {
            throw new RuntimeException("Top-level method '" + methodName +
                "' declares result '" + resultName + "' but no assignment found in body");
        }
    }

    // 8. Build AST node
    MethodDecl methodNode = new MethodDecl(methodName, resultType, resultName,
                                           annotations, modifiers, params, bodyStmt);
    System.out.println("[DEBUG] Built top-level method: " + methodName);
    return builder.visitMethodDecl(methodNode);
}
    
    /**

Shared param builder for both MethodDeclContext and TopLevelMethodDeclContext.
*/
private List<Param> buildParams(DanexParser.ParamListContext paramListCtx) {
List<Param> params = new ArrayList<>();
if (paramListCtx != null) {
for (var pCtx : paramListCtx.param()) {
String pType = pCtx.type().getText();
String pName = pCtx.IDENTIFIER().getText();
boolean varargs = pCtx.VARARGS() != null;
Param paramNode = new Param(pType, pName, varargs);
paramNode = (Param) builder.visitParam(paramNode);
params.add(paramNode);
}
}
return params;
}

/**

Shared annotations builder.
*/
private List<Annotation> buildAnnotations(List<DanexParser.AnnotationContext> annotationCtxs) {
List<Annotation> annotations = new ArrayList<>();
for (var annCtx : annotationCtxs) {
Object annObj = visit(annCtx);
if (annObj instanceof Annotation) {
annotations.add((Annotation) annObj);
}
}
return annotations;
}

/**

Shared modifier builder.
*/
private List<String> buildModifiers(List<DanexParser.ModifierContext> modifierCtxs) {
List<String> modifiers = new ArrayList<>();
for (var m : modifierCtxs) {
modifiers.add(m.getText());
}
return modifiers;
}

/**

Shared method body builder: handles both block and expression bodies.
*/
private Stmt buildMethodBody(String methodName, String resultName, DanexParser.MethodBodyContext bodyCtx) {
if (bodyCtx.block() != null) {
Object bObj = visit(bodyCtx.block());
if (!(bObj instanceof Stmt)) {
throw new RuntimeException("Expected Stmt from block, got: " + bObj);
}
return (Stmt) bObj;
} else {
Expr expr = (Expr) visit(bodyCtx.expression());
String target = (resultName != null) ? resultName : methodName;
AssignExpr assign = new AssignExpr(target, expr);
assign = (AssignExpr) builder.visitAssignExpr(assign);
ExprStmt exprStmt = new ExprStmt(assign);
exprStmt = (ExprStmt) builder.visitExprStmt(exprStmt);
List<Stmt> stmts = new ArrayList<>();
stmts.add(exprStmt);
BlockStmt block = new BlockStmt(stmts);
return (BlockStmt) builder.visitBlockStmt(block);
}
}
    // -------------------
    // Blocks
    // -------------------
    @Override
    public Object visitBlock(DanexParser.BlockContext ctx) {
        List<Stmt> stmts = new ArrayList<>();
        for (var bc : ctx.blockContent()) {
            Object o = visit(bc);
            if (o instanceof Stmt) {
                stmts.add((Stmt) o);
            } else {
                throw new RuntimeException("Expected Stmt in blockContent, got: " + o);
            }
        }
        BlockStmt blockNode = new BlockStmt(stmts);
        return builder.visitBlockStmt(blockNode);
    }

    @Override
    public Object visitBlockContent(DanexParser.BlockContentContext ctx) {
        if (ctx.statement() != null) {
            Object stmtObj = visit(ctx.statement());
            if (stmtObj instanceof Stmt) return stmtObj;
            if (stmtObj instanceof Decl) {
                throw new RuntimeException("Declaration not allowed here: " + stmtObj);
            }
            throw new RuntimeException("Expected Stmt in blockContent, got: " + stmtObj);
        } else if (ctx.expressionStatement() != null) {
            return visit(ctx.expressionStatement());
        } else if (ctx.ifStatement() != null) {
            return visit(ctx.ifStatement());
        } else if (ctx.whileStatement() != null) {
            return visit(ctx.whileStatement());
        } else if (ctx.doWhileStatement() != null) {
            return visit(ctx.doWhileStatement());
        } else if (ctx.forStatement() != null) {
            return visit(ctx.forStatement());
        } else if (ctx.tryStatement() != null) {
            return visit(ctx.tryStatement());
        } else if (ctx.exitStatement() != null) {
            return visit(ctx.exitStatement());
        } else if (ctx.throwStatement() != null) {
            return visit(ctx.throwStatement());
        } else if (ctx.returnStatement() != null) {
            return visit(ctx.returnStatement());
        } else {
            throw new RuntimeException("Unknown blockContent: " + ctx.getText());
        }
    }

    // -------------------
    // If / While / DoWhile / For
    // -------------------
    @Override
    public Object visitIfStatement(DanexParser.IfStatementContext ctx) {
        Expr condition = (Expr) visit(ctx.expression());
        Stmt thenBranch = (Stmt) visit(ctx.block(0));
        Stmt elseBranch = null;
        if (ctx.ELSE() != null) {
            elseBranch = (Stmt) visit(ctx.block(1));
        }
        IfStmt ifNode = new IfStmt(condition, thenBranch, elseBranch);
        return builder.visitIfStmt(ifNode);
    }

    @Override
    public Object visitWhileStatement(DanexParser.WhileStatementContext ctx) {
        Expr condition = (Expr) visit(ctx.expression());
        Stmt body = (Stmt) visit(ctx.block());
        WhileStmt whileNode = new WhileStmt(condition, body);
        return builder.visitWhileStmt(whileNode);
    }

    @Override
    public Object visitDoWhileStatement(DanexParser.DoWhileStatementContext ctx) {
        Stmt body = (Stmt) visit(ctx.block());
        Expr condition = (Expr) visit(ctx.expression());
        DoWhileStmt doNode = new DoWhileStmt(body, condition);
        return builder.visitDoWhileStmt(doNode);
    }

    @Override
    public Object visitForStatement(DanexParser.ForStatementContext ctx) {
        // init
        Stmt initStmt = null;
        if (ctx.assignment(0) != null) {
            Expr initExpr = (Expr) visit(ctx.assignment(0));
            ExprStmt eStmt = new ExprStmt(initExpr);
            initStmt = (ExprStmt) builder.visitExprStmt(eStmt);
        }
        Expr condition = (Expr) visit(ctx.expression());
        Stmt body = (Stmt) visit(ctx.block());
        Stmt updateStmt = null;
        if (ctx.assignment(1) != null) {
            Expr updateExpr = (Expr) visit(ctx.assignment(1));
            ExprStmt uStmt = new ExprStmt(updateExpr);
            updateStmt = (ExprStmt) builder.visitExprStmt(uStmt);
        }
        // ForStmt constructor: (Stmt init, Expr condition, Expr update, Stmt body)
        Expr updateExpr = null;
        if (updateStmt != null && updateStmt instanceof ExprStmt) {
            updateExpr = ((ExprStmt) updateStmt).expression;
        }
        ForStmt forNode = new ForStmt(initStmt, condition, updateExpr, body);
        return builder.visitForStmt(forNode);
    }

    // -------------------
    // Throw, Return, Exit
    // -------------------
    @Override
    public Object visitThrowStatement(DanexParser.ThrowStatementContext ctx) {
        Expr value = (Expr) visit(ctx.expression());
        ThrowStmt throwNode = new ThrowStmt(value);
        return builder.visitThrowStmt(throwNode);
    }

    @Override
    public Object visitExitStatement(DanexParser.ExitStatementContext ctx) {
        ExitStmt exitNode = new ExitStmt();
        return builder.visitExitStmt(exitNode);
    }

    // -------------------
    // Expression statements
    // -------------------
    @Override
    public Object visitExpressionStatement(DanexParser.ExpressionStatementContext ctx) {
        Expr expr = (Expr) visit(ctx.expression());
        ExprStmt exprStmt = new ExprStmt(expr);
        return builder.visitExprStmt(exprStmt);
    }

    // -------------------
    // Assignment & binary/unary
    // -------------------
    @Override
    public Object visitAssignment(DanexParser.AssignmentContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        String op = ctx.assignOp().getText();
        Expr value = (Expr) visit(ctx.assignment());
        AssignExpr assignNode;
        if ("=".equals(op)) {
            assignNode = new AssignExpr(name, value);
        } else {
            // e.g., a += b
            String binOp = op.substring(0, op.length() - 1);
            VariableExpr leftVar = new VariableExpr(name);
            leftVar = (VariableExpr) builder.visitVariableExpr(leftVar);
            BinaryExpr bin = new BinaryExpr(leftVar, binOp, value);
            bin = (BinaryExpr) builder.visitBinaryExpr(bin);
            assignNode = new AssignExpr(name, bin);
        }
        return builder.visitAssignExpr(assignNode);
    }

    @Override
    public Object visitLogicalOrExpr(DanexParser.LogicalOrExprContext ctx) {
        if (ctx.logicalAndExpr().size() == 1) {
            return visit(ctx.logicalAndExpr(0));
        }
        Expr left = (Expr) visit(ctx.logicalAndExpr(0));
        for (int i = 1; i < ctx.logicalAndExpr().size(); i++) {
            String op = ctx.OR_OR(i - 1).getText();
            Expr right = (Expr) visit(ctx.logicalAndExpr(i));
            BinaryExpr bin = new BinaryExpr(left, op, right);
            left = (BinaryExpr) builder.visitBinaryExpr(bin);
        }
        return left;
    }

    @Override
    public Object visitLogicalAndExpr(DanexParser.LogicalAndExprContext ctx) {
        if (ctx.nullCoalesceExpr().size() == 1) {
            return visit(ctx.nullCoalesceExpr(0));
        }
        Expr left = (Expr) visit(ctx.nullCoalesceExpr(0));
        for (int i = 1; i < ctx.nullCoalesceExpr().size(); i++) {
            String op = ctx.AND_AND(i - 1).getText();
            Expr right = (Expr) visit(ctx.nullCoalesceExpr(i));
            BinaryExpr bin = new BinaryExpr(left, op, right);
            left = (BinaryExpr) builder.visitBinaryExpr(bin);
        }
        return left;
    }

    @Override
    public Object visitNullCoalesceExpr(DanexParser.NullCoalesceExprContext ctx) {
        if (ctx.comparisonExpr().size() == 1) {
            return visit(ctx.comparisonExpr(0));
        }
        Expr left = (Expr) visit(ctx.comparisonExpr(0));
        for (int i = 1; i < ctx.comparisonExpr().size(); i++) {
            Expr right = (Expr) visit(ctx.comparisonExpr(i));
            NullCoalesceExpr nc = new NullCoalesceExpr(left, right);
            left = (NullCoalesceExpr) builder.visitNullCoalesceExpr(nc);
        }
        return left;
    }

    @Override
    public Object visitComparisonExpr(DanexParser.ComparisonExprContext ctx) {
        if (ctx.additiveExpr().size() == 1) {
            return visit(ctx.additiveExpr(0));
        }
        Expr left = (Expr) visit(ctx.additiveExpr(0));
        for (int i = 1; i < ctx.additiveExpr().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            Expr right = (Expr) visit(ctx.additiveExpr(i));
            if ("<=>".equals(op)) {
                ComparatorExpr cmp = new ComparatorExpr(left, right);
                left = (ComparatorExpr) builder.visitComparatorExpr(cmp);
            } else {
                BinaryExpr bin = new BinaryExpr(left, op, right);
                left = (BinaryExpr) builder.visitBinaryExpr(bin);
            }
        }
        return left;
    }

    @Override
    public Object visitAdditiveExpr(DanexParser.AdditiveExprContext ctx) {
        if (ctx.multiplicativeExpr().size() == 1) {
            return visit(ctx.multiplicativeExpr(0));
        }
        Expr left = (Expr) visit(ctx.multiplicativeExpr(0));
        for (int i = 1; i < ctx.multiplicativeExpr().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            Expr right = (Expr) visit(ctx.multiplicativeExpr(i));
            BinaryExpr bin = new BinaryExpr(left, op, right);
            left = (BinaryExpr) builder.visitBinaryExpr(bin);
        }
        return left;
    }

    @Override
    public Object visitMultiplicativeExpr(DanexParser.MultiplicativeExprContext ctx) {
        if (ctx.unaryExpr().size() == 1) {
            return visit(ctx.unaryExpr(0));
        }
        Expr left = (Expr) visit(ctx.unaryExpr(0));
        for (int i = 1; i < ctx.unaryExpr().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            Expr right = (Expr) visit(ctx.unaryExpr(i));
            BinaryExpr bin = new BinaryExpr(left, op, right);
            left = (BinaryExpr) builder.visitBinaryExpr(bin);
        }
        return left;
    }

@Override
public Object visitUnaryExpr(DanexParser.UnaryExprContext ctx) {
    // First, build the AST for the primary expression:
    Expr expr = (Expr) visit(ctx.primaryExpr());

    // Then wrap in prefix operators. The parse tree children are a sequence of operator tokens, then the primaryExpr.
    // We iterate in reverse so that nesting matches textual order:
    int childCount = ctx.getChildCount();
    // childCount >= 1; last child is primaryExpr, preceding are operators
    for (int i = childCount - 2; i >= 0; i--) {
        String op = ctx.getChild(i).getText();
        UnaryExpr u = new UnaryExpr(op, expr);
        expr = (UnaryExpr) builder.visitUnaryExpr(u);
    }
    return expr;
        }

    // -------------------
    // Primary expressions
    // -------------------
    @Override
    public Object visitPrimaryExpr(DanexParser.PrimaryExprContext ctx) {
        if (ctx.expression() != null) {
            return visit(ctx.expression());
        }
        if (ctx.doExpression() != null) {
            return visit(ctx.doExpression());
        }
        if (ctx.tryExpression() != null) {
            return visit(ctx.tryExpression());
        }
        if (ctx.functionCall() != null) {
            return visit(ctx.functionCall());
        }
        if (ctx.literal() != null) {
            return visit(ctx.literal());
        }
        if (ctx.IDENTIFIER() != null) {
            VariableExpr varNode = new VariableExpr(ctx.IDENTIFIER().getText());
            return builder.visitVariableExpr(varNode);
        }
        throw new RuntimeException("Unknown primaryExpr: " + ctx.getText());
    }

    @Override
    public Object visitFunctionCall(DanexParser.FunctionCallContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        VariableExpr callee = new VariableExpr(name);
        callee = (VariableExpr) builder.visitVariableExpr(callee);

        List<Expr> args = new ArrayList<>();
        if (ctx.expression() != null && !ctx.expression().isEmpty()) {
            for (var eCtx : ctx.expression()) {
                Expr arg = (Expr) visit(eCtx);
                args.add(arg);
            }
        }
        CallExpr callNode = new CallExpr(callee, args);
        return builder.visitCallExpr(callNode);
    }

    @Override
public Object visitLiteral(DanexParser.LiteralContext ctx) {
    Object value;
    if (ctx.NUMBER() != null) {
        String text = ctx.NUMBER().getText();
        if (text.contains(".")) {
            // Floating literal: keep as Double
            value = Double.parseDouble(text);
        } else {
            // Integer literal: parse as Integer if in int range, otherwise Long
            try {
                value = Integer.parseInt(text);
            } catch (NumberFormatException e) {
                // too big for int?
                value = Long.parseLong(text);
            }
        }
    } else if (ctx.STRING() != null) {
        String text = ctx.STRING().getText();
        // remove quotes; handle escapes as needed
        value = text.substring(1, text.length() - 1);
    } else if (ctx.TRUE() != null) {
        value = Boolean.TRUE;
    } else if (ctx.FALSE() != null) {
        value = Boolean.FALSE;
    } else if (ctx.NULL() != null) {
        value = null;
    } else {
        throw new RuntimeException("Unknown literal: " + ctx.getText());
    }
    LiteralExpr lit = new LiteralExpr(value);
    return builder.visitLiteralExpr(lit);
        }

    // -------------------
    // Do-expression
    // -------------------
    @Override
    public Object visitDoExpression(DanexParser.DoExpressionContext ctx) {
        BlockStmt block = (BlockStmt) visit(ctx.block());
        DoExpr doNode = new DoExpr(block.statements);
        return builder.visitDoExpr(doNode);
    }

    // -------------------
    // Try-expression
    // -------------------
    @Override
    public Object visitTryExpression(DanexParser.TryExpressionContext ctx) {
        BlockStmt tryBlock = (BlockStmt) visit(ctx.block(0));
        String catchType = null;
        String catchName = null;
        List<Stmt> catchStmts = null;
        if (ctx.CATCH() != null) {
            catchType = ctx.IDENTIFIER(0).getText();
            catchName = ctx.IDENTIFIER(1).getText();
            BlockStmt catchBlock = (BlockStmt) visit(ctx.block(1));
            catchStmts = catchBlock.statements;
        }
        List<Stmt> finallyStmts = null;
        if (ctx.FINALLY() != null) {
            int lastIdx = ctx.block().size() - 1;
            BlockStmt finallyBlock = (BlockStmt) visit(ctx.block(lastIdx));
            finallyStmts = finallyBlock.statements;
        }
        TryExpr tryNode = new TryExpr(tryBlock.statements, catchType, catchName, catchStmts, finallyStmts);
        return builder.visitTryExpr(tryNode);
    }

    // -------------------
    // Try-statement
    // -------------------
    @Override
    public Object visitTryStatement(DanexParser.TryStatementContext ctx) {
        List<ResourceDecl> resources = new ArrayList<>();
        if (ctx.resourceSpec() != null) {
            for (var resCtx : ctx.resourceSpec().resourceDecl()) {
                ResourceDecl rd = buildResourceDecl(resCtx);
                rd = (ResourceDecl) builder.visitResourceDecl(rd);
                resources.add(rd);
            }
        }
        BlockStmt tryBlock = (BlockStmt) visit(ctx.block(0));

        String catchType = null;
        String catchName = null;
        List<Stmt> catchStmts = null;
        if (ctx.CATCH() != null) {
            catchType = ctx.IDENTIFIER(0).getText();
            catchName = ctx.IDENTIFIER(1).getText();
            BlockStmt catchBlock = (BlockStmt) visit(ctx.block(1));
            catchStmts = catchBlock.statements;
        }
        List<Stmt> finallyStmts = null;
        if (ctx.FINALLY() != null) {
            int lastIdx = ctx.block().size() - 1;
            BlockStmt finallyBlock = (BlockStmt) visit(ctx.block(lastIdx));
            finallyStmts = finallyBlock.statements;
        }
        TryStmt tryNode = new TryStmt(resources, tryBlock.statements, catchType, catchName, catchStmts, finallyStmts);
        return builder.visitTryStmt(tryNode);
    }

    private ResourceDecl buildResourceDecl(DanexParser.ResourceDeclContext ctx) {
        String type = ctx.type().getText();
        String name = ctx.IDENTIFIER().getText();
        Expr initExpr = (Expr) visit(ctx.expression());
        return new ResourceDecl(type, name, initExpr);
    }

    // -------------------
    // Annotation
    // -------------------
    @Override
    public Object visitAnnotation(DanexParser.AnnotationContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        Annotation ann = new Annotation(name);
        return builder.visitAnnotation(ann);
    }

    // -------------------
    // Type: return as text if needed
    // -------------------
    @Override
    public Object visitType(DanexParser.TypeContext ctx) {
        return ctx.getText();
    }
    // Other rules omitted for brevity; add as needed.
            }
