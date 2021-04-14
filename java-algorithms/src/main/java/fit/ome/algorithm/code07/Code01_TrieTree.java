package fit.ome.algorithm.code07;

import java.util.HashMap;
import java.util.Map;

/**
 * Trie Tree
 * <p>
 * 习惯上成为前缀树 prefix Tree
 */
public class Code01_TrieTree {
    // ---- 第一种版本
    // 基于字符串实现的一种，26个英文字母
    public static class LetterNode {
        int pass;
        int end;

        LetterNode[] nexts;

        public LetterNode(int p, int e) {
            this.pass = p;
            end = e;
            /**
             * 只有26个英文字母，所以定义路线数只有26个
             */
            nexts = new LetterNode[26];
        }
    }

    /**
     * 字母前Tire Tree
     */
    public static class LetterTireTree {

        LetterNode root;

        public LetterTireTree() {
            root = new LetterNode(0, 0);
        }

        /**
         * 增加字符串
         *
         * @param word
         */
        public void insert(String word) {

        }

        /**
         * 删除字符串
         *
         * @param word
         */
        public void delete(String word) {

        }

        /**
         * 搜索word出现过几次
         *
         * @param word
         * @return
         */
        public int search(String word) {
            return 0;
        }

        /**
         * 搜索相同前缀的单词数
         *
         * @param word
         * @return
         */
        public int searchPrefix(String word) {
            return 0;
        }

    }
    // --------------------
    // 第二种实现方式
    // 和 1 的差别是支持任意字符

    public static class CharNode {
        /**
         * 经过当前节点的字符串数
         */
        int pass;
        /**
         * 以这个字符结尾的字符串的数量
         */
        int end;
        Map<Integer, CharNode> nextsMap;

        public CharNode() {
            pass = 0;
            end = 0;
            nextsMap = new HashMap<>();
        }
    }

    public static class CharTrieTree {
        CharNode root;

        public CharTrieTree() {
            root = new CharNode();
        }

        /**
         * 增加字符串
         *
         * @param word
         */
        public void insert(String word) {
            if (word == null) {
                return;

            }
            char[] s = word.toCharArray();
            CharNode node = root;
            node.pass++;
            for (int i = 0; i < s.length; i++) {
                CharNode curNode = node.nextsMap.get(s[i]);
                if (curNode == null) {
                    curNode = new CharNode();
                    node.nextsMap.put((int) s[i], curNode);
                }
                curNode.pass++;

            }
            node.end++;

        }

        /**
         * 删除字符串
         *
         * @param word
         */
        public void delete(String word) {
            if (search(word) == 0) {
                return;
            }
            char[] s = word.toCharArray();
            CharNode node = root;
            node.pass--;
            for (int i = 0; i < s.length; i++) {
                if (--node.nextsMap.get((int) s[i]).pass == 0) {
                    node.nextsMap.remove((int) s[i]);
                    return;
                }
                node = node.nextsMap.get((int) s[i]);
            }
            node.end--;
        }

        /**
         * 搜索word出现过几次
         * <p>
         * <p>
         * 需要一直匹配到字符串的最后一个字符，判断结尾字符出现的 次数
         *
         * @param word
         * @return
         */
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] s = word.toCharArray();
            CharNode node = root;
            int index = 0;
            for (int i = 0; i < s.length; i++) {
                index = s[i];
                node = root.nextsMap.get(index);
                if (node == null) {
                    return 0;
                }
            }
            return node.end;
        }

        /**
         * 搜索相同前缀的单词数
         *
         * @param word
         * @return
         */
        public int searchPrefix(String word) {
            if (word == null) {
                return 0;
            }
            char[] s = word.toCharArray();
            CharNode node = root;
            node.pass--;
            int index = 0;
            for (int i = 0; i < s.length; i++) {
                index = s[i];
                node = node.nextsMap.get(index);
                if (node == null) {
                    return 0;
                }

            }
            return node.pass;
        }

    }
}
