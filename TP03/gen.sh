#!/bin/sh

mkdir data
for nbGroup in $(echo 20 40 80 160); do
    for maxGroupeSize in $(echo 1 3 5); do
        for p in $(echo 0.3 0.6); do
            for i in $(seq 1 5); do
                python3 ./gen.py $nbGroup $maxGroupeSize $p > ./data/$nbGroup\_$maxGroupeSize\_$p.$i
            done
         done
    done
done