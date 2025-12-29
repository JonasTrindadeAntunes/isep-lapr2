package app.domain.model;

import app.domain.shared.MessageBundle;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * The interface Load from csv.
 */
public interface LoadFromCSV {

    /**
     * Gets list of files.
     *
     * @param pathh the pathh
     * @return the list of files
     */
    static List<String> getListOfFiles(String pathh) {

        try {
            List<File> files = Files.list(Paths.get(pathh)).map(Path::toFile)
                    .filter(File::isFile)
                    .collect(Collectors.toList());


            List<String> listOfFilesName = new ArrayList<>(files.size());

            for (Object file : files) {
                String path = file.toString();
                Path phatToAFile = Paths.get(path);
                if (path.matches(".*\\.csv"))
                    listOfFilesName.add(phatToAFile.getFileName().toString());
            }

            return listOfFilesName;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


    /**
     * Load from csv
     *
     * @param fileName the file name
     * @return the input stream
     */
    static InputStream loadFromCSV(String fileName) {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException(MessageBundle.getString("filenotfound"));
        } else {

            return inputStream;

        }

    }

    static InputStream loadFromCSV(File file) {
        try {
            FileInputStream in = new FileInputStream(file);
            if(in == null){
                throw new IllegalArgumentException(MessageBundle.getString("filenotfound"));
            }else {
                return in;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
