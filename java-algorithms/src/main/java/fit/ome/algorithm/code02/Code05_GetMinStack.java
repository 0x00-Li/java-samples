package fit.ome.algorithm.code02;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 * <p>
 * 1）pop、push、getMin操作的时间复杂度都是 O(1)。
 * <p>
 * 2）设计的栈类型可以使用现成的栈结构。
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/20
 **/
public class Code05_GetMinStack {
    public static class MyStack1 {
        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;

        public MyStack1() {
            dataStack = new Stack<>();
            minStack = new Stack<>();

        }

        public void push(int v) {
            if (minStack.isEmpty()) {
                minStack.push(v);
            } else if (v < getMin()) {
                minStack.push(v);
            } else {
                Integer minNum = minStack.peek();
                minStack.push(minNum);
            }
            this.dataStack.push(v);
        }

        public int pop() {
            if (dataStack.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            minStack.pop();
            Integer pop = dataStack.pop();

            return pop;
        }

        public int getMin() {
            if (minStack.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return minStack.peek();
        }
    }

    public static class MyStack2 {
        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;

        public MyStack2() {
            this.dataStack = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(int v) {

            if (minStack.isEmpty()) {
                minStack.push(v);
            } else if (v <= getMin()) {
                minStack.push(v);
            }
            this.dataStack.push(v);
        }

        public int pop() {
            if (dataStack.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            Integer pop = dataStack.pop();
            if (pop.equals(getMin())) {
                minStack.pop();
            }
            return pop;
        }

        public int getMin() {
            if (minStack.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return minStack.peek();
        }
    }
}
