# EV3 hostname
EV3_NAME = ev3
# This name must match the <finalName> value in pom.xml
JAR_NAME = myev3robot-jar-with-dependencies.jar
# Logging file name
LOG_PROP_NAME = logging.properties

default: build scp

build:
	# Build jar file
	mvn clean package

scp:
	# Copy jar file to EV3
	scp target/$(JAR_NAME) robot@$(EV3_NAME):/home/robot

run:
	# Run jar on EV3
	ssh robot@$(EV3_NAME) java -jar $(JAR_NAME)

debug:
	# Debug jar on EV3
	ssh robot@$(EV3_NAME) java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005 -jar $(JAR_NAME)

logging:
	scp $(LOG_PROP_NAME) robot@$(EV3_NAME):/home/robot
	ssh robot@$(EV3_NAME) java -Djava.util.logging.config.file=$(LOG_PROP_NAME) -jar $(JAR_NAME)


