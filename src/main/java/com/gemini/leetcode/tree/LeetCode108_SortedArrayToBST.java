package com.gemini.leetcode.tree;

/**
 * 108. 将有序数组转换为二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定有序数组: [-10,-3,0,5,9],
 * <p>
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 */
public class LeetCode108_SortedArrayToBST {

    // [0,1,2,3,4]
    public TreeNode sortedArrayToBST(int[] nums) {
        int len = nums.length;
        return dfs(0, len - 1, nums);
    }

    TreeNode dfs(int left, int right, int[] nums) {
        if (left > right) {
            return null;
        }
        int p = (right + left) / 2;
        TreeNode node = new TreeNode(nums[p]);
        node.left = dfs(left, p - 1, nums);
        node.right = dfs(p + 1, right, nums);
        return node;
    }
}
