#!/bin/bash

for prog in "$@"; do
    echo "Logging $prog ..."
    java -enableassertions -jar jar/Words.jar "$prog" -testmode -displayerror 2>&1 | grep -i "error" >"$prog.log"
done
