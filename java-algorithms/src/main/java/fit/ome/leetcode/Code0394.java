package fit.ome.leetcode;

import java.util.*;

/**
 * 字符串解码
 * https://leetcode-cn.com/problems/decode-string/
 */
public class Code0394 {
    public String decodeString1(String s) {

        int[] lastIndex = new int[1];
        lastIndex[0] = -1;

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); ) {
            if (s.charAt(i) != '[' && !(s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                lastIndex[0] = i;
                res.append(s.charAt(i));
                i++;
            } else if (s.charAt(i) == '[') {
                lastIndex[0] = i;
                res.append(part(s, i + 1,  lastIndex));
                i = lastIndex[0];

            } else {
                i++;
            }

        }
        return res.toString();
    }


    // 递归每次处理一个方括号区间的字符串
    // i 位置肯定不是[
    // 前方开始的数字位
    public StringBuilder part(String s, int i, int[] lastIndex) {
        StringBuilder sb = new StringBuilder();
        for (int j = i; j < s.length(); ) {
            if (s.charAt(j) == '[') {
                sb.append(part(s, ++j, lastIndex));
                j = lastIndex[0];
                continue;
            } else if (s.charAt(j) == ']' && j > i) {
                int n=i-2;
                while (n>=0&&s.charAt(n)>='0'&&s.charAt(n)<='9') {
n--;
                }
                int repeat = Integer.parseInt(s.substring(n+1,i-1));
                String rs = new String(sb);
                for (int k = 1; k < repeat; k++) {
                    sb.append(rs);
                }
                lastIndex[0] = ++j;
                break;
            } else if (!(s.charAt(j)>='0'&&s.charAt(j)<='9')) {
                sb.append(s.charAt(j));
            }
            lastIndex[0] = ++j;
        }
        return sb;
    }

    public static void main(String[] args) {
//        System.out.println(new Code0394().decodeString1("3[a]2[bc]"));
        System.out.println(new Code0394().decodeString1("100[a]"));
//        System.out.println(new Code0394().decodeString1("3[a2[c]]"));
    }

    public String decodeString(String s) {
        StringBuilder ans = new StringBuilder(s);
        StringBuilder sb = new StringBuilder();
        Stack<Integer> lPos = new Stack<>();
        // 前指针，不回退
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                lPos.push(i);
            } else if (s.charAt(i) == ']') {
                Integer pop = lPos.pop();
                if (pop == i - 1) {
                    // 空匹配忽略
                } else {
                    char c = s.charAt(pop - 1);
                    for (int j = 0; j < Integer.parseInt(String.valueOf(c)); j++) {
                        sb.append(s.substring(pop + 1, i - 1));
                    }
                }

                if (lPos.isEmpty()) {
                    ans.append(sb);
                    sb.delete(0, sb.length() - 1);
                }
            }

        }

        return ans.toString();
    }
}
