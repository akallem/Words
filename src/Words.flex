/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2000 Gerwin Klein <lsf@jflex.de>                          *
 * All rights reserved.                                                    *
 *                                                                         *
 * Thanks to Larry Bell and Bob Jamison for suggestions and comments.      *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

%%


%byaccj

%{
  private Words yyparser;

  public Yylex(java.io.Reader r, Words yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

	/* Lexemes */
IDENTIFIER = [a-zA-Z_][a-zA-Z0-9_]*
NUM = [0-9]+ ("." [0-9]+)?
STRING = \"[^\"\n]*\"

	/* Multi-character Operators */
DEREF = "'s"
GEQ = ">="
LEQ = "<="


%%


	/* Single-character Operators and Separators */
"+" | 
"-" | 
"*" | 
"/" | 
"^" |
"=" |
"<" |
">" | 
"(" | 
")" |
"{" |
"}"	|
","	|
"."		{ return (int) yycharat(0); }

	/* Keywords */
"A" | "a"			{ return Words.A; }
"and"				{ return Words.AND; }
"anywhere"			{ return Words.ANYWHERE; }
"as"				{ return Words.AS; }
"at"				{ return Words.AT; }
"be"				{ return Words.BE; }
"can"				{ return Words.CAN; }
"down"				{ return Words.DOWN; }
"has"				{ return Words.HAS; }
"If"				{ return Words.IF; }
"is"				{ return Words.IS; }
"left"				{ return Words.LEFT; }
"long"				{ return Words.LONG; }
"Make"				{ return Words.MAKE; }
"move"				{ return Words.MOVE; }
"moves"				{ return Words.MOVES; }
"means"				{ return Words.MEANS; }
"not"				{ return Words.NOT; }
"nothing"			{ return Words.NOTHING; }
"now"				{ return Words.NOW; }
"of"				{ return Words.OF; }
"or"				{ return Words.OR; }
"Remove"			{ return Words.REMOVE; }
"Repeat"			{ return Words.REPEAT; }
"right"				{ return Words.RIGHT; }
"say"				{ return Words.SAY; }
"says"				{ return Words.SAYS; }
"Stop"				{ return Words.STOP; }
"then"				{ return Words.THEN; }
"times"				{ return Words.TIMES; }
"turns"				{ return Words.TURNS; }
"up"				{ return Words.UP; }
"wait"				{ return Words.WAIT; }
"waits"				{ return Words.WAITS; }
"Whenever"			{ return Words.WHENEVER; }
"which" | "that"	{ return Words.WHICH; }
"While"				{ return Words.WHILE; }
"with"				{ return Words.WITH; }

	/* Other lexemes */
{IDENTIFIER} 	{ yyparser.yylval = new WordsVal(yytext()); return Words.IDENTIFIER; }
{NUM}			{ yyparser.yylval = new WordsVal(Double.parseDouble(yytext())); return Words.NUM; }
{STRING}		{ yyparser.yylval = new WordsVal(yytext());	return Words.STRING; }

	/* Multi-character operators */
{DEREF}			{ return Words.DEREF; }
{GEQ}			{ return Words.GEQ; }
{LEQ}			{ return Words.LEQ; }

	/* Whitespace (no action) */
[ \t\r\n]+ 		{ }

	/* Backspace (error) */
\b				{ System.err.println("Sorry, backspace doesn't work"); }

	/* Error fallback */
[^]				{ System.err.println("Error: unexpected character '" + yytext() + "'"); return -1; }
