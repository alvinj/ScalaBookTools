#!/bin/sh

#SRC_DIR="../../markdown"
SRC_DIR="/Users/al/Projects/HelloScala2ScalaLang.org/docs.scala-lang/_overviews/scala-book"

echo "removing old markdown files"
rm *.md *.bak 2> /dev/null

echo "copying all markdown files to current directory"
cp ${SRC_DIR}/* .

