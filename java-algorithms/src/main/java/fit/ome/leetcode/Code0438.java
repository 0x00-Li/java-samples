package fit.ome.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 找到字符串中所有字母的异位词
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 */
public class Code0438 {
    public List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0 || s.length() < p.length()) {
            return ans;
        }
        int[] sCount = new int[26];//词频统计
        int[] pCount = new int[26];// 词频统计

        int l = 0;
        int r = 0;
        for (r = 0; r < p.length(); r++) {
            sCount[s.charAt(r) - 'a']++;
            pCount[p.charAt(r) - 'a']++;
        }
        int index = 0;

        while (r < s.length()) {
            for (index = 0; index < 26; index++) {
                if (sCount[index] != pCount[index]) {
                    break;
                }
            }
            if (index == 26) {
                ans.add(l);
            }
            sCount[s.charAt(l++) - 'a']--;
            sCount[s.charAt(r++) - 'a']++;
        }
        // 处理最后一位
        for (index = 0; index < 26; index++) {
            if (sCount[index] != pCount[index]) {
                break;
            }
        }
        if (index == 26) {
            ans.add(l);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Code0438().findAnagrams("abab", "ab"));
    }
}
