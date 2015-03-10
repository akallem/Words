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

	/* Keywords */
A = "A|a|An|an"
AND = "and"
ANYWHERE = "anywhere"
AS = "as"
AT = "at"
BE = "be"
CAN = "can"
DOWN = "down"
HAS = "has"
IF = "If"
IS = "is"
LEFT = "left"
LONG = "long"
MAKE = "Make"
MOVE = "move"
MOVES = "moves"
MEANS = "means"
NOT = "not"
NOTHING = "nothing"
NOW = "now"
OF = "of"
OR = "or"
REMOVE = "Remove"
REPEAT = "Repeat"
RIGHT = "right"
SAY = "say"
SAYS = "says"
STOP = "Stop"
THEN = "then"
TIMES = "times"
TURNS = "turns"
UP = "up"
WAIT = "wait"
WAITS = "waits"
WHENEVER = "Whenever"
WHICH = "which|that"
WHILE = "While"
WITH = "with"

	/* Other lexemes */
IDENTIFIER = [a-zA-z_][a-zA-z0-9_]*
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
"(" | 
")" |
"{" |
"}"	|
","	|
"."		{ return (int) yycharat(0); }

	/* Keywords */
{A}				{ return Words.A; }
{AND}			{ return Words.AND; }
{ANYWHERE}		{ return Words.ANYWHERE; }
{AS}			{ return Words.AS; }
{AT}			{ return Words.AT; }
{BE}			{ return Words.BE; }
{CAN}			{ return Words.CAN; }
{DOWN}			{ return Words.DOWN; }
{HAS}			{ return Words.HAS; }
{IF}			{ return Words.IF; }
{IS}			{ return Words.IS; }
{LEFT}			{ return Words.LEFT; }
{LONG}			{ return Words.LONG; }
{MAKE}			{ return Words.MAKE; }
{MOVE}			{ return Words.MOVE; }
{MOVES}			{ return Words.MOVES; }
{MEANS}			{ return Words.MEANS; }
{NOT}			{ return Words.NOT; }
{NOTHING}		{ return Words.NOTHING; }
{NOW}			{ return Words.NOW; }
{OF}			{ return Words.OF; }
{OR}			{ return Words.OR; }
{REMOVE}		{ return Words.REMOVE; }
{REPEAT}		{ return Words.REPEAT; }
{RIGHT}			{ return Words.RIGHT; }
{SAY}			{ return Words.SAY; }
{SAYS}			{ return Words.SAYS; }
{STOP}			{ return Words.STOP; }
{THEN}			{ return Words.THEN; }
{TIMES}			{ return Words.TIMES; }
{TURNS}			{ return Words.TURNS; }
{UP}			{ return Words.UP; }
{WAIT}			{ return Words.WAIT; }
{WAITS}			{ return Words.WAITS; }
{WHENEVER}		{ return Words.WHENEVER; }
{WHICH}			{ return Words.WHICH; }
{WHILE}			{ return Words.WHILE; }
{WITH}			{ return Words.WITH; }


	/* Other lexemes */
{IDENTIFIER} 	{ yyparser.yylval = new WordsVal(yytext()); return Words.IDENTIFIER; }
{NUM}			{ yyparser.yylval = new WordsVal(Double.parseDouble(yytext())); return Words.NUM; }
{STRING}		{ yyparser.yylval = new WordsVal(yytext());	return Words.STRING; }

	/* Multi-character operators */
{DEREF}			{ return Words.DEREF; }
{GEQ}			{ return Words.GEQ; }
{LEQ}			{ return Words.LEQ; }

	/* Whitespace (no action) */
[ \t\n]+ 		{ }

	/* Backspace (error) */
\b				{ System.err.println("Sorry, backspace doesn't work"); }

	/* Error fallback */
[^]				{ System.err.println("Error: unexpected character '"+yytext()+"'"); return -1; }
