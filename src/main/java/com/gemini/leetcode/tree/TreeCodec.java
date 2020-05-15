package com.gemini.leetcode.tree;

/**
 * com.gemini.leetcode.tree.TreeCodec
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 示例:
 * <p>
 * 你可以将以下二叉树：
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 *
 * @author zhanghailin
 */
public class TreeCodec {

    /*// Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return rserialize(root, new StringBuilder()).toString();
    }

    private StringBuilder rserialize(TreeNode root, StringBuilder str) {
        if (root == null) {
            str.append("null,");
        } else {
            str.append(root.val).append(",");
            str = rserialize(root.left, new StringBuilder(str.toString()));
            str = rserialize(root.right, str);
        }
        return str;
    }

    private TreeNode rdeserialize(List<String> l) {
        // Recursive deserialization.
        if (l.get(0).equals("null")) {
            l.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);

        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<>(Arrays.asList(data_array));
        return rdeserialize(data_list);
    }*/

    // Encodes a tree to a single string.
    StringBuilder res = new StringBuilder();

    public String serialize(TreeNode root) {
        dfs(root);
        //System.out.println(res.toString());
        return res.toString();
    }

    void dfs(TreeNode root) {
        if (root == null) {
            res.append("#,");
            return;
        }
        res.append(root.val).append(',');
        dfs(root.left);
        dfs(root.right);
    }


    // Decodes your encoded data to tree.
    int u = 0;

    public TreeNode deserialize(String data) {
        if (data.charAt(u) == '#') {
            u += 2;
            return null;
        }
        int sum = 0;
        boolean isNeg = false;
        if (data.charAt(u) == '-') {
            isNeg = true;
            u++;
        }
        while (data.charAt(u) != ',') sum = sum * 10 + data.charAt(u++) - '0';
        u++;
        if (isNeg) sum = -sum;
        TreeNode root = new TreeNode(sum);
        root.left = deserialize(data);
        root.right = deserialize(data);
        return root;
    }

    public static void main(String[] args) {

    }

}
