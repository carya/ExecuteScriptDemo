#!/bin/bash

case "$1" in
    "start")
    	sudo snmpd
    	if [ $? -eq 0 ]; then
    		echo "start snmpd success."
        fi
        ;;
    "stop")
    	pid=$(sudo lsof -i:161 | grep snmpd | awk '{print $2}')
    	sudo kill -9 $pid
    	if [ $? -eq 0 ]; then
    		echo "stop snmpd success."
    	fi
        ;;
    *)
        echo "unsupport commond"
        ;;
esac
