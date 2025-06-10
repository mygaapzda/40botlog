import java.io.*;
import java.util.*;

class Result {

    /*
     * Complete the 'surfaceArea' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY A as parameter.
     */

    public static int surfaceArea(List<List<Integer>> A) {
        int H = A.size();
        int W = A.get(0).size();
        int area = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                int height = A.get(i).get(j);

                if (height > 0) {
                    // Top and bottom surfaces
                    area += 2;
                }

                // North side
                int northHeight = (i == 0) ? 0 : A.get(i - 1).get(j);
                area += Math.max(height - northHeight, 0);

                // South side
                int southHeight = (i == H - 1) ? 0 : A.get(i + 1).get(j);
                area += Math.max(height - southHeight, 0);

                // West side
                int westHeight = (j == 0) ? 0 : A.get(i).get(j - 1);
                area += Math.max(height - westHeight, 0);

                // East side
                int eastHeight = (j == W - 1) ? 0 : A.get(i).get(j + 1);
                area += Math.max(height - eastHeight, 0);
            }
        }

        return area;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int H = Integer.parseInt(firstMultipleInput[0]);

        int W = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> A = new ArrayList<>();

        for (int i = 0; i < H; i++) {
            String[] ARowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> ARowItems = new ArrayList<>();

            for (int j = 0; j < W; j++) {
                int AItem = Integer.parseInt(ARowTempItems[j]);
                ARowItems.add(AItem);
            }

            A.add(ARowItems);
        }

        int result = Result.surfaceArea(A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
