package leetcode;

import java.util.Arrays;

/**
 * Description: <a href="https://leetcode.com/problems/sudoku-solver/">leetcode url</a>
 *
 * @author youyou
 * @date 10/11/22 9:41 AM
 */
public class Test37_SudokuSolver {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        Solution solution = new Solution();
        solution.solveSudoku(board);
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
    }

    private static class Solution {
        public void solveSudoku(char[][] board) {
            exhaustion(board);
        }

        /**
         * There are 9 blocks in the board. every line or column has 3 blocks.
         * 1. Loop from 1 to 9, the current number is `num`.
         * 2. Search each block by line, the current line is `line`.
         * 3. If there are two `num` in this `line`. We know the line number of the last number's position.
         * 3.1 Find which block do not have `num`. The column of this block is from `start` to `end`.
         * 3.2 Search `num` in the column of the board to find there are two `num` in these columns.
         * 3.3 If there are, then we confirm the column position of the last `num`, and we get the position of `num`.
         * 3.3.1 save `num` in the board.
         * 3.3.2 break to step 2.
         * 3.4 If not, break to step 2.
         * 4. Do the same processes by searching columns first.
         */
        @Deprecated
        private void step1(char[][] board) {
            for (char num = '1'; num <= '9'; num++) {
                for (int line = 1; line <= 3; line++) {
                    // count of the num in each line
                    int countInLine = 0;
                    int[] positionOfNum = new int[2];
                    int columSumHasNum = 0;
                    for (int i = (line - 1) * 3; i < 3; i++) {
                        boolean hasNum = false;
                        for (int j = 0; j < board[i].length; j++) {
                            if (num == board[i][j]) {
                                hasNum = true;
                                countInLine++;
                                columSumHasNum += j / 3;
                                break;
                            }
                        }
                        // if this line don't have num
                        if (!hasNum) {
                            positionOfNum[0] = i;
                        }
                    }

                    // only 2 num in this line of blocks.
                    if (countInLine == 2) {
                        int start = (3 - columSumHasNum) * 3;
                        int countInColumn = 0;
                        for (int i = start; i < start + 3; i++) {
                            boolean hasNum = false;
                            for (char[] chars : board) {
                                if (num == chars[i]) {
                                    countInColumn++;
                                    hasNum = true;
                                    break;
                                }
                            }

                            if (!hasNum) {
                                positionOfNum[1] = i;
                            }
                        }

                        if (countInColumn == 2) {
                            System.out.println(Arrays.toString(positionOfNum));
                            board[positionOfNum[0]][positionOfNum[1]] = num;
                        }
                    }
                }
            }
        }

        /**
         * Proof by exhaustion - try every number (from 1 to 9) on empty positions.
         * 1. Loop all position in the board from left to right and from top to bottom.
         * 2. If the current element in the board is '.'.
         * 3. Set 1 to 9 in this position separately.
         * 4. Check if the number you set is valid for current board.
         *    4.1 If it is invalid, jump to step 3.
         *    4.2 If it is valid now, jump to step 1, to the next position.
         * 5. If we can not find any number which is suitable for current position, after loop from 1 to 9.
         * 6. It means the number in the last position is wrong, So return to last position and change next number.
         */
        private boolean exhaustion(char[][] board) {
            for (int line = 0; line < board.length; line++) {
                for (int column = 0; column < board[line].length; column++) {
                    if ('.' == board[line][column]) {
                        for (char num = '1'; num <= '9'; num++) {
                            if (isSatisfy(board, line, column, num)) {
                                board[line][column] = num;
                                if (!exhaustion(board)) {
                                    board[line][column] = '.';
                                }
                            }
                        }

                        if ('.' == board[line][column]) return false;
                    }
                }
            }

            return true;
        }

        private boolean isSatisfy(char[][] board, int line, int colum, char num) {
            for (int i = 0; i < board.length; i++) {
                if (num == board[line][i])
                    return false;
                if (num == board[i][colum])
                    return false;

                if (num == board[((line / 3) * 3) + (i / 3)][((colum / 3) * 3) + (i % 3)])
                    return false;
            }
            return true;
        }
    }
}
