#!/bin/bash

for prog in "$@"; do
    if [[ "$prog" == *"Error"* ]]; then
        java -enableassertions -jar jar/Words.jar "$prog" -testmode -displayerror 2>&1 | grep -i "error" > run.log.tmp
    else
        java -enableassertions -jar jar/Words.jar "$prog" -testmode > run.log.tmp
    fi
    
    if ! diff run.log.tmp "$prog.log"; then
    	echo "[System Test] ${prog##*/} FAILED  Check run.log.tmp.  Aborting..."
    	exit 1
    fi
    echo "[System Test] ${prog##*/} OK"
done

rm run.log.tmp
