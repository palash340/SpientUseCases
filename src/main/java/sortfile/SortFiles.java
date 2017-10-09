package sortfile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Palash on 10/9/17.
 */
public class SortFiles {
    public static void main(String[] args) {
        SortFiles sf = new SortFiles();
        List<File> fileList = sf.listAllFiles("src/main/resources", "split");
        System.out.println("fileList = " + fileList);
        sf.sortFile(fileList);
    }

    public void sortFile(List<File> fileList){
        fileList.forEach(file -> {
            try{
                // Read file and sort in-memory
                List<String> wordList = new ArrayList<String>();
                Files.lines(Paths.get(file.getAbsolutePath())).forEach(inputLine -> {
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

    public List<File> listAllFiles(String filePath, String filePattern){
        File[] listOfFiles = new File(filePath).listFiles(new MyFileNameFilter(filePattern));
        return Arrays.asList(listOfFiles);
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
