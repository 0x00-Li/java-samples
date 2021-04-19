package fit.ome.algorithm.code10;

/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
 * 【要求】
 * 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
 * <p>
 * <p>
 * 情况分析
 * 1. 两个链表不相交
 * 2. 两个链表相交但是无环
 * 3. 两个链表相交有环
 * <p>
 * 交叉节点获取，分析：
 * 1. 如果两个链表相交，一定是后半段相交，因为是单链表
 * 2. 分析出两个链表的长短，以及差的节点数
 * 3. 推进长链表至，相交前，两个两个链表相同长度的位置
 * 4. 无环链表，然后两个链表并行推进，相等则为交点
 * 5. 有环链表，先获取环中节点
 * 5.1 如果是两个共同的环中节点，解决思路和无环的一致，
 * 5.2 如果不是共同的环中节点，直接分析单条链表中的入环点即可
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/4/18
 **/
public class Code01_FindFirstIntersectNode {
    public static class Node {
        int val;
        Node next;

        public Node(int v) {
            val = v;
        }
    }

    public static Node firstIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 != null && loop2 != null) {
            // 链表中有环
            return loopNode(head1, loop1, head2, loop2);
        } else {
            // 链表中无环
            return noLoopNode(head1, head2);
        }

    }

    /**
     * 获取有环链表的入口第一个节点
     * <p>
     * 先将环外节点长度对其
     *
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
    public static Node loopNode(Node head1, Node loop1, Node head2, Node loop2) {

        return null;
    }

    /**
     * 获取无环链表入口第一个入环节点
     * <p>
     * 1.单链表，两个链表相交之后不会再分开
     * 2. 长短差异体现在相交前
     * 3. 长链表优先推进，保证两个链表相交前，登长，然后同时推进，求出交点的入口节点
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoopNode(Node head1, Node head2) {
        Node curr = head1;
        int len = 0;
        while (curr != null) {
            len++;
        }
        curr = head2;
        while (curr != null) {
            len--;
        }
        Node lNode = len > 0 ? head1 : head2;// 长链表头
        Node sNode = lNode == head1 ? head2 : head1; //短链表头

        while (len > 0) {
            lNode = lNode.next;
            len--;
        }
        while (lNode != sNode) {
            lNode = lNode.next;
            sNode = sNode.next;
        }

        return sNode;
    }

    /**
     * 获取环中节点
     * 无环返回空
     * <p>
     * <p>
     * 通过快慢指针，判断是否有环，并获取相关的环,入环节点
     *
     * @param head
     * @return
     */
    private static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node fast = head.next.next;
        Node slow = head.next;

        while (fast != slow) {
            if (fast == null || slow == null) {
                // 无环
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        // 至此，说明，有环，fast 和slow相遇

        // 重新设置快指针
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;

    }

}
