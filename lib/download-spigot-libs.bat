@echo off
IF NOT EXIST BuildTools (
    mkdir BuildTools
)
cd BuildTools

:: You need to be running in java JDK 1.8 in order to run buildtools. If you are
:: running a newer version of java, simply uncomment these lines and add your own
:: java path here. NOTE: This will NOT change any of your environment variables!
:: set JAVA_HOME=C:\Program Files\Java\jre1.8.0_311\
set Path=%JAVA_HOME%\bin;%Path%

curl -z BuildTools.jar -o BuildTools.jar https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar

:: 1_20_R1
IF NOT EXIST "../1_20_R1" (
    mkdir "../1_20_R1"
)
cd "../1_20_R1"
java -jar ../BuildTools/BuildTools.jar --rev 1.20.1 --output-dir ../../nms