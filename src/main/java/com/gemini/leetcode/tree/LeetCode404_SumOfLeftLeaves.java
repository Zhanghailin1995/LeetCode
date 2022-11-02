package com.gemini.leetcode.tree;

import com.gemini.support.TreeNode;

/**
 * com.gemini.leetcode.tree.LeetCode404_SumOfLeftLeaves
 * <p>
 * 404. Sum of Left Leaves
 * Find the sum of all left leaves in a given binary tree.
 * <p>
 * Example:
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 *
 * @author zhanghailin
 */
public class LeetCode404_SumOfLeftLeaves {

    int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        helper(root);
        return sum;
    }

    private void helper(TreeNode node) {
        if (node == null) return;
        if (node.left != null && node.left.left == null && node.left.right == null) {
            sum += node.left.val;

        }
        helper(node.left);
        helper(node.right);

    }
}
