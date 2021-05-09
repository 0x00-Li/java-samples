package fit.ome.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电话号码的字母组合
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/9
 **/
public class Code0017 {
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        char[] numChar = digits.toCharArray();
        List<String> res = new ArrayList<>();
        process(numChar, 0, "", res);
        return res;
    }

    public void process(char[] numChar, int i, String path, List<String> res) {
        if (i == numChar.length) {
            res.add(path);
            return;
        }
        char[] chars = num2char(numChar[i]);
        for (char c : chars) {
            process(numChar, i + 1, path + c, res);
        }
    }

    private char[] num2char(char num) {
        int base = num + 2 * (num - 50);
        if (num == '7') {
            return new char[]{(char) (base + 47), (char) (base + 48), (char) (base + 49), (char) (base + 50)};
        } else if (num == '8') {
            return new char[]{(char) (base + 48), (char) (base + 49), (char) (base + 50)};
        } else if (num == '9') {
            return new char[]{(char) (base + 48), (char) (base + 49), (char) (base + 50), (char) (base + 51),};
        } else {
            return new char[]{(char) (base + 47), (char) (base + 48), (char) (base + 49)};
        }
    }

// backtrack

    public List<String> letterCombinations1(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        Map<Character, String> phoneMap = new HashMap<>();
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");

        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        backtrack(digits, phoneMap, 0, path, res);
        return res;
    }

    public void backtrack(String digits, Map<Character, String> phoneMap, int i, StringBuilder path, List<String> res) {
        if (i == digits.length()) {
            res.add(path.toString());
        } else {
            char digit = digits.charAt(i);
            String nums = phoneMap.get(digit);
            for (int j = 0; j < nums.length(); j++) {
                path.append(nums.charAt(j));
                backtrack(digits, phoneMap, i + 1, path, res);
                path.deleteCharAt(i);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Code0017().letterCombinations1("23"));
//        String s = "23456789";
//        for (int i = 0; i < s.length(); i++) {
//            System.out.println(new Code0017().num2char(s.toCharArray()[i]));
//        }
    }
}
