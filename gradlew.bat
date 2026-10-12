@if "%DEBUG%"=="" @echo off
@rem Gradle wrapper batch script for Windows
setlocal enabledelayedexpansion
set DEFAULT_JVM_OPTS=
set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
set APP_HOME=%DIRNAME%
set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

if not defined JAVA_EXE (
    if exist "C:\java17\jdk-17.0.11+9\bin\java.exe" (
        set JAVA_EXE=C:\java17\jdk-17.0.11+9\bin\java.exe
    ) else if defined JAVA_HOME (
        set JAVA_EXE=%JAVA_HOME%\bin\java.exe
    ) else (
        set JAVA_EXE=java.exe
    )
)

"%JAVA_EXE%" %DEFAULT_JVM_OPTS% -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
