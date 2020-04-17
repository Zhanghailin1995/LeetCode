package com.gemini.leetcode;

/**
 * com.gemini.leetcode.LeetCode322_CoinChange
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *  
 *
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhanghailin
 */
public class LeetCode322_CoinChange {

    public int coinChange(int[] coins, int amount) {
        return dp(coins, amount);
    }

    private int dp(int[] coins, int n) {
        if (n == 0) return 0;
        if (n < 0) return -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int subproblem = dp(coins, n - coins[i]);
            if (subproblem == -1) continue;
            min = Math.min(min, 1 + subproblem);
        }
        return min != Integer.MAX_VALUE ? min : -1;
    }

    public static void main(String[] args) {
        LeetCode322_CoinChange coinChange = new LeetCode322_CoinChange();
        int[] coins = {1, 2, 5};
        int min = coinChange.dp(coins, 11);
        System.out.println(min);
    }
}
