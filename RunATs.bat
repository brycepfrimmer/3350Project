REM @echo off

IF "%1" NEQ "" (SET SLEEP=%1) ELSE (SET SLEEP=1)

call SetClassPath

set SLEEP=1
java -cp %CLASSPATH% acceptanceTests.TestRunner %SLEEP%

pause



