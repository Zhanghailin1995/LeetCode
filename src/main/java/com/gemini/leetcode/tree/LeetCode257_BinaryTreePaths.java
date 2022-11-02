package com.gemini.leetcode.tree;

import com.gemini.support.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * com.gemini.leetcode.tree.LeetCode257_BinaryTreePaths
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 * @author zhanghailin
 */
public class LeetCode257_BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new LinkedList<>();
        dfs(root, ans, new StringBuilder());
        return ans;
    }

    // 将String 换成StringBuilder之后速度大大提升
    private void dfs(TreeNode node, List<String> list, StringBuilder str) {
        if (node == null) {
            return;
        }
        str.append(node.val);
        if (node.left == null && node.right == null) {
            list.add(str.toString());
            return;
        }
        str.append("->");
        dfs(node.left, list, new StringBuilder(str));
        dfs(node.right, list, new StringBuilder(str));
    }

}
