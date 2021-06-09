package fit.ome.leetcode;

import java.util.Stack;

/**
 * 反转二叉树
 * https://leetcode-cn.com/problems/invert-binary-tree/
 */
public class Code0226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode t;
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            t = pop.right;
            pop.right = pop.left;
            pop.left = t;
           if(pop.right!=null){
               stack.push(pop.right);
           }
           if(pop.left!=null){
               stack.push(pop.left);
           }

        }
        return root;
    }

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
}
