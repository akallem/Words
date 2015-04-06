FOR %%PROG IN (%*) DO (
    ECHO "Logging %%PROG"
    java -jar jar/Words.jar "%%PROG" -nogui >"%%PROG.log"
)
