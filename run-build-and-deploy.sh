
echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>> BUILD Modules <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
./gradlew app-downstream-mock:build
./gradlew app-webflux:build
./gradlew app-webmvc:build
./gradlew app-component-test:build

echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>> BUILD Images <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
(
  cd app-webflux || exit
  ./docker-build.sh
)

(
  cd app-webmvc || exit
  ./docker-build.sh
)

(
  cd app-downstream-mock || exit
  ./docker-build.sh
)

echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>> DEPLOY <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
./run-docker-compose.sh > /dev/null &

echo ">>>>>>>>>>>>>>>> SLEEP 10 seconds letting apps to start <<<<<<<<<<<<<<<"
sleep 10

echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>> RUN BDD Test <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
./run-component-test.sh