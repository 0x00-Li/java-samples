package fit.ome.leetcode;

/**
 * 相交链表
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 */
public class Code0160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
// 先同步两个链表的长度差
        ListNode currA = headA;
        int lenA = 0;
        while (currA != null) {
            currA = currA.next;
            lenA++;
        }
        ListNode currB = headB;
        while (currB != null) {
            currB = currB.next;
            lenA--;
        }
        ListNode longer = lenA > 0 ? headA : headB;
        ListNode shorter = longer == headA ? headB : headA;
        for (int i = 0; i < Math.abs(lenA); i++) {
            longer = longer.next;
        }
        while (longer!=null&&shorter!=null){
            if(longer==shorter){
                return longer;
            }
            longer=longer.next;
            shorter=shorter.next;
        }
        return null;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
