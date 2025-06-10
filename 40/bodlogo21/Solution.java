import java.io.*;
import java.util.*;

class Result {

    public static List<Integer> matchingStrings(List<String> stringList, List<String> queries) {
        Map<String, Integer> freqMap = new HashMap<String, Integer>();

        // Count frequency of each string in stringList
        for (String s : stringList) {
            if (freqMap.containsKey(s)) {
                freqMap.put(s, freqMap.get(s) + 1);
            } else {
                freqMap.put(s, 1);
            }
        }

        List<Integer> result = new ArrayList<Integer>();

        // For each query, get its count from freqMap
        for (String q : queries) {
            if (freqMap.containsKey(q)) {
                result.add(freqMap.get(q));
            } else {
                result.add(0);
            }
        }

        return result;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int stringListCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> stringList = new ArrayList<String>();

        for (int i = 0; i < stringListCount; i++) {
            String stringListItem = bufferedReader.readLine();
            stringList.add(stringListItem);
        }

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> queries = new ArrayList<String>();

        for (int i = 0; i < queriesCount; i++) {
            String queriesItem = bufferedReader.readLine();
            queries.add(queriesItem);
        }

        List<Integer> res = Result.matchingStrings(stringList, queries);

        for (int i = 0; i < res.size(); i++) {
            bufferedWriter.write(String.valueOf(res.get(i)));
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
