import java.io.*;

class Result {
    public static String encryption(String s) {
        s = s.replaceAll("\\s+", "");
        int len = s.length();
        int rows = (int) Math.floor(Math.sqrt(len));
        int cols = (int) Math.ceil(Math.sqrt(len));

        if (rows * cols < len) {
            rows++;
        }

        StringBuilder encrypted = new StringBuilder();

        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                int index = r * cols + c;
                if (index < len) {
                    encrypted.append(s.charAt(index));
                }
            }
            encrypted.append(" ");
        }

        return encrypted.toString().trim();
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        String result = Result.encryption(s);
        System.out.println(result);
        bufferedReader.close();
    }
}
