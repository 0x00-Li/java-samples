package fit.ome.leetcode;

import javax.swing.tree.TreeNode;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;

/**
 * 二叉树展开为链表
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/30
 **/
public class Code0114 {
    TreeNode tail;

    public void flatten(TreeNode root) {
        tail = root;
        pre(root);
    }

    public void pre(TreeNode node) {
        if (node == null) {
            return;
        }

        // do node
        TreeNode r = node.right;
        TreeNode l = node.left;
        if (l != null) {
            tail.right = l;
            tail.left = null;
            tail = tail.right;
            pre(l);
        }

        if (r != null) {
            tail.right = r;
            tail.left = null;
            tail = tail.right;
            pre(r);
        }

    }

    /**
     * 迭代实现
     *
     * @param root
     */
    public void flatten1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode node = root;
        while (node != null) {
            if (node.right != null) {
                stack.push(node.right);
            }
            node.right = node.left;
            node.left = null;
            if(node.right!=null){
                node = node.right;
            }

            if (node == null && !stack.isEmpty()) {
                node = stack.pop();
            }
        }
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
