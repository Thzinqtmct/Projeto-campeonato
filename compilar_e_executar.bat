@echo off
echo === Compilando o projeto ===

if not exist bin mkdir bin

set JAVAC=%APPDATA%\.minecraft\runtime\java-runtime-gamma\bin\javac.exe
set JAVA=%APPDATA%\.minecraft\runtime\java-runtime-gamma\bin\java.exe

"%JAVAC%" -encoding UTF-8 -d bin src/model/*.java src/Main.java

if %ERRORLEVEL% NEQ 0 (
    echo ERRO na compilacao!
    pause
    exit /b 1
)

echo Compilacao concluida!
echo.
echo === Executando o sistema ===
"%JAVA%" -Dfile.encoding=UTF-8 -cp bin Main

pause
