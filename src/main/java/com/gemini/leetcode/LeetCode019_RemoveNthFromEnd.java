package com.gemini.leetcode;

/**
 * com.gemini.leetcode.LeetCode019_RemoveNthFromEnd
 *
 * @author zhanghailin
 */
public class LeetCode019_RemoveNthFromEnd {

    //方法一：两次遍历算法
    //思路
    //
    //我们注意到这个问题可以容易地简化成另一个问题：删除从列表开头数起的第 (L - n + 1) 个结点，其中 L 是列表的长度。只要我们找到列表的长度 L，这个问题就很容易解决。
    //
    //算法
    //
    //首先我们将添加一个哑结点作为辅助，该结点位于列表头部
    //哑结点用来简化某些极端情况，例如列表中只含有一个结点，或需要删除列表的头部
    //在第一次遍历中，我们找出列表的长度 LL。然后设置一个指向哑结点的指针，并移动它遍历列表，直至它到达第 (L - n) 个结点那里.
    //我们把第 (L - n)个结点的 next 指针重新链接至第 (L - n + 2) 个结点，完成这个算法。

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }
        len -= n;
        node = dummy;
        while (len > 0) {
            len--;
            node = node.next;
        }
        node.next = node.next.next;
        return dummy.next;
    }

    // 双指针，第一个指针先往前走n个节点，然后第2个指针和第一个指针一起走，第一个指针走到底的时候，第二个指针正好走到n ,删除即可
    // dummy节点是链表中很常用的一种辅助手段
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}



