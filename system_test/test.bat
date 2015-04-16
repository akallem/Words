@echo off

:loop
if "%~1" neq "" (
  java -enableassertions -jar jar/Words.jar %1 -testmode > run.log.tmp

  fc run.log.tmp %1.log >nul 2>&1 && echo [System Test] %~n1%~x1 OK || (echo [System Test] %~n1%~x1 FAILED  Check run.log.tmp.  Aborting...  & exit 1)
  
  shift
  goto :loop
)

del run.log.tmp
