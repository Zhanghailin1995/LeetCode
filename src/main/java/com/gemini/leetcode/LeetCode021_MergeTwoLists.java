package com.gemini.leetcode;

import com.gemini.support.ListNode;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 *
 * com.gemini.leetcode.LeetCode021_MergeTwoLists
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhanghailin
 */
public class LeetCode021_MergeTwoLists {


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        // 创建一个哑结点，声明一个移动指针指向这个哑结点，后续这个移动指针会不停的移动
        ListNode dummy = new ListNode(-1), p = dummy;
        // 创建两个在两个链表上移动的指针
        ListNode m = list1, n = list2;
        while (m != null && n != null) {
            if (m.val < n.val) { // m较小
                p.next = m; // 将m挂在新链表后面
                p = m; // p指向最新的链表节点
                m = m.next; // m往后移动到下一个
            } else {
                p.next = n;
                p = n;
                n = n.next;
            }
        }

        if (m != null) {
            p.next = m;
        }

        if (n != null) {
            p.next = n;
        }

        return dummy.next;
    }
}
