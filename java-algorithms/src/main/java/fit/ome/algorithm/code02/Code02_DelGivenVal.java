package fit.ome.algorithm.code02;

import org.w3c.dom.Node;

/**
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/20
 **/
public class Code02_DelGivenVal {
    public static class Node {
        public int v;
        public Node next;

        public Node(int v) {
            this.v = v;
        }
    }

    /**
     * 可能有连续的值需要删除
     *
     * @param head
     * @param val
     * @return
     */
    public static Node remove(Node head, int val) {
        // 判断从头开始有连续值需要删除
        while (head != null) {
            if (head.v != val) {
                break;
            }
            head = head.next;
        }
        Node curr = head;
        Node pre = head;
        while (curr != null) {
            if (curr.v == val) {
                // 值相等，删除当前位置
                pre.next = curr.next;
            } else {
                // 值不相等，等待指针跟随移动
                pre=curr;
            }
            // 指针位置，移动
            curr = curr.next;
        }
        return head;
    }

}
