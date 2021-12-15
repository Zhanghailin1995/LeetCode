package com.gemini.leetcode;

/**
 * com.gemini.leetcode.LeetCode002_AddTowNumbers
 *
 * @author zhanghailin
 */
public class LeetCode002_AddTwoNumbers {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        // 判断为null 注意特殊情况
        // l1=[0,1]，l2=[0,1,2]l2=[0,1,2]	当一个列表比另一个列表长时
        // l1=[]l1=[]，l2=[0,1]l2=[0,1]	当一个列表为空时，即出现空列表
        // l1=[9,9]l1=[9,9]，l2=[1]l2=[1]	求和运算最后可能出现额外的进位，这一点很容易被遗忘

        // 作者：LeetCode
        // 链接：https://leetcode-cn.com/problems/add-two-numbers/solution/liang-shu-xiang-jia-by-leetcode/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            // 以下两步为移动当前节点至下一个节点
            curr.next = new ListNode(sum % 10);//
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {

    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        String head = String.valueOf(val);
        ListNode p = next;
        while (p != null) {
            head += "->" +p.val;
            p = p.next;
        }
        return head;
    }
}
