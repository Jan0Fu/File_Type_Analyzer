package analyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PatternFile {

    public static List<Pattern> getPatterns(String path) throws IOException {
        List<Pattern> patterns = new ArrayList<>();
        Files.readAllLines(Paths.get(path)).forEach(line -> {
            String[] parts = line.split(";");
            patterns.add(new Pattern(Integer.parseInt(parts[0]),
                    parts[1].replaceAll("\"", ""), parts[2].replaceAll("\"", "")));
        });
        patterns.sort((a, b) -> b.getPriority() - a.getPriority());
        return patterns;
    }
}
