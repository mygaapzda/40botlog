import java.io.*;
import java.util.*;

class Result {

    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
        Set<String> obstacleSet = new HashSet<>();
        for (List<Integer> obs : obstacles) {
            obstacleSet.add(obs.get(0) + "_" + obs.get(1));
        }

        int attackCount = 0;

        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        for (int[] dir : directions) {
            int row = r_q + dir[0];
            int col = c_q + dir[1];

            while (row >= 1 && row <= n && col >= 1 && col <= n && !obstacleSet.contains(row + "_" + col)) {
                attackCount++;
                row += dir[0];
                col += dir[1];
            }
        }

        return attackCount;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
        int n = Integer.parseInt(firstMultipleInput[0]);
        int k = Integer.parseInt(firstMultipleInput[1]);

        String[] queenPosition = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
        int r_q = Integer.parseInt(queenPosition[0]);
        int c_q = Integer.parseInt(queenPosition[1]);

        List<List<Integer>> obstacles = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            String[] obstaclePosition = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
            List<Integer> obstacle = new ArrayList<>();
            obstacle.add(Integer.parseInt(obstaclePosition[0]));
            obstacle.add(Integer.parseInt(obstaclePosition[1]));
            obstacles.add(obstacle);
        }

        int result = Result.queensAttack(n, k, r_q, c_q, obstacles);
        System.out.println(result);

        bufferedReader.close();
    }
}
