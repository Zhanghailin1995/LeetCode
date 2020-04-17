package com.gemini.learning;

/**
 * com.gemini.learning.Fibonacci
 *
 * @author zhanghailin
 */
public class Fibonacci {

    int fib0(int n) {
        if (n == 1 || n == 2) return 1;
        return fib0(n - 1) + fib0(n - 2);
    }

    int[] cal;

    int fib1(int n) {
        cal = new int[n + 1];
        return helper(n);
    }

    int helper(int n) {
        if (n == 1 || n == 2) return 1;
        if (cal[n] != 0) return cal[0];
        cal[n] = helper(n - 1) + helper(n - 2);
        return cal[n];
    }

    int fib2(int n) {
        if (n == 2 || n == 1) {
            return 1;
        }
        int prev = 1, curr = 1;
        for (int i = 3; i <= n; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }

        return curr;
    }
}
