package fit.ome.leetcode;

import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * 从前序与中序遍历序列构造二叉树
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * <p>
 * 通过前序明确跟节点，通过中序，明确左右节点
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/29
 **/
public class Code0105 {
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        // 维护中序数组中的值的点的位置
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, 0, preorder.length - 1, 0, inorder.length - 1, inMap);
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 维护中序数组中的值的点的位置
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, 0, preorder.length - 1, 0, inorder.length - 1, inMap);
    }

    public TreeNode myBuildTree(int[] preOrder,  int preLeft, int preRight, int inLeft, int inRight, Map<Integer, Integer> inMap) {
        if (preLeft > preRight) {
            return null;
        }
        // 前序遍历，第一个是根节点
        int root = preLeft;
        int inOrderRoot = inMap.get(preOrder[root]);

        int leftSize = inOrderRoot - inLeft;

        TreeNode node = new TreeNode();
        node.val = preOrder[root];

        node.left = myBuildTree(preOrder,  preLeft + 1, preLeft + leftSize, inLeft, inOrderRoot - 1, inMap);
        node.right = myBuildTree(preOrder,   preLeft + leftSize + 1, preRight, inOrderRoot + 1, inRight, inMap);
        return node;
    }



    // i 根节点的在pre中 位置
    // l 树在 inorder 中的起始位置，包含l位置
    // r 树在 inorder 中的结束位置，不包含r位置
//    private TreeNode makeTree(int[] preorder, int i, int l, int r, Map<Integer, Integer> inMap) {
//        TreeNode node = new TreeNode();
//        node.val = preorder[i];
//        Integer rootIndex = inMap.get(node.val);//  只能提供左子树长度相关信息
//        int leftLen = rootIndex - l; //  2
//        int rightLen = r - rootIndex;//  0
//
//        int nextLeft = i + 1; // 1
//        int nextRight = i + leftLen + 1;// 1
//        if (nextLeft < nextRight && leftLen > 0 && nextLeft < preorder.length) {
//            node.left = makeTree(preorder, nextLeft, l, rootIndex, inMap);
//        }
//        if (nextRight < preorder.length && rightLen > 0 && nextRight < preorder.length) {
//            node.right = makeTree(preorder, nextRight, rootIndex + 1, r, inMap);
//        }
//        return node;
//    }

    public static void main(String[] args) {

//        System.out.println(new Code0105().buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
        System.out.println(new Code0105().buildTree(new int[]{1, 2}, new int[]{1, 2}));
//        System.out.println(new Code0105().buildTree(new int[]{1,2,3}, new int[]{2,3,1}));
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
