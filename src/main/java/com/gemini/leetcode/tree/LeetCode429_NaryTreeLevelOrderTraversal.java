package com.gemini.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * com.gemini.leetcode.tree.LeetCode429_NaryTreeLevelOrderTraversal
 * 429. N叉树的层序遍历
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 * <p>
 * 例如，给定一个 3叉树 :
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 返回其层序遍历:
 * <p>
 * [
 * [1],
 * [3,2,4],
 * [5,6]
 * ]
 * <p>
 * <p>
 * 说明:
 * <p>
 * 树的深度不会超过 1000。
 * 树的节点总数不会超过 5000。
 *
 * @author zhanghailin
 */
public class LeetCode429_NaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        // 创建队列依次存放每层的结点
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            // 创建数组来接收出队的结点，存放的是每层的结点
            List<Integer> tmp = new ArrayList<>();
            int len = q.size();
            for (int i = 0; i < len; i++) {
                // 定义 node 接收出队结点，然后加入数组 tmp 中
                Node node = q.poll();
                if (node == null) continue;
                tmp.add(node.val);
                if (node.children != null) {
                    q.addAll(node.children);
                }
            }
            // 数组每次都是放的一层的结点，然后一层一层的放入二维数组中
            ans.add(tmp);
        }
        return ans;
    }

    List<List<Integer>> levels = new ArrayList<>();

    public List<List<Integer>> levelOrder2(Node root) {
        if (root == null) return levels;
        dfs(root, 0);
        return levels;
    }

    // dfs
    void dfs(Node node, int level) {
        if (node == null) return;
        if (levels.size() - 1 < level)
            levels.add(new ArrayList<>());

        // fulfil the current level

        levels.get(level).add(node.val);
        for (Node child : node.children) {
            dfs(child, level + 1);
        }

    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}


