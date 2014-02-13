REM @echo off

REM delete all .class files out of the bin directory
if not exist %CD%\bin mkdir bin
cd bin
erase /s *.class
cd ..


call SetClassPath


REM @echo off
javac -d bin\ -cp %classpath% src\org\eclipse\wb\swt\*.java src\cmms\*.java
javac -d bin\ -cp %classpath% src\junitTest\*.java
PAUSE
