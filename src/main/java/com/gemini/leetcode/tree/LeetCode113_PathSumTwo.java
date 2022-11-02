package com.gemini.leetcode.tree;

import com.gemini.support.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * com.gemini.leetcode.tree.LeetCode113_PathSumTwo
 * <p>
 * 113. 路径总和 II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
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
 * /  \    / \
 * 7    2  5   1
 * 返回:
 * <p>
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 *
 * @author zhanghailin
 */
public class LeetCode113_PathSumTwo {

    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return ans;
        helper(root, 0, sum, new ArrayList<>());
        return ans;
    }

    private void helper(TreeNode node, int preSum, int target, List<Integer> tmp) {
        List<Integer> _tmp = new ArrayList<>(tmp);
        _tmp.add(node.val);
        if (node.left == null && node.right == null && preSum + node.val == target) {
            ans.add(_tmp);
            return;
        }
        preSum += node.val;
        if (node.right != null) {
            helper(node.right, preSum, target, _tmp);
        }
        if (node.left != null) {
            helper(node.left, preSum, target, _tmp);
        }
    }


    /*****************************************分割线***********************************************/

    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        if (root == null) return ans;
        backtrack(root, 0, sum, new ArrayList<>());
        return ans;
    }

    private void backtrack(TreeNode node, int preSum, int target, List<Integer> tmp) {
        tmp.add(node.val);
        if (node.left == null && node.right == null && preSum + node.val == target) {
            ans.add(new ArrayList<>(tmp));
            tmp.remove(tmp.size() - 1);
            return;
        }
        preSum += node.val;
        if (node.right != null) {
            backtrack(node.right, preSum, target, tmp);
        }
        if (node.left != null) {
            backtrack(node.left, preSum, target, tmp);
        }
        tmp.remove(tmp.size() - 1);
    }

    /*****************************************分割线***********************************************/

    public List<List<Integer>> pathSum3(TreeNode root, int sum) {
        if (root == null) return ans;
        backtrack2(root, sum, new ArrayList<>());
        return ans;
    }

    private void backtrack2(TreeNode node, int target, List<Integer> tmp) {
        if (node == null) return;
        tmp.add(node.val);
        if (node.left == null && node.right == null && target == node.val) {
            ans.add(new ArrayList<>(tmp));
        }
        backtrack2(node.left, target - node.val, tmp);
        backtrack2(node.right, target - node.val, tmp);
        tmp.remove(tmp.size() - 1);
    }

}
