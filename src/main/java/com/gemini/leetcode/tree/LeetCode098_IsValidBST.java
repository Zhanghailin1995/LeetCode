package com.gemini.leetcode.tree;

import java.util.Stack;

/**
 * com.gemini.leetcode.tree.LeetCode098_IsValidBST
 *
 * @author zhanghailin
 */
public class LeetCode098_IsValidBST {

    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);

    }

    //乍一看，这是一个平凡的问题。只需要遍历整棵树，检查 node.right.val > node.val 和
    //node.left.val < node.val 对每个结点是否成立。
    //
    //
    //
    //问题是，这种方法并不总是正确。不仅右子结点要大于该节点，整个右子树的元素都应该大于该节点。例如:
    //
    public boolean helper(TreeNode node,Integer lower,Integer upper) {
        if (node == null) return true;

        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (! helper(node.right, val, upper)) return false;
        if (! helper(node.left, lower, val)) return false;
        return true;

    }

    // 首先你要记得二叉树的中序遍历的方法

    // 中序遍历为升序
    public boolean isValidBST2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        double inorder = - Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }


    public static void main(String[] args) {
    }
}
