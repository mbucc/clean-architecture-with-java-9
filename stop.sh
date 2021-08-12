#! /bin/sh -e

ps a -o pid,args \
  | grep 'java .* --module com.example.main' \
  | grep -v grep \
  | awk '{print $1}' \
  | while read pid; do echo killing $pid; kill $pid; done
