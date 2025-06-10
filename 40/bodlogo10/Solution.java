import java.io.*;
import java.util.*;

class Result {
    public static String gridSearch(List<String> G, List<String> P) {
        int R = G.size();
        int C = G.get(0).length();
        int r = P.size();
        int c = P.get(0).length();

        for (int i = 0; i <= R - r; i++) {
            for (int j = 0; j <= C - c; j++) {
                boolean found = true;
                for (int k = 0; k < r; k++) {
                    String sub = G.get(i + k).substring(j, j + c);
                    if (!sub.equals(P.get(k))) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return "YES";
                }
            }
        }
        return "NO";
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            String[] firstMultipleInput = bufferedReader.readLine().split(" ");
            int R = Integer.parseInt(firstMultipleInput[0]);
            int C = Integer.parseInt(firstMultipleInput[1]);

            List<String> G = new ArrayList<>();
            for (int i = 0; i < R; i++) {
                G.add(bufferedReader.readLine());
            }

            String[] secondMultipleInput = bufferedReader.readLine().split(" ");
            int r = Integer.parseInt(secondMultipleInput[0]);
            int c = Integer.parseInt(secondMultipleInput[1]);

            List<String> P = new ArrayList<>();
            for (int i = 0; i < r; i++) {
                P.add(bufferedReader.readLine());
            }

            String result = Result.gridSearch(G, P);
            System.out.println(result);
        }

        bufferedReader.close();
    }
}
