package com.gemini.leetcode.tree;

import com.gemini.support.TreeNode;
import javafx.util.Pair;

import java.util.Stack;

/**
 * com.gemini.leetcode.tree.LeetCode112_PathSum
 * <p>
 * 112. 路径总和
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * @author zhanghailin
 */
public class LeetCode112_PathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        return helper(root, 0, sum);
    }

    public boolean helper(TreeNode node, int preSum, int target) {
        if (node.left == null && node.right == null && preSum + node.val == target) return true; //叶子节点是指没有子节点的节点。
        preSum += node.val;
        //if (preSum > target) return false; 节点中会有负数节点
        if (node.right != null) {
            if (helper(node.right, preSum, target)) return true;
        }
        if (node.left != null) {
            if (helper(node.left, preSum, target)) return true;
        }
        return false;
    }

    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) return false;

        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, 0));

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> top = stack.pop();
            TreeNode node = top.getKey();
            int pre_sum = top.getValue();
            int cur_sum = pre_sum + node.val;
            if ((node.right == null) && (node.left == null) && (cur_sum == 0))
                return true;
            if (node.right != null) {
                stack.push(new Pair<>(node.right, cur_sum));
            }
            if (node.left != null) {
                stack.push(new Pair<>(node.left, cur_sum));
            }
        }
        return false;
    }
}
