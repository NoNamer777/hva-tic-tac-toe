package models;

public class TicTacToe {
    private final static int GRID_SIZE = 3;
    private char turn = 'X';
    private int movesLeft = 9;
    private char winner;

    public Tile[][] grid = new Tile[GRID_SIZE][GRID_SIZE];

    public TicTacToe() {
        // fill grid with tiles
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = new Tile();
            }
        }

        drawGrid();
    }

    public void drawGrid() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                builder.append(grid[i][j].getState());
                builder.append((j == 2) ? "\n" : " | ");
            }
        }

        System.out.println(builder.toString());
    }

    public boolean changeTile(int i, int j) {
        boolean changed = grid[i][j].changeState(turn);
        if (changed) {
            drawGrid();

            if (!isGameOver()) {
                turn = (turn == 'X') ? 'O' : 'X';
                movesLeft--;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean isGameOver() {
        // check rows and cols for win
        for (int i = 0; i < 3; i++) {
            if (grid[i][0].getState() == turn && grid[i][1].getState() == turn && grid[i][2].getState() == turn) {
                winner = turn;
                return true;
            } else if (grid[0][i].getState() == turn && grid[1][i].getState() == turn && grid[2][i].getState() == turn) {
                winner = turn;
                return true;
            }
        }

        // check diagonals for win
        if ((grid[0][0].getState() == turn && grid[1][1].getState() == turn && grid[2][2].getState() == turn) ||
                (grid[0][2].getState() == turn && grid[1][1].getState() == turn && grid[2][0].getState() == turn)) {
            winner = turn;
            return true;
        }

        if (movesLeft == 0) {
            winner = 'D';
            return true;
        }

        return false;
    }

    public char getTurn() {
        return turn;
    }

    public char getWinner() { return winner; }
}
