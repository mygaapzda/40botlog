import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = bufferedReader.readLine().trim().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        String[] nums = bufferedReader.readLine().trim().split(" ");
        List<Integer> s = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            s.add(Integer.parseInt(nums[i]));
        }

        int result = Result.nonDivisibleSubset(k, s);
        System.out.println(result);
    }
}

class Result {
    public static int nonDivisibleSubset(int k, List<Integer> s) {
        int[] remainderCounts = new int[k];
        for (int num : s) {
            remainderCounts[num % k]++;
        }

        int count = 0;
        if (remainderCounts[0] > 0) count++;

        for (int i = 1; i <= k / 2; i++) {
            if (i == k - i) {
                count++;
            } else {
                count += Math.max(remainderCounts[i], remainderCounts[k - i]);
            }
        }

        return count;
    }
}
