@echo off

set JDIR=c:\cjava_z\local\build\classes

pushd %JDIR%

cd

java -cp %JDIR% TestLocal %1 %2 %3 %4 %5 %6

popd
