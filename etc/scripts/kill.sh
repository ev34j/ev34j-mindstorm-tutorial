#!/bin/sh

kill -9 $(ps aux | grep 'java' | awk '{print $2}')