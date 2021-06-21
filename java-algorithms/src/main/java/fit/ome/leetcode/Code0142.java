package fit.ome.leetcode;

/**
 * 环形链表II
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 */
public class Code0142 {
    public ListNode detectCycle(ListNode head) {
// 快慢指针，查找环中节点
        // 单步执行，查找入环节点
        ListNode fast=head;
        ListNode slow=head;
        while (fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){
                break;
            }
        }
        if(fast==null||fast.next==null){
            return null;
        }
        fast=head;
        while (fast!=slow){
            fast=fast.next;
            slow=slow.next;
        }
        return fast;
    }
      class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
}
