package leetcode;

import leetcode.RemoveNthNodeFromEndOfList.ListNode;
/**
 * Description: https://leetcode.com/problems/swap-nodes-in-pairs/
 *
 * @author youyou
 * @date 3/18/21 1:37 PM
 */
public class SwapNodesInPairs {

    public static void main(String[] args) {
        Solution solution = new Solution();
        RemoveNthNodeFromEndOfList.outputListNode(solution.swapPairs(
                new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))))
        ));

        RemoveNthNodeFromEndOfList.outputListNode(solution.swapPairs(
                null
        ));

        RemoveNthNodeFromEndOfList.outputListNode(solution.swapPairs(
                new ListNode(1)
        ));
    }

    private static class Solution {
        /**
         * Description: swap every two adjacent nodes and return its head.
         *
         * @param head the head of original list
         * @return the head of swapped list
         * @author youyou
         * @date 3/18/21 3:56 PM
         */
        public ListNode swapPairs(ListNode head) {
            if (head == null) return head;

            ListNode dummy = new ListNode(0, head);
            ListNode left = head;
            ListNode right = left.next;
            head = dummy;

            while (right != null) {
                head.next = right;
                left.next = right.next;
                right.next = left;

                ListNode temp = left;
                left = right;
                right = temp;

                if (right.next != null) {
                    right = right.next;
                    left = left.next;
                    head = head.next;
                } else break;

                right = right.next;
                left = left.next;
                head = head.next;
            }

            return dummy.next;
        }
    }
}
