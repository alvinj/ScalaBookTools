#!/bin/sh

EPUB_SCRIPT=createEpub.sh
rm $EPUB_SCRIPT

cd MarkdownFiles

./1copyMdFilesToHere.sh
./2sedAllFiles.sh
./3fixMdHeaderAndTitle.sh
./4generatePandocEpubScript.sh

# come back here
cd ..

# createEpub.sh is generated by the 4th script above
echo "creating ScalaBook.epub ..."
./${EPUB_SCRIPT}


