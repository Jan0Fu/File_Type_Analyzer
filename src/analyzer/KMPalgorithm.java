package analyzer;

public class KMPalgorithm implements Algorithm {

    @Override
    public boolean patternSearch(String text, String pattern) {
        int[] lps = prefixFunction(pattern);
        int i = 0, j = 0;
        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
            if (j == pattern.length()) {
                return true;
            }
        }
        return false;
    }

    private int[] prefixFunction(String pattern) {
        int[] prefix = new int[pattern.length()];
        prefix[0] = 0;
        for (int i = 1; i < pattern.length(); i++) {
            int j = prefix[i - 1];
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = prefix[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            prefix[i] = j;
        }
        return prefix;
    }
}
