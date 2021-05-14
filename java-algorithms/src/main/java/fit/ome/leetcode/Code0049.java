package fit.ome.leetcode;

import java.util.*;

/**
 * 字母异位词分组
 * <p>
 * https://leetcode-cn.com/problems/group-anagrams/
 */
public class Code0049 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> resMap = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char[] s = strs[i].toCharArray();
            Arrays.sort(s);
            String ss = new String(s);
            List<String> strings = resMap.get(ss);
            if(strings==null){
                strings=new ArrayList<>();
                resMap.put(ss,strings);
            }
            strings.add(strs[i]);
        }

        return new ArrayList<>(resMap.values());
    }

    public static void main(String[] args) {
        System.out.println(1 & 0);
    }
}
