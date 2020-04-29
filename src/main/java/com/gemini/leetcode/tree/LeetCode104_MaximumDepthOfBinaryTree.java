package com.gemini.leetcode.tree;

import javafx.util.Pair;

import java.util.LinkedList;

/**
 * com.gemini.leetcode.tree.LeetCode104_MaximumDepthOfBinaryTree
 *
 * @author zhanghailin
 */
public class LeetCode104_MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.right == null && root.left == null) return 1;

        int max_depth = Integer.MIN_VALUE;
        if (root.left != null) {
            max_depth = Math.max(maxDepth(root.left), max_depth);
        }
        if (root.right != null) {
            max_depth = Math.max(maxDepth(root.right), max_depth);
        }

        return max_depth + 1;
    }


    public int maxDepth2(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root == null) {
            return 0;
        } else {
            stack.add(new Pair<>(root, 1));
        }
        int max_depth = 0;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> pair = stack.poll();// 栈底出
            TreeNode node = pair.getKey();
            int depth = pair.getValue();
            if (node.right == null && node.left == null) {
                max_depth = Math.max(depth, max_depth);
            }
            if (node.right != null) {
                stack.add(new Pair<>(node.right, depth + 1));// 栈顶入，是广度优先搜索
            }
            if (node.left != null) {
                stack.add(new Pair<>(node.left, depth + 1));
            }
        }
        return max_depth;
    }
}
