import java.io.*;
import java.util.*;

class Result {

    public static int shortPalindrome(String s) {
        int mod = 1000000007;
        int n = s.length();

        int[] leftCount = new int[26];   // counts chars before i
        int[] rightCount = new int[26];  // counts chars after i
        int[][] pairCount = new int[26][26]; // counts pairs formed so far

        for (int i = 0; i < n; i++) {
            rightCount[s.charAt(i) - 'a']++;
        }

        long result = 0;

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            rightCount[c]--;

            for (int j = 0; j < 26; j++) {
                // Count palindromes formed ending at c, starting with j
                result = (result + ((long) pairCount[j][c] * rightCount[j]) % mod) % mod;
            }

            for (int j = 0; j < 26; j++) {
                // Add pairs where first char = j and second char = c
                pairCount[j][c] = (pairCount[j][c] + leftCount[j]) % mod;
            }

            leftCount[c]++;
        }

        return (int) result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = bufferedReader.readLine();

        int result = Result.shortPalindrome(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
