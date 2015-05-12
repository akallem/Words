Words

COMS 4115, Spring 2015, Final Project Report

Team 16

Andrew Kallem (ajk2214)		Project Manager

Alex Mark (acm2209)			Language Guru

James Young (jty2106)		System Architect

Michael Ben-Ami (mzb2106)		System Integrator

Wangda Zhang (wz2295)		Tester

# 1. Introductory Whitepaper

## 1.1. Overview

What is the best way to teach students new to computer programming?  First-time programmers can range from children as young as 7 (NOTE:  http://www.nytimes.com/2014/05/11/us/reading-writing-arithmetic-and-lately-coding.html?_r=0) to adult learners taking up computer programming later in life.  Would these students young and old learn better if their first programming language sounded more like normal speech?  If so, how much can a programming language be made to sound like normal speech while still having a rigorously defined syntax and allowing for rich expression?  These are the questions that the *Words* programming language is designed to explore.

*Words* is an *experimental*, *natural, educational, object-oriented* programming language enabling first-time programmers to create *interactive 2D animations*.

* **Experimental:** *Words* is experimental in two senses.  First, it is an open question whether students will learn computer programming more effectively using *Words*’s restricted domain (2D animations) and natural English syntax.  Second, *Words*’s aggressively-applied natural English syntax investigates how much a programming language can be made to sound like normal speech.

* **Natural:** All constructs in *Words* were designed to read as natural English.  Indeed, most statements in the language read as completely normal English sentences!  In some cases, *Words* allows optional or synonymous keywords to help the code read naturally (e.g., **a** versus **an**; **which** versus **that**; pronouns).

* **Educational:** *Words* introduces users to many features found in modern, high-level programming languages such as classes, inheritance, event listeners, and dynamic typing.  *Words*’s design goal is to help new users become familiar and comfortable with these concepts without their necessarily realizing it.

* **Object-oriented:** *Words* is fundamentally object-oriented.  All objects inherit from a single base class called *thing*. All objects are automatically given a visualization in the 2D scene, helping students understand object-oriented design more concretely.

* **2D animations:** *Words* lets users create interactive scenes and animations on a 2D grid.  Users define classes and instances of objects that appear on the grid, define their behaviors and interactions, and watch as their program comes to life.

The principal design goals of *Words* are:

* *Words* should be as close to natural English as possible, with most constructs simply "making sense".  Even a person with no programming experience should be able to read and understand most or all of a *Words* program.

* *Words* should be expressive enough to allow users to define interesting behavior for their objects, explore their creativity, and fuel their interest in programming.

* *Words* is a "stepping stone" programming language.  The skills learned in *Words* should be transferrable, with a reasonable learning curve, to modern general purpose programming languages.

## 1.2. The *Words* Programming Environment

*Words* programs are executed using a Java-based Words interpreter.  Programs can be entered into an interactive REPL console or written in advance in a text file that is read by the interpreter.  In either case, the programming environment includes two components: a graphical 2D grid showing the moving and interacting objects that the program has defined (herein referred to as "the board" or “the world”) and a text console showing any relevant non-graphical messages and providing the user with a REPL interface (herein referred to as “the command line” or “console”).

*Words* programs can create people, items, food, or any "thing" in a student’s imagination.  The student can define types of things, create instances of each thing, place them on the board, and alter their properties and interactions.  All things have in-built basic actions such as moving and speaking, letting users create rich (and perhaps funny!) interactions.  These basic actions can be composed into more complex behaviors.  All actions for an object are placed on a per-object action queue and run one-at-a-time in discrete time steps.

## 1.3. What Makes *Words* Different

*Words* is neither the first language aimed at first-time programming students nor the first language to use a natural English syntax.  What makes *Words* different is its aggressive combination of the two.  Here we highlight differences with three existing and somewhat similar languages.

ALICE (NOTE:  http://www.alice.org/index.php) is an educational programming language developed by researchers first at the University of Virginia and later at Carnegie Mellon.  Like *Words*, it is object-oriented, is driven by event listeners, and restricts its domain to animated scenes (in Alice’s case, 3D scenes).  However, the experience of programming in ALICE is fundamentally different from *Words* and modern general-purposes languages.  In ALICE, users drag and drop language constructs (conditionals, loops, etc.) from a palette.  As a result, there is no explicit syntax for them to remember; the GUI itself ensures programs are valid.  On the one hand, ALICE’s approach may help new programmers learn the core concepts better without the overhead of typing code or learning a syntax.  On the other hand, programmers using ALICE may form a misconception as to what a program embodies, putting these new programmers at a disadvantage later on when they graduate to more advanced programming languages that require different structures and syntax.  By contrast, *Words* immediately introduces new programmers to the concept of a program being a simple text file having a rigorous syntax.

Code.org (NOTE:  http://code.org/) is an educational website for computer programming.  One of its offerings is a drag and drop coding interface (similar to ALICE) for solving 2D animated puzzles.  While Code.org’s syntax is in many way close to natural English, making it more similar to *Words* than ALICE, its syntax is still not as aggressively English-based as *Words*.  Like ALICE and unlike *Words*, Code.org users do not learn to type code themselves.  Finally, perhaps the most important difference between *Words* and Code.org is that Code.org presents users with closed puzzles that they must solve using a limited palette of coding structures, whereas *Words* allows users to open-endedly explore their creativity (within its domain of 2D animations).

HyperTalk (NOTE:  http://en.wikipedia.org/wiki/HyperTalk) is a desktop scripting language designed to allow first-time programmers to develop their own applications on the HyperCard platform, itself an application that shipped with all Apple computers in the late 1980s and 1990s.  Like *Words*, programs are created as text files that are run by the HyperTalk interpreter. HyperTalk’s syntax resembles English in many ways, but unlike *Words*, HyperTalk statements often read as stilted rather than natural English sentences, using some constructions (such as end to close a block) that do not appear in natural English at all.  HyperTalk also differs from *Words* in focusing on a different problem domain.

# 2. Language Tutorial

This section serves as an introduction to the *Words *programming language.  Our aim in this tutorial is to show you the essential parts of the programming language in real programs to get you started as quickly as possible.  From the information in this tutorial, you should be able to design your own interactive animated worlds.

As you dive deeper into *Words* programming, you may find it useful to read the Reference Manual to learn more details about the language. 

## 2.1. Setup

### 2.1.1. System Requirements

The *Words* interpreter (which runs your *Words* programs) was itself created in Java.  So, to run *Words* programs, you’ll need the Java Runtime Environment (JRE) on your computer.  You can download and install the latest JRE from Oracle at [http://www.oracle.com/technetwork/java/javase/downloads/index.html](http://www.oracle.com/technetwork/java/javase/downloads/index.html).  Select JRE, then download and run right installer for your computer.

### 2.1.2. Downloading and Building *Words*

Currently, the *Words* interpreter is provided as source code hosted on GitHub.  To run *Words* programs, you will have to download the *Words* source code and build it on your computer.  To download the source code, you will need git, available for download at [http://git-scm.com/downloads](http://git-scm.com/downloads).  To build the source code, you will need ant, available for download at [https://ant.apache.org/bindownload.cgi](https://ant.apache.org/bindownload.cgi).

You can download the *Words* source code by opening a command prompt (sometimes called the terminal or console) in the directory (folder) where you would like to store *Words*, then executing the following command:

	git clone https://github.com/Aalk4308/Words.git

This will create a new directory on your computer called Words.  To build *Words*, enter that directory and then execute the following command:

ant

Congratulations!  You have successfully downloaded and built *Words*.  You’re now ready to run and write your first programs.

### 2.1.3. Running *Words*

To run *Words*, enter the Words directory you created and execute the following command:

java -jar jar/Words.jar

You will see a text prompt (described below) where you can write statements in the *Words* language and see them executed, as well as a blank grid called the "board" or “world”.

*Words* also comes bundled with full sample programs in the samples directory.  You can run one of these programs with the following command:

java -jar jar/Words.jar samples/HelloWorld.words

When you run a program, *Words* processes the entire file (in this case, HelloWorld.words), then presents you with the console to allow you to further interact with the program.

You can follow along the rest of this tutorial by writing *Words* statements directly into the *Words* console.  But remember, you can also create a *Words* programs as a separate file like the ones in the sample directory using a text editor.  Once you gain experience with *Words*, you will probably find it easier to write programs in a file so that you can edit them more easily.

## 2.2. Basic *Words* Features

### 2.2.1. Objects

Start up the *Words* environment.  In front of you is a blank world and a text prompt called the console.  In this world, you will create **objects**.  Each object can **move** around the world, **say** statements, and hold **properties**.

Let’s begin by creating two objects, Bob and Alice, on the board. Type into your console

	Bob is a thing at 0,0.

	Alice is a thing at 1,0.

and hit enter.  Soon, you should see Bob appear in the middle of the world and Alice appear to his right.  Try moving Bob around by typing in the console

	Make Bob move left 3.

and you should soon see Bob move left 3 spaces on the board.  Try changing the direction he moves, or try changing how far he moves — each **frame**, when moves are performed, you should see him move around the board.

In addition to moving around the board, you can also make Bob say things. Type into the console

	Make Bob say "Hello World".

and watch Bob’s text prompt change to say "Hello World."

In addition to performing actions, objects can also hold properties.  These properties can be used in later calculations or directly in an object’s actions. For instance, try typing into your console:

	Bob's catchphrase is "heyyyyyyyy".

	Make Bob say Bob's catchphrase.

You should now see Bob say "heyyyyyyyy."  In the first line, you set the name of Bob’s "catchphrase" property to be "heyyyyyyyy."  In the second line, you recalled the property by requesting “Bob’s catchphrase.”

Congratulations!  You’ve created your very first object, you’ve made it move, you’ve given it properties, and you’ve made it say things.  These are the most basic features that an object can perform.

### 2.2.2. Object Action Queue

Each object contains an **action queue **which contains the actions the object will perform in the future. You can think of an object’s action queue as its personal "to-do list".  The *Words *environment keeps track of **frames**, which are set amounts of time (defaulted to be 1 second per frame). During each frame, you can enqueue actions onto an object (add to their to-do list) by typing statements at the console or by loading a program from a file. At the end of each frame, the *Words* environment dequeues the first action from each object’s action queue, and executes these actions simultaneously (checks off the first item on every object’s to-do list).

To enqueue a statement on an object’s action queue, we use the Make command. For an example, try running:

	

	Make Bob move left 4.

	Make Bob say "hey".

	Make Alice move left 2.

	Make Alice say "hey".

	Make Bob move right 4.

Nine actions are enqueued onto Bob’s action queue, and two actions are enqueued onto Alice’s action queue. For the next two frames, both Bob and Alice will move left. In the third frame, Alice will speak while Bob continues moving left. In the fourth frame, Bob continues moving left, and In the fifth frame, he returns Alice’s greeting. For the next four frames, Bob moves right to return to his starting position. 

Each time an action is enqueued, it goes to the back of the queue, where it will be executed after all other actions in the queue. However, actions can be forced to the front of the queue by using the keyword now. For instance, let’s give Bob a long list of actions. Type into your console:

	Make Bob move left 1000.

He should now be moving left for the next thousand frames. However, say you suddenly want to make him move right for two frames. You can type into your console:

	Make Bob move right 2 now.

For two frames, you should see Bob move right before he continues moving left for however many frames he has left in his thousand-frame move. You can also pause Bob by using the wait action, which halts the execution of an object’s action queue for a set number of frames. Try typing:

	Make Bob wait 10 now.

Bob will now pause for 10 frames before continuing to execute his action queue. Once again, by using the now command, the waiting was put at the front of the command. If the now keyword was not used, Bob would have finished his current action queue (the 1,000 moves to the left), and then paused for 10 seconds.

An object’s item queue can also be cleared by using the Stop command. Depending on your reading speed, Bob may still be moving left on the board, but you’re really tired of watching him move left. To stop him and completely clear his action queue, just type:

	Stop Bob.

Basic actions, custom actions (discussed in the next section), and property modifications can all be enqueued onto an object’s action queue. Other commands which do not use the Make command are executed before action queues are dequeued. For instance, try running the following:

	

	Bob's catchphrase is "yo".

	Make Bob say Bob's catchphrase.

	Make Bob's catchphrase be "Hello".

	Make Bob say Bob's catchphrase.

Before Bob says his catchphrase at the next frame’s execution, it is set to be "yo". Then, three actions are placed in Bob’s action queue and the first one is executed. Therefore, Bob will say “yo” in the first frame. Another frame will pass where Bob’s catchphrase is set to be “Hello”. Then, in the third frame, he will say “Hello”, which had been updated in the second frame.

### 2.2.3. Classes

So far, you created Bob and Alice by defining them to each be a thing.  However, they aren’t only things — they’re persons!  Persons, like any class of things, can have special properties and actions that apply to them only and not all things.  This is why *Words* has the concept of classes.  When an object is created, it is assigned a **class**.  Each class can contain a number of properties and **custom actions** that get inherited to the object.

Let’s create a person class for Bob and Alice.  Type into your console:

A person is a thing which {

	has a height of 5.9.

	has a weight of 150.

	can jump which means {

		Make them move up 2.

		Make them move down 2.

	}

}

First, we defined the class person as a subclass of thing.  This means that person automatically inherits all properties and actions of thing.  Later on if you’d like, you can make other classes (like adult or child) that are subclasses of person, and they would inherit the properties and actions of person.  In this way, you create a *hierarchy* of classes that eventually lead back to thing.

To create new properties and actions in the person class we used the which keyword and opened a block of statements using a curly brace.  You can see that each person will start off with a height of 5.9 and a weight of 150.  Any new person will have these properties by default.  Additionally, each person will be able to jump, which we have defined in the inner block.  

You can’t change an object’s class once it’s been created, so to make Bob and Alice persons, we’ll have to remove and recreate them.  Let’s make Bob and Alice be persons.  Type into your console:

Remove Bob.

Remove Alice.

Bob is a person at 0,0.

Alice is a person at 2,0.

Make Bob jump.

Make Bob say "My height is " + Bob's height. 

Make Bob's height be 6.2.

Make Bob say "Wow, I just grew to " + Bob's height. 

Make Alice say "My height is " + Alice's height.

After we remove Bob as a thing and create Bob as a person, we watch him jump.  We then watch him say his height, which is the default of the person class.  Then we change his height and watch him say his new height.  Notice that Alice’s height has not changed — only Bob’s height did.  The changes to one object’s properties don’t affect the properties of other objects of the same class.

### 2.2.4. Looping

It’s possible to make objects repeat a sequence of actions multiple times by **looping**.**  **There are two separate types of loops.  Try typing into your console:

Repeat 5 times {

	Make Bob say "I'm going to jump".

	Make Bob jump. 

	Make Bob say "I just finished jumping".

}

Watch and see Bob repeat these actions 5 times.

It is also possible to make Bob do something until a certain condition is met.  For instance, try typing:

	Bob's jump_counter is 0.

	While Bob's jump_counter < 3 {

Make Bob say "I'm going to jump".

Make Bob jump.

Bob's jump_counter is Bob's jump_counter + 1.

	}

A while loop executes the list of statements enclosed in braces until its **condition** (sometimes called its **predicate**) is no longer true.  In this condition, we test if Bob’s jump_counter (one of his properties) is greater than 0.  In the loop, we decrement Bob’s jump_counter so that it will eventually equal zero, at which point the condition will no longer be true. 

Conditions can also be significantly more complicated than this one.  You can use boolean logic, arithmetic, and more than one property from more than one object.  For details and example on these more complicated conditionals and their rules, you can consult the Reference Manual. 

### 2.2.5. Event Listeners and Locally Scoped Objects

Objects can also interact with each other through event listeners.  An event listener, like a while loop, has a condition and a block of statements.  At the end of each frame, the condition will be checked, and, if the condition is true, the statements will be executed.  For instance, let’s make Bob and Alice play a round of Marco Polo.

Whenever Bob says "Marco" {

	Stop Alice.

	Make Alice say "Polo".

	Repeat 10 times {

		Make Alice move anywhere.

	}

}

	Whenever Alice says "Polo" {

		Stop Bob.

		Bob's xMove is Alice's column - Bob's column.

		Bob's yMove is Alice's row - Bob's row.

		Make Bob move right Bob's xMove.

		Make Bob move up Bob's yMove.

		Make Bob say "Marco".

}

We have created two event listeners.  The first event listener causes Alice to respond and say "Polo" whenever Bob calls says "Marco", then move a few cells in the hopes that Bob won’t catch her.  The second event listener causes Bob to pursue Alice at her last known position and then say "Marco" to continue his search.

This version of the game will unfortunately last forever, since there is no condition to stop them from playing when Bob catches Alice!.  A more complicated version of the game might involve Alice and Bob switching off hiding and seeking.  Try adding the following properties and event listeners:

	Alice's role is "seeker".

Bob's role is "hider".

Whenever Alice says "Marco" {

	Stop Bob.

	Make Bob say "Polo".

	Repeat 10 times {

		Make Bob move anywhere.

	}

}

	Whenever Bob says "Polo" {

		Stop Alice.

		Make Alice move left Bob's column - Alice's column.

		Make Alice move up Bob's row - Alice's row.

		Make Alice say "Marco".

}

Whenever Alice's row = Bob's row and Alice's column = Bob's column { 

	Stop Alice.

	Stop Bob.

	If Alice's role = "seeker" then {

		Alice's role is "hider".

		Repeat 10 times {

			Make Alice move anywhere.

		}

		Bob's role is "seeker".

		Make Bob wait 5.

		Make Bob say "Marco".

	}

If Bob's role = "seeker" then {

		Bob's role = "hider".

		Repeat 10 times {

			Make Bob move anywhere.

		}

		Alice's role is "seeker".

		Make Alice wait 5.

		Make Alice say "Marco".

	}

}

Now, since Alice and Bob each hold their roles as hider and seeker, and each know how to perform both roles, they can switch off.  Whenever they are on the same cell, they will switch roles.  The new hider can move anywhere for 5 frames while the seeker waits 5 frames. After the seeker waits 5 frames, the person will start calling "Marco" and the game will once again be on.

The conditions for an event listener can use the same logic that we used in the while loop and can also look at actions that happened in the past turn. Event Listener conditions can also look for conditions that affect any member of a class. For instance, let’s assume that while Bob and Alice are playing Marco Polo, they also eat any food they stumble across. We create a food class, and then define what happens when a person intersects food. We also use the touches keyword, which tests if two objects are in the same position. 

	A food is a thing.

	Food_maker is a thing at -50, -50.

	

	Repeat 100 times {

		Pizza is a food at Food_maker's column, Food_maker's row.

		Food_maker's row is Food_maker's row + 1.

		Food_maker's column is Food_maker's column + 1.

	}

	Remove Food_maker.

	

	Whenever a person [p] touches a food [f] {

		Make p say "Yum!" now.

		Remove f.

	}

First we defined a food class, and then we placed 100 slices of pizza on a linear path through the board. In the repeat loop, we used a **locally scoped **object for pizza. A "Pizza" object is created within the loop at a location defined by the item generator. While you are in the repeat loop, you can modify the Pizza object as much as you want, but after we leave the loop, we have no way of reaching the Pizza variable any more. If you try writing “Remove Pizza.” you will receive an error as Pizza is not defined outside of the loop. The only way to interact with the Pizza is now through a class event listener, such as the one presented.

The event listener will be called whenever a person and food are in the same location. A person will "eat" the food by saying yum, and the food will go missing. The bracket notation defined a local name for the person and food: p and f can therefore be used to modify the person and food that are on the same space. If multiple people and food end up touching in the same frame, the event listener will be called on all qualifying pairs. 

## 2.3. Where to Go from Here

Congratulations!  You’ve completed the *Words* tutorial.  With your new knowledge, you should be able to start creating your own interesting worlds, and you can have fun making objects interact.  However, *Words* contains many more features that were not covered in this tutorial.  You can find this and more in the Reference Manual*.*

Eventually, you may start to feel limited by the *Words* environment.  That is okay.  The environment is not designed to be your last programming language — merely your first.  When the time comes that you cannot express your ideas in *Words*, you may want to look for a more advanced programming language, using the experience you gained in *Words* as a stepping stone.

# 3. Language Reference Manual

This section provides a formal specification of the *Words* programming language.  It is modeled heavily after the reference manual for the C programming language found in Appendix A of Kernighan and Ritchie’s *The C Programming Language* (second edition).

Portions of the *Words* grammar are presented in the relevant subsections to help the reader understand the language.  A complete grammar is presented in the final section.

*Words* is designed so that a program’s execution should be displayed on a graphical user interface (GUI), allowing the user to see how the objects they have created are behaving. However, the GUI is not explicitly required by the language itself.  Consequently, this manual focuses on the formal properties and requirements of the language and only mentions the GUI when it helps explain the language.

## 3.1. Lexical Conventions

### 3.1.1. Programs

A program consists of a list of statements stored in a single file or entered through a REPL interface.  Programs are translated one statement at a time.

*program*:

	*statement-list*

*statement-list*:

	*statement*

	*statement* *statement-list*

The character set for *Words* is 7-bit ASCII.  As such, the program file can use any character encoding that is downward-compatible with 7-bit ASCII, including UTF-8 and ISO 8859-1.  (Likewise, UTF-16 and UTF-32 encoded program files are not supported because they are not downward-compatible to 7-bit ASCII.)

### 3.1.2. Tokens

There are six types of lexical tokens in *Words*: identifiers, references, keywords, literals, operators, and separators.  Adjacent identifiers, references, keywords, and literals should be separated from one another by one or more whitespace characters when necessary.  Whitespace characters are the space, tab, newline, and carriage return characters.  Aside from separating adjacent tokens, whitespace characters are ignored.

### 3.1.3. Comments

The characters // designate a comment except if they occur within a string literal.  All characters from the // to the end of the line are treated as whitespace and ignored.

### 3.1.4. Identifiers

An identifier is a sequence of letters, digits, and/or underscore characters.  The first character in an identifier must be a letter or underscore.  There is no length limit on identifiers.  Identifiers are case sensitive: uppercase and lowercase versions of a letter are considered different.  Identifiers cannot be a keyword.

### 3.1.5. References

A reference is a sequence of letters, digits, and/or underscore characters immediately followed (without any intervening whitespace) by the characters 's.  References follow the same rules as identifiers with respect to the first character, length, case sensitivity, and the portion of the reference prior to the 's not matching a keyword.

### 3.1.6. Keywords

The following is a complete list of keywords.  For clarity, keywords are always written in **bold fixed-width** font.  Keywords are case sensitive, though in some cases the first character may be either upper-case or lower-case as shown in the table.

<table>
  <tr>
    <td>a</td>
    <td>has</td>
    <td>moves</td>
    <td>that</td>
  </tr>
  <tr>
    <td>A</td>
    <td>her</td>
    <td>means</td>
    <td>their</td>
  </tr>
  <tr>
    <td>above</td>
    <td>Her</td>
    <td>next</td>
    <td>Their</td>
  </tr>
  <tr>
    <td>an</td>
    <td>him</td>
    <td>not</td>
    <td>them</td>
  </tr>
  <tr>
    <td>An</td>
    <td>his</td>
    <td>nothing</td>
    <td>then</td>
  </tr>
  <tr>
    <td>and</td>
    <td>His</td>
    <td>now</td>
    <td>times</td>
  </tr>
  <tr>
    <td>anywhere</td>
    <td>If</td>
    <td>of</td>
    <td>to</td>
  </tr>
  <tr>
    <td>as</td>
    <td>is</td>
    <td>or</td>
    <td>touches</td>
  </tr>
  <tr>
    <td>As</td>
    <td>it</td>
    <td>Remove</td>
    <td>up</td>
  </tr>
  <tr>
    <td>at</td>
    <td>its</td>
    <td>Repeat</td>
    <td>wait</td>
  </tr>
  <tr>
    <td>be</td>
    <td>Its</td>
    <td>Reset</td>
    <td>waits</td>
  </tr>
  <tr>
    <td>below</td>
    <td>left</td>
    <td>right</td>
    <td>Whenever</td>
  </tr>
  <tr>
    <td>can</td>
    <td>long</td>
    <td>say</td>
    <td>which</td>
  </tr>
  <tr>
    <td>down</td>
    <td>Make</td>
    <td>says</td>
    <td>While</td>
  </tr>
  <tr>
    <td>Exit</td>
    <td>move</td>
    <td>Stop</td>
    <td>with</td>
  </tr>
</table>


### 3.1.7. Literals

There are two types of literals in *Words*: number literals and string literals.

#### 3.1.7.1. Number Literals

A number literal is a sequence of digits, optionally followed by a decimal point and another sequence of digits.  A number literal cannot begin or end with a decimal point, i.e., .123 and 123. are not valid number literals.  All number literals are considered to be floating point decimal numbers.

#### 3.1.7.2. String Literals

A string literal is a sequence of characters, excluding newline characters and double-quotes, enclosed within double-quotes, i.e., "string".  Within a string literal, a newline character may be represented with the escape sequence \n, a double-quote character may be represented with the escape sequence \", and a backslash may be represented with the escape sequence \\.

### 3.1.8. Operators and Separators

The following characters or sequences of characters are operators in *Words*: +, -, *, /, =, <, <=, >, and >=, as well as the keywords and, or, and not.

The following characters are separators in *Words*: ., (, ), {, }, [, ]  and ,.

## 3.2. Semantic Concepts

### 3.2.1. Semantic Items

The fundamental semantic concepts in *Words* are:

* **Classes:** A class specifies a type for objects.  The class definition provides the common properties of the objects in the class and the custom actions that objects in the class can perform.  All classes (except the base class) must have a single parent class, and they inherit the common properties and custom actions of their parent class.  The special built-in base class *thing* exists to be an available parent class to new classes.  The sequence of classes from a given class (or an object of a given class) through its parent, grandparent, etc. up to the base class *thing* is called its class chain.

* **Objects:** An object is a bundled, durable, and mutable collection of properties belonging to a single class.  An object’s class is immutable and set when the object is created.  The properties of an object are identifier-value pairs, where the value may be a string, a number, or a pointer to another object.  All objects inherit the properties of the classes in their class chain and can both override the properties of or contain additional properties beyond those provided by their class chain.  All objects include special number properties *row* and *column* representing the object’s position in the GUI.  These special properties may be positive or negative but cannot be assigned a non-numeric value.  All objects also include special string properties *name* and *class* representing the name and class given to the object when it was created.  These properties are immutable, and attempting to change them generates a runtime error.  All objects include an initially empty action queue of actions that they will perform over time (see the Action Queues section below).

* **Basic actions:** The basic actions are property assignment, move, say, and wait.  All objects can perform any of the basic actions.  A basic action can be added to an object’s action queue.

* **Custom actions: **Custom actions are sequences of statements to be performed when the custom action is invoked.  Custom actions are defined within a class definition and are denoted with an identifier.  An object can invoke a custom action only if it appears in its class chain.  A custom action can be added to an object’s action queue.

* **Strings: **A string is a sequence of characters.

* **Numbers:** All numbers are floating point.

We use the somewhat generic term ‘items’ to refer to any of these semantic concepts.

When a property of an object is sought using an identifier, *Words* first searches the identifier-value pairs constituting the properties of the object for a matching identifier.  The search continues through the properties defined in the object’s class chain and terminates as soon as a property matching the identifier is found.  Likewise, when a custom action is invoked by an object, *Words* first searches the custom actions of the object’s class, then up through the custom actions defined in its class chain.

### 3.2.2. Identifiers and References

An identifier, possibly immediately preceded by one or more references (a reference list), refers to a location in storage.  This location contains one of five types of items: an object, a class, a custom action, a string, or a number.  The location may also be empty/unused, in which case the identifier is taken to refer to the special keyword **nothing**.

An *unreferenced identifier* (one that is not immediately preceded by a reference list) refers to either an object, a class, a custom action, or **nothing**, except within the body of a custom action definition, in which case it may also refer to a string or number argument to the custom action.  A *referenced identifier* (one that is immediately preceded by a reference list) refers to either an object, a number, a string, or **nothing**.

For example, if the following appears not in the body of a custom action definition:

John may refer to either an object, a class, a custom action, or **nothing**.

John's mother may refer to either an object, a number, a string, or **nothing**.

In the first example, John is an unreferenced identifier.  In the second example, mother is a referenced identifier since it is immediately preceded by the reference list John's.

Note that both referenced identifiers and unreferenced identifiers may refer to objects.  When a language construct expects an object, the grammar generates a (possibly empty) reference list followed by an identifier:

*queueing-statement*:

STOP *reference-list* *identifier*.

*reference-list*:

	*reference* *reference-list*

	*ɛ*

A reference refers to the item named by the portion of the reference excluding the final characters 's.  For example, the reference John's refers to the same item as the identifier John.  The item a reference refers to must be an object.  If it does not refer to an object, or refers to **nothing**, a runtime error is generated.  References may be chained together to form a reference list.  For example, John's mother's is a reference list.

To determine the item that a referenced identifier refers to, *Words* follows the chain of objects embodied in the reference list.  For example, John's mother's height refers to a (presumably but not necessarily number) property on an object.  The object in question is referred to by John's mother.  mother in turn refers to a property (which must be an object, since it was part of a reference) on an object referred to by the identifier John.  If the object John did not exist, or if it did not have a property mother, or if its property mother was not an object, a runtime error would have been generated.  If merely the property height did not exist, the result would have been **nothing** but not an error.

The type of item that an identifier refers to is determined dynamically at runtime, so the grammar of *Words* does not enforce that referenced identifiers and unreferenced identifiers refer to an acceptable type of item.  As described above, incorrect usage generates a runtime error.

## 3.3. Expressions and Predicates

### 3.3.1. Expressions

An expression is a sequence of operators and other lexical tokens that can be evaluated.  An evaluated expression produces either an object, a number, a string, **nothing**, true, or false.  Note that true and false are not keywords or a type of object property but merely an intermediate or final result of expression evaluation.

There are two types of expressions: value expressions and relational expressions, discussed below.

#### 3.3.1.1. Operator Precedence and Associativity

The following table presents the operators available in expressions, their meaning, acceptable operands, their associativity, and their precedence.  The table is presented in decreasing order of precedence with the highest precedence operators first.  Rows are grouped, and all operators in the same group have the same precedence.  Blank rows separate groups.

<table>
  <tr>
    <td>Operator</td>
    <td>Type</td>
    <td>Meaning</td>
    <td>Operands</td>
    <td>Associativity</td>
  </tr>
  <tr>
    <td>-</td>
    <td>unary</td>
    <td>Negation</td>
    <td>Number</td>
    <td>right-to-left</td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>^</td>
    <td>binary</td>
    <td>Exponentiation</td>
    <td>Number</td>
    <td>right-to-left</td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>*</td>
    <td>binary</td>
    <td>Multiplication</td>
    <td>Number</td>
    <td>left-to-right</td>
  </tr>
  <tr>
    <td>/</td>
    <td>binary</td>
    <td>Division</td>
    <td>Number</td>
    <td>left-to-right</td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>+</td>
    <td>binary</td>
    <td>Addition, string concatenation</td>
    <td>Number, string</td>
    <td>left-to-right</td>
  </tr>
  <tr>
    <td>-</td>
    <td>binary</td>
    <td>Subtraction</td>
    <td>Number</td>
    <td>left-to-right</td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>=</td>
    <td>binary</td>
    <td>Equality</td>
    <td>Number, string, object, nothing</td>
    <td>N/A</td>
  </tr>
  <tr>
    <td><</td>
    <td>binary</td>
    <td>Less than (numeric or alphabetical)</td>
    <td>Number, string</td>
    <td>N/A</td>
  </tr>
  <tr>
    <td>></td>
    <td>binary</td>
    <td>Greater than (numeric or alphabetical)</td>
    <td>Number, string</td>
    <td>N/A</td>
  </tr>
  <tr>
    <td><=</td>
    <td>binary</td>
    <td>Less than or equal (numeric or alphabetical)</td>
    <td>Number, string</td>
    <td>N/A</td>
  </tr>
  <tr>
    <td>>=</td>
    <td>binary</td>
    <td>Greater than or equal (numeric or alphabetical)</td>
    <td>Number, string</td>
    <td>N/A</td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>not</td>
    <td>unary</td>
    <td>Logical negation</td>
    <td>True, false</td>
    <td>N/A</td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>and</td>
    <td>binary</td>
    <td>Logical conjunction</td>
    <td>True, false</td>
    <td>left-to-right</td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>or</td>
    <td>binary</td>
    <td>Logical disjunction</td>
    <td>True, false</td>
    <td>left-to-right</td>
  </tr>
</table>


Attempting to apply an operator to an invalid operand (after type conversions are attempted, as discussed below) generates in a runtime error.

#### 3.3.1.2. Value Expressions

A value expression is an expression whose evaluation results in an object, a number, a string, or **nothing**.  Value expressions follow the usual infix arithmetic notation and permit using + for string concatenation.

*value-expression*:

	*reference-list* *identifier*

	*literal*

	NOTHING

	( *value-expression* )

-*value-expression*

*value-expression* + *value-expression*

*value-expression* - *value-expression*

*value-expression* * *value-expression*

*value-expression* / *value-expression*

*value-expression* ^ *value-expression*

#### 3.3.1.3. Relational Expressions

A relational expression is an expression that compares value expressions using a relational operator and results in either true or false.

*relational-expression*:

	*value-expression* = *value-expression*

*	value-expression* < *value-expression*

*	value-expression* > *value-expression*

*	value-expression* <= *value-expression*

*	value-expression* >= *value-expression*

#### 3.3.1.4. Type Coercions

For certain operators, number operands may be coerced to strings, or vice versa, based on the following table.

<table>
  <tr>
    <td>Operator</td>
    <td>Left Side (LS)
Type</td>
    <td>Right Side (RS)
Type</td>
    <td>Coercion(s) Attempted</td>
  </tr>
  <tr>
    <td>-
(unary)</td>
    <td>N/A</td>
    <td>String</td>
    <td>RS to number, else error</td>
  </tr>
  <tr>
    <td>^</td>
    <td>String</td>
    <td>String</td>
    <td>LS and RS to number, else error</td>
  </tr>
  <tr>
    <td>*</td>
    <td>String</td>
    <td>String</td>
    <td>LS and RS to number, else error</td>
  </tr>
  <tr>
    <td>/</td>
    <td>String</td>
    <td>String</td>
    <td>LS and RS to number, else error</td>
  </tr>
  <tr>
    <td>+</td>
    <td>String</td>
    <td>Number</td>
    <td>LS to number, else RS to string</td>
  </tr>
  <tr>
    <td>+</td>
    <td>Number</td>
    <td>String</td>
    <td>RS to number, else LS to string</td>
  </tr>
  <tr>
    <td>-
(binary)</td>
    <td>String</td>
    <td>String</td>
    <td>LS and RS to number, else error</td>
  </tr>
  <tr>
    <td>=</td>
    <td>String</td>
    <td>Number</td>
    <td>LS to number, else RS to string</td>
  </tr>
  <tr>
    <td>=</td>
    <td>Number</td>
    <td>String</td>
    <td>RS to number, else LS to string</td>
  </tr>
  <tr>
    <td><</td>
    <td>Number</td>
    <td>String</td>
    <td>Error</td>
  </tr>
  <tr>
    <td><</td>
    <td>String</td>
    <td>Number</td>
    <td>Error</td>
  </tr>
  <tr>
    <td>></td>
    <td>Number</td>
    <td>String</td>
    <td>Error</td>
  </tr>
  <tr>
    <td>></td>
    <td>String</td>
    <td>Number</td>
    <td>Error</td>
  </tr>
  <tr>
    <td><=</td>
    <td>Number</td>
    <td>String</td>
    <td>Error</td>
  </tr>
  <tr>
    <td><=</td>
    <td>String</td>
    <td>Number</td>
    <td>Error</td>
  </tr>
  <tr>
    <td>>=</td>
    <td>Number</td>
    <td>String</td>
    <td>Error</td>
  </tr>
  <tr>
    <td>>=</td>
    <td>String</td>
    <td>Number</td>
    <td>Error</td>
  </tr>
</table>


Thus, a number can be converted to a string with the expression n + "", and a string can be converted to a number with the expression s + 0.

In all cases where a string is accepted, an object can be coerced into a string equal to the object’s special *name* property.

### 3.3.2. Predicates

A predicate is a condition that can be true or false.  There are two types of predicates: boolean predicates and basic action predicates.

#### 3.3.2.1. Boolean Predicates

A boolean predicate is a combination of relational expressions or other boolean predicates using logical operators.

*boolean-predicate*:

*relational-expression*

( *boolean-predicate* )

NOT ( *boolean-predicate* )

*boolean-predicate* AND *boolean-predicate*

*boolean-predicate* OR *boolean-predicate*

Boolean predicates are evaluated left-to-right and employ short-circuit evaluation, where evaluation ceases as soon as the final result is definitively known.

#### 3.3.2.2. Basic Action Predicates

A basic action predicate tests whether certain behavior has occurred in the current frame (see the Frames section below).

*basic-action-predicate*:

	*subject* *alias *MOVES *direction*opt

	*subject* *alias *SAYS *value-expression*

	*subject* *alias *WAITS

*subject* *alias *TOUCHES *subject alias*

*subject* *alias adjacency-expression subject alias*

*subject*:

	*reference-list* *identifier*

	A identifier

*alias*:

	[ *identifier *]

	*ɛ*

*adjacency-expression:*

	IS NEXT TO

	IS BELOW

	IS ABOVE

	IS LEFT OF

	IS RIGHT OF

In the first three forms (moves, says, waits), the predicate indicates whether an object has performed one of those basic actions in the current frame.  In the case of a wait predicate, it will also be true if the object’s action queue was empty in the current frame, which is semantically equivalent to a wait action.  In the fourth form (touches), the predicate indicates whether two objects have come to the same position in the current frame.  In the final form, the predicate indicates whether two objects have come to be directly adjacent to one another in a specific or any direction in the current frame.

There is no basic action predicate for the property assignment basic action.

A basic action predicate can use one of two types of "subjects": a named object using a reference list and an identifier or the name of a class.  Consider the following:

X **is a** thing.

**Whenever** X **moves** {

	**Stop** X.

}

In this example, the predicate and listener body refers to the object X directly.  Now consider the following:

X **is a** thing.

**Whenever** **a** thing [Y] **moves** {

	**Stop** Y.

}

In this example, the predicate will be evaluated for every member of the class *thing*, and for each one that is true, the body of the listener will execute using the alias Y to refer to the object that satisfied the predicate (see the Scope section below).

## 3.4. Statements

A statement is the unit of translation in *Words*.  Statements are translated and executed one at a time.  They are terminated by the . separator.  There are two main types of statements: *declarative statements* and *non-declarative statements*.  Declarative statements declare either a class or an event listener and can only appear in the main body of a program, not within an enclosing block.  Non-declarative statements are any other statement and can appear anywhere within a program.  They have two sub-types: *immediate statements* and *queueing statements*.  Immediate statements have an immediate impact on the execution of the program.  Queueing statements add to or otherwise modify an object’s action queue (see the Action Queues section below). (NOTE:  Of course, the addition or modification to an object’s action queue does take place immediately (when the statement is translated and executed), but the main semantic effect of the statement and portions of its evaluation do not occur until the action reaches the front of the action queue, hence the distinction from immediate statements.)

*statement*:

	*declarative-statement*

*	non-declarative-statement*

*declarative-statement*:

	*class-declare-statement*

*	listener-declare-statement*

*non-declarative-statement:*

	*immediate-statement*

	*queuing-statement*

### 3.4.1. Declarative Statements

#### 3.4.1.1. Class Declaration Statements

A class declaration statement creates a new class by specifying the name of the class, the parent class, and optionally a set of property and custom action definitions via one or more class definition statements.

*class-declare-statement*:

A *identifier* IS A *identifier*.

A *identifier* IS A *identifier* WHICH { *class-definition-statement-list* }

*class-definition-statement-list*:

	*class-definition-statement*

	*class-definition-statement class-definition-statement-list*

The identifier used to name a class must not match any existing class at the time the statement is executed, else a runtime error is generated.

##### 3.4.1.1.1. Class Definition Statements

The details of a class are specified by a series of class definition statements.  These statements define properties of objects in the class and custom actions that can be invoked by objects in the class.

*class-definition-statement*:

HAS A *identifier* OF *literal*.

CAN *identifier* WHICH MEANS { *non-declarative-statement-list* }

	CAN *identifier* WITH *parameter-list* WHICH MEANS { *non-declarative-statement-list* }

*parameter-list*:

	*parameter*

	*parameter*, *parameter-list*

*parameter*:

	*identifier*

Here, *parameter-list* provides the names of the (optional) parameters to the custom action, and *statement-list* is the body of the custom action that is executed when the custom action is invoked.  Duplicate parameter names in *parameter-list* are simply ignored.  Only non-declarative statements are permissible in the definition of a custom action.  Within the body of a custom action, the keywords **him**, **her**, **it**, **them**, **his**, **her**, **its**, **their**, **His**, **Her**, **Its**, and **Their** used in place of an identifier or reference, refer to the object that invoked the custom action.

#### 3.4.1.2. Listener Declaration Statements

A listener declaration statement creates an event listener based on a predicate.  The predicate is tested on each frame (see the Frames section below), and if the predicate is true, the given statement list is executed in that frame.

*listener-declare-statement*:

	WHENEVER *predicate* { *non-declarative-statement-list* }

AS LONG AS *predicate* { *non-declarative-statement-list* }

In the first form, the event listener that is created is permanent for the duration of the program’s execution.

In the second form, the statement list will execute each frame (including the frame in which the listener was created) while the predicate is true.  Once the predicate is false for the first time (again, including the frame in which the listener was created), the listener is destroyed.

### 3.4.2. Immediate Statements

There are six main kinds of immediate statements: object creation, object destruction, object property assignment, iteration, conditionals, and runtime control.

*immediate-statement*:

	*object-create-statement*

	*object-destroy-statement*

	*property-assign-statement*

	*iteration-statement*

	*conditional-statement*

*	runtime-control-statement*

#### 3.4.2.1. Object Creation Statements

An object creation statement creates a new object of a given class at a given position.

*object-create-statement*:

	*identifier* IS A *identifier* AT *position*.

*position*:

	*value-expression*, *value-expression*

Properties may be later added to the object using property assignment statements or queued property assignment actions.

The two value expressions for position respectively specify the column and row of the object (i.e., x and y) in the GUI, respectively, which may be zero or negative.  They must each evaluate to a number or a value that can be coerced to a number, else a runtime error is generated.  The actual column and row of the object are the values of the value expressions each rounded to the nearest integer.

The identifier used to name an object must not match any existing object in scope at the time the statement is executed, else a runtime error is generated.

The identifier used to name an object is assigned to the object’s special *name* property and cannot subsequently be changed.

#### 3.4.2.2. Object Destruction Statements

An object destruction statement completely removes an object.  The identifier previously used to name the object can subsequently be reused.

*object-destroy-statement*:

	REMOVE *reference-list identifier*.

Note that the original identifier used to create an object need not be the identifier provided when destroying an object.  Consider the following:

X **is a** thing.

Y **is a** thing.

X's Z **is** Y.

**Remove** X's Z.

This program has the effect of creating and shortly afterward destroying the object Y.  The identifier Y is not used in the object destruction statement, but it is the result of the evaluation of the value expression X's Z.

#### 3.4.2.3. Property Assignment Statements

A property assignment statement creates a new property on an object or modifies an existing property.

*property-assign-statement*:

*reference reference-list identifier* IS *value-expression*.

Here, at least one reference is required in order to identify the object whose properties are to be modified.  Consider the following (assuming X and Y are previously created objects):

X's P **is** Y.			// Valid

Z **is** B.			// Invalid

The identifier is the name of the property, and the value-expression is evaluated to the value of the property.

For the avoidance of doubt, note that the following assignment is valid since **nothing** is a value expression:

X's P **is** **nothing**.		// Valid

This form has the effect of removing property P from object X, if it previously existed.  No error is generated if property P did not previously exist.

#### 3.4.2.4. Iteration Statements

An iteration statement allows for a set of statements to be executed more than once.

*iteration-statement*:

	REPEAT *value-expression* TIMES { *non-declarative-statement-list* }

	WHILE *boolean-predicate* { *non-declarative-statement-list* }

In the first form, the statement list is executed some number of times based on the evaluation of the value expression.  The value expression must evaluate to a number or a value that can be coerced to a number, else a runtime error is generated.  The number of times the statement list is executed is the value of the value expression rounded to the nearest integer.  If the value expression evaluates to zero or a negative number, the statement list is not executed at all.

In the second form, the statement list is executed as long as the boolean predicate is true.  Naturally, the statement list should make some change (e.g., modifying object properties) so that the boolean predicate will eventually be false.  If the boolean predicate is already false, statement list is not executed at all.

A common error is to use an object property in the boolean predicate but to modify that property in the statement list using a queueing statement, such as the following (assuming X is a previously created object):

X's P **is** 1.

**While** X's P < 5 {

	**Make** X's P **be** X's P + 1.

}

This will result in an infinite loop, because the change to X’s property P will not take place immediately but only when that action is executed.

#### 3.4.2.5. Conditional Statements

A condition statement executes a given statement list if a given boolean predicate is true.

*conditional-statement*:

	IF *boolean-predicate* THEN { *non-declarative-statement-list* }

#### 3.4.2.6. Runtime Control Statements

A runtime control statement allows the user to restart or terminate a *Words* program.  They are most useful when *Words* is being used in a REPL interface, but they can be used in a program stored in a file as well.

*runtime-control-statement*:

	RESET.

EXIT.

The reset statement causes the environment to completely restart as if the *Words* program were just started.  All existing classes, objects, and listeners are removed.

The exit statement causes the *Words* program to exit.

### 3.4.3. Queueing Statements

Queueing statements add to or modify an object’s action queue (see the Action Queues section below).  The five types of actions that can be added to an objects action queue — each of the four basic actions (property assignment, move, say, and wait) and custom actions — correspond to the queueing statements below.  In addition, a sixth queueing statement using the Stop keyword completely clears an object’s action queue.

*queueing-statement*:

	MAKE *reference-list* *queue-assign-property-list *NOWopt.

MAKE *reference-list* *identifier* MOVE *direction value-expression*opt NOWopt.

MAKE *reference-list* *identifier* SAY *value-expression* NOWopt.

MAKE *reference-list* *identifier* WAIT *value-expression* NOWopt.

STOP *reference-list* *identifier*.

*queueing-custom-action-statement*

*queueing-custom-action-statement*:

	MAKE *reference-list* *identifier identifier* NOWopt.

	MAKE *reference-list* *identifier identifier* WITH *argument-list* NOWopt.

*argument**-list*:

	*argument*

	*argument*, *argument-list*

*argument*:

	*identifier value-expression*

*direction*:

	ANYWHERE

	DOWN

	LEFT

	RIGHT

	UP

Without the now keyword, the action is enqueued at the end of the object’s action queue.  If the now keyword is used, the action is enqueued at the front of the object’s action queue and will be the next action processed in the next frame (see the Frames section below).  Thus, to add several actions to the front of an object’s action queue in a desired order using the now keyword, one must specify them in reverse order.  Consider the following (assuming X is a previously created object):

**Make **X **move left **3 **now**.

**Make **X **say** "example" **now**.

**Make **X's P **be** 5 **now**.

The first three actions on X’s action queue are, in order, the property assignment, the say action, and the move action, because each enqueued action became the new front, ahead of the previously enqueued action.

#### 3.4.3.1. Move Actions

The optional value expression to a queueing statement for a move action must evaluate to either a number or a value that can be coerced to a number, else a runtime error is generated.  If omitted, the value is assumed to be 1.  A negative number is treated as a positive number with the opposite direction.  If the anywhere keyword is used, a random direction is chosen and the subsequent semantics are as if that direction were provided explicitly.  The statement causes a move action to be enqueued on the object’s action queue, with the number of units to move rounded to the nearest integer.  A zero is treated and enqueued as a wait action for 1 frame.

#### 3.4.3.2. Say Actions

The value expression to a queueing statement for a say action must evaluate to a string or a value that can be coerced to a string, else a runtime error is generated.  The statement causes a say action to be enqueued on the object’s action queue.

#### 3.4.3.3. Wait Actions

The value expression to a queueing statement for a wait action must evaluate to either a number or a value that can be coerced to a number, else a runtime error is generated.  The statement causes a wait action to be enqueued on the object’s action queue, with the number of frames to wait rounded to the nearest integer.  A negative number or zero generates a runtime error.

#### 3.4.3.4. Custom Actions

When invoking a custom action, the argument list consists of identifier-value pairs.  The value expressions are evaluated and passed as the argument values for the corresponding parameters in the custom action definition.  The identifier-value pairs may appear in any order, not necessarily the order that was used in the custom action definition.  Any identifier-value pairs where the identifier does not match the parameter name in the custom action definition are ignored.

## 3.5. Scope

Scope in Words is slightly different for classes, objects, listeners aliases, and parameters, but follow natural rules.

### 3.5.1. Classes

The names of classes have global scope in Words below the point in the program where they are declared.  Since class declarations are declarative statements, they can only appear within the main body of the program and not within a block.

### 3.5.2. Objects

When an object is created, it continues to exist (and is displayed in the GUI) regardless of where in the program it was created.  However, the identifier used to name the object has scope only within the block enclosing the object creation statement.

For iteration statements, the scope resets on each execution of the body.  For example, consider the following:

**Repeat** 5 **times** {

X **is** **a** thing **at** 0,0.

**Repeat** 10 **times** {

	**Make** X **move** **anywhere**.

}

}

In this example, 5 objects named X are created, and each is queued to move 10 times in a random direction each time.  The use of the identifier X outside of the iteration would be a runtime error.  (If some other object of that name existed, the object creation statement in the iteration body would itself have been a runtime error.)

### 3.5.3. Listener Aliases

Aliases creates for an object in a basic action predicate have scope only within the body of the listener.

### 3.5.4. Parameters

Parameters to custom actions have local scope within the body of the custom action.

## 3.6. Action Queues

Each object has an action queue.  An action queue is a series of basic or custom actions that the object will perform in order over time (see the Frames section below).

An action on the queue may be either expandable or executable based on the following table:

<table>
  <tr>
    <td>Expandable</td>
    <td>Executable</td>
  </tr>
  <tr>
    <td>Movement actions for >1 unit or based on a value expression other than a number literal
Wait actions for >1 unit or based on a value express other than a 	number literal
Any custom action</td>
    <td>Any property assignment action
Movement actions for 1 unit using a number literal
Any say action
Wait actions for 1 frame using a number literal</td>
  </tr>
</table>


### 3.6.1. Expanding Expandable Actions

When the first action on an action queue is an expandable action (see the Frames section below), it is expanded.  The semantics of expansion depend on the type of action.

#### 3.6.1.1. Movement Actions

The action is replaced with a number of 1 unit movement actions equal to the evaluation of the value expression.  The actions are placed at the front of the action queue.

#### 3.6.1.2. Wait Actions

The action is replaced with a number of 1 frame wait actions equal to the evaluation of the value expression.  The actions are placed at the front of the action queue.

#### 3.6.1.3. Custom Actions

The arguments (if any) to the custom action are evaluated and the statements in the custom action definition are executed.  Non-queueing statements are executed immediately as normal.  However, queueing statements cause their actions to be enqueued at the *front* of the object’s action queue in *the order in which they appeared* in the body.  In this way, the single custom action on the queue is "expanded" into its constituent actions (which, of course, can be basic or custom actions that themselves may be expandable.)

## 3.7. Frames

A frame is a length of time during the program’s execution.  Certain activities are performed at the end of each frame.  Typically, every frame is the same fixed length of time, but it is possible for the GUI to use variable-length frames or allow the user to adjust the frame rate or pause the current frame during program execution.

Each frame consists of three phases in the following order: statement translation and execution, action queue processing, and listener evaluation.  After these phases are completed, the GUI can be updated to display objects at their current positions, what the objects are saying, etc.

### 3.7.1. Phase 1: Statement Translation and Execution

In this phase, any statements received since the previous frame are translated and executed.  Thus, if the program is provided as a file, all statements in the file are translated and executed (even before the first frame).  If statements are provided by the user in a REPL interface, all complete statements they entered since the previous frame are translated and executed.

### 3.7.2. Phase 2: Action Queue Processing

In this phase, the first action in each object’s action queue is processed.  If it is an expandable action, it is expanded (see the Action Queues section above), and expansion process repeats until the first action is an executable action.  The first action (now an executable action) is then dequeued and executed.  If the object’s action queue is empty, there is no effect.  An object’s action queue is not processed until the first frame *after* the frame in which it was created.  In this way, an object will always first appear at the position specified when it was created.

Naturally, executing a move action updates the object’s row and column properties.  Movement left and right decreases or increases the column respectively, and movement up and down increases or decreases the row respectively.  Executing a say action causes some implementation-defined internal state to be updated such that the object will say the provided value expression in the GUI.  Executing a wait action causes no change other than advancing the action queue.

When the first action is expanded or executed, any value expressions are evaluated only at that time, and within the scope in which the queueing statement originally appeared.  The value expressions are *not* evaluated when the action was first enqueued.  Consider the following (assuming X and Y are previously created objects):

**Make **X **move left **Y's P + 1.

**Make** X's P **be** Y's Q + 10.

In this example, we have queued a move action on X.  When this action is the first, it must be expanded, and at that time, the value expression Y's P + 1 is evaluated to determine how many 1-unit move actions to expand it into.  Some time later after the movement is complete, the queued assignment to X’s property P will be the first action on X’s action queue.  The value that is assigned will be determined by evaluating the value expression Y's Q + 10 at this time.

The order in which the expansion and execution of actions occur within a frame is arbitrary and implementation-defined.  Consider, for example, the following (assuming X and Y are previously created objects):

X's P **is** 10.

Y's P **is** 20.

**Make **X **move left **3.

**Make **Y **move left **3.

**Make** X's** **P **be** Y's P.

**Make** Y's** **P **be** X's P.

In this example, we have queued property assignments to both objects X and Y.  They will be dequeued and executed in the same frame.  It is implementation-defined whether X’s P becomes 10 and Y’s P becomes 10, or X’s P becomes 20 and Y’s P becomes 10, as if the actions were first executed on X and then on Y, or vice versa.  The implementation may also have X’s P become 20 and Y’s P become 10, as if the actions were executed simultaneously.

If the first action is a custom action that includes object destruction statements, the object destructions are not processed until after the entire phase is complete.

### 3.7.3. Phase 3: Listener Evaluation

In this phase, the predicates for event listeners are evaluated and, if they are true, the body of the event listener is executed.  Boolean predicate evaluation reflects any changes to object property values made in the prior two phases.  Basic action predicate evaluation reflects any actions that were executed in the action queue processing phase.

All listener predicate evaluations within a frame occur "simultaneously" with respect to object properties based on the property values in place after the prior two phases in the frame.  If the listener body includes object destruction statements, the object destructions are not processed until after the entire phase is complete.

If the body of the event listener includes queueing statements, they will affect the object’s action queue immediately (as normal) but any enqueued actions cannot possibly be dequeued and executed until the next frame.  If two different listeners both have queueing statements for the same object, the order of evaluation is arbitrary and the resultant behavior is implementation-defined.

## 3.8. Grammar

The complete grammar of *Words* is presented below.

In this presentation, the terminal symbols consist of any text or characters written in fixed-width font as well as the italicized symbols *identifier*, *reference*, *number*, and *string*, which correspond to identifiers, references, number literals, and string literals respectively.  All other italicized symbols are non-terminals, the productions for which are given in the grammar.  The start symbol is *program*.

The terminal symbols written in upper-case fixed-width font map one-to-one to the keywords, operators, and separators presented earlier (apart from capitalization), with the exception of the following:

<table>
  <tr>
    <td>Terminal Symbol</td>
    <td>Corresponding Keywords</td>
  </tr>
  <tr>
    <td>A</td>
    <td>a, A, an, An</td>
  </tr>
  <tr>
    <td>WHICH</td>
    <td>that, which</td>
  </tr>
</table>


The *ɛ* symbol indicates that the non-terminal at the head of the production may be the empty string.

The ‘opt’ subscript indicates that the symbol is optional in that production.  Some parser generators might require these productions to be split into two separate productions, one with and one without the optional symbol.

For ease of presentation, the grammar shown below does not enforce the precedence and associativity of the operators listed earlier.  When transforming the grammar into suitable input for a parser generator such as Yacc, the precedence and associativity of the operators can be supplied.

*program*:

	*statement-list*

*statement-list*:

	*statement*

	*statement* *statement-list*

*statement*:

	*declarative-statement*

*	non-declarative-statement*

*non-declarative-statement-list*:

	*non-declarative-statement*

	*non-declarative-statement non-declarative-statement-list*

*declarative-statement*:

	*class-declare-statement*

*	listener-declare-statement*

*class-declare-statement*:

	A *identifier* IS A *identifier*.

A *identifier* IS A *identifier* WHICH { *class-definition-statement-list* }

*class-definition-statement-list*:

	*class-definition-statement*

	*class-definition-statement class-definition-statement-list*

*class-definition-statement*:

HAS A *identifier* OF *literal*.

CAN *identifier* WHICH MEANS { *non-declarative-statement-list* }

	CAN *identifier* WITH *parameter-list* WHICH MEANS { *non-declarative-statement-list* }

*listener-declare-statement*:

	WHENEVER *predicate* { *non-declarative-statement-list* }

AS LONG AS *predicate* { *non-declarative-statement-list* }

*non-declarative-statement:*

	*immediate-statement*

	*queuing-statement*

*immediate-statement*:

	*object-create-statement*

	*object-destroy-statement*

	*property-assign-statement*

	*iteration-statement*

	*conditional-statement*

*	runtime-control-statement*

*object-create-statement*:

	*identifier* IS A *identifier* AT *position*.

*object-destroy-statement*:

	REMOVE *reference-list identifier*.

*property-assign-statement*:

*reference reference-list identifier* IS *value-expression*.

*iteration-statement*:

	REPEAT *value-expression* TIMES { *non-declarative-statement-list* }

	WHILE *boolean-predicate* { *non-declarative-statement-list* }

*conditional-statement*:

	IF *boolean-predicate* THEN { *non-declarative-statement-list* }

*runtime-control-statement*:

	RESET.

EXIT.

*queueing-statement*:

	MAKE *reference-list* *queue-assign-property-list *NOWopt.

MAKE *reference-list* *identifier* MOVE *direction value-expression*opt NOWopt.

MAKE *reference-list* *identifier* SAY *value-expression* NOWopt.

MAKE *reference-list* *identifier* WAIT *value-expression* NOWopt.

STOP *reference-list* *identifier*.

*queueing-custom-action-statement*

*queueing-custom-action-statement*:

	MAKE *reference-list* *identifier identifier* NOWopt.

	MAKE *reference-list* *identifier identifier* WITH *argument-list* NOWopt.

*predicate*:

	*basic-action-predicate*

	*boolean-predicate*

*basic-action-predicate*:

	*subject* *alias *MOVES *direction*opt

	*subject* *alias *SAYS *value-expression*

	*subject* *alias *WAITS

*subject* *alias *TOUCHES *subject alias*

*subject* *alias **adjacency-expression **subject alias*

*boolean-predicate*:

*relational-expression*

( *boolean-predicate* )

NOT ( *boolean-predicate* )

*boolean-predicate* AND *boolean-predicate*

*boolean-predicate* OR *boolean-predicate*

*relational-expression*:

	*value-expression* = *value-expression*

*	value-expression* < *value-expression*

*	value-expression* > *value-expression*

*	value-expression* <= *value-expression*

*	value-expression* >= *value-expression*

*value-expression*:

	*reference-list* *identifier*

	*literal*

	NOTHING

	( *value-expression* )

-*value-expression*

*value-expression* + *value-expression*

*value-expression* - *value-expression*

*value-expression* * *value-expression*

*value-expression* / *value-expression*

*value-expression* ^ *value-expression*

*reference-list*:

	*reference* *reference-list*

	*ɛ*

*parameter-list*:

	*parameter*

	*parameter*, *parameter-list*

*argument-list*:

	*argument*

	*argument*, *argument-list*

*queue-assign-property-list*:

	*queue-assign-property*

	*queue-assign-property*, *queue-assign-property*

*argument*:

	*identifier value-expression*

*parameter:*

	*identifier*

*subject*:

	*reference-list* *identifier*

	A identifier

*alias*:

	[ *identifier *]

	*ɛ*

*queue-assign-property*:

	*identifier *BE* value-expression*

*adjacency-expression:*

	IS NEXT TO

	IS BELOW

	IS ABOVE

	IS LEFT OF

	IS RIGHT OF

*direction*:

	ANYWHERE

	DOWN

	LEFT

	RIGHT

	UP

*position*:

	*value-expression*, *value-expression*

*literal*:

	*number*

	*string*

# 4. Project Management

Written by Andrew Kallem (project manager)

## 4.1. Project Management Process

This section describes the major themes of how we managed the project during the semester.

### 4.1.1. Meetings

We recognized that holding in-person meetings fairly frequently would be critical to ensuring we made steady progress on the project, so we held a regularly scheduled weekly meeting with the entire team lasting up to two hours, with additional meetings as needed (for the entire group or just some team members).  When possible, we used a projector to share one of our computer’s screens so that we could all see the same thing.  The regular meeting agenda was typically:

* Review outstanding pull requests from the previous week and merge them (see Pull Request and Code Review below)

* Discuss proposed changes to the language, with the Language Guru having the ultimate say

* Discuss minor project management logistics (e.g., TA meetings)

* Agree on the major goals for the next week’s "sprint" (see Agile Development Model below)

* Divide development responsibility for how to achieve those goals

Several times, we had meetings on more focused topics such as details of the system architecture.

### 4.1.2. Agile Development Model

We agreed early in the project that it would probably be counterproductive to try to lay out a detailed week-by-week plan to govern the project from start to completion.  After all, we were still learning the material in class and didn’t know what we didn’t know.  Instead, we agreed to approach the project two weeks and later one week at a time.  Each one of these periods was called a "sprint".  As mentioned above, for each sprint, we agreed in our meeting the major goals for that sprint and how to divide the work to achieve those goals.  The critical point was that these goals had to be:

* **Ambitious:** It would not serve us well if the goals were trivial.  We would fail to be making real progress.  Instead, our goals for each sprint had to be ambitious enough to be meaningful so that not too much of the project was pushed to the tail end.

* **Reasonable:** On the other hand, it would not serve us well if our sprint goals were *too* ambitious as to be unreasonable for that period of time.  Our goals had to be reasonably achievable within one or two weeks, otherwise we might feel discouraged and the idea of setting and achieving goals would lose its meaning.

* **Steady:** Having some sprints be much more ambitious than others would be mentally taxing and make it harder to fit this project’s work into our work from other classes.  Instead, we sought to keep a fairly steady development pace.  This also helped gauge our progress to make sure we weren’t falling behind.

Taking to heart the advice mentioned several times in class, we set a very early goal of getting a basic system up and running.  While many portions of that early effort were subsequently redesigned, it was an extremely helpful learning process and made the project appear more tractable.  Starting with a crude but working system that we could iteratively refine and extend was both motivating and a great way to actually see our progress.

Following Agile, we were not afraid to implement sometimes significant refactors to our existing code when we realized it was necessary.  Also, we sometimes worked independently on our sprint goals and sometimes pair programmed.

### 4.1.3. Source Control, Pull Requests, and Code Review

We used git for source control and GitHub as our central repository.  A critical component of our project management was to use GitHub’s pull request feature.  When any team member had code that they felt was ready to be merged into our master branch, we required them to first push it as a separate branch on GitHub and issue a "pull request" for it. (NOTE:  We held fast to this process during the entire project.  The only time we ever pushed directly to master was for truly trivial changes when all of us were in the room together to affirm it.)  Doing so causes GitHub to send an email alert to the other team members indicating that the branch was available.  Each other team member was then responsible for reviewing the pull request (either on GitHub itself or by pulling it to their own local repository if desired) and providing their comments or questions on GitHub.  Generally, only after a quorum of team member’s had reviewed the code would we merge it into our master branch.  (We would often wait to perform this merge until our in-person meetings, unless it was a critical bug fix.)

Our pull request and code review process provided several benefits:

* It provided us assurance that our master branch could not be unexpectedly broken

* It enabled everyone to learn about all the components of our system by having to review the code before it was merged to our master branch

* It helped us enforce our style guide

* It allowed us to catch bugs in our code and gaps in our test coverage that the original developer missed, before it the code merged to our master branch

### 4.1.4. Issue Tracking

We used GitHub’s issue tracker feature in two ways.  First, after we divided responsibilities at our team meeting, we opened issues in the issue tracker and assigned it to the relevant person.  In this way, there was no way to forget who was working on what.  Second, when any of us had an idea for future work, encountered an unexpected bug, identified a gap in test coverage, or the like, they opened a new issue for it in the issue tracker.  The benefit was having a central place to record things we wanted to remember discussing at our next meeting.

### 4.1.5. Project Documents

We used Google Drive / Google Docs for all of our project documents (whitepaper, tutorial, reference manual, miscellaneous notes, and this final report).  In this way, every team member always had access to the very latest version of all our documents.  Since our language became quite stable fairly early in the project (see Language Evolution below), we did not find it necessary to version control our reference manual together with our source code.

## 4.2. Roles and Responsibilities

Our development roles and responsibilities were fluid throughout the project and not strictly aligned with our "official" team roles.  Instead, we divided responsibilities based on a combination of interest, capacity, and ability.  Below are the major implementation details by team member.  In many cases, two or more team members worked together on or contributed toward the same topic.

* **Andrew Kallem:** Grammar, AST infrastructure, relational and boolean operators, conditional statements, scope, action queues, basic actions, custom actions, inheritance, GUI, syntax error reporting, type coercions

* **Alex Mark:** Frame loop, runtime environment, scope, custom actions, basic action predicates, event listeners, semantic check infrastructure (Java exceptions), type coercions

* **James Young:** Core lexer/parser infrastructure, arithmetic operators, iteration statements, special object properties, type coercions

* **Michael Ben-Ami:** Core lexer/parser infrastructure, runtime environment, property assignment, reference list dereferencing, object removal, class creation, runtime control statements

* **Wangda Zhang: **Unit test infrastructure, system test infrastructure (logging), basic actions, boolean predicates, event listeners, command line options

All members of the team contributed significantly to our unit tests and system tests (discussed below under Test Plan and Scripts).

## 4.3. Style Guide

While implementing *Words*, we adopted the following style guide for our Java code.  The text below is copied from the actual style guide document we used during the project (with minor edits) and so is written in the present tense.

———

Our style guide’s main purpose is to help ensure our code uses reasonably consistent formatting and documentation to help make it more readable to ourselves and others.  It is not meant to be overly prescriptive, and for anything that is not explicit or clear, we agree to use our best judgment of what is acceptable.

Generally, we follow Google’s published style guide for Java ([https://google-styleguide.googlecode.com/svn/trunk/javaguide.html](https://google-styleguide.googlecode.com/svn/trunk/javaguide.html)).  Our style guide below lists only:

* points where we want to explicitly acknowledge that we agree with Google’s style guide (i.e., points that are often a source of difference across Java developers)

* points where we want to explicitly differ from Google’s style guide

Anything not listed below should follow Google’s style guide, within reason.  We use § to refer to the section in the Google style guide that we are repeating or differing from.

Of course, the portion of our code generated automatically by our lexer and parser will not follow these guidelines, since we don’t control that portion of our source.

### 4.3.1. Formatting

#### 4.3.1.1. Imports

Imports should be logically grouped, but we do not require a consistent ordering to the major groups or alphabetical ordering within groups. (§3.3.3)

#### 4.3.1.2. One-Line Blocks

We allow conditional and loop blocks having only one statement in the body to omit the opening and closing braces. (§4.1.1)

#### 4.3.1.3. Brace Placement

We choose to follow the K&R style of brace placement as suggested in Google’s style guide (§4.1.2).

#### 4.3.1.4. Indentation

We use the tab character for all indentation.  Our editors should be set up to use the actual tab character and not replace it with some number of space characters. (§4.2)

#### 4.3.1.5. Column Limit and Line Breaks

We do not impose a hard limit on column width or any requirements on where a line is broken.  Our goal is clarity, and we agree to break wide lines when and where reasonable. (§4.4, §4.5)

#### 4.3.1.6. Whitespace

We choose to follow Google’s style guide for horizontal whitespace, particularly the space before the ( following a if, for, while, or catch, and the space preceding a {.

#### 4.3.1.7. Variables

We do not prohibit multiple variables from being declared on one line and allow them to be declared at the top of the block. (§4.8.2)

### 4.3.2. Documentation

We use Javadoc to document classes and important public methods, but we are not overly prescriptive about the format. (§7.1.2)  We agree to use our judgment about what aspects of the code require documentation; typically these are the major public methods of classes that are frequently used by other portions of the code base. (§7.3)  The description should mention important side effects and should mention possible return values (particularly error values such as null or exceptions).  We do not require the same level of documentation for unit test code.

### 4.3.3. Assertions

Where possible and reasonable, we employ Java’s assert statement or AssertionError exception to check that the invariants we expect to hold true in our code are not violated.  We agree this defensive coding practice is beneficial as it aids debugging (by potentially catches errors before they become more difficult to trace) and helps ensure our code integrates correctly.

### 4.3.4. Unit Tests

Our unit tests should follow our style guide with respect to formatting, but we agree to be more lax about their coding quality and documentation compared to our main code base.  We agree that the most important thing is for our unit tests to correctly test their class or method, not necessarily to be elegant code.  A somewhat "hacky" looking test that does its job properly is acceptable.

## 4.4. Project Timeline

Below is a list of our two-week or one-week "sprints" over the course of the project.

<table>
  <tr>
    <td>Sprint</td>
    <td>Goals</td>
  </tr>
  <tr>
    <td>9-Feb - 23-Feb</td>
    <td>Suggest a few language ideas and pick the one the group finds most interesting
Agree on Words as our language
Write and submit whitepaper</td>
  </tr>
  <tr>
    <td>23-Feb - 9-Mar</td>
    <td>Pursue an overall goal of getting a simple "hello world" program to work (for learning, not necessarily with proper architecture)
Set up repository
Create simple GUI to display Words animations
Get a simple build process up and running for a Java project using jflex, byaccj, and make (later replaced with ant)
Implement a limited core of the grammar (for learning, not necessarily properly structured)
Refine language following whitepaper submission; get the main concepts/syntax to feel about 90% stable
Hold preliminary discussions about system architecture, e.g., all the main concepts in Words (classes, objects, listeners, etc.) are represented by objects in Java
Preliminarily investigate/propose testing architecture</td>
  </tr>
  <tr>
    <td>9-Mar - 16-Mar</td>
    <td>Continue to flesh out architecture plans
Write draft tutorial
Write draft reference manual
Implement draft grammar in jflex/byaccj and construct preliminary AST</td>
  </tr>
  <tr>
    <td>16-Mar - 23-Mar</td>
    <td>Finalize tutorial
Finalize reference manual
Finalize system architecture plans, e.g., how to handle dynamic types, how to walk the AST
Finalize testing architecture plans (JUnit tests and system tests)
Draft style guide</td>
  </tr>
  <tr>
    <td>23-Mar - 30-Apr</td>
    <td>Migrate existing preliminary code to final system architecture; placeholders for methods and classes not yet implemented
Agree on style guide
Reimplement “hello world” in new architecture
Implement relational operators, boolean operators, conditional statements
Use JUnit testing for the first time; ensure it is working
Clarify sticky points of the language that arose in prior two weeks (scope, listeners by class name, aliases)</td>
  </tr>
  <tr>
    <td>30-Mar - 6-Apr</td>
    <td>Begin development in earnest
Implement arithmetic operators
Implement iteration statements
Implement reference lists, property assignment
Use system testing framework for the first time</td>
  </tr>
  <tr>
    <td>6-Apr - 13-Apr</td>
    <td>Implement scope
Implement class creation and inheritance
Implement runtime control statements
Implement wait and property assignment basic actions
Implement event listeners for boolean predicates</td>
  </tr>
  <tr>
    <td>13-Apr - 20-Apr</td>
    <td>Implement event listeners for basic action predicates
Implement custom actions with correct scoping
Implement object removal and garbage collection
Improve GUI</td>
  </tr>
  <tr>
    <td>20-Apr - 27-Apr</td>
    <td>Develop sample programs
Draft and revise class presentation</td>
  </tr>
  <tr>
    <td>27-Apr - 11-May</td>
    <td>Deliver class presentation
Perform final cleanup of code base
Draft and finalize remaining sections of final report</td>
  </tr>
</table>


## 4.5. Project Log

See the Appendix for our git commit history.

# 5. Language Evolution

Written by Alex Mark (language guru)

## 5.1. Language Principles

It was clear from the beginning that our language would evolve throughout the semester. To make this evolution a positive process for our language, it was important to remember our three original principles described in the whitepaper, quoted here verbatim:

* *Words* should be as close to natural English as possible, with most constructs simply "making sense".  Even a person with no programming experience should be able to read and understand most or all of a *Words* program.

* *Words* should be expressive enough to allow users to define interesting behavior for their objects, explore their creativity, and fuel their interest in programming.

* *Words* is a "stepping stone" programming language.  The skills learned in *Words* should be transferrable, with a reasonable learning curve, to modern general purpose programming languages.

Each time an ambiguity or question about the language was brought up, we needed to refer back to these original principles. We would have to ask ourselves "What will make intuitive sense to the user?" and “Does that still give us enough power for the language to be interesting?” These questions applied to all syntax changes, as well as the semantics of how the language operated. Here are a few examples:

The syntax of our block structure changed very early on in the language’s evolution. Originally, a block started with a colon, statements within a block ended with semicolons, and the final statement of a block ended with a period. This syntax seemed analogous to English, and looked like this:

Whenever Fred's position > 0:

	Make Fred move down 1;

	Make Fred say "I'm moving down".

Whenever Fred's position > 0:

	Repeat 5 times:

	Make Fred move down 1;

	Make Fred say "I'm moving down"..

However, we soon realized that our language could have nested blocks, and that created a much stickier situation. Would we have two periods at the end of each block (in a very non-English like way)? We eventually decided that nested blocks were not very natural at all, and decided the best way to handle them was with traditional programming style brackets (used throughout this report).

More complex situations involved the semantic meanings of certain phrases. For instance, our grammar allowed conditional statements to be predicated on two objects touching each other. We had to decided what it meant when a programmer typed "Whenever Fred touches Bob". Originally, we had the most simple meaning in our program: the listener fired whenever Fred and Bob were at the same position. However, we soon realized that our programs were not acting as we thought they would. For instance, look at the below program. 

Whenever Fred touches Bob {

	Stop Fred.

	Make Fred say "Sorry".

	Make Fred move down.

}

When Fred and Bob intersect, we would expect Fred to stop, say "sorry", and move down. However, instead we saw Fred stop, say “sorry”, and keep saying “sorry”. This was caused by the listener repeatedly firing -- since Fred was still on the same location as Bob, they were still “touching” and Fred kept stopping and saying “sorry” -- he never got to the point where he would move away. 

Realizing that, while this fit our language guide, it did not meet our first principle that Words programs should make sense and be intuitive, we amended the definition of "touches" so the listener would only fire if at least one of the objects had moved in the last turn. Thus, Fred would now stop, say “sorry”, and then move down.

While this provides two brief examples of changes, many more changes were made to the language specification. We changed how object scoping worked, how we could alias objects in event listeners and custom actions, and much more. The process for these changes in described in section 5.3

## 5.2. Interpreter Components

The Words interpreter is not very special. It uses a combination of Lex and Yacc -- specifically, JFlex and BYACC/J -- to operate in a Java environment. The GUI on which the *Words* environment is displayed is programmed with Java’s AWT and Swing libraries. 

## 5.3. LRM-Interpreter Consistency and Updates

As described in 5.1, the language evolved while we were developing it. The following procedure was taken to make sure that the LRM and the interpreter were always consistent:

1. When a language update was suggested that would break standards with the LRM, an issue was filed in the GitHub issue tracker.

2. At the next team meeting, this language update would be discussed with the team while I, as language guru, held the final say.

3. If we decided to make the update, the update would be implemented and a Pull Request opened on Github. That pull request would reference that it breaks standards with the LRM.

4. When that Pull Request was merged to master, the LRM would be updated. 

However, we also needed to ensure that our entire implementation was in line with the LRM. To ensure that, we actively used the LRM while developing (i.e. the person implemented the addition operator would check the LRM to see what it was supposed to do), and our code review process, described in section 4.1, provided four other sets of eyes to make sure that the interpreter always matched the LRM. 

# 6. System Architecture

Written by James Young (system architect)

## 6.1. Words Architecture Block Diagram

![image alt text](image_0.jpg)

## 6.2. Input Thread 

The *Words* system was implemented in Java, so we utilized JFlex to implement our Lexical Analyzer, or Lexer, and utilized BYACC/J to implement the Syntactic Analyzer, or Parser. The Words.flex and Words.y files, fed into JFlex and BYACC/J respectively, can be found in the project code base.

Input into the *Words* environment is similar to that of a dynamic programming language, where it can be in the form of a source program or can perform interactions using REPL commands from the *Words* command line. The Input Thread passes all input into the Lexer which in turn passes the output token stream to the Parser. The parser produces an AST Statement List, which is the root level node of our system, and passes it from the Input Thread to the Frame Loop Thread. 

## 6.3. Frame Loop Thread

The Frame Loop runs at a rate of one frame per second by default, so the thread will hold off evaluating new AST Statement Lists created by the Input Thread by placing all AST Statement Lists that come into the thread between iterations of the Frame Loop in the AST Queue. The AST Queue is emptied into the Frame Loop at the beginning of each iteration, where each AST Statement List is evaluated in order of receipt.

The Frame Loop contains four primary functions, which are performed in the order listed on every iteration of the loop:

1. **Evaluate each AST**: Evaluate all AST Statement Lists currently in the AST Queue at the time the loop calls for the AST Queue to be emptied. Each AST is dequeued from the AST Queue and evaluated one at a time. All AST evaluations reflect interactions with and changes to the Runtime Environment. AST evaluations take the form of:

    1. class definitions

    2. object creation

    3. event listener activation

    4. push actions to an object’s action queue

    5. object property assignment

2. **Process actions**: The first action on each object’s action queue is dequeued and "performed" by the Runtime Environment. All visible actions are in the form of  “move” and/or “say” commands, so an object’s location values and/or current message are changed to reflect changes in the environment after an action is performed. 

3. **Check and run listeners**: All of the currently active listeners are checked to see if the conditions of the listener are met. If a listener’s condition is met, the environment evaluates the code from the listener and interacts with the Runtime Environment to reflect changes resulting from the listener. 

4. **Update the GUI**: Once all of the new AST Statement lists have been evaluated, actions have been performed and listeners have been checked, the Frame Loop will update the GUI to reflect the current state of the system. 

## 6.4. Communication with Runtime Environment

The most important implementation of the Frame Loop Thread is the constant communication with the *Words* Runtime Environment. Throughout all steps of the Frame Loop, there are changes that are being made to the environment and also information that is being retrieved from the environment. This is crucial in order to maintain data consistency and correctness, as well as maintaining the main functionality of the *Words* language which is based on visual output. Every property of every object must be accounted for and correctly represented in order to provide expected output.

# 7. Development Environment and Runtime

Written by Michael Ben-Ami (system integrator)

## 7.1. Software Development Environment

The *Words* interpreter is written in Java and runs on the Java Virtual Machine (JVM). The Java code is organized as a collection of packages: words, words.ast, words.environment, words.exceptions, and words.test. 

The classes in the words package coordinate the interaction of major subsystems in the interpreter. For example, the lexer and parser exist in this package, and the intermediate code generated by the parser (ASTs) is passed to the *Words* runtime environment through classes in this package. The words package also contains the GUI, and controls the interaction between the GUI and the *Words* runtime environment. 

The words.ast package contains classes that each represent a type of node that can be found in an abstract syntax tree. They inherit from a base abstract class AST and implement the abstract method eval(), which evaluates a subtree with the given node as the root. Calls to eval() often lead to a post-order chain of eval() calls through the AST and produce side-effects in the *Words* runtime environment.

The words.environment package contains classes that make up the *Words* runtime environment, which is discussed in more detail in section 7.4.

words.exceptions and words.test contain runtime exceptions and JUnit tests, respectively. Exceptions are thrown (and caught) when a user violates the semantics given by the Reference Manual. Syntax errors are caught by the parser.

## 7.2. Build Environment

We use Apache Ant for building. The build file is called build.xml and its contents are below. Some targets in the build file include:

* jar (default): creates executable jar file from compiled Java classes

* compile: compiles Java source files into JVM bytecode

* grammar: runs the parser and lexer to generate some Java source files

* run: runs the *Words* interpreter

* test: runs JUnit and system-level tests

**build.xml**

<project name="Words" default="jar">

    <!-- variables, access via ${} -->

    <property name="src.dir" location="src/words" />

    <property name="bin.dir" location="bin" />

    <property name="jar.dir" location="jar" />

    <property name="lib.dir" location="lib" />

    <property name="test.dir" location="test/words/test" />

    <property name="bin.test.dir" location="bin/words/test" />

    <property name="test.report.dir" location="report" />

    <property name="system_test.dir" location="system_test" />

    <!-- classpath for junit -->

    <path id="junit.classpath">

        <pathelement location="${lib.dir}/junit-4.12.jar" />

        <pathelement location="${lib.dir}/hamcrest-core-1.3.jar" />

        <pathelement location="${bin.dir}" />

        <pathelement location="${bin.test.dir}" />

    </path>

    <!-- os condition -->

    <condition property="isWindows">

        <os family="windows" />

    </condition>

    <!-- lex and yacc -->

    <target name="grammar" depends="grammar-windows, grammar-other" />

    <target name="grammar-windows" if="isWindows">

        <exec executable="yacc" dir="${src.dir}">

            <arg value="-J" />

            <arg value="-Jclass=Words" />

            <arg value="-Jpackage=words" />

            <arg value="Words.y" />

        </exec>

        <exec executable="cmd" dir="${src.dir}">

            <arg value="/c" />

            <arg value="jflex.bat" />

            <arg value="Words.flex" />

        </exec>

    </target>

    <target name="grammar-other" unless="isWindows">

        <exec executable="yacc" dir="${src.dir}">

            <arg value="-J" />

            <arg value="-Jclass=Words" />

            <arg value="-Jpackage=words" />

            <arg value="Words.y" />

        </exec>

        <exec executable="jflex" dir="${src.dir}">

            <arg value="Words.flex" />

        </exec>

    </target>

    <!-- compile program java code -->

    <target name="compile" depends="grammar">

        <mkdir dir="${bin.dir}" />

        <javac srcdir="${src.dir}" destdir="${bin.dir}" debug="true" includeantruntime="false">

            <classpath>

                <pathelement location="${lib.src}/slick.jar" />

                <pathelement location="${lib.src}/lwjgl.jar" />

            </classpath>

        </javac>

    </target>

    <!-- compile junit testing code -->

    <target name="test-compile" depends="grammar, compile">

        <mkdir dir="${bin.test.dir}" />

        <javac srcdir="${test.dir}" destdir="${bin.test.dir}" includeantruntime="false">

            <classpath refid="junit.classpath" />

        </javac>

    </target>

    <!-- build jar -->

    <target name="jar" depends="compile">

        <mkdir dir="${jar.dir}" />

        <jar destfile="${jar.dir}/Words.jar" basedir="${bin.dir}">

            <manifest>

                <attribute name="Main-Class" value="words/Words" />

            </manifest>

        </jar>

    </target>

    <!-- run program -->

    <target name="run">

        <java jar="${jar.dir}/Words.jar" fork="true" />

    </target>

    <!-- run JUnit tests and integration tests -->

    <!-- report is plain, could also be xml -->

    <target name="test-unit" depends="clean, test-compile">

        <mkdir dir="${test.report.dir}" />

        <junit printsummary="on" fork="true" haltonfailure="true">

            <assertions>

                <enable />

            </assertions>

            <classpath refid="junit.classpath" />

            <formatter type="plain" />

            <batchtest todir="${test.report.dir}">

                <fileset dir="${bin.test.dir}">

                    <include name="**/*Test*.class" />

                </fileset>

            </batchtest>

        </junit>

    </target>

    <!-- run system tests -->

    <target name="test-system" depends="clean, jar, test-system-windows, test-system-other" />

    <target name="test-system-windows" if="isWindows">

        <apply executable="${system_test.dir}/test.bat" failonerror="true">

            <fileset dir="${system_test.dir}" includes="*.words" />

        </apply>

    </target>

    <target name="test-system-other" unless="isWindows">

        <apply executable="${system_test.dir}/test.sh" failonerror="true">

            <fileset dir="${system_test.dir}" includes="*.words" />

        </apply>

    </target>

    <target name="test" depends="clean, test-unit, test-system">

    </target>

    <!-- delete existing build -->

    <target name="clean">

        <delete dir="${bin.dir}" />

        <delete dir="${jar.dir}" />

        <delete dir="${bin.test.dir}" />

        <delete dir="${test.report.dir}" />

        <delete file="${src.dir}/Words.java" />

        <delete file="${src.dir}/WordsVal.java" />

        <delete file="${src.dir}/Yylex.java" />

        <delete file="${src.dir}/Yylex.java~" />

        <delete file="run.log.tmp" />

    </target>

</project>

## 7.3. Lexer and Parser

To generate a parser and a lexer written in Java, we used BYACC/J and JFlex, respectively. In our case, JFlex reads the lexer specification, written in lex syntax, from Words.flex, and outputs to Yylex.java. BYACC/J reads the grammar specification, written in yacc syntax, from Words.y, and outputs to Words.java, which calls methods defined in Yylex.java to read in tokens. Words.java also contains the main() method for the interpreter as a whole, which is written in the "code" section of Words.y.

## 7.4. *Words* Runtime Environment

This section gives a brief overview of important classes and collections in the words.environment package. The state of the environment is kept in the Environment class, which maintains collections of *Words* classes (WordsClass), *Words* objects (WordsObject), *Words* scopes (Scope), and *Words* event listeners (WordsEventListener).

The collection of WordsClass objects is implemented as a HashMap<String, WordsClass> which maps class names to WordsClass objects. This implementation is useful for constant-time semantic checks to see if a *Words* class name is already in use or hasn’t been declared yet, which come into play during *Words* class and *Words* object creation.

The collection of WordsObject objects is implemented as a HashMap<WordsClass, HashSet<WordsObject>>. This implementation if useful for performing bulk operations on *Words* objects of a given *Words* class, such as when an event listener is defined to listen for the satisfaction of predicates describing all the objects of one or more *Words* classes.

The collection of Scope objects is implemented as a LinkedList<Scope>, which acts a stack, and where a Scope object contains a HashMap<String, Property> that contains bindings from names to Words properties. The stack is used to follow the control flow of the interpreter, such that the current scope is always at the top of the stack. Each Scope object also has a link to its parent.

The collection of WordsEventListener objects is implemented as an ArrayList<WordsEventListener>. This implementation is useful for simple iteration through each *Words* event listener to evaluate its predicates. This evaluation happens at each iteration of the frame loop.

# 8. Test Plan and Scripts

Written by Wangda Zhang (tester)

During the development of *Words*, every programmer worked on a component of the project. In order to keep the code quality high, we have to perform extensive tests to ensure that desired functionalities are implemented and side effects are kept minimal. Broadly speaking, we use two levels of testings in this project: unit/integration testing, and system testing. We developed a comprehensive test suite integrated with the Apache Ant (NOTE:  http://ant.apache.org) tool to automate all these testings. In the following, we discuss our test methodologies in detail.

## 8.1. Unit/Integration Testing

The purpose of this type of testing is to ensure that a small piece of code (e.g. a method) works as expected. We use the JUnit (NOTE:  http://junit.org) framework to test our Java code. For unit testing, we want to verify that the functionality of a particular code section against its design specification; for integration testing, we want to verify that the integration of multiple components through their interfaces works in our expected ways. However, we do not specify a clear-cut boundary between these two levels of testing, since in many cases code sections to be tested are tightly involved with other code. For example, in order to test the evaluation of a simple AST node, there is unlikely to be a way to avoid invoking code that interacts with the environment contexts.

We require that every public method should be tested to meet its specification, and every desired exception should be thrown. These tests can be implemented in Junit with assertions and expected exceptions. We also developed many edge cases to help us identify potential bugs in our code, which is proved to be very effective through our code review.

The benefit of unit testing is to allow us to quickly locate questionable code fragments, and to easily construct specific tests. The following are some sample test cases.  (The appendix has all of the test cases.)

1. a simple test for rounding position parameters to integers:

public class TestPosition {

	@Test

	public void testDoubleParams() {

		Position pos = new Position(3.7, 5.2);

		assertEquals("X position correctly rounded", pos.x, 4);

		assertEquals("Y position correctly rounded", pos.y, 5);

	}

}

2. a test for enqueuing say actions, involving interactions with the environment:

public class TestINodeQueueSay extends TestINode {

  @Test

  public void testWorkingSay() throws WordsRuntimeException {

    environment.createObject("Fred", "thing", new Position(0, 0));

    loop.fastForwardEnvironment(1);

    assertEquals("Initially no message",

environment.getVariable("Fred").objProperty.getCurrentMessage(), null);

    AST idLeaf = new LNodeIdentifier("Fred");

    AST strLeaf = new LNodeString("Hello World");

    INode testNode = new INodeQueueSay(new INodeReferenceList(), idLeaf, strLeaf, null);

    testNode.eval(environment);

    loop.fastForwardEnvironment(1);

    assertEquals("New message assigned", environment.getVariable("Fred").objProperty.getCurrentMessage(), "Hello World");

	}

}

3. a test for throwing expected exception in case of type errors:

public class TestSayAction extends TestINode {

  @Test (expected = WordsProgramException.class)

  public void onlyOperatesOnStringsAndNumbers() throws WordsRuntimeException, WordsProgramException {

    environment.createObject("Fred", "thing", new Position(0, 0));

    Action action1 = new SayAction(environment.getCurrentScope(), nothingLeaf);

    action1.execute(environment.getVariable("Fred").objProperty, environment);

    Action action2 = new SayAction(environment.getCurrentScope(), trueLeaf);

    action2.execute(environment.getVariable("Fred").objProperty, environment);

    }

}

## 8.2. System Testing

We use system testing to verify that a *Words* program meet its specifications end-to-end. For every language feature, we have to make sure that given a *Words* program input, we can get the expected 2D animation output. But a challenge here is we cannot easily compare two animations, then how can we test such visual output?

To solve this problem, we developed a logging technique. For every frame, we print all the objects and their related information to a log file, and in the future, we only need to compare the logs without eyeballing the animation outputs. The following figure shows the organization of our log files, where each object is logged with its class name, location, and saying messages.

![image alt text](image_1.png)

This logging method gives us many benefits. First, it is easy to diff text logs with existing tools. Second, it is much faster to generate the logs without actually displaying the animation, because the one-second delay between two consecutive frames is saved. Third, this method also allows us to test programs with errors in them. We just need to log the syntax errors and runtime errors at the same time.

We write *Words* programs to perform system tests. The following are two sample test cases:

1. a test program for listeners with boolean predicates:

Fred is a thing at 0,-5.

John is a thing at 0,5.

Kevin is a thing at 1,0.

As long as Fred's row < 5 { Make Fred move up. }

As long as John's row > -5 { Make John move down. }

Whenever Fred's row = John's row { Make Kevin say "Mooooo!!". }

2. a test program with a syntax error (missing ending dot) and runtime errors (undefined object and missing property) in it:

Fred is a thing at 1,1.

Alex is a thing

Alex is a person at 0,0.

Make Fred's friend move up.

## 8.3. Automated Test Suite

To make it easy for us to run a lot of tests, we developed an automated test suite, which runs on both Windows and Unix systems. We organize unit tests and system tests in separate directories, and use both Apache Ant and customized scripts to automate the testing process. By simply typing "ant test", we can obtain detailed test reports as below. In case there is any error, the testing will stop immediately for us to investigate the problem.

![image alt text](image_2.png)

This automatic test suite is of great importance for us to develop high-quality code. We require that all tests have to be performed and passed before any code is committed to our master branch. In this way, we keep the master branch having as few bugs as possible.

As a summary, we have implemented over 200 unit tests, developed over 3,000 lines of testing code, and built more than 15,000 lines of logs. Our tests cover a large fraction of our entire code base, ensure that our implementation meets the language specification, and make our development process much easier.

# 9. Conclusions

## 9.1. Team Lessons Learned

Below are some of the key lessons we learned as a team:

* Everyone can bring valuable perspective and insights to the table.  Some of the language and architecture decisions we made were much better off because we talked about them as a team before deciding what to do (even though one team member was often given the final say).

* A team is way more productive when the team members have a clear structure to work within.  For example, early on we developed a common standard for AST nodes.  With that, it was a simple matter to divide up work and have each team member flesh out the implementation of different types of nodes.

* We all know it’s important to document our code on a single-person project (to remind ourselves later why you did what we you did), but we learned that it’s even more important on a team project.  Documentation helps you understand what the code is meant to do, which is even more critical when you didn’t write it yourself and have no memories to draw upon.  The documentation also helps you both review other people’s code better and know how to use their public methods correctly.

* We should have had food at our meetings!  Food makes everything more fun and would have helped keep our energy levels up during some of our longer meetings.

## 9.2. Individual Lessons Learned

### 9.2.1. Project Manager: Andrew Kallem

I learned many things during this project.  Here are two of the most important.

First, I’m now a convert to unit testing.  When we first considered using JUnit, I was skeptical that it would provide any value beyond the system testing we were already planning to do, namely, writing sample *Words* programs and then ensuring their output stays the same.  Writing unit tests seemed like it would be more time consuming, which indeed it was.  But I learned that when you do break something, the investment in unit tests is well worth it, because the failed unit tests clearly identify what the issue is and how to resolve it.  By contrast, merely knowing that the output of a *Words* program changed gives little direction toward understanding or fixing the problem.

Second, I learned how valuable code review can be.  This project was my first experience giving and receiving reasonably thorough (as opposed to perfunctory) code review, and it was helpful every time.  Sometimes it caught little bugs, sometimes it caught bigger ones, and sometimes it just enforced good coding practices.  Indeed, I took away a number of Java programming tips by having others review my code.

### 9.2.2. Language Guru: Alex Mark

During the course of this project, I learned many things, but two lessons stood out.

First, I learned the importance of following standards instead of trying to completely develop new patterns. When we first started the project, we had a three hour architecture meeting where we developed a "Command" class that acted as a statement in a program, but it had a huge number of implementation failures and pieces that were not thought out. We had yet to learn about ASTs in class, but it quickly became clear that this original architecture was baloney, and that we needed to actually form an abstract syntax tree. 

Second, I learned a great deal of things about creating a reasonable-sized codebase with a team of four other people. It was important for me to stay completely up to date with the code base -- I read all pull requests and merges into master, and this allowed me to always know how the language was implemented at any time, and made me doubt less when implementing features. The code base only reached around 7500 lines of code, and we generally had around a thousand lines of changes a week, so it was not impossible to keep up with. Additionally, it was important to keep all code quality high -- I would have to use code written by other people, and they would have to use code written by me, so bad code was not simply the problem of the person writing it -- it was a problem for the entire team. 

### 9.2.3. System Architect: James Young

I can say with absolute confidence that this project has been the largest and most rewarding project that I have ever been a part of, inside or outside of the classroom. I have learned so much from this project, but I would like to talk about the two lessons I find the most important.

The first lesson I learned was the importance of team cohesion and communication. The team was in constant contact with one another throughout every phase of this project, which lead us to a better understanding of our project parameters and increased productivity. Everyone knew what they needed to work on in order to make the project a success and we were always able to depend on each other for assistance when we ran into issues with whatever we were working on. 

The second lesson I found to be of major importance was the role of code testing and review.

We tried to write tests for all of the code we wrote in order to make sure that every part of our system was functional. This made it much easier to debug features when we knew where to look for problems. Code review was also a large part of the project as we tried to have every member of the team look over the code of a branch before we merged it into our master branch. This made it easier for all of us to make sure that everyone followed our style guide and to debug each others code.

### 9.2.4. System Integrator: Michael Ben-Ami

This project was probably my most successful team venture in academia. What seemed incredibly daunting at the beginning of the semester became more and more manageable each week. Suddenly there was this larger than life thing that we had created. So I really got the power of a team. On other teams I’ve worked on, we’ve produced work faster than any of us would have as an individual. But it seems like Words could only have worked as a team project. It wasn’t just faster for multiple things to be developed in parallel, it was necessary because of interconnectedness of modules. Code reviews are necessary. They catch bugs, increase quality, and most importantly, accelerate personal development in programming. I am also a unit-testing convert.

Our team management was exceptional. Our project manager, Andrew, kept us on track and constantly moving forward. It was clear from the beginning that he would be a strong leader, and we would take cues from him. I’ve learned that having a designated leader really works if it’s done right. Other group projects without designated leaders often have teammates being overly diplomatic with each other, not wanting to step on each other’s toes, and not having open or detailed discussions of what is expected of each member. On the other hand, allowing someone to "manage" you doesn’t sound attractive either. Andrew was so willing to put in the “dirty work” and to help out team members to make sure everybody was included and participating in what they wanted to work on, that I was more than willing to do anything he asked. The next time I am leading a project, this will be the model I will try to follow.

### 9.2.5. Tester: Wangda Zhang

I consider this project as a good integration of theory and practice. I find that I understand the theory of programming language and compilers much better when I actually apply it to build some real applications, since there are still many details I have to pay attention to in practice. Also, real world is not always perfect as in theory and at times we have to make numerous engineering tradeoffs, especially in our process of designing a new language. To make things work, we need a lot of communications and compromises.

I also learned the importance of good management, thorough testing, and code review in developing complex softwares. Without any of these, a project with many developers could easily become a mess even if it is a smaller course project at school. In addition, it is crucial to set appropriate goals so that a team can make steady progress every week.

## 9.3. Advice for Future Teams

Here are some pieces of advice we would give future teams:

* Start coding early, but not *too* early.  A good rule of thumb is, once you’ve learned about it in class, start coding it then.  Conversely, if you haven’t learned about it in class yet, wait to code it until you have (although you may give it a try earlier in the interest of getting your full system up and running, though you may have to refactor it later).

* Meet in person at least every week.  Sometimes the meetings will be long, and sometimes they will be short, but they’re valuable either way.  If you meet less frequently than once a week, the project can easily slip away from you.

* Make steady, incremental progress every week.

* Make code review a formal part of your process.  Don’t think of it as implying that you doubt one another’s abilities, think of it as a learning opportunity and a form of quality control.  Your code base will be much better for it, and you will learn a lot.

* It’s better to communicate too much than to communicate too little.

* Don’t treat the project and your language as a democracy.  Resist the temptation to get everyone’s complete buy-in on every decision.  Discuss major decisions as a team, but ultimately let one person decide (depending on what the decision is) and move on.

* Don’t be afraid to refactor code you’ve already written if you realize it’s necessary.  Investing time now in getting your architecture right will save you time later.

* If your team is having a problem (e.g., with team dynamics, with the timeline, with the concepts), the worst thing you can do is ignore it.  Call it out early and honestly and try to resolve it.

## 9.4. Suggestions for the Instructor

Here are some suggestions we would offer the instructor about the course as a whole:

* For some of the major concepts (LL(1) parsing, SLR parsing, flow graphs, code optimization, beta reductions in lambda calculus), do at least two examples in class for each concept rather than just one.  Sometimes a single example isn’t enough to understand a concept until its presented with another for comparison.  This is especially important for some concepts where the wording of the definition takes some thinking to even understand (e.g., FIRST and FOLLOW, handles, viable prefixes).

* For the review session lecture, instead of reviewing the homework solutions, let students vote (perhaps in advance) on what topics they want to cover.  Students who want to review the homework solutions can do that separately with the TAs during office hours.

* Make the homeworks worth more.  It’s a shame they’re only worth 10% of the grade considering how time-consuming they can be.

* Some of the core concepts can seem similar at first glance, e.g., parse trees versus abstract syntax trees, SDDs versus 	SDTSs, three address code versus machine code.  It would be helpful to call out more explicitly the fact that while they may appear similar, they have important differences (in definition and in use).

# 10. Appendix

## 10.1. Git Log

Below is the complete git log of our project.  The number of commits per team member should not be taken as a reflection of the amount of work done, since some of us preferred to separate our work into multiple commits while others tended to squash their work into a single commit, and some commits entailed much more work than others.

2015-05-09: Merge pull request #142 from Aalk4308/predicate-move-fix (Alex Mark)

2015-05-09: Merge pull request #143 from Aalk4308/minor-doc-fix (Aalk4308)

2015-05-09: Fix some documentation (Andrew Kallem)

2015-05-08: Make Andrew happy (Alex Mark)

2015-05-08: Overall superior implementation (Alex Mark)

2015-05-08: Merge branch 'master' of github.com:Aalk4308/Words into predicate-move-fix (Alex Mark)

2015-05-08: Fix bug about listener not firing (Alex Mark)

2015-05-06: Merge pull request #141 from Aalk4308/misc-cleanup (Aalk4308)

2015-05-05: Fix object creation adjacency bug (Alex Mark)

2015-05-05: Move move predicate logic to WordsObject class, fire predicate on row or column assignment (Alex Mark)

2015-05-04: Consolidated display of REPL prompt (Andrew Kallem)

2015-05-04: Minor documentation improvements (Andrew Kallem)

2015-05-04: Remove prepareForRemoval() to flagForRemoval() (Andrew Kallem)

2015-05-04: Made consistent the use of InvalidTypeException (Andrew Kallem)

2015-05-04: Harmonized exception messages (Andrew Kallem)

2015-05-04: Renamed TestWordsPosition to TestPosition (Andrew Kallem)

2015-05-04: Do not allow properties to be assigned within an object creaton statement (Andrew Kallem)

2015-05-04: Remove unused imports (Andrew Kallem)

2015-04-27: Merge pull request #138 from Aalk4308/remove-missing-obj-fix (Aalk4308)

2015-04-27: Merge pull request #137 from Aalk4308/remove-fix (mzbenami)

2015-04-27: Merge pull request #136 from Aalk4308/speed-option (Aalk4308)

2015-04-27: Throw exception if attempt to remove an object that does not exist (Andrew Kallem)

2015-04-27: Added unit test for trying to remove missing object (Andrew Kallem)

2015-04-27: Fix remove bug (Alex Mark)

2015-04-27: Added simple command line option to control speed (Andrew Kallem)

2015-04-27: Merge pull request #134 from Aalk4308/turing-machine (Aalk4308)

2015-04-27: Added Turing Machine sample program (Andrew Kallem)

2015-04-27: Merge pull request #131 from Aalk4308/delayed-removal (Alex Mark)

2015-04-27: Merge branch 'master' into delayed-removal (Alex Mark)

2015-04-27: Merge branch 'master' of github.com:Aalk4308/Words (Alex Mark)

2015-04-27: Fix a test case (Alex Mark)

2015-04-27: Merge pull request #130 from Aalk4308/fix-action-queue-bug (Alex Mark)

2015-04-27: Merge branch 'master' into fix-action-queue-bug (Alex Mark)

2015-04-27: Merge pull request #128 from Aalk4308/more-object-props (Alex Mark)

2015-04-27: Merge pull request #125 from Aalk4308/sample-programs (Alex Mark)

2015-04-27: Merge pull request #121 from Aalk4308/test-adjacency (Alex Mark)

2015-04-27: Merge pull request #120 from Aalk4308/creation-wait-fix (Alex Mark)

2015-04-27: Merge pull request #119 from Aalk4308/fix-touches (Alex Mark)

2015-04-27: Fix adjacency predicate to work on object creation (Alex Mark)

2015-04-27: Add Prof Aho sample. (Alex Mark)

2015-04-27: Make objects touch when created (Alex Mark)

2015-04-25: more Unit tests (jtyoung1292)

2015-04-25: combined if block for special cases (jtyoung1292)

2015-04-25: Merge branch 'master' into delayed-removal (Andrew Kallem)

2015-04-25: Merge branch 'fix-removal-bug' into delayed-removal (Andrew Kallem)

2015-04-25: Merge branch 'master' into fix-action-queue-bug (Andrew Kallem)

2015-04-25: Merge pull request #127 from Aalk4308/whitespace-syntax-errors (Aalk4308)

2015-04-25: Merge pull request #126 from Aalk4308/gui-left-right-flip (Aalk4308)

2015-04-25: Merge pull request #124 from Aalk4308/pronoun-fixes (Aalk4308)

2015-04-25: Merge pull request #123 from Aalk4308/argument-scope (Aalk4308)

2015-04-25: Merge pull request #122 from Aalk4308/fix-eval-equals (Aalk4308)

2015-04-25: Change removal to go through every scope on the stack and not remove by the object's name, since an object may appear in the stack under an alias. (Andrew Kallem)

2015-04-25: Merge branch 'fix-action-queue-bug' into delayed-removal (Andrew Kallem)

2015-04-25: Merge remote-tracking branch 'origin/fix-touches' into fix-action-queue-bug (Andrew Kallem)

2015-04-25: Objects should be removed from the stack not based on their name (which may differ from the variable name in teh current scope) but by their value as an object itself (Andrew Kallem)

2015-04-25: added tests for name and class properties (jtyoung1292)

2015-04-25: Fixes bug in action queue in cases where the statements within a custom action are only immediate statements (Andrew Kallem)

2015-04-25: WordsObject now throws new exception when user tries to modify name or class (jtyoung1292)

2015-04-25: edits to name and class properties (jtyoung1292)

2015-04-25: Flag objects for removal but do not remove until the end of each phase of the frame loop (Andrew Kallem)

2015-04-24: name and class properties added (jtyoung1292)

2015-04-24: Replace tabs with spaces when displaying syntax error so that the carat lines up correctly (Andrew Kallem)

2015-04-24: Flip the left and right buttons in the GUI (Andrew Kallem)

2015-04-24: Merge branch 'fix-eval-equals' into sample-programs (Andrew Kallem)

2015-04-24: Added unit tests to compare objects for equality (Andrew Kallem)

2015-04-24: Fix bug in eval equals when comparing two objects that are not the same (Andrew Kallem)

2015-04-24: Added additional players to multiplayer marco polo (Andrew Kallem)

2015-04-24: Merge branch 'argument-scope' into sample-programs (Andrew Kallem)

2015-04-24: Fix bug where arguments to a custom action were being evaluated in the scope of the custom action where they need to be pre-evaluated in the scope of the caller (Andrew Kallem)

2015-04-24: Merge branch 'pronoun-fixes' into sample-programs (Andrew Kallem)

2015-04-24: Fix minor issues in grammar and custom actions with pronoun capitalization and priority in lexer (Andrew Kallem)

2015-04-24: Added first version of multiplayer Marco Polo (Andrew Kallem)

2015-04-24: Merge branch 'master' into sample-programs (Andrew Kallem)

2015-04-24: Updated unit tests and system tests for adjacency predicates (Andrew Kallem)

2015-04-24: Merge branch 'fix-touches' of https://github.com/Aalk4308/Words into test-adjacency (Andrew Kallem)

2015-04-24: Fix same bug in adjacency (Alex Mark)

2015-04-24: Merge branch 'fix-touches' of https://github.com/Aalk4308/Words into test-adjacency (Andrew Kallem)

2015-04-24: Undo what I just did. Null instaceof object returns false (Alex Mark)

2015-04-24: Merge branch 'creation-wait-fix' into fix-touches (Alex Mark)

2015-04-24: Fix potential null pointer exception (Alex Mark)

2015-04-24: Added unit tests for INodeAdjacencyPredicate (Andrew Kallem)

2015-04-24: Use a fake wait flag instead of an actual wait action after each object is created (Alex Mark)

2015-04-24: Make touches only fire when the objects move to touch (Alex Mark)

2015-04-24: Fix a comment directly on master (Alex Mark)

2015-04-24: Merge pull request #115 from Aalk4308/custom-actions (Alex Mark)

2015-04-24: Fix merge conflicts (Alex Mark)

2015-04-24: Merge pull request #114 from Aalk4308/test-error (Alex Mark)

2015-04-24: Merge pull request #113 from Aalk4308/object-to-string (Alex Mark)

2015-04-24: Merge pull request #112 from Aalk4308/adjacency-predicates (Alex Mark)

2015-04-24: Merge pull request #111 from Aalk4308/remove (Alex Mark)

2015-04-24: Change custom action test to use inheritence (Alex Mark)

2015-04-24: integrated buildlog (Wangda)

2015-04-24: add logging for errors (Wangda)

2015-04-22: added JUnit test (jtyoung1292)

2015-04-22: object to string for string concatenation implemented (jtyoung1292)

2015-04-22: Updated RemoveTest to use listeners so objects aren't deleted immediately, and included log in the commit (Andrew Kallem)

2015-04-21: safer hashmap iteration in clearRefererers() (Michael Ben-Ami)

2015-04-21: moved updateReferers and clearReferers further down in WordsObject class (Michael Ben-Ami)

2015-04-21: got rid of unnecessary null checks and class list iteration (Michael Ben-Ami)

2015-04-21: Merge branch 'refactor-scope' into custom-actions (Andrew Kallem)

2015-04-21: junit tests added (Michael Ben-Ami)

2015-04-21: looks good, need unit test (Michael Ben-Ami)

2015-04-20: Renamed unit test files for actions (Andrew Kallem)

2015-04-20: Modified system test for custom actions to include multiple arguments (Andrew Kallem)

2015-04-20: Added unit tests for scope-related methods in Environment (Andrew Kallem)

2015-04-20: Handle duplicate custom action definition names and inheritance of custom action lookups (Andrew Kallem)

2015-04-20: Improve documentation of environment package classes and methods (Andrew Kallem)

2015-04-20: Fix all unit tests to include scope in actions.  Add system tests for custom actions and scope. (Andrew Kallem)

2015-04-20: Retain scope in action so that it is available (as a closure) when it executes (Andrew Kallem)

2015-04-20: basic removal, ref list broke (Michael Ben-Ami)

2015-04-20: Refactored scope to be a separate class (Andrew Kallem)

2015-04-20: Remove extraneous debug statement (Andrew Kallem)

2015-04-20: Merge branch 'master' into sample-programs (Andrew Kallem)

2015-04-20: Updated implementation of custom actions (Andrew Kallem)

2015-04-20: Separate CustomActionDefinition from CustomAction (Andrew Kallem)

2015-04-20: Refactored grammar to use the terms arguments and parameter properly.  Added system test for custom actions (Andrew Kallem)

2015-04-20: use a loop instead of repeating the same statement 7 times (Alex Mark)

2015-04-20: custom actions work. No tests, no parameters, no inheritence (Alex Mark)

2015-04-20: Fix remaining test errors (Andrew Kallem)

2015-04-20: Fix all but one test errors (Andrew Kallem)

2015-04-20: Fix most test errors (Andrew Kallem)

2015-04-20: Refactored getObject to getVariable.  Tests not yet refactored (Andrew Kallem)

2015-04-20: Preparatory refactoring of objectsByName into variableS (Andrew Kallem)

2015-04-20: Merge branch 'master' into adjacency-predicates (Andrew Kallem)

2015-04-20: Merge pull request #104 from Aalk4308/implement-stop (Aalk4308)

2015-04-20: Update Direction in TestINodeQueueStop (Andrew Kallem)

2015-04-20: Merge remote-tracking branch 'origin/implement-stop' into implement-stop (Andrew Kallem)

2015-04-20: Merge pull request #105 from Aalk4308/gui-colors-zoom (Alex Mark)

2015-04-20: Merge pull request #106 from Aalk4308/refactor-direction (Alex Mark)

2015-04-20: Added unit test for adjacency method in Position (Andrew Kallem)

2015-04-20: First implementation of adjacency predicate (Andrew Kallem)

2015-04-20: Merge branch 'refactor-direction' into adjacency-predicates (Andrew Kallem)

2015-04-19: Never attempt to display more than a fixed number of objects on a cell (Andrew Kallem)

2015-04-19: Merge pull request #107 from Aalk4308/refactor-direction-2 (Aalk4308)

2015-04-19: changed clearQueue to clearActionQueue and fixed error (jtyoung1292)

2015-04-19: Refactor Direction to not use Type (Alex Mark)

2015-04-19: Merge branch 'refactor-direction' into sample-programs (Andrew Kallem)

2015-04-19: Refactored Direction class to not be an object but just provide a static enum (Andrew Kallem)

2015-04-19: First version of Marco Polo sample program (Andrew Kallem)

2015-04-19: Merge remote-tracking branch 'origin/implement-stop' into sample-programs (Andrew Kallem)

2015-04-19: Modified sample programs (Andrew Kallem)

2015-04-19: Renamed sample program files (Andrew Kallem)

2015-04-19: Merge branch 'master' into sample-programs (Andrew Kallem)

2015-04-18: Better rendering of objects in the GUI when they are on the same position (Andrew Kallem)

2015-04-18: Handle newline escape correctly and display it correctly in GUI (Andrew Kallem)

2015-04-18: Merge branch 'master' into gui-colors-zoom (Andrew Kallem)

2015-04-18: JUnit test (jtyoung1292)

2015-04-18: Stop command implemented (jtyoung1292)

2015-04-18: Merge pull request #96 from Aalk4308/basic-action-predicate (Alex Mark)

2015-04-18: Render each object with a random but reproducible background color (Andrew Kallem)

2015-04-18: Scale font sizes in GUI based on zoom, and minor updates to placement (Andrew Kallem)

2015-04-17: Add while loop (Alex Mark)

2015-04-17: Include missing file (Alex Mark)

2015-04-17: JUnit test update (jtyoung1292)

2015-04-17: Incorporate PR comments (Alex Mark)

2015-04-17: check for boolean argument not needed (jtyoung1292)

2015-04-17: Get rid of all warnings, use nicer imports (Alex Mark)

2015-04-17: first test for while passes (jtyoung1292)

2015-04-17: JUnit test got while (jtyoung1292)

2015-04-17: AST done (Alex Mark)

2015-04-17: Add system test (Alex Mark)

2015-04-17: Merge pull request #88 from Aalk4308/class-create (Alex Mark)

2015-04-17: Fix grandchild bug (Alex Mark)

2015-04-17: Fix merge conflict (Alex Mark)

2015-04-17: We have working listeners with aliases. Tests are inadequate (Alex Mark)

2015-04-17: JUnit tests (Michael Ben-Ami)

2015-04-17: Make listeners use aliases (Alex Mark)

2015-04-17: andrew's refactoring, grammar change (Michael Ben-Ami)

2015-04-17: while loop implemented (jtyoung1292)

2015-04-17: fixed conflicts (Michael Ben-Ami)

2015-04-17: Fix naming error (Alex Mark)

2015-04-17: Merge branch 'master' of github.com:Aalk4308/Words into basic-action-predicate (Alex Mark)

2015-04-17: Merge pull request #94 from Aalk4308/better-syntax-errors (Alex Mark)

2015-04-17: Fix merge conflict (Alex Mark)

2015-04-17: Merge pull request #92 from Aalk4308/listener-creation (Alex Mark)

2015-04-17: Remove Windows line endings from BooleanListenerTest log (Andrew Kallem)

2015-04-17: Fix dumb merge conflict (Alex Mark)

2015-04-17: Merge branch 'listener-creation' of github.com:Aalk4308/Words into listener-creation (Alex Mark)

2015-04-17: Merge pull request #89 from Aalk4308/implement-wait (Alex Mark)

2015-04-17: Change grammar to no longer use turns, create system test (Alex Mark)

2015-04-17: Merge branch 'implement-wait' of github.com:Aalk4308/Words into implement-wait (Alex Mark)

2015-04-17: Merge pull request #87 from Aalk4308/queue-prop-assign (Alex Mark)

2015-04-17: Merge pull request #84 from Aalk4308/grammar-block-restrictions (Alex Mark)

2015-04-17: Check in test code (Alex Mark)

2015-04-17: Make event listeners work for all subjects, tests still needed (Alex Mark)

2015-04-17: Merge in class creation (Alex Mark)

2015-04-16: Refactor environment to handle inheritence (Alex Mark)

2015-04-16: Add tests for basic action predicates (Alex Mark)

2015-04-16: fix exceptions (Wangda Zhang)

2015-04-16: Improved syntax error messaging by repeating the line and showing the position near where the error occurred (Andrew Kallem)

2015-04-16: Merge in Wangda (Alex Mark)

2015-04-16: Start testing but merge in Wangda (Alex Mark)

2015-04-16: Merge branch 'grammar-block-restrictions' into better-syntax-errors (Andrew Kallem)

2015-04-16: remove unnecessary null object check (Wangda Zhang)

2015-04-16: implement event listeners for boolen predicates (Wangda Zhang)

2015-04-16: Merge branch 'master' into queue-prop-assign (Andrew Kallem)

2015-04-16: Merge branch 'master' into grammar-block-restrictions (Andrew Kallem)

2015-04-16: chmod test file (Wangda Zhang)

2015-04-16: add unit tests for Wait (Wangda Zhang)

2015-04-16: Merge branch 'master' of https://github.com/Aalk4308/Words into implement-wait (Wangda Zhang)

2015-04-16: Merge pull request #83 from Aalk4308/default-assertions (ZhangWangda)

2015-04-16: looking good, system tests good (Michael Ben-Ami)

2015-04-16: Merge branch 'default-assertions' into class-create (Michael Ben-Ami)

2015-04-16: null property value issues (Michael Ben-Ami)

2015-04-16: Do other basic action predicates (Alex Mark)

2015-04-16: Rework object out of scope model, do the touches predicate (Alex Mark)

2015-04-15: Added system tests for queued property assignment (Andrew Kallem)

2015-04-15: createclass inode skeleton (Michael Ben-Ami)

2015-04-15: Added unit tests for queue assignment, and some additional tests for queue move (Andrew Kallem)

2015-04-15: Update system test for delayed evaluation (Andrew Kallem)

2015-04-15: changed to assert: syntax (Michael Ben-Ami)

2015-04-15: Modified implementation of queued property assignment to use inherited attributes (Andrew Kallem)

2015-04-15: Factored out the code that converts an ASTValue to a WordsProperty (Andrew Kallem)

2015-04-15: Merge branch 'queue-move-delay-eval' (Andrew Kallem)

2015-04-15: First implementation of queued property assignment (Andrew Kallem)

2015-04-15: Added an initial simple test of delayed eval, which will need to be further fleshed out when delayed property assignment is implemented (Andrew Kallem)

2015-04-15: Modify INodeQueueMove and WordsMove so that the distance is not evaluated until the action is expanded (Andrew Kallem)

2015-04-15: Update grammar to prevent class and listener declarations from occuring within blocks (Andrew Kallem)

2015-04-14: enable assertions by default for tests; fix WordsAction (Wangda Zhang)

2015-04-14: fix TestWordsSay (Wangda Zhang)

2015-04-14: remove WordsWait(double lengthValue) method, only takes AST (Wangda Zhang)

2015-04-14: Merge branch 'master' of https://github.com/Aalk4308/Words into implement-wait (Wangda Zhang)

2015-04-14: Merge pull request #82 from Aalk4308/system-test-refactor (Aalk4308)

2015-04-14: Merge pull request #81 from Aalk4308/flip-y-axis (Aalk4308)

2015-04-14: throws WordsObjectNotFoundException from INodeQueueSay (Wangda Zhang)

2015-04-14: implement INodeQueueWait (Wangda Zhang)

2015-04-13: Reduced system test default log length from 500 to 100 (Andrew Kallem)

2015-04-13: Renamed system test files to be consistent (Andrew Kallem)

2015-04-13: Flip y-axis in the GUI (Andrew Kallem)

2015-04-13: Regenerate system test logs for tests that use up down movement (Andrew Kallem)

2015-04-13: Update TestWordsObject to use new y-axis (Andrew Kallem)

2015-04-13: Change moveUp and moveDown to move WordsObject y in the natural direction (Andrew Kallem)

2015-04-13: Merge pull request #80 from Aalk4308/queue-move (James Young)

2015-04-13: edit to test for WordsObject (jtyoung1292)

2015-04-13: Merge branch 'master' into queue-move (jtyoung1292)

2015-04-13: JUnit tests (jtyoung1292)

2015-04-13: Merge pull request #65 from Aalk4308/reset-exit (Aalk4308)

2015-04-13: add clean before tests (Wangda)

2015-04-13: moved first scope to setupEnvironment() (Michael Ben-Ami)

2015-04-13: Merge branch 'master' into reset-exit (Michael Ben-Ami)

2015-04-13: Merge pull request #79 from Aalk4308/fix-wordssay-errors (Aalk4308)

2015-04-13: clear gettable and ugettable (Michael Ben-Ami)

2015-04-13: Fix test errors in WordsObject that were creating WordsSay objects using the old constructor (Andrew Kallem)

2015-04-13: Merge branch 'master' into reset-exit (Michael Ben-Ami)

2015-04-13: Merge pull request #64 from Aalk4308/test-queuesay (Aalk4308)

2015-04-13: Merge pull request #63 from Aalk4308/say-ast (Aalk4308)

2015-04-13: Merge pull request #62 from Aalk4308/abort-build-on-failure (Aalk4308)

2015-04-13: Merge pull request #61 from Aalk4308/test-coercion-idioms (Aalk4308)

2015-04-13: Merge pull request #60 from Aalk4308/words-object-tests (Aalk4308)

2015-04-13: Merge pull request #59 from Aalk4308/words-class-tests (Aalk4308)

2015-04-13: Merge pull request #57 from Aalk4308/local-scope (Aalk4308)

2015-04-13: no-error Reset system test (Michael Ben-Ami)

2015-04-13: functionality of negative distance and ANYWHERE moved to WordsMove (jtyoung1292)

2015-04-13: getOpposite method added to Direction (jtyoung1292)

2015-04-13: Merge branch 'test-queuesay' of https://github.com/Aalk4308/Words into test-queuesay (Wangda Zhang)

2015-04-13: remove test on only string and num, moved to TestWordsSay (Wangda Zhang)

2015-04-13: add TestWordsSay.java (Wangda Zhang)

2015-04-13: WordsSay only takes AST (Wangda Zhang)

2015-04-13: anywhere implemented without switch (jtyoung1292)

2015-04-13: QueueMove now checks distance for null value (jtyoung1292)

2015-04-13: fix typo (ZhangWangda)

2015-04-13: add test for expected exception (Wangda Zhang)

2015-04-12: Move now supports negative values (jtyoung1292)

2015-04-12: add test for variables going out of scope (Alex Mark)

2015-04-12: Merge branch 'master' of https://github.com/Aalk4308/Words into queue-move (jtyoung1292)

2015-04-12: WordsObject throws InvalidTypeException when row or column is not assigned as a number (jtyoung1292)

2015-04-11: better reset system test (Michael Ben-Ami)

2015-04-11: exit and reset with tests (Michael Ben-Ami)

2015-04-11: fix file mode (Wangda Zhang)

2015-04-11: add unit test for INodeQueueSay (Wangda Zhang)

2015-04-11: Add additional test case (Alex Mark)

2015-04-11: fix test case (Alex Mark)

2015-04-11: add AST to WordsSay; move WordsProgramExceptions to words.exceptions (Wangda Zhang)

2015-04-11: Modified counting sample program (Andrew Kallem)

2015-04-10: Added some simple sample programs (Andrew Kallem)

2015-04-10: Update build.xml so that if JUnit test fails, ant will say BUILD FAILED (Andrew Kallem)

2015-04-10: Test string to number and number to string coercion idioms (Andrew Kallem)

2015-04-10: Added unit test for object property inheritance from class (Andrew Kallem)

2015-04-10: Merge branch 'words-class-tests' into words-object-tests (Andrew Kallem)

2015-04-10: Added many tests for WordsObject class (Andrew Kallem)

2015-04-10: Added unit tests for WordsClass (Andrew Kallem)

2015-04-10: Added implementation of setProperty in WordsClass (Andrew Kallem)

2015-04-10: Merge branch 'gui-grid-numbers' of https://github.com/Aalk4308/Words into local-scope (Alex Mark)

2015-04-10: Added simple number labels at the top and left sides of the grid UI (Andrew Kallem)

2015-04-10: more thorough test of local scope (Alex Mark)

2015-04-10: Add logging test (Alex Mark)

2015-04-10: Add test cases (Alex Mark)

2015-04-10: Make repeat use a local scope (Alex Mark)

2015-04-10: Build scoping into the environment (Alex Mark)

2015-04-10: Merge pull request #56 from Aalk4308/fix-log-generation (Alex Mark)

2015-04-10: Fix mode that we run logging files (Alex Mark)

2015-04-10: Merge pull request #48 from Aalk4308/cleanup-tests (Aalk4308)

2015-04-10: Merge pull request #41 from Aalk4308/arithmetic-operators (Aalk4308)

2015-04-10: Merge pull request #39 from Aalk4308/eval-ref-list-eval-assign (Aalk4308)

2015-04-09: throws assertion errors (Michael Ben-Ami)

2015-04-09: null check before assertion error in inoderetrieveproperty (Michael Ben-Ami)

2015-04-09: Fix typo (Alex Mark)

2015-04-09: Modified Windows batch script for system test to print only filename and not full path (Andrew Kallem)

2015-04-09: better assertion and exception throwing (Michael Ben-Ami)

2015-04-09: fixed test name in Negate test (jtyoung1292)

2015-04-09: Only print file name, not full file path during system test (Alex Mark)

2015-04-09: added . into exception message (jtyoung1292)

2015-04-09: added log for arithmetic.wrods test file (jtyoung1292)

2015-04-09: added tests (jtyoung1292)

2015-04-09: Merge branch 'master' into arithmetic-operators (jtyoung1292)

2015-04-08: Fix comment (Andrew Kallem)

2015-04-08: forgot to add ref list tests (Michael Ben-Ami)

2015-04-08: ref list tests (Michael Ben-Ami)

2015-04-08: retrieve property tests (Michael Ben-Ami)

2015-04-08: assignment test for exception (Michael Ben-Ami)

2015-04-08: JUnit Assignment complete (Michael Ben-Ami)

2015-04-08: started modularizing junit tests (Michael Ben-Ami)

2015-04-08: Updated system test script for Windows to use new more concise output format (Andrew Kallem)

2015-04-08: Clean up display of test cases (Alex Mark)

2015-04-08: merged in master, fixed that junit test (Alex Mark)

2015-04-08: Fix merge conflict, imports (Alex Mark)

2015-04-08: Merge pull request #44 from Aalk4308/automated-system-test (Alex Mark)

2015-04-08: Fix build error (Alex Mark)

2015-04-08: Merge branch 'master' of https://github.com/Aalk4308/Words into automated-system-test (Alex Mark)

2015-04-08: Merge pull request #46 from Aalk4308/refactor-packages (Alex Mark)

2015-04-08: system test success, junit struggles (Michael Ben-Ami)

2015-04-08: Test for INodeAssign (Michael Ben-Ami)

2015-04-08: unit tests in progress (Michael Ben-Ami)

2015-04-07: added eval assign java file (Michael Ben-Ami)

2015-04-07: changes from Monday's meeting (Michael Ben-Ami)

2015-04-07: Update build.xml (ZhangWangda)

2015-04-07: Merge branch 'master' of https://github.com/Aalk4308/Words into refactor-packages (Alex Mark)

2015-04-07: move wordsui out of environment package (Alex Mark)

2015-04-07: Fix JUnit issues (Alex Mark)

2015-04-07: Merge branch 'automated-system-test' of https://github.com/Aalk4308/Words into automated-system-test (Andrew Kallem)

2015-04-07: Merge branch 'master' into automated-system-test (Andrew Kallem)

2015-04-07: Merge pull request #43 from Aalk4308/refactor-and-cleanup (Aalk4308)

2015-04-07: indentation fix (ZhangWangda)

2015-04-07: Fix compile issues, still having trouble running tests (Alex Mark)

2015-04-07: indentation fix (ZhangWangda)

2015-04-07: Refactor code base to use a package structure (Alex Mark)

2015-04-07: Minor refactoring.  Ensure frame limit is only in place during testing and not normal use. (Andrew Kallem)

2015-04-07: Updated system test example programs (Andrew Kallem)

2015-04-07: Automate system testing for non-Windows systems (Andrew Kallem)

2015-04-07: Setting up ability to run system test automatically on Unix (Andrew Kallem)

2015-04-07: Committing the log files for the sample programs (Andrew Kallem)

2015-04-07: Automated system testing now works on Windows (Andrew Kallem)

2015-04-07: add separator between cases for system test scripts (Wangda Zhang)

2015-04-07: In no GUI mode, execute each frame with no delay between frames so that the tests run as quickly as possible (Andrew Kallem)

2015-04-07: Print welcome message and user prompt to stderr so it is not captured by the logging (Andrew Kallem)

2015-04-07: Move MarcoPolo example out of system_test directory (Andrew Kallem)

2015-04-07: Add sentinel line number if none is provided (Andrew Kallem)

2015-04-07: Renamed system test script to test (Andrew Kallem)

2015-04-07: Remove debug output when AST generation failed.  Handle case where statement is null. (Andrew Kallem)

2015-04-07: Minor improvements to documentation and code style (Andrew Kallem)

2015-04-07: Fix formatString error in WordsObjectNotFoundException which was causing a string format exception (Andrew Kallem)

2015-04-07: Added debug flag to javac in build.xml so that stack traces from crashes show line numbers in our files (Andrew Kallem)

2015-04-07: Added back line numbers to AST nodes (Andrew Kallem)

2015-04-07: Refactored all JUnit tests (Andrew Kallem)

2015-04-07: Remove legacy ASTType enum (Andrew Kallem)

2015-04-06: Moved system testing related code to a separate folder from JUnit testing (Andrew Kallem)

2015-04-06: Refactored all INode and LNode types to be separate subclasses (Andrew Kallem)

2015-04-06: Merge pull request #42 from Aalk4308/logging (ZhangWangda)

2015-04-06: fix merge conflicts (Wangda Zhang)

2015-04-06: added divide by 0 exception (jtyoung1292)

2015-04-06: Merge pull request #34 from Aalk4308/boolean-and-relational (Aalk4308)

2015-04-06: Added tests for evalIf (Andrew Kallem)

2015-04-06: Merge branch 'master' into boolean-and-relational (Andrew Kallem)

2015-04-06: Merge pull request #35 from Aalk4308/test-cases (Alex Mark)

2015-04-06: Fix naming (Alex Mark)

2015-04-06: updated test program (jtyoung1292)

2015-04-06: added WordsArithmeticException (jtyoung1292)

2015-04-06: Update Option.java (ZhangWangda)

2015-04-06: style changed to conform to style guide (jtyoung1292)

2015-04-06: fixed evalAdd to try to coerce strings to numbers first (jtyoung1292)

2015-04-06: new error added to arithmetic methods (jtyoung1292)

2015-04-06: done with windows script (Wangda Zhang)

2015-04-06: working on windows scripts (Wangda Zhang)

2015-04-06: add logging and comparing (Wangda Zhang)

2015-04-05: add implemented with string concatenation (jtyoung1292)

2015-04-05: exponentiation implemented (jtyoung1292)

2015-04-05: divide and multiply implemented (jtyoung1292)

2015-04-05: Add documentation (Alex Mark)

2015-04-05: Add test for iteration (Alex Mark)

2015-04-05: negate implemented (jtyoung1292)

2015-04-05: evalSubtract implemented (jtyoung1292)

2015-04-05: minor spacing fix (Michael Ben-Ami)

2015-04-05: accounted for NOTHING properties in evalRefList() (Michael Ben-Ami)

2015-04-05: first evalRefList() and evalAssign() (Michael Ben-Ami)

2015-04-04: Added unit tests for boolean operators (Andrew Kallem)

2015-04-04: Added unit tests for other relational operators (Andrew Kallem)

2015-04-04: Merge branch 'test-cases' into boolean-and-relational (Andrew Kallem)

2015-04-04: Fix typo (Andrew Kallem)

2015-04-04: Added tests for evalGEQ (Andrew Kallem)

2015-04-04: Merge branch 'test-cases' into boolean-and-relational (Andrew Kallem)

2015-04-04: In TestINode, changed variable name to testNode, which will allow for more consistency across tests (Andrew Kallem)

2015-04-04: Merged latest test-case template and added equals test cases (Andrew Kallem)

2015-04-04: Merge branch 'test-cases' into boolean-and-relational (Andrew Kallem)

2015-04-04: Reorganized TestINode.java slightly to make it easier for others to add more tests without dependencies between tests (Andrew Kallem)

2015-04-04: Merge branch 'test-cases' into boolean-and-relational (Andrew Kallem)

2015-04-03: make final compile fixes for ASTValue being its own cclass (Alex Mark)

2015-04-03: Merge branch 'master' of https://github.com/Aalk4308/Words into test-cases (Alex Mark)

2015-04-03: Add new files (Alex Mark)

2015-04-03: Add ASTValue test cases (Alex Mark)

2015-04-03: Add documentation about instance variables (Alex Mark)

2015-04-03: Merge pull request #36 from Aalk4308/queuepop-fix (Aalk4308)

2015-04-03: Sometimes I introduce bugs into the codebase, and they make it all the way to master. (Alex Mark)

2015-04-03: fix indent (Alex Mark)

2015-04-03: Add template for test cases, first test cases (Alex Mark)

2015-04-02: Documentated short-circuit evaluation logic for And and Or (Andrew Kallem)

2015-04-01: Merge branch 'master' into boolean-and-relational (Andrew Kallem)

2015-04-01: Merge pull request #32 from Aalk4308/exceptions (Aalk4308)

2015-04-01: Added operator type mismatch exception (Andrew Kallem)

2015-04-01: Implemented left-to-right short-circuit evaluation of boolean operators (Andrew Kallem)

2015-04-01: Fix precedence of AND and OR in grammar (Andrew Kallem)

2015-04-01: Fix compile errors after merge (Andrew Kallem)

2015-04-01: Merge branch 'exceptions' into boolean-and-relational (Andrew Kallem)

2015-04-01: Fix extra period (Alex Mark)

2015-04-01: Include decorated exception in inode.java (Alex Mark)

2015-04-01: Add missing files (Alex Mark)

2015-04-01: Refactor a bit as discussed with Andrew. Maintain a distinction between runtime exceptions (things that went wrong) and where they went wrong (their respective AST) (Alex Mark)

2015-04-01: HAve the WordsProgramException store the offending AST and not just its lineNo (Alex Mark)

2015-04-01: HAve the WordsProgramException store the offending AST and not just its lineNo (Alex Mark)

2015-04-01: Renamed ReferenceException to WordsReferenceException for consistency (Andrew Kallem)

2015-04-01: Refactored coercion methods to a single tryCoerceTo method (Andrew Kallem)

2015-03-31: Simplify type coercions in evalEquals to use the num/string coercion methods (Andrew Kallem)

2015-03-31: Edits to assertion messages (Andrew Kallem)

2015-03-31: Simplify conditions in checkRelOpArgTypes (Andrew Kallem)

2015-03-31: Minor refactoring and documentation improvement (Andrew Kallem)

2015-03-31: Merge branch 'exceptions' into boolean-and-relational (Andrew Kallem)

2015-03-31: Fix style issues (Alex Mark)

2015-03-31: Refactor exceptions into two distinct groups (Alex Mark)

2015-03-31: rename arg to args for clarity (Alex Mark)

2015-03-31: rename arg to args for clarity (Alex Mark)

2015-03-31: Fix coersion problems (Alex Mark)

2015-03-31: PR comments (Alex Mark)

2015-03-30: Added an (incomplete) words program to test basic relational operator and boolean predicate functionality (Andrew Kallem)

2015-03-30: Implementations of evalOr, evalAnd, and evalNot (Andrew Kallem)

2015-03-30: Implemented evalIf (Andrew Kallem)

2015-03-30: Added exceptions into the inode evaluation (Alex Mark)

2015-03-30: Preliminary implementation of evalEquals (Andrew Kallem)

2015-03-30: Preliminary implementations for eval of greater, less, GEQ, and LEQ (Andrew Kallem)

2015-03-30: Created missing exceptions (Alex Mark)

2015-03-30: Created exceptions, included line numbers (Alex Mark)

2015-03-30: Added boolean value type for ASTValue (Andrew Kallem)

2015-03-30: Merge pull request #14 from Aalk4308/skeleton (Alex Mark)

2015-03-30: Added additional TODOs (Andrew Kallem)

2015-03-30: Added an implementation for Repeat statement (Andrew Kallem)

2015-03-30: Added handling for row and column properties and added setProperty method to WordsObject (Andrew Kallem)

2015-03-30: Added some additional documentation and notes for implementation of certain methods (Andrew Kallem)

2015-03-30: Have new objects wait one frame so that they appear in the given position (Andrew Kallem)

2015-03-30: Implementations of evalQueueMove and evalQueueSay, along with other minor modifications, to get simple hello world program running (Andrew Kallem)

2015-03-30: Added simple program from our original make-it-work sprint (Andrew Kallem)

2015-03-30: Throw assertion error for any AST eval method that is not yet implemented (Andrew Kallem)

2015-03-30: Renamed syntax.w to syntax.words (Andrew Kallem)

2015-03-30: Stub methods in WordsClass and WordsObject, with implementations of some methods.  Stub WordsCustomActionDefinition class (which is distinct from WordsCustomAction). (Andrew Kallem)

2015-03-30: Reinstated implementation of evalStatementList (Andrew Kallem)

2015-03-30: Rewrote implementation of WordsAction and various subclasses (leaving some important but clear TODOs) to allow for executable vs. expandable actions (Andrew Kallem)

2015-03-30: Added placeholders eval methods for all AST node types.  Included default case with assertion to catch errors. (Andrew Kallem)

2015-03-30: Renamed Value to ASTValue for clarity (Andrew Kallem)

2015-03-30: Minor refactoring of field names in Value and LNode (Andrew Kallem)

2015-03-30: Minor refactoring (Andrew Kallem)

2015-03-30: Fix all compile errors (in some cases by temporarily commenting out lines).  Remove hashmap params to AST eval function (Andrew Kallem)

2015-03-26: Add missing files (Alex Mark)

2015-03-26: First version of new architecture. Completely non-working and with sections that don't compile. Can likely use a refactor in parts already (Alex Mark)

2015-03-26: Merge pull request #13 from Aalk4308/grammar-additions (Alex Mark)

2015-03-24: Fixed typo in example syntax program (Andrew Kallem)

2015-03-24: Added preliminary support for local variables in listener predicates, called 'aliases' in the grammar. (Andrew Kallem)

2015-03-24: Added AST and grammar support for the 'touches' predicate (Andrew Kallem)

2015-03-24: Added 'touches' as a keyword (Andrew Kallem)

2015-03-24: Added support in grammar and AST for Reset and Exit statements.  Both are harmlessly set up as internal nodes rather than leaf nodes in case we want to add parameters to them later, like error return values or types of resets. (Andrew Kallem)

2015-03-22: Merge pull request #12 from Aalk4308/complete-grammar (Aalk4308)

2015-03-22: Use StringBuilder in INode toString method to make concatenation more efficient (Andrew Kallem)

2015-03-22: Remove old copyright notices (Andrew Kallem)

2015-03-17: Merge branch 'repl-interface' into complete-grammar (Andrew Kallem)

2015-03-17: Avoid infinite loop if user redirects file into standard input (Andrew Kallem)

2015-03-17: Take program filename as a command line argument, then present REPL interface (Andrew Kallem)

2015-03-17: Merge branch 'complete-grammar' into repl-interface (Andrew Kallem)

2015-03-17: Avoid exception if AST fails to be completely generated due to a syntax error (Andrew Kallem)

2015-03-17: Preliminary REPL interface (Andrew Kallem)

2015-03-17: Added support for strings to include the escape sequences listed in the reference manual (Andrew Kallem)

2015-03-17: Lexer now ignores comments denoted by // (Andrew Kallem)

2015-03-16: Added support in the grammar for his, her, its, and their references within the body of a custom action (Andrew Kallem)

2015-03-15: Removed unnecessary/incorrect form of property assignment statement (Andrew Kallem)

2015-03-12: Minor refactoring (Andrew Kallem)

2015-03-12: Added some basic javadoc documentation (Andrew Kallem)

2015-03-12: Various minor cleanups in the grammar (Andrew Kallem)

2015-03-12: Make indenting in grammar more consistent to make it more readable (Andrew Kallem)

2015-03-12: Merge branch 'simplify-now-keyword' into complete-grammar (Andrew Kallem)

2015-03-12: Simplified now keyword to avoid needing different internal node types whether or not the keyword is present (Andrew Kallem)

2015-03-12: Updated grammar to allow support for queued simultaneous property assignment construct (Andrew Kallem)

2015-03-12: Merge branch 'lexeme-based-refs' into complete-grammar (Andrew Kallem)

2015-03-12: Remove mistakenly added lex/yacc generated files (Andrew Kallem)

2015-03-12: Simplified handling of required reference chain (Andrew Kallem)

2015-03-12: Made the 's dereference operator a part of the lexer instead of the parser, which allows for easier building of the syntax tree for reference chains, particularly for multiple assignment statements. (Andrew Kallem)

2015-03-12: Remove legacy sample.w program (Andrew Kallem)

2015-03-12: Added test program with all Words syntax constructs (Andrew Kallem)

2015-03-12: Added all synonyms for 'a' to lexer (Andrew Kallem)

2015-03-12: Added basic line number counting and syntax error messages (Andrew Kallem)

2015-03-12: Remove leading and trailing quotes for the string value in string leaf nodes (Andrew Kallem)

2015-03-11: Implementaton of Words grammar in flex and yacc, with building of abstract syntax tree and temporary dumping to console for debugging (Andrew Kallem)

2015-03-11: Added abstract syntax tree base class, internal node, and leaf node (Andrew Kallem)

2015-03-11: Added abstract syntax tree base class, internal node, and leaf node (Andrew Kallem)

2015-03-11: Added abstract syntax tree base class, internal node, and leaf node (Andrew Kallem)

2015-03-10: Merge branch 'master' into complete-grammar (Andrew Kallem)

2015-03-10: Merge pull request #11 from Aalk4308/exception-fix (Aalk4308)

2015-03-10: add necessary exceptions (Alex Mark)

2015-03-10: Initial version of a complete Lex and Yacc grammar for the language.  Compiles with no parser conflicts, but not yet tested. (Andrew Kallem)

2015-03-09: Merge pull request #10 from Aalk4308/testing (Alex Mark)

2015-03-09: Merge branch 'testing' of https://github.com/Aalk4308/cs4115 into testing (Wangda Zhang)

2015-03-09: add lib/ (Wangda Zhang)

2015-03-09: Fix merge conflict (Alex Mark)

2015-03-09: Merge pull request #9 from Aalk4308/working-move (Alex Mark)

2015-03-09: add dumb test (Wangda Zhang)

2015-03-09: minor revision (Wangda Zhang)

2015-03-09: update build.xml to add junit (Wangda Zhang)

2015-03-04: fix two newlines (Alex Mark)

2015-03-04: allow Words to receive expressions for numbers (Alex Mark)

2015-03-03: add function arg exception (Alex Mark)

2015-03-03: Add error handling to the current functions (Alex Mark)

2015-03-03: Make saying things work... just for fun (Alex Mark)

2015-03-02: removed all debug code (Alex Mark)

2015-03-02: removed debug print statements (Alex Mark)

2015-03-02: Create and move commands both work in a hacky way (Alex Mark)

2015-03-02: Merge pull request #8 from Aalk4308/threaded-but-only-runs-once (Aalk4308)

2015-03-02: working threaded example for piping in a file (Alex Mark)

2015-03-02: Merge branch 'master' of github.com:Aalk4308/cs4115 into threaded-but-only-runs-once (Alex Mark)

2015-03-02: Merge pull request #7 from Aalk4308/sample-program (Aalk4308)

2015-03-02: Added a simple sample program for us to test. (Andrew Kallem)

2015-03-02: Changed default build to just build, not run (Andrew Kallem)

2015-03-02: Create a thing class (Alex Mark)

2015-03-02: made thread safe (Alex Mark)

2015-03-02: threading works but is not thread safe (Alex Mark)

2015-03-02: add action listeners (Alex Mark)

2015-03-02: First amount of threading, not working (Alex Mark)

2015-03-02: Merge pull request #5 from Aalk4308/gui-improvements (Alex Mark)

2015-03-01: Merge pull request #4 from Aalk4308/first-parser (Alex Mark)

2015-03-01: Refactored to use WordsPosition class for coordinates instead of x,y (Andrew Kallem)

2015-03-01: Basic rendering of objects works well enough for early testing, though will need further refinement down the road. (Andrew Kallem)

2015-03-01: Rough rendering of an object works, but it is not centered and does not adjust to the cell size (Andrew Kallem)

2015-03-01: Added methods to manipulate what is displayed on the UI (clear, add objects, and render) (Andrew Kallem)

2015-03-01: Improved documentation of existing code (Andrew Kallem)

2015-02-28: parse functions and object creation into a command (Alex Mark)

2015-02-27: it's working... sort of (Alex Mark)

2015-02-27: It compiles (Alex Mark)

2015-02-27: Fixing more errors... (Alex Mark)

2015-02-27: add CommandType (Alex Mark)

2015-02-27: First try at defining our language -- lex errors (Alex Mark)

2015-02-27: Merge pull request #3 from Aalk4308/reorganize-with-ant (Alex Mark)

2015-02-26: Renamed files to Words.flex and Words.y (Andrew Kallem)

2015-02-25: make ant build work on non windows systems (Alex Mark)

2015-02-23: Clarified some magic numbers in WordsUI class (Andrew Kallem)

2015-02-23: Reorganized project to use ant build mechanism and have a reasonable .gitignore. (Andrew Kallem)

2015-02-23: Merge pull request #2 from Aalk4308/java-skeleton (Aalk4308)

2015-02-20: Created skeleton files for eventual words world backend (Alex Mark)

2015-02-16: Merge pull request #1 from Aalk4308/organized-structure (Alex Mark)

2015-02-16: create folder structure (Alex Mark)

2015-02-12: lex yacc calc (Michael Ben-Ami)

2015-02-09: Initial commit (Aalk4308)

## 10.2. Complete Source Code

The following pages provide the complete source code of *Words*, as well as our test cases, scripts, and sample programs.

