import java.io.*;
import java.util.*;

class Result {

    /*
     * Complete the 'almostSorted' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void almostSorted(List<Integer> arr) {
        int n = arr.size();
        List<Integer> sorted = new ArrayList<>(arr);
        Collections.sort(sorted);

        // If already sorted
        if (arr.equals(sorted)) {
            System.out.println("yes");
            return;
        }

        // Find the indices where arr differs from sorted
        List<Integer> diffIndices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!arr.get(i).equals(sorted.get(i))) {
                diffIndices.add(i);
            }
        }

        // If exactly two differences, try swap
        if (diffIndices.size() == 2) {
            int i = diffIndices.get(0);
            int j = diffIndices.get(1);
            System.out.println("yes");
            System.out.println("swap " + (i + 1) + " " + (j + 1));
            return;
        }

        // If more than 2 differences, try reverse subarray
        int start = diffIndices.get(0);
        int end = diffIndices.get(diffIndices.size() - 1);

        // Reverse the sublist in arr
        List<Integer> sub = arr.subList(start, end + 1);
        List<Integer> reversedSub = new ArrayList<>(sub);
        Collections.reverse(reversedSub);

        // Create a new list with reversed subarray and check if sorted
        List<Integer> temp = new ArrayList<>(arr);
        for (int i = 0; i < reversedSub.size(); i++) {
            temp.set(start + i, reversedSub.get(i));
        }

        if (temp.equals(sorted)) {
            System.out.println("yes");
            System.out.println("reverse " + (start + 1) + " " + (end + 1));
        } else {
            System.out.println("no");
        }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String[] arrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrTemp[i]);
            arr.add(arrItem);
        }

        Result.almostSorted(arr);

        bufferedReader.close();
    }
}
