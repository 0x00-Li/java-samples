package fit.ome.leetcode_day;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Code_0037 {
    private static final String NODE_SEPARATOR = "#";
Deque<TreeNode> deque=new LinkedList<>();
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        TreeNode node=root;
        deque.offer(node);
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()){
            TreeNode n = deque.poll();
            sb.append(n==null?"":n.val);

            deque.offer(n.left);
            deque.push(n.right);
        }

        pre(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(NODE_SEPARATOR);
        return new TreeNode(1);
    }
    public void dserialize(String[] data,TreeNode node){

    }


    //先序遍历进行序列化
    public void pre(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NODE_SEPARATOR);
            return;
        }
        sb.append(node.val);
        sb.append(NODE_SEPARATOR);
        pre(node.left, sb);
        pre(node.right, sb);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
