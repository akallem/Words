@echo off

:loop
if "%~1" neq "" (
  echo Logging %1 ...
  java -jar jar/Words.jar %1 -nogui >%1.log

  shift
  goto :loop
)