package fit.ome.leetcode;

/**
 * 环形链表
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * 快慢指针进行判断
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/6/3
 **/
public class Code0141 {

    public boolean hasCycle(ListNode head) {


        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head.next;
        while (true) {
            fast = fast.next;
            if (slow.next == null) {
                return false;
            }
            slow = slow.next.next;
            if (fast == slow) {
                return true;
            }
            if (fast == null || slow == null) {
                return false;
            }
        }

    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
