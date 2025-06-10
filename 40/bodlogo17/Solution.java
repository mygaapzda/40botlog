import java.io.*;
import java.util.*;

class Result {

    /*
     * Complete the 'countSort' function below.
     *
     * The function accepts 2D_STRING_ARRAY arr as parameter.
     */
    public static void countSort(List<List<String>> arr) {
        // We'll use 100 buckets (from 0 to 99) since the problem input values are expected in that range
        List<List<String>> buckets = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            buckets.add(new ArrayList<String>());
        }

        // According to the problem (like HackerRank count sort), the first half of the inputs should be replaced by "-"
        int n = arr.size();
        for (int i = 0; i < n; i++) {
            int index = Integer.parseInt(arr.get(i).get(0));
            String value = (i < n / 2) ? "-" : arr.get(i).get(1);
            buckets.get(index).add(value);
        }

        // Print all the strings in order of the buckets
        StringBuilder result = new StringBuilder();
        for (List<String> bucket : buckets) {
            for (String str : bucket) {
                result.append(str).append(" ");
            }
        }

        // Print the result (trim trailing space)
        System.out.println(result.toString().trim());
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(Arrays.asList(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")));
        }

        Result.countSort(arr);

        bufferedReader.close();
    }
}
