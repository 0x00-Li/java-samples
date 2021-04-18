package fit.ome.algorithm.code09;

import org.w3c.dom.Node;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * <p>
 * 1）把链表放入数组里，在数组上做partition（笔试用）
 * <p>
 * 2）分成小、中、大三部分，再把各个部分之间串起来（面试用）
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/4/18
 **/
public class Code03_SmallerEqualBigger {

    public static class Node {
        int val;
        Node next;

        public Node(int v) {
            val = v;
        }
    }

    /**
     * 把链表放入数组里，在数组上做partition
     *
     * @param head
     */
    public static Node partitionList(Node head, int pivot) {

        if (head == null || head.next == null) {
            return null;
        }
        // 链表转成数组
        Node curr = head;
        int len = 0;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        Node[] arrNode = new Node[len];
        curr = head;
        for (int i = 0; i != arrNode.length; i++) {
            arrNode[i] = curr;
            curr = curr.next;
        }
        arrPartition(arrNode, pivot);
        int i = 0;
        for (i = 1; i != arrNode.length; i++) {
            arrNode[i - 1].next = arrNode[i];

        }
        arrNode[i - 1].next = null;
        return arrNode[0];


    }

    private static void arrPartition(Node[] arr, int pivot) {
        int small = -1;
        int big = arr.length;
        int index = 0;
        while (index != big) {
            if (arr[index].val < pivot) {
                swap(arr, ++small, index++);
            } else if (arr[index].val == pivot) {
                index++;
            } else {
                swap(arr, --big, index);
            }
        }
    }

    private static void swap(Node[] arr, int i, int j) {
        Node tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 使用大中小个列表进行处理
     *
     * @param head
     * @param pivot
     * @return
     */
    public static Node partitionList2(Node head, int pivot) {
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node bH = null;
        Node bT = null;
        Node next = null;

        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.val < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }


            } else if (head.val == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }

            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }

            }

            head = next;
        }

        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }
        if (eT != null) {
            eT.next = bH;
        }

        return sH != null ? sH : (eH != null ? eH : bH);
    }

    // for test
    public static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.val + ",");
            curr = curr.next;
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        Node head = new Node(6);
        head.next = new Node(3);
        head.next.next = new Node(8);
        head.next.next.next = new Node(10);
        head.next.next.next.next = new Node(7);
        printList(head);
        printList(partitionList(head, 7));
        head = new Node(6);
        head.next = new Node(3);
        head.next.next = new Node(8);
        head.next.next.next = new Node(10);
        head.next.next.next.next = new Node(7);
        printList(partitionList2(head, 7));

    }
}
