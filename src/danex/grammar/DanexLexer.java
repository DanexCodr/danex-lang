// Generated from grammar/DanexLexer.g4 by ANTLR 4.9.2
package danex.grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DanexLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SINGLE_COMMENT", "MULTI_COMMENT", "WS", "HASH", "ARROW", "LAMBDA", "NULLCOAL", 
			"COMPARATOR", "MOD2", "VARARGS", "AND_AND", "OR_OR", "EQEQ", "NEQ", "LE", 
			"GE", "PLUSEQ", "MINUSEQ", "STAREQ", "DIVEQ", "MODEQ", "IF", "ELSE", 
			"WHILE", "DO", "FOR", "RETURN", "THROW", "EXIT", "CLASS", "PUBLIC", "PRIVATE", 
			"STATIC", "TRUE", "FALSE", "NULL", "USE", "AS", "TRY", "CATCH", "FINALLY", 
			"ASYNC", "AWAIT", "IDENTIFIER", "NUMBER", "STRING", "PLUS", "MINUS", 
			"STAR", "DIV", "MOD", "LT", "GT", "EQ", "BANG", "AND", "OR", "LPAREN", 
			"RPAREN", "LBRACE", "RBRACE", "SEMI", "COMMA"
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


	public DanexLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DanexLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2A\u0193\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\3\2\3\2\3\2\3\2\7\2\u0086\n\2\f\2\16\2\u0089\13\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\7\3\u0092\n\3\f\3\16\3\u0095\13\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\4\6\4\u009e\n\4\r\4\16\4\u009f\3\4\3\4\3\5\3\5\3\6\3"+
		"\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3"+
		"\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3"+
		"\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3!\3"+
		"!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3"+
		"$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3\'\3\'\3\'\3(\3(\3(\3(\3)\3)\3"+
		")\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3"+
		",\3-\3-\7-\u0155\n-\f-\16-\u0158\13-\3.\6.\u015b\n.\r.\16.\u015c\3.\3"+
		".\6.\u0161\n.\r.\16.\u0162\5.\u0165\n.\3/\3/\3/\3/\7/\u016b\n/\f/\16/"+
		"\u016e\13/\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65"+
		"\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3"+
		"?\3?\3@\3@\3\u0093\2A\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63"+
		"e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\3\2\b\4\2\f\f\17\17\5\2\13\f\17"+
		"\17\"\"\5\2C\\aac|\6\2\62;C\\aac|\3\2\62;\4\2$$^^\2\u019b\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"+
		"\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2"+
		"\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2"+
		"\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W"+
		"\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2"+
		"\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2"+
		"\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}"+
		"\3\2\2\2\2\177\3\2\2\2\3\u0081\3\2\2\2\5\u008c\3\2\2\2\7\u009d\3\2\2\2"+
		"\t\u00a3\3\2\2\2\13\u00a5\3\2\2\2\r\u00a8\3\2\2\2\17\u00ab\3\2\2\2\21"+
		"\u00ae\3\2\2\2\23\u00b2\3\2\2\2\25\u00b5\3\2\2\2\27\u00b9\3\2\2\2\31\u00bc"+
		"\3\2\2\2\33\u00bf\3\2\2\2\35\u00c2\3\2\2\2\37\u00c5\3\2\2\2!\u00c8\3\2"+
		"\2\2#\u00cb\3\2\2\2%\u00ce\3\2\2\2\'\u00d1\3\2\2\2)\u00d4\3\2\2\2+\u00d7"+
		"\3\2\2\2-\u00da\3\2\2\2/\u00dd\3\2\2\2\61\u00e2\3\2\2\2\63\u00e8\3\2\2"+
		"\2\65\u00eb\3\2\2\2\67\u00ef\3\2\2\29\u00f6\3\2\2\2;\u00fc\3\2\2\2=\u0101"+
		"\3\2\2\2?\u0107\3\2\2\2A\u010e\3\2\2\2C\u0116\3\2\2\2E\u011d\3\2\2\2G"+
		"\u0122\3\2\2\2I\u0128\3\2\2\2K\u012d\3\2\2\2M\u0131\3\2\2\2O\u0134\3\2"+
		"\2\2Q\u0138\3\2\2\2S\u013e\3\2\2\2U\u0146\3\2\2\2W\u014c\3\2\2\2Y\u0152"+
		"\3\2\2\2[\u015a\3\2\2\2]\u0166\3\2\2\2_\u0171\3\2\2\2a\u0173\3\2\2\2c"+
		"\u0175\3\2\2\2e\u0177\3\2\2\2g\u0179\3\2\2\2i\u017b\3\2\2\2k\u017d\3\2"+
		"\2\2m\u017f\3\2\2\2o\u0181\3\2\2\2q\u0183\3\2\2\2s\u0185\3\2\2\2u\u0187"+
		"\3\2\2\2w\u0189\3\2\2\2y\u018b\3\2\2\2{\u018d\3\2\2\2}\u018f\3\2\2\2\177"+
		"\u0191\3\2\2\2\u0081\u0082\7%\2\2\u0082\u0083\7/\2\2\u0083\u0087\3\2\2"+
		"\2\u0084\u0086\n\2\2\2\u0085\u0084\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0085"+
		"\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008a\3\2\2\2\u0089\u0087\3\2\2\2\u008a"+
		"\u008b\b\2\2\2\u008b\4\3\2\2\2\u008c\u008d\7%\2\2\u008d\u008e\7%\2\2\u008e"+
		"\u008f\7/\2\2\u008f\u0093\3\2\2\2\u0090\u0092\13\2\2\2\u0091\u0090\3\2"+
		"\2\2\u0092\u0095\3\2\2\2\u0093\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094"+
		"\u0096\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0097\7/\2\2\u0097\u0098\7%\2"+
		"\2\u0098\u0099\7%\2\2\u0099\u009a\3\2\2\2\u009a\u009b\b\3\2\2\u009b\6"+
		"\3\2\2\2\u009c\u009e\t\3\2\2\u009d\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f"+
		"\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\b\4"+
		"\2\2\u00a2\b\3\2\2\2\u00a3\u00a4\7%\2\2\u00a4\n\3\2\2\2\u00a5\u00a6\7"+
		"?\2\2\u00a6\u00a7\7@\2\2\u00a7\f\3\2\2\2\u00a8\u00a9\7/\2\2\u00a9\u00aa"+
		"\7@\2\2\u00aa\16\3\2\2\2\u00ab\u00ac\7A\2\2\u00ac\u00ad\7A\2\2\u00ad\20"+
		"\3\2\2\2\u00ae\u00af\7>\2\2\u00af\u00b0\7?\2\2\u00b0\u00b1\7@\2\2\u00b1"+
		"\22\3\2\2\2\u00b2\u00b3\7\'\2\2\u00b3\u00b4\7\'\2\2\u00b4\24\3\2\2\2\u00b5"+
		"\u00b6\7\60\2\2\u00b6\u00b7\7\60\2\2\u00b7\u00b8\7\60\2\2\u00b8\26\3\2"+
		"\2\2\u00b9\u00ba\7(\2\2\u00ba\u00bb\7(\2\2\u00bb\30\3\2\2\2\u00bc\u00bd"+
		"\7~\2\2\u00bd\u00be\7~\2\2\u00be\32\3\2\2\2\u00bf\u00c0\7?\2\2\u00c0\u00c1"+
		"\7?\2\2\u00c1\34\3\2\2\2\u00c2\u00c3\7#\2\2\u00c3\u00c4\7?\2\2\u00c4\36"+
		"\3\2\2\2\u00c5\u00c6\7>\2\2\u00c6\u00c7\7?\2\2\u00c7 \3\2\2\2\u00c8\u00c9"+
		"\7@\2\2\u00c9\u00ca\7?\2\2\u00ca\"\3\2\2\2\u00cb\u00cc\7-\2\2\u00cc\u00cd"+
		"\7?\2\2\u00cd$\3\2\2\2\u00ce\u00cf\7/\2\2\u00cf\u00d0\7?\2\2\u00d0&\3"+
		"\2\2\2\u00d1\u00d2\7,\2\2\u00d2\u00d3\7?\2\2\u00d3(\3\2\2\2\u00d4\u00d5"+
		"\7\61\2\2\u00d5\u00d6\7?\2\2\u00d6*\3\2\2\2\u00d7\u00d8\7\'\2\2\u00d8"+
		"\u00d9\7?\2\2\u00d9,\3\2\2\2\u00da\u00db\7k\2\2\u00db\u00dc\7h\2\2\u00dc"+
		".\3\2\2\2\u00dd\u00de\7g\2\2\u00de\u00df\7n\2\2\u00df\u00e0\7u\2\2\u00e0"+
		"\u00e1\7g\2\2\u00e1\60\3\2\2\2\u00e2\u00e3\7y\2\2\u00e3\u00e4\7j\2\2\u00e4"+
		"\u00e5\7k\2\2\u00e5\u00e6\7n\2\2\u00e6\u00e7\7g\2\2\u00e7\62\3\2\2\2\u00e8"+
		"\u00e9\7f\2\2\u00e9\u00ea\7q\2\2\u00ea\64\3\2\2\2\u00eb\u00ec\7h\2\2\u00ec"+
		"\u00ed\7q\2\2\u00ed\u00ee\7t\2\2\u00ee\66\3\2\2\2\u00ef\u00f0\7t\2\2\u00f0"+
		"\u00f1\7g\2\2\u00f1\u00f2\7v\2\2\u00f2\u00f3\7w\2\2\u00f3\u00f4\7t\2\2"+
		"\u00f4\u00f5\7p\2\2\u00f58\3\2\2\2\u00f6\u00f7\7v\2\2\u00f7\u00f8\7j\2"+
		"\2\u00f8\u00f9\7t\2\2\u00f9\u00fa\7q\2\2\u00fa\u00fb\7y\2\2\u00fb:\3\2"+
		"\2\2\u00fc\u00fd\7g\2\2\u00fd\u00fe\7z\2\2\u00fe\u00ff\7k\2\2\u00ff\u0100"+
		"\7v\2\2\u0100<\3\2\2\2\u0101\u0102\7e\2\2\u0102\u0103\7n\2\2\u0103\u0104"+
		"\7c\2\2\u0104\u0105\7u\2\2\u0105\u0106\7u\2\2\u0106>\3\2\2\2\u0107\u0108"+
		"\7r\2\2\u0108\u0109\7w\2\2\u0109\u010a\7d\2\2\u010a\u010b\7n\2\2\u010b"+
		"\u010c\7k\2\2\u010c\u010d\7e\2\2\u010d@\3\2\2\2\u010e\u010f\7r\2\2\u010f"+
		"\u0110\7t\2\2\u0110\u0111\7k\2\2\u0111\u0112\7x\2\2\u0112\u0113\7c\2\2"+
		"\u0113\u0114\7v\2\2\u0114\u0115\7g\2\2\u0115B\3\2\2\2\u0116\u0117\7u\2"+
		"\2\u0117\u0118\7v\2\2\u0118\u0119\7c\2\2\u0119\u011a\7v\2\2\u011a\u011b"+
		"\7k\2\2\u011b\u011c\7e\2\2\u011cD\3\2\2\2\u011d\u011e\7v\2\2\u011e\u011f"+
		"\7t\2\2\u011f\u0120\7w\2\2\u0120\u0121\7g\2\2\u0121F\3\2\2\2\u0122\u0123"+
		"\7h\2\2\u0123\u0124\7c\2\2\u0124\u0125\7n\2\2\u0125\u0126\7u\2\2\u0126"+
		"\u0127\7g\2\2\u0127H\3\2\2\2\u0128\u0129\7p\2\2\u0129\u012a\7w\2\2\u012a"+
		"\u012b\7n\2\2\u012b\u012c\7n\2\2\u012cJ\3\2\2\2\u012d\u012e\7w\2\2\u012e"+
		"\u012f\7u\2\2\u012f\u0130\7g\2\2\u0130L\3\2\2\2\u0131\u0132\7c\2\2\u0132"+
		"\u0133\7u\2\2\u0133N\3\2\2\2\u0134\u0135\7v\2\2\u0135\u0136\7t\2\2\u0136"+
		"\u0137\7{\2\2\u0137P\3\2\2\2\u0138\u0139\7e\2\2\u0139\u013a\7c\2\2\u013a"+
		"\u013b\7v\2\2\u013b\u013c\7e\2\2\u013c\u013d\7j\2\2\u013dR\3\2\2\2\u013e"+
		"\u013f\7h\2\2\u013f\u0140\7k\2\2\u0140\u0141\7p\2\2\u0141\u0142\7c\2\2"+
		"\u0142\u0143\7n\2\2\u0143\u0144\7n\2\2\u0144\u0145\7{\2\2\u0145T\3\2\2"+
		"\2\u0146\u0147\7c\2\2\u0147\u0148\7u\2\2\u0148\u0149\7{\2\2\u0149\u014a"+
		"\7p\2\2\u014a\u014b\7e\2\2\u014bV\3\2\2\2\u014c\u014d\7c\2\2\u014d\u014e"+
		"\7y\2\2\u014e\u014f\7c\2\2\u014f\u0150\7k\2\2\u0150\u0151\7v\2\2\u0151"+
		"X\3\2\2\2\u0152\u0156\t\4\2\2\u0153\u0155\t\5\2\2\u0154\u0153\3\2\2\2"+
		"\u0155\u0158\3\2\2\2\u0156\u0154\3\2\2\2\u0156\u0157\3\2\2\2\u0157Z\3"+
		"\2\2\2\u0158\u0156\3\2\2\2\u0159\u015b\t\6\2\2\u015a\u0159\3\2\2\2\u015b"+
		"\u015c\3\2\2\2\u015c\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u0164\3\2"+
		"\2\2\u015e\u0160\7\60\2\2\u015f\u0161\t\6\2\2\u0160\u015f\3\2\2\2\u0161"+
		"\u0162\3\2\2\2\u0162\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0165\3\2"+
		"\2\2\u0164\u015e\3\2\2\2\u0164\u0165\3\2\2\2\u0165\\\3\2\2\2\u0166\u016c"+
		"\7$\2\2\u0167\u016b\n\7\2\2\u0168\u0169\7^\2\2\u0169\u016b\13\2\2\2\u016a"+
		"\u0167\3\2\2\2\u016a\u0168\3\2\2\2\u016b\u016e\3\2\2\2\u016c\u016a\3\2"+
		"\2\2\u016c\u016d\3\2\2\2\u016d\u016f\3\2\2\2\u016e\u016c\3\2\2\2\u016f"+
		"\u0170\7$\2\2\u0170^\3\2\2\2\u0171\u0172\7-\2\2\u0172`\3\2\2\2\u0173\u0174"+
		"\7/\2\2\u0174b\3\2\2\2\u0175\u0176\7,\2\2\u0176d\3\2\2\2\u0177\u0178\7"+
		"\61\2\2\u0178f\3\2\2\2\u0179\u017a\7\'\2\2\u017ah\3\2\2\2\u017b\u017c"+
		"\7>\2\2\u017cj\3\2\2\2\u017d\u017e\7@\2\2\u017el\3\2\2\2\u017f\u0180\7"+
		"?\2\2\u0180n\3\2\2\2\u0181\u0182\7#\2\2\u0182p\3\2\2\2\u0183\u0184\7("+
		"\2\2\u0184r\3\2\2\2\u0185\u0186\7~\2\2\u0186t\3\2\2\2\u0187\u0188\7*\2"+
		"\2\u0188v\3\2\2\2\u0189\u018a\7+\2\2\u018ax\3\2\2\2\u018b\u018c\7}\2\2"+
		"\u018cz\3\2\2\2\u018d\u018e\7\177\2\2\u018e|\3\2\2\2\u018f\u0190\7=\2"+
		"\2\u0190~\3\2\2\2\u0191\u0192\7.\2\2\u0192\u0080\3\2\2\2\f\2\u0087\u0093"+
		"\u009f\u0156\u015c\u0162\u0164\u016a\u016c\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}