#!/usr/bin/env bash

PARALLEL_COUNT=100
ENDPOINT="localhost:8081/bad-example"

for i in $(seq 1 $PARALLEL_COUNT); do
  {
    curl -s -w "\n[Request $i] Elapsed: %{time_total}s\n---\n" "http://$ENDPOINT"
  } &
done

wait
echo "All $PARALLEL_COUNT requests completed"
