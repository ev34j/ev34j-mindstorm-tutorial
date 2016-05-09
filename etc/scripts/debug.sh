#!/bin/sh

java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005 -jar myev3robot-jar-with-dependencies.jar