package com.gemini.leetcode.tree;

import javafx.util.Pair;

import java.util.LinkedList;

/**
 * com.gemini.leetcode.tree.LeetCode111_MinimumDepthOfBinaryTree
 * <p>
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最小深度  2.
 *
 * @author zhanghailin
 */
public class LeetCode111_MinimumDepthOfBinaryTree {

    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) return 1;

        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }

        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }
        return min_depth + 1;
    }

    public int minDepth2(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root == null) {
            return 0;
        } else {
            stack.add(new Pair<>(root, 1));
        }

        int min_depth = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.poll(); // 栈底出
            root = current.getKey();
            int current_depth = current.getValue();
            if (current_depth > min_depth) continue;
            if (root.right == null && root.left == null) {
                min_depth = Math.min(min_depth, current_depth);
            }
            if (root.left != null) {
                stack.add(new Pair<>(root.left, current_depth + 1));// 栈顶入 BFS
            }
            if (root.right != null) {
                stack.add(new Pair<>(root.right, current_depth + 1));
            }
        }
        return min_depth;
    }

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("a"); //[A]
        linkedList.add("b");//[A,B]
        linkedList.add("c");//[A,B,C]
        System.out.println(linkedList.poll());//[B,C]
    }
}
