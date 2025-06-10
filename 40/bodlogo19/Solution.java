import java.io.*;
import java.util.*;

class Result {

    public static int lilysHomework(List<Integer> arr) {
        List<Integer> sortedAsc = new ArrayList<>(arr);
        Collections.sort(sortedAsc);

        List<Integer> sortedDesc = new ArrayList<>(arr);
        Collections.sort(sortedDesc, Collections.reverseOrder());

        int swapsAsc = countSwaps(new ArrayList<>(arr), sortedAsc);
        int swapsDesc = countSwaps(new ArrayList<>(arr), sortedDesc);

        return Math.min(swapsAsc, swapsDesc);
    }

    private static int countSwaps(List<Integer> original, List<Integer> target) {
        int n = original.size();
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(original.get(i), i);
        }

        int swaps = 0;
        for (int i = 0; i < n; i++) {
            if (!original.get(i).equals(target.get(i))) {
                swaps++;

                int toSwapIdx = indexMap.get(target.get(i));
                int valAtI = original.get(i);

                // Update the map
                indexMap.put(valAtI, toSwapIdx);
                indexMap.put(target.get(i), i);

                // Swap elements in original
                original.set(toSwapIdx, valAtI);
                original.set(i, target.get(i));
            }
        }
        return swaps;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // Number of elements
        int n = Integer.parseInt(bufferedReader.readLine().trim());

        // Read the array
        String[] arrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(arrTemp[i]));
        }

        // Call the function
        int result = Result.lilysHomework(arr);

        // Print result
        System.out.println(result);

        bufferedReader.close();
    }
}
