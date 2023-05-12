package analyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        if (args.length < 2) {
            System.out.println("Please provide all arguments");
            System.exit(0);
        }

        String algorithm = "--KMP";
        String patternPath = args[1];
        File folder = new File(args[0]);
        File[] files = folder.listFiles();

        FileAnalyzer analyzer = new FileAnalyzer(algorithm);
        List<Callable<String>> callables = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        if (files != null) {
            for (File file: files) {
                callables.add(() -> analyzer.searchFile(file, patternPath));
            }

            List<Future<String>> results = executor.invokeAll(callables);
            for (var result: results) {
                System.out.println(result.get());
            }
            executor.shutdown();
        }
    }
}


