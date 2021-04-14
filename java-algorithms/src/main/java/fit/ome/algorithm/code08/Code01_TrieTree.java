package fit.ome.algorithm.code08;

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
        // 用于前缀匹配
        int pass;
        // 用于精确的全字符匹配
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
            if (search(word) == 0) {
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
            if (word == null) {
                return 0;
            }
            char[] s = word.toCharArray();
            LetterNode node = root;
            int index = 0;// 字母在数组中对应的位置
            for (int i = 0; i < s.length; i++) {
                index = s[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
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
            LetterNode node = root;
            int index = 0;// 字母在数组中对应的位置
            for (int i = 0; i < s.length; i++) {
                index = s[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
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
            if (word==null) {
                return;
            }
            char[] s = word.toCharArray();
            CharNode node = root;
            node.pass++;
            for (int i = 0; i < s.length; i++) {
                int index = s[i];
                CharNode curNode = node.nextsMap.get(index);
                if (curNode == null) {
                    curNode = new CharNode();
                    node.nextsMap.put(index, curNode);
                }
                curNode.pass++;
                node=curNode;

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
                int index = s[i];
                if (--node.nextsMap.get(index).pass == 0) {
                    node.nextsMap.remove(index);
                    return;
                }
                node = node.nextsMap.get(index);
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
                node = node.nextsMap.get(index);
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


    // --------------------
    // 3、 暴力正确解
    //
    public static class Right {
        // 统计单词出现的次数
        Map<String, Integer> wordCount;

        public Right() {
            wordCount = new HashMap<>();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            if (wordCount.containsKey(word)) {
                wordCount.put(word, (wordCount.get(word) + 1));
            }else {
                wordCount.put(word,1);
            }
        }

        public void delete(String word) {
            if (word == null) {
                return;
            }
            if (wordCount.containsKey(word)) {
                if (wordCount.get(word).equals(1)) {
                    wordCount.remove(word);
                } else {
                    wordCount.put(word, (wordCount.get(word) + 1));
                }
            }
        }

        public int search(String word) {
            if (word == null || !wordCount.containsKey(word)) return 0;
            return wordCount.get(word);
        }

        public int searchPrefix(String word) {
            if (word == null)
                return 0;
            int ans = 0;
            for (Map.Entry<String, Integer> entry :
                    wordCount.entrySet()) {
                if (entry.getKey().startsWith(word)) {
                    ans += entry.getValue();
                }
            }
            return ans;
        }
    }

    //------------
    //for test
    public static String generateRandomStr(int strLen) {
        char[] str = new char[(int) (Math.random() * strLen + 1)];
        for (int i = 0; i < str.length; i++) {
            int v = (int) (Math.random() * 6);
            str[i] = (char) (v + 97);
        }
        return String.valueOf(str);
    }

    public static String[] generateStrArr(int arrLen, int strLen) {
        String[] arr = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = generateRandomStr(strLen);
        }
        return arr;
    }

    public static void main(String[] args) {
        int arrLen = 20;
        int strLen = 9;
        int opTimes = 1000;
        for (int i = 0; i < opTimes; i++) {
            String[] arr = generateStrArr(arrLen, strLen);
            LetterTireTree letter = new LetterTireTree();
            CharTrieTree charTrie = new CharTrieTree();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {

                double decide = Math.random();
                if (decide < 0.25) {
                    letter.insert(arr[j]);
                    charTrie.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    letter.delete(arr[j]);
                    charTrie.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int l = letter.search(arr[j]);
                    int c = charTrie.search(arr[j]);
                    int r = right.search(arr[j]);
                    if (l != c || c != r) {
                        System.out.println("Oops!!!");
                        break;
                    }
                } else {
                    int l = letter.searchPrefix(arr[j]);
                    int c = charTrie.searchPrefix(arr[j]);
                    int r = right.searchPrefix(arr[j]);
                    if (l != c || c != r) {
                        System.out.println("Oops!!!");
                        break;
                    }
                }
            }

        }
        System.out.printf("finish");
    }
}
