import java.io.*;
import java.util.*;

class Result {

    public static List<String> bomberMan(int n, List<String> grid) {
        if (n == 1) return grid;

        int rows = grid.size();
        int cols = grid.get(0).length();

        List<String> fullGrid = fillGrid(rows, cols, 'O');
        if (n % 2 == 0) return fullGrid;

        char[][] first = explode(grid);
        char[][] second = explode(toList(first));

        if ((n - 3) % 4 == 0) return toList(first);
        else return toList(second);
    }

    private static char[][] explode(List<String> grid) {
        int rows = grid.size();
        int cols = grid.get(0).length();
        char[][] result = new char[rows][cols];

        for (char[] row : result) {
            Arrays.fill(row, 'O');
        }

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid.get(i).charAt(j) == 'O') {
                    result[i][j] = '.';
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dx[d];
                        int nj = j + dy[d];
                        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols) {
                            result[ni][nj] = '.';
                        }
                    }
                }
            }
        }
        return result;
    }

    private static List<String> toList(char[][] grid) {
        List<String> list = new ArrayList<>();
        for (char[] row : grid) {
            list.add(new String(row));
        }
        return list;
    }

    private static List<String> fillGrid(int rows, int cols, char ch) {
        List<String> full = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            char[] row = new char[cols];
            Arrays.fill(row, ch);
            full.add(new String(row));
        }
        return full;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r = Integer.parseInt(firstMultipleInput[0]);
        int c = Integer.parseInt(firstMultipleInput[1]);
        int n = Integer.parseInt(firstMultipleInput[2]);

        List<String> grid = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            String gridItem = bufferedReader.readLine();
            grid.add(gridItem);
        }

        List<String> result = Result.bomberMan(n, grid);

        for (String line : result) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
