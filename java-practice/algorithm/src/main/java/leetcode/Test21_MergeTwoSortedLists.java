package leetcode;

import leetcode.Test19_RemoveNthNodeFromEndOfList.ListNode;

/**
 * Description: <a href="https://leetcode.com/problems/merge-two-sorted-lists/">...</a>
 *
 * @author youyou
 * @date 3/18/21 10:43 AM
 */
public class Test21_MergeTwoSortedLists {

    public static void main(String[] args) {

        Solution solution = new Solution();
        Test19_RemoveNthNodeFromEndOfList.outputListNode(solution.mergeTwoLists(
                new ListNode(1, new ListNode(2, new ListNode(4))),
                new ListNode(1, new ListNode(3, new ListNode(4)))
        ));

        Test19_RemoveNthNodeFromEndOfList.outputListNode(solution.mergeTwoLists(
                null, null
        ));

        Test19_RemoveNthNodeFromEndOfList.outputListNode(solution.mergeTwoLists(
                null, new ListNode(0)
        ));
    }

    private static class Solution {
        /**
         * Description: merge two sorted lists.
         *
         * @param l1 list 1
         * @param l2 list 2
         * @return the head of merged list.
         * @author youyou
         * @date 3/18/21 1:34 PM
         */
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null && l2 == null) return null;

            ListNode dummy = new ListNode();
            ListNode head = dummy;

            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    head.next = l1;
                    l1 = l1.next;
                } else {
                    head.next = l2;
                    l2 = l2.next;
                }

                head = head.next;
            }

            if (l1 != null) head.next = l1;
            if (l2 != null) head.next = l2;
            return dummy.next;
        }
    }
}
