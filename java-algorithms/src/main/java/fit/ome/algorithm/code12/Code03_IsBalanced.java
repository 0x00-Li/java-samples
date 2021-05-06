package fit.ome.algorithm.code12;

/**
 * 平衡二叉树
 * <p>
 * 任意节点的子树的高度差都小于等于1
 */
public class Code03_IsBalanced {
    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int v) {
            val = v;
        }
    }

    /**
     * 递归读取每科左或者右的子树的高度，进行比较
     */
    public static boolean isBalanced1(Node node) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(node, ans);
        return ans[0];

    }

    public static int process1(Node node, boolean[] ans) {
        if (!ans[0] || node == null) {
            return -1;
        }
        int leftH = process1(node.left, ans);
        int rightH = process1(node.right, ans);
        if (Math.abs(leftH - rightH) > 1) {
            ans[0] = false;
        }
        return Math.max(leftH, rightH) + 1;
    }

    /**
     * 统计二叉树信息，然后判断是否是平衡
     */
    public static class Info {
        boolean isBalanced;
        public int height;

        public Info(boolean b, int h) {
            isBalanced = b;
            height = h;
        }
    }

    public static boolean isBalanced2(Node node) {
        return process2(node).isBalanced;
    }

    public static Info process2(Node node) {
        if (node == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process2(node.left);
        Info rightInfo = process2(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = true;
        if (!leftInfo.isBalanced || !rightInfo.isBalanced) {
            isBalanced = false;
        } else {
            // 都是平衡树的时候，判断高度
            isBalanced = Math.abs(leftInfo.height - rightInfo.height) < 2;
        }
        return new Info(isBalanced, height);
    }

    // for test
    public static Node generateRandomTree(int maxLevel, int maxVal) {
        return generate(1, maxLevel, maxVal);
    }

    public static Node generate(int level, int maxLevel, int maxVal) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxVal));
        head.left = generate(level + 1, maxLevel, maxVal);
        head.right = generate(level + 1, maxLevel, maxVal);
        return head;
    }

    // for test
    public static void main(String[] args) {
        int maxLevel = 5;
        int maxVal = 3000;
        int opTimes = 100000;
        for (int i = 0; i < opTimes; i++) {
            Node node = generateRandomTree(maxLevel, maxVal);
            if (isBalanced1(node) != isBalanced2(node)) {
                System.out.println("Oops!!!!");
                break;
            }
        }
        System.out.println("finish");
    }
}
