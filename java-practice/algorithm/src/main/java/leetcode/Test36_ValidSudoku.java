package leetcode;

import java.util.*;

/**
 * Description: <a href="https://leetcode.com/problems/valid-sudoku/">leetcode link</a>
 *
 * @author youyou
 * @date 10/4/22 8:35 AM
 */
public class Test36_ValidSudoku {
    public static void main(String[] args) {
        char[][] board = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        Solution solution = new Solution();
        System.out.println(solution.isValidSudoku(board));

        board = new char[][]{{'8', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        System.out.println(solution.isValidSudoku(board));

        board = new char[][]{
                  {'.', '.', '.', '.', '5', '.', '.', '1', '.'}
                , {'.', '4', '.', '3', '.', '.', '.', '.', '.'}
                , {'.', '.', '.', '.', '.', '3', '.', '.', '1'}
                , {'8', '.', '.', '.', '.', '.', '.', '2', '.'}
                , {'.', '.', '2', '.', '7', '.', '.', '.', '.'}
                , {'.', '1', '5', '.', '.', '.', '.', '.', '.'}
                , {'.', '.', '.', '.', '.', '2', '.', '.', '.'}
                , {'.', '2', '.', '9', '.', '.', '.', '.', '.'}
                , {'.', '.', '4', '.', '.', '.', '.', '.', '.'}};
        System.out.println(solution.isValidSudoku(board));
    }

    private static class Solution {
        /**
         * 3 parts (rows, columns, and 3 * 3 blocks) check separately
         *
         * 1. checking rows needs a 2 nested loop.
         *   1.1 first loop represents rows.
         *   1.2 second loop represents columns.
         * 2. checking columns needs 2 nested loop too, same like step 1.
         * 3. checking sub-boxes needs 3 nested loop
         *   3.1 first loop to iterate each sub-box.
         *   3.2 second loop for every line in a sub-box.
         *   3.3 the third loop for every colum in a line.
         * */
        public boolean isValidSudoku(char[][] board) {
            for (int i = 0; i < board.length; i++) {
                Set<Character> row = new HashSet<>();
                Set<Character> column = new HashSet<>();
                for (int index = 0; index < board.length; index++) {
                    if (board[i][index] != '.' && row.contains(board[i][index])) return false;
                    else row.add(board[i][index]);

                    if (board[index][i] != '.' && column.contains(board[index][i])) return false;
                    column.add(board[index][i]);
                }


                if ((i + 1) % 3 == 0) {
                    List<Set<Character>> blocks = Arrays.asList(new HashSet<>(), new HashSet<>(), new HashSet<>());
                    for (int rowIndex = i; rowIndex > i - 3; rowIndex--) {
                        for (int columnIndex = 0; columnIndex < board.length; columnIndex++) {
                            if (board[rowIndex][columnIndex] != '.' && blocks.get(columnIndex / 3).contains(board[rowIndex][columnIndex]))
                                return false;
                            blocks.get(columnIndex / 3).add(board[rowIndex][columnIndex]);
                        }
                    }
                }
            }
            return true;
        }
    }
}
