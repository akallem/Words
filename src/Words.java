//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 15 "Words.y"
  import java.io.*;
//#line 19 "Words.java"




public class Words
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class WordsVal is defined in WordsVal.java


String   yytext;//user variable to return contextual strings
WordsVal yyval; //used to return semantic vals from action routines
WordsVal yylval;//the 'lval' (result) I got from yylex()
WordsVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new WordsVal[YYSTACKSIZE];
  yyval=new WordsVal();
  yylval=new WordsVal();
  valptr=-1;
}
void val_push(WordsVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
WordsVal val_pop()
{
  if (valptr<0)
    return new WordsVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
WordsVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new WordsVal();
  return valstk[ptr];
}
final WordsVal dup_yyval(WordsVal val)
{
  WordsVal dup = new WordsVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short A=257;
public final static short AND=258;
public final static short ANYWHERE=259;
public final static short AS=260;
public final static short AT=261;
public final static short BE=262;
public final static short CAN=263;
public final static short DOWN=264;
public final static short HAS=265;
public final static short IF=266;
public final static short IS=267;
public final static short LEFT=268;
public final static short LONG=269;
public final static short MAKE=270;
public final static short MOVE=271;
public final static short MOVES=272;
public final static short MEANS=273;
public final static short NOT=274;
public final static short NOTHING=275;
public final static short NOW=276;
public final static short OF=277;
public final static short OR=278;
public final static short REMOVE=279;
public final static short REPEAT=280;
public final static short RIGHT=281;
public final static short SAY=282;
public final static short SAYS=283;
public final static short STOP=284;
public final static short THEN=285;
public final static short TIMES=286;
public final static short TURNS=287;
public final static short UP=288;
public final static short WAIT=289;
public final static short WAITS=290;
public final static short WHENEVER=291;
public final static short WHICH=292;
public final static short WHILE=293;
public final static short WITH=294;
public final static short IDENTIFIER=295;
public final static short REFERENCE=296;
public final static short NUM=297;
public final static short STRING=298;
public final static short GEQ=299;
public final static short LEQ=300;
public final static short UMINUS=301;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,   31,    2,    3,    3,    3,
    3,    3,    3,    3,    4,    4,    5,    5,    6,    6,
    7,    7,    8,    8,    9,    9,   10,   11,   11,   12,
   12,   13,   14,   14,   15,   15,   15,   15,   15,   15,
   15,   15,   15,   15,   15,   15,   16,   16,   16,   16,
   17,   17,   18,   18,   18,   18,   19,   19,   19,   19,
   19,   20,   20,   20,   20,   20,   21,   21,   21,   21,
   21,   21,   21,   21,   21,   21,   22,   22,   23,   23,
   23,   23,   23,   24,   25,   25,   26,   26,   27,   28,
   29,   30,   30,
};
final static short yylen[] = {                            2,
    1,    1,    2,    1,    1,    0,    3,    1,    1,    1,
    1,    1,    1,    1,    6,    9,    1,    2,    1,    1,
    4,    6,    7,    9,    7,    9,    4,    4,    6,    6,
    5,    6,    5,    7,    6,    7,    6,    7,    7,    8,
    6,    7,    7,    8,    4,    1,    5,    6,    7,    8,
    1,    1,    3,    4,    4,    3,    1,    3,    4,    3,
    3,    3,    3,    3,    3,    3,    2,    1,    1,    3,
    2,    3,    3,    3,    3,    3,    0,    2,    1,    1,
    1,    1,    1,    3,    1,    3,    1,    3,    2,    1,
    1,    1,    1,
};
final static short yydefred[] = {                         0,
    6,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   90,   91,    0,    1,    0,    4,    8,    9,   10,   11,
   12,   13,   14,    5,   46,    0,    0,    0,    0,    0,
    0,   69,   92,   93,    0,    0,    0,   57,    0,    0,
    0,   68,    0,    0,    0,    0,    0,    0,   51,    0,
    0,    0,    3,    0,    0,    7,    0,    0,    0,   71,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   67,   78,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   58,   70,    0,   61,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   27,    0,   45,    0,    0,    0,   56,    0,    0,
   28,    0,    0,    0,   59,    0,    0,   79,   80,   81,
   82,   83,    0,    0,    0,    0,    0,   47,    0,   33,
   54,    0,   31,    0,    0,    0,    0,   15,    0,   32,
    0,   35,    0,   37,    0,    0,   41,    0,   48,    0,
    0,    0,   30,    0,    0,    0,   29,    0,   34,   36,
   38,    0,   39,   42,    0,   43,    0,   49,    0,    0,
    0,   25,    0,    0,    0,    0,    0,   19,   20,   40,
   44,   50,   88,    0,    0,    0,    0,   16,   18,   26,
    0,    0,    0,    0,    0,    0,    0,   21,    0,    0,
    0,    0,    0,    0,   86,   22,   23,    0,    0,   24,
};
final static short yydgoto[] = {                         13,
   14,   15,   16,   17,  186,  187,  188,  189,   18,   19,
   20,   21,   22,   23,   24,   25,   48,   49,   50,   38,
   39,   40,  133,  165,  205,  160,  161,   26,   41,   42,
   28,
};
final static short yysindex[] = {                       219,
    0, -278, -241,   78, -256, -256,   86, -256,   78,   78,
    0,    0,    0,    0,  219,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -209, -256,   22, -198, -190,
   35,    0,    0,    0,   86,   78,  -71,    0,   12, -278,
 -256,    0, -278, -278,   86,   82, -278,  -32,    0, -211,
 -278, -107,    0,  -27, -278,    0, -160,   78,   78,    0,
  -29,  -18,   78,   78,   -4,   86,   86,   86,   86,   86,
   86,   86,   86,   86,   86,    0,    0,  -94,   88,  360,
   17,   91,  219, -140,  219, -278,  369, -109, -278,   43,
  -26,    0,    0, -105,    0,  219,  422,  422,   99,   99,
   75,   75,   75,  422,  422,  422,   86,  -89,   86,   86,
  -38,    0,  219,    0,   57,  -89,   86,    0,   61, -216,
    0,   86,  -39,  219,    0,   66,  102,    0,    0,    0,
    0,    0,   68,  110,   41,  128, -278,    0,   69,    0,
    0,  422,    0,   86, -278,  378,   74,    0,   73,    0,
  154,    0,  157,    0,  118,  159,    0,   -8,    0,    2,
  162,   86,    0,  414,  163,  -53,    0, -224,    0,    0,
    0,  164,    0,    0,  165,    0,  172,    0, -278,  422,
   86,    0,   86, -278,  -34,   94, -224,    0,    0,    0,
    0,    0,    0,  422,  176, -205, -278,    0,    0,    0,
  -52, -278,  -20,  101,  -65,  187, -245,    0,  219,  -37,
 -278,  188,  117,  120,    0,    0,    0,  219,  129,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,  -44,  -44,  -44,  -44,  -44,  -44,  -44,
    0,    0,    0,    0,   14,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -44,    0,    0,    0,
    0,    0,    0,    0,  -44,  -44,    0,    0,    0,    0,
  -44,    0,    0,    0,  -44,    0,    0,    0,    0,  132,
    0,    0,    0,  -44,    0,    0,    0,  -44,  -44,    0,
    0,    0,  -44,  -44,    0,  -44,  -44,  -44,  -44,  -44,
  -44,  -44,  -44,  -44,  -44,    0,    0,    0,    0,    0,
    0,    0,    0,   60,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -19,    0,    0,    5,   15,   49,   55,
  -41,  -11,   19,   36,   44,   92,  -44,    0,  -44,  -44,
    0,    0,    0,    0,    0,  137,  -44,    0,    0,    0,
    0,  -44,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -44,    0,    0,    0,    0,    0,    0,    0,
    0,  138,    0,  -44,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -36,  -44,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -35,
  -44,    0,  -44,    0,    0,    0,  139,    0,    0,    0,
    0,    0,    0,  216,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -13,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  284,    0,    0,    0,   85,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  226,    0,  126,    0,
  374,  175,  169,  103,   76, -108,    0,  349,  213,   84,
    0,
};
final static int YYTABLESIZE=560;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         74,
   74,   74,   74,   74,   74,   74,  148,  138,   89,   87,
   89,   92,   45,    2,  125,   85,   11,   35,   74,   74,
   74,   60,   93,   70,   69,  208,   68,   30,   71,   75,
   75,   75,   75,   75,   75,   75,  166,  176,  184,   12,
  185,   74,   73,   75,  144,   66,   63,  178,   75,   75,
   75,   33,   34,   70,   69,   65,   68,   54,   71,   76,
   76,   76,   76,   76,   76,   76,   64,   56,   57,   58,
  193,   74,   73,   75,   59,   72,   62,  145,   76,   76,
   76,   74,   70,   69,   63,   68,  201,   71,  202,   73,
   83,   73,   73,   73,   73,   72,   89,   72,   72,   72,
   72,   67,   67,   60,   67,   72,   67,   45,   73,   73,
   73,   75,   35,  154,   72,   72,   72,   36,   96,   67,
   67,   67,   35,   70,   69,   45,   68,   66,   71,   37,
   35,  116,   64,  112,   72,   52,  114,   65,    2,  113,
   70,   76,  117,   70,   69,   71,   68,  152,   71,  118,
   63,   70,   69,   67,   68,  157,   71,  122,   62,   70,
   69,   61,   68,  173,   71,  124,   63,  107,   72,  128,
   64,   73,   64,  159,  129,   72,  108,   72,  130,   43,
   44,  140,   47,   51,   91,  143,   63,  109,   94,   95,
  150,  131,   72,  163,  110,   72,  168,  169,  132,  170,
   11,   55,  171,   72,  174,  179,   64,  183,  182,  190,
  191,   72,   27,   65,   64,   77,   74,  192,  198,   74,
  204,  200,  197,  209,   87,   89,  210,   27,   63,   86,
  211,   63,   51,  216,   74,  214,   74,  136,   60,   87,
   89,  217,  218,   74,   74,   74,   75,   32,   64,   75,
   77,   64,  147,  220,   52,  137,  207,   74,   74,   53,
   55,   84,   66,   17,   75,   60,   75,  175,   12,   33,
   34,  199,   65,   75,   75,   75,   76,  177,   85,   76,
   66,   67,   66,   90,  141,  195,  215,   75,   75,   66,
  212,    0,   65,   62,   76,   27,   76,   27,   53,   65,
    0,   63,    0,   76,   76,   76,   73,    0,   27,   73,
   66,   67,   72,   62,    0,   72,    0,   76,   76,    0,
   62,   63,    0,    0,   73,   27,   73,  158,   63,    0,
   72,    0,   72,   73,   73,   73,   27,    0,    0,   72,
   72,   72,   32,  153,    0,    0,    0,   73,   73,   64,
   29,   31,   32,   72,   72,    0,    0,    0,   67,   67,
   32,    0,    0,   12,   33,   34,  115,   81,  119,   64,
    0,    0,    0,   12,   33,   34,   64,  151,    0,  126,
   46,   12,   33,   34,    0,  156,    0,    0,   76,    0,
    0,   78,   79,  172,    0,   82,  139,    0,    0,   84,
   93,   70,   69,   88,   68,    0,   71,  149,   60,   62,
   70,   69,    0,   68,  121,   71,    0,    0,   80,   70,
   69,   27,   68,  167,   71,    0,  111,   87,    0,    0,
   27,    0,    0,    0,  120,    0,    0,  123,    0,   97,
   98,   99,  100,  101,  102,  103,  104,  105,  106,    0,
    0,    0,    0,   72,    0,   70,   69,  181,   68,    0,
   71,    0,   72,   70,   69,    0,   68,    0,   71,    0,
    0,   72,    0,    0,    1,    2,    0,    0,    3,    0,
  127,    0,  134,  135,    4,  162,    0,    0,    5,    0,
  142,    0,  213,  162,    0,  146,    0,    6,    7,    0,
    0,  219,    8,    0,    0,    0,  155,   72,    0,    9,
    0,   10,    0,   11,   12,   72,    0,  164,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  162,    0,    0,
    0,    0,  196,    0,    0,  180,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  203,    0,    0,    0,    0,
  206,    0,    0,    0,  194,    0,  164,    0,    0,  206,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   42,   43,   44,   45,   46,   47,   46,   46,   44,   46,
   46,   41,   40,    0,   41,  123,  295,   45,   60,   61,
   62,   41,   41,   42,   43,   46,   45,  269,   47,   41,
   42,   43,   44,   45,   46,   47,  145,   46,  263,  296,
  265,   60,   61,   62,  261,   41,  258,   46,   60,   61,
   62,  297,  298,   42,   43,   41,   45,  267,   47,   41,
   42,   43,   44,   45,   46,   47,  278,   46,  267,  260,
  179,   60,   61,   62,   40,   94,   41,  294,   60,   61,
   62,  123,   42,   43,   41,   45,  292,   47,  294,   41,
  123,   43,   44,   45,   46,   41,  257,   43,   44,   45,
   46,   42,   43,  123,   45,   94,   47,   40,   60,   61,
   62,  123,   45,   46,   60,   61,   62,   40,  123,   60,
   61,   62,   45,   42,   43,   40,   45,  123,   47,    4,
   45,  272,   41,   46,   94,   10,   46,  123,  125,  123,
   42,  123,  283,   42,   43,   47,   45,   46,   47,  290,
  258,   42,   43,   94,   45,   46,   47,  267,  123,   42,
   43,   36,   45,   46,   47,  123,  123,  262,   94,  259,
  278,  123,  278,   46,  264,   94,  271,  123,  268,    5,
    6,  125,    8,    9,   59,  125,  258,  282,   63,   64,
  125,  281,   94,  125,  289,   94,  123,  125,  288,   46,
  295,   27,   46,   94,   46,   44,  278,  261,   46,   46,
   46,   94,    0,  285,  123,   41,  258,   46,  125,  261,
  273,   46,  257,  123,  261,  261,  292,   15,  258,  257,
   44,  258,   58,   46,  276,  273,  278,  276,  258,  276,
  276,  125,  123,  285,  286,  287,  258,  275,  278,  261,
  295,  278,  292,  125,  123,  294,  277,  299,  300,  123,
  123,   46,  258,  125,  276,  285,  278,  276,  296,  297,
  298,  187,  258,  285,  286,  287,  258,  276,  292,  261,
  299,  300,  278,   58,  116,  183,  211,  299,  300,  285,
  207,   -1,  278,  258,  276,   83,  278,   85,   15,  285,
   -1,  258,   -1,  285,  286,  287,  258,   -1,   96,  261,
  299,  300,  258,  278,   -1,  261,   -1,  299,  300,   -1,
  285,  278,   -1,   -1,  276,  113,  278,  287,  285,   -1,
  276,   -1,  278,  285,  286,  287,  124,   -1,   -1,  285,
  286,  287,  275,  276,   -1,   -1,   -1,  299,  300,  258,
    2,  274,  275,  299,  300,   -1,   -1,   -1,  299,  300,
  275,   -1,   -1,  296,  297,  298,   83,  286,   85,  278,
   -1,   -1,   -1,  296,  297,  298,  285,  276,   -1,   96,
    7,  296,  297,  298,   -1,  276,   -1,   -1,   40,   -1,
   -1,   43,   44,  276,   -1,   47,  113,   -1,   -1,   51,
   41,   42,   43,   55,   45,   -1,   47,  124,   35,   36,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   45,   42,
   43,  209,   45,   46,   47,   -1,   78,   54,   -1,   -1,
  218,   -1,   -1,   -1,   86,   -1,   -1,   89,   -1,   66,
   67,   68,   69,   70,   71,   72,   73,   74,   75,   -1,
   -1,   -1,   -1,   94,   -1,   42,   43,   44,   45,   -1,
   47,   -1,   94,   42,   43,   -1,   45,   -1,   47,   -1,
   -1,   94,   -1,   -1,  256,  257,   -1,   -1,  260,   -1,
  107,   -1,  109,  110,  266,  137,   -1,   -1,  270,   -1,
  117,   -1,  209,  145,   -1,  122,   -1,  279,  280,   -1,
   -1,  218,  284,   -1,   -1,   -1,  133,   94,   -1,  291,
   -1,  293,   -1,  295,  296,   94,   -1,  144,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  179,   -1,   -1,
   -1,   -1,  184,   -1,   -1,  162,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  197,   -1,   -1,   -1,   -1,
  202,   -1,   -1,   -1,  181,   -1,  183,   -1,   -1,  211,
};
}
final static short YYFINAL=13;
final static short YYMAXTOKEN=301;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,null,
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'^'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"A","AND","ANYWHERE","AS","AT","BE",
"CAN","DOWN","HAS","IF","IS","LEFT","LONG","MAKE","MOVE","MOVES","MEANS","NOT",
"NOTHING","NOW","OF","OR","REMOVE","REPEAT","RIGHT","SAY","SAYS","STOP","THEN",
"TIMES","TURNS","UP","WAIT","WAITS","WHENEVER","WHICH","WHILE","WITH",
"IDENTIFIER","REFERENCE","NUM","STRING","GEQ","LEQ","UMINUS",
};
final static String yyrule[] = {
"$accept : program",
"program : statement_list",
"statement_list : statement",
"statement_list : statement statement_list",
"statement : immediate_statement",
"statement : queueing_statement",
"$$1 :",
"statement : error $$1 '.'",
"immediate_statement : class_create_statement",
"immediate_statement : object_create_statement",
"immediate_statement : object_destroy_statement",
"immediate_statement : property_assign_statement",
"immediate_statement : iteration_statement",
"immediate_statement : conditional_statement",
"immediate_statement : listener_statement",
"class_create_statement : A identifier IS A identifier '.'",
"class_create_statement : A identifier IS A identifier WHICH '{' class_definition_statement_list '}'",
"class_definition_statement_list : class_definition_statement",
"class_definition_statement_list : class_definition_statement class_definition_statement_list",
"class_definition_statement : class_property_definition_statement",
"class_definition_statement : class_custom_action_definition_statement",
"class_property_definition_statement : HAS A identifier '.'",
"class_property_definition_statement : HAS A identifier OF literal '.'",
"class_custom_action_definition_statement : CAN identifier WHICH MEANS '{' statement_list '}'",
"class_custom_action_definition_statement : CAN identifier WITH identifier_list WHICH MEANS '{' statement_list '}'",
"object_create_statement : identifier IS A identifier AT position '.'",
"object_create_statement : identifier IS A identifier WITH parameter_list AT position '.'",
"object_destroy_statement : REMOVE reference_list identifier '.'",
"property_assign_statement : identifier IS value_expression '.'",
"property_assign_statement : reference reference_list identifier IS value_expression '.'",
"iteration_statement : REPEAT value_expression TIMES '{' statement_list '}'",
"iteration_statement : WHILE boolean_predicate '{' statement_list '}'",
"conditional_statement : IF boolean_predicate THEN '{' statement_list '}'",
"listener_statement : WHENEVER predicate '{' statement_list '}'",
"listener_statement : AS LONG AS predicate '{' statement_list '}'",
"queueing_statement : MAKE reference_list identifier BE value_expression '.'",
"queueing_statement : MAKE reference_list identifier BE value_expression NOW '.'",
"queueing_statement : MAKE reference_list identifier MOVE direction '.'",
"queueing_statement : MAKE reference_list identifier MOVE direction NOW '.'",
"queueing_statement : MAKE reference_list identifier MOVE direction value_expression '.'",
"queueing_statement : MAKE reference_list identifier MOVE direction value_expression NOW '.'",
"queueing_statement : MAKE reference_list identifier SAY value_expression '.'",
"queueing_statement : MAKE reference_list identifier SAY value_expression NOW '.'",
"queueing_statement : MAKE reference_list identifier WAIT value_expression TURNS '.'",
"queueing_statement : MAKE reference_list identifier WAIT value_expression TURNS NOW '.'",
"queueing_statement : STOP reference_list identifier '.'",
"queueing_statement : queueing_custom_action_statement",
"queueing_custom_action_statement : MAKE reference_list identifier identifier '.'",
"queueing_custom_action_statement : MAKE reference_list identifier identifier NOW '.'",
"queueing_custom_action_statement : MAKE reference_list identifier identifier WITH parameter_list '.'",
"queueing_custom_action_statement : MAKE reference_list identifier identifier WITH parameter_list NOW '.'",
"predicate : basic_action_predicate",
"predicate : boolean_predicate",
"basic_action_predicate : reference_list identifier MOVES",
"basic_action_predicate : reference_list identifier MOVES direction",
"basic_action_predicate : reference_list identifier SAYS value_expression",
"basic_action_predicate : reference_list identifier WAITS",
"boolean_predicate : relational_expression",
"boolean_predicate : '(' boolean_predicate ')'",
"boolean_predicate : NOT '(' boolean_predicate ')'",
"boolean_predicate : boolean_predicate AND boolean_predicate",
"boolean_predicate : boolean_predicate OR boolean_predicate",
"relational_expression : value_expression '=' value_expression",
"relational_expression : value_expression '<' value_expression",
"relational_expression : value_expression '>' value_expression",
"relational_expression : value_expression LEQ value_expression",
"relational_expression : value_expression GEQ value_expression",
"value_expression : reference_list identifier",
"value_expression : literal",
"value_expression : NOTHING",
"value_expression : '(' value_expression ')'",
"value_expression : '-' value_expression",
"value_expression : value_expression '+' value_expression",
"value_expression : value_expression '-' value_expression",
"value_expression : value_expression '*' value_expression",
"value_expression : value_expression '/' value_expression",
"value_expression : value_expression '^' value_expression",
"reference_list :",
"reference_list : reference reference_list",
"direction : ANYWHERE",
"direction : DOWN",
"direction : LEFT",
"direction : RIGHT",
"direction : UP",
"position : value_expression ',' value_expression",
"identifier_list : identifier",
"identifier_list : identifier ',' identifier_list",
"parameter_list : parameter",
"parameter_list : parameter ',' parameter_list",
"parameter : identifier value_expression",
"identifier : IDENTIFIER",
"reference : REFERENCE",
"literal : NUM",
"literal : STRING",
};

//#line 297 "Words.y"


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
//#line 538 "Words.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 114 "Words.y"
{ yyval.obj = val_peek(0).obj; root = (AST) yyval.obj; }
break;
case 2:
//#line 118 "Words.y"
{ yyval.obj = new INode(AST.Type.STATEMENT_LIST, val_peek(0).obj); }
break;
case 3:
//#line 119 "Words.y"
{ yyval.obj = new INode(AST.Type.STATEMENT_LIST, val_peek(1).obj); ((INode) yyval.obj).add(((INode) val_peek(0).obj).children); }
break;
case 4:
//#line 122 "Words.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 5:
//#line 123 "Words.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 6:
//#line 124 "Words.y"
{ System.err.println("Syntax error on line " + lexer.lineNumber + " near '" + lexer.yytext() + "'"); }
break;
case 7:
//#line 124 "Words.y"
{ yyerrflag = 0; }
break;
case 8:
//#line 127 "Words.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 9:
//#line 128 "Words.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 10:
//#line 129 "Words.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 11:
//#line 130 "Words.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 12:
//#line 131 "Words.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 13:
//#line 132 "Words.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 14:
//#line 133 "Words.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 15:
//#line 136 "Words.y"
{ yyval.obj = new INode(AST.Type.CREATE_CLASS, val_peek(4).obj, val_peek(1).obj, null); }
break;
case 16:
//#line 137 "Words.y"
{ yyval.obj = new INode(AST.Type.CREATE_CLASS, val_peek(7).obj, val_peek(4).obj, val_peek(1).obj); }
break;
case 17:
//#line 141 "Words.y"
{ yyval.obj = new INode(AST.Type.CLASS_STATEMENT_LIST, val_peek(0).obj); }
break;
case 18:
//#line 142 "Words.y"
{ yyval.obj = new INode(AST.Type.CLASS_STATEMENT_LIST, val_peek(1).obj); ((INode) yyval.obj).add(((INode) val_peek(0).obj).children); }
break;
case 19:
//#line 146 "Words.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 20:
//#line 147 "Words.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 21:
//#line 151 "Words.y"
{ yyval.obj = new INode(AST.Type.DEFINE_PROPERTY, val_peek(1).obj, null); }
break;
case 22:
//#line 152 "Words.y"
{ yyval.obj = new INode(AST.Type.DEFINE_PROPERTY, val_peek(3).obj, val_peek(1).obj); }
break;
case 23:
//#line 156 "Words.y"
{ yyval.obj = new INode(AST.Type.DEFINE_ACTION, val_peek(5).obj, null, val_peek(1).obj); }
break;
case 24:
//#line 157 "Words.y"
{ yyval.obj = new INode(AST.Type.DEFINE_ACTION, val_peek(7).obj, val_peek(5).obj, val_peek(1).obj); }
break;
case 25:
//#line 161 "Words.y"
{ yyval.obj = new INode(AST.Type.CREATE_OBJ, val_peek(6).obj, val_peek(3).obj, null, val_peek(1).obj); }
break;
case 26:
//#line 162 "Words.y"
{ yyval.obj = new INode(AST.Type.CREATE_OBJ, val_peek(8).obj, val_peek(5).obj, val_peek(3).obj, val_peek(1).obj); }
break;
case 27:
//#line 166 "Words.y"
{ yyval.obj = new INode(AST.Type.REMOVE, val_peek(2).obj, val_peek(1).obj); }
break;
case 28:
//#line 170 "Words.y"
{ yyval.obj = new INode(AST.Type.ASSIGN, new INode(AST.Type.REFERENCE_LIST), val_peek(3).obj, val_peek(1).obj); }
break;
case 29:
//#line 171 "Words.y"
{ yyval.obj = new INode(AST.Type.ASSIGN, (new INode(AST.Type.REFERENCE_LIST, val_peek(5).obj)).add(((INode) val_peek(4).obj).children), val_peek(3).obj, val_peek(1).obj); }
break;
case 30:
//#line 175 "Words.y"
{ yyval.obj = new INode(AST.Type.REPEAT, val_peek(4).obj, val_peek(1).obj); }
break;
case 31:
//#line 176 "Words.y"
{ yyval.obj = new INode(AST.Type.WHILE, val_peek(3).obj, val_peek(1).obj); }
break;
case 32:
//#line 180 "Words.y"
{ yyval.obj = new INode(AST.Type.IF, val_peek(4).obj, val_peek(1).obj); }
break;
case 33:
//#line 184 "Words.y"
{ yyval.obj = new INode(AST.Type.LISTENER_PERM, val_peek(3).obj, val_peek(1).obj); }
break;
case 34:
//#line 185 "Words.y"
{ yyval.obj = new INode(AST.Type.LISTENER_TEMP, val_peek(3).obj, val_peek(1).obj); }
break;
case 35:
//#line 189 "Words.y"
{ yyval.obj = new INode(AST.Type.QUEUE_ASSIGN, val_peek(4).obj, val_peek(3).obj, val_peek(1).obj); }
break;
case 36:
//#line 190 "Words.y"
{ yyval.obj = new INode(AST.Type.QUEUE_ASSIGN_NOW, val_peek(5).obj, val_peek(4).obj, val_peek(2).obj); }
break;
case 37:
//#line 191 "Words.y"
{ yyval.obj = new INode(AST.Type.QUEUE_MOVE, val_peek(4).obj, val_peek(3).obj, val_peek(1).obj); }
break;
case 38:
//#line 192 "Words.y"
{ yyval.obj = new INode(AST.Type.QUEUE_MOVE_NOW, val_peek(5).obj, val_peek(4).obj, val_peek(2).obj); }
break;
case 39:
//#line 193 "Words.y"
{ yyval.obj = new INode(AST.Type.QUEUE_ASSIGN, val_peek(5).obj, val_peek(4).obj, val_peek(2).obj, val_peek(1).obj); }
break;
case 40:
//#line 194 "Words.y"
{ yyval.obj = new INode(AST.Type.QUEUE_MOVE_NOW, val_peek(6).obj, val_peek(5).obj, val_peek(3).obj, val_peek(2).obj); }
break;
case 41:
//#line 195 "Words.y"
{ yyval.obj = new INode(AST.Type.QUEUE_SAY, val_peek(4).obj, val_peek(3).obj, val_peek(1).obj); }
break;
case 42:
//#line 196 "Words.y"
{ yyval.obj = new INode(AST.Type.QUEUE_SAY_NOW, val_peek(5).obj, val_peek(4).obj, val_peek(2).obj); }
break;
case 43:
//#line 197 "Words.y"
{ yyval.obj = new INode(AST.Type.QUEUE_WAIT, val_peek(5).obj, val_peek(4).obj, val_peek(2).obj); }
break;
case 44:
//#line 198 "Words.y"
{ yyval.obj = new INode(AST.Type.QUEUE_WAIT_NOW, val_peek(6).obj, val_peek(5).obj, val_peek(3).obj); }
break;
case 45:
//#line 199 "Words.y"
{ yyval.obj = new INode(AST.Type.QUEUE_STOP, val_peek(2).obj, val_peek(1).obj); }
break;
case 46:
//#line 200 "Words.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 47:
//#line 204 "Words.y"
{ yyval.obj = new INode(AST.Type.QUEUE_ACTION, val_peek(3).obj, val_peek(2).obj, val_peek(1).obj); }
break;
case 48:
//#line 205 "Words.y"
{ yyval.obj = new INode(AST.Type.QUEUE_ACTION_NOW, val_peek(4).obj, val_peek(3).obj, val_peek(2).obj); }
break;
case 49:
//#line 206 "Words.y"
{ yyval.obj = new INode(AST.Type.QUEUE_ACTION, val_peek(5).obj, val_peek(4).obj, val_peek(3).obj, val_peek(1).obj); }
break;
case 50:
//#line 207 "Words.y"
{ yyval.obj = new INode(AST.Type.QUEUE_ACTION_NOW, val_peek(6).obj, val_peek(5).obj, val_peek(4).obj, val_peek(2).obj); }
break;
case 51:
//#line 211 "Words.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 52:
//#line 212 "Words.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 53:
//#line 216 "Words.y"
{ yyval.obj = new INode(AST.Type.MOVES_PREDICATE, val_peek(2).obj, val_peek(1).obj); }
break;
case 54:
//#line 217 "Words.y"
{ yyval.obj = new INode(AST.Type.MOVES_PREDICATE, val_peek(3).obj, val_peek(2).obj, val_peek(0).obj); }
break;
case 55:
//#line 218 "Words.y"
{ yyval.obj = new INode(AST.Type.SAYS_PREDICATE, val_peek(3).obj, val_peek(2).obj, val_peek(0).obj); }
break;
case 56:
//#line 219 "Words.y"
{ yyval.obj = new INode(AST.Type.WAITS_PREDICATE, val_peek(2).obj, val_peek(1).obj); }
break;
case 57:
//#line 223 "Words.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 58:
//#line 224 "Words.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 59:
//#line 225 "Words.y"
{ yyval.obj = new INode(AST.Type.NOT, val_peek(1).obj); }
break;
case 60:
//#line 226 "Words.y"
{ yyval.obj = new INode(AST.Type.AND, val_peek(2).obj, val_peek(0).obj); }
break;
case 61:
//#line 227 "Words.y"
{ yyval.obj = new INode(AST.Type.OR, val_peek(2).obj, val_peek(0).obj); }
break;
case 62:
//#line 231 "Words.y"
{ yyval.obj = new INode(AST.Type.EQUALS, val_peek(2).obj, val_peek(0).obj); }
break;
case 63:
//#line 232 "Words.y"
{ yyval.obj = new INode(AST.Type.LESS, val_peek(2).obj, val_peek(0).obj); }
break;
case 64:
//#line 233 "Words.y"
{ yyval.obj = new INode(AST.Type.GREATER, val_peek(2).obj, val_peek(0).obj); }
break;
case 65:
//#line 234 "Words.y"
{ yyval.obj = new INode(AST.Type.LEQ, val_peek(2).obj, val_peek(0).obj); }
break;
case 66:
//#line 235 "Words.y"
{ yyval.obj = new INode(AST.Type.GEQ, val_peek(2).obj, val_peek(0).obj); }
break;
case 67:
//#line 239 "Words.y"
{ yyval.obj = new INode(AST.Type.PROPERTY, val_peek(1).obj, val_peek(0).obj); }
break;
case 68:
//#line 240 "Words.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 69:
//#line 241 "Words.y"
{ yyval.obj = new LNode(AST.Type.NOTHING); }
break;
case 70:
//#line 242 "Words.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 71:
//#line 243 "Words.y"
{ yyval.obj = new INode(AST.Type.NEGATE, val_peek(0).obj); }
break;
case 72:
//#line 244 "Words.y"
{ yyval.obj = new INode(AST.Type.ADD, val_peek(2).obj, val_peek(0).obj); }
break;
case 73:
//#line 245 "Words.y"
{ yyval.obj = new INode(AST.Type.SUBTRACT, val_peek(2).obj, val_peek(0).obj); }
break;
case 74:
//#line 246 "Words.y"
{ yyval.obj = new INode(AST.Type.MULTIPLY, val_peek(2).obj, val_peek(0).obj); }
break;
case 75:
//#line 247 "Words.y"
{ yyval.obj = new INode(AST.Type.DIVIDE, val_peek(2).obj, val_peek(0).obj); }
break;
case 76:
//#line 248 "Words.y"
{ yyval.obj = new INode(AST.Type.EXPONENTIATE, val_peek(2).obj, val_peek(0).obj); }
break;
case 77:
//#line 252 "Words.y"
{ yyval.obj = new INode(AST.Type.REFERENCE_LIST); }
break;
case 78:
//#line 253 "Words.y"
{ yyval.obj = new INode(AST.Type.REFERENCE_LIST, val_peek(1).obj); ((INode) yyval.obj).add(((INode) val_peek(0).obj).children); }
break;
case 79:
//#line 257 "Words.y"
{ yyval.obj = new LNode(AST.Type.DIRECTION, Direction.Type.ANYWHERE); }
break;
case 80:
//#line 258 "Words.y"
{ yyval.obj = new LNode(AST.Type.DIRECTION, Direction.Type.DOWN); }
break;
case 81:
//#line 259 "Words.y"
{ yyval.obj = new LNode(AST.Type.DIRECTION, Direction.Type.LEFT); }
break;
case 82:
//#line 260 "Words.y"
{ yyval.obj = new LNode(AST.Type.DIRECTION, Direction.Type.RIGHT); }
break;
case 83:
//#line 261 "Words.y"
{ yyval.obj = new LNode(AST.Type.DIRECTION, Direction.Type.UP); }
break;
case 84:
//#line 265 "Words.y"
{ yyval.obj = new INode(AST.Type.POSITION, val_peek(2).obj, val_peek(0).obj); }
break;
case 85:
//#line 269 "Words.y"
{ yyval.obj = new INode(AST.Type.IDENTIFIER_LIST, val_peek(0).obj); }
break;
case 86:
//#line 270 "Words.y"
{ yyval.obj = new INode(AST.Type.IDENTIFIER_LIST, val_peek(2).obj); ((INode) yyval.obj).add(((INode) val_peek(0).obj).children); }
break;
case 87:
//#line 274 "Words.y"
{ yyval.obj = new INode(AST.Type.PARAMETER_LIST, val_peek(0).obj); }
break;
case 88:
//#line 275 "Words.y"
{ yyval.obj = new INode(AST.Type.PARAMETER_LIST, val_peek(2).obj); ((INode) yyval.obj).add(((INode) val_peek(0).obj).children); }
break;
case 89:
//#line 279 "Words.y"
{ yyval.obj = new INode(AST.Type.PARAMETER, val_peek(1).obj, val_peek(0).obj); }
break;
case 90:
//#line 283 "Words.y"
{ yyval.obj = new LNode(AST.Type.IDENTIFIER, val_peek(0).sval); }
break;
case 91:
//#line 287 "Words.y"
{ yyval.obj = new LNode(AST.Type.REFERENCE, val_peek(0).sval); }
break;
case 92:
//#line 291 "Words.y"
{ yyval.obj = new LNode(AST.Type.NUM, val_peek(0).dval); }
break;
case 93:
//#line 292 "Words.y"
{ yyval.obj = new LNode(AST.Type.STRING, val_peek(0).sval); }
break;
//#line 1059 "Words.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Words()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Words(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
