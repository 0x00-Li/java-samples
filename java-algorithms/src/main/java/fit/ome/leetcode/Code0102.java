package fit.ome.leetcode;

import java.util.*;

/**
 * 二叉树层序遍历
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/26
 **/
public class Code0102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        List<Integer> level = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty() || !queue2.isEmpty()) {
            while (!queue.isEmpty()) {
                root = queue.poll();
                level.add(root.val);
                if (root.left != null) {
                    queue2.offer(root.left);
                }
                if (root.right != null) {
                    queue2.offer(root.right);
                }
            }
            if (level.size() > 0) {
                res.add(new ArrayList<>(level));
                level.clear();
            }
            while (!queue2.isEmpty()) {
                root = queue2.poll();
                level.add(root.val);
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
            }
            if (level.size() > 0) {
                res.add(new ArrayList<>(level));
                level.clear();
            }
        }
        return res;
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
