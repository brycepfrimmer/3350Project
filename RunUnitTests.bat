REM @echo off

call SetClasspath

REM @echo off

rm -rf databases\Vehicles\Vehicles.lck
rm -rf databases\ManFields\ManFields.lck
java junit.textui.TestRunner tests.TestCMMS > AllTests.txt
rm -rf databases\Vehicles\Vehicles.lck
rm -rf databases\ManFields\ManFields.lck
java junit.textui.TestRunner tests.IntegrationTests > AllTests.txt
