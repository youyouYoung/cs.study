package link.driver;

import link.LinkedNode;

import static link.LinkedListUtils.findKthToTail;

/**
 * 功能描述: link包的驱动类
 *
 * @author youyou
 * @date 12/2/19 10:23 PM
 */
public class LinkDriver {

    public static void main(String[] args) {
        LinkedNode<Integer> head = createLinkedList(10);
        System.out.println(findKthToTail(head, 1));
        System.out.println(findKthToTail(head, 3));
        // System.out.println(findKthToTail(head, 13));

        head = createLinkedList(100);
        System.out.println(findKthToTail(head, 50));
        System.out.println(findKthToTail(head, 0));

    }

    private static LinkedNode<Integer> createLinkedList(int number) {
        LinkedNode<Integer> head = null;
        LinkedNode<Integer> node = null;
        for (int i = 0; i < number; i++) {
            if (node != null) {
                node.setNext(new LinkedNode<Integer>());
                node = node.getNext();
            } else {
                node = new LinkedNode<Integer>();
                head = node;
            }
            node.setValue(i);
        }

        return head;
    }
}
