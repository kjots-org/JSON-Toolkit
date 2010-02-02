package org.kjots.json.content.rebase.org.json.simple.parser;

/**
 * Modified for use in <em>JSON Toolkit</em> by <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>:
 * <ul>
 * <li>Rebased into <code>org.kjots.json.content.rebase.org.json.simple.parser</code></li>
 * <li>Updated to use <code>java.math.BigDecimal</code> for numeric values</li>
 * <li>Updated to reject leading zeros in numbers, as per JSON specification (RFC 4627 section 2.4)</li>
 * </ul>
 */
%%

%{
private StringBuffer sb=new StringBuffer();

int getPosition(){
	return yychar;
}

%}

%table
%unicode
%state STRING_BEGIN

%yylexthrow ParseException
%char

HEX_D = [a-fA-F0-9]
NUMBER = [-]?([0]|[1-9][0-9]*)((\.[0-9]+)?([eE][-+]?[0-9]+)?)
WS = [ \t\r\n]
UNESCAPED_CH = [^\"\\]
FALLBACK_CH = .
%%

<STRING_BEGIN> \"	 			{ yybegin(YYINITIAL);return new Yytoken(Yytoken.TYPE_VALUE, sb.toString());}
<STRING_BEGIN> {UNESCAPED_CH}+	{ sb.append(yytext());}
<STRING_BEGIN> \\\" 			{sb.append('"');}
<STRING_BEGIN> \\\\				{sb.append('\\');}
<STRING_BEGIN> \\\/				{sb.append('/');}
<STRING_BEGIN> \\b				{sb.append('\b');}
<STRING_BEGIN> \\f				{sb.append('\f');}
<STRING_BEGIN> \\n				{sb.append('\n');}
<STRING_BEGIN> \\r				{sb.append('\r');}
<STRING_BEGIN> \\t				{sb.append('\t');}
<STRING_BEGIN> \\u{HEX_D}{HEX_D}{HEX_D}{HEX_D}	{	try{
														int ch=Integer.parseInt(yytext().substring(2),16);
														sb.append((char)ch);
													}
													catch(Exception e){
														throw new ParseException(yychar, ParseException.ERROR_UNEXPECTED_EXCEPTION, e);
													}
												}
<STRING_BEGIN> \\				{sb.append('\\');}
												
<YYINITIAL> \" 					{ sb.delete(0, sb.length());yybegin(STRING_BEGIN);}
<YYINITIAL> {NUMBER}    { java.math.BigDecimal val=new java.math.BigDecimal(yytext()); return new Yytoken(Yytoken.TYPE_VALUE, val);}
<YYINITIAL> "true"|"false"		{ Boolean val=Boolean.valueOf(yytext()); return new Yytoken(Yytoken.TYPE_VALUE, val);}
<YYINITIAL> "null"				{ return new Yytoken(Yytoken.TYPE_VALUE, null);}
<YYINITIAL> "{"					{ return new Yytoken(Yytoken.TYPE_LEFT_BRACE,null);}
<YYINITIAL> "}"					{ return new Yytoken(Yytoken.TYPE_RIGHT_BRACE,null);}
<YYINITIAL> "["					{ return new Yytoken(Yytoken.TYPE_LEFT_SQUARE,null);}
<YYINITIAL> "]"					{ return new Yytoken(Yytoken.TYPE_RIGHT_SQUARE,null);}
<YYINITIAL> ","					{ return new Yytoken(Yytoken.TYPE_COMMA,null);}
<YYINITIAL> ":"					{ return new Yytoken(Yytoken.TYPE_COLON,null);}
<YYINITIAL> {WS}+		    	{}
<YYINITIAL> {FALLBACK_CH}		{ throw new ParseException(yychar, ParseException.ERROR_UNEXPECTED_CHAR, new Character(yycharat(0)));}