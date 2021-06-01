package fit.ome.leetcode;

/**
 * 最大路径和
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 */
public class Code0124 {
    public int maxPathSum(TreeNode root) {
        return pre(root).maxSum;
    }

    public Info pre(TreeNode node) {
        if (node == null) {
            return null;
        }
        if(node.val==4){
            System.out.println(node.val);
        }
        Info leftInfo = pre(node.left);
        Info rightInfo = pre(node.right);
        int leftSum = (leftInfo == null ? 0 : Math.max(leftInfo.leftSum,leftInfo.rightSum) )+ node.val; // 左和
        int rightSum = (rightInfo == null ? 0 : Math.max(rightInfo.rightSum,rightInfo.leftSum)) + node.val;// 右和

        int maxSum = Math.max(leftSum, rightSum);// 左右求最大
        maxSum = Math.max(maxSum, leftSum + rightSum - node.val);// 对比整个回路
        maxSum = Math.max((leftInfo == null ? Integer.MIN_VALUE : leftInfo.maxSum), maxSum);// 比对左子最大
        maxSum = Math.max((rightInfo == null ? Integer.MIN_VALUE : rightInfo.maxSum), maxSum);// 比对右子最大
        maxSum=Math.max(maxSum,node.val);// 比对当前节点值

        return new Info(leftSum, rightSum, maxSum);
    }

    public static class Info {
        int leftSum;
        int rightSum;
        int maxSum=Integer.MIN_VALUE;


        public Info(int ls, int rs, int m) {
            leftSum = ls;
            rightSum = rs;
            maxSum = m;

        }

    }

    public static void main(String[] args) {
//        TreeNode root=new TreeNode(1);
//        root.left=new TreeNode(2);
//        root.right=new TreeNode(3);
        TreeNode root=new TreeNode(5);
        root.left=new TreeNode(4);
        root.right=new TreeNode(8);

        root.left.left=new TreeNode(11);
        root.left.left.left=new TreeNode(7);
        root.left.left.right=new TreeNode(2);

        root.right.left=new TreeNode(13);
        root.right.right=new TreeNode(4);
        root.right.right.right=new TreeNode(1);

        System.out.println(new Code0124().maxPathSum(root));
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
