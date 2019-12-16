#!/bin/sh

for file in `ls -1 *.md`
do
    sed -i.bak -f _commands.sed $file
done


