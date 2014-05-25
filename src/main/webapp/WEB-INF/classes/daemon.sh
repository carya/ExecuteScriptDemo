#!/bin/bash

case "$1" in
    "start")
        echo "start success."
        ;;
    "stop")
        echo "stop success."
        ;;
    "restart")
        echo "restart success."
        ;;
    *)
        echo "unsupport commond"
        ;;
esac
