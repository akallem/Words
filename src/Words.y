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
  import java.util.HashMap;
%}
      
%token NL          /* newline  */
%token <dval> NUM  /* a number */
%token DEFINITION
%token PERIOD
%token FUNCTION
%token CELL
%token COMMA
%token <sval> STR_LIT
%token <sval> VARIABLE

%type <obj> command
%type <sval> arguments
%type <sval> argument
%left '-' '+'
%left '*' '/'
%left NEG          /* negation--unary minus */
%right '^'         /* exponentiation        */
      
%%

input:   /* empty string */
       | input line
       ;
      
line:  command PERIOD NL  { game.addCommandToQueue($1); }
       ;
       
command: 	VARIABLE DEFINITION VARIABLE CELL NUM COMMA NUM { 
				HashMap properties = new HashMap<String, Object>();
				properties.put("name", $1);
				properties.put("class", $3);
				WordsPosition pos = new WordsPosition($5, $7);
				properties.put("cell", pos);
				$$ = new Command(CommandType.CREATE, properties); 
				}
		|	FUNCTION VARIABLE VARIABLE arguments {
				HashMap properties = new HashMap<String, Object>();
				properties.put("objectName", $2);
				properties.put("functionName", $3);
				properties.put("arguments", $4);
				$$ = new Command(CommandType.MAKE, properties); 
				}
		;

argument	:	STR_LIT { $$ = $1;}
			|	NUM	{$$ = Double.toString($1);}
			|	VARIABLE {$$ = $1;}
			;
		
arguments	: 	argument	{$$ = $1;}
			|	arguments argument {$$ = $1 + " " + $2;}
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


  static Game game;

  public static void main(String args[]) throws IOException {
    System.out.println("Welcome to Words!");
    
    WordsUI ui = new WordsUI();
    game = new Game(ui);
    game.start();

    Words yyparser;
    // interactive mode
	System.out.println("Input your block of commands with CTRL-D");

	while (true) {
		yyparser = new Words(new InputStreamReader(System.in));
    	yyparser.yyparse();
    	System.out.println("Your commands have been enqueued");
    }
  }
