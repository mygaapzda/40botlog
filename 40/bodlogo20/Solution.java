import java.io.*;
import java.util.*;

class Result {

    public static String isValid(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (freq.containsKey(c)) {
                freq.put(c, freq.get(c) + 1);
            } else {
                freq.put(c, 1);
            }
        }

        Map<Integer, Integer> freqCount = new HashMap<>();
        for (int count : freq.values()) {
            if (freqCount.containsKey(count)) {
                freqCount.put(count, freqCount.get(count) + 1);
            } else {
                freqCount.put(count, 1);
            }
        }

        if (freqCount.size() == 1) {
            return "YES";
        } else if (freqCount.size() == 2) {
            List<Integer> keys = new ArrayList<>(freqCount.keySet());
            int freq1 = keys.get(0);
            int freq2 = keys.get(1);
            int count1 = freqCount.get(freq1);
            int count2 = freqCount.get(freq2);

            if ((freq1 == 1 && count1 == 1) || (freq2 == 1 && count2 == 1)) {
                return "YES";
            }

            if (Math.abs(freq1 - freq2) == 1 && (count1 == 1 || count2 == 1)) {
                return "YES";
            }
        }

        return "NO";
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String s = bufferedReader.readLine();
        String result = Result.isValid(s);

        System.out.println(result);
    }
}
