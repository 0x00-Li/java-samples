package fit.ome.leetcode;

import java.util.Locale;

/**
 * 实现前缀树
 * Tire
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 */
public class Code0208 {

    public class Trie {
        Node root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new Node();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            if (word == null || word.length() == 0) {
                root.pathCount++;
                root.endCount++;
                return;
            }
            char[] chars = word.toCharArray();

            Node node = root;
            for (char c : chars) {
                int index = c - 'a';
                Node tNode = node.next[index];
                if (tNode == null) {
                    tNode = new Node();
                    node.next[index] = tNode;
                }
                tNode.pathCount++;
                node = tNode;
            }
            node.endCount++;
            node.pathCount--;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {

            if (word == null || word.length() == 0) {
                return true;
            }
            char[] chars = word.toCharArray();

            Node node = root;
            for (char c : chars) {
                node = node.next[c - 'a'];
                if (node == null) {
                    return false;
                }
            }
            return node.endCount > 0;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            if (prefix == null || prefix.length() == 0) {
                return true;
            }
            char[] chars = prefix.toCharArray();

            Node node = root;
            for (char c : chars) {
                node = node.next[c - 'a'];
                if (node == null) {
                    return false;
                }
            }
            return node.pathCount > 0||node.endCount>0;
        }
    }

    public static class Node {
        int pathCount;
        int endCount;
        Node[] next = new Node[26];
    }
}
