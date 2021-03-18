package leetcode;

/**
 * Description: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * @author youyou
 * @date 3/12/21 10:30 AM
 */
public class RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        ListNode head2 = new ListNode(1);
        Solution solution = new Solution();
        outputListNode(solution.removeNthFromEnd(head, 2));
        outputListNode(solution.removeNthFromEnd(head2, 2));
        outputListNode(solution.removeNthFromEnd(head2, 1));
    }

    public static void outputListNode(ListNode head) {
        StringBuilder result = new StringBuilder();
        while (head != null) {
            result.append(head.val);
            result.append(" ");
            head = head.next;
        }

        System.out.println(result);
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    private static class Solution {
        /**
         * Description: remove the nth node from the end of the list and return its head.
         *
         * We use two pointers and a one-time loop from the head to the end. and we keep these two pointers
         * have constant distance. and the distance is n.
         *
         * @param head the head of list
         * @param n the number of nth.
         * @return the head of the list which removed the nth node
         * @author youyou
         * @date 3/12/21 4:02 PM
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null || n < 1) return head;

            int loopCount = 0;
            ListNode endNode = head;
            ListNode nthNode = loopCount == n - 1 ? head : null;
            ListNode lastNthNode = null;

            while (endNode.next != null) {
                endNode = endNode.next;
                if (nthNode != null) nthNode = nthNode.next;
                if (lastNthNode != null) lastNthNode = lastNthNode.next;

                loopCount++;
                if (loopCount == n) lastNthNode = head;
                if (loopCount == n - 1) nthNode = head;
            }

            if (nthNode == null) return head;
            else if (lastNthNode == null) return head.next;

            lastNthNode.next = nthNode.next;
            return head;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return val + "";
        }
    }
}
