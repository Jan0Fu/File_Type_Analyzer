package analyzer;

public class RabinKarpAlgorithm implements Algorithm {

    @Override
    public boolean patternSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        if (m > n) {
            return false;
        }
        int patternHash = hash(pattern);
        int currentHash = hash(text.substring(0, m));

        for (int i = 0; i < n - m + 1; i++) {
            if (currentHash == patternHash && text.substring(i, i + m).equals(pattern)) {
                return true;
            }
            if (i < n - m) {
                currentHash = rehash(currentHash, text.charAt(i), text.charAt(i + m), m);
            }
        }
        return false;
    }

    private static int hash(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash * 31 + str.charAt(i)) % 101;
        }
        return hash;
    }

    private static int rehash(int hash, char left, char right, int m) {
        int newHash = hash - left * (int) Math.pow(31, m - 1);
        newHash = newHash * 31 + right;
        return (newHash % 101 + 101) % 101;
    }
}
