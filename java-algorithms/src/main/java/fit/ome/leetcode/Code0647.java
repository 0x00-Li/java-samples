package fit.ome.leetcode;

/**
 * 回文子串
 * <p>
 * https://leetcode-cn.com/problems/palindromic-substrings/
 * <p>
 * 统计有多少个回文子串
 * <p>
 * 改进manacher算法进行求解
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/12
 **/
public class Code0647 {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherStr(s.toCharArray());
        int C = -1;
        int R = -1;
        int max = 0;
        int[] pArr = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {

                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            // 半径长度中包含 分隔符，所以除以2
            max += pArr[i] >> 1;
        }
        return max;
    }

    public char[] manacherStr(char[] s) {
        char[] res = new char[(s.length << 1) + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : s[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Code0647().countSubstrings("aaa"));
    }


}
