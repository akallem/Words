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
%token EXIT
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
%token RESET
%token RIGHT
%token SAY
%token SAYS
%token STOP
%token THEN
%token TIMES
%token TOUCHES
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
%type <obj> object_create_statement
%type <obj> object_destroy_statement
%type <obj> property_assign_statement
%type <obj> iteration_statement
%type <obj> conditional_statement
%type <obj> listener_statement
%type <obj> runtime_control_statement
%type <obj> queueing_statement
%type <obj> queueing_custom_action_statement
%type <obj> predicate
%type <obj> basic_action_predicate
%type <obj> boolean_predicate
%type <obj> relational_expression
%type <obj> value_expression
%type <obj> reference_list
%type <obj> identifier_list
%type <obj> parameter_list
%type <obj> queue_assign_property_list
%type <obj> reference
%type <obj> identifier
%type <obj> parameter
%type <obj> subject
%type <obj> alias
%type <obj> queue_assign_property
%type <obj> direction
%type <obj> now
%type <obj> position
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
		statement					{ $$ = new INode(AST.ASTType.STATEMENT_LIST, lexer.lineNumber, $1); }
	|	statement statement_list	{ $$ = new INode(AST.ASTType.STATEMENT_LIST, lexer.lineNumber, $1); ((INode) $$).add(((INode) $2).children); }

statement:
		immediate_statement			{ $$ = $1; }
	|	queueing_statement			{ $$ = $1; }
	|	error { hasError = true; yyerror("Line " + lexer.lineNumber + " near '" + lexer.yytext() + "'"); } '.' { yyerrflag = 0; }

immediate_statement:
		class_create_statement		{ $$ = $1; }
	|	object_create_statement		{ $$ = $1; }
	|	object_destroy_statement	{ $$ = $1; }
	|	property_assign_statement	{ $$ = $1; }
	|	iteration_statement			{ $$ = $1; }
	|	conditional_statement		{ $$ = $1; }
	|	listener_statement			{ $$ = $1; }
	|	runtime_control_statement	{ $$ = $1; }

class_create_statement:
		A identifier IS A identifier '.'											{ $$ = new INode(AST.ASTType.CREATE_CLASS, lexer.lineNumber, $2, $5, null); }
	|	A identifier IS A identifier WHICH '{' class_definition_statement_list '}'	{ $$ = new INode(AST.ASTType.CREATE_CLASS, lexer.lineNumber, $2, $5, $8); }
	;

class_definition_statement_list:
		class_definition_statement													{ $$ = new INode(AST.ASTType.CLASS_STATEMENT_LIST, lexer.lineNumber, $1); }
	|	class_definition_statement class_definition_statement_list					{ $$ = new INode(AST.ASTType.CLASS_STATEMENT_LIST, lexer.lineNumber, $1); ((INode) $$).add(((INode) $2).children); }
	;

class_definition_statement:
		HAS A identifier '.'														{ $$ = new INode(AST.ASTType.DEFINE_PROPERTY, lexer.lineNumber, $3, null); }
	|	HAS A identifier OF literal '.'												{ $$ = new INode(AST.ASTType.DEFINE_PROPERTY, lexer.lineNumber, $3, $5); }
	|	CAN identifier WHICH MEANS '{' statement_list '}'							{ $$ = new INode(AST.ASTType.DEFINE_ACTION, lexer.lineNumber, $2, null, $6); }
	|	CAN identifier WITH identifier_list WHICH MEANS '{' statement_list '}'		{ $$ = new INode(AST.ASTType.DEFINE_ACTION, lexer.lineNumber, $2, $4, $8); }
	;

object_create_statement:
		identifier IS A identifier AT position '.'									{ $$ = new INode(AST.ASTType.CREATE_OBJ, lexer.lineNumber, $1, $4, null, $6); }
	|	identifier IS A identifier WITH parameter_list AT position '.'				{ $$ = new INode(AST.ASTType.CREATE_OBJ, lexer.lineNumber, $1, $4, $6, $8); }
	;

object_destroy_statement:
		REMOVE reference_list identifier '.'										{ $$ = new INode(AST.ASTType.REMOVE, lexer.lineNumber, $2, $3); }
	;

property_assign_statement:
		reference reference_list identifier IS value_expression '.'					{ $$ = new INode(AST.ASTType.ASSIGN, lexer.lineNumber, (new INode(AST.ASTType.REFERENCE_LIST, lexer.lineNumber, $1)).add(((INode) $2).children), $3, $5); }
	;

iteration_statement:
		REPEAT value_expression TIMES '{' statement_list '}'						{ $$ = new INode(AST.ASTType.REPEAT, lexer.lineNumber, $2, $5); }
	|	WHILE boolean_predicate '{' statement_list '}'								{ $$ = new INode(AST.ASTType.WHILE, lexer.lineNumber, $2, $4); }
	;

conditional_statement:
		IF boolean_predicate THEN '{' statement_list '}'							{ $$ = new INode(AST.ASTType.IF, lexer.lineNumber, $2, $5); }
	;

listener_statement:
		WHENEVER predicate '{' statement_list '}'									{ $$ = new INode(AST.ASTType.LISTENER_PERM, lexer.lineNumber, $2, $4); }
	|	AS LONG AS predicate '{' statement_list '}'									{ $$ = new INode(AST.ASTType.LISTENER_TEMP, lexer.lineNumber, $4, $6); }
	;

runtime_control_statement:
		RESET '.'																	{ $$ = new INode(AST.ASTType.RESET, lexer.lineNumber); }
	|	EXIT '.'																	{ $$ = new INode(AST.ASTType.EXIT, lexer.lineNumber); }
	;

queueing_statement:
		MAKE reference_list queue_assign_property_list '.'							{ $$ = new INode(AST.ASTType.QUEUE_ASSIGN, lexer.lineNumber, $2, $3, null); }
	|	MAKE reference_list queue_assign_property_list now '.'						{ $$ = new INode(AST.ASTType.QUEUE_ASSIGN, lexer.lineNumber, $2, $3, $4); }
	|	MAKE reference_list identifier MOVE direction '.'							{ $$ = new INode(AST.ASTType.QUEUE_MOVE, lexer.lineNumber, $2, $3, $5, null, null); }
	|	MAKE reference_list identifier MOVE direction now '.'						{ $$ = new INode(AST.ASTType.QUEUE_MOVE, lexer.lineNumber, $2, $3, $5, null, $6); }
	|	MAKE reference_list identifier MOVE direction value_expression '.'			{ $$ = new INode(AST.ASTType.QUEUE_MOVE, lexer.lineNumber, $2, $3, $5, $6, null); }
	|	MAKE reference_list identifier MOVE direction value_expression now '.'		{ $$ = new INode(AST.ASTType.QUEUE_MOVE, lexer.lineNumber, $2, $3, $5, $6, $7); }
	|	MAKE reference_list identifier SAY value_expression '.'						{ $$ = new INode(AST.ASTType.QUEUE_SAY, lexer.lineNumber, $2, $3, $5, null); }
	|	MAKE reference_list identifier SAY value_expression now '.'					{ $$ = new INode(AST.ASTType.QUEUE_SAY, lexer.lineNumber, $2, $3, $5, $6); }
	|	MAKE reference_list identifier WAIT value_expression TURNS '.'				{ $$ = new INode(AST.ASTType.QUEUE_WAIT, lexer.lineNumber, $2, $3, $5, null); }
	|	MAKE reference_list identifier WAIT value_expression TURNS now '.'			{ $$ = new INode(AST.ASTType.QUEUE_WAIT, lexer.lineNumber, $2, $3, $5, $7); }
	|	STOP reference_list identifier '.'											{ $$ = new INode(AST.ASTType.QUEUE_STOP, lexer.lineNumber, $2, $3); }
	|	queueing_custom_action_statement											{ $$ = $1; }
	;

queueing_custom_action_statement:
		MAKE reference_list identifier identifier '.'								{ $$ = new INode(AST.ASTType.QUEUE_ACTION, lexer.lineNumber, $2, $3, $4, null, null); }
	|	MAKE reference_list identifier identifier now '.'							{ $$ = new INode(AST.ASTType.QUEUE_ACTION, lexer.lineNumber, $2, $3, $4, null, $5); }
	|	MAKE reference_list identifier identifier WITH parameter_list '.'			{ $$ = new INode(AST.ASTType.QUEUE_ACTION, lexer.lineNumber, $2, $3, $4, $6, null); }
	|	MAKE reference_list identifier identifier WITH parameter_list now '.'		{ $$ = new INode(AST.ASTType.QUEUE_ACTION, lexer.lineNumber, $2, $3, $4, $6, $7); }
	;

predicate:
		basic_action_predicate														{ $$ = $1; }
	|	boolean_predicate															{ $$ = $1; }
	;

basic_action_predicate:
		subject alias MOVES															{ $$ = new INode(AST.ASTType.MOVES_PREDICATE, lexer.lineNumber, $1, $2, null); }
	|	subject alias MOVES direction												{ $$ = new INode(AST.ASTType.MOVES_PREDICATE, lexer.lineNumber, $1, $2, $4); }
	|	subject alias SAYS value_expression											{ $$ = new INode(AST.ASTType.SAYS_PREDICATE, lexer.lineNumber, $1, $2, $4); }
	|	subject alias WAITS															{ $$ = new INode(AST.ASTType.WAITS_PREDICATE, lexer.lineNumber, $1, $2); }
	|	subject alias TOUCHES subject alias											{ $$ = new INode(AST.ASTType.TOUCHES_PREDICATE, lexer.lineNumber, $1, $2, $4, $5); }
	;

boolean_predicate:
		relational_expression						{ $$ = $1; }
	|	'(' boolean_predicate ')'					{ $$ = $2; }
	|	NOT '(' boolean_predicate ')'				{ $$ = new INode(AST.ASTType.NOT, lexer.lineNumber, $3); }
	|	boolean_predicate AND boolean_predicate		{ $$ = new INode(AST.ASTType.AND, lexer.lineNumber, $1, $3); }
	|	boolean_predicate OR boolean_predicate		{ $$ = new INode(AST.ASTType.OR, lexer.lineNumber, $1, $3); }
	;

relational_expression:
		value_expression '=' value_expression		{ $$ = new INode(AST.ASTType.EQUALS, lexer.lineNumber, $1, $3); }
	|	value_expression '<' value_expression		{ $$ = new INode(AST.ASTType.LESS, lexer.lineNumber, $1, $3); }
	|	value_expression '>' value_expression		{ $$ = new INode(AST.ASTType.GREATER, lexer.lineNumber, $1, $3); }
	|	value_expression LEQ value_expression		{ $$ = new INode(AST.ASTType.LEQ, lexer.lineNumber, $1, $3); }
	|	value_expression GEQ value_expression		{ $$ = new INode(AST.ASTType.GEQ, lexer.lineNumber, $1, $3); }
	;

value_expression:
		reference_list identifier					{ $$ = new INode(AST.ASTType.RETRIEVE_PROPERTY, lexer.lineNumber, $1, $2); }
	|	literal										{ $$ = $1; }
	|	NOTHING										{ $$ = new LNode(AST.ASTType.NOTHING, lexer.lineNumber); }
	|	'(' value_expression ')'					{ $$ = $2; }
	|	'-' value_expression %prec UMINUS			{ $$ = new INode(AST.ASTType.NEGATE, lexer.lineNumber, $2); }
	|	value_expression '+' value_expression		{ $$ = new INode(AST.ASTType.ADD, lexer.lineNumber, $1, $3); }
	|	value_expression '-' value_expression		{ $$ = new INode(AST.ASTType.SUBTRACT, lexer.lineNumber, $1, $3); }
	|	value_expression '*' value_expression		{ $$ = new INode(AST.ASTType.MULTIPLY, lexer.lineNumber, $1, $3); }
	|	value_expression '/' value_expression		{ $$ = new INode(AST.ASTType.DIVIDE, lexer.lineNumber, $1, $3); }
	|	value_expression '^' value_expression		{ $$ = new INode(AST.ASTType.EXPONENTIATE, lexer.lineNumber, $1, $3); }
	;

reference_list:
													{ $$ = new INode(AST.ASTType.REFERENCE_LIST, lexer.lineNumber); }
	|	reference reference_list					{ $$ = new INode(AST.ASTType.REFERENCE_LIST, lexer.lineNumber, $1); ((INode) $$).add(((INode) $2).children); }
	;

identifier_list:
		identifier									{ $$ = new INode(AST.ASTType.IDENTIFIER_LIST, lexer.lineNumber, $1); }
	|	identifier ',' identifier_list				{ $$ = new INode(AST.ASTType.IDENTIFIER_LIST, lexer.lineNumber, $1); ((INode) $$).add(((INode) $3).children); }
	;

parameter_list:
		parameter									{ $$ = new INode(AST.ASTType.PARAMETER_LIST, lexer.lineNumber, $1); }
	|	parameter ',' parameter_list				{ $$ = new INode(AST.ASTType.PARAMETER_LIST, lexer.lineNumber, $1); ((INode) $$).add(((INode) $3).children); }
	;

queue_assign_property_list:
		queue_assign_property										{ $$ = new INode(AST.ASTType.QUEUE_ASSIGN_PROPERTY_LIST, lexer.lineNumber, $1); }
	|	queue_assign_property ',' queue_assign_property_list		{ $$ = new INode(AST.ASTType.QUEUE_ASSIGN_PROPERTY_LIST, lexer.lineNumber, $1); ((INode) $$).add(((INode) $3).children); }
	;


reference:
		REFERENCE									{ $$ = new LNode(AST.ASTType.REFERENCE, lexer.lineNumber, $1); }
	;

identifier:
		IDENTIFIER									{ $$ = new LNode(AST.ASTType.IDENTIFIER, lexer.lineNumber, $1); }
	;

parameter:
		identifier value_expression					{ $$ = new INode(AST.ASTType.PARAMETER, lexer.lineNumber, $1, $2); }
	;

subject:
		reference_list identifier					{ $$ = new INode(AST.ASTType.SUBJECT, lexer.lineNumber, null, $1, $2); }
	|	A identifier								{ $$ = new INode(AST.ASTType.SUBJECT, lexer.lineNumber, $2, null, null); }
	;

alias:
													{ $$ = new INode(AST.ASTType.ALIAS, lexer.lineNumber); }
	|	'[' identifier ']'							{ $$ = new INode(AST.ASTType.ALIAS, lexer.lineNumber, $2); }
	;

queue_assign_property:
		identifier BE value_expression				{ $$ = new INode(AST.ASTType.QUEUE_ASSIGN_PROPERTY, lexer.lineNumber, $1, $3); }
	;

direction:
		ANYWHERE									{ $$ = new LNode(AST.ASTType.DIRECTION, lexer.lineNumber, Direction.Type.ANYWHERE); }
	|	DOWN										{ $$ = new LNode(AST.ASTType.DIRECTION, lexer.lineNumber, Direction.Type.DOWN); }
	|	LEFT										{ $$ = new LNode(AST.ASTType.DIRECTION, lexer.lineNumber, Direction.Type.LEFT); }
	|	RIGHT										{ $$ = new LNode(AST.ASTType.DIRECTION, lexer.lineNumber, Direction.Type.RIGHT); }
	|	UP											{ $$ = new LNode(AST.ASTType.DIRECTION, lexer.lineNumber, Direction.Type.UP); }
	;

now:
		NOW											{ $$ = new LNode(AST.ASTType.NOW, lexer.lineNumber); }
	;

position:
		value_expression ',' value_expression		{ $$ = new INode(AST.ASTType.POSITION, lexer.lineNumber, $1, $3); }
	;

literal:
		NUM											{ $$ = new LNode(AST.ASTType.NUM, lexer.lineNumber, $1); }
	|	STRING										{ $$ = new LNode(AST.ASTType.STRING, lexer.lineNumber, $1); }
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
	if (!hideErrors)
		System.err.println("Error: " + error);
}

public int getDepth() {
	return lexer.depth;
}

public Words(Reader r) {
	lexer = new Yylex(r, this);
}


public static FrameLoop frameLoop;
public AST root;
public boolean hideErrors = false;
public boolean hasError = false;

public static void main(String args[]) throws IOException {
	System.out.println("Welcome to Words!");

	WordsUI ui = null;
	// Handle GUI option
	for (int i = 0; i < args.length; ++i) {
		if (args[i].equals("-nogui")) {
			System.out.println("GUI turned off");
			Option.GUI = false;
			Option.TIME_TO_WAIT = 100;
		}
	}
	if (Option.GUI)
		ui = new WordsUI();

	frameLoop = new FrameLoop(ui);
	frameLoop.start();

	// Read and parse program argument, if any
	for (int i = 0; i < args.length; ++i) {
		if (args[i].charAt(0) != '-') {
			// Read in Words program from file
			String filename = args[i];
			try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
				Words parser = new Words(br);
				parser.yyparse();

				// Temporary: dump AST to console.  (TODO: Enqueue AST for evaluation by frame loop thread.)
				System.err.println();
				System.err.println();
				if (parser.root != null)
					frameLoop.enqueueAST(parser.root);
				else
					System.err.println("Failed to generate AST");

				br.close();
			} catch (IOException e) {
				System.err.println("Unable to read file " + filename);
			}
			break;
		}
	}

	// If no GUI, then no REPL
	if (!Option.GUI)
		return;

	// Simple REPL interface
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	while (true) {
		String fragment = "";
		int depth = 0;

		while (true) {
			// Prompt user
			if (depth > 0)
				System.out.printf("... ");
			else
				System.out.printf("> ");

			// Read next line and exit on EOF
			String line = br.readLine();

			if (line == null)
				System.exit(0);

			fragment = fragment + line;

			// Attempt to parse the fragment
			Words tester = new Words(new StringReader(fragment));
			tester.hideErrors = true;
			tester.yyparse();

			depth = tester.getDepth();

			// If the depth is greater than 0, we have to keep reading lines to get a fragment that is at least potentially complete
			if (depth == 0)
				break;
		}

		Words parser = new Words(new StringReader(fragment));
		parser.yyparse();

		// Temporary: dump AST to console.  (TODO: Enqueue AST for evaluation by frame loop thread.)
		// In REPL interface, we might want to evaluate only ASTs that had no syntax errors
		System.err.println();
		System.err.println();
		if (parser.root != null)
			frameLoop.enqueueAST(parser.root);
		else
			System.err.println("Failed to generate AST");
	}
}
