package com.gemini.leetcode;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * <p>
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode016_ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        int ans = nums[0] + nums[1] + nums[2];
        int temp = 0;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    temp = nums[i] + nums[j] + nums[k];
                    if (Math.abs(temp - target) < Math.abs(ans - target)) { // 当前差值的绝对值 < 之前差值的绝对值
                        ans = temp;
                    }
                }
            }
        }
        return ans;
    }

    // 排序加双指针方法(关键在于找到移动指针的条件,左移和右移的条件)
    // 先排序
    // 当前的差值小于之前的差值,记录下当前的三数之和
    // 当前的三数之和大于target ,左移右指针<-
    // 当前的三数之和小于target ,右移左指针->
    // 当前的三数之和等于target ,直接返回当前三数之和为结果
    public int threeSumClosest2(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        int temp = 0;
        for (int i = 0; i < len - 2; i++) {
            int L = i + 1, R = len - 1;
            while (L < R) {
                temp = nums[i] + nums[L] + nums[R];
                if (Math.abs(temp - target) < Math.abs(ans - target)) {
                    ans = temp;
                }
                if (temp > target) {
                    R--;
                } else if (temp < target) {
                    L++;
                } else {
                    return ans;
                }

            }
        }
        return ans;
    }
}
