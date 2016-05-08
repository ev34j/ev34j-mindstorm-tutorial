# EV3 hostname
EV3_NAME = ev3dev

# EV3 password
EV3_PASSWORD = maker

# This name must match the <finalName> value in pom.xml
JAR_NAME = myev3robot-jar-with-dependencies.jar

# Logging file name
LOG_PROP_NAME = logging.properties

# If you use ssh-keygen, comment out the next line and uncomment the one following it
SSH_PREFIX = sshpass -p $(EV3_PASSWORD)
#SSH_PREFIX =

default: build scp

clean:
	mvn clean

build:
	# Build jar file
	mvn package
	say "Build complete"

scp:
	# Copy jar file to EV3
	$(SSH_PREFIX) scp target/$(JAR_NAME) robot@$(EV3_NAME):/home/robot
	say "Copy complete"

run:
	# Run jar on EV3
	$(SSH_PREFIX) ssh robot@$(EV3_NAME) java -jar $(JAR_NAME)

debug:
	# Debug jar on EV3
	$(SSH_PREFIX) ssh robot@$(EV3_NAME) java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005 -jar $(JAR_NAME)

logging:
	$(SSH_PREFIX) scp $(LOG_PROP_NAME) robot@$(EV3_NAME):/home/robot
	$(SSH_PREFIX) ssh robot@$(EV3_NAME) java -Djava.util.logging.config.file=$(LOG_PROP_NAME) -jar $(JAR_NAME)


