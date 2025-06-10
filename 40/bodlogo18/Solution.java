import java.io.*;
import java.util.List;
import java.util.ArrayList;

class Result {

    public static int activityNotifications(List<Integer> expenditure, int d) {
        int notifications = 0;
        int[] count = new int[201];

        for (int i = 0; i < d; i++) {
            count[expenditure.get(i)]++;
        }

        for (int i = d; i < expenditure.size(); i++) {
            double median = getMedian(count, d);
            int current = expenditure.get(i);

            if (current >= 2 * median) {
                notifications++;
            }

            count[expenditure.get(i - d)]--;
            count[current]++;
        }

        return notifications;
    }

    private static double getMedian(int[] count, int d) {
        int sum = 0;
        int median1 = 0, median2 = 0;

        if (d % 2 == 1) {
            int medianPos = d / 2 + 1;
            for (int i = 0; i < count.length; i++) {
                sum += count[i];
                if (sum >= medianPos) {
                    return i;
                }
            }
        } else {
            int medianPos1 = d / 2;
            int medianPos2 = medianPos1 + 1;
            for (int i = 0; i < count.length; i++) {
                sum += count[i];
                if (sum >= medianPos1 && median1 == 0) {
                    median1 = i;
                }
                if (sum >= medianPos2) {
                    median2 = i;
                    break;
                }
            }
            return (median1 + median2) / 2.0;
        }
        return 0; // fallback
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);
        int d = Integer.parseInt(firstMultipleInput[1]);

        String[] expenditureTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> expenditure = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            expenditure.add(Integer.parseInt(expenditureTemp[i]));
        }

        int result = Result.activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
