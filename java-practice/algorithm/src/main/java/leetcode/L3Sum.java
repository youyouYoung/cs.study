package leetcode;

import java.util.*;

/**
 * Description: https://leetcode.com/problems/3sum/
 *
 * @author youyou
 * @date 3/5/21 1:05 PM
 */
public class L3Sum {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.threeSumBySort(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(solution.threeSumBySort(new int[]{0, 0, 0}));
    }

    private static class Solution {

        /**
         * Description: Find all unique triplets in the array which gives the sum of zero.
         *
         * Firstly, we choose one of the element of array named x and suppose x in one of triplets.
         * Then, we choose behind element of x one by one, named it y, and also suppose y is in the above triplet.
         * So, we just need check if there is a number z = 0-x-y in the array.
         * But in this way, the time will be O(N^3)
         *
         * Thus, we assign a map and the key is z, similarly, the value is the list which contains [x, y]...
         * Firstly, we choose one number as x and we use the behind choose number to match the map. if the map contain the number
         * we pop the value and add the nonexistent array into the final result.
         * if not, we just name the number as y. then calculate the number z. put this entry into map: {z: [[x, y], ...]}
         * in this way, the time will be O(N^2)
         *
         * Finally, the result is Time Limit Exceeded
         *
         * @param nums number array
         * @return all unique triplets
         * @author youyou
         * @date 3/5/21 1:06 PM
         */
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums == null || nums.length < 3) return result;

            List<Set<Integer>> filterList = new ArrayList<>();
            Map<Integer, List<List<Integer>>> restNumMap = new HashMap<>(nums.length << 2);
            for (int i = 0; i < nums.length; i++) {
                final int x = nums[i];

                for (int j = i + 1; j < nums.length; j++) {
                    final int y = nums[j];

                    if (restNumMap.containsKey(y)) {
                        List<List<Integer>> restNum = restNumMap.get(y);
                        appendList(y, result, restNum, filterList);
                        restNumMap.remove(y);
                    } else {
                        int z = 0 - x - y;
                        List<Integer> list = new ArrayList<Integer>() {{
                            add(x);
                            add(y);
                        }};

                        List<List<Integer>> restNum = restNumMap.containsKey(z) ? restNumMap.get(z) : new ArrayList<List<Integer>>();
                        if (!restNum.contains(list)) {
                            restNum.add(list);
                        }
                        restNumMap.put(z, restNum);
                    }
                }
                restNumMap.clear();
            }

            return result;
        }

        private void appendList(int y, List<List<Integer>> resultList, List<List<Integer>> restNum, List<Set<Integer>> filterList) {
            for (List<Integer> rest : restNum) {
                rest.add(y);
                Set<Integer> candidate = new HashSet<>(rest);
                if (!filterList.contains(candidate)) {
                    resultList.add(rest);
                    filterList.add(candidate);
                }
            }
        }

        /**
         * Description: another way to find all unique triplets in the array which gives the sum of zero.
         *
         * In order to filtering the repeat triplet, we sort the array from beginning.
         * Then, we choose one of number in array as number x. and the next one is y and final one is z.
         *
         * Next, we calculate the sum = x+y+z, if the sum>0, we move z to forward. and if sum<0, we move y to backward.
         * but if sum==0, this list [x, y, z] is a kind of result. and put it in to the final results.
         *
         * in this process, if we need to move y or z to the next value and at same time the next y or z just equals last one,
         * we just move y or z again and do not calculate sum. because there is not any mean for the same value because of repeat.
         *
         * @param nums number array
         * @return all unique triplets
         * @author youyou
         * @date 3/5/21 9:41 PM
         */
        public List<List<Integer>> threeSumBySort(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums == null || nums.length < 3) return result;

            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;

                int x = nums[i];
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    boolean moveLeft = false;
                    boolean moveRight = false;

                    int sum = x + nums[left] + nums[right];
                    if (sum == 0) {
                        List<Integer> triplet = new ArrayList<>();
                        triplet.add(x);
                        triplet.add(nums[left]);
                        triplet.add(nums[right]);
                        result.add(triplet);

                        moveLeft = true;
                        moveRight = true;
                    } else if (sum > 0) {
                        moveRight = true;
                    } else {
                        moveLeft = true;
                    }

                    while (moveLeft && left++ < nums.length - 1 && nums[left-1] == nums[left]);
                    while (moveRight && right-- > i && nums[right+1] == nums[right]);
                }
            }

            return result;
        }
    }
}
