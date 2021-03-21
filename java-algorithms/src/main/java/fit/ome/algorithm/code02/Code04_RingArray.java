package fit.ome.algorithm.code02;

/**
 * 使用环形数组，实现队列和栈
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/20
 **/
public class Code04_RingArray {
    public static class RingArray {
        int[] arr;

        // 当前可访问的元素标识
        int head;
        // 当前待放入元素的最后位置
        int tail;
        // 整个环形数组容量
        private final int capacity;
        // 已经放入的元素的长度
        private int size;


        public RingArray(int capacity) {
            arr = new int[capacity];
            this.capacity = capacity;
            this.size = 0;
            this.head = 0;
            this.tail = 0;
        }

        public void push(int v) {
            if (capacity == size) {
                throw new RuntimeException("容量已经达到上限");
            }
            size++;
            arr[tail] = v;
            tail = nextIndex(tail);

        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("当前无元素");
            }
            size--;
            int t = arr[head];
            head = nextIndex(head);
            return t;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private int nextIndex(int tail) {
            return tail < capacity - 1 ? tail : 0;
        }

    }
}
