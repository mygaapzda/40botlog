import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n, q;
        String[] firstInput = bufferedReader.readLine().split(" ");
        n = Integer.parseInt(firstInput[0]);
        q = Integer.parseInt(firstInput[1]);

        String[] numberStr = bufferedReader.readLine().split(" ");
        List<Integer> number = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            number.add(Integer.parseInt(numberStr[i]));
        }

        List<Integer> result = Result.waiter(number, q);

        for (Integer val : result) {
            System.out.println(val);
        }

        bufferedReader.close();
    }
}

class Result {

    public static List<Integer> waiter(List<Integer> number, int q) {
        List<Integer> result = new ArrayList<>();

        List<Integer> primes = generatePrimes(q);

        Stack<Integer> currentStack = new Stack<>();
        for (int num : number) {
            currentStack.push(num);
        }

        for (int i = 0; i < q; i++) {
            Stack<Integer> nextStack = new Stack<>();
            Stack<Integer> divisibleStack = new Stack<>();
            int prime = primes.get(i);

            while (!currentStack.isEmpty()) {
                int top = currentStack.pop();
                if (top % prime == 0) {
                    divisibleStack.push(top);
                } else {
                    nextStack.push(top);
                }
            }

            while (!divisibleStack.isEmpty()) {
                result.add(divisibleStack.pop());
            }

            currentStack = nextStack;
        }

        while (!currentStack.isEmpty()) {
            result.add(currentStack.pop());
        }

        return result;
    }

    private static List<Integer> generatePrimes(int q) {
        List<Integer> primes = new ArrayList<>();
        int num = 2;
        while (primes.size() < q) {
            if (isPrime(num)) {
                primes.add(num);
            }
            num++;
        }
        return primes;
    }

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
}
v
