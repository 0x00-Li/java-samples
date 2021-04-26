package fit.ome.algorithm.code12;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断是否是完全二叉树
 * <p>
 * <p>
 * 定义
 * <p>
 * 满二叉树，或者在成为满二叉树的路上的二叉树
 * <p>
 * 从上到下，从左到右 的过程中变成满二叉树
 * <p>
 * 叶子节点满，或者叶子节点在不慢情况的下，是从左到有的满的状态
 */
public class Code01_IsCBT {
    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int v) {
            this.val = v;
        }
    }

    // 直接判断是否是完全二叉树
    // 完全二叉树的定义：满二叉树，或者从上到下，从左到右成为满二叉树的路上
    public static boolean isCBT1(Node root) {
        if (root == null) {
            return true;
        }
        boolean leaf = false;
        Node head = root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            Node l = node.left;
            Node r = node.right;
            if (
                    (
                            leaf && (l != null || r != null)
                    )
                            ||
                            (
                                    l == null && r != null
                            )) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    public static boolean isCBT2(Node root) {
        if (root == null) {
            return true;
        }
        return process(root).isCBT;
    }

    // 扩大实现
    // 树的信息对象
    // 是否是满二叉树 isFull
    // 是否是完全二叉树 isCBT
    // 树的高度
    public static class Info {
        boolean isFull;
        boolean isCBT;
        int height;

        public Info(boolean full, boolean cbt, int height) {
            this.isFull = full;
            this.isCBT = cbt;
            this.height = height;
        }
    }

    public static Info process(Node root) {
        if (root == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        // 判断是否是满二叉树
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isCBT = false;
//        leftInfo.isFull&&rightInfo.isFull&&leftInfo.height<=rightInfo.height+1;
        if (isFull) {
            isCBT = true;
        } else {// 当前节点为头的子树，不是满二叉树
            if (rightInfo.isCBT && leftInfo.isCBT) {
                if (rightInfo.isFull && leftInfo.isCBT && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                if (rightInfo.isFull && leftInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
                    isCBT = true;
                }
            }
        }
        return new Info(isFull, isCBT, height);
    }

    // for test
    public static Node generateRandomBST(int maxVal, int maxLevel) {
        return generate(1, maxVal, maxLevel);
    }

    // for test
    public static Node generate(int level, int maxVal, int maxLevel) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node node = new Node((int) (Math.random() * maxVal));
        node.left = generate(level + 1, maxVal, maxLevel);
        node.right = generate(level + 1, maxVal, maxLevel);
        return node;
    }


    public static void main(String[] args) {
        int maxVal = 1000;
        int maxLevel = 20;
        int opTimes = 1000;
        for (int i = 0; i < opTimes; i++) {
            Node node = generateRandomBST(maxVal, maxLevel);
            if (isCBT1(node) != isCBT2(node)) {
                System.out.println("Oops!!!");
                break;
            }
        }
        System.out.println("finish");

    }
}
