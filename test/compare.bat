#!/bin/bash

for prog in "$@"
do
    echo "Logging $prog ..."
    java -jar jar/Words.jar "$prog" -nogui >run.log
    echo "Diff with $prog.log"
    diff run.log "$prog.log"
done

rm run.log
