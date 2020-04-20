package com.gemini.leetcode.tree;

/**
 * com.gemini.leetcode.tree.LeetCode096_NumTrees
 *
 * @author zhanghailin
 */
public class LeetCode096_NumTrees {

    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        // 计算dp table 表达式依赖n 依赖n-1的计算结果，可以通过自上而下计算解决重复子问题，已经双重循环来计算表达式
        for (int i = 2; i <= n; i++) {
            // dp[n] = dp[n-1]*dp[0]+dp[n-2]*dp[1]+...+dp[0]*dp[n-1]
            // dp[n-1] = dp[n-2]* dp[0] +... dp[0]*dp[n-2]
            // dp[0] = 1 dp[1] = 1
            // 从2开始算起 n = i
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    // 卡特兰数递推公式 h(n+1)=(2*(2*n+1)/(n+2)) * h(n)
    public int numTrees2(int n) {
        // Note: we should use long here instead of int, otherwise overflow
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }


    public static void main(String[] args) {
        LeetCode096_NumTrees numTrees = new LeetCode096_NumTrees();
        numTrees.numTrees(3);
    }
}
