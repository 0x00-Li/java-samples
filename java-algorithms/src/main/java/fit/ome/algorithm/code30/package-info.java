package fit.ome.algorithm.code30;
/**
 * morris 遍历
 *
 * 产生morris序中有左子树的节点会出现两次
 *
 * 遍历定义：
 * 1. 如果没有左树，直接向优益 cur=cur.right
 *
 * 2. 如果有左子树，查找左子树的最右节点mostRight
 *
 *      a. 如果最右节点右指针指向null，设置mostRight.right=cur
 *
 *      b. 如果最右节点的右指针指向当前节点cur自身，设置 mostRight.right=null
 * 3. 循环遍历直到 cur==null
 *
 *        a
 *       / \
 *     b    c
 *    /\   /\
 *   d e  f g
 * a b e d b a c f c g
 *
 *           1
 *  *       / \
 *  *     2    3
 *  *    /\   /\
 *  *   4 5  6 7
 * 1 2 4 2 5 1 3 6 3 7
 */