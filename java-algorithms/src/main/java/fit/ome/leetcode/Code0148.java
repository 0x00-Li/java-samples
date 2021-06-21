package fit.ome.leetcode;

/**
 * 排序链表
 * https://leetcode-cn.com/problems/sort-list/
 */
public class Code0148 {

    public ListNode sortList(ListNode head) {
        // 使用快排

        sort(head, null);
        return head;

    }

    public void sort(ListNode start, ListNode end) {
        ListNode partition = partition(start, end);
        if (partition!=null&&start != partition)
            sort(start, partition);
        if (partition!=null&&partition != end)
            sort(partition.next, end);
    }

    public ListNode partition(ListNode start, ListNode end) {
        if (start == end||start==null) {
            return null;
        }
        ListNode curr = start.next;
        ListNode pivot = start;
        while (curr != null) {
            if (curr == end) {
                break;
            }
            if (curr.val < start.val) {
                pivot = pivot.next;
                // 交换节点
                int t = curr.val;
                curr.val = pivot.val;
                pivot.val = t;
            }
            curr = curr.next;
        }

        int t = pivot.val;
        pivot.val = start.val;
        start.val = t;

        return pivot;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(4);
        root.next = new ListNode(2);
        root.next.next = new ListNode(1);
        root.next.next.next = new ListNode(3);

        new Code0148().sortList(root);
    }

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
}
