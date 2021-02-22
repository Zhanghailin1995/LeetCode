package com.gemini.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.gemini.leetcode.tree.LeetCode652_FindDuplicateSubtrees
 *
 * @author zhanghailin
 */
public class LeetCode652_FindDuplicateSubtrees {
    // 找重复值，可以用map

    List<TreeNode> ans = new ArrayList<>();
    Map<String, Integer> count = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        collect(root);
        return ans;
    }

    /*public String collect(TreeNode node) {
        if (node == null) return "#";
        StringBuilder serial = new StringBuilder(String.valueOf(node.val))
                .append(",")
                .append(collect(node.left))
                .append(",")
                .append(collect(node.right));
        count.put(serial.toString(), count.getOrDefault(serial.toString(), 0) + 1);
        if (count.get(serial.toString()) == 2) {
            ans.add(node);
        }
        return serial.toString();
    }*/

    private String collect(TreeNode root) {
        if (root == null) {
            return "#";
        }

        String serial = root.val + "," + collect(root.left) + "," + collect(root.right);
        count.put(serial, count.getOrDefault(serial, 0) + 1);
        if (count.get(serial) == 2) {
            ans.add(root);
        }
        return serial;
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    // List<TreeNode> ans = new ArrayList<>();
    Map<Integer, Integer> cnt2 = new HashMap<>();
    /**
     * 3、树Hash 最快解法，hash计算比字符串拼接快10倍
     */
    public List<TreeNode> findDuplicateSubtrees1(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 3524335;
        }

        int id = ((dfs(node.left) ^ 3) * 3423443 + (dfs(node.right) ^ 13)) * 3423443 + node.val;
        int count = cnt2.getOrDefault(id, 0) + 1;
        if (count == 2) {
            ans.add(node);
        }
        cnt2.put(id, count);
        return id;
    }
}
