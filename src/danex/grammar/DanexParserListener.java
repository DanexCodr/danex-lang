// Generated from grammar/DanexParser.g4 by ANTLR 4.9.2
package danex.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DanexParser}.
 */
public interface DanexParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DanexParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(DanexParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(DanexParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(DanexParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(DanexParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#importStmt}.
	 * @param ctx the parse tree
	 */
	void enterImportStmt(DanexParser.ImportStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#importStmt}.
	 * @param ctx the parse tree
	 */
	void exitImportStmt(DanexParser.ImportStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#classDecl}.
	 * @param ctx the parse tree
	 */
	void enterClassDecl(DanexParser.ClassDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#classDecl}.
	 * @param ctx the parse tree
	 */
	void exitClassDecl(DanexParser.ClassDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(DanexParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(DanexParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#classBodyMember}.
	 * @param ctx the parse tree
	 */
	void enterClassBodyMember(DanexParser.ClassBodyMemberContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#classBodyMember}.
	 * @param ctx the parse tree
	 */
	void exitClassBodyMember(DanexParser.ClassBodyMemberContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#methodDecl}.
	 * @param ctx the parse tree
	 */
	void enterMethodDecl(DanexParser.MethodDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#methodDecl}.
	 * @param ctx the parse tree
	 */
	void exitMethodDecl(DanexParser.MethodDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#topLevelMethodDecl}.
	 * @param ctx the parse tree
	 */
	void enterTopLevelMethodDecl(DanexParser.TopLevelMethodDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#topLevelMethodDecl}.
	 * @param ctx the parse tree
	 */
	void exitTopLevelMethodDecl(DanexParser.TopLevelMethodDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#resultDecl}.
	 * @param ctx the parse tree
	 */
	void enterResultDecl(DanexParser.ResultDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#resultDecl}.
	 * @param ctx the parse tree
	 */
	void exitResultDecl(DanexParser.ResultDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(DanexParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(DanexParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#modifier}.
	 * @param ctx the parse tree
	 */
	void enterModifier(DanexParser.ModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#modifier}.
	 * @param ctx the parse tree
	 */
	void exitModifier(DanexParser.ModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(DanexParser.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(DanexParser.ParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(DanexParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(DanexParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBody(DanexParser.MethodBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBody(DanexParser.MethodBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(DanexParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(DanexParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#blockContent}.
	 * @param ctx the parse tree
	 */
	void enterBlockContent(DanexParser.BlockContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#blockContent}.
	 * @param ctx the parse tree
	 */
	void exitBlockContent(DanexParser.BlockContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(DanexParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(DanexParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(DanexParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(DanexParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(DanexParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(DanexParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(DanexParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(DanexParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#doWhileStatement}.
	 * @param ctx the parse tree
	 */
	void enterDoWhileStatement(DanexParser.DoWhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#doWhileStatement}.
	 * @param ctx the parse tree
	 */
	void exitDoWhileStatement(DanexParser.DoWhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(DanexParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(DanexParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#tryStatement}.
	 * @param ctx the parse tree
	 */
	void enterTryStatement(DanexParser.TryStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#tryStatement}.
	 * @param ctx the parse tree
	 */
	void exitTryStatement(DanexParser.TryStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#resourceSpec}.
	 * @param ctx the parse tree
	 */
	void enterResourceSpec(DanexParser.ResourceSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#resourceSpec}.
	 * @param ctx the parse tree
	 */
	void exitResourceSpec(DanexParser.ResourceSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#resourceDecl}.
	 * @param ctx the parse tree
	 */
	void enterResourceDecl(DanexParser.ResourceDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#resourceDecl}.
	 * @param ctx the parse tree
	 */
	void exitResourceDecl(DanexParser.ResourceDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#exitStatement}.
	 * @param ctx the parse tree
	 */
	void enterExitStatement(DanexParser.ExitStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#exitStatement}.
	 * @param ctx the parse tree
	 */
	void exitExitStatement(DanexParser.ExitStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#throwStatement}.
	 * @param ctx the parse tree
	 */
	void enterThrowStatement(DanexParser.ThrowStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#throwStatement}.
	 * @param ctx the parse tree
	 */
	void exitThrowStatement(DanexParser.ThrowStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(DanexParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(DanexParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(DanexParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(DanexParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#assignOp}.
	 * @param ctx the parse tree
	 */
	void enterAssignOp(DanexParser.AssignOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#assignOp}.
	 * @param ctx the parse tree
	 */
	void exitAssignOp(DanexParser.AssignOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(DanexParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(DanexParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#lambdaExpr}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExpr(DanexParser.LambdaExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#lambdaExpr}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExpr(DanexParser.LambdaExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#logicalOrExpr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpr(DanexParser.LogicalOrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#logicalOrExpr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpr(DanexParser.LogicalOrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#logicalAndExpr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpr(DanexParser.LogicalAndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#logicalAndExpr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpr(DanexParser.LogicalAndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#nullCoalesceExpr}.
	 * @param ctx the parse tree
	 */
	void enterNullCoalesceExpr(DanexParser.NullCoalesceExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#nullCoalesceExpr}.
	 * @param ctx the parse tree
	 */
	void exitNullCoalesceExpr(DanexParser.NullCoalesceExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#comparisonExpr}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpr(DanexParser.ComparisonExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#comparisonExpr}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpr(DanexParser.ComparisonExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr(DanexParser.AdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr(DanexParser.AdditiveExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpr(DanexParser.MultiplicativeExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpr(DanexParser.MultiplicativeExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(DanexParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(DanexParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpr(DanexParser.PrimaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpr(DanexParser.PrimaryExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#doExpression}.
	 * @param ctx the parse tree
	 */
	void enterDoExpression(DanexParser.DoExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#doExpression}.
	 * @param ctx the parse tree
	 */
	void exitDoExpression(DanexParser.DoExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#tryExpression}.
	 * @param ctx the parse tree
	 */
	void enterTryExpression(DanexParser.TryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#tryExpression}.
	 * @param ctx the parse tree
	 */
	void exitTryExpression(DanexParser.TryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(DanexParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(DanexParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(DanexParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(DanexParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanexParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(DanexParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanexParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(DanexParser.TypeContext ctx);
}