package com.gemini.leetcode.tree;

/**
 * com.gemini.leetcode.tree.LeetCode236_LCAOfBinaryTree
 *
 * @author zhanghailin
 */
public class LeetCode236_LCAOfBinaryTree {

    private TreeNode ans = null;

    //我们递归遍历整棵二叉树，定义 f(x) x 节点的子树中是否包含 p 节点或 q 节点，如果包含为 true，否则为 false。那么符合条件的最近公共祖先 x 一定满足如下条件：
    //(f(lson)&&f(rson))||((x==p||x==q)&&(f(lson)||f(rson)))
    //其中 lson 和 rson 分别代表 x 节点的左孩子和右孩子.初看可能会感觉条件判断有点复杂，我们来一条条看，
    //f(lson)&&f(rson) 说明左子树和右子树均包含 p 节点或 q 节点,如果左子树包含的是 p 节点，那么右子树只能包含 q 节点,反之亦然.
    //因为 p 节点和 q 节点都是不同且唯一的节点，因此如果满足这个判断条件即可说明 x 就是我们要找的最近公共祖先。
    //再来看第二条判断条件，这个判断条件即是考虑了 x 恰好是 p 节点或 q 节点且它的左子树或右子树有一个包含了另一个节点的情况，
    //因此如果满足这个判断条件亦可说明 x 就是我们要找的最近公共祖先。
    //
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        // 左子树包含或者右子树包含，或者当前节点是p,q其中之一
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }
}
