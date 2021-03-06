package fit.ome.tpl;

/**
 * 并查集
 */
public class UnionFind {
    // parent[i] = k ： i的父亲是k
    private int[] parent;
    // size[i] = k ： 如果i是代表节点，size[i]才有意义，否则无意义
    // i所在的集合大小是多少
    private int[] size;
    // 辅助结构
    // 用于在查找代表节点的时候，进行回溯各个途径节点
    private int[] help;
    // 一共有多少个集合
    private int sets;

    /**
     * 代表有多个集合
     *
     * @param N
     */
    public UnionFind(int N) {
        parent = new int[N];
        size = new int[N];
        help = new int[N];
        sets = N;
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // 从i开始一直往上，往上到不能再往上，代表节点，返回
    // 这个过程要做路径压缩
    private int find(int i) {
        int hi = 0;
        while (i != parent[i]) {
            help[hi++] = i;
            i = parent[i];
        }
        for (hi--; hi >= 0; hi--) {
            parent[help[hi]] = i;
        }
        return i;
    }

    /**
     * 将两个元素进行关联
     * <p>
     * 关联后，属于同一个集合
     * 调用这个方法，说明，i和j是一个集合中的，需要统一下代表节点，更新一下非联通集合数
     *
     * @param i
     * @param j
     */
    public void union(int i, int j) {
        int f1 = find(i);
        int f2 = find(j);
        // 代表节点相等，说明已经在一个集合中，不进行下一步处理
        // 代表节点不相等，不一定是一个集合里面的
        if (f1 != f2) {
            if (size[f1] >= size[f2]) {
                size[f1] += size[f2];
                parent[f2] = f1;
            } else {
                size[f2] += size[f1];
                parent[f1] = f2;
            }
            sets--;
        }

    }

    /**
     * 集合数量
     *
     * @return
     */
    public int sets() {
        return sets;
    }
}

