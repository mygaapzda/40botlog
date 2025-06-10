import java.io.*;
import java.util.*;

class Result {

    public static int downToZero(int n) {
        // Queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        // Map to track visited nodes and minimum steps
        Map<Integer, Integer> visited = new HashMap<>();

        queue.add(n);
        visited.put(n, 0);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            int steps = visited.get(current);

            // If we've reached zero, return the number of steps
            if (current == 0) {
                return steps;
            }

            // Step 1: Subtract 1
            if (!visited.containsKey(current - 1)) {
                visited.put(current - 1, steps + 1);
                queue.add(current - 1);
            }

            // Step 2: Replace with max factor
            for (int i = 2; i * i <= current; i++) {
                if (current % i == 0) {
                    int factor = Math.max(i, current / i);
                    if (!visited.containsKey(factor)) {
                        visited.put(factor, steps + 1);
                        queue.add(factor);
                    }
                }
            }
        }

        return -1; // fallback, should not reach here
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        for (int qItr = 0; qItr < q; qItr++) {
            int n = Integer.parseInt(bufferedReader.readLine().trim());
            int result = Result.downToZero(n);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
