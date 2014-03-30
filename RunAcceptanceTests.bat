REM @echo off

IF "%1" NEQ "" (SET SLEEP=%1) ELSE (SET SLEEP=1)

Call SetClassPath.bat

cd databases\

Call ClearDB.bat

cd ..

set SLEEP=1
java -cp %CLASSPATH% acceptanceTests.TestRunner %SLEEP% >> AllTests.txt

cd databases\

Call RestoreDB.bat

cd ..

pause



