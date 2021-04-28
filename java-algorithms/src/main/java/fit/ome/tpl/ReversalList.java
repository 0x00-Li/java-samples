package fit.ome.tpl;

/**
 * 单链表反转
 */
public class ReversalList {

    public static class Node {
        int val;
        Node next;

        public Node(int v) {
            val = v;
        }
    }

    public static Node reversal(Node root) {

        Node head = root;
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void printList(Node root) {
        Node cur = root;
        while (cur != null) {
            System.out.print(cur.val + "->");
            cur = cur.next;
        }
        System.out.println("null");

    }

    public static void main(String[] args) {
        Node root = new Node(0);
        root.next = new Node(1);
        root.next.next = new Node(2);
        root.next.next.next = new Node(3);
        printList(root);
        printList(reversal(root));
    }
}
