#!/bin/bash

for prog in "$@"; do
    echo "Logging $prog ..."
    java -jar jar/Words.jar "$prog" -testmode >"$prog.log"
done
