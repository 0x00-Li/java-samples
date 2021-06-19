package fit.ome.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文链表
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class Code0234 {


    // 暴力保存
    public boolean isPalindrome(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        int l = 0;
        int r = list.size() - 1;
        while (l < r) {
            if (list.get(l++).val != list.get(r--).val) {
                return false;
            }
        }
        return true;
    }

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
}
