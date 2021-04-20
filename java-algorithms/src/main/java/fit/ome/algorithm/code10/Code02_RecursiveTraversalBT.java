package fit.ome.algorithm.code10;

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
     * @param head
     */
    public static void beforeTraversal(Node head) {

    }

}
