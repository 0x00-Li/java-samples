package fit.ome.leetcode;

import java.util.Stack;

/**
 * 最小栈
 * https://leetcode-cn.com/problems/min-stack/
 */
public class Code0155 {
    static class MinStack {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> min = new Stack<>();
        Stack<Integer> tmp=new Stack<>();
        public MinStack() {
        }

        public void push(int val) {
            if (stack.isEmpty()||val <= min.peek()) {
                min.push(val);
            }
            stack.push(val);
        }

        public void pop() {
            Integer pop = stack.pop();
            if (pop.equals( min.peek())) {
                min.pop();
                if(min.isEmpty()){
                    while (!stack.isEmpty()){
                        tmp.push(stack.pop());
                    }
                    while (!tmp.isEmpty()){
                        if(stack.isEmpty()||min.isEmpty()||tmp.peek()<=min.peek()){
                            min.push(tmp.peek());
                        }
                        stack.push(tmp.pop());
                    }
                }
            }
        }
        public int top() {
            return stack.peek();
        }
        public int getMin() {
            return min.peek();
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);

        minStack.pop();
        minStack.getMin()
                ;
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
    }
}
