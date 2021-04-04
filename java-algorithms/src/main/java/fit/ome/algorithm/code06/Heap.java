package fit.ome.algorithm.code06;

/**
 * 底层是 AVL(Adelson-Velsky-Landis Tree) 完全二叉树 实现的
 * <p>
 * 在数组中，构建avl树，给定一个节点i，
 * 1. 做孩子位置 2*i+1
 * 2. 右孩子位置 2*i+2
 * 3. 父节点 (i+1)/2 -1
 * <br>
 * 默认实现，构建大根堆
 */
public class Heap {
    /**
     * 堆中指定位置增加了新元素，请求调整
     *
     * @param arr
     * @param index 新插入元素的位置
     */
    public void heapInsert(int[] arr, int index) {

        // 元素需要和自己的父级元素进行大小比较
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }

    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

    }

    /**
     * 弹出堆顶元素
     *
     * @return
     */
    public int pop() {
        return 1;
    }

    /**
     * 调整堆
     *
     * @param arr   堆数组
     * @param index 新加入堆的元素位置
     * @param size  当前堆大小
     */
    public void heapify(int[] arr, int index, int size) {

    }
}
