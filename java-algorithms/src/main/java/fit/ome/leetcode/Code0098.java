package fit.ome.leetcode;

import java.util.Stack;

/**
 * 验证二叉搜索树
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 * <p>
 * 中序遍历递增
 */
public class Code0098 {
    int rootVal = 0;

    public boolean isValidBST(TreeNode root) {

        return v(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean v(TreeNode node, long low, long up) {
        if (node == null) {
            return true;
        }
        if (node.val <= low || node.val >= up) {
            return false;
        }
        return v(node.left, low, node.val) && v(node.right, node.val, up);

    }
    public boolean isValidBST1(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        long v=Long.MIN_VALUE;
        while (!stack.isEmpty()||root!=null){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            if(root.val<=v){
                return false;
            }
            v=root.val;
            root=root.right;
        }
        return true;
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
