package fit.ome.algorithm.code02;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列结构实现栈结构
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/20
 **/
public class Code07_TwoQueueImplStack {
    public static class MyStack {
        private Queue<Integer> dataQueue;
        private Queue<Integer> backQueue;

        private MyStack() {
            dataQueue = new LinkedList<>();
            backQueue = new LinkedList<>();
        }

        public void push(int v) {
            dataQueue.offer(v);
        }

        public int pop() {
            if (dataQueue.isEmpty()) {
                throw new RuntimeException("queue is empty");
            }
            return exchangeQueue();
        }

        public int exchangeQueue() {
            while (dataQueue.size() > 1) {
                backQueue.offer(dataQueue.poll());
            }
            Integer poll = dataQueue.poll();
            dataQueue = backQueue;
            backQueue = new LinkedList<>();
            return poll;

        }

        public int peek() {
            while (dataQueue.size() > 1) {
                backQueue.offer(dataQueue.poll());
            }
            Integer ans = dataQueue.poll();
            backQueue.offer(ans);

            Queue tmp = dataQueue;
            dataQueue = backQueue;
            backQueue = tmp;

            return ans;

        }
        public boolean isEmpty(){
            return dataQueue.isEmpty();
        }
    }
}
