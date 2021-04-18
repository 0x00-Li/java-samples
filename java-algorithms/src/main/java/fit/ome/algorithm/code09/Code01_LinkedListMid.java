package fit.ome.algorithm.code09;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 快慢指针联系
 * 1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 * <p>
 * 2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 * <p>
 * 3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
 * <p>
 * 4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/4/18
 **/
public class Code01_LinkedListMid {

    public static class Node {
        int val;
        Node next;

        public Node(int v) {
            val = v;
        }
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     * 快指针步长为2
     * 慢指针为 1
     * <p>
     * 长度为偶的时候，
     * 快指针停止，在倒数第二个节点
     * 慢指针会停止在，上中点
     * <p>
     * 长度为奇数的时候
     * 快指针，会刚好走到尾节点
     * 慢指针会走到中点
     *
     * @param head
     * @return
     */
    public static Node midOrUpNode(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null || head.next.next == null) {
            return head;
        }
        Node quick = head;
        Node slow = head;
        while (quick.next != null && quick.next.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;

    }

    /**
     * 在设置起始指针的时候，保留错位空间
     *
     * @param head
     * @return
     */
    public static Node midOrUpNode2(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null || head.next.next == null) {
            return head;
        }
        Node quick = head.next.next;
        Node slow = head.next;
        while (quick.next != null && quick.next.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;

    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     *
     * @param head
     * @return
     */
    public static Node midOrDownNode(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null || head.next.next == null) {
            return head;
        }
        Node quick = head;
        Node slow = head;
        while (quick.next != null && quick.next.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        slow = slow.next;
        return slow;

    }

    /**
     * 将快慢指针和中点相对位置前置
     *
     * @param head
     * @return
     */
    public static Node midOrDownNode2(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        Node quick = head.next;
        Node slow = head.next;
        while (quick.next != null && quick.next.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;

    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     * 基于快慢指针的分析，保留一个前置指针可以解决
     *
     * @param head
     * @return
     */
    public static Node midOrUpPreNode(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null || head.next.next == null) {
            return head;
        }
        Node quick = head;
        Node slow = head;
        Node pre = head;
        while (quick.next != null && quick.next.next != null) {
            pre = slow;
            slow = slow.next;
            quick = quick.next.next;
        }
        return pre;

    }

    /**
     * 错位空间前置
     *
     * @param head
     * @return
     */
    public static Node midOrUpPreNode2(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null || head.next.next == null) {
            return head;
        }
        Node quick = head.next.next;
        Node slow = head;

        while (quick.next != null && quick.next.next != null) {

            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;

    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
     *
     * @param head
     * @return
     */
    public static Node midOrDownPreNode(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null || head.next.next == null) {
            return head;
        }
        Node quick = head;
        Node slow = head;

        while (quick.next != null && quick.next.next != null) {

            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;

    }

    public static Node midOrDownPreNode2(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null || head.next.next == null) {
            return head;
        }
        Node quick = head.next;
        Node slow = head;

        while (quick.next != null && quick.next.next != null) {

            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;

    }

    // for test
    public static Node rightMidOrUpNode(Node head) {
        if (head == null) {
            return head;
        }
        List<Node> list = new ArrayList<>();
        Node n = head;
        while (n != null) {
            list.add(n);
            n = n.next;
        }
        // 3  /2 =1 ==> 1
        // 4/2=2 ==> 2
        return list.get((list.size() - 1) / 2);
    }

    // for test
    public static Node rightMidOrDownNode(Node head) {
        if (head == null) {
            return head;
        }
        List<Node> list = new ArrayList<>();
        Node n = head;
        while (n != null) {
            list.add(n);
            n = n.next;
        }
        // 3 /2 =1 ==> 1
        // 4 /2=2 ==> 2
        return list.get((list.size()) / 2);
    }

    public static void main(String[] args) {
        Node head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = new Node(5);
        head.next.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next.next = new Node(7);

        Node rightMidOrUpNode = rightMidOrUpNode(head);
        if (midOrUpNode(head) != rightMidOrUpNode || rightMidOrUpNode != midOrUpNode2(head)) {
            System.out.println("Oops!!!");
            System.out.println("mid or up mid node failed!!");
        }
        Node rightMidDown = rightMidOrDownNode(head);
        if (midOrDownNode(head) != rightMidDown || rightMidDown != midOrDownNode2(head)) {
            System.out.println("Oops!!!");
            System.out.println("mid or down mid node failed!!");
        }

        if (midOrUpPreNode(head) != midOrUpPreNode2(head)) {
            System.out.println("Oops!!");
            System.out.println("mid or up pre failed");
        }

        if (midOrDownPreNode(head) != midOrDownPreNode2(head)) {
            System.out.println("Oops!!");
            System.out.println("mid or down pre failed");
        }
    }
}
