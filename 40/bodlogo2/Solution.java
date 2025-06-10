import java.util.*;

class Result {
    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        List<Integer> uniqueRanks = new ArrayList<>();
        uniqueRanks.add(ranked.get(0));
        for (int i = 1; i < ranked.size(); i++) {
            if (!ranked.get(i).equals(ranked.get(i - 1))) {
                uniqueRanks.add(ranked.get(i));
            }
        }

        List<Integer> result = new ArrayList<>();
        int index = uniqueRanks.size() - 1;

        for (int score : player) {
            while (index >= 0 && score >= uniqueRanks.get(index)) {
                index--;
            }
            result.add(index + 2);
        }

        return result;
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rankedCount = scanner.nextInt();
        List<Integer> ranked = new ArrayList<>();
        for (int i = 0; i < rankedCount; i++) {
            ranked.add(scanner.nextInt());
        }

        int playerCount = scanner.nextInt();
        List<Integer> player = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            player.add(scanner.nextInt());
        }

        List<Integer> result = Result.climbingLeaderboard(ranked, player);
        for (int rank : result) {
            System.out.println(rank);
        }

        scanner.close();
    }
}
