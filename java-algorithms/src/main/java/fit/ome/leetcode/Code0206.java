package fit.ome.leetcode;

/**
 * 反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class Code0206 {
    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;

            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return cur;
    }

    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        ListNode pre = cur.next;
        cur.next = null;
        head = pre.next;
        while (pre != null && head != null) {
            pre.next = cur;

            cur = pre;
            pre = head;
            head = pre.next;
        }
        pre.next = cur;
        return pre;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
