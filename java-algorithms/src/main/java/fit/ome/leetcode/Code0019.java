package fit.ome.leetcode;

/**
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/9
 **/
public class Code0019 {
    /**
     * Definition for singly-linked list.
     * *
     * *
     * *
     * *
     * *
     * *
     * *
     */
    public static class ListNode {
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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
// 拉开前后指针的距离 n ,
        while (n > 0 && fast != null) {
            fast = fast.next;
            n--;
        }
        if (n > 0) {
            // n 大于链表长度
            return head;
        }
        if (n == 0 && fast == null) {
            // 需要移除第一个节点
            slow = slow.next;
            return slow;
        }
        // fast多前进一个节点，保证慢指针停留在待删除指针的前一位置
//        fast = fast.next;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    public static void printList(ListNode head){
        ListNode curr=head;
        while (curr!=null){
            System.out.print(curr.val+",");
            curr=curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);

        printList(new Code0019().removeNthFromEnd(head,5));
    }
}
