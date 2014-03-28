call SetClasspath

cd databases\

Call ClearDB.bat

cd ..

REM @echo off

del -rf databases\Vehicles\Vehicles.lck
java junit.textui.TestRunner echo tests.IntegrationTests >> AllTests.txt

cd databases\

Call RestoreDB.bat

cd ..

pause
