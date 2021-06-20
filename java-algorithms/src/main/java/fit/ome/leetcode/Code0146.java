package fit.ome.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 实现LRU缓存
 * https://leetcode-cn.com/problems/lru-cache/
 */
public class Code0146 {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.get(1);
        lruCache.put(3,3);
        lruCache.get(2);
        lruCache.put(4,4);
        lruCache.get(1);
        lruCache.get(3);
        lruCache.get(4);

    }
    public static class LRUCache {
        Map<Integer, ListNode> cacheKV = new HashMap();
        int capacity = 0;
        int size = 0;
        static int MAX_CAPACITY = 3000;
        ListNode head;
        ListNode tail;

        public LRUCache(int capacity) {
            this.capacity = Math.min(capacity, MAX_CAPACITY);
        }

        public int get(int key) {
            ListNode listNode = cacheKV.get(key);
            if (listNode == null) {
                return -1;
            }
            moveToHead(listNode);
            return listNode.v;
        }

        public void put(int key, int value) {
            ListNode listNode = cacheKV.get(key);

            // 已经缓存在当前key
            if (listNode != null) {
                listNode.v = value;
                moveToHead(listNode);
            } else {
                //新创建缓存项
                if (size < capacity) {
                    size++;
                } else {
                    // 需要先移除尾结点
                    if (tail != null) {
                        cacheKV.remove(tail.k);
                        tail = tail.pre;
                    }
                    if (tail != null)
                        tail.next = null;
                }
                //  添加到头节点
                ListNode node = new ListNode(key, value);
                cacheKV.put(key, node);
                node.next = head;
                if (head != null) {
                    head.pre = node;
                }
                head = node;

                if (tail == null) {
                    tail = node;
                }
            }
        }

        private void moveToHead(ListNode listNode) {
            if (listNode == head) {
                return;
            }
            if (listNode == tail) {

                ListNode tHead = tail;
                tail = tail.pre;
                tail.next = null;

                tHead.next = head;
                tHead.pre = null;

                head.pre=tHead;
                head = tHead;
                return;
            }

            ListNode pre = listNode.pre;
            ListNode next = listNode.next;
            pre.next = next;
            next.pre = pre;

            listNode.pre = null;
            listNode.next = head;
            head.pre = listNode;

            head = listNode;

        }

        public static class ListNode {
            int k;
            int v;
            ListNode next;
            ListNode pre;

            public ListNode(int key, int val) {
                k = key;
                v = val;
            }
        }
    }


}
