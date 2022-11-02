package com.gemini.leetcode.tree;

import com.gemini.support.TreeNode;

import java.util.HashMap;

/**
 * com.gemini.leetcode.tree.LeetCode105_BuildTree
 *
 * @author zhanghailin
 */
public class LeetCode105_BuildTree {

    int pre_idx = 0;
    int[] preorder;
    int[] inorder;

    HashMap<Integer, Integer> idx_map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;

        // build a hashmap value -> its index
        for (int idx = 0; idx < inorder.length; idx++) {
            idx_map.put(inorder[idx], idx);
        }
        return helper(0, inorder.length);
    }

    public TreeNode helper(int in_left, int in_right) {
        if (in_left == in_right)
            return null;
        int root_val = preorder[pre_idx];
        TreeNode root = new TreeNode(root_val);

        // 获取当前节点在中序遍历序列中的索引
        int index = idx_map.get(root_val);

        pre_idx++;

        // 以[左起点,当前节点在中序遍历序列中的索引]递归构建左子树
        root.left = helper(in_left, index);
        // 以[当前节点在中序遍历序列中的索引,右起点]递归构建右子树
        root.right = helper(index + 1, in_right);
        return root;
    }

    public static void main(String[] args) {
        int[] io = new int[]{3, 9, 20, 15, 7};
        int[] po = new int[]{9, 3, 15, 20, 7};

        LeetCode105_BuildTree leetCode105_buildTree = new LeetCode105_BuildTree();
        leetCode105_buildTree.buildTree(io, po);
    }

}
