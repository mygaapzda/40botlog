import java.io.*;
import java.util.*;

class Result {

    public static String highestValuePalindrome(String s, int n, int k) {
        char[] chars = s.toCharArray();
        boolean[] changed = new boolean[n];  // track changed indices
        
        int left = 0;
        int right = n - 1;

        // First pass: make palindrome with minimum changes
        while (left < right) {
            if (chars[left] != chars[right]) {
                char maxChar = (char) Math.max(chars[left], chars[right]);
                chars[left] = maxChar;
                chars[right] = maxChar;
                changed[left] = true;
                changed[right] = true;
                k--;  // one change used
            }
            left++;
            right--;
        }
        
        if (k < 0) return "-1";  // not possible to make palindrome within k changes

        // Second pass: maximize palindrome value by changing digits to '9'
        left = 0;
        right = n - 1;
        while (left <= right && k > 0) {
            if (left == right) {
                // Middle element in odd length string
                if (chars[left] != '9' && k > 0) {
                    chars[left] = '9';
                    k--;
                }
            } else {
                if (chars[left] != '9') {
                    // If either side was changed in first pass, cost 1 change to upgrade both to '9'
                    // Else cost 2 changes (change both digits)
                    if (changed[left] || changed[right]) {
                        if (k >= 1) {
                            chars[left] = '9';
                            chars[right] = '9';
                            k--;
                        }
                    } else {
                        if (k >= 2) {
                            chars[left] = '9';
                            chars[right] = '9';
                            k -= 2;
                        }
                    }
                }
            }
            left++;
            right--;
        }

        return new String(chars);
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);
        int k = Integer.parseInt(firstMultipleInput[1]);

        String s = bufferedReader.readLine();

        String result = Result.highestValuePalindrome(s, n, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
