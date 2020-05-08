package com.gemini.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * com.gemini.leetcode.tree.LeetCode199_BinaryTreeRightSideView
 * 199. 二叉树的右视图
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 * <p>
 * 1            <---
 * /   \
 * 2     3         <---
 * \     \
 * 5     4       <---
 *
 * @author zhanghailin
 */
public class LeetCode199_BinaryTreeRightSideView {

    // 常规思路解法，此题和114题目类似，BFS，然后取最后一个节点加入到结果集中
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    ans.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return ans;
    }

    List<Integer> list = new ArrayList<>();

    //dfs
    public List<Integer> rightSideView2(TreeNode root) {
        dfs(root, 0);
        return list;
    }

    void dfs(TreeNode t, int depth) {
        if (t == null) return;
        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
        if (depth == list.size()) list.add(t.val);
        depth++;
        dfs(t.right, depth);
        dfs(t.left, depth);
    }
}
