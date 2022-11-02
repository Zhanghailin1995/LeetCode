package com.gemini.leetcode.tree;

import com.gemini.support.TreeNode;

/**
 * com.gemini.leetcode.tree.LeetCode129_SumRootToLeafNumbers
 * 129. 求根到叶子节点数字之和
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * <p>
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * <p>
 * 计算从根到叶子节点生成的所有数字之和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 1
 * / \
 * 2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 * 示例 2:
 * <p>
 * 输入: [4,9,0,5,1]
 * 4
 * / \
 * 9   0
 * / \
 * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 *
 * @author zhanghailin
 */
public class LeetCode129_SumRootToLeafNumbers {
    private int sum = 0;

    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        dfs(root, 0);
        return sum;
    }

    // 一开始使用str 拼接最后Integer.valueOf,时间长达10ms以上
    private void dfs(TreeNode node, int i) {
        i = i * 10 + node.val;
        if (node.left == null && node.right == null) {
            sum += i;
        }

        if (node.left != null) {
            dfs(node.left, i);
        }
        if (node.right != null) {
            dfs(node.right, i);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = Tool.createTree(new int[]{1, 2, 3});
        LeetCode129_SumRootToLeafNumbers leetCode129_sumRootToLeafNumbers = new LeetCode129_SumRootToLeafNumbers();
        System.out.println(leetCode129_sumRootToLeafNumbers.sumNumbers(treeNode));
    }
}
