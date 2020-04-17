package com.gemini.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * com.gemini.interview.Interview0804_Subsets
 * <p>
 * 幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入： nums = [1,2,3]
 * 输出：
 * [
 * [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-set-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhanghailin
 */
public class Interview0804_Subsets {


    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, res, new ArrayList<>());
        return res;
    }

    private void backtrack(int start, int[] nums, List<List<Integer>> res, List<Integer> list) {
        if (start > nums.length) return;
        res.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            backtrack(i + 1, nums, res, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        Interview0804_Subsets interview0804_subsets = new Interview0804_Subsets();
        int[] nums = {1, 2, 3};
        interview0804_subsets.subsets(nums).forEach(System.out::println);
    }
}
