package org.learning.lld.revision_practice.tic_tac;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class Player {
    private final String name;
    private final char symbol;
}

class Board {
    private final char[][] grid;
    private int movesCount;

    public Board() {
        grid = new char[3][3];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row=0;row<grid.length;row++) {
            for (int col=0;col<grid[0].length;col++) {
                grid[row][col] = '-';
            }
        }

        movesCount = 0;
    }

    public synchronized void makeMove(int row, int col, char symbol) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid.length || grid[row][col] != '-') {
            throw new IllegalArgumentException("Invalid move");
        }
        grid[row][col] = symbol;
        movesCount++;
    }

    public boolean isFull() {
        return movesCount == grid.length * grid.length;
    }

    public boolean hasWinner() {
        // check row
        for (int row = 0;row < 3; row++) {
            if (grid[row][0] != '-') {
                boolean res = true;
                for (int col = 1;col < grid.length;col++) {

                }

                return true;
            }
        }

        return false;
    }
}

class Game {

}

public class Main {
    public static void main(String[] args) {

    }
}
