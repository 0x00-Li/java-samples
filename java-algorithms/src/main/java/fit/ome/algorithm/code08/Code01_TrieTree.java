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

        public LetterNode() {
            this.pass = 0;
            end = 0;
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
            root = new LetterNode();
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
            LetterNode node = root;
            node.pass++;
            for (int i = 0; i < s.length; i++) {
                int index = s[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new LetterNode();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        /**
         * 删除字符串
         *
         * @param word
         */
        public void delete(String word) {
            if (word == null) {
                return;
            }
            char[] s = word.toCharArray();
            LetterNode node = root;
            int index = 0;
            node.pass--;
            for (int i = 0; i < s.length; i++) {
                index = s[i] - 'a';
                if (--node.nexts[index].pass == 0) {
                    node.nexts[index] = null;
                    return;
                }
                node = node.nexts[index];
            }
            node.end--;

        }

        /**
         * 搜索word出现过几次
         *
         * @param word
         * @return
         */
        public int search(String word) {
            if(word==null){
                return 0;
            }
            char[] s = word.toCharArray();
            LetterNode node=root;
            int index=0;// 字母在数组中对应的位置
            for (int i = 0; i < s.length; i++) {
                index=s[i]-'a';
                if(node.nexts[index]==null){
                    return 0;
                }
                node=node.nexts[index];
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
            if(word==null){
                return 0;
            }
            char[] s = word.toCharArray();
            LetterNode node=root;
            int index=0;// 字母在数组中对应的位置
            for (int i = 0; i < s.length; i++) {
                index=s[i]-'a';
                if(node.nexts[index]==null){
                    return 0;
                }
                node=node.nexts[index];
            }
            return node.pass;
        }

    }
}
