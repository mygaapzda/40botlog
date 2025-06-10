import java.io.*;
import java.util.*;
import java.util.Arrays;

class Result {

    /*
     * Complete the 'organizingContainers' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts 2D_INTEGER_ARRAY container as parameter.
     */

    public static String organizingContainers(List<List<Integer>> container) {
        int n = container.size();

        int[] containerCapacities = new int[n];
        int[] ballTypeCounts = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                containerCapacities[i] += container.get(i).get(j);
                ballTypeCounts[j] += container.get(i).get(j);
            }
        }

        Arrays.sort(containerCapacities);
        Arrays.sort(ballTypeCounts);

        for (int i = 0; i < n; i++) {
            if (containerCapacities[i] != ballTypeCounts[i]) {
                return "Impossible";
            }
        }

        return "Possible";
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        for (int qItr = 0; qItr < q; qItr++) {
            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<List<Integer>> container = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String[] containerRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                List<Integer> containerRowItems = new ArrayList<>();

                for (int j = 0; j < n; j++) {
                    int containerItem = Integer.parseInt(containerRowTempItems[j]);
                    containerRowItems.add(containerItem);
                }

                container.add(containerRowItems);
            }

            String result = Result.organizingContainers(container);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
