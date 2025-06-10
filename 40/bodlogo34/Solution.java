import java.io.*;
import java.util.*;

class Result {

    /*
     * Complete the 'contacts' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts 2D_STRING_ARRAY queries as parameter.
     */

    public static List<Integer> contacts(List<List<String>> queries) {
        List<Integer> result = new ArrayList<>();
        TrieNode root = new TrieNode();

        for (List<String> query : queries) {
            String operation = query.get(0);
            String argument = query.get(1);

            if (operation.equals("add")) {
                addContact(root, argument);
            } else if (operation.equals("find")) {
                int count = findCount(root, argument);
                result.add(count);
            }
        }

        return result;
    }

    private static void addContact(TrieNode root, String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
            node.count++; // increase prefix count
        }
    }

    private static int findCount(TrieNode root, String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            node = node.children.get(c);
            if (node == null) {
                return 0;
            }
        }
        return node.count;
    }

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int count = 0; // counts words sharing this prefix
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int queriesRows = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> queries = new ArrayList<>();

        for (int i = 0; i < queriesRows; i++) {
            queries.add(Arrays.asList(bufferedReader.readLine().trim().split(" ")));
        }

        List<Integer> result = Result.contacts(queries);

        for (int i = 0; i < result.size(); i++) {
            bufferedWriter.write(String.valueOf(result.get(i)));
            if (i != result.size() - 1) {
                bufferedWriter.write("\n");
            }
        }
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
