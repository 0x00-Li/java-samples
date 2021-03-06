package fit.ome.algorithm.code15;
/**
 * 并查集
 *  通过查找相关的数据，并进行合并（查找代表节点）
 *  如果不相关，产生新的集合，如果相关，进行集合的合并
 *
 *  实现：
 *  假定所有集合具有独立的集合属性无法合并，设定每个几点代表节点是自己，每个集合的大小都为1
 *  两个节点进行判断是否可以合并，
 *      通过记录父节点信息
 *      在每次对两个节点进行判断的时候，分别查找代表节点，然后判断是否可以合并为同一个集合
 *  遇到一个可以合并的节点，然后总集合数减 1
 *
 */