package leetcode;

import leetcode.RemoveNthNodeFromEndOfList.ListNode;

/**
 * Description: https://leetcode.com/problems/merge-k-sorted-lists/
 *
 * @author youyou
 * @date 3/18/21 4:36 PM
 */
public class MergeKSortedLists {

    public static void main(String[] args) {
        Solution solution = new Solution();
        RemoveNthNodeFromEndOfList.outputListNode(solution.mergeKLists(new ListNode[]{
                new ListNode(1, new ListNode(4, new ListNode(5))),
                new ListNode(1, new ListNode(3, new ListNode(4))),
                new ListNode(2, new ListNode(6))
        }));
    }

    private static class Solution {
        /**
         * Description: merge all the linked-list into one sorted linked-list.
         *
         * First, get the smallest listNode of all the linked-list. and then link it to
         * the result linked-list at the same time remove it from the original list.
         *
         * do above step until all the linked-list is null.
         *
         * @param lists linked list
         * @return the merged result
         * @author youyou
         * @date 3/18/21 7:33 PM
         */
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) return null;
            if (lists.length == 1) return lists[0];

            ListNode dummy = new ListNode();
            ListNode head = dummy;
            int[] result;
            while ((result = smallestValueNode(lists))[1] > 1) {
                head.next = lists[result[0]];
                lists[result[0]] = lists[result[0]].next;
                head = head.next;
            }

            head.next = lists[result[0]];
            return dummy.next;
        }

        /**
         * Description: find the index of listNode which has the smallest value in the array.
         * and also get the number of nonnull listNode in the array.
         *
         * @param lists the list of listNode
         * @return the index and the number.
         * @author youyou
         * @date 3/18/21 7:31 PM
         */
        private int[] smallestValueNode(ListNode[] lists) {
            int index = 0;
            int number = lists.length;
            Integer smallestValue = null;

            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) {
                    number--;
                    continue;
                }

                if (smallestValue == null || smallestValue.compareTo(lists[i].val) > 0) {
                    smallestValue = lists[i].val;
                    index = i;
                }
            }

            return new int[]{index, number};
        }
    }
}
