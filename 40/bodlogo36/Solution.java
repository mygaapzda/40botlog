import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(bufferedReader.readLine().trim());

        for (int qItr = 0; qItr < q; qItr++) {
            String s = bufferedReader.readLine();
            int result = Result.sherlockAndAnagrams(s);
            System.out.println(result);
        }

        bufferedReader.close();
    }
}

class Result {

    public static int sherlockAndAnagrams(String s) {
        Map<String, Integer> substringCount = new HashMap<>();
        int totalPairs = 0;

        for (int length = 1; length < s.length(); length++) {
            for (int start = 0; start + length <= s.length(); start++) {
                String substring = s.substring(start, start + length);
                char[] chars = substring.toCharArray();
                Arrays.sort(chars);
                String signature = new String(chars);

                int count = 0;
                if (substringCount.containsKey(signature)) {
                    count = substringCount.get(signature);
                }
                totalPairs += count;
                substringCount.put(signature, count + 1);
            }
        }

        return totalPairs;
    }
}
