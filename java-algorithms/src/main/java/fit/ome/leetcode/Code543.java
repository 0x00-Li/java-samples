package fit.ome.leetcode;

/**
 * 二叉树的直径
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/16
 **/
public class Code543 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 每棵子树的直径然后求最大
    public int diameterOfBinaryTree(TreeNode root) {

        if (root == null) {
            return 0;
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        return Math.max(Math.max(leftInfo.maxDiameter, rightInfo.maxDiameter), leftInfo.level + rightInfo.level );
    }

    public Info process(TreeNode node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        return new Info(Math.max(leftInfo.level, rightInfo.level) + 1, Math.max(Math.max(leftInfo.maxDiameter, rightInfo.maxDiameter), leftInfo.level + rightInfo.level ));
    }

    public static class Info {
        int level;
        int maxDiameter;

        public Info(int l, int d) {
            level = l;
            maxDiameter = d;
        }
    }
}
