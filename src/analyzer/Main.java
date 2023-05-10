package analyzer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Please provide all(3) arguments");
            System.exit(0);
        }
        String path = args[0];
        String pattern = args[1];
        String fileType = args[2];
        System.out.println(FileAnalyzer.isPatternPresent(path, pattern) ? fileType : "Unknown file type");
    }
}

class FileAnalyzer {

    private final static int BUFFER_SIZE = 4096;
    private static boolean isFound = false;

    public static boolean isPatternPresent(String file, String pattern) {
        try (InputStream inputStream = new FileInputStream(file)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String byteString = new String(buffer);
                int index = byteString.indexOf(pattern);
                if (index != -1) {
                    isFound = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isFound;
    }
}

