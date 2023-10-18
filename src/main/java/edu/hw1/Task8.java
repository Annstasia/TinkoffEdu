package edu.hw1;

public class Task8 {
    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        final int boardX = 8;
        final int boardY = 8;
        final int possibleMovesNumber = 8;
        final int[][] DELTA = new int[][] {
            {1, 2},
            {1, -2},
            {-1, 2},
            {-1, -2},
            {2, 1},
            {2, -1},
            {-2, 1},
            {-2, -1}
        };
        for (int i = 0; i < boardX; ++i) {
            for (int j = 0; j < boardY; ++j) {
                if (board[i][j] == 1) {
                    for (int k = 0; k < possibleMovesNumber; ++k) {
                        if (i + DELTA[k][0] < boardX && i + DELTA[k][0] >= 0
                            && j + DELTA[k][1] < boardY
                            && j + DELTA[k][1] >= 0
                            && board[i + DELTA[k][0]][j + DELTA[k][1]] == 1
                        ) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
