package analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class FileAnalyzer {

    private final Algorithm algorithm;

    public FileAnalyzer(String algorithm) {
        switch (algorithm) {
            case "--naive" -> this.algorithm = new NaiveAlgorithm();
            case "--KMP" -> this.algorithm = new KMPalgorithm();
            default -> this.algorithm = new KMPalgorithm();
        }
    }

    public String searchFile(File file, String pattern, String fileType) {
        boolean isFound = false;
        long startTime = System.nanoTime();

        try (InputStream inputStream = new FileInputStream(file)) {
            int BUFFER_SIZE = 4096;
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                if (algorithm.patternSearch(new String(buffer), pattern)) {
                    isFound = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        double elapsedTime = (System.nanoTime() - startTime) / 1_000_000_000.0;
        return isFound ? (file.getName() + ": " + fileType) : (file.getName() + ": Unknown file type");
    }
}
