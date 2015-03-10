/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2001 Gerwin Klein <lsf@jflex.de>                          *
 * All rights reserved.                                                    *
 *                                                                         *
 * This is a modified version of the example from                          *
 *   http://www.lincom_asg.com/~rjamison/byacc/                            *
 *                                                                         *
 * Thanks to Larry Bell and Bob Jamison for suggestions and comments.      *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

%{
  import java.io.*;
%}
     
	/* Keyword-related tokens */
%token A
%token AND
%token ANYWHERE
%token AS
%token AT
%token BE
%token CAN
%token DOWN
%token HAS
%token IF
%token IS
%token LEFT
%token LONG
%token MAKE
%token MOVE
%token MOVES
%token MEANS
%token NOT
%token NOTHING
%token NOW
%token OF
%token OR
%token REMOVE
%token REPEAT
%token RIGHT
%token SAY
%token SAYS
%token STOP
%token THEN
%token TIMES
%token TURNS
%token UP
%token WAIT
%token WAITS
%token WHENEVER
%token WHICH
%token WHILE
%token WITH

	/* Other lexeme-related tokens */
%token <sval> IDENTIFIER
%token <dval> NUM
%token <sval> STRING

	/* Multi-character operators */
%token DEREF
%token GEQ
%token LEQ

	/* Non-terminal symbol types */
%type <obj> statement_list
%type <obj> statement
%type <obj> immediate_statement
%type <obj> class_create_statement
%type <obj> class_definition_statement_list
%type <obj> class_definition_statement
%type <obj> class_property_definition_statement
%type <obj> class_custom_action_definition_statement
%type <obj> object_create_statement
%type <obj> property_assign_statement
%type <obj> iteration_statement
%type <obj> conditional_statement
%type <obj> listener_statement
%type <obj> queueing_statement
%type <obj> queueing_custom_action_statement
%type <obj> predicate
%type <obj> basic_action_predicate
%type <obj> boolean_predicate
%type <obj> relational_expression
%type <obj> value_expression
%type <obj> reference
%type <obj> direction
%type <obj> position
%type <obj> identifier_list
%type <obj> parameter_list
%type <obj> parameter
%type <obj> literal

	/* Operators precedence and associativity */
%left AND
%left OR
%left '-' '+'
%left '*' '/'
%right '^'			/* exponentiation */
%nonassoc UMINUS


%%


statement_list:
		statement					{}
	|	statement statement_list	{}

statement:
		immediate_statement			{}
	|	queueing_statement			{}

immediate_statement:
		class_create_statement		{}
	|	object_create_statement		{}
	|	object_destroy_statement	{}
	|	property_assign_statement	{}
	|	iteration_statement			{}
	|	conditional_statement		{}
	|	listener_statement			{}

class_create_statement:
		A IDENTIFIER IS A IDENTIFIER '.'											{}
	|	A IDENTIFIER IS A IDENTIFIER WHICH '{' class_definition_statement_list '}'	{}
	;

class_definition_statement_list:
		class_definition_statement										{}
	|	class_definition_statement class_definition_statement_list		{}
	;

class_definition_statement:
		class_property_definition_statement			{}
	|	class_custom_action_definition_statement	{}
	;

class_property_definition_statement:
		HAS A IDENTIFIER '.'				{}
	|	HAS A IDENTIFIER OF literal '.'		{}
	;

class_custom_action_definition_statement:
		CAN IDENTIFIER WHICH MEANS '{' statement_list '}'							{}
	|	CAN IDENTIFIER WITH identifier_list WHICH MEANS '{' statement_list '}'		{}
	;

object_create_statement:
		IDENTIFIER IS A IDENTIFIER AT position '.'							{}
	|	IDENTIFIER IS A IDENTIFIER WITH parameter_list AT position '.'		{}
	;

object_destroy_statement:
		REMOVE reference '.'		{}
	;

property_assign_statement:
		IDENTIFIER DEREF reference IS value_expression '.'		{}
	;

iteration_statement:
		REPEAT value_expression TIMES '{' statement_list '}'	{}
	|	WHILE boolean_predicate '{' statement_list '}'			{}
	;

conditional_statement:
		IF boolean_predicate THEN '{' statement_list '}'		{}
	;

listener_statement:
		WHENEVER predicate '{' statement_list '}'					{}
	|	AS LONG AS predicate '{' statement_list '}'					{}
	;

queueing_statement:
		MAKE reference BE value_expression '.'						{}
	|	MAKE reference BE value_expression NOW '.'					{}
	|	MAKE reference MOVE direction '.'							{}
	|	MAKE reference MOVE direction NOW '.'						{}
	|	MAKE reference MOVE direction value_expression '.'			{}
	|	MAKE reference MOVE direction value_expression NOW '.'		{}
	|	MAKE reference SAY value_expression '.'						{}
	|	MAKE reference SAY value_expression NOW '.'					{}
	|	MAKE reference WAIT value_expression TURNS '.'				{}
	|	MAKE reference WAIT value_expression TURNS NOW '.'			{}
	|	STOP reference '.'											{}
	|	queueing_custom_action_statement							{}
	;

queueing_custom_action_statement:
		MAKE reference IDENTIFIER '.'								{}
	|	MAKE reference IDENTIFIER NOW '.'							{}
	|	MAKE reference IDENTIFIER WITH identifier_list '.'			{}
	|	MAKE reference IDENTIFIER WITH identifier_list NOW '.'		{}
	;

predicate:
		basic_action_predicate		{}
	|	boolean_predicate			{}
	;

basic_action_predicate:
		reference MOVES						{}
	|	reference MOVES direction			{}
	|	reference SAYS value_expression		{}
	|	reference WAITS						{}
	;

boolean_predicate:
		relational_expression						{}
	|	'(' boolean_predicate ')'					{}
	|	NOT '(' boolean_predicate ')'				{}
	|	boolean_predicate AND boolean_predicate		{}
	|	boolean_predicate OR boolean_predicate		{}
	;

relational_expression:
		value_expression '=' value_expression		{}
	|	value_expression '<' value_expression		{}
	|	value_expression '>' value_expression		{}
	|	value_expression LEQ value_expression		{}
	|	value_expression GEQ value_expression		{}
	;

value_expression:
		IDENTIFIER									{}
	|	literal										{}
	|	NOTHING										{}
	|	'(' value_expression ')'					{}
	|	'-' value_expression %prec UMINUS			{}
	|	value_expression '+' value_expression		{}
	|	value_expression '-' value_expression		{}
	|	value_expression '*' value_expression		{}
	|	value_expression '/' value_expression		{}
	|	value_expression '^' value_expression		{}
	;

reference:
		IDENTIFIER							{}
	|	reference DEREF IDENTIFIER			{}
	;

direction:
		ANYWHERE		{}
	|	DOWN			{}
	|	LEFT			{}
	|	RIGHT			{}
	|	UP				{}
	;

position:
		value_expression ',' value_expression	{}
	;

identifier_list:
		IDENTIFIER								{}
	|	IDENTIFIER ',' identifier_list			{}
	;

parameter_list:
		parameter								{}
	|	parameter ',' parameter_list			{}
	;

parameter:
		IDENTIFIER value_expression				{}
	;

literal:
		NUM			{}
	|	STRING		{}
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
	System.out.println("[Quit with CTRL_D]");
	yyparser = new Words(new InputStreamReader(System.in));

    yyparser.yyparse();
    System.out.println();
    System.out.println("Have a nice day");
  }
