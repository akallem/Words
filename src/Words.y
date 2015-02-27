/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2001 Gerwin Klein <lsf@jflex.de>                          *
 * All rights reserved.                                                    *
 *                                                                         *
 * This is a modified version of the example from                          *
 *   http://www.lincom-asg.com/~rjamison/byacc/                            *
 *                                                                         *
 * Thanks to Larry Bell and Bob Jamison for suggestions and comments.      *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

%{
  import java.io.*;
%}
      
%token NL          /* newline  */
%token <dval> NUM  /* a number */
%token DEFINITION
%token PERIOD
%token FUNCTION
%token CELL
%token <val> VARIABLE

%type <dval> exp

%left '-' '+'
%left '*' '/'
%left NEG          /* negation--unary minus */
%right '^'         /* exponentiation        */
      
%%

input:   /* empty string */
       | input line
       ;
      
line:    NL      { System.out.print("Expression: "); }
       | command PERIOD NL  { game.addCommandToQueue($1)); }
       ;
       
command: { $$ = null;}
       
exp:     NUM                { $$ = $1; }
       | exp '+' exp        { $$ = $1 + $3; }
       | exp '-' exp        { $$ = $1 - $3; }
       | exp '*' exp        { $$ = $1 * $3; }
       | exp '/' exp        { $$ = $1 / $3; }
       | '-' exp  %prec NEG { $$ = -$2; }
       | exp '^' exp        { $$ = Math.pow($1, $3); }
       | '(' exp ')'        { $$ = $2; }
       ;

%%

  private Yylex lexer;


  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new WordsVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error);
  }


  public Words(Reader r) {
    lexer = new Yylex(r, this);
  }


  static boolean interactive;

  public static void main(String args[]) throws IOException {
    System.out.println("Welcome to Words!");
    
    WordsUI ui = new WordsUI();
    Game game = new Game();

    Words yyparser;
    // interactive mode
	System.out.println("[Quit with CTRL-D]");
	System.out.print("Expression: ");
	yyparser = new Words(new InputStreamReader(System.in));

    yyparser.yyparse();
    
    if (interactive) {
      System.out.println();
      System.out.println("Have a nice day");
    }
  }
