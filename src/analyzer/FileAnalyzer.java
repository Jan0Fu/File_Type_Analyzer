package analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

class FileAnalyzer {

    private final Algorithm algorithm;

    public FileAnalyzer(String algorithm) {
        switch (algorithm) {
            case "--naive" -> this.algorithm = new NaiveAlgorithm();
            case "--kmp" -> this.algorithm = new KMPalgorithm();
            case "--rabin" ->this.algorithm = new RabinKarpAlgorithm();
            default -> this.algorithm = new KMPalgorithm();
        }
    }

    public String searchFile(File file, String patternPath) throws IOException {
        boolean isFound = false;
        String fileType = "";
        List<Pattern> patterns = PatternFile.getPatterns(patternPath);
        long startTime = System.nanoTime();

        try (InputStream inputStream = new FileInputStream(file)) {
            int BUFFER_SIZE = 4096;
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                for (var p: patterns)
                    if (algorithm.patternSearch(new String(buffer), p.getPattern())) {
                        isFound = true;
                        fileType = p.getFileType();
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
