REM @echo off

REM delete all .class files out of the bin directory
if not exist %CD%\bin mkdir bin
cd bin
erase /s *.class
cd ..


call SetClassPath


REM @echo off
javac -d bin\ -cp %classpath% src\cmmsObjects\*.java
javac -d bin\ -cp %classpath% src\cmmsBusiness\*.java
javac -d bin\ -cp %classpath% src\cmmsPersistence\*.java
javac -d bin\ -cp %classpath% src\cmmsPresentation\*.java
javac -d bin\ -cp %classpath% src\cmmsApplication\*.java

javac -d bin\ -cp %classpath% src\org\eclipse\wb\swt\*.java

javac -d bin\ -cp %classpath% src\testsObjects\*.java
javac -d bin\ -cp %classpath% src\testsPersistence\*.java
javac -d bin\ -cp %classpath% src\tests\*.java
pause