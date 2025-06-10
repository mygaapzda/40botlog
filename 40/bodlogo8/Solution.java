import java.io.*;
import java.util.*;

class Result {

    /*
     * Complete the 'biggerIsGreater' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING w as parameter.
     */

    public static String biggerIsGreater(String w) {
        char[] chars = w.toCharArray();
        int i = chars.length - 2;

        // Step 1: Find the first character from the end which is smaller than the next one
        while (i >= 0 && chars[i] >= chars[i + 1]) {
            i--;
        }

        // If no such character exists, then this is the last permutation
        if (i < 0) {
            return "no answer";
        }

        // Step 2: Find the smallest character on the right side of i that is bigger than chars[i]
        int j = chars.length - 1;
        while (chars[j] <= chars[i]) {
            j--;
        }

        // Step 3: Swap characters at i and j
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;

        // Step 4: Sort the substring after position i to get the next lexicographical permutation
        Arrays.sort(chars, i + 1, chars.length);

        return new String(chars);
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = Integer.parseInt(bufferedReader.readLine().trim());

        for (int TItr = 0; TItr < T; TItr++) {
            String w = bufferedReader.readLine();
            String result = Result.biggerIsGreater(w);
            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
