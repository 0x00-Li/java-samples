package fit.ome.leetcode_day;

import java.util.*;

/**
 * 逆波兰表达式求值
 * <p>
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 */
public class Code_0150 {
    public int evalRPN(String[] tokens) {
        Map<String, Integer> numMap = new HashMap<>();
        numMap.put("0", 0);
        numMap.put("1", 1);
        numMap.put("2", 2);
        numMap.put("3", 3);
        numMap.put("4", 4);
        numMap.put("5", 5);
        numMap.put("6", 6);
        numMap.put("7", 7);
        numMap.put("8", 8);
        numMap.put("9", 9);
        Set<String> op = new HashSet<>();
        op.add("+");
        op.add("-");
        op.add("*");
        op.add("/");
        Stack<String> stack = new Stack<>();
        int ans = numMap.get(tokens[0]);

        for (int i = 1; i < tokens.length; i++) {
            String s=tokens[i];
            if(numMap.containsKey(s)){
                stack.push(s);
            }else {
                while (!stack.isEmpty()){
                    if (s.equals("+")) {
                        ans += numMap.get(stack.pop());
                    } else if (s.equals("-")) {
                        ans -= numMap.get(stack.pop());
                    } else if (s.equals("*")) {
                        ans *= numMap.get(stack.pop());
                    } else if (s.equals("/")) {
                        ans /= numMap.get(stack.pop());
                    }
                }
            }
        }
//
//
//        for (int i = tokens.length - 1; i >= 0; i--) {
//            String s = tokens[i];
//            if (stack.isEmpty()) {
//                stack.push(s);
//            } else if (op.contains(s)) {
//                stack.push(s);
//            } else if (!numMap.containsKey(stack.peek())) {
//                stack.push(s);
//            } else {
//                stack.push(s);
//                ans += cal(stack, numMap, op);
//            }
//        }
        return ans;

    }

    public static void main(String[] args) {
        new Code_0150().evalRPN(new String[]{"2","1","+","3","*"});
    }

    private int cal(Stack<String> stack, Map<String, Integer> numMap, Set<String> op) {
        Stack<Integer> opNum = new Stack<>();

        while (!stack.isEmpty() && numMap.containsKey(stack.peek())) {
            opNum.add(numMap.get(stack.pop()));
        }
        String currOp = stack.pop();
        int ans = opNum.pop();

        while (!opNum.isEmpty()) {
            if (currOp.equals("+")) {
                ans += opNum.pop();
            } else if (currOp.equals("-")) {
                ans -= opNum.pop();
            } else if (currOp.equals("*")) {
                ans *= opNum.pop();
            } else if (currOp.equals("/")) {
                ans /= opNum.pop();
            }
        }
        if (!stack.isEmpty() && numMap.containsKey(stack.peek())) {
            stack.push(String.valueOf(ans));
            ans += cal(stack, numMap, op);
        }
        return ans;


    }
}
