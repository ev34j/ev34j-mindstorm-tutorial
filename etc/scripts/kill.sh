#!/bin/sh

kill -9 $(ps -aef | grep -v grep | grep 'java' | awk '{print $2}')