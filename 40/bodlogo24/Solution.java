import java.util.*;

class Result {

    static class Node {
        int data;
        int depth;
        Node left, right;

        Node(int data, int depth) {
            this.data = data;
            this.depth = depth;
        }
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        Node root = new Node(1, 1);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        for (List<Integer> pair : indexes) {
            Node current = queue.poll();
            int left = pair.get(0);
            int right = pair.get(1);

            if (left != -1) {
                current.left = new Node(left, current.depth + 1);
                queue.add(current.left);
            }

            if (right != -1) {
                current.right = new Node(right, current.depth + 1);
                queue.add(current.right);
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int k : queries) {
            swapAtDepth(root, k);
            List<Integer> traversal = new ArrayList<>();
            inOrderTraversal(root, traversal);
            result.add(traversal);
        }

        return result;
    }

    private static void swapAtDepth(Node node, int k) {
        if (node == null) return;
        if (node.depth % k == 0) {
            Node temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        swapAtDepth(node.left, k);
        swapAtDepth(node.right, k);
    }

    private static void inOrderTraversal(Node node, List<Integer> result) {
        if (node == null) return;
        inOrderTraversal(node.left, result);
        result.add(node.data);
        inOrderTraversal(node.right, result);
    }
}

// ✅ Solution class with main()
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        List<List<Integer>> indexes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().split(" ");
            List<Integer> row = new ArrayList<>();
            row.add(Integer.parseInt(parts[0]));
            row.add(Integer.parseInt(parts[1]));
            indexes.add(row);
        }

        int q = Integer.parseInt(sc.nextLine());
        List<Integer> queries = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            queries.add(Integer.parseInt(sc.nextLine()));
        }

        List<List<Integer>> result = Result.swapNodes(indexes, queries);
        for (List<Integer> line : result) {
            for (int i = 0; i < line.size(); i++) {
                System.out.print(line.get(i));
                if (i < line.size() - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}
