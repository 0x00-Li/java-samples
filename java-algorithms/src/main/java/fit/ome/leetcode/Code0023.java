package fit.ome.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 合并n个升序链表
 */
public class Code0023 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        List<ListNode> all = new ArrayList<>();
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                all.add(lists[i]);
            }
        }
        if (all.size() == 0) {
            return null;
        }
        if (all.size() == 1) {
            return all.get(0);
        }
        ListNode res = all.get(0);
        ListNode curr = null;

        int minIndex = 0;

        for (int i = 1; i < all.size(); i++) {
            if (all.get(i).val < res.val) {
                res = all.get(i);
                minIndex = i;
            }
        }
        curr = res;
        if (res.next == null) {
            all.remove(minIndex);
        } else {
            all.set(minIndex, all.get(minIndex).next);
        }
        merge(all, curr);

        return res;
    }

    public void merge(List<ListNode> lists, ListNode cur) {
        if (lists.size() == 0) {
            return;
        }
        int minIndex = 0;
        ListNode min = lists.get(0);
        for (int i = 1; i < lists.size(); i++) {
            if (lists.get(i).val < min.val) {
                min = lists.get(i);
                minIndex = i;
            }
        }
        cur.next = min;
        if (lists.get(minIndex).next == null) {
            lists.remove(minIndex);
        } else {
            lists.set(minIndex, lists.get(minIndex).next);
        }

        cur = cur.next;
        merge(lists, cur);
    }

    // 以上方法，效率较差，使用小根堆进行优化【也可使用两两合并思路进行优化】
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> minStack = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                minStack.offer(lists[i]);
            }
        }
        ListNode res=null;

        if(!minStack.isEmpty()){
            res=minStack.poll();
            if(res.next!=null){
                minStack.offer(res.next);
            }
        }
        ListNode curr=res;
        while (!minStack.isEmpty()){
           curr.next= minStack.poll();
           curr=curr.next;
           if(curr.next!=null){
               minStack.offer(curr.next);
           }
        }
        return res;
    }
}
