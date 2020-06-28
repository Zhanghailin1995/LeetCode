package com.gemini.leetcode;

/**
 * com.gemini.leetcode.LeetCode209_MinimumSizeSubarraySum
 * <p>
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 * <p>
 * 示例:
 * <p>
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 *
 * @author zhanghailin
 */
public class LeetCode209_MinimumSizeSubarraySum {

    // 双指针法，即使是这样一个题目，想要完全做到bug free 也还是需要斟酌一下细节问题
    public static int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) return 0;
        int L = 0;
        int R = 0;
        int sum = nums[L];
        int ans = Integer.MAX_VALUE;
        while (L <= R && R < nums.length) {

            /*for (int i = L + 1; i <= R; i++) {
                sum += nums[i];
            }*/
            if (sum < s) {
                if (++R < nums.length) {
                    sum += nums[R];
                }
            } else {
                ans = Math.min(R - L + 1, ans);
                sum -= nums[L++];
                /*if (++L < nums.length) {
                    sum -= nums[L];
                }*/
            }
        }
        if (ans == Integer.MAX_VALUE) {
            return 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,4,4};
        System.out.println(minSubArrayLen(4, nums));
    }
}
