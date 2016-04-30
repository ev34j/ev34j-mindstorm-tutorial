
build:
	mvn clean package

scp: build
	scp target/myev3robot-1.0-jar-with-dependencies.jar robot@ev3:/home/robot
