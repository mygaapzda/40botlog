import java.io.*;
import java.util.*;

class UnionFind {
    int[] parent;
    int[] size;

    public UnionFind(int n) {
        parent = new int[n + 1];
        size = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }

    void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }
    }
}

class Result {

    public static List<Integer> componentsInGraph(List<List<Integer>> gb) {
        int maxNode = 0;
        for (List<Integer> edge : gb) {
            maxNode = Math.max(maxNode, Math.max(edge.get(0), edge.get(1)));
        }

        UnionFind uf = new UnionFind(maxNode);

        for (List<Integer> edge : gb) {
            uf.union(edge.get(0), edge.get(1));
        }

        List<Integer> componentSizes = new ArrayList<>();
        boolean[] counted = new boolean[maxNode + 1];

        for (int i = 1; i <= maxNode; i++) {
            int root = uf.find(i);
            if (!counted[root] && uf.size[root] > 1) {
                counted[root] = true;
                componentSizes.add(uf.size[root]);
            }
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int s : componentSizes) {
            if (s < min) min = s;
            if (s > max) max = s;
        }

        return Arrays.asList(min, max);
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> gb = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] parts = bufferedReader.readLine().trim().split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            gb.add(Arrays.asList(a, b));
        }

        List<Integer> result = Result.componentsInGraph(gb);
        System.out.println(result.get(0) + " " + result.get(1));
    }
}
