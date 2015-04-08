#!/bin/bash

for prog in "$@"; do
    echo "Logging $prog ..."
    java -jar jar/Words.jar "$prog" -nogui >run.log.tmp
    echo "Diff with $prog.log"
    diff run.log.tmp "$prog.log"

    echo
    echo "==========="
    echo
done

rm run.log.tmp
