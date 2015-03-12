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
%token <sval> REFERENCE
%token <dval> NUM
%token <sval> STRING

	/* Multi-character operators */
%token GEQ
%token LEQ

	/* Non-terminal symbol types */
%type <obj> program
%type <obj> statement_list
%type <obj> statement
%type <obj> immediate_statement
%type <obj> class_create_statement
%type <obj> class_definition_statement_list
%type <obj> class_definition_statement
%type <obj> class_property_definition_statement
%type <obj> class_custom_action_definition_statement
%type <obj> object_create_statement
%type <obj> object_destroy_statement
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
%type <obj> assignment_list
%type <obj> reference_list
%type <obj> direction
%type <obj> now
%type <obj> position
%type <obj> identifier_list
%type <obj> parameter_list
%type <obj> parameter
%type <obj> identifier
%type <obj> reference
%type <obj> assignment
%type <obj> literal

	/* Operators precedence and associativity */
%left AND
%left OR
%left '-' '+'
%left '*' '/'
%right '^'			/* exponentiation */
%nonassoc UMINUS


%%


program:
		statement_list				{ $$ = $1; root = (AST) $$; }
	;

statement_list:
		statement					{ $$ = new INode(AST.Type.STATEMENT_LIST, $1); }
	|	statement statement_list	{ $$ = new INode(AST.Type.STATEMENT_LIST, $1); ((INode) $$).add(((INode) $2).children); }

statement:
		immediate_statement			{ $$ = $1; }
	|	queueing_statement			{ $$ = $1; }
	|	error { System.err.println("Syntax error on line " + lexer.lineNumber + " near '" + lexer.yytext() + "'"); } '.' { yyerrflag = 0; }

immediate_statement:
		class_create_statement		{ $$ = $1; }
	|	object_create_statement		{ $$ = $1; }
	|	object_destroy_statement	{ $$ = $1; }
	|	property_assign_statement	{ $$ = $1; }
	|	iteration_statement			{ $$ = $1; }
	|	conditional_statement		{ $$ = $1; }
	|	listener_statement			{ $$ = $1; }

class_create_statement:
		A identifier IS A identifier '.'											{ $$ = new INode(AST.Type.CREATE_CLASS, $2, $5, null); }
	|	A identifier IS A identifier WHICH '{' class_definition_statement_list '}'	{ $$ = new INode(AST.Type.CREATE_CLASS, $2, $5, $8); }
	;

class_definition_statement_list:
		class_definition_statement													{ $$ = new INode(AST.Type.CLASS_STATEMENT_LIST, $1); }
	|	class_definition_statement class_definition_statement_list					{ $$ = new INode(AST.Type.CLASS_STATEMENT_LIST, $1); ((INode) $$).add(((INode) $2).children); }
	;

class_definition_statement:
		class_property_definition_statement											{ $$ = $1; }
	|	class_custom_action_definition_statement									{ $$ = $1; }
	;

class_property_definition_statement:
		HAS A identifier '.'														{ $$ = new INode(AST.Type.DEFINE_PROPERTY, $3, null); }
	|	HAS A identifier OF literal '.'												{ $$ = new INode(AST.Type.DEFINE_PROPERTY, $3, $5); }
	;

class_custom_action_definition_statement:
		CAN identifier WHICH MEANS '{' statement_list '}'							{ $$ = new INode(AST.Type.DEFINE_ACTION, $2, null, $6); }
	|	CAN identifier WITH identifier_list WHICH MEANS '{' statement_list '}'		{ $$ = new INode(AST.Type.DEFINE_ACTION, $2, $4, $8); }
	;

object_create_statement:
		identifier IS A identifier AT position '.'									{ $$ = new INode(AST.Type.CREATE_OBJ, $1, $4, null, $6); }
	|	identifier IS A identifier WITH parameter_list AT position '.'				{ $$ = new INode(AST.Type.CREATE_OBJ, $1, $4, $6, $8); }
	;

object_destroy_statement:
		REMOVE reference_list identifier '.'										{ $$ = new INode(AST.Type.REMOVE, $2, $3); }
	;

property_assign_statement:
		identifier IS value_expression '.'											{ $$ = new INode(AST.Type.ASSIGN, new INode(AST.Type.REFERENCE_LIST), $1, $3); }
	|	reference reference_list identifier IS value_expression '.'					{ $$ = new INode(AST.Type.ASSIGN, (new INode(AST.Type.REFERENCE_LIST, $1)).add(((INode) $2).children), $3, $5); }
	;

iteration_statement:
		REPEAT value_expression TIMES '{' statement_list '}'						{ $$ = new INode(AST.Type.REPEAT, $2, $5); }
	|	WHILE boolean_predicate '{' statement_list '}'								{ $$ = new INode(AST.Type.WHILE, $2, $4); }
	;

conditional_statement:
		IF boolean_predicate THEN '{' statement_list '}'							{ $$ = new INode(AST.Type.IF, $2, $5); }
	;

listener_statement:
		WHENEVER predicate '{' statement_list '}'									{ $$ = new INode(AST.Type.LISTENER_PERM, $2, $4); }
	|	AS LONG AS predicate '{' statement_list '}'									{ $$ = new INode(AST.Type.LISTENER_TEMP, $4, $6); }
	;

queueing_statement:
		MAKE reference_list assignment_list '.'										{ $$ = new INode(AST.Type.QUEUE_ASSIGN, $2, $3); }
	|	MAKE reference_list assignment_list now '.'									{ $$ = new INode(AST.Type.QUEUE_ASSIGN, $2, $3, $4); }
	|	MAKE reference_list identifier MOVE direction '.'							{ $$ = new INode(AST.Type.QUEUE_MOVE, $2, $3, $5); }
	|	MAKE reference_list identifier MOVE direction now '.'						{ $$ = new INode(AST.Type.QUEUE_MOVE, $2, $3, $5, $6); }
	|	MAKE reference_list identifier MOVE direction value_expression '.'			{ $$ = new INode(AST.Type.QUEUE_MOVE, $2, $3, $5, $6); }
	|	MAKE reference_list identifier MOVE direction value_expression now '.'		{ $$ = new INode(AST.Type.QUEUE_MOVE, $2, $3, $5, $6, $7); }
	|	MAKE reference_list identifier SAY value_expression '.'						{ $$ = new INode(AST.Type.QUEUE_SAY, $2, $3, $5); }
	|	MAKE reference_list identifier SAY value_expression now '.'					{ $$ = new INode(AST.Type.QUEUE_SAY, $2, $3, $5, $6); }
	|	MAKE reference_list identifier WAIT value_expression TURNS '.'				{ $$ = new INode(AST.Type.QUEUE_WAIT, $2, $3, $5); }
	|	MAKE reference_list identifier WAIT value_expression TURNS now '.'			{ $$ = new INode(AST.Type.QUEUE_WAIT, $2, $3, $5, $7); }
	|	STOP reference_list identifier '.'											{ $$ = new INode(AST.Type.QUEUE_STOP, $2, $3); }
	|	queueing_custom_action_statement											{ $$ = $1; }
	;

queueing_custom_action_statement:
		MAKE reference_list identifier identifier '.'								{ $$ = new INode(AST.Type.QUEUE_ACTION, $2, $3, $4); }
	|	MAKE reference_list identifier identifier now '.'							{ $$ = new INode(AST.Type.QUEUE_ACTION, $2, $3, $4, $5); }
	|	MAKE reference_list identifier identifier WITH parameter_list '.'			{ $$ = new INode(AST.Type.QUEUE_ACTION, $2, $3, $4, $6); }
	|	MAKE reference_list identifier identifier WITH parameter_list now '.'		{ $$ = new INode(AST.Type.QUEUE_ACTION, $2, $3, $4, $6, $7); }
	;

predicate:
		basic_action_predicate														{ $$ = $1; }
	|	boolean_predicate															{ $$ = $1; }
	;

basic_action_predicate:
		reference_list identifier MOVES												{ $$ = new INode(AST.Type.MOVES_PREDICATE, $1, $2); }
	|	reference_list identifier MOVES direction									{ $$ = new INode(AST.Type.MOVES_PREDICATE, $1, $2, $4); }
	|	reference_list identifier SAYS value_expression								{ $$ = new INode(AST.Type.SAYS_PREDICATE, $1, $2, $4); }
	|	reference_list identifier WAITS												{ $$ = new INode(AST.Type.WAITS_PREDICATE, $1, $2); }
	;

boolean_predicate:
		relational_expression						{ $$ = $1; }
	|	'(' boolean_predicate ')'					{ $$ = $2; }
	|	NOT '(' boolean_predicate ')'				{ $$ = new INode(AST.Type.NOT, $3); }
	|	boolean_predicate AND boolean_predicate		{ $$ = new INode(AST.Type.AND, $1, $3); }
	|	boolean_predicate OR boolean_predicate		{ $$ = new INode(AST.Type.OR, $1, $3); }
	;

relational_expression:
		value_expression '=' value_expression		{ $$ = new INode(AST.Type.EQUALS, $1, $3); }
	|	value_expression '<' value_expression		{ $$ = new INode(AST.Type.LESS, $1, $3); }
	|	value_expression '>' value_expression		{ $$ = new INode(AST.Type.GREATER, $1, $3); }
	|	value_expression LEQ value_expression		{ $$ = new INode(AST.Type.LEQ, $1, $3); }
	|	value_expression GEQ value_expression		{ $$ = new INode(AST.Type.GEQ, $1, $3); }
	;

value_expression:
		reference_list identifier					{ $$ = new INode(AST.Type.PROPERTY, $1, $2); }
	|	literal										{ $$ = $1; }
	|	NOTHING										{ $$ = new LNode(AST.Type.NOTHING); }
	|	'(' value_expression ')'					{ $$ = $2; }
	|	'-' value_expression %prec UMINUS			{ $$ = new INode(AST.Type.NEGATE, $2); }
	|	value_expression '+' value_expression		{ $$ = new INode(AST.Type.ADD, $1, $3); }
	|	value_expression '-' value_expression		{ $$ = new INode(AST.Type.SUBTRACT, $1, $3); }
	|	value_expression '*' value_expression		{ $$ = new INode(AST.Type.MULTIPLY, $1, $3); }
	|	value_expression '/' value_expression		{ $$ = new INode(AST.Type.DIVIDE, $1, $3); }
	|	value_expression '^' value_expression		{ $$ = new INode(AST.Type.EXPONENTIATE, $1, $3); }
	;

reference_list:
													{ $$ = new INode(AST.Type.REFERENCE_LIST); }
	|	reference reference_list					{ $$ = new INode(AST.Type.REFERENCE_LIST, $1); ((INode) $$).add(((INode) $2).children); }
	;

identifier_list:
		identifier									{ $$ = new INode(AST.Type.IDENTIFIER_LIST, $1); }
	|	identifier ',' identifier_list				{ $$ = new INode(AST.Type.IDENTIFIER_LIST, $1); ((INode) $$).add(((INode) $3).children); }
	;

parameter_list:
		parameter									{ $$ = new INode(AST.Type.PARAMETER_LIST, $1); }
	|	parameter ',' parameter_list				{ $$ = new INode(AST.Type.PARAMETER_LIST, $1); ((INode) $$).add(((INode) $3).children); }
	;

assignment_list:
		assignment									{ $$ = new INode(AST.Type.QUEUE_ASSIGNMENT_LIST, $1); }
	|	assignment ',' assignment_list				{ $$ = new INode(AST.Type.QUEUE_ASSIGNMENT_LIST, $1); ((INode) $$).add(((INode) $3).children); }
	;


reference:
		REFERENCE									{ $$ = new LNode(AST.Type.REFERENCE, $1); }
	;

identifier:
		IDENTIFIER									{ $$ = new LNode(AST.Type.IDENTIFIER, $1); }
	;

parameter:
		identifier value_expression					{ $$ = new INode(AST.Type.PARAMETER, $1, $2); }
	;

assignment:
		identifier BE value_expression				{ $$ = new INode(AST.Type.QUEUE_ASSIGNMENT, $1, $3); }
	;

direction:
		ANYWHERE									{ $$ = new LNode(AST.Type.DIRECTION, Direction.Type.ANYWHERE); }
	|	DOWN										{ $$ = new LNode(AST.Type.DIRECTION, Direction.Type.DOWN); }
	|	LEFT										{ $$ = new LNode(AST.Type.DIRECTION, Direction.Type.LEFT); }
	|	RIGHT										{ $$ = new LNode(AST.Type.DIRECTION, Direction.Type.RIGHT); }
	|	UP											{ $$ = new LNode(AST.Type.DIRECTION, Direction.Type.UP); }
	;

now:
		NOW											{ $$ = new LNode(AST.Type.NOW); }
	;

position:
		value_expression ',' value_expression		{ $$ = new INode(AST.Type.POSITION, $1, $3); }
	;

literal:
		NUM											{ $$ = new LNode(AST.Type.NUM, $1); }
	|	STRING										{ $$ = new LNode(AST.Type.STRING, $1); }
	;


%%


private Yylex lexer;

private int yylex() {
	int yyl_return = -1;
	try {
		yylval = new WordsVal(0);
		yyl_return = lexer.yylex();
	} catch (IOException e) {
		System.err.println("IO error :"+e);
	}

	return yyl_return;
}


public void yyerror(String error) {
	System.err.println("Error: " + error);
}

public Words(Reader r) {
	lexer = new Yylex(r, this);
}


static Game game;
static AST root;

public static void main(String args[]) throws IOException {
	System.out.println("Welcome to Words!");

	WordsUI ui = new WordsUI();
	game = new Game(ui);
	game.start();

	Words yyparser;

	// interactive mode
	System.out.println("[Quit with CTRL_D]");
	yyparser = new Words(new InputStreamReader(System.in));
	//yyparser.yydebug = true;
	yyparser.yyparse();

	System.out.println();
	System.out.println();
	root.dump(0);
}
