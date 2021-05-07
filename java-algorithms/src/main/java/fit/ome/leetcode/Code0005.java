package fit.ome.leetcode;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 */
public class Code0005 {


    public static void main(String[] args) {
        System.out.println(new Code0005().longestPalindrome("babad"));
    }

    // 暴力解决,迭代实现
    public String longestPalindrome1(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        char[] chars = s.toCharArray();
        int[] res = new int[]{0, 0};
        for (int i = 0; i < chars.length; i++) {
            int[] res1 = process(chars, i);
            res = (res[1] - res[0]) > (res1[1] - res1[0]) ? res : res1;
        }
        return s.substring(res[0], res[1] + 1);

    }

    public int[] process(char[] arr, int currMid) {
        if (currMid == 0) {
            return new int[]{0, 0};
        }
        if (currMid == (arr.length - 1)) {
            if (arr[currMid] == arr[currMid - 1]) {
                return new int[]{currMid - 1, currMid};
            }
        }
        int start = currMid;
        int end = currMid;
        // 偶数长度
        // 只往前对比相等
        if (arr[currMid] == arr[currMid - 1]) {
            start = currMid - 1;
        }
        while (start > 0 && end < (arr.length - 1)) {
            if (arr[start - 1] != arr[end + 1]) {
                break;
            }
            start--;
            end++;
        }
        int[] res1 = new int[]{start, end};
        // 奇数长度
        start = currMid;
        end = currMid;
        while (start > 0 && end < (arr.length - 1)) {
            if (arr[start - 1] != arr[end + 1]) {
                break;
            }
            start--;
            end++;
        }
        int[] res2 = new int[]{start, end};
        return (res2[1] - res2[0]) > (res1[1] - res1[0]) ? res2 : res1;

    }
//// 暴力解决，递归实现
//public String longestPalindrome2(String s) {
//    if (s == null || s.length() == 0) {
//        return s;
//    }
//    char[] chars = s.toCharArray();
//
//    return null;
//}


    // 动态规划优化
    // 回文字符串0 到 N 的长度
    // 回文的左侧边界的尝试范围是从 0 到 N
    // 根据左边界和回文长度可以确定右侧边界
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int len = s.length();
        boolean[][] res = new boolean[len][len];
        char[] chars = s.toCharArray();
        // 单字符串都是回文
        for (int i = 0; i < chars.length; i++) {
            res[i][i] = true;
        }
        int maxLen = 1;// 最大回文长度
        int begin = 0;// 回文起始长度

        // 尝试不同长度的回文
        for (int length = 2; length <= len; length++) {

            // 左侧起始位置
            for (int L = 0; L < len; L++) {
                int R = L + length - 1;
                if (R >= len) {
                    break;
                }
                if (chars[L] == chars[R]) {
                    if (R - L < 3) {

                        res[L][R] = true;
                    } else {
                        res[L][R] = res[L + 1][R - 1];
                    }

                } else {

                    res[L][R] = false;
                }
                if (res[L][R] && (R - L + 1) > maxLen) {
                    maxLen = R - L + 1;
                    begin = L;
                }
            }
        }


        return s.substring(begin, begin + maxLen);

    }
}
