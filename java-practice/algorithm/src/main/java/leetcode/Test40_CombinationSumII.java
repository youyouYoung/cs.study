package leetcode;

import java.util.*;

/**
 * Description: <a href="https://leetcode.com/problems/combination-sum-ii/">leetcode problem 40</a> todo it's hard for me locate recursive problem
 *
 * @author youyou
 * @date 10/28/22 9:34 AM
 */
public class Test40_CombinationSumII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println(solution.combinationSum2(candidates, target));

        candidates = new int[]{2, 5, 2, 1, 2};
        target = 5;
        System.out.println(solution.combinationSum2(candidates, target));

        candidates = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        target = 30;
        System.out.println(solution.combinationSum2(candidates, target));
    }

    private static class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            return way1(candidates, target);
        }

        /**
         * backtrack - avoiding duplicated combinations by sorting the candidates
         * */
        private List<List<Integer>> way1(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(candidates);
            backtracking(candidates, target, result, new LinkedList<>(), 0);
            return result;
        }

        /**
         * candidates = [2,5,2,1,2], target = 5
         * 2,
         * 2, 5  F (pop 5)
         * 2, 2,
         * 2, 2, 1  T (store result and pop 1)
         * 2, 2, 2  F (pop 2)
         * 2, 2  (at the end, pop 2)
         * 2, 1,
         * 2, 1, 2  T (repeat, drop it, and pop 2)
         * 2, 1  (at the end, pop 1)
         */
        private void backtracking(int[] candidates, int target, List<List<Integer>> result, LinkedList<Integer> comb, int cursor) {
            if (target <= 0) {
                if (target == 0)
                    result.add(new ArrayList<>(comb));
                return;
            }

            for (int current = cursor; current < candidates.length; current++) {
                if (current > cursor && candidates[current] == candidates[current - 1])
                    continue;

                comb.add(candidates[current]);
                backtracking(candidates, target - candidates[current], result, comb, current + 1);
                comb.removeLast();
            }
        }

        /**
         * backtrack - avoiding duplicated combinations by counter table.
         * */
        private List<List<Integer>> way2(int[] candidates, int target) {
            List<List<Integer>> results = new ArrayList<>();
            Map<Integer, int[]> counterTable = new TreeMap<>();
            for (int candidate : candidates) {
                if (counterTable.containsKey(candidate)) {
                    counterTable.get(candidate)[1] += 1;
                } else {
                    counterTable.put(candidate, new int[] {candidate, 1});
                }
            }

            backtracking(new LinkedList<>(), target, 0, new ArrayList<>(counterTable.values()), results);
            return results;
        }

        /**
         * 1. creat a variable to store the results.
         * 2. convert the candidates to counter table.
         * 3. invoke backtracking algorithm.
         * */
        private void backtracking(List<Integer> comb, int target, int cursor, List<int[]> counterTable, List<List<Integer>> results) {
            if (target <= 0) {
                if (target == 0) {
                    results.add(new ArrayList<>(comb));
                }
                return;
            }

            for (int current = cursor; current < counterTable.size(); current++) {
                int[] candidate = counterTable.get(current);
                if (candidate[1] <= 0) continue;

                candidate[1] -= 1;
                comb.add(candidate[0]);
                backtracking(comb, target - candidate[0], current, counterTable, results);

                comb.remove(comb.size() - 1);
                candidate[1] += 1;
            }
        }
    }
}
