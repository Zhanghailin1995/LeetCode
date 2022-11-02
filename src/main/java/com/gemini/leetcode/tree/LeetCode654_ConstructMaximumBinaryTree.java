package com.gemini.leetcode.tree;

import com.gemini.support.TreeNode;

/**
 * com.gemini.leetcode.tree.LeetCode654_ConstructMaximumBinaryTree
 *
 * @author zhanghailin
 */
public class LeetCode654_ConstructMaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    public TreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int index = -1, maxVal = Integer.MIN_VALUE;
        // 求解数组最大值索引
        for (int i = start; i <= end; i++) {
            if (maxVal < nums[i]) {
                maxVal = nums[i];
                index = i;
            }
        }

        TreeNode root = new TreeNode(maxVal);
        root.left = buildTree(nums, start, index - 1);
        root.right = buildTree(nums, index + 1, end);
        return root;
    }
}
