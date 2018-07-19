#! /bin/sh
pushd cube-untap
    ./gradlew clean build
popd

cp cube-untap/build/libs/cube-0.0.1-SNAPSHOT.jar compiledApp/cube.jar