package com.gemini.leetcode.tree;

import com.gemini.support.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * com.gemini.leetcode.tree.LeetCode297_BinaryTreeCodec
 *
 * @author zhanghailin
 */
public class LeetCode297_BinaryTreeCodec {
    // 二叉树的问题几乎都可以用递归解决，辅助以其他手段
    // 先找到递归终结条件，以及对当前节点自身应该如何处理，之后就是递归

    public static String serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }

    public static TreeNode deserialize(String data) {
        // String[] list = data.split(",");
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(",")) {
            nodes.addLast(s);
        }
        return buildTree(nodes);
    }

    public static TreeNode buildTree(List<String> l) {
        String first = l.remove(0);
        if (first.equals("#")) {
            return null;
        }
        TreeNode treeNode = new TreeNode(Integer.valueOf(first));
        // l.remove(0);
        treeNode.left = buildTree(l);
        treeNode.right = buildTree(l);
        return treeNode;
    }


}
