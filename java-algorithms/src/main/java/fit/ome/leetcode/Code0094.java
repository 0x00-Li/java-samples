package fit.ome.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树中序遍历
 */
public class Code0094 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        m(root, res);
        return res;
    }

    public void m(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        m(node.left, res);
        res.add(node.val);
        m(node.right, res);
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
