# only works with the Java extension of yacc: 
# byacc/j from http://troi.lincom-asg.com/~rjamison/byacc/

ifeq ($(OS),Windows_NT)
    RM = del
else
    RM = rm -f
endif

JFLEX  = jflex 
BYACCJ = yacc -J
JAVAC  = javac

# targets:

all: Parser.class

run: Parser.class
	java Parser

build: clean Parser.class

clean:
	$(RM) *~ *.class *.java

Parser.class: Yylex.java Parser.java
	$(JAVAC) Parser.java

Yylex.java: calc.flex
	$(JFLEX) calc.flex

Parser.java: calc.y
	$(BYACCJ) calc.y
