import java.io.*;
import java.util.*;

class Result {

    public static int minimumMoves(List<String> grid, int startX, int startY, int goalX, int goalY) {
        int n = grid.size();
        boolean[][] visited = new boolean[n][n];
        int[][] dist = new int[n][n];

        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        dist[startX][startY] = 0;

        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1];

            for (int[] dir : directions) {
                int newX = x, newY = y;

                // Glide in current direction until wall or edge
                while (true) {
                    newX += dir[0];
                    newY += dir[1];

                    if (newX < 0 || newX >= n || newY < 0 || newY >= n || grid.get(newX).charAt(newY) == 'X') {
                        break;
                    }

                    if (dist[newX][newY] > dist[x][y] + 1) {
                        dist[newX][newY] = dist[x][y] + 1;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
        }

        return dist[goalX][goalY];
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        List<String> grid = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            grid.add(bufferedReader.readLine());
        }

        String[] input = bufferedReader.readLine().split(" ");
        int startX = Integer.parseInt(input[0]);
        int startY = Integer.parseInt(input[1]);
        int goalX = Integer.parseInt(input[2]);
        int goalY = Integer.parseInt(input[3]);

        int result = Result.minimumMoves(grid, startX, startY, goalX, goalY);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
