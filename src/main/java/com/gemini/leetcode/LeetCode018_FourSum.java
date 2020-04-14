package com.gemini.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * com.gemini.leetcode.LeetCode018_FourSum
 * <p>
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhanghailin
 */
public class LeetCode018_FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int L = j + 1;
                int R = len - 1;
                // i,j固定，可取值的就是L,R, 最小就是L,L+1,最大R,R-1
                /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏，忽略*/
                int sum = nums[i] + nums[j] + nums[L] + nums[L + 1];
                if (sum > target) {
                    continue;
                }
                /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
                sum = nums[i] + nums[j] + nums[R - 1] + nums[R];
                if (sum < target) {
                    continue;
                }
                while (L < R) {
                    sum = nums[i] + nums[j] + nums[L] + nums[R];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[L], nums[R]));
                        while (L < R && nums[L] == nums[L + 1]) L++; // 去重
                        while (L < R && nums[R] == nums[R - 1]) R--; // 去重
                        L++;
                        R--;
                    } else if (sum < target) {
                        L++;
                    } else {
                        R--;
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        LeetCode018_FourSum fourSum = new LeetCode018_FourSum();
        int[] nums = {1, 0, -1, 0, -2, 2};
        List<List<Integer>> list = fourSum.fourSum(nums, 0);
        list.forEach(integers -> System.out.println(integers.toString()));
    }
}
