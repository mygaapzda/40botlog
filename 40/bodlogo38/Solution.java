import java.io.*;
import java.util.*;

class Result {

    public static int steadyGene(String gene) {
        int n = gene.length();
        int target = n / 4;

        // Count frequency of each nucleotide
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : gene.toCharArray()) {
            freq.put(c, freq.containsKey(c) ? freq.get(c) + 1 : 1);
        }

        // Helper to get frequency or 0 if absent
        int freqA = freq.containsKey('A') ? freq.get('A') : 0;
        int freqC = freq.containsKey('C') ? freq.get('C') : 0;
        int freqG = freq.containsKey('G') ? freq.get('G') : 0;
        int freqT = freq.containsKey('T') ? freq.get('T') : 0;

        // If already steady
        if (freqA == target && freqC == target && freqG == target && freqT == target) {
            return 0;
        }

        int minLength = n;
        int left = 0;
        char[] arr = gene.toCharArray();

        for (int right = 0; right < n; right++) {
            char rChar = arr[right];
            freq.put(rChar, freq.get(rChar) - 1);

            while (left <= right &&
                (freq.containsKey('A') ? freq.get('A') : 0) <= target &&
                (freq.containsKey('C') ? freq.get('C') : 0) <= target &&
                (freq.containsKey('G') ? freq.get('G') : 0) <= target &&
                (freq.containsKey('T') ? freq.get('T') : 0) <= target) {

                minLength = Math.min(minLength, right - left + 1);
                char lChar = arr[left];
                freq.put(lChar, freq.get(lChar) + 1);
                left++;
            }
        }

        return minLength;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        String gene = bufferedReader.readLine();

        int result = Result.steadyGene(gene);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
