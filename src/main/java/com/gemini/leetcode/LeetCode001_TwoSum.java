package com.gemini.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * com.gemini.leetcode.LeetCode001_TwoSum
 *
 * @author zhanghailin
 */
public class LeetCode001_TwoSum {

    public int[] twoSum(int[] nums, int target) {
        // key 是 nums 中的数字，value 为 nums中的的数字的数组下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
