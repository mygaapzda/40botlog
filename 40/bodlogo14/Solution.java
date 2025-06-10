import java.io.*;
import java.util.*;

class Result {

    public static int twoPluses(List<String> grid) {
        int n = grid.size();
        int m = grid.get(0).length();
        char[][] g = new char[n][m];

        // Convert input List<String> to 2D char array
        for (int i = 0; i < n; i++) {
            g[i] = grid.get(i).toCharArray();
        }

        List<int[]> pluses = new ArrayList<>();

        // Find all possible pluses in the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int size = 0;
                while (isValidPlus(g, i, j, size)) {
                    pluses.add(new int[]{i, j, size});
                    size++;
                }
            }
        }

        int maxProduct = 0;

        // Check all combinations of two non-overlapping pluses
        for (int i = 0; i < pluses.size(); i++) {
            for (int j = i + 1; j < pluses.size(); j++) {
                int[] a = pluses.get(i);
                int[] b = pluses.get(j);
                Set<String> p1 = getCells(a[0], a[1], a[2]);
                Set<String> p2 = getCells(b[0], b[1], b[2]);

                boolean overlap = false;
                for (String cell : p1) {
                    if (p2.contains(cell)) {
                        overlap = true;
                        break;
                    }
                }

                if (!overlap) {
                    int areaA = 4 * a[2] + 1;
                    int areaB = 4 * b[2] + 1;
                    maxProduct = Math.max(maxProduct, areaA * areaB);
                }
            }
        }

        return maxProduct;
    }

    // Check if a plus of size `size` can be placed at (x, y)
    private static boolean isValidPlus(char[][] grid, int x, int y, int size) {
        int n = grid.length;
        int m = grid[0].length;

        if (x - size < 0 || x + size >= n || y - size < 0 || y + size >= m) return false;

        for (int i = -size; i <= size; i++) {
            if (grid[x + i][y] != 'G' || grid[x][y + i] != 'G') return false;
        }
        return true;
    }

    // Get the set of cells occupied by the plus at (x, y) of given size
    private static Set<String> getCells(int x, int y, int size) {
        Set<String> cells = new HashSet<>();
        cells.add(x + "," + y);
        for (int i = 1; i <= size; i++) {
            cells.add((x + i) + "," + y);
            cells.add((x - i) + "," + y);
            cells.add(x + "," + (y + i));
            cells.add(x + "," + (y - i));
        }
        return cells;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] dimensions = bufferedReader.readLine().trim().split(" ");
        int n = Integer.parseInt(dimensions[0]);
        int m = Integer.parseInt(dimensions[1]);

        List<String> grid = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String row = bufferedReader.readLine();
            grid.add(row);
        }

        int result = Result.twoPluses(grid);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
