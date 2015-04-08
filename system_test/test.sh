#!/bin/bash

for prog in "$@"; do
    java -jar jar/Words.jar "$prog" -testmode > run.log.tmp
    
    if ! diff run.log.tmp "$prog.log"; then
    	echo "[System Test] $prog FAILED  Check run.log.tmp.  Aborting..."
    	exit 1
    fi
    echo "[System Test] $prog OK"
done

rm run.log.tmp
