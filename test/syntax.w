A food is a thing.
A pizza is a food.
A person is a thing which {
	has a height.
	has a hair_color of "brown".
	can jump which means {
		Make them move up 10.
		Make them move down 10.
	}
}

Alex is a person at 0,0.
John is a person at Alex's row + 1,0.
Andrew is a person with height 160, hair_color "blonde" at 4,0.
Wangda is an person with height Andrew's height + 2 at 3,0.

Remove Peter.
Remove Peter's mother.

Fred's row is 5.
John's height is 150.
Peter's father is Fred.
Peter's sentence is "This is my sentence.".
Peter's father is nothing.

Peter's father's height is Fred's height + 10.
Peter's mother is Fred's wife.

Make Fred say "I'm going to to left.".
Make Fred move left 3.
Make Fred say "Now I'm going to to right.".
Make Fred move right 3.
Make Fred's status be "done".
Make Fred say "Hi!".
Make Peter wait 10 turns.
Make Peter's mother say Fred's status.

Make Peter's mother be Jane.
Make Peter's height be 150.

Make Peter's height be 150, mother be Jane.

Make Fred move left 3.
Make Fred move left Fred's mother's property.
Make Fred move left.
Make Fred move left -3.
Make Fred move left 3.1416.
Make Fred move left -3.1416.
Make Fred move anywhere.
Make Fred move anywhere 2.

Fred's jump_height is 20.
Make Fred move left 3.
Make John move left 3.
Make Fred's jump_height be 10.
Make John jump with height Fred's jump_height.
Make Fred jump with height Fred's jump_height.

Make Fred say "Hello world!" now.
Make Fred jump with height 10 now.

Stop Fred.
Stop Fred's mother.

Repeat 5 times {
	Make Fred move left 3.
	Make Fred say "Hi!".
}

Repeat 5 times {
	John's property is John's property / 2.
}

While John's property >= 1 {
	Make Fred's property be Fred's property / 2.
}

If John's property = Peter's property then {
	John's property is 5.
}

If John's property > Peter's property and (Peter's property + 1 < Fred's property - 10 or Peter's property < Andrew's property) then {
	Peter's property is 5.
	Make Fred move left 3.
}

Whenever John's property = Peter's property {
	Make John say "We're the same!".
	Make Peter say "Yay homogeny".
}

As long as John's property < 1 {
	Make Fred move left now.
}

Whenever Fred says "Polo" {
	Stop John.
	Make John move left Fred's column - John's column.
	Make John move up Fred's row - John's row.
	Make John say "Marco".
}

Whenever Fred moves left {
	Stop John.
}

Whenever Fred waits {
	Make John say "hurry up" now. 
}