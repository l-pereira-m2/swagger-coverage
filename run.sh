./gradlew clean build -x test
rm -r -f swagger-coverage-1.5.0
unzip ./swagger-coverage-1.5.0.zip
./swagger-coverage-commandline-1.5.0/bin/swagger-coverage-commandline -s swagger-coverage-commandline/src/test/resources/v3/petstory.yaml -i swagger-coverage-commandline/src/test/resources/v3/swagger-coverage-output -c swagger-coverage-commandline/src/test/resources/configuration.json