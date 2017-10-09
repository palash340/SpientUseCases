package sortfile;

import java.io.File;
import java.io.FilenameFilter;
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
    }

    public List<File> listAllFiles(String filePath, String filePattern){
        File[] listOfFiles = new File(filePath).listFiles(new MyFileNameFilter(filePattern));
        List<File> fileList  = Arrays.asList(listOfFiles);
        return fileList;
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
