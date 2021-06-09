package fit.ome.leetcode;

/**
 * 最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * <p>
 * 自底向上节点开始查找，p和q
 */
public class Code0236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }
        return process(root, p, q).node;
    }

    public Info process(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            // 不在此子树上
            return new Info(null, false, false);
        }

        // 是否在坐子树上
        Info leftInfo = process(node.left, p, q);
        if(leftInfo.hasP&&leftInfo.hasQ){
            return leftInfo;
        }
        // 是否在右子树上
        Info rightInfo = process(node.right, p, q);
        if(rightInfo.hasP&&rightInfo.hasQ){
            return rightInfo;
        }
        boolean hasP = leftInfo.hasP || rightInfo.hasP||node==p;
        boolean hasQ = leftInfo.hasQ || rightInfo.hasQ||node==q;
        return new Info(node, hasP, hasQ);
    }

    public static class Info {
        boolean hasP;
        boolean hasQ;
        TreeNode node;

        public Info(TreeNode n, boolean p, boolean q) {
            node = n;
            hasP = p;
            hasQ = q;
        }
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        System.out.println(new Code0236().lowestCommonAncestor(root,root.right,root.left).val);;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
