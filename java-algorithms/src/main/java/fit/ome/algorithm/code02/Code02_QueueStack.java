package fit.ome.algorithm.code02;

import org.w3c.dom.Node;

/**
 * 队列实现，满足FIFO
 * 栈实现 后进先出
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/20
 **/
public class Code02_QueueStack {


    public static class Node {
        public int val;

        public Node next;

        public Node(int v) {
            val = v;
        }
    }

    public static class MyQueue {
        public Node head;
        public Node end;

        // 进入队列的放入队列尾部
        public Node push(int val) {
            if (head == null) {
                head = new Node(val);
                end = head;
                return head;
            }
            end.next = new Node(val);
            end = end.next;
            return head;
        }

        public Node pop() {
            Node v = head;
            head = head.next;
            return v;
        }
    }

    public static class MyStack {
        public Node top;
        public Node bottom;

        public Node push(int v) {
            if (top == null) {
                top = new Node(v);
                bottom = top;
            }
            bottom.next = new Node(v);
            bottom = bottom.next;
            return top;
        }

        public Node pop() {
            Node n = top;
            top = top.next;
            return n;
        }
    }


}
