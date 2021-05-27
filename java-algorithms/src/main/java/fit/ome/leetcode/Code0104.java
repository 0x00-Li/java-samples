package fit.ome.leetcode;

/**
 * 二叉树最大深度
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/27
 **/
public class Code0104 {
    public int maxDepth(TreeNode root) {
return m(root,0);
    }
    public static int m(TreeNode node,int deep){
        if (node==null){
            return deep;
        }
        deep++;

        return Math.max(m(node.left,deep),m(node.right,deep));
    }

    public class TreeNode {
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
