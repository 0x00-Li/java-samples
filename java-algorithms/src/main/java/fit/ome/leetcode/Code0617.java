package fit.ome.leetcode;

public class Code0617 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return mergeNode(root1,root2);
    }
    public TreeNode mergeNode(TreeNode node1,TreeNode node2){
        if(node1==null){
            return node2;
        }
        if(node2==null){
            return node1;
        }
        node1.val+=node2.val;
        node1.left=mergeNode(node1.left,node2.left);
        node1.right=mergeNode(node1.right,node2.right);
        return node1;
    }

    /**
     * Definition for a binary tree node.
     */
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
