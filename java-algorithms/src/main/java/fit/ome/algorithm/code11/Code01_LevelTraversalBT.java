package fit.ome.algorithm.code11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 分层打印二叉树
 */
public class Code01_LevelTraversalBT {
    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int v) {
            val = v;
        }
    }

    public static void level(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue=new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.print(poll.val+",");
            if(poll.left!=null){
                queue.add(poll.left);
            }
            if(poll.right!=null){
                queue.add(poll.right);
            }
        }

    }

    public static void main(String[] args) {
        Node root=new Node(0);
        root.left=new Node(1);
        root.right=new Node(2);
        root.left.left=new Node(3);
        root.left.right=new Node(4);
        root.right.left=new Node(5);
        root.right.right=new Node(6);
        level(root);

    }
}
