package com.gemini.leetcode.tree;

import com.gemini.support.TreeNode;

import java.util.*;

/**
 * com.gemini.leetcode.tree.LeetCode107_LevelOrderBottom
 *
 * @author zhanghailin
 */
public class LeetCode107_LevelOrderBottom {

    List<List<Integer>> levels = new ArrayList<List<Integer>>();


    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return levels;
        dfs(root,0);
        Collections.reverse(levels);
        return levels;
    }

    void dfs(TreeNode node, int level) {
        if (levels.size() - 1 < level)
            levels.add(new ArrayList<>());

        // fulfil the current level
        levels.get(level).add(node.val);
        if (node.left != null) {
            dfs(node.left, level + 1);
        }
        if (node.right != null) {
            dfs(node.right, level + 1);
        }
    }

    public List<List<Integer>> levelOrderBottom_bfs(TreeNode root) {
        // 创建二维数组接收每层的结点
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        // 创建队列依次存放每层的结点
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            // 创建数组来接收出队的结点，存放的是每层的结点
            List<Integer> tmp = new ArrayList<>();
            int len = q.size();
            for (int i = 0; i < len; i++) {
                // 定义 node 接收出队结点，然后加入数组 tmp 中
                TreeNode node = q.poll();
                if (node == null) continue;
                tmp.add(node.val);
                // 如果有左右孩子，就依次入队、出队、进数组
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            // 数组每次都是放的一层的结点，然后一层一层的放入二维数组中
            ans.addFirst(tmp);
        }
        return ans;

    }
}
