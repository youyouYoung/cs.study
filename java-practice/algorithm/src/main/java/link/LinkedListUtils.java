package link;

/**
 * 功能描述: 链表结构工具类
 *
 * @author youyou
 * @date 12/2/19 10:05 PM
 */
public class LinkedListUtils {

    /**
     * 功能描述: 找到链表倒数第K个结点
     *
     * @param head 头结点
     * @param k 位置
     * @return 倒数第K个节点
     * @author youyou
     * @date 12/2/19 10:09 PM
     */
    public static LinkedNode<Integer> findKthToTail(LinkedNode<Integer> head, int k) {
        if (head == null || k <= 0) {
            throw new RuntimeException("参数异常");
        }

        LinkedNode<Integer> firstPointer = head;
        LinkedNode<Integer> secondPointer = head;
        for (int i = k - 1; i > 0; i--) {
            if (firstPointer == null) {
                throw new RuntimeException("K超过范围");
            }

            firstPointer = firstPointer.getNext();
        }

        while (firstPointer.getNext() != null) {
            firstPointer = firstPointer.getNext();
            secondPointer = secondPointer.getNext();
        }
        return secondPointer;
    }
}
