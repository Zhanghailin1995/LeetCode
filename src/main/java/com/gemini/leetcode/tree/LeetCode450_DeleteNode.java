package com.gemini.leetcode.tree;

import com.gemini.support.TreeNode;

/**
 * com.gemini.leetcode.tree.LeetCode450_DeleteNode
 *
 * @author zhanghailin
 */
public class LeetCode450_DeleteNode {

    //两个要点：
    //1.二叉搜索树的搜索树，肯定是小于从left搜索，大于从right搜索，并且递归查询
    //2.当找到相应的节点后，需要删除。删除后需要从左右任意节点进行重新补充。

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                // 寻找当前节点的右节点的最左深子节点，并且将当前节点的左节点挂到该节点的左子树上，
                // 使用右节点替换当前节点
                TreeNode node = root.right;
                while (node.left!=null) {
                    node = node.left;
                }
                node.left = root.left;
                return root.right;
            }
        }
        return root;
    }

}
