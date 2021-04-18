package fit.ome.algorithm.code09;

import org.w3c.dom.Node;

import java.util.Stack;

/**
 * 判断是否是回文链表,
 * <p>
 * <p>
 * 不是根据节点的地址，是根据节点的值
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/4/18
 **/
public class Code02_IsPalindromeList {
    public static class Node {
        int val;
        Node next;

        public Node(int v) {
            val = v;
        }
    }

    /**
     * 直接暴力通过jdk的栈实现
     * <p>
     * 根据回文链表的话，正序和逆序结果是一样的
     * <p>
     * 全部压入栈，然后弹出和正序的链表进行比较
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Stack<Node> stack = new Stack<Node>();
        Node n = head;
        while (n != null) {
            stack.add(n);
            n = n.next;
        }
        n = head;
        while (!stack.isEmpty()) {
            if (n.val != stack.pop().val) {
                return false;
            }
            n = n.next;
        }
        return true;

    }

    /**
     * 进行空间优化
     * <p>
     * 在第一种方法使用了O(n)的空间复杂度
     * <p>
     * 其实只需要对链表的后半段进行栈处理就可以了
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 获取中点之后的节点
        // 奇数个点的话，取正中点
        // 偶数个节点的话，取下中点
        Node right = head.next;
        Node node = head.next;
        while (node.next != null && node.next.next != null) {
            right = right.next;
            node = node.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while (right != null) {
            stack.add(right);
            right = right.next;
        }
        node = head;
        while (!stack.isEmpty()) {
            if (node.val != stack.pop().val) {
                return false;
            }
            node = node.next;
        }
        return true;

    }

    /**
     * 使用O(1) 复杂度进行判断
     * 不占用额外空间
     * <p>
     * <p>
     * 找出中点，然后翻转中点
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node right = head.next; // 右侧链表七点，整条链表的中点或者中下点
        Node curr = head.next;
        while (curr.next != null && curr.next.next != null) {
            curr = curr.next.next;
            right = right.next;
        }
        // 翻转链表
        Node h = right.next;//h -> 右侧头节点
        right.next = null; // 头节点下一个指向空
        Node tmp = null; // 新建临时节点
        while (h != null) {
            tmp = h.next;  // 保存原链表的下一个节点
            h.next = right; // 调整新链表头的下一个节点

            right = h; // 向右移动
            h = tmp; // 向右移动
        }
        Node rh = right;// 设置为右侧新链表的 新头
        Node lh = head;

        boolean res = true;
        while (lh != null && rh != null) {
            if (lh.val != rh.val) {
                res = false;
                break;
            }
            lh = lh.next;
            rh = rh.next;
        }

        // 恢复链表

        // 先恢复右侧链表
        rh = right.next;
        right.next = null;
        curr = null;
        while (rh != null) {
            curr = rh.next;
            rh.next = right;

            right = rh;
            rh = curr;
        }


        return res;

    }

    //for test
    public static void printList(Node head) {
        Node n = head;
        while (n != null) {
            System.out.print(n.val);
            n = n.next;

        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        Node head = null;
        System.out.println(isPalindrome1(head));
        // =================
        head = new Node(0);
        System.out.println(isPalindrome1(head));

        // ====================
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        head.next.next.next.next.next = new Node(0);
        printList(head);
        System.out.println(isPalindrome1(head));
        printList(head);

        System.out.println(isPalindrome2(head));
        printList(head);
        System.out.println(isPalindrome3(head));

        printList(head);
        System.out.println("================================");
        head = new Node(3);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(2);
        head.next.next.next.next.next = new Node(1);
        head.next.next.next.next.next.next = new Node(3);
        printList(head);
        System.out.println(isPalindrome1(head));
        printList(head);
        System.out.println(isPalindrome2(head));
        printList(head);
        System.out.println(isPalindrome3(head));
        printList(head);
        System.out.println("================================");

    }
}
