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

NUM = [0-9]+ ("." [0-9]+)?
NL  = \n | \r | \r\n
PERIOD = "."
DEFINITION = "is a "
FUNCTION = "Make"
CELL = "at cell"
STR_LIT = \"*?\"
VARIABLE = [a-zA-z]+
COMMA = ","


%%

/* operators */
"+" | 
"-" | 
"*" | 
"/" | 
"^" | 
"(" | 
")"    { return (int) yycharat(0); }

/* newline */
{NL}   { return Words.NL; }
{DEFINITION} { return Words.DEFINITION; }
{PERIOD} { return Words.PERIOD; }
{FUNCTION} { return Words.FUNCTION; }
{CELL} { return Words.CELL; }
{STR_LIT} { yyparser.yylval = new WordsVal(yytext());
				return Words.STR_LIT; }
{VARIABLE} { yyparser.yylval = new WordsVal(yytext());
				return Words.VARIABLE; }
{COMMA} {	return Words.COMMA;}

/* float */
{NUM}  { yyparser.yylval = new WordsVal(Double.parseDouble(yytext()));
         return Words.NUM; }

/* whitespace */
[ \t]+ { }

\b     { System.err.println("Sorry, backspace doesn't work"); }

/* error fallback */
[^]    { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1; }
