import java.util.*;

class Result {

    public static String timeInWords(int h, int m) {
        String[] numbers = {
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
            "eighteen", "nineteen", "twenty"
        };

        String[] timeNumbers = new String[31];
        for (int i = 1; i <= 30; i++) {
            if (i <= 20) {
                timeNumbers[i] = numbers[i];
            } else {
                timeNumbers[i] = "twenty " + numbers[i - 20];
            }
        }

        if (m == 0) {
            return numbers[h] + " o' clock";
        } else if (m == 15) {
            return "quarter past " + numbers[h];
        } else if (m == 30) {
            return "half past " + numbers[h];
        } else if (m == 45) {
            return "quarter to " + numbers[h + 1];
        } else if (m < 30) {
            return timeNumbers[m] + (m == 1 ? " minute" : " minutes") + " past " + numbers[h];
        } else {
            return timeNumbers[60 - m] + ((60 - m) == 1 ? " minute" : " minutes") + " to " + numbers[h + 1];
        }
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int h = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println(Result.timeInWords(h, m));
        scanner.close();
    }
}
