How to build EPUB and MOBI versions of Scala Book
=================================================

Here are a few notes on how to build EPUB and MOBI files.



The build process
-----------------
- `cd` into the *epub* directory; the EPUB and MOBI process currently starts there
- `cd` into the *MarkdownFiles* directory (*$PROJECT_HOME/epub/MarkdownFiles*)
- `cp` the latest Scala Book markdown files that were created for the website into that directory
- `cd` back up to the *epub* directory (*$PROJECT_HOME/epub*)
- run the *1createEpub.sh* script to generate the EPUB file
- after that works, run the *1createMobi.sh* script to generate the MOBI file



Software requirements
---------------------
- Pandoc
- Kindlegen
- Unix utilities like `find` and `sed`
- Scala



Other notes
-----------
- I just put a *cover.jpg* file in here to verify that the process works



Pandoc/epub information
-----------------------

- https://pandoc.org/epub.html



ePub Example: https://medium.com/@davidgrophland/making-an-ebook-from-markdown-to-kindle-cf224326b1a2
---------------------------------------------------------------------------------------
- command:

pandoc -o Book.epub metadata.yaml Book.md 
    --toc --epub-stylesheet=stylesheet.css --toc-depth=2

- metadata file:

````
---
title:
- type: main
  text: The Night Alphabet
creator:
- role: author
  text: David Donachie
identifier:
- scheme: DOI
  text: doi:10.234234.234/33
publisher:  Solipsist Press
rights: Copyright ©2017 David Donachie
date: 2017-09-24
...
````

- their *stylesheet.css* file:

````
    body { font-family: Georgia,"Times New Roman",Times; margin: 5%; text-align: justify; font-size: medium; }
    code { font-family: monospace; }
    section h1 { text-align: left; font-size: 4.5em; font-weight: bold; margin-bottom: 0; padding-bottom: 0; font-style: italic; }
    h1 + p { margin-top: 0; padding-top: 0; }
    h2 { text-align: left; font-size: 1.5em; }
    h3 { text-align: left; font-size: 1em; font-weight: bold; }
    h4 { text-align: left; }
    h5 { text-align: left; }
    h6 { text-align: left; }
    h1.title { text-align: left; font-size: 2em; margin-top: 50%; }
    h2.author { font-size: 1.3em; }
    h3.date { }
    div.title h1 { text-align: left; font-size: 2em; margin-top: 50%; }
    ol.toc { padding: 0; margin-left: 1em; }
    ol.toc li { list-style-type: none; margin: 0; padding: 0; }
    hr { border: none; text-align: center; }
    hr:after { content: '* * *'; }
    #toc > ol > li > a { display: none; }
    #the-night-alphabet blockquote { margin-top: 50%; font-style: italic; }
    #the-night-alphabet h1 { text-align: center; font-size: 4em; page-break-after: always; }
````












