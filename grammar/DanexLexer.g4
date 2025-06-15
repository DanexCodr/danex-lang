// Updated DanexLexer.g4
lexer grammar DanexLexer;

// ------------------------
// LEXER RULES
// ------------------------

// Custom comment styles
SINGLE_COMMENT: '#-' ~[\r\n]* -> skip;
MULTI_COMMENT: '##-' .*? '-##' -> skip;

// Whitespace
WS: [ \t\r\n]+ -> skip;

// Annotation marker
HASH: '#';

// Multi-character operators (longer ones first)
ARROW       : '=>';
LAMBDA      : '->';
NULLCOAL    : '??';
COMPARATOR  : '<=>';
MOD2        : '%%';
VARARGS     : '...';
AND_AND     : '&&';
OR_OR       : '||';
EQEQ        : '==';
NEQ         : '!=';
LE          : '<=';
GE          : '>=';
PLUSEQ      : '+=';
MINUSEQ     : '-=';
STAREQ      : '*=';
DIVEQ       : '/=';
MODEQ       : '%=';

// Keywords (place before IDENTIFIER)
IF      : 'if';
ELSE    : 'else';
WHILE   : 'while';
DO      : 'do';
FOR     : 'for';
RETURN  : 'return';
THROW   : 'throw';
EXIT    : 'exit';
CLASS   : 'class';
PUBLIC  : 'public';
PRIVATE : 'private';
STATIC  : 'static';
TRUE    : 'true';
FALSE   : 'false';
NULL    : 'null';
USE     : 'use';
AS      : 'as';
TRY     : 'try';
CATCH   : 'catch';
FINALLY : 'finally';
ASYNC   : 'async';
AWAIT   : 'await';

// Identifiers and literals
IDENTIFIER  : [a-zA-Z_][a-zA-Z0-9_]*;
NUMBER      : [0-9]+ ('.' [0-9]+)?;
STRING      : '"' (~["\\] | '\\' .)* '"';

// Single-character operators and symbols
PLUS    : '+';
MINUS   : '-';
STAR    : '*';
DIV     : '/';
MOD     : '%';
LT      : '<';
GT      : '>';
EQ      : '=';
BANG    : '!';
AND     : '&';
OR      : '|';
LPAREN  : '(';
RPAREN  : ')';
LBRACE  : '{';
RBRACE  : '}';
SEMI    : ';';
COMMA   : ',';
