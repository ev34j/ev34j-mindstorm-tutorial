# EV3 hostname -- if you cannot see the EV3 hostname on your network, you can also use an IP address here
EV3_HOSTNAME = ev3dev1
#EV3_HOSTNAME = brickpi

# EV3 password
EV3_PASSWORD = maker

# This name must match the <finalName> value in pom.xml
JAR_NAME = ev3robot-jar-with-dependencies.jar

# Logging file name
LOG_PROP_NAME = logging.properties

# If you use ssh-keygen, comment out the next line and uncomment the one following it
SSH_PREFIX = sshpass -p $(EV3_PASSWORD)
#SSH_PREFIX =

default: build scp

clean:
	# Clear target output directory
	mvn clean

build:
	# Build jar file
	mvn package
	say "Build complete"

scp:
	# Copy jar file to EV3
	$(SSH_PREFIX) scp target/$(JAR_NAME) robot@$(EV3_HOSTNAME):/home/robot
	say "Copy complete"

run:
	# Run jar on EV3
	$(SSH_PREFIX) ssh robot@$(EV3_HOSTNAME) java -jar $(JAR_NAME)

debug:
	# Debug jar on EV3
	$(SSH_PREFIX) ssh robot@$(EV3_HOSTNAME) java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005 -jar $(JAR_NAME)

logging:
	$(SSH_PREFIX) scp etc/$(LOG_PROP_NAME) robot@$(EV3_HOSTNAME):/home/robot
	$(SSH_PREFIX) ssh robot@$(EV3_HOSTNAME) java -Djava.util.logging.config.file=$(LOG_PROP_NAME) -jar $(JAR_NAME)

kill:
	# Kill java process on EV3
	$(SSH_PREFIX) ssh robot@$(EV3_HOSTNAME) kill -9 $(ps aux | grep 'java' | awk '{print $2}')

copy-scripts:
	$(SSH_PREFIX) scp etc/scripts/*.sh robot@$(EV3_HOSTNAME):/home/robot

