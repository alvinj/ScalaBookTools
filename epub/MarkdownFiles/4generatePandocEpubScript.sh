#!/bin/sh

# LIST_OF_FILES_IN_ORDER has a list of *.md files in the correct order

PANDOC_SCRIPT_NAME=../createEpub.sh
OUR_DIR_NAME=MarkdownFiles

echo "pandoc -o ScalaBook.epub \\" >  $PANDOC_SCRIPT_NAME
echo "  metadata.txt \\"           >> $PANDOC_SCRIPT_NAME

for infile in `cat LIST_OF_FILES_IN_ORDER`
do
    if [ ! -z "$infile" ]
    then
        echo "  ${OUR_DIR_NAME}/${infile} \\" >> $PANDOC_SCRIPT_NAME
    fi
done

chmod +x $PANDOC_SCRIPT_NAME

