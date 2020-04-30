package com.gemini.leetcode.tree;

/**
 * com.gemini.leetcode.tree.LeetCode114_FlattenBinaryTreeToLinkedList
 *
 * @author zhanghailin
 */
//@formatter:off
//114. 二叉树展开为链表
//给定一个二叉树，原地将它展开为链表。
//
//例如，给定二叉树
//
//    1
//   / \
//  2   5
// / \   \
//3   4   6
//将其展开为：
//
//1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6
//@formatter:on
public class LeetCode114_FlattenBinaryTreeToLinkedList {

    private TreeNode pre = null;

    // 观察结果其实就是二叉树的先序遍历
    // 所以采用二叉树的先序遍历递归解法
    // 将当前节点连到上一个节点的右节点上
    // 提前存储左右节点，防止节点丢失
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;
        if (pre != null)
            pre.right = root;

        pre = root;

        flatten(left);
        flatten(right);
    }

    /**************************************分割线**********************************************/
    // 可以从此题很好的理解莫里斯遍历
    //    1.将左子树插入到右子树的地方
    //    2.将原来的右子树接到左子树的最右边节点
    //    3.考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null

    //    1
    //   / \
    //  2   5
    // / \   \
    //3   4   6
    //
    ////将 1 的左子树插入到右子树的地方
    //    1
    //     \
    //      2         5
    //     / \         \
    //    3   4         6
    ////将原来的右子树接到左子树的最右边节点
    //    1
    //     \
    //      2
    //     / \
    //    3   4
    //         \
    //          5
    //           \
    //            6
    //
    // //将 2 的左子树插入到右子树的地方
    //    1
    //     \
    //      2
    //       \
    //        3       4
    //                 \
    //                  5
    //                   \
    //                    6
    //
    // //将原来的右子树接到左子树的最右边节点
    //    1
    //     \
    //      2
    //       \
    //        3
    //         \
    //          4
    //           \
    //            5
    //             \
    //              6
    //
    //  ......
    //
    public void flatten2(TreeNode root) {
        while (root != null) {
            //左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;

            }
        }
    }
}
