package fit.ome.algorithm.code09;

import java.util.HashMap;
import java.util.Map;

/**
 * 一种特殊的单链表节点类描述如下
 * class Node {
 * int value;
 * Node next;
 * Node rand;
 * Node(int val) { value = val; }
 * }
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 * 【要求】
 * 时间复杂度O(N)，额外空间复杂度O(1)
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/4/18
 **/
public class Code04_CopyRandomList {

    public static class Node {
        int val;
        Node next;
        Node rand;

        public Node(int v) {
            val = v;
        }
    }

    /**
     * 借助map实现拷贝
     *
     * @param head
     * @return
     */
    public static Node copyList1(Node head) {

        // key => old Node
        // value =new Node
        Map<Node, Node> map = new HashMap<>();
        Node curr = head;
        // 构建出所有node
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            map.get(curr).next = map.get(curr.next);
            map.get(curr).rand = map.get(curr.rand);
            curr = curr.next;
        }
        return map.get(head);
    }

    /**
     * 在原来的链表中，的节点间隙里面增加新节点，然后拷贝相关引用,生成新链表
     * 如：
     * 1-> 2-> 3 -> null
     * 1 -> 1'->2->2'->3->3'->null
     *
     * @param head
     * @return
     */
    public static Node copyList2(Node head) {
        Node curr = head;
        Node next = null;
        // add now node
        while (curr != null) {
            next = curr.next;

            curr.next = new Node(curr.val);
            curr.next.next = next;

            curr = next;
        }

        curr = head;
        while (curr != null) {
            curr.next.rand = curr.rand != null ? curr.rand.next : null;
            curr = curr.next.next;
        }
        // copy random
        curr = head;
        Node res = curr.next;
        Node copyNode = null;
        while (curr != null) {

            // 获取新的节点
            copyNode = curr.next;

            // 删除新的节点
            curr = curr.next.next;
            // 拷贝引用
            copyNode.next = curr != null ? curr.next : null;


        }
        return res;
    }

    private static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.val + (curr.rand != null ? "-" + curr.rand.val : ""));
            System.out.print(",");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(4);
        head.next.next = new Node(3);
        head.next.next.next = new Node(7);

        head.next.next.next.next = new Node(10);
        head.next.next.next.next.next = new Node(13);

        head.next.next.rand = head.next.next.next.next.next;
        printList(head);
        printList(copyList1(head));
        printList(copyList2(head));
    }
}
