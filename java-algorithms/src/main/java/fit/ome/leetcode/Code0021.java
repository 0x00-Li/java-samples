package fit.ome.leetcode;

/**
 * 合并链表
 */
public class Code0021 {
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

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null||l2==null){
            return l1==null?l2:l1;
        }
        ListNode res = null;

        ListNode cur1 = l1;
        ListNode cur2 = l2;
        if(cur1.val<cur2.val){
            res=cur1;
            cur1=cur1.next;
        }else {
            res=cur2;
            cur2=cur2.next;
        }
        ListNode p = res;
        while (cur1 != null && cur2 != null) {
            if (cur1.val < cur2.val) {
                p.next = cur1;
                cur1 = cur1.next;
            } else {
                p.next = cur2;
                cur2 = cur2.next;
            }
            p=p.next;
        }

        while (cur1!=null){
            p.next=cur1;
            cur1=cur1.next;
            p=p.next;
        }
        while (cur2!=null){
            p.next=cur2;
            cur2=cur2.next;
            p=p.next;
        }

        return res;
    }
}
