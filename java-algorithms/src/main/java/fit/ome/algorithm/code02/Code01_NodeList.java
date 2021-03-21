package fit.ome.algorithm.code02;

import org.w3c.dom.Node;

import java.util.ArrayList;

/**
 * 单项链表
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/20
 **/
public class Code01_NodeList {

    private Node head = null;
    private Node end = null;

    private void add(int v) {
        if (head == null) {
            head = new Node(v);
            end = head;
            return;
        }

        end.next = new Node(v);
        end = end.next;
    }

    /**
     * 有问题，逻辑不成立
     *
     * @param v
     */
    public void remove(int v) {
        Node curr = head;
        Node pre = head;
        while (curr != null) {
            if (curr.v == v) {
                pre = curr.next;
            } else {
                curr = curr.next;
            }
        }
        head = pre;
    }


    public static class Node {
        public int v;
        public Node next;

        public Node(int v) {
            this.v = v;
        }
    }


    public Node reverseList(Node head) {
        Node preHead = head;
        Node currHead = null;
        Node currNext = null;
        while (preHead != null) {
            currHead = preHead;
            currHead.next = currNext;


            preHead = preHead.next;
            currNext = currHead;

        }
        return currHead;
    }

    public static Node reverseList01(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            // 持有下一个节点
            next = head.next;
            // 置空原始head的下一个节点
            head.next = null;
            // 获取新链表的第一个节点
            pre = head;
            // head 后移
            head = next;
        }
        return pre;
    }


    public static Node generatedLinkedList(int maxSize, int maxValue) {
        int size = (int) (Math.random() * (maxSize + 1));
        Node head = null;
        Node next = null;
        for (int i = 0; i < size; i++) {
            next = new Node((int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1)));
            if (head == null) {
                head = next;
            }
        }
        return head;
    }

    public static Node testReverseLinkedList(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        int size = list.size();
        for (int i = 1; i < size; i++) {
            list.get(i - 1).next = list.get(i);
        }
        return list.get(size - 1);
    }

    public static boolean checkLinkedList(Node a, Node b) {
        while (a != null && b != null) {
            if (a.v != b.v) {
                return false;
            }
            a = a.next;
            b = b.next;
        }
        return a == null && b == null;
    }

    /**
     * 双向链表
     */
    public static class DoubleNode {
        public int v;
        public DoubleNode next;
        public DoubleNode pre;

        public DoubleNode(int data) {
            this.v = data;
        }
    }

    /**
     * 反转双向链表
     *
     * @param head
     * @return
     */
    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            // 转移当前节点的下一个节点
            next = head.next;

            // 新链表获取第一个节点
            pre = head;
            // 衔接，前后节点
            pre.next = head.pre;
            pre.pre = next;
            // 原始head 移动
            head = next;

        }
        return pre;
    }

    public static DoubleNode testDoubleList(DoubleNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<DoubleNode> list = new ArrayList<>();

        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int size = list.size();
        list.get(0).next = null;
        list.get(size - 1).pre = null;
        for (int i = 1; i < size - 2; i++) {
            list.get(i).next = list.get(i + 1);
            list.get(i).pre = list.get(i - 1);
        }
        return list.get(size - 1);
    }

    public static DoubleNode generatedRandomDoubleList(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        DoubleNode head = new DoubleNode((int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue)));
        DoubleNode curr = head;
        DoubleNode next = null;
        for (int i = 1; i < len; i++) {
            next = new DoubleNode((int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue)));

            next.pre = curr;
            curr.next = next;

            curr = next;

        }
        return head;

    }

    public static boolean checkDoubleList(DoubleNode head, DoubleNode head2) {
        boolean null1 = head != null;
        boolean null2 = head2 != null;
        if (null1 && null2) {
            return true;
        }
        if (null1 ^ null2) {
            return false;
        }
        if (head.pre != null || head2.pre != null) {
            return false;
        }
        DoubleNode end1 = null;
        DoubleNode end2 = null;
        while (head != null && head2 != null) {
            if (head.v != head2.v) {
                return false;
            }
            end1 = head;
            end2 = head2;

            head = head.next;
            head2 = head2.next;
        }
        if (head != null || head2 != null) {
            return false;
        }

        while (end1 != null && end2 != null) {
            if (end1.v != end2.v) {
                return false;
            }
            end1 = end1.next;
            end2 = end2.next;
        }

        return end1 == null && end2 == null;
    }

    public static void main(String[] args) {
        int times = 10000;
        int maxSize = 20;
        int maxValue = 100;
        System.out.println("begin linked list");
        for (int i = 0; i < times; i++) {
            Node head = generatedLinkedList(maxSize, maxValue);
            Node reverseHead = reverseList01(head);
            Node back = testReverseLinkedList(reverseHead);
            if (!checkLinkedList(head, back)) {
                System.out.println("Oops!");
                break;
            }


            DoubleNode dhead = generatedRandomDoubleList(maxSize, maxValue);
            DoubleNode reverseDHead = reverseDoubleList(dhead);
            DoubleNode dback = testDoubleList(dhead);
            if (!checkDoubleList(dhead, dback)) {
                System.out.println("double check Oops!");
                break;
            }
        }
        System.out.println("finish lined list");
    }
}
