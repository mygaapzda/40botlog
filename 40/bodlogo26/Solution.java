import java.util.*;

public class Solution {

    static class MyQueue {
        Stack<Integer> stackNewest = new Stack<>();
        Stack<Integer> stackOldest = new Stack<>();

        // Add an element to the end of the queue
        void enqueue(int x) {
            stackNewest.push(x);
        }

        // Move elements from stackNewest to stackOldest if needed
        void shiftStacks() {
            if (stackOldest.isEmpty()) {
                while (!stackNewest.isEmpty()) {
                    stackOldest.push(stackNewest.pop());
                }
            }
        }

        // Remove the element at the front of the queue
        void dequeue() {
            shiftStacks();
            stackOldest.pop();
        }

        // Print the element at the front of the queue
        int peek() {
            shiftStacks();
            return stackOldest.peek();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();  // Number of queries

        MyQueue queue = new MyQueue();

        for (int i = 0; i < q; i++) {
            int queryType = sc.nextInt();

            if (queryType == 1) {
                int x = sc.nextInt();
                queue.enqueue(x);
            } else if (queryType == 2) {
                queue.dequeue();
            } else if (queryType == 3) {
                System.out.println(queue.peek());
            }
        }

        sc.close();
    }
}
