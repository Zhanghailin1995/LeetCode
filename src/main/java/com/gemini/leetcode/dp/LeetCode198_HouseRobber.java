package com.gemini.leetcode.dp;

/**
 * com.gemini.leetcode.dp.LeetCode198_HouseRobber
 * <p>
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2:
 * <p>
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * @author zhanghailin
 */
public class LeetCode198_HouseRobber {

    // dp解法的关键，找到状态转移方程
    // f(k) 为盗取前k个房间的最大金额 A(k) 为第k个房子的金额
    // f(k) = max(f(k-2)+ A(k),f(k-1))

    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[len];
    }

    // 可以简化上述解法，应为每次都只需要前两个最大值，两个变量就足够用了。
    // 取f(-1) = f(0) = 0
    public int rob2(int[] nums) {
        int prevMax = 0; // prevMax = f(k-2)
        int currMax = 0; // curMax = f(k-1)
        for (int x : nums) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp; //
        }
        return currMax;

    }
}
