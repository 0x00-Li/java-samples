package fit.ome.leetcode;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 对称二叉树
 * https://leetcode-cn.com/problems/symmetric-tree/
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/25
 **/
public class Code0101 {
    public boolean isSymmetric1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<Integer> vl=new ArrayList<>();
        vl.add(root.val);
        while (!stack.isEmpty()) {
            root=stack.pop();

            if(root.left!=null){
                stack.push(root.left);
                vl.add(0,root.left.val);
            }
            if(root.right!=null){
                stack.push(root.right);
                vl.add(root.right.val);
            }
        }
        for (int i = 0,j=vl.size()-1; i <=j ; i++,j--) {
            if(vl.get(i)!=vl.get(j)){
                return false;
            }

        }
        return true;

    }

    public boolean isSymmetric(TreeNode root) {

        return m(root.left, root.right);
    }

    public boolean m(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        } else if (n1 != null && n2 != null && n1.val != n2.val) {
            return false;
        } else if ((n1 == null && n2 != null) || (n1 != null && n2 == null)) {
            return false;
        }
        return m(n1.left, n2.right) && m(n1.right, n2.left);
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
