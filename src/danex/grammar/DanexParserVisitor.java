// Generated from grammar/DanexParser.g4 by ANTLR 4.9.2
package danex.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DanexParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DanexParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DanexParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(DanexParser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(DanexParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#importStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportStmt(DanexParser.ImportStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#classDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDecl(DanexParser.ClassDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#classBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBody(DanexParser.ClassBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#classBodyMember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBodyMember(DanexParser.ClassBodyMemberContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#methodDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDecl(DanexParser.MethodDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#topLevelMethodDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopLevelMethodDecl(DanexParser.TopLevelMethodDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#resultDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResultDecl(DanexParser.ResultDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotation(DanexParser.AnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModifier(DanexParser.ModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(DanexParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(DanexParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#methodBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodBody(DanexParser.MethodBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(DanexParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#blockContent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockContent(DanexParser.BlockContentContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(DanexParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(DanexParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(DanexParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#doWhileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoWhileStatement(DanexParser.DoWhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(DanexParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#tryStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTryStatement(DanexParser.TryStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#resourceSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResourceSpec(DanexParser.ResourceSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#resourceDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResourceDecl(DanexParser.ResourceDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#exitStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExitStatement(DanexParser.ExitStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#throwStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThrowStatement(DanexParser.ThrowStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(DanexParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(DanexParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#assignOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignOp(DanexParser.AssignOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(DanexParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#lambdaExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaExpr(DanexParser.LambdaExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#logicalOrExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpr(DanexParser.LogicalOrExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#logicalAndExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpr(DanexParser.LogicalAndExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#nullCoalesceExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullCoalesceExpr(DanexParser.NullCoalesceExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#comparisonExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpr(DanexParser.ComparisonExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#additiveExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpr(DanexParser.AdditiveExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpr(DanexParser.MultiplicativeExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#unaryExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(DanexParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#primaryExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpr(DanexParser.PrimaryExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#doExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoExpression(DanexParser.DoExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#tryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTryExpression(DanexParser.TryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(DanexParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(DanexParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link DanexParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(DanexParser.TypeContext ctx);
}