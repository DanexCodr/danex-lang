// Updated DanexParser.g4
parser grammar DanexParser;
options { tokenVocab=DanexLexer; }

// ------------------------
// PARSER RULES
// ------------------------

compilationUnit
    : statement* EOF
    ;

statement
    : classDecl
    | methodDecl
    | topLevelMethodDecl
    | importStmt
    ;

importStmt
    : USE IDENTIFIER (AS IDENTIFIER)? SEMI
    ;

classDecl
    : annotation* modifier* CLASS IDENTIFIER classBody
    ;

classBody
    : LBRACE classBodyMember* RBRACE
    ;

classBodyMember
    : methodDecl
    | classDecl
    ;

methodDecl
    : annotation* resultDecl? modifier* IDENTIFIER LPAREN paramList? RPAREN methodBody
    ;

topLevelMethodDecl
    : annotation* resultDecl? modifier* IDENTIFIER LPAREN paramList? RPAREN methodBody
    ;

resultDecl
    : LPAREN IDENTIFIER (IDENTIFIER)? RPAREN
    ;

annotation
    : HASH IDENTIFIER
    ;

modifier
    : PUBLIC
    | PRIVATE
    | STATIC
    ;

paramList
    : param (COMMA param)*
    ;

param
    : type (VARARGS IDENTIFIER | IDENTIFIER)
    ;

methodBody
    : block
    | ARROW expression SEMI
    ;

block
    : LBRACE blockContent* RBRACE
    ;

blockContent
    : statement
    | expressionStatement
    | ifStatement
    | whileStatement
    | doWhileStatement
    | forStatement
    | tryStatement
    | exitStatement
    | throwStatement
    | returnStatement
    ;

expressionStatement
    : expression SEMI
    ;

ifStatement
    : IF LPAREN expression RPAREN block (ELSE block)?
    ;

whileStatement
    : WHILE LPAREN expression RPAREN block
    ;

doWhileStatement
    : DO block WHILE LPAREN expression RPAREN SEMI
    ;

forStatement
    : FOR LPAREN assignment SEMI expression SEMI assignment RPAREN block
    ;

tryStatement
    : TRY (resourceSpec)? block (CATCH LPAREN IDENTIFIER IDENTIFIER RPAREN block)? (FINALLY block)?
    ;

resourceSpec
    : LPAREN resourceDecl (SEMI resourceDecl)* RPAREN
    ;

resourceDecl
    : type IDENTIFIER EQ expression
    ;

exitStatement
    : EXIT SEMI
    ;

throwStatement
    : THROW LPAREN expression RPAREN SEMI
    ;

returnStatement
    : RETURN expression? SEMI
    ;

// Allow compound assignments
assignment
    : IDENTIFIER assignOp assignment
    ;

assignOp
    : EQ
    | PLUSEQ
    | MINUSEQ
    | STAREQ
    | DIVEQ
    | MODEQ
    ;

expression
    : lambdaExpr
    | logicalOrExpr
    ;

lambdaExpr
    : LPAREN paramList? RPAREN LAMBDA expression
    ;

logicalOrExpr
    : logicalAndExpr (OR_OR logicalAndExpr)*
    ;

logicalAndExpr
    : nullCoalesceExpr (AND_AND nullCoalesceExpr)*
    ;

nullCoalesceExpr
    : comparisonExpr (NULLCOAL comparisonExpr)*
    ;

comparisonExpr
    : additiveExpr ((LT | GT | LE | GE | COMPARATOR | EQEQ | NEQ) additiveExpr)*
    ;

additiveExpr
    : multiplicativeExpr ((PLUS | MINUS) multiplicativeExpr)*
    ;

multiplicativeExpr
    : unaryExpr ((STAR | DIV | MOD | MOD2) unaryExpr)*
    ;

// Fixed unaryExpr: allow multiple prefix operators but no left recursion
unaryExpr
    : (BANG | PLUS | MINUS | AWAIT)* primaryExpr
    ;

primaryExpr
    : LPAREN expression RPAREN
    | doExpression
    | tryExpression
    | functionCall
    | literal
    | IDENTIFIER
    ;

doExpression
    : DO block
    ;

tryExpression
    : TRY block (CATCH LPAREN IDENTIFIER IDENTIFIER RPAREN block)? (FINALLY block)?
    ;

functionCall
    : IDENTIFIER LPAREN (expression (COMMA expression)*)? RPAREN
    ;

literal
    : NUMBER
    | STRING
    | TRUE
    | FALSE
    | NULL
    ;

type
    : IDENTIFIER
    ;
