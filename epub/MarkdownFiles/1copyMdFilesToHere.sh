#!/bin/sh

SRC_DIR="../../markdown"

echo "removing old markdown files"
rm *.md *.bak 2> /dev/null

echo "copying all markdown files to current directory"
cp ${SRC_DIR}/* .

