package fit.ome.algorithm.code06;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 底层是 AVL(Adelson-Velsky-Landis Tree) 完全二叉树 实现的
 * <p>
 * 在数组中，构建avl树，给定一个节点i，
 * 1. 做孩子位置 2*i+1
 * 2. 右孩子位置 2*i+2
 * 3. 父节点 (i-1)/2
 * <br>
 * 默认实现，构建大根堆
 */
public class Heap {
    /**
     * 自己实现 的 大根堆
     */
    public static class MaxHeap {
        int[] heap;
        final int limit;
        int heapSize;

        public MaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            this.heapSize = 0;
        }

        public boolean isFull() {
            return heapSize == limit;

        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public void push(int v) {
            if (heapSize == limit) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = v;
            heapInsert(heap, heapSize++);
        }

        /**
         * 堆中指定位置增加了新元素，需要向上调整（目标是大根堆）
         *
         * @param arr
         * @param index 新插入元素的位置
         */
        public void heapInsert(int[] arr, int index) {
            // 元素需要和自己的父级元素进行大小比较
            //
            // ---
            // 隐藏两个条件
            // 索引位来说，index==0 的时候 (0-1)/2==0 ,所以不需要讨论(index)<0的情况
            // 值上对比来说，子节点大于父节点
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }

        }


        private void swap(int[] arr, int i, int j) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;

        }

        /**
         * 弹出堆顶元素
         * <p>
         * 直接将堆顶元素和最后一个元素交换，然后缩小堆大小
         *
         * @return
         */
        public int pop() {
            int ans = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;
        }

        /**
         * 调整堆：出现在值被移除或者替换掉的场景，总体目标是大根堆向下调整
         *
         * @param arr   堆数组
         * @param index 新加入堆的元素位置
         * @param size  当前堆大小
         */
        public void heapify(int[] arr, int index, int size) {
            // 左孩子索引位置
            int left = (index << 1) + 1;
            // 如果有左孩子，然后进行讨论;
            // 因为左孩子比右孩子索引位靠前，对左孩子进行优先讨论
            while (left < size) {
                // 有右孩子，然后讨论两个孩子啊大小
                int maxIndex = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
                // 获取孩子中，大值孩子索引位，然后比较大值孩子和当前节点的大小
                maxIndex = arr[maxIndex] > arr[index] ? maxIndex : index;
                // 说明所有的孩子都没当前节点大，不用调整
                if (maxIndex == index) {
                    break;
                }
                // 交换数据，继续进行堆调整
                swap(arr, maxIndex, index);
                index = maxIndex;
                left = (index << 1) + 1;

            }


        }
    }

    /**
     * 使用系统提供的大根堆，进行验证
     */
    public static class RightMaxHeap {
        int[] heap;
        final int limit;
        int size;

        public RightMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isFull() {
            return limit == size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void push(int v) {
            if (limit == size) {
                throw new RuntimeException(" heap is full");
            }
            heap[size++] = v;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (heap[i] > heap[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = heap[maxIndex];
            heap[maxIndex] = heap[--size];
            return ans;
        }
    }

    public static class MyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
//            return o1 > o2 ? -1 : 1;
            return o2 - o1;
        }
    }

    public static void main(String[] args) {
        // 系统堆演示
        // -------------
        // 默认小跟堆，使用比较器，调整为大根堆
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MyComparator());
//        maxHeap.add(5);
//        maxHeap.add(3);
//        maxHeap.add(4);
//        maxHeap.add(6);
//        System.out.println(maxHeap.peek());//6
//        maxHeap.add(7);
//        maxHeap.add(1);
//        maxHeap.add(3);
//        maxHeap.add(45);
//        maxHeap.add(0);
//        System.out.println(maxHeap.peek());// 45
//
//        while (!maxHeap.isEmpty()){
//            System.out.print(maxHeap.poll());
//            System.out.print(",");
//        }

        int value = 1000;// 数值最大值
        int limit = 100;// 堆容量
        int testTimes = 100000;// 测试次数
        System.out.println("start");
        for (int i = 0; i < testTimes; i++) {
            // 当前次的堆容量
            int currLimit = (int) (Math.random() * limit) + 1;
            MaxHeap maxHeap = new MaxHeap(currLimit);
            RightMaxHeap rightHeap = new RightMaxHeap(currLimit);
            // 测试操作次数
            int curOpTimes = (int) (Math.random() * currLimit) + 1;
            for (int j = 0; j < curOpTimes; j++) {
                if (maxHeap.isEmpty() != rightHeap.isEmpty()) {
                    System.out.println("empty Oops!!!");
                    break;
                }
                if (maxHeap.isFull() != rightHeap.isFull()) {
                    System.out.println("full Oops!!!");
                    break;
                }

                if (maxHeap.isEmpty()) {
                    int curV = (int) (Math.random() * value) + 1;
                    maxHeap.push(curV);
                    rightHeap.push(curV);
                } else if (maxHeap.isFull()) {
                    if (maxHeap.pop() != rightHeap.pop()) {
                        System.out.println("full pop Oops!!!!");
                        break;
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curV = (int) (Math.random() * value);
                        maxHeap.push(curV);
                        rightHeap.push(curV);
                    } else {
                        int pop = maxHeap.pop();
                        int pop1 = rightHeap.pop();
                        if (pop != pop1) {
                            System.out.println("else pop Oops!!!!");

                            break;
                        }
                    }
                }
            }
        }
        System.out.println("finish");

    }
}
