package com.gemini.leetcode.tree;

import com.gemini.support.TreeNode;

public class LeetCode110_IsBalancedBinaryTree {


    public boolean isBalanced(TreeNode root) {
        return helper(root) >= 0;
    }

    public int helper(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        int l = helper(treeNode.left);
        int r = helper(treeNode.right);
        if (l == -1 || r == -1 || Math.abs(l - r) > 1) return -1;
        return Math.max(l, r) + 1;
    }
}
