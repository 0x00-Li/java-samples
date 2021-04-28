package fit.ome.algorithm.code30;

/**
 * morris 遍历
 * 适用于二叉树遍历，会产生自身的遍历序
 * <p>
 * 可以加工出前中后三种遍历序
 * <p>
 * 场景：不适用于头结点结果强依赖左右子节点结果的遍历
 * <p>
 * 时间复杂度O(N) 空间复杂度 O(1)
 */
public class Code01_MorrisTraversal {
    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int v) {
            val = v;
        }
    }

    // 递归遍历
    public static void recursion(Node root) {
        if (root == null) {
            return;
        }

        // 1. 处理业务，前序打印
        recursion(root.left);
        // 2. 处理业务，中序打印
        recursion(root.right);
        // 3. 处理业务，后序打印
    }

    // morris 遍历
    public static void morris(Node root) {
        Node curr = root;
        Node mostRight = null;
        while (curr != null) {
            System.out.print(curr.val + " ");
            mostRight = curr.left;
            if (mostRight != null) {
                // 滑动到左子树的最右节点
                while (mostRight.right != null && mostRight.right != curr) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    // 首次到达最右，最右指针指向当前节点
                    mostRight.right = curr;
                    curr = curr.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            // 无左树，直接向进入右子树
            curr = curr.right;
        }
        System.out.println();
    }

    // morris 前序遍历
    public static void morrisPre(Node root) {
        Node curr = root;
        Node mostRight = null;
        while (curr != null) {
            mostRight = curr.left;
            if (mostRight != null) {
                // 滑动到左子树的最右节点
                while (mostRight.right != null && mostRight.right != curr) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {
                    // 前序打印
                    System.out.print(curr.val + " ");

                    // 首次到达最右，最右指针指向当前节点

                    mostRight.right = curr;
                    curr = curr.left;
                    continue;
                } else {
                    // 此处是二次到达
                    mostRight.right = null;
                }
            } else {
                System.out.print(curr.val + " ");
            }
            // 无左树，直接向进入右子树
            curr = curr.right;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);
        morris(root);
        morrisPre(root);
    }
}
