// Generated from grammar/DanexParser.g4 by ANTLR 4.9.2
package danex.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DanexParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SINGLE_COMMENT=1, MULTI_COMMENT=2, WS=3, HASH=4, ARROW=5, LAMBDA=6, NULLCOAL=7, 
		COMPARATOR=8, MOD2=9, VARARGS=10, AND_AND=11, OR_OR=12, EQEQ=13, NEQ=14, 
		LE=15, GE=16, PLUSEQ=17, MINUSEQ=18, STAREQ=19, DIVEQ=20, MODEQ=21, IF=22, 
		ELSE=23, WHILE=24, DO=25, FOR=26, RETURN=27, THROW=28, EXIT=29, CLASS=30, 
		PUBLIC=31, PRIVATE=32, STATIC=33, TRUE=34, FALSE=35, NULL=36, USE=37, 
		AS=38, TRY=39, CATCH=40, FINALLY=41, ASYNC=42, AWAIT=43, IDENTIFIER=44, 
		NUMBER=45, STRING=46, PLUS=47, MINUS=48, STAR=49, DIV=50, MOD=51, LT=52, 
		GT=53, EQ=54, BANG=55, AND=56, OR=57, LPAREN=58, RPAREN=59, LBRACE=60, 
		RBRACE=61, SEMI=62, COMMA=63;
	public static final int
		RULE_compilationUnit = 0, RULE_statement = 1, RULE_importStmt = 2, RULE_classDecl = 3, 
		RULE_classBody = 4, RULE_classBodyMember = 5, RULE_methodDecl = 6, RULE_topLevelMethodDecl = 7, 
		RULE_resultDecl = 8, RULE_annotation = 9, RULE_modifier = 10, RULE_paramList = 11, 
		RULE_param = 12, RULE_methodBody = 13, RULE_block = 14, RULE_blockContent = 15, 
		RULE_varDecl = 16, RULE_expressionStatement = 17, RULE_ifStatement = 18, 
		RULE_whileStatement = 19, RULE_doWhileStatement = 20, RULE_forStatement = 21, 
		RULE_tryStatement = 22, RULE_resourceSpec = 23, RULE_resourceDecl = 24, 
		RULE_exitStatement = 25, RULE_throwStatement = 26, RULE_returnStatement = 27, 
		RULE_assignment = 28, RULE_assignOp = 29, RULE_expression = 30, RULE_lambdaExpr = 31, 
		RULE_logicalOrExpr = 32, RULE_logicalAndExpr = 33, RULE_nullCoalesceExpr = 34, 
		RULE_comparisonExpr = 35, RULE_additiveExpr = 36, RULE_multiplicativeExpr = 37, 
		RULE_unaryExpr = 38, RULE_primaryExpr = 39, RULE_doExpression = 40, RULE_tryExpression = 41, 
		RULE_functionCall = 42, RULE_literal = 43, RULE_type = 44;
	private static String[] makeRuleNames() {
		return new String[] {
			"compilationUnit", "statement", "importStmt", "classDecl", "classBody", 
			"classBodyMember", "methodDecl", "topLevelMethodDecl", "resultDecl", 
			"annotation", "modifier", "paramList", "param", "methodBody", "block", 
			"blockContent", "varDecl", "expressionStatement", "ifStatement", "whileStatement", 
			"doWhileStatement", "forStatement", "tryStatement", "resourceSpec", "resourceDecl", 
			"exitStatement", "throwStatement", "returnStatement", "assignment", "assignOp", 
			"expression", "lambdaExpr", "logicalOrExpr", "logicalAndExpr", "nullCoalesceExpr", 
			"comparisonExpr", "additiveExpr", "multiplicativeExpr", "unaryExpr", 
			"primaryExpr", "doExpression", "tryExpression", "functionCall", "literal", 
			"type"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'#'", "'=>'", "'->'", "'??'", "'<=>'", "'%%'", 
			"'...'", "'&&'", "'||'", "'=='", "'!='", "'<='", "'>='", "'+='", "'-='", 
			"'*='", "'/='", "'%='", "'if'", "'else'", "'while'", "'do'", "'for'", 
			"'return'", "'throw'", "'exit'", "'class'", "'public'", "'private'", 
			"'static'", "'true'", "'false'", "'null'", "'use'", "'as'", "'try'", 
			"'catch'", "'finally'", "'async'", "'await'", null, null, null, "'+'", 
			"'-'", "'*'", "'/'", "'%'", "'<'", "'>'", "'='", "'!'", "'&'", "'|'", 
			"'('", "')'", "'{'", "'}'", "';'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SINGLE_COMMENT", "MULTI_COMMENT", "WS", "HASH", "ARROW", "LAMBDA", 
			"NULLCOAL", "COMPARATOR", "MOD2", "VARARGS", "AND_AND", "OR_OR", "EQEQ", 
			"NEQ", "LE", "GE", "PLUSEQ", "MINUSEQ", "STAREQ", "DIVEQ", "MODEQ", "IF", 
			"ELSE", "WHILE", "DO", "FOR", "RETURN", "THROW", "EXIT", "CLASS", "PUBLIC", 
			"PRIVATE", "STATIC", "TRUE", "FALSE", "NULL", "USE", "AS", "TRY", "CATCH", 
			"FINALLY", "ASYNC", "AWAIT", "IDENTIFIER", "NUMBER", "STRING", "PLUS", 
			"MINUS", "STAR", "DIV", "MOD", "LT", "GT", "EQ", "BANG", "AND", "OR", 
			"LPAREN", "RPAREN", "LBRACE", "RBRACE", "SEMI", "COMMA"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "DanexParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DanexParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class CompilationUnitContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(DanexParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterCompilationUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitCompilationUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitCompilationUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compilationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HASH) | (1L << CLASS) | (1L << PUBLIC) | (1L << PRIVATE) | (1L << STATIC) | (1L << USE) | (1L << IDENTIFIER) | (1L << LPAREN))) != 0)) {
				{
				{
				setState(90);
				statement();
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(96);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public ClassDeclContext classDecl() {
			return getRuleContext(ClassDeclContext.class,0);
		}
		public MethodDeclContext methodDecl() {
			return getRuleContext(MethodDeclContext.class,0);
		}
		public ImportStmtContext importStmt() {
			return getRuleContext(ImportStmtContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(101);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				classDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				methodDecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(100);
				importStmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportStmtContext extends ParserRuleContext {
		public TerminalNode USE() { return getToken(DanexParser.USE, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(DanexParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(DanexParser.IDENTIFIER, i);
		}
		public TerminalNode SEMI() { return getToken(DanexParser.SEMI, 0); }
		public TerminalNode AS() { return getToken(DanexParser.AS, 0); }
		public ImportStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterImportStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitImportStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitImportStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportStmtContext importStmt() throws RecognitionException {
		ImportStmtContext _localctx = new ImportStmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_importStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			match(USE);
			setState(104);
			match(IDENTIFIER);
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(105);
				match(AS);
				setState(106);
				match(IDENTIFIER);
				}
			}

			setState(109);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(DanexParser.CLASS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(DanexParser.IDENTIFIER, 0); }
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public ClassDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterClassDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitClassDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitClassDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclContext classDecl() throws RecognitionException {
		ClassDeclContext _localctx = new ClassDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_classDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==HASH) {
				{
				{
				setState(111);
				annotation();
				}
				}
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PUBLIC) | (1L << PRIVATE) | (1L << STATIC))) != 0)) {
				{
				{
				setState(117);
				modifier();
				}
				}
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(123);
			match(CLASS);
			setState(124);
			match(IDENTIFIER);
			setState(125);
			classBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(DanexParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(DanexParser.RBRACE, 0); }
		public List<ClassBodyMemberContext> classBodyMember() {
			return getRuleContexts(ClassBodyMemberContext.class);
		}
		public ClassBodyMemberContext classBodyMember(int i) {
			return getRuleContext(ClassBodyMemberContext.class,i);
		}
		public ClassBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterClassBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitClassBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitClassBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassBodyContext classBody() throws RecognitionException {
		ClassBodyContext _localctx = new ClassBodyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_classBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(LBRACE);
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HASH) | (1L << CLASS) | (1L << PUBLIC) | (1L << PRIVATE) | (1L << STATIC) | (1L << IDENTIFIER) | (1L << LPAREN))) != 0)) {
				{
				{
				setState(128);
				classBodyMember();
				}
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(134);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBodyMemberContext extends ParserRuleContext {
		public MethodDeclContext methodDecl() {
			return getRuleContext(MethodDeclContext.class,0);
		}
		public ClassDeclContext classDecl() {
			return getRuleContext(ClassDeclContext.class,0);
		}
		public ClassBodyMemberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBodyMember; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterClassBodyMember(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitClassBodyMember(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitClassBodyMember(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassBodyMemberContext classBodyMember() throws RecognitionException {
		ClassBodyMemberContext _localctx = new ClassBodyMemberContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_classBodyMember);
		try {
			setState(138);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				methodDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				classDecl();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDeclContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(DanexParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(DanexParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DanexParser.RPAREN, 0); }
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public ResultDeclContext resultDecl() {
			return getRuleContext(ResultDeclContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public MethodDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterMethodDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitMethodDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitMethodDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodDeclContext methodDecl() throws RecognitionException {
		MethodDeclContext _localctx = new MethodDeclContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_methodDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==HASH) {
				{
				{
				setState(140);
				annotation();
				}
				}
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(146);
				resultDecl();
				}
			}

			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PUBLIC) | (1L << PRIVATE) | (1L << STATIC))) != 0)) {
				{
				{
				setState(149);
				modifier();
				}
				}
				setState(154);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(155);
			match(IDENTIFIER);
			setState(156);
			match(LPAREN);
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(157);
				paramList();
				}
			}

			setState(160);
			match(RPAREN);
			setState(161);
			methodBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TopLevelMethodDeclContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(DanexParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(DanexParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DanexParser.RPAREN, 0); }
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public ResultDeclContext resultDecl() {
			return getRuleContext(ResultDeclContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public TopLevelMethodDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topLevelMethodDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterTopLevelMethodDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitTopLevelMethodDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitTopLevelMethodDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TopLevelMethodDeclContext topLevelMethodDecl() throws RecognitionException {
		TopLevelMethodDeclContext _localctx = new TopLevelMethodDeclContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_topLevelMethodDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==HASH) {
				{
				{
				setState(163);
				annotation();
				}
				}
				setState(168);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(169);
				resultDecl();
				}
			}

			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PUBLIC) | (1L << PRIVATE) | (1L << STATIC))) != 0)) {
				{
				{
				setState(172);
				modifier();
				}
				}
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(178);
			match(IDENTIFIER);
			setState(179);
			match(LPAREN);
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(180);
				paramList();
				}
			}

			setState(183);
			match(RPAREN);
			setState(184);
			methodBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResultDeclContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(DanexParser.LPAREN, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(DanexParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(DanexParser.IDENTIFIER, i);
		}
		public TerminalNode RPAREN() { return getToken(DanexParser.RPAREN, 0); }
		public ResultDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resultDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterResultDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitResultDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitResultDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResultDeclContext resultDecl() throws RecognitionException {
		ResultDeclContext _localctx = new ResultDeclContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_resultDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(LPAREN);
			setState(187);
			match(IDENTIFIER);
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(188);
				match(IDENTIFIER);
				}
			}

			setState(191);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationContext extends ParserRuleContext {
		public TerminalNode HASH() { return getToken(DanexParser.HASH, 0); }
		public TerminalNode IDENTIFIER() { return getToken(DanexParser.IDENTIFIER, 0); }
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitAnnotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitAnnotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_annotation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(HASH);
			setState(194);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModifierContext extends ParserRuleContext {
		public TerminalNode PUBLIC() { return getToken(DanexParser.PUBLIC, 0); }
		public TerminalNode PRIVATE() { return getToken(DanexParser.PRIVATE, 0); }
		public TerminalNode STATIC() { return getToken(DanexParser.STATIC, 0); }
		public ModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PUBLIC) | (1L << PRIVATE) | (1L << STATIC))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DanexParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DanexParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitParamList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			param();
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(199);
				match(COMMA);
				setState(200);
				param();
				}
				}
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode VARARGS() { return getToken(DanexParser.VARARGS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(DanexParser.IDENTIFIER, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			type();
			setState(210);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARARGS:
				{
				setState(207);
				match(VARARGS);
				setState(208);
				match(IDENTIFIER);
				}
				break;
			case IDENTIFIER:
				{
				setState(209);
				match(IDENTIFIER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodBodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(DanexParser.ARROW, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(DanexParser.SEMI, 0); }
		public MethodBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterMethodBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitMethodBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitMethodBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodBodyContext methodBody() throws RecognitionException {
		MethodBodyContext _localctx = new MethodBodyContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_methodBody);
		try {
			setState(217);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(212);
				block();
				}
				break;
			case ARROW:
				enterOuterAlt(_localctx, 2);
				{
				setState(213);
				match(ARROW);
				setState(214);
				expression();
				setState(215);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(DanexParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(DanexParser.RBRACE, 0); }
		public List<BlockContentContext> blockContent() {
			return getRuleContexts(BlockContentContext.class);
		}
		public BlockContentContext blockContent(int i) {
			return getRuleContext(BlockContentContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(LBRACE);
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HASH) | (1L << IF) | (1L << WHILE) | (1L << DO) | (1L << FOR) | (1L << RETURN) | (1L << THROW) | (1L << EXIT) | (1L << CLASS) | (1L << PUBLIC) | (1L << PRIVATE) | (1L << STATIC) | (1L << TRUE) | (1L << FALSE) | (1L << NULL) | (1L << USE) | (1L << TRY) | (1L << AWAIT) | (1L << IDENTIFIER) | (1L << NUMBER) | (1L << STRING) | (1L << PLUS) | (1L << MINUS) | (1L << BANG) | (1L << LPAREN))) != 0)) {
				{
				{
				setState(220);
				blockContent();
				}
				}
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(226);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContentContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public DoWhileStatementContext doWhileStatement() {
			return getRuleContext(DoWhileStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public TryStatementContext tryStatement() {
			return getRuleContext(TryStatementContext.class,0);
		}
		public ExitStatementContext exitStatement() {
			return getRuleContext(ExitStatementContext.class,0);
		}
		public ThrowStatementContext throwStatement() {
			return getRuleContext(ThrowStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public BlockContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockContent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterBlockContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitBlockContent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitBlockContent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContentContext blockContent() throws RecognitionException {
		BlockContentContext _localctx = new BlockContentContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_blockContent);
		try {
			setState(239);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(228);
				statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(229);
				varDecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(230);
				expressionStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(231);
				ifStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(232);
				whileStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(233);
				doWhileStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(234);
				forStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(235);
				tryStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(236);
				exitStatement();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(237);
				throwStatement();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(238);
				returnStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(DanexParser.IDENTIFIER, 0); }
		public TerminalNode SEMI() { return getToken(DanexParser.SEMI, 0); }
		public TerminalNode EQ() { return getToken(DanexParser.EQ, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_varDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			type();
			setState(242);
			match(IDENTIFIER);
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EQ) {
				{
				setState(243);
				match(EQ);
				setState(244);
				expression();
				}
			}

			setState(247);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(DanexParser.SEMI, 0); }
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitExpressionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitExpressionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			expression();
			setState(250);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(DanexParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(DanexParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DanexParser.RPAREN, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(DanexParser.ELSE, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			match(IF);
			setState(253);
			match(LPAREN);
			setState(254);
			expression();
			setState(255);
			match(RPAREN);
			setState(256);
			block();
			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(257);
				match(ELSE);
				setState(258);
				block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileStatementContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(DanexParser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(DanexParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DanexParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			match(WHILE);
			setState(262);
			match(LPAREN);
			setState(263);
			expression();
			setState(264);
			match(RPAREN);
			setState(265);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoWhileStatementContext extends ParserRuleContext {
		public TerminalNode DO() { return getToken(DanexParser.DO, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(DanexParser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(DanexParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DanexParser.RPAREN, 0); }
		public TerminalNode SEMI() { return getToken(DanexParser.SEMI, 0); }
		public DoWhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doWhileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterDoWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitDoWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitDoWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoWhileStatementContext doWhileStatement() throws RecognitionException {
		DoWhileStatementContext _localctx = new DoWhileStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_doWhileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			match(DO);
			setState(268);
			block();
			setState(269);
			match(WHILE);
			setState(270);
			match(LPAREN);
			setState(271);
			expression();
			setState(272);
			match(RPAREN);
			setState(273);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStatementContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(DanexParser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(DanexParser.LPAREN, 0); }
		public List<AssignmentContext> assignment() {
			return getRuleContexts(AssignmentContext.class);
		}
		public AssignmentContext assignment(int i) {
			return getRuleContext(AssignmentContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(DanexParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(DanexParser.SEMI, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DanexParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_forStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			match(FOR);
			setState(276);
			match(LPAREN);
			setState(277);
			assignment();
			setState(278);
			match(SEMI);
			setState(279);
			expression();
			setState(280);
			match(SEMI);
			setState(281);
			assignment();
			setState(282);
			match(RPAREN);
			setState(283);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TryStatementContext extends ParserRuleContext {
		public TerminalNode TRY() { return getToken(DanexParser.TRY, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public ResourceSpecContext resourceSpec() {
			return getRuleContext(ResourceSpecContext.class,0);
		}
		public TerminalNode CATCH() { return getToken(DanexParser.CATCH, 0); }
		public TerminalNode LPAREN() { return getToken(DanexParser.LPAREN, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(DanexParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(DanexParser.IDENTIFIER, i);
		}
		public TerminalNode RPAREN() { return getToken(DanexParser.RPAREN, 0); }
		public TerminalNode FINALLY() { return getToken(DanexParser.FINALLY, 0); }
		public TryStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tryStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterTryStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitTryStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitTryStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TryStatementContext tryStatement() throws RecognitionException {
		TryStatementContext _localctx = new TryStatementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_tryStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(TRY);
			setState(287);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(286);
				resourceSpec();
				}
			}

			setState(289);
			block();
			setState(296);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CATCH) {
				{
				setState(290);
				match(CATCH);
				setState(291);
				match(LPAREN);
				setState(292);
				match(IDENTIFIER);
				setState(293);
				match(IDENTIFIER);
				setState(294);
				match(RPAREN);
				setState(295);
				block();
				}
			}

			setState(300);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FINALLY) {
				{
				setState(298);
				match(FINALLY);
				setState(299);
				block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResourceSpecContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(DanexParser.LPAREN, 0); }
		public List<ResourceDeclContext> resourceDecl() {
			return getRuleContexts(ResourceDeclContext.class);
		}
		public ResourceDeclContext resourceDecl(int i) {
			return getRuleContext(ResourceDeclContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(DanexParser.RPAREN, 0); }
		public List<TerminalNode> SEMI() { return getTokens(DanexParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(DanexParser.SEMI, i);
		}
		public ResourceSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resourceSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterResourceSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitResourceSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitResourceSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResourceSpecContext resourceSpec() throws RecognitionException {
		ResourceSpecContext _localctx = new ResourceSpecContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_resourceSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			match(LPAREN);
			setState(303);
			resourceDecl();
			setState(308);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMI) {
				{
				{
				setState(304);
				match(SEMI);
				setState(305);
				resourceDecl();
				}
				}
				setState(310);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(311);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResourceDeclContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(DanexParser.IDENTIFIER, 0); }
		public TerminalNode EQ() { return getToken(DanexParser.EQ, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ResourceDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resourceDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterResourceDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitResourceDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitResourceDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResourceDeclContext resourceDecl() throws RecognitionException {
		ResourceDeclContext _localctx = new ResourceDeclContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_resourceDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			type();
			setState(314);
			match(IDENTIFIER);
			setState(315);
			match(EQ);
			setState(316);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExitStatementContext extends ParserRuleContext {
		public TerminalNode EXIT() { return getToken(DanexParser.EXIT, 0); }
		public TerminalNode SEMI() { return getToken(DanexParser.SEMI, 0); }
		public ExitStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exitStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterExitStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitExitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitExitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExitStatementContext exitStatement() throws RecognitionException {
		ExitStatementContext _localctx = new ExitStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_exitStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			match(EXIT);
			setState(319);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ThrowStatementContext extends ParserRuleContext {
		public TerminalNode THROW() { return getToken(DanexParser.THROW, 0); }
		public TerminalNode LPAREN() { return getToken(DanexParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DanexParser.RPAREN, 0); }
		public TerminalNode SEMI() { return getToken(DanexParser.SEMI, 0); }
		public ThrowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throwStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterThrowStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitThrowStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitThrowStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ThrowStatementContext throwStatement() throws RecognitionException {
		ThrowStatementContext _localctx = new ThrowStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_throwStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
			match(THROW);
			setState(322);
			match(LPAREN);
			setState(323);
			expression();
			setState(324);
			match(RPAREN);
			setState(325);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(DanexParser.RETURN, 0); }
		public TerminalNode SEMI() { return getToken(DanexParser.SEMI, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
			match(RETURN);
			setState(329);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DO) | (1L << TRUE) | (1L << FALSE) | (1L << NULL) | (1L << TRY) | (1L << AWAIT) | (1L << IDENTIFIER) | (1L << NUMBER) | (1L << STRING) | (1L << PLUS) | (1L << MINUS) | (1L << BANG) | (1L << LPAREN))) != 0)) {
				{
				setState(328);
				expression();
				}
			}

			setState(331);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(DanexParser.IDENTIFIER, 0); }
		public AssignOpContext assignOp() {
			return getRuleContext(AssignOpContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			match(IDENTIFIER);
			setState(334);
			assignOp();
			setState(335);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignOpContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(DanexParser.EQ, 0); }
		public TerminalNode PLUSEQ() { return getToken(DanexParser.PLUSEQ, 0); }
		public TerminalNode MINUSEQ() { return getToken(DanexParser.MINUSEQ, 0); }
		public TerminalNode STAREQ() { return getToken(DanexParser.STAREQ, 0); }
		public TerminalNode DIVEQ() { return getToken(DanexParser.DIVEQ, 0); }
		public TerminalNode MODEQ() { return getToken(DanexParser.MODEQ, 0); }
		public AssignOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterAssignOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitAssignOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitAssignOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignOpContext assignOp() throws RecognitionException {
		AssignOpContext _localctx = new AssignOpContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_assignOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(337);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUSEQ) | (1L << MINUSEQ) | (1L << STAREQ) | (1L << DIVEQ) | (1L << MODEQ) | (1L << EQ))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public LambdaExprContext lambdaExpr() {
			return getRuleContext(LambdaExprContext.class,0);
		}
		public LogicalOrExprContext logicalOrExpr() {
			return getRuleContext(LogicalOrExprContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_expression);
		try {
			setState(341);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(339);
				lambdaExpr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(340);
				logicalOrExpr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LambdaExprContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(DanexParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DanexParser.RPAREN, 0); }
		public TerminalNode LAMBDA() { return getToken(DanexParser.LAMBDA, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public LambdaExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterLambdaExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitLambdaExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitLambdaExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaExprContext lambdaExpr() throws RecognitionException {
		LambdaExprContext _localctx = new LambdaExprContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_lambdaExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			match(LPAREN);
			setState(345);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(344);
				paramList();
				}
			}

			setState(347);
			match(RPAREN);
			setState(348);
			match(LAMBDA);
			setState(349);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicalOrExprContext extends ParserRuleContext {
		public List<LogicalAndExprContext> logicalAndExpr() {
			return getRuleContexts(LogicalAndExprContext.class);
		}
		public LogicalAndExprContext logicalAndExpr(int i) {
			return getRuleContext(LogicalAndExprContext.class,i);
		}
		public List<TerminalNode> OR_OR() { return getTokens(DanexParser.OR_OR); }
		public TerminalNode OR_OR(int i) {
			return getToken(DanexParser.OR_OR, i);
		}
		public LogicalOrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOrExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterLogicalOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitLogicalOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitLogicalOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalOrExprContext logicalOrExpr() throws RecognitionException {
		LogicalOrExprContext _localctx = new LogicalOrExprContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_logicalOrExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(351);
			logicalAndExpr();
			setState(356);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR_OR) {
				{
				{
				setState(352);
				match(OR_OR);
				setState(353);
				logicalAndExpr();
				}
				}
				setState(358);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicalAndExprContext extends ParserRuleContext {
		public List<NullCoalesceExprContext> nullCoalesceExpr() {
			return getRuleContexts(NullCoalesceExprContext.class);
		}
		public NullCoalesceExprContext nullCoalesceExpr(int i) {
			return getRuleContext(NullCoalesceExprContext.class,i);
		}
		public List<TerminalNode> AND_AND() { return getTokens(DanexParser.AND_AND); }
		public TerminalNode AND_AND(int i) {
			return getToken(DanexParser.AND_AND, i);
		}
		public LogicalAndExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalAndExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterLogicalAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitLogicalAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitLogicalAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalAndExprContext logicalAndExpr() throws RecognitionException {
		LogicalAndExprContext _localctx = new LogicalAndExprContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_logicalAndExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			nullCoalesceExpr();
			setState(364);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND_AND) {
				{
				{
				setState(360);
				match(AND_AND);
				setState(361);
				nullCoalesceExpr();
				}
				}
				setState(366);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NullCoalesceExprContext extends ParserRuleContext {
		public List<ComparisonExprContext> comparisonExpr() {
			return getRuleContexts(ComparisonExprContext.class);
		}
		public ComparisonExprContext comparisonExpr(int i) {
			return getRuleContext(ComparisonExprContext.class,i);
		}
		public List<TerminalNode> NULLCOAL() { return getTokens(DanexParser.NULLCOAL); }
		public TerminalNode NULLCOAL(int i) {
			return getToken(DanexParser.NULLCOAL, i);
		}
		public NullCoalesceExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nullCoalesceExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterNullCoalesceExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitNullCoalesceExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitNullCoalesceExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NullCoalesceExprContext nullCoalesceExpr() throws RecognitionException {
		NullCoalesceExprContext _localctx = new NullCoalesceExprContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_nullCoalesceExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(367);
			comparisonExpr();
			setState(372);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NULLCOAL) {
				{
				{
				setState(368);
				match(NULLCOAL);
				setState(369);
				comparisonExpr();
				}
				}
				setState(374);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparisonExprContext extends ParserRuleContext {
		public List<AdditiveExprContext> additiveExpr() {
			return getRuleContexts(AdditiveExprContext.class);
		}
		public AdditiveExprContext additiveExpr(int i) {
			return getRuleContext(AdditiveExprContext.class,i);
		}
		public List<TerminalNode> LT() { return getTokens(DanexParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(DanexParser.LT, i);
		}
		public List<TerminalNode> GT() { return getTokens(DanexParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(DanexParser.GT, i);
		}
		public List<TerminalNode> LE() { return getTokens(DanexParser.LE); }
		public TerminalNode LE(int i) {
			return getToken(DanexParser.LE, i);
		}
		public List<TerminalNode> GE() { return getTokens(DanexParser.GE); }
		public TerminalNode GE(int i) {
			return getToken(DanexParser.GE, i);
		}
		public List<TerminalNode> COMPARATOR() { return getTokens(DanexParser.COMPARATOR); }
		public TerminalNode COMPARATOR(int i) {
			return getToken(DanexParser.COMPARATOR, i);
		}
		public List<TerminalNode> EQEQ() { return getTokens(DanexParser.EQEQ); }
		public TerminalNode EQEQ(int i) {
			return getToken(DanexParser.EQEQ, i);
		}
		public List<TerminalNode> NEQ() { return getTokens(DanexParser.NEQ); }
		public TerminalNode NEQ(int i) {
			return getToken(DanexParser.NEQ, i);
		}
		public ComparisonExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterComparisonExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitComparisonExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitComparisonExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonExprContext comparisonExpr() throws RecognitionException {
		ComparisonExprContext _localctx = new ComparisonExprContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_comparisonExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			additiveExpr();
			setState(380);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMPARATOR) | (1L << EQEQ) | (1L << NEQ) | (1L << LE) | (1L << GE) | (1L << LT) | (1L << GT))) != 0)) {
				{
				{
				setState(376);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMPARATOR) | (1L << EQEQ) | (1L << NEQ) | (1L << LE) | (1L << GE) | (1L << LT) | (1L << GT))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(377);
				additiveExpr();
				}
				}
				setState(382);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AdditiveExprContext extends ParserRuleContext {
		public List<MultiplicativeExprContext> multiplicativeExpr() {
			return getRuleContexts(MultiplicativeExprContext.class);
		}
		public MultiplicativeExprContext multiplicativeExpr(int i) {
			return getRuleContext(MultiplicativeExprContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(DanexParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(DanexParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(DanexParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(DanexParser.MINUS, i);
		}
		public AdditiveExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterAdditiveExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitAdditiveExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitAdditiveExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExprContext additiveExpr() throws RecognitionException {
		AdditiveExprContext _localctx = new AdditiveExprContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_additiveExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			multiplicativeExpr();
			setState(388);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(384);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(385);
				multiplicativeExpr();
				}
				}
				setState(390);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicativeExprContext extends ParserRuleContext {
		public List<UnaryExprContext> unaryExpr() {
			return getRuleContexts(UnaryExprContext.class);
		}
		public UnaryExprContext unaryExpr(int i) {
			return getRuleContext(UnaryExprContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(DanexParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(DanexParser.STAR, i);
		}
		public List<TerminalNode> DIV() { return getTokens(DanexParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(DanexParser.DIV, i);
		}
		public List<TerminalNode> MOD() { return getTokens(DanexParser.MOD); }
		public TerminalNode MOD(int i) {
			return getToken(DanexParser.MOD, i);
		}
		public List<TerminalNode> MOD2() { return getTokens(DanexParser.MOD2); }
		public TerminalNode MOD2(int i) {
			return getToken(DanexParser.MOD2, i);
		}
		public MultiplicativeExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterMultiplicativeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitMultiplicativeExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitMultiplicativeExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExprContext multiplicativeExpr() throws RecognitionException {
		MultiplicativeExprContext _localctx = new MultiplicativeExprContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_multiplicativeExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			unaryExpr();
			setState(396);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MOD2) | (1L << STAR) | (1L << DIV) | (1L << MOD))) != 0)) {
				{
				{
				setState(392);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MOD2) | (1L << STAR) | (1L << DIV) | (1L << MOD))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(393);
				unaryExpr();
				}
				}
				setState(398);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryExprContext extends ParserRuleContext {
		public PrimaryExprContext primaryExpr() {
			return getRuleContext(PrimaryExprContext.class,0);
		}
		public List<TerminalNode> BANG() { return getTokens(DanexParser.BANG); }
		public TerminalNode BANG(int i) {
			return getToken(DanexParser.BANG, i);
		}
		public List<TerminalNode> PLUS() { return getTokens(DanexParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(DanexParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(DanexParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(DanexParser.MINUS, i);
		}
		public List<TerminalNode> AWAIT() { return getTokens(DanexParser.AWAIT); }
		public TerminalNode AWAIT(int i) {
			return getToken(DanexParser.AWAIT, i);
		}
		public UnaryExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitUnaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExprContext unaryExpr() throws RecognitionException {
		UnaryExprContext _localctx = new UnaryExprContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_unaryExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AWAIT) | (1L << PLUS) | (1L << MINUS) | (1L << BANG))) != 0)) {
				{
				{
				setState(399);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AWAIT) | (1L << PLUS) | (1L << MINUS) | (1L << BANG))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(404);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(405);
			primaryExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryExprContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(DanexParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DanexParser.RPAREN, 0); }
		public DoExpressionContext doExpression() {
			return getRuleContext(DoExpressionContext.class,0);
		}
		public TryExpressionContext tryExpression() {
			return getRuleContext(TryExpressionContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(DanexParser.IDENTIFIER, 0); }
		public PrimaryExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterPrimaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitPrimaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitPrimaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExprContext primaryExpr() throws RecognitionException {
		PrimaryExprContext _localctx = new PrimaryExprContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_primaryExpr);
		try {
			setState(416);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(407);
				match(LPAREN);
				setState(408);
				expression();
				setState(409);
				match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(411);
				doExpression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(412);
				tryExpression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(413);
				functionCall();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(414);
				literal();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(415);
				match(IDENTIFIER);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoExpressionContext extends ParserRuleContext {
		public TerminalNode DO() { return getToken(DanexParser.DO, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public DoExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterDoExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitDoExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitDoExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoExpressionContext doExpression() throws RecognitionException {
		DoExpressionContext _localctx = new DoExpressionContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_doExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			match(DO);
			setState(419);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TryExpressionContext extends ParserRuleContext {
		public TerminalNode TRY() { return getToken(DanexParser.TRY, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode CATCH() { return getToken(DanexParser.CATCH, 0); }
		public TerminalNode LPAREN() { return getToken(DanexParser.LPAREN, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(DanexParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(DanexParser.IDENTIFIER, i);
		}
		public TerminalNode RPAREN() { return getToken(DanexParser.RPAREN, 0); }
		public TerminalNode FINALLY() { return getToken(DanexParser.FINALLY, 0); }
		public TryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterTryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitTryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitTryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TryExpressionContext tryExpression() throws RecognitionException {
		TryExpressionContext _localctx = new TryExpressionContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_tryExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421);
			match(TRY);
			setState(422);
			block();
			setState(429);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CATCH) {
				{
				setState(423);
				match(CATCH);
				setState(424);
				match(LPAREN);
				setState(425);
				match(IDENTIFIER);
				setState(426);
				match(IDENTIFIER);
				setState(427);
				match(RPAREN);
				setState(428);
				block();
				}
			}

			setState(433);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FINALLY) {
				{
				setState(431);
				match(FINALLY);
				setState(432);
				block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(DanexParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(DanexParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DanexParser.RPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DanexParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DanexParser.COMMA, i);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			match(IDENTIFIER);
			setState(436);
			match(LPAREN);
			setState(445);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DO) | (1L << TRUE) | (1L << FALSE) | (1L << NULL) | (1L << TRY) | (1L << AWAIT) | (1L << IDENTIFIER) | (1L << NUMBER) | (1L << STRING) | (1L << PLUS) | (1L << MINUS) | (1L << BANG) | (1L << LPAREN))) != 0)) {
				{
				setState(437);
				expression();
				setState(442);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(438);
					match(COMMA);
					setState(439);
					expression();
					}
					}
					setState(444);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(447);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(DanexParser.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(DanexParser.STRING, 0); }
		public TerminalNode TRUE() { return getToken(DanexParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(DanexParser.FALSE, 0); }
		public TerminalNode NULL() { return getToken(DanexParser.NULL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(449);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << NULL) | (1L << NUMBER) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(DanexParser.IDENTIFIER, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanexParserListener ) ((DanexParserListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DanexParserVisitor ) return ((DanexParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(451);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3A\u01c8\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\3\2\7\2^\n\2\f\2\16\2a\13\2\3\2\3\2\3\3\3\3\3\3\5\3h"+
		"\n\3\3\4\3\4\3\4\3\4\5\4n\n\4\3\4\3\4\3\5\7\5s\n\5\f\5\16\5v\13\5\3\5"+
		"\7\5y\n\5\f\5\16\5|\13\5\3\5\3\5\3\5\3\5\3\6\3\6\7\6\u0084\n\6\f\6\16"+
		"\6\u0087\13\6\3\6\3\6\3\7\3\7\5\7\u008d\n\7\3\b\7\b\u0090\n\b\f\b\16\b"+
		"\u0093\13\b\3\b\5\b\u0096\n\b\3\b\7\b\u0099\n\b\f\b\16\b\u009c\13\b\3"+
		"\b\3\b\3\b\5\b\u00a1\n\b\3\b\3\b\3\b\3\t\7\t\u00a7\n\t\f\t\16\t\u00aa"+
		"\13\t\3\t\5\t\u00ad\n\t\3\t\7\t\u00b0\n\t\f\t\16\t\u00b3\13\t\3\t\3\t"+
		"\3\t\5\t\u00b8\n\t\3\t\3\t\3\t\3\n\3\n\3\n\5\n\u00c0\n\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\f\3\f\3\r\3\r\3\r\7\r\u00cc\n\r\f\r\16\r\u00cf\13\r\3\16"+
		"\3\16\3\16\3\16\5\16\u00d5\n\16\3\17\3\17\3\17\3\17\3\17\5\17\u00dc\n"+
		"\17\3\20\3\20\7\20\u00e0\n\20\f\20\16\20\u00e3\13\20\3\20\3\20\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00f2\n\21\3\22"+
		"\3\22\3\22\3\22\5\22\u00f8\n\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\5\24\u0106\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\30\3\30\5\30\u0122\n\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\5\30\u012b\n\30\3\30\3\30\5\30\u012f\n\30\3\31\3\31\3\31\3\31\7"+
		"\31\u0135\n\31\f\31\16\31\u0138\13\31\3\31\3\31\3\32\3\32\3\32\3\32\3"+
		"\32\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\5\35\u014c"+
		"\n\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3 \3 \5 \u0158\n \3!\3!"+
		"\5!\u015c\n!\3!\3!\3!\3!\3\"\3\"\3\"\7\"\u0165\n\"\f\"\16\"\u0168\13\""+
		"\3#\3#\3#\7#\u016d\n#\f#\16#\u0170\13#\3$\3$\3$\7$\u0175\n$\f$\16$\u0178"+
		"\13$\3%\3%\3%\7%\u017d\n%\f%\16%\u0180\13%\3&\3&\3&\7&\u0185\n&\f&\16"+
		"&\u0188\13&\3\'\3\'\3\'\7\'\u018d\n\'\f\'\16\'\u0190\13\'\3(\7(\u0193"+
		"\n(\f(\16(\u0196\13(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3)\3)\5)\u01a3\n)\3*\3"+
		"*\3*\3+\3+\3+\3+\3+\3+\3+\3+\5+\u01b0\n+\3+\3+\5+\u01b4\n+\3,\3,\3,\3"+
		",\3,\7,\u01bb\n,\f,\16,\u01be\13,\5,\u01c0\n,\3,\3,\3-\3-\3.\3.\3.\2\2"+
		"/\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDF"+
		"HJLNPRTVXZ\2\t\3\2!#\4\2\23\2788\5\2\n\n\17\22\66\67\3\2\61\62\4\2\13"+
		"\13\63\65\5\2--\61\6299\4\2$&/\60\2\u01d2\2_\3\2\2\2\4g\3\2\2\2\6i\3\2"+
		"\2\2\bt\3\2\2\2\n\u0081\3\2\2\2\f\u008c\3\2\2\2\16\u0091\3\2\2\2\20\u00a8"+
		"\3\2\2\2\22\u00bc\3\2\2\2\24\u00c3\3\2\2\2\26\u00c6\3\2\2\2\30\u00c8\3"+
		"\2\2\2\32\u00d0\3\2\2\2\34\u00db\3\2\2\2\36\u00dd\3\2\2\2 \u00f1\3\2\2"+
		"\2\"\u00f3\3\2\2\2$\u00fb\3\2\2\2&\u00fe\3\2\2\2(\u0107\3\2\2\2*\u010d"+
		"\3\2\2\2,\u0115\3\2\2\2.\u011f\3\2\2\2\60\u0130\3\2\2\2\62\u013b\3\2\2"+
		"\2\64\u0140\3\2\2\2\66\u0143\3\2\2\28\u0149\3\2\2\2:\u014f\3\2\2\2<\u0153"+
		"\3\2\2\2>\u0157\3\2\2\2@\u0159\3\2\2\2B\u0161\3\2\2\2D\u0169\3\2\2\2F"+
		"\u0171\3\2\2\2H\u0179\3\2\2\2J\u0181\3\2\2\2L\u0189\3\2\2\2N\u0194\3\2"+
		"\2\2P\u01a2\3\2\2\2R\u01a4\3\2\2\2T\u01a7\3\2\2\2V\u01b5\3\2\2\2X\u01c3"+
		"\3\2\2\2Z\u01c5\3\2\2\2\\^\5\4\3\2]\\\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3"+
		"\2\2\2`b\3\2\2\2a_\3\2\2\2bc\7\2\2\3c\3\3\2\2\2dh\5\b\5\2eh\5\16\b\2f"+
		"h\5\6\4\2gd\3\2\2\2ge\3\2\2\2gf\3\2\2\2h\5\3\2\2\2ij\7\'\2\2jm\7.\2\2"+
		"kl\7(\2\2ln\7.\2\2mk\3\2\2\2mn\3\2\2\2no\3\2\2\2op\7@\2\2p\7\3\2\2\2q"+
		"s\5\24\13\2rq\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2uz\3\2\2\2vt\3\2\2"+
		"\2wy\5\26\f\2xw\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2\2\2{}\3\2\2\2|z\3\2"+
		"\2\2}~\7 \2\2~\177\7.\2\2\177\u0080\5\n\6\2\u0080\t\3\2\2\2\u0081\u0085"+
		"\7>\2\2\u0082\u0084\5\f\7\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2\2\2\u0085"+
		"\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087\u0085\3\2"+
		"\2\2\u0088\u0089\7?\2\2\u0089\13\3\2\2\2\u008a\u008d\5\16\b\2\u008b\u008d"+
		"\5\b\5\2\u008c\u008a\3\2\2\2\u008c\u008b\3\2\2\2\u008d\r\3\2\2\2\u008e"+
		"\u0090\5\24\13\2\u008f\u008e\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3"+
		"\2\2\2\u0091\u0092\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0094"+
		"\u0096\5\22\n\2\u0095\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u009a\3"+
		"\2\2\2\u0097\u0099\5\26\f\2\u0098\u0097\3\2\2\2\u0099\u009c\3\2\2\2\u009a"+
		"\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009d\3\2\2\2\u009c\u009a\3\2"+
		"\2\2\u009d\u009e\7.\2\2\u009e\u00a0\7<\2\2\u009f\u00a1\5\30\r\2\u00a0"+
		"\u009f\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\7="+
		"\2\2\u00a3\u00a4\5\34\17\2\u00a4\17\3\2\2\2\u00a5\u00a7\5\24\13\2\u00a6"+
		"\u00a5\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2"+
		"\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ad\5\22\n\2\u00ac"+
		"\u00ab\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00b1\3\2\2\2\u00ae\u00b0\5\26"+
		"\f\2\u00af\u00ae\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1"+
		"\u00b2\3\2\2\2\u00b2\u00b4\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b5\7."+
		"\2\2\u00b5\u00b7\7<\2\2\u00b6\u00b8\5\30\r\2\u00b7\u00b6\3\2\2\2\u00b7"+
		"\u00b8\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\7=\2\2\u00ba\u00bb\5\34"+
		"\17\2\u00bb\21\3\2\2\2\u00bc\u00bd\7<\2\2\u00bd\u00bf\7.\2\2\u00be\u00c0"+
		"\7.\2\2\u00bf\u00be\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1"+
		"\u00c2\7=\2\2\u00c2\23\3\2\2\2\u00c3\u00c4\7\6\2\2\u00c4\u00c5\7.\2\2"+
		"\u00c5\25\3\2\2\2\u00c6\u00c7\t\2\2\2\u00c7\27\3\2\2\2\u00c8\u00cd\5\32"+
		"\16\2\u00c9\u00ca\7A\2\2\u00ca\u00cc\5\32\16\2\u00cb\u00c9\3\2\2\2\u00cc"+
		"\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\31\3\2\2"+
		"\2\u00cf\u00cd\3\2\2\2\u00d0\u00d4\5Z.\2\u00d1\u00d2\7\f\2\2\u00d2\u00d5"+
		"\7.\2\2\u00d3\u00d5\7.\2\2\u00d4\u00d1\3\2\2\2\u00d4\u00d3\3\2\2\2\u00d5"+
		"\33\3\2\2\2\u00d6\u00dc\5\36\20\2\u00d7\u00d8\7\7\2\2\u00d8\u00d9\5> "+
		"\2\u00d9\u00da\7@\2\2\u00da\u00dc\3\2\2\2\u00db\u00d6\3\2\2\2\u00db\u00d7"+
		"\3\2\2\2\u00dc\35\3\2\2\2\u00dd\u00e1\7>\2\2\u00de\u00e0\5 \21\2\u00df"+
		"\u00de\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2"+
		"\2\2\u00e2\u00e4\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e5\7?\2\2\u00e5"+
		"\37\3\2\2\2\u00e6\u00f2\5\4\3\2\u00e7\u00f2\5\"\22\2\u00e8\u00f2\5$\23"+
		"\2\u00e9\u00f2\5&\24\2\u00ea\u00f2\5(\25\2\u00eb\u00f2\5*\26\2\u00ec\u00f2"+
		"\5,\27\2\u00ed\u00f2\5.\30\2\u00ee\u00f2\5\64\33\2\u00ef\u00f2\5\66\34"+
		"\2\u00f0\u00f2\58\35\2\u00f1\u00e6\3\2\2\2\u00f1\u00e7\3\2\2\2\u00f1\u00e8"+
		"\3\2\2\2\u00f1\u00e9\3\2\2\2\u00f1\u00ea\3\2\2\2\u00f1\u00eb\3\2\2\2\u00f1"+
		"\u00ec\3\2\2\2\u00f1\u00ed\3\2\2\2\u00f1\u00ee\3\2\2\2\u00f1\u00ef\3\2"+
		"\2\2\u00f1\u00f0\3\2\2\2\u00f2!\3\2\2\2\u00f3\u00f4\5Z.\2\u00f4\u00f7"+
		"\7.\2\2\u00f5\u00f6\78\2\2\u00f6\u00f8\5> \2\u00f7\u00f5\3\2\2\2\u00f7"+
		"\u00f8\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fa\7@\2\2\u00fa#\3\2\2\2\u00fb"+
		"\u00fc\5> \2\u00fc\u00fd\7@\2\2\u00fd%\3\2\2\2\u00fe\u00ff\7\30\2\2\u00ff"+
		"\u0100\7<\2\2\u0100\u0101\5> \2\u0101\u0102\7=\2\2\u0102\u0105\5\36\20"+
		"\2\u0103\u0104\7\31\2\2\u0104\u0106\5\36\20\2\u0105\u0103\3\2\2\2\u0105"+
		"\u0106\3\2\2\2\u0106\'\3\2\2\2\u0107\u0108\7\32\2\2\u0108\u0109\7<\2\2"+
		"\u0109\u010a\5> \2\u010a\u010b\7=\2\2\u010b\u010c\5\36\20\2\u010c)\3\2"+
		"\2\2\u010d\u010e\7\33\2\2\u010e\u010f\5\36\20\2\u010f\u0110\7\32\2\2\u0110"+
		"\u0111\7<\2\2\u0111\u0112\5> \2\u0112\u0113\7=\2\2\u0113\u0114\7@\2\2"+
		"\u0114+\3\2\2\2\u0115\u0116\7\34\2\2\u0116\u0117\7<\2\2\u0117\u0118\5"+
		":\36\2\u0118\u0119\7@\2\2\u0119\u011a\5> \2\u011a\u011b\7@\2\2\u011b\u011c"+
		"\5:\36\2\u011c\u011d\7=\2\2\u011d\u011e\5\36\20\2\u011e-\3\2\2\2\u011f"+
		"\u0121\7)\2\2\u0120\u0122\5\60\31\2\u0121\u0120\3\2\2\2\u0121\u0122\3"+
		"\2\2\2\u0122\u0123\3\2\2\2\u0123\u012a\5\36\20\2\u0124\u0125\7*\2\2\u0125"+
		"\u0126\7<\2\2\u0126\u0127\7.\2\2\u0127\u0128\7.\2\2\u0128\u0129\7=\2\2"+
		"\u0129\u012b\5\36\20\2\u012a\u0124\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012e"+
		"\3\2\2\2\u012c\u012d\7+\2\2\u012d\u012f\5\36\20\2\u012e\u012c\3\2\2\2"+
		"\u012e\u012f\3\2\2\2\u012f/\3\2\2\2\u0130\u0131\7<\2\2\u0131\u0136\5\62"+
		"\32\2\u0132\u0133\7@\2\2\u0133\u0135\5\62\32\2\u0134\u0132\3\2\2\2\u0135"+
		"\u0138\3\2\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0139\3\2"+
		"\2\2\u0138\u0136\3\2\2\2\u0139\u013a\7=\2\2\u013a\61\3\2\2\2\u013b\u013c"+
		"\5Z.\2\u013c\u013d\7.\2\2\u013d\u013e\78\2\2\u013e\u013f\5> \2\u013f\63"+
		"\3\2\2\2\u0140\u0141\7\37\2\2\u0141\u0142\7@\2\2\u0142\65\3\2\2\2\u0143"+
		"\u0144\7\36\2\2\u0144\u0145\7<\2\2\u0145\u0146\5> \2\u0146\u0147\7=\2"+
		"\2\u0147\u0148\7@\2\2\u0148\67\3\2\2\2\u0149\u014b\7\35\2\2\u014a\u014c"+
		"\5> \2\u014b\u014a\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014d\3\2\2\2\u014d"+
		"\u014e\7@\2\2\u014e9\3\2\2\2\u014f\u0150\7.\2\2\u0150\u0151\5<\37\2\u0151"+
		"\u0152\5> \2\u0152;\3\2\2\2\u0153\u0154\t\3\2\2\u0154=\3\2\2\2\u0155\u0158"+
		"\5@!\2\u0156\u0158\5B\"\2\u0157\u0155\3\2\2\2\u0157\u0156\3\2\2\2\u0158"+
		"?\3\2\2\2\u0159\u015b\7<\2\2\u015a\u015c\5\30\r\2\u015b\u015a\3\2\2\2"+
		"\u015b\u015c\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015e\7=\2\2\u015e\u015f"+
		"\7\b\2\2\u015f\u0160\5> \2\u0160A\3\2\2\2\u0161\u0166\5D#\2\u0162\u0163"+
		"\7\16\2\2\u0163\u0165\5D#\2\u0164\u0162\3\2\2\2\u0165\u0168\3\2\2\2\u0166"+
		"\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167C\3\2\2\2\u0168\u0166\3\2\2\2"+
		"\u0169\u016e\5F$\2\u016a\u016b\7\r\2\2\u016b\u016d\5F$\2\u016c\u016a\3"+
		"\2\2\2\u016d\u0170\3\2\2\2\u016e\u016c\3\2\2\2\u016e\u016f\3\2\2\2\u016f"+
		"E\3\2\2\2\u0170\u016e\3\2\2\2\u0171\u0176\5H%\2\u0172\u0173\7\t\2\2\u0173"+
		"\u0175\5H%\2\u0174\u0172\3\2\2\2\u0175\u0178\3\2\2\2\u0176\u0174\3\2\2"+
		"\2\u0176\u0177\3\2\2\2\u0177G\3\2\2\2\u0178\u0176\3\2\2\2\u0179\u017e"+
		"\5J&\2\u017a\u017b\t\4\2\2\u017b\u017d\5J&\2\u017c\u017a\3\2\2\2\u017d"+
		"\u0180\3\2\2\2\u017e\u017c\3\2\2\2\u017e\u017f\3\2\2\2\u017fI\3\2\2\2"+
		"\u0180\u017e\3\2\2\2\u0181\u0186\5L\'\2\u0182\u0183\t\5\2\2\u0183\u0185"+
		"\5L\'\2\u0184\u0182\3\2\2\2\u0185\u0188\3\2\2\2\u0186\u0184\3\2\2\2\u0186"+
		"\u0187\3\2\2\2\u0187K\3\2\2\2\u0188\u0186\3\2\2\2\u0189\u018e\5N(\2\u018a"+
		"\u018b\t\6\2\2\u018b\u018d\5N(\2\u018c\u018a\3\2\2\2\u018d\u0190\3\2\2"+
		"\2\u018e\u018c\3\2\2\2\u018e\u018f\3\2\2\2\u018fM\3\2\2\2\u0190\u018e"+
		"\3\2\2\2\u0191\u0193\t\7\2\2\u0192\u0191\3\2\2\2\u0193\u0196\3\2\2\2\u0194"+
		"\u0192\3\2\2\2\u0194\u0195\3\2\2\2\u0195\u0197\3\2\2\2\u0196\u0194\3\2"+
		"\2\2\u0197\u0198\5P)\2\u0198O\3\2\2\2\u0199\u019a\7<\2\2\u019a\u019b\5"+
		"> \2\u019b\u019c\7=\2\2\u019c\u01a3\3\2\2\2\u019d\u01a3\5R*\2\u019e\u01a3"+
		"\5T+\2\u019f\u01a3\5V,\2\u01a0\u01a3\5X-\2\u01a1\u01a3\7.\2\2\u01a2\u0199"+
		"\3\2\2\2\u01a2\u019d\3\2\2\2\u01a2\u019e\3\2\2\2\u01a2\u019f\3\2\2\2\u01a2"+
		"\u01a0\3\2\2\2\u01a2\u01a1\3\2\2\2\u01a3Q\3\2\2\2\u01a4\u01a5\7\33\2\2"+
		"\u01a5\u01a6\5\36\20\2\u01a6S\3\2\2\2\u01a7\u01a8\7)\2\2\u01a8\u01af\5"+
		"\36\20\2\u01a9\u01aa\7*\2\2\u01aa\u01ab\7<\2\2\u01ab\u01ac\7.\2\2\u01ac"+
		"\u01ad\7.\2\2\u01ad\u01ae\7=\2\2\u01ae\u01b0\5\36\20\2\u01af\u01a9\3\2"+
		"\2\2\u01af\u01b0\3\2\2\2\u01b0\u01b3\3\2\2\2\u01b1\u01b2\7+\2\2\u01b2"+
		"\u01b4\5\36\20\2\u01b3\u01b1\3\2\2\2\u01b3\u01b4\3\2\2\2\u01b4U\3\2\2"+
		"\2\u01b5\u01b6\7.\2\2\u01b6\u01bf\7<\2\2\u01b7\u01bc\5> \2\u01b8\u01b9"+
		"\7A\2\2\u01b9\u01bb\5> \2\u01ba\u01b8\3\2\2\2\u01bb\u01be\3\2\2\2\u01bc"+
		"\u01ba\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd\u01c0\3\2\2\2\u01be\u01bc\3\2"+
		"\2\2\u01bf\u01b7\3\2\2\2\u01bf\u01c0\3\2\2\2\u01c0\u01c1\3\2\2\2\u01c1"+
		"\u01c2\7=\2\2\u01c2W\3\2\2\2\u01c3\u01c4\t\b\2\2\u01c4Y\3\2\2\2\u01c5"+
		"\u01c6\7.\2\2\u01c6[\3\2\2\2,_gmtz\u0085\u008c\u0091\u0095\u009a\u00a0"+
		"\u00a8\u00ac\u00b1\u00b7\u00bf\u00cd\u00d4\u00db\u00e1\u00f1\u00f7\u0105"+
		"\u0121\u012a\u012e\u0136\u014b\u0157\u015b\u0166\u016e\u0176\u017e\u0186"+
		"\u018e\u0194\u01a2\u01af\u01b3\u01bc\u01bf";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}