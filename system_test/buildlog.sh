#!/bin/bash

for prog in "$@"; do
    echo "Logging $prog ..."
    java -enableassertions -jar jar/Words.jar "$prog" -testmode >"$prog.log"
done
