import scala.io.Source
import java.io._
import java.nio.file.{Files, Paths, StandardCopyOption}
import scala.collection.mutable.ArrayBuffer
import util.control.Breaks._

/**
 * Loop through all of the MD files, deleting their header sections
 * and adding a `#` title tag to each.
 */
object ConvertMarkdownFiles extends App {

    val filenames = Source.fromFile("LIST_OF_FILES_IN_ORDER")
        .getLines
        .filter(_.trim != "")
        .toList

    var fileCounter = 1
    // for each MD file ...
    for (mdFilename <- filenames) {
        println(s"\nworking on $mdFilename ...")

        val fileContentsAsSeq = readFile(mdFilename)

        val title = getTitleFromMarkdownContents(fileContentsAsSeq)
        val completeTitleLine = createTitleLine(title, fileCounter)

        val allLinesAfterHeader = getAllLinesAfterHeader(fileContentsAsSeq)

        // put the title line before all other lines
        val allLinesWithTitle = Seq(completeTitleLine, "") ++: allLinesAfterHeader

        // write the new contents to the same file
        writeFile(mdFilename, allLinesWithTitle)
        fileCounter += 1
    }


    // this includes a kludge because of what i had to do for the “Prelude” title
    def createTitleLine(titleField: String, fileCounter: Int): String = 
        if (titleField.startsWith("Prelude")) {
            s"# ${fileCounter}. Prelude: A Taste of Scala\n"
        } else {
            s"# ${fileCounter}. $titleField\n"
        }


    def getAllLinesAfterHeader(fileContents: Seq[String]): Seq[String] = {
        val linesAsArrayBuffer = ArrayBuffer[String]()
        var threeDashCounter = 0
        for (line <- fileContents) {
            if (threeDashCounter == 2) {
                // you’re after the second `---` string, so
                // yield the remaining lines
                linesAsArrayBuffer += s"$line\n"
            }
            if (line.startsWith("---")) threeDashCounter += 1
        }
        // TODO necessary?
        linesAsArrayBuffer.toList
    }

    def getTitleFromMarkdownContents(fileContents: Seq[String]): String = {
        var title = ""
        breakable {
            for (line <- fileContents) {
                if (line.startsWith("title: ")) {
                    title = line.split(":")(1).trim
                    break
                }
            }
        }
        title
    }

    def readFile(filename: String): Seq[String] = {
        val bufferedSource = io.Source.fromFile(filename)
        val lines = (for (line <- bufferedSource.getLines()) yield line).toList
        bufferedSource.close
        lines
    }
    
    def writeFile(filename: String, lines: Seq[String]): Unit = {
        //println(s"ENTERED writeFile, writing $filename, contains ${lines.size} lines")
        val file = new File(filename)
        val bw = new BufferedWriter(new FileWriter(file))
        for (line <- lines) {
            bw.write(line)
        }
        bw.close()
    }

}




