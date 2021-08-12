#! /bin/sh -e

[ "x$1" = "x" ] && requests=1000 || requests=$1
[ "x$2" = "x" ] && concurrency=5 || concurrency=$2
echo ab -n $requests -c $concurrency -p post-body.txt http://jdevgarden:8000/myapp 2>&1 | tee myapp.log
ab -n $requests -c $concurrency -p post-body.txt http://jdevgarden:8000/myapp 2>&1 | tee myapp.log
