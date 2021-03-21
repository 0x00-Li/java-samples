package fit.ome.algorithm.code02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 用栈结构实现队列结构
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/20
 **/
public class Code06_TwoStackImplQueue {
    public static class MyQueue {
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public MyQueue() {
            pushStack = new Stack<>();
            popStack = new Stack<>();
        }

        public void add(int v) {
            pushStack.push(v);
        }

        private void pushToPop() {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }

        public int pop() {
            if (pushStack.isEmpty() && popStack.isEmpty()) {
                throw new RuntimeException("queue is empty");
            }
            pushToPop();
            return popStack.pop();

        }

        public boolean isEmpty() {
            return pushStack.isEmpty() && popStack.isEmpty();
        }

        public int peek() {
            pushToPop();
            return popStack.peek();
        }
    }

}
