package analyzer;

public class Main {

    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("Please provide all(3) arguments");
            System.exit(0);
        }

        String algorithm = args[0];
        String file = args[1];
        String pattern = args[2];
        String fileType = args[3];
        FileAnalyzer analyzer = new FileAnalyzer(algorithm);

        analyzer.searchFile(file, pattern, fileType);
    }
}


