package com.gemini.leetcode.dp;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *  
 *
 * 提示：
 *
 * 1 <= n <= 45
 *
 * 作者：力扣 (LeetCode)
 * 链接：<a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xn854d/">...</a>
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class LeetCode070_ClimbStairs {

    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int climbStairs1(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }

    public int climbStairs2(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int prev1 = 1;
        int prev2 = 2;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = prev2 + prev1;
            int temp = prev2;
            prev2 = prev1 + prev2;
            prev1 = temp;
        }
        return result;
    }


    public int climbStairs3(int n) {
        return fibonacci(n, 1, 1);
    }

    // f3 = f2 + f1
    // f4 = f3 + f2
    // 尾递归优化的原理
    // 迭代的时候，每次都会保存上一次的结果，所以可以用两个变量来保存上一次的结果
    // prev1, prev2 = prev2, prev1 + prev2

    public int fibonacci(int n, int a, int b) {
        if (n <= 1)
            return b;
        return fibonacci(n - 1, b, a + b);
    }



    public static void main(String[] args) {
        LeetCode070_ClimbStairs climbStairs = new LeetCode070_ClimbStairs();
        System.out.println(climbStairs.climbStairs(10));
        System.out.println(climbStairs.climbStairs1(10));
        System.out.println(climbStairs.climbStairs2(10));
        System.out.println(climbStairs.climbStairs3(10));
    }
}
