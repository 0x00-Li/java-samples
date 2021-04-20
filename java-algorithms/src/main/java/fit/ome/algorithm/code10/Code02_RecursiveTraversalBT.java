package fit.ome.algorithm.code10;

import org.w3c.dom.Node;

/**
 * 遍历二叉树
 * 先序：任何子树的处理顺序都是，先头节点、再左子树、然后右子树
 * <p>
 * 中序：任何子树的处理顺序都是，先左子树、再头节点、然后右子树
 * <p>
 * 后序：任何子树的处理顺序都是，先左子树、再右子树、然后头节点
 * <p>
 * 递归遍历二叉树
 * 1）理解递归序
 * <p>
 * 2）先序、中序、后序都可以在递归序的基础上加工出来
 * <p>
 * 3）第一次到达一个节点就打印就是先序、第二次打印即中序、第三次即后序
 */
public class Code02_RecursiveTraversalBT {

    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int v) {
            val = v;
        }
    }

    /**
     * 前序遍历
     *
     * @param root
     */
    public static void beforeTraversal(Node root) {
        if (root == null) {
            return;
        }

        // 优先打印根节点
        System.out.print(root.val + " ");
        beforeTraversal(root.left);
        beforeTraversal(root.right);

    }

    public static void midTraversal(Node root) {
        if (root == null) {
            return;
        }
        midTraversal(root.left);

        System.out.print(root.val + " ");

        midTraversal(root.right);

    }

    public static void afterTraversal(Node root) {
        if (root == null) {
            return;
        }
        afterTraversal(root.left);
        afterTraversal(root.right);
        System.out.print(root.val + " ");

    }

    // 总结

    /**
     * 每个节点都会经历三次
     *
     * @param node
     */
    public static void f(Node node) {
        if (node == null) {
            return;
        }
        System.out.print("(1):" + node.val + ";");
        // 1
        f(node.left);
        System.out.print("(2):" + node.val + ";");
        // 2
        f(node.right);
        System.out.print("(3):" + node.val + ";");
        // 3
    }

    public static void main(String[] args) {
        Node root = new Node(0);
        root.left = new Node(1);
        root.right = new Node(2);

        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(5);
        root.right.right = new Node(6);
        beforeTraversal(root);
        System.out.println();
        System.out.println("=====================");
        midTraversal(root);
        System.out.println();
        System.out.println("=====================");
        afterTraversal(root);
        System.out.println()
        ;
        System.out.println("==============");
        f(root);

    }

}
