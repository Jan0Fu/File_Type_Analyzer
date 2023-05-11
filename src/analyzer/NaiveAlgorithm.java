package analyzer;

public class NaiveAlgorithm implements Algorithm {

    @Override
    public boolean patternSearch(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();
        for (int i = 0; i < textLength - patternLength + 1; i++) {
            for (int j = 0; j < patternLength; j++) {
                if (!(text.charAt(i + j) == pattern.charAt(j))) {
                    break;
                }
                else if (j == patternLength - 1) return true;
            }
        }
        return false;
    }
}
