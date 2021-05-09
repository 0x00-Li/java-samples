package fit.ome.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 判断有效括号
 * <p>
 * 使用栈进行先进后出
 * 复杂度O(N)
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/10
 **/
public class Code0020 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character,Character> pairMap=new HashMap<>();
        pairMap.put('(',')');
        pairMap.put('{','}');
        pairMap.put('[',']');
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                if (pairMap.get(stack.peek())!=null&&c == pairMap.get(stack.peek())) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        System.out.println(new Code0020().isValid("([)]"));
        System.out.println((int)'(');
        System.out.println((int)')');
        System.out.println((int)'{');
        System.out.println((int)'}');
        System.out.println((int)'[');
        System.out.println((int)']');
        System.out.println((char)124);
        System.out.println((char)92);
    }
}
