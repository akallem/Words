Words Programming Language

A project for COMS 4115, Spring 2015
[Course Website](http://www.cs.columbia.edu/~aho/cs4115/)

Andrew Kallem (Aalk4308)		Project Manager
Alex Mark (alexthemark)			Language Guru
James Young (jtyoung1292)		System Architect
Michael Ben-Ami (mzbenami)		System Integrator
Wangda Zhang (ZhangWangda)		Tester

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

## 1.4 More Information

For more information, including a tutorial, the Language Reference Manual and our group's final report, please view the documentation folder of this repository. 
