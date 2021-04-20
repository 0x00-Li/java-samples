package fit.ome.algorithm.code10;

import java.util.Stack;

/**
 * 非递归实现
 * <p>
 * 递归其实是一个jvm实现的一个压栈的过程，我们手动压栈代替系统的压栈
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/4/20
 **/
public class Code03_UnrecursiveTraversalBT {
    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int v) {
            this.val = v;
        }
    }

    public static void pre(Node root) {
        if (root != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(root);
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                System.out.print(node.val + " ");
                if (node.right != null) {
                    stack.add(node.right);
                }
                if (node.left != null) {
                    stack.add(node.left);
                }
            }

        }
    }

    public static void mid(Node root) {
        if (root != null) {
            Stack<Node> stack = new Stack<>();

            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.add(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    System.out.print(root.val + " ");
                    root = root.right;
                }
            }
        }
    }

    public static void in(Node root) {
        Node cur = root;
        System.out.print("in-order: ");
        if (cur != null) {
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || cur != null) {
                if (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    cur = stack.pop();
                    System.out.print(cur.val + " ");
                    cur = cur.right;
                }
            }
        }
        System.out.println();
    }

    public static void pos1(Node cur) {
        Node root = cur;
        System.out.print("pos orderd:");
        if (root != null) {
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(root);
            while (!s1.isEmpty()) {
                root = s1.pop();
                s2.push(root);
                if (root.left != null) {
                    s1.push(root.left);
                }
                if (root.right != null) {
                    s1.push(root.right);
                }
            }
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().val + " ");
            }
        }
        System.out.println();

    }

    public static void pos2(Node root) {
        if (root != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Node cur = stack.peek();
                if (cur.left != null && cur.left != root && cur.right != root) {
                    stack.push(cur.left);
                } else if (cur.right != null && cur.right != root) {
                    stack.push(cur.right);
                } else {
                    System.out.print(stack.pop().val + " ");
                    root = cur;

                }
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(0);
        root.left = new Node(1);
        root.right = new Node(2);

        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(5);
        root.right.right = new Node(6);
        pre(root);
        System.out.println();
        System.out.println("=====");
        mid(root);
        System.out.println();
        in(root);
        System.out.println(

        );
        pos1(root);
        System.out.println("===========");
        pos2(root);
    }
}
