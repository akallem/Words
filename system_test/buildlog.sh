#!/bin/bash

for prog in "$@"; do
    echo "Logging $prog ..."
    if [[ "$prog" == *"Error"* ]]; then
        java -enableassertions -jar jar/Words.jar "$prog" -testmode -displayerror 2>&1 | grep -i "error" > "$prog.log"
    else
        java -enableassertions -jar jar/Words.jar "$prog" -testmode > "$prog.log"
    fi
done
