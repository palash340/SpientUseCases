package sortfile;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Palash on 10/9/17.
 */
public class SortFiles {
    public static void main(String[] args) throws IOException {
        SortFiles sf = new SortFiles();
        DirectoryStream<Path> fileList = sf.listAllFiles("src/main/resources", "split");
        sf.sortFile(fileList);
    }

    public void sortFile(DirectoryStream<Path> fileList){
        for(Path entry: fileList){
            System.out.println("entry = " + entry.getFileName());
        }

        //while(iterator.hasNext()){
          //  iterator.next();
        //}

        fileList.forEach(filePath -> {
            try{
                System.out.println("filePath.toAbsolutePath() = " + filePath.toAbsolutePath());
                // Read file and sort in-memory
                List<String> wordList = new ArrayList<String>();
                Files.lines(Paths.get(filePath.toUri())).forEach(inputLine -> {
                    String[] words = inputLine.split("\\s+");
                    for(String word : words)
                        wordList.add(word);
                });
                // Sort list
                Collections.sort(wordList);
                // Write to a temp file sorted file
                System.out.println("wordList = " + wordList);
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        });
    }


    public void mergeSortedFiles(List<File> fileList){

    }

    public DirectoryStream<Path> listAllFiles(String filePath, String filePattern) throws IOException {
        return Files.newDirectoryStream(Paths.get(filePath), path -> path.toString().startsWith(filePattern));
    }
}

class MyFileNameFilter implements FilenameFilter {

    private String prefix;

    public MyFileNameFilter(String prefix) {
        this.prefix = prefix.toLowerCase();
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().startsWith(prefix);
    }

}
