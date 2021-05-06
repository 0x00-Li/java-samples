package fit.ome.algorithm.code12;

import java.util.ArrayList;
import java.util.List;

/**
 * 是否是二叉搜索树
 * <p>
 * 中序遍历，所有节点增序
 */
public class Code02_IsBST {
    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int v) {
            val = v;
        }
    }

    /**
     * 对二叉树，进行中序遍历，然后寻边比较
     * O(N)
     *
     * @param node
     * @return
     */
    public static boolean isBST1(Node node) {
        if (node == null) {
            return true;
        }
        ArrayList<Node> nodes = new ArrayList<>();
        in(node, nodes);
        for (int i = 1; i < nodes.size(); i++) {
            if (nodes.get(i).val <= nodes.get(i - 1).val) {
                return false;
            }
        }
        return true;

    }

    public static void in(Node node, List<Node> arr) {
        if (node == null) {
            return;
        }
        in(node.left, arr);
        arr.add(node);
        in(node.right, arr);
    }


    /**
     * 构建树的信息结构
     */
    public static class Info {
        boolean isBST;
        int max;
        int min;

        public Info(boolean i, int max, int min) {
            isBST = i;
            this.max = max;
            this.min = min;
        }
    }

    public static boolean isBST2(Node node) {
        if (node == null) {
            return true;
        }

        return process(node).isBST;
    }

    public static Info process(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int max = x.val;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
        }
        int min = x.val;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
        }
        boolean isBST = true;
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        if (leftInfo != null && leftInfo.max >= x.val) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.min <= x.val) {
            isBST = false;
        }
        return new Info(isBST, max, min);

    }
    // for test
    public static Node generateRandomBST(int maxLevel,int maxVal){
        return generate(1,maxLevel,maxVal);
    }
    //for test
    public static Node generate(int level,int maxLevel,int maxVal){
        if(level>maxLevel||Math.random()<0.5){
            return null;
        }
        Node head=new Node((int) (Math.random()*maxVal));
        head.left=generate(level+1,maxLevel,maxVal);
        head.right=generate(level+1,maxLevel,maxVal);
        return head;
    }

    public static void main(String[] args) {


        int maxLevel=4;
        int maxVal=300;
        int opTimes=1000;
        for (int i = 0; i < opTimes; i++) {
            Node node = generateRandomBST(maxLevel, maxVal);
            if(isBST1(node)!=isBST2(node)){
                System.out.println("Oops!!!");
                break;
            }
        }
        System.out.println("finish");
    }

}
