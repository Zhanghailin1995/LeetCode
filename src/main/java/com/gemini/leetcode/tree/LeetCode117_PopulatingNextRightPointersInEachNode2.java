package com.gemini.leetcode.tree;

/**
 * com.gemini.leetcode.tree.LeetCode117_PopulatingNextRightPointersInEachNode2
 * <p>
 * 117. 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * <p>
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhanghailin
 */
public class LeetCode117_PopulatingNextRightPointersInEachNode2 {

    // 解题思路来自116题，由于不是完全二叉树，所以再找next节点的时候需要特殊处理一下
    public Node connect(Node root) {
        dfs(root, null);
        return root;
    }

    private void dfs(Node node, Node next) {
        if (node != null) {
            node.next = next;
            dfs(node.right, getNext(node));
            dfs(node.left, node.right != null ? node.right : getNext(node));
        }
    }

    private static Node getNext(Node node) {
        if (node.next == null) return null;
        while (node.next != null) {
            if (node.next.left != null) {
                return node.next.left;
            }
            if (node.next.right != null) {
                return node.next.right;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * 一路向右找到next节点
     */
    private static Node getLeftNext(Node node) {
        if (node.right != null) return node.right;
        while (node.next != null) {
            if (node.next.left != null) {
                return node.next.left;
            }
            if (node.next.right != null) {
                return node.next.right;
            }
            node = node.next;
        }
        return null;
    }

    private static Node getRightNext(Node node) {
        if (node.next == null) return null;
        while (node.next != null) {
            if (node.next.left != null) {
                return node.next.left;
            }
            if (node.next.right != null) {
                return node.next.right;
            }
            node = node.next;
        }
        return null;
    }


}
