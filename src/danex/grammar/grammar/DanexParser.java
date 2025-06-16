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
		RULE_expressionStatement = 16, RULE_ifStatement = 17, RULE_whileStatement = 18, 
		RULE_doWhileStatement = 19, RULE_forStatement = 20, RULE_tryStatement = 21, 
		RULE_resourceSpec = 22, RULE_resourceDecl = 23, RULE_exitStatement = 24, 
		RULE_throwStatement = 25, RULE_returnStatement = 26, RULE_assignment = 27, 
		RULE_assignOp = 28, RULE_expression = 29, RULE_lambdaExpr = 30, RULE_logicalOrExpr = 31, 
		RULE_logicalAndExpr = 32, RULE_nullCoalesceExpr = 33, RULE_comparisonExpr = 34, 
		RULE_additiveExpr = 35, RULE_multiplicativeExpr = 36, RULE_unaryExpr = 37, 
		RULE_primaryExpr = 38, RULE_doExpression = 39, RULE_tryExpression = 40, 
		RULE_functionCall = 41, RULE_literal = 42, RULE_type = 43;
	private static String[] makeRuleNames() {
		return new String[] {
			"compilationUnit", "statement", "importStmt", "classDecl", "classBody", 
			"classBodyMember", "methodDecl", "topLevelMethodDecl", "resultDecl", 
			"annotation", "modifier", "paramList", "param", "methodBody", "block", 
			"blockContent", "expressionStatement", "ifStatement", "whileStatement", 
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
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HASH) | (1L << CLASS) | (1L << PUBLIC) | (1L << PRIVATE) | (1L << STATIC) | (1L << USE) | (1L << IDENTIFIER) | (1L << LPAREN))) != 0)) {
				{
				{
				setState(88);
				statement();
				}
				}
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(94);
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
		public TopLevelMethodDeclContext topLevelMethodDecl() {
			return getRuleContext(TopLevelMethodDeclContext.class,0);
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
			setState(100);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(96);
				classDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(97);
				methodDecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(98);
				topLevelMethodDecl();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(99);
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
			setState(102);
			match(USE);
			setState(103);
			match(IDENTIFIER);
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(104);
				match(AS);
				setState(105);
				match(IDENTIFIER);
				}
			}

			setState(108);
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
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==HASH) {
				{
				{
				setState(110);
				annotation();
				}
				}
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PUBLIC) | (1L << PRIVATE) | (1L << STATIC))) != 0)) {
				{
				{
				setState(116);
				modifier();
				}
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(122);
			match(CLASS);
			setState(123);
			match(IDENTIFIER);
			setState(124);
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
			setState(126);
			match(LBRACE);
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HASH) | (1L << CLASS) | (1L << PUBLIC) | (1L << PRIVATE) | (1L << STATIC) | (1L << IDENTIFIER) | (1L << LPAREN))) != 0)) {
				{
				{
				setState(127);
				classBodyMember();
				}
				}
				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(133);
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
			setState(137);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(135);
				methodDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(136);
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
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==HASH) {
				{
				{
				setState(139);
				annotation();
				}
				}
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(145);
				resultDecl();
				}
			}

			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PUBLIC) | (1L << PRIVATE) | (1L << STATIC))) != 0)) {
				{
				{
				setState(148);
				modifier();
				}
				}
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(154);
			match(IDENTIFIER);
			setState(155);
			match(LPAREN);
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(156);
				paramList();
				}
			}

			setState(159);
			match(RPAREN);
			setState(160);
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
			setState(165);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==HASH) {
				{
				{
				setState(162);
				annotation();
				}
				}
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(168);
				resultDecl();
				}
			}

			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PUBLIC) | (1L << PRIVATE) | (1L << STATIC))) != 0)) {
				{
				{
				setState(171);
				modifier();
				}
				}
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(177);
			match(IDENTIFIER);
			setState(178);
			match(LPAREN);
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(179);
				paramList();
				}
			}

			setState(182);
			match(RPAREN);
			setState(183);
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
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DanexParser.RPAREN, 0); }
		public TerminalNode IDENTIFIER() { return getToken(DanexParser.IDENTIFIER, 0); }
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
			setState(185);
			match(LPAREN);
			setState(186);
			type();
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(187);
				match(IDENTIFIER);
				}
			}

			setState(190);
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
			setState(192);
			match(HASH);
			setState(193);
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
			setState(195);
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
			setState(197);
			param();
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(198);
				match(COMMA);
				setState(199);
				param();
				}
				}
				setState(204);
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
			setState(205);
			type();
			setState(209);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARARGS:
				{
				setState(206);
				match(VARARGS);
				setState(207);
				match(IDENTIFIER);
				}
				break;
			case IDENTIFIER:
				{
				setState(208);
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
			setState(216);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(211);
				block();
				}
				break;
			case ARROW:
				enterOuterAlt(_localctx, 2);
				{
				setState(212);
				match(ARROW);
				setState(213);
				expression();
				setState(214);
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
			setState(218);
			match(LBRACE);
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HASH) | (1L << IF) | (1L << WHILE) | (1L << DO) | (1L << FOR) | (1L << RETURN) | (1L << THROW) | (1L << EXIT) | (1L << CLASS) | (1L << PUBLIC) | (1L << PRIVATE) | (1L << STATIC) | (1L << TRUE) | (1L << FALSE) | (1L << NULL) | (1L << USE) | (1L << TRY) | (1L << AWAIT) | (1L << IDENTIFIER) | (1L << NUMBER) | (1L << STRING) | (1L << PLUS) | (1L << MINUS) | (1L << BANG) | (1L << LPAREN))) != 0)) {
				{
				{
				setState(219);
				blockContent();
				}
				}
				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(225);
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
			setState(237);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(227);
				statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(228);
				expressionStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(229);
				ifStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(230);
				whileStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(231);
				doWhileStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(232);
				forStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(233);
				tryStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(234);
				exitStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(235);
				throwStatement();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(236);
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
		enterRule(_localctx, 32, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			expression();
			setState(240);
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
		enterRule(_localctx, 34, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			match(IF);
			setState(243);
			match(LPAREN);
			setState(244);
			expression();
			setState(245);
			match(RPAREN);
			setState(246);
			block();
			setState(249);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(247);
				match(ELSE);
				setState(248);
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
		enterRule(_localctx, 36, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(WHILE);
			setState(252);
			match(LPAREN);
			setState(253);
			expression();
			setState(254);
			match(RPAREN);
			setState(255);
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
		enterRule(_localctx, 38, RULE_doWhileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(DO);
			setState(258);
			block();
			setState(259);
			match(WHILE);
			setState(260);
			match(LPAREN);
			setState(261);
			expression();
			setState(262);
			match(RPAREN);
			setState(263);
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
		enterRule(_localctx, 40, RULE_forStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			match(FOR);
			setState(266);
			match(LPAREN);
			setState(267);
			assignment();
			setState(268);
			match(SEMI);
			setState(269);
			expression();
			setState(270);
			match(SEMI);
			setState(271);
			assignment();
			setState(272);
			match(RPAREN);
			setState(273);
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
		enterRule(_localctx, 42, RULE_tryStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			match(TRY);
			setState(277);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(276);
				resourceSpec();
				}
			}

			setState(279);
			block();
			setState(286);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CATCH) {
				{
				setState(280);
				match(CATCH);
				setState(281);
				match(LPAREN);
				setState(282);
				match(IDENTIFIER);
				setState(283);
				match(IDENTIFIER);
				setState(284);
				match(RPAREN);
				setState(285);
				block();
				}
			}

			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FINALLY) {
				{
				setState(288);
				match(FINALLY);
				setState(289);
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
		enterRule(_localctx, 44, RULE_resourceSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			match(LPAREN);
			setState(293);
			resourceDecl();
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMI) {
				{
				{
				setState(294);
				match(SEMI);
				setState(295);
				resourceDecl();
				}
				}
				setState(300);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(301);
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
		enterRule(_localctx, 46, RULE_resourceDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			type();
			setState(304);
			match(IDENTIFIER);
			setState(305);
			match(EQ);
			setState(306);
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
		enterRule(_localctx, 48, RULE_exitStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			match(EXIT);
			setState(309);
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
		enterRule(_localctx, 50, RULE_throwStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			match(THROW);
			setState(312);
			match(LPAREN);
			setState(313);
			expression();
			setState(314);
			match(RPAREN);
			setState(315);
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
		enterRule(_localctx, 52, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			match(RETURN);
			setState(319);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DO) | (1L << TRUE) | (1L << FALSE) | (1L << NULL) | (1L << TRY) | (1L << AWAIT) | (1L << IDENTIFIER) | (1L << NUMBER) | (1L << STRING) | (1L << PLUS) | (1L << MINUS) | (1L << BANG) | (1L << LPAREN))) != 0)) {
				{
				setState(318);
				expression();
				}
			}

			setState(321);
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
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
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
		enterRule(_localctx, 54, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			match(IDENTIFIER);
			setState(324);
			assignOp();
			setState(325);
			assignment();
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
		enterRule(_localctx, 56, RULE_assignOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
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
		enterRule(_localctx, 58, RULE_expression);
		try {
			setState(331);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(329);
				lambdaExpr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(330);
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
		enterRule(_localctx, 60, RULE_lambdaExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			match(LPAREN);
			setState(335);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(334);
				paramList();
				}
			}

			setState(337);
			match(RPAREN);
			setState(338);
			match(LAMBDA);
			setState(339);
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
		enterRule(_localctx, 62, RULE_logicalOrExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(341);
			logicalAndExpr();
			setState(346);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR_OR) {
				{
				{
				setState(342);
				match(OR_OR);
				setState(343);
				logicalAndExpr();
				}
				}
				setState(348);
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
		enterRule(_localctx, 64, RULE_logicalAndExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			nullCoalesceExpr();
			setState(354);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND_AND) {
				{
				{
				setState(350);
				match(AND_AND);
				setState(351);
				nullCoalesceExpr();
				}
				}
				setState(356);
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
		enterRule(_localctx, 66, RULE_nullCoalesceExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			comparisonExpr();
			setState(362);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NULLCOAL) {
				{
				{
				setState(358);
				match(NULLCOAL);
				setState(359);
				comparisonExpr();
				}
				}
				setState(364);
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
		enterRule(_localctx, 68, RULE_comparisonExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			additiveExpr();
			setState(370);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMPARATOR) | (1L << EQEQ) | (1L << NEQ) | (1L << LE) | (1L << GE) | (1L << LT) | (1L << GT))) != 0)) {
				{
				{
				setState(366);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMPARATOR) | (1L << EQEQ) | (1L << NEQ) | (1L << LE) | (1L << GE) | (1L << LT) | (1L << GT))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(367);
				additiveExpr();
				}
				}
				setState(372);
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
		enterRule(_localctx, 70, RULE_additiveExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			multiplicativeExpr();
			setState(378);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(374);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(375);
				multiplicativeExpr();
				}
				}
				setState(380);
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
		enterRule(_localctx, 72, RULE_multiplicativeExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			unaryExpr();
			setState(386);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MOD2) | (1L << STAR) | (1L << DIV) | (1L << MOD))) != 0)) {
				{
				{
				setState(382);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MOD2) | (1L << STAR) | (1L << DIV) | (1L << MOD))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(383);
				unaryExpr();
				}
				}
				setState(388);
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
		enterRule(_localctx, 74, RULE_unaryExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AWAIT) | (1L << PLUS) | (1L << MINUS) | (1L << BANG))) != 0)) {
				{
				{
				setState(389);
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
				setState(394);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(395);
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
		enterRule(_localctx, 76, RULE_primaryExpr);
		try {
			setState(406);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(397);
				match(LPAREN);
				setState(398);
				expression();
				setState(399);
				match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(401);
				doExpression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(402);
				tryExpression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(403);
				functionCall();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(404);
				literal();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(405);
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
		enterRule(_localctx, 78, RULE_doExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(408);
			match(DO);
			setState(409);
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
		enterRule(_localctx, 80, RULE_tryExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(411);
			match(TRY);
			setState(412);
			block();
			setState(419);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CATCH) {
				{
				setState(413);
				match(CATCH);
				setState(414);
				match(LPAREN);
				setState(415);
				match(IDENTIFIER);
				setState(416);
				match(IDENTIFIER);
				setState(417);
				match(RPAREN);
				setState(418);
				block();
				}
			}

			setState(423);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FINALLY) {
				{
				setState(421);
				match(FINALLY);
				setState(422);
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
		enterRule(_localctx, 82, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(425);
			match(IDENTIFIER);
			setState(426);
			match(LPAREN);
			setState(435);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DO) | (1L << TRUE) | (1L << FALSE) | (1L << NULL) | (1L << TRY) | (1L << AWAIT) | (1L << IDENTIFIER) | (1L << NUMBER) | (1L << STRING) | (1L << PLUS) | (1L << MINUS) | (1L << BANG) | (1L << LPAREN))) != 0)) {
				{
				setState(427);
				expression();
				setState(432);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(428);
					match(COMMA);
					setState(429);
					expression();
					}
					}
					setState(434);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(437);
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
		enterRule(_localctx, 84, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(439);
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
		enterRule(_localctx, 86, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3A\u01be\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\3\2\7\2\\\n\2\f\2\16\2_\13\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3g\n"+
		"\3\3\4\3\4\3\4\3\4\5\4m\n\4\3\4\3\4\3\5\7\5r\n\5\f\5\16\5u\13\5\3\5\7"+
		"\5x\n\5\f\5\16\5{\13\5\3\5\3\5\3\5\3\5\3\6\3\6\7\6\u0083\n\6\f\6\16\6"+
		"\u0086\13\6\3\6\3\6\3\7\3\7\5\7\u008c\n\7\3\b\7\b\u008f\n\b\f\b\16\b\u0092"+
		"\13\b\3\b\5\b\u0095\n\b\3\b\7\b\u0098\n\b\f\b\16\b\u009b\13\b\3\b\3\b"+
		"\3\b\5\b\u00a0\n\b\3\b\3\b\3\b\3\t\7\t\u00a6\n\t\f\t\16\t\u00a9\13\t\3"+
		"\t\5\t\u00ac\n\t\3\t\7\t\u00af\n\t\f\t\16\t\u00b2\13\t\3\t\3\t\3\t\5\t"+
		"\u00b7\n\t\3\t\3\t\3\t\3\n\3\n\3\n\5\n\u00bf\n\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\f\3\f\3\r\3\r\3\r\7\r\u00cb\n\r\f\r\16\r\u00ce\13\r\3\16\3\16\3\16"+
		"\3\16\5\16\u00d4\n\16\3\17\3\17\3\17\3\17\3\17\5\17\u00db\n\17\3\20\3"+
		"\20\7\20\u00df\n\20\f\20\16\20\u00e2\13\20\3\20\3\20\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00f0\n\21\3\22\3\22\3\22\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00fc\n\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\27\3\27\5\27\u0118\n\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\5\27\u0121\n\27\3\27\3\27\5\27\u0125\n\27\3\30\3\30\3"+
		"\30\3\30\7\30\u012b\n\30\f\30\16\30\u012e\13\30\3\30\3\30\3\31\3\31\3"+
		"\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\5"+
		"\34\u0142\n\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\37\3\37\5\37"+
		"\u014e\n\37\3 \3 \5 \u0152\n \3 \3 \3 \3 \3!\3!\3!\7!\u015b\n!\f!\16!"+
		"\u015e\13!\3\"\3\"\3\"\7\"\u0163\n\"\f\"\16\"\u0166\13\"\3#\3#\3#\7#\u016b"+
		"\n#\f#\16#\u016e\13#\3$\3$\3$\7$\u0173\n$\f$\16$\u0176\13$\3%\3%\3%\7"+
		"%\u017b\n%\f%\16%\u017e\13%\3&\3&\3&\7&\u0183\n&\f&\16&\u0186\13&\3\'"+
		"\7\'\u0189\n\'\f\'\16\'\u018c\13\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3("+
		"\5(\u0199\n(\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3*\5*\u01a6\n*\3*\3*\5*\u01aa"+
		"\n*\3+\3+\3+\3+\3+\7+\u01b1\n+\f+\16+\u01b4\13+\5+\u01b6\n+\3+\3+\3,\3"+
		",\3-\3-\3-\2\2.\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\64\668:<>@BDFHJLNPRTVX\2\t\3\2!#\4\2\23\2788\5\2\n\n\17\22\66\67\3\2"+
		"\61\62\4\2\13\13\63\65\5\2--\61\6299\4\2$&/\60\2\u01c8\2]\3\2\2\2\4f\3"+
		"\2\2\2\6h\3\2\2\2\bs\3\2\2\2\n\u0080\3\2\2\2\f\u008b\3\2\2\2\16\u0090"+
		"\3\2\2\2\20\u00a7\3\2\2\2\22\u00bb\3\2\2\2\24\u00c2\3\2\2\2\26\u00c5\3"+
		"\2\2\2\30\u00c7\3\2\2\2\32\u00cf\3\2\2\2\34\u00da\3\2\2\2\36\u00dc\3\2"+
		"\2\2 \u00ef\3\2\2\2\"\u00f1\3\2\2\2$\u00f4\3\2\2\2&\u00fd\3\2\2\2(\u0103"+
		"\3\2\2\2*\u010b\3\2\2\2,\u0115\3\2\2\2.\u0126\3\2\2\2\60\u0131\3\2\2\2"+
		"\62\u0136\3\2\2\2\64\u0139\3\2\2\2\66\u013f\3\2\2\28\u0145\3\2\2\2:\u0149"+
		"\3\2\2\2<\u014d\3\2\2\2>\u014f\3\2\2\2@\u0157\3\2\2\2B\u015f\3\2\2\2D"+
		"\u0167\3\2\2\2F\u016f\3\2\2\2H\u0177\3\2\2\2J\u017f\3\2\2\2L\u018a\3\2"+
		"\2\2N\u0198\3\2\2\2P\u019a\3\2\2\2R\u019d\3\2\2\2T\u01ab\3\2\2\2V\u01b9"+
		"\3\2\2\2X\u01bb\3\2\2\2Z\\\5\4\3\2[Z\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3"+
		"\2\2\2^`\3\2\2\2_]\3\2\2\2`a\7\2\2\3a\3\3\2\2\2bg\5\b\5\2cg\5\16\b\2d"+
		"g\5\20\t\2eg\5\6\4\2fb\3\2\2\2fc\3\2\2\2fd\3\2\2\2fe\3\2\2\2g\5\3\2\2"+
		"\2hi\7\'\2\2il\7.\2\2jk\7(\2\2km\7.\2\2lj\3\2\2\2lm\3\2\2\2mn\3\2\2\2"+
		"no\7@\2\2o\7\3\2\2\2pr\5\24\13\2qp\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2"+
		"\2ty\3\2\2\2us\3\2\2\2vx\5\26\f\2wv\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2"+
		"\2\2z|\3\2\2\2{y\3\2\2\2|}\7 \2\2}~\7.\2\2~\177\5\n\6\2\177\t\3\2\2\2"+
		"\u0080\u0084\7>\2\2\u0081\u0083\5\f\7\2\u0082\u0081\3\2\2\2\u0083\u0086"+
		"\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0087\3\2\2\2\u0086"+
		"\u0084\3\2\2\2\u0087\u0088\7?\2\2\u0088\13\3\2\2\2\u0089\u008c\5\16\b"+
		"\2\u008a\u008c\5\b\5\2\u008b\u0089\3\2\2\2\u008b\u008a\3\2\2\2\u008c\r"+
		"\3\2\2\2\u008d\u008f\5\24\13\2\u008e\u008d\3\2\2\2\u008f\u0092\3\2\2\2"+
		"\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0094\3\2\2\2\u0092\u0090"+
		"\3\2\2\2\u0093\u0095\5\22\n\2\u0094\u0093\3\2\2\2\u0094\u0095\3\2\2\2"+
		"\u0095\u0099\3\2\2\2\u0096\u0098\5\26\f\2\u0097\u0096\3\2\2\2\u0098\u009b"+
		"\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009c\3\2\2\2\u009b"+
		"\u0099\3\2\2\2\u009c\u009d\7.\2\2\u009d\u009f\7<\2\2\u009e\u00a0\5\30"+
		"\r\2\u009f\u009e\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1"+
		"\u00a2\7=\2\2\u00a2\u00a3\5\34\17\2\u00a3\17\3\2\2\2\u00a4\u00a6\5\24"+
		"\13\2\u00a5\u00a4\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7"+
		"\u00a8\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa\u00ac\5\22"+
		"\n\2\u00ab\u00aa\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00b0\3\2\2\2\u00ad"+
		"\u00af\5\26\f\2\u00ae\u00ad\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3"+
		"\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3"+
		"\u00b4\7.\2\2\u00b4\u00b6\7<\2\2\u00b5\u00b7\5\30\r\2\u00b6\u00b5\3\2"+
		"\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\7=\2\2\u00b9"+
		"\u00ba\5\34\17\2\u00ba\21\3\2\2\2\u00bb\u00bc\7<\2\2\u00bc\u00be\5X-\2"+
		"\u00bd\u00bf\7.\2\2\u00be\u00bd\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0"+
		"\3\2\2\2\u00c0\u00c1\7=\2\2\u00c1\23\3\2\2\2\u00c2\u00c3\7\6\2\2\u00c3"+
		"\u00c4\7.\2\2\u00c4\25\3\2\2\2\u00c5\u00c6\t\2\2\2\u00c6\27\3\2\2\2\u00c7"+
		"\u00cc\5\32\16\2\u00c8\u00c9\7A\2\2\u00c9\u00cb\5\32\16\2\u00ca\u00c8"+
		"\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd"+
		"\31\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d3\5X-\2\u00d0\u00d1\7\f\2\2"+
		"\u00d1\u00d4\7.\2\2\u00d2\u00d4\7.\2\2\u00d3\u00d0\3\2\2\2\u00d3\u00d2"+
		"\3\2\2\2\u00d4\33\3\2\2\2\u00d5\u00db\5\36\20\2\u00d6\u00d7\7\7\2\2\u00d7"+
		"\u00d8\5<\37\2\u00d8\u00d9\7@\2\2\u00d9\u00db\3\2\2\2\u00da\u00d5\3\2"+
		"\2\2\u00da\u00d6\3\2\2\2\u00db\35\3\2\2\2\u00dc\u00e0\7>\2\2\u00dd\u00df"+
		"\5 \21\2\u00de\u00dd\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0"+
		"\u00e1\3\2\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e4\7?"+
		"\2\2\u00e4\37\3\2\2\2\u00e5\u00f0\5\4\3\2\u00e6\u00f0\5\"\22\2\u00e7\u00f0"+
		"\5$\23\2\u00e8\u00f0\5&\24\2\u00e9\u00f0\5(\25\2\u00ea\u00f0\5*\26\2\u00eb"+
		"\u00f0\5,\27\2\u00ec\u00f0\5\62\32\2\u00ed\u00f0\5\64\33\2\u00ee\u00f0"+
		"\5\66\34\2\u00ef\u00e5\3\2\2\2\u00ef\u00e6\3\2\2\2\u00ef\u00e7\3\2\2\2"+
		"\u00ef\u00e8\3\2\2\2\u00ef\u00e9\3\2\2\2\u00ef\u00ea\3\2\2\2\u00ef\u00eb"+
		"\3\2\2\2\u00ef\u00ec\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00ee\3\2\2\2\u00f0"+
		"!\3\2\2\2\u00f1\u00f2\5<\37\2\u00f2\u00f3\7@\2\2\u00f3#\3\2\2\2\u00f4"+
		"\u00f5\7\30\2\2\u00f5\u00f6\7<\2\2\u00f6\u00f7\5<\37\2\u00f7\u00f8\7="+
		"\2\2\u00f8\u00fb\5\36\20\2\u00f9\u00fa\7\31\2\2\u00fa\u00fc\5\36\20\2"+
		"\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc%\3\2\2\2\u00fd\u00fe\7"+
		"\32\2\2\u00fe\u00ff\7<\2\2\u00ff\u0100\5<\37\2\u0100\u0101\7=\2\2\u0101"+
		"\u0102\5\36\20\2\u0102\'\3\2\2\2\u0103\u0104\7\33\2\2\u0104\u0105\5\36"+
		"\20\2\u0105\u0106\7\32\2\2\u0106\u0107\7<\2\2\u0107\u0108\5<\37\2\u0108"+
		"\u0109\7=\2\2\u0109\u010a\7@\2\2\u010a)\3\2\2\2\u010b\u010c\7\34\2\2\u010c"+
		"\u010d\7<\2\2\u010d\u010e\58\35\2\u010e\u010f\7@\2\2\u010f\u0110\5<\37"+
		"\2\u0110\u0111\7@\2\2\u0111\u0112\58\35\2\u0112\u0113\7=\2\2\u0113\u0114"+
		"\5\36\20\2\u0114+\3\2\2\2\u0115\u0117\7)\2\2\u0116\u0118\5.\30\2\u0117"+
		"\u0116\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u0120\5\36"+
		"\20\2\u011a\u011b\7*\2\2\u011b\u011c\7<\2\2\u011c\u011d\7.\2\2\u011d\u011e"+
		"\7.\2\2\u011e\u011f\7=\2\2\u011f\u0121\5\36\20\2\u0120\u011a\3\2\2\2\u0120"+
		"\u0121\3\2\2\2\u0121\u0124\3\2\2\2\u0122\u0123\7+\2\2\u0123\u0125\5\36"+
		"\20\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125-\3\2\2\2\u0126\u0127"+
		"\7<\2\2\u0127\u012c\5\60\31\2\u0128\u0129\7@\2\2\u0129\u012b\5\60\31\2"+
		"\u012a\u0128\3\2\2\2\u012b\u012e\3\2\2\2\u012c\u012a\3\2\2\2\u012c\u012d"+
		"\3\2\2\2\u012d\u012f\3\2\2\2\u012e\u012c\3\2\2\2\u012f\u0130\7=\2\2\u0130"+
		"/\3\2\2\2\u0131\u0132\5X-\2\u0132\u0133\7.\2\2\u0133\u0134\78\2\2\u0134"+
		"\u0135\5<\37\2\u0135\61\3\2\2\2\u0136\u0137\7\37\2\2\u0137\u0138\7@\2"+
		"\2\u0138\63\3\2\2\2\u0139\u013a\7\36\2\2\u013a\u013b\7<\2\2\u013b\u013c"+
		"\5<\37\2\u013c\u013d\7=\2\2\u013d\u013e\7@\2\2\u013e\65\3\2\2\2\u013f"+
		"\u0141\7\35\2\2\u0140\u0142\5<\37\2\u0141\u0140\3\2\2\2\u0141\u0142\3"+
		"\2\2\2\u0142\u0143\3\2\2\2\u0143\u0144\7@\2\2\u0144\67\3\2\2\2\u0145\u0146"+
		"\7.\2\2\u0146\u0147\5:\36\2\u0147\u0148\58\35\2\u01489\3\2\2\2\u0149\u014a"+
		"\t\3\2\2\u014a;\3\2\2\2\u014b\u014e\5> \2\u014c\u014e\5@!\2\u014d\u014b"+
		"\3\2\2\2\u014d\u014c\3\2\2\2\u014e=\3\2\2\2\u014f\u0151\7<\2\2\u0150\u0152"+
		"\5\30\r\2\u0151\u0150\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0153\3\2\2\2"+
		"\u0153\u0154\7=\2\2\u0154\u0155\7\b\2\2\u0155\u0156\5<\37\2\u0156?\3\2"+
		"\2\2\u0157\u015c\5B\"\2\u0158\u0159\7\16\2\2\u0159\u015b\5B\"\2\u015a"+
		"\u0158\3\2\2\2\u015b\u015e\3\2\2\2\u015c\u015a\3\2\2\2\u015c\u015d\3\2"+
		"\2\2\u015dA\3\2\2\2\u015e\u015c\3\2\2\2\u015f\u0164\5D#\2\u0160\u0161"+
		"\7\r\2\2\u0161\u0163\5D#\2\u0162\u0160\3\2\2\2\u0163\u0166\3\2\2\2\u0164"+
		"\u0162\3\2\2\2\u0164\u0165\3\2\2\2\u0165C\3\2\2\2\u0166\u0164\3\2\2\2"+
		"\u0167\u016c\5F$\2\u0168\u0169\7\t\2\2\u0169\u016b\5F$\2\u016a\u0168\3"+
		"\2\2\2\u016b\u016e\3\2\2\2\u016c\u016a\3\2\2\2\u016c\u016d\3\2\2\2\u016d"+
		"E\3\2\2\2\u016e\u016c\3\2\2\2\u016f\u0174\5H%\2\u0170\u0171\t\4\2\2\u0171"+
		"\u0173\5H%\2\u0172\u0170\3\2\2\2\u0173\u0176\3\2\2\2\u0174\u0172\3\2\2"+
		"\2\u0174\u0175\3\2\2\2\u0175G\3\2\2\2\u0176\u0174\3\2\2\2\u0177\u017c"+
		"\5J&\2\u0178\u0179\t\5\2\2\u0179\u017b\5J&\2\u017a\u0178\3\2\2\2\u017b"+
		"\u017e\3\2\2\2\u017c\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017dI\3\2\2\2"+
		"\u017e\u017c\3\2\2\2\u017f\u0184\5L\'\2\u0180\u0181\t\6\2\2\u0181\u0183"+
		"\5L\'\2\u0182\u0180\3\2\2\2\u0183\u0186\3\2\2\2\u0184\u0182\3\2\2\2\u0184"+
		"\u0185\3\2\2\2\u0185K\3\2\2\2\u0186\u0184\3\2\2\2\u0187\u0189\t\7\2\2"+
		"\u0188\u0187\3\2\2\2\u0189\u018c\3\2\2\2\u018a\u0188\3\2\2\2\u018a\u018b"+
		"\3\2\2\2\u018b\u018d\3\2\2\2\u018c\u018a\3\2\2\2\u018d\u018e\5N(\2\u018e"+
		"M\3\2\2\2\u018f\u0190\7<\2\2\u0190\u0191\5<\37\2\u0191\u0192\7=\2\2\u0192"+
		"\u0199\3\2\2\2\u0193\u0199\5P)\2\u0194\u0199\5R*\2\u0195\u0199\5T+\2\u0196"+
		"\u0199\5V,\2\u0197\u0199\7.\2\2\u0198\u018f\3\2\2\2\u0198\u0193\3\2\2"+
		"\2\u0198\u0194\3\2\2\2\u0198\u0195\3\2\2\2\u0198\u0196\3\2\2\2\u0198\u0197"+
		"\3\2\2\2\u0199O\3\2\2\2\u019a\u019b\7\33\2\2\u019b\u019c\5\36\20\2\u019c"+
		"Q\3\2\2\2\u019d\u019e\7)\2\2\u019e\u01a5\5\36\20\2\u019f\u01a0\7*\2\2"+
		"\u01a0\u01a1\7<\2\2\u01a1\u01a2\7.\2\2\u01a2\u01a3\7.\2\2\u01a3\u01a4"+
		"\7=\2\2\u01a4\u01a6\5\36\20\2\u01a5\u019f\3\2\2\2\u01a5\u01a6\3\2\2\2"+
		"\u01a6\u01a9\3\2\2\2\u01a7\u01a8\7+\2\2\u01a8\u01aa\5\36\20\2\u01a9\u01a7"+
		"\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aaS\3\2\2\2\u01ab\u01ac\7.\2\2\u01ac\u01b5"+
		"\7<\2\2\u01ad\u01b2\5<\37\2\u01ae\u01af\7A\2\2\u01af\u01b1\5<\37\2\u01b0"+
		"\u01ae\3\2\2\2\u01b1\u01b4\3\2\2\2\u01b2\u01b0\3\2\2\2\u01b2\u01b3\3\2"+
		"\2\2\u01b3\u01b6\3\2\2\2\u01b4\u01b2\3\2\2\2\u01b5\u01ad\3\2\2\2\u01b5"+
		"\u01b6\3\2\2\2\u01b6\u01b7\3\2\2\2\u01b7\u01b8\7=\2\2\u01b8U\3\2\2\2\u01b9"+
		"\u01ba\t\b\2\2\u01baW\3\2\2\2\u01bb\u01bc\7.\2\2\u01bcY\3\2\2\2+]flsy"+
		"\u0084\u008b\u0090\u0094\u0099\u009f\u00a7\u00ab\u00b0\u00b6\u00be\u00cc"+
		"\u00d3\u00da\u00e0\u00ef\u00fb\u0117\u0120\u0124\u012c\u0141\u014d\u0151"+
		"\u015c\u0164\u016c\u0174\u017c\u0184\u018a\u0198\u01a5\u01a9\u01b2\u01b5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}