package com.gemini.leetcode;

import com.gemini.support.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * com.gemini.leetcode.LeetCode023_MergeKLists
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 *  
 * <p>
 * 提示：
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhanghailin
 */
public class LeetCode023_MergeKLists {


    // 解法就是归并排序，从每个链表中取第一个，然后比较，最小的加入输出结果，然后再从刚刚最小的那个链表再取一个，一直到取完
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(ListNode::getVal));
        if (lists != null && lists.length > 0) {
            for (ListNode node : lists) {
                // 这个是边界条件，数组中有可能为null
                if (node != null) {
                    queue.add(node);
                }
            }
        }
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            p.setNext(node);
            p = node;
            if (node.getNext() != null) {
                queue.add(node.getNext());
            }

        }
        return dummy.getNext();
    }

}
