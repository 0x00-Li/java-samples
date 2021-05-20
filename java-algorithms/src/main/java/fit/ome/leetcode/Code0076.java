package fit.ome.leetcode;

/**
 * 最小覆盖子串
 * <p>
 * https://leetcode-cn.com/problems/minimum-window-substring/
 */
public class Code0076 {
    // 滑动窗口
    public String minWindow(String s, String t) {
        char[] str = s.toCharArray();
        char[] ts = t.toCharArray();
        if (ts.length > str.length) {
            return "";
        }
        int[] strCount = new int[58];// str的词频统计

        int[] tCount = new int[58];// t词频
        for (int i = 0; i < ts.length; i++) {
            tCount[ts[i] - 'A']++;
        }
        int slow = 0;
        int fast = slow;

        int start = -1;
        int end = -1;
        while (slow < str.length) {
            int index = 0;
            while (index < ts.length) {
                if (strCount[ts[index] - 'A'] >= tCount[ts[index] - 'A']) {
                    index++;
                } else {
                    if (fast < str.length) {
                        strCount[str[fast++] - 'A']++;
                    } else {
                        break;
                    }
                }
            }
            if (index == ts.length) {
                if (start == -1 || end == -1) {
                    start = slow;
                    end = fast;
                } else if (fast - slow < end - start) {
                    start = slow;
                    end = fast;
                }
            }
            strCount[str[slow++] - 'A']--;
        }
        return start == -1 || end == -1 ? "" : s.substring(start, end);
    }

    public static void main(String[] args) {
        System.out.println(new Code0076().minWindow("aa", "aa"));
    }
}
