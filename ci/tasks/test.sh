#! /bin/sh
cd cube-untap
    ./gradlew clean build
    cp ./build/libs/cube-0.0.1-SNAPSHOT.jar compiledApp/cube.jar