package fit.ome.algorithm.code08;

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
}
