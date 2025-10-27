install:
	gradle clean
	gradle installDist

run:
	./build/install/app/bin/app ../../file1.json ../../file2.json

build:
	./gradlew clean build

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain