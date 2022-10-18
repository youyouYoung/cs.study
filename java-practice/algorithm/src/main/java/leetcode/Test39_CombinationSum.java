package leetcode;

import java.util.*;

/**
 * Description: <a href="https://leetcode.com/problems/combination-sum/">leetcode problem 39</a>
 *
 * @author youyou
 * @date 10/17/22 9:43 AM
 */
public class Test39_CombinationSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = new int[] {2,3,6,7};
        int target = 7;
        System.out.println(solution.combinationSum(candidates, target));
        candidates = new int[] {2,3,5};
        target = 8;
        System.out.println(solution.combinationSum(candidates, target));
        candidates = new int[] {2};
        target = 1;
        System.out.println(solution.combinationSum(candidates, target));
    }

    private static class Solution {
        /**
         * candidates = [2,3,6,7], target = 7
         * 1. Find all possible results.
         * 2. Reduce all repeated results.
         *
         * How to find all possible results:
         *   1. Define a List<Integer> `result`.
         *   2. Loop the `candidates`, the current element is number.
         *   3. If `target - sum(result) < number`, then add `number` in results.
         *   4. Recursive step 2 and 3:
         *      1. Until `target - sum(result) = 0`, then the `result` is one of the solutions, add it.
         *      2. Or `target - sum(result) < min(candidates)`:
         *         1. Drop the result, it is fail.
         *         2. Remember `sum(result)` do not have a solution.
         *         3. Return false
         *
         * */
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Set<List<Integer>> result = new HashSet<>();
            Set<Integer> noAnswerSet = new HashSet<>();
            List<Integer> solution = new ArrayList<>();
            Arrays.sort(candidates);
            combinationSum(candidates, target, result, solution, noAnswerSet);
            return new ArrayList<>(result);
        }

        private void combinationSum(int[] candidates, int target, Set<List<Integer>> result, List<Integer> solution, Set<Integer> noAnswerSet) {
            int sum = solution.stream().mapToInt(Integer::intValue).sum();
            if (sum > target) return;
            if (noAnswerSet.contains(sum)) return;

            if (target - sum == 0) {
                List<Integer> list = new ArrayList<>(solution);
                list.sort(Integer::compareTo);
                result.add(list);
                return;
            } else if (target - sum < candidates[0]) {
                noAnswerSet.add(sum);
                return;
            }

            for (int candidate : candidates) {
                solution.add(candidate);
                combinationSum(candidates, target, result, solution, noAnswerSet);
                solution.remove(solution.size() - 1);
            }
        }
    }
}
