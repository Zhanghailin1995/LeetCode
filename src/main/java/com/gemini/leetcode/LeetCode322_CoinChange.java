package com.gemini.leetcode;

import java.util.Arrays;

/**
 * com.gemini.leetcode.LeetCode322_CoinChange
 * <p>
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * <p>
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *  
 * <p>
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhanghailin
 */
public class LeetCode322_CoinChange {

    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount + 1]);
    }

    private int coinChange(int[] coins, int rem, int[] dp) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (dp[rem] != 0) return dp[rem];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, dp);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        dp[rem] = (min == Integer.MAX_VALUE) ? -1 : min;
        return dp[rem];
    }

    /***********************************************分割线*********************************************************/

    // dp[0] = 0;
    // dp[n<0] = -1;
    // dp[n] = min{dp[n-coin]+1|coin in coins}

    // 自下而上解决重复dp
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= amount) {
                    // dp[5] = Math.min(dp[5],dp[5-5]+1)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}
