package analyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        if (args.length < 3) {
            System.out.println("Please provide all arguments");
            System.exit(0);
        }

        String algorithm = "--KMP";
        File folder = new File(args[0]);
        File[] files = folder.listFiles();
        String pattern = args[1];
        String fileType = args[2];

        FileAnalyzer analyzer = new FileAnalyzer(algorithm);
        List<Callable<String>> callables = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        if (files != null) {
            for (File file: files) {
                callables.add(() -> analyzer.searchFile(file, pattern, fileType));
            }

            List<Future<String>> results = executor.invokeAll(callables);
            for (var result: results) {
                System.out.println(result.get());
            }
            executor.shutdown();
        }
    }
}


