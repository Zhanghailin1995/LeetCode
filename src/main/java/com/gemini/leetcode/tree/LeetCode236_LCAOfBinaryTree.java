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

    //https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/c-jing-dian-di-gui-si-lu-fei-chang-hao-li-jie-shi-/
    //算法
    //(递归) O(n)
    //
    //当我们用递归去做这个题时不要被题目误导，应该要明确一点
    //这个函数的功能有三个：给定两个节点 p 和 q
    //
    //如果 p 和 q 都存在，则返回它们的公共祖先；
    //如果只存在一个，则返回存在的一个；
    //如果 p 和 q 都不存在，则返回NULL
    //本题说给定的两个节点都存在，那自然还是能用上面的函数来解决
    //
    //具体思路：
    //（1） 如果当前结点 root 等于 NULL，则直接返回 NULL
    //（2） 如果 root 等于 p 或者 q ，那这棵树一定返回 p 或者 q
    //（3） 然后递归左右子树，因为是递归，使用函数后可认为左右子树已经算出结果，用 left 和 right 表示
    //（4） 此时若left为空，那最终结果只要看 right；若 right 为空，那最终结果只要看 left
    //（5） 如果 left 和 right 都非空，因为只给了 p 和 q 两个结点，都非空，说明一边一个，因此 root 是他们的最近公共祖先
    //（6） 如果 left 和 right 都为空，则返回空（其实已经包含在前面的情况中了）
    //
    //时间复杂度是 O(n)：每个结点最多遍历一次或用主定理，空间复杂度是 O(n)：需要系统栈空间
    //
    //作者：Wilson79
    //链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/c-jing-dian-di-gui-si-lu-fei-chang-hao-li-jie-shi-/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left == null)
            return right;
        if (right == null)
            return left;

        return root;

    }
}
