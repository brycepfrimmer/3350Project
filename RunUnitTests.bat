REM @echo off

call SetClasspath

REM @echo off

java junit.textui.TestRunner tests.TestCMMS > AllTests.txt
java junit.textui.TestRunner tests.IntegrationTests > AllTests.txt
