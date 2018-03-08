#!/usr/bin/env bash
docker run -it --rm --name survey-db -p 9042:9042 cassandra:3.10
