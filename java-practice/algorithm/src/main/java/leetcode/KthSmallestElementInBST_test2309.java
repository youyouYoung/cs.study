package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Description: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * date 18/04/2022 17:10
 *
 * @author yangguang
 */
public class KthSmallestElementInBST_test2309 {
    private class Solution {
        public int kthSmallest(TreeNode root, int k) {
            List<TreeNode> sortedTreeNode = new ArrayList<>();
            sortTreeRecursively(root, sortedTreeNode, k);
            return sortedTreeNode.get(k - 1).val;
        }

        /**
         * description: sort the tree firstly, and get the kth element in list
         * date 17:29 2022/4/18
         **/
        private void sortTreeRecursively(TreeNode root, List<TreeNode> sortedTreeNode, int k) {
            if (Objects.isNull(root)) return;
            if (sortedTreeNode.size() >= k) return;

            sortTreeRecursively(root.left, sortedTreeNode, k);
            sortedTreeNode.add(root);
            sortTreeRecursively(root.right, sortedTreeNode, k);
        }
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}




