import edu.princeton.cs.algs4.Stack;

public class Board {


    private final int size;
    private final int[][] blocks;

    private int manhattan = -1;

    public Board(int[][] blocks) {
        size = blocks.length;
        this.blocks = new int[size][size];
        copy(blocks, this.blocks, size);
    }

    private void copy(int[][] input, int[][] output, int length) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                output[i][j] = input[i][j];
            }
        }
    }

    public int dimension() {
        return size;
    }

    public int hamming() {
        int result = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (blocks[i][j] != 0 && blocks[i][j] != goalValue(i, j)) {
                    result++;
                }
            }
        }
        return result;
    }

    public int manhattan() {
        if (manhattan != -1) {
            return manhattan;
        }

        manhattan = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (blocks[i][j] != 0) {
                    manhattan += manhattan(blocks[i][j], i, j);
                }
            }
        }
        return manhattan;
    }

    private int manhattan(int value, int i, int j) {
        int targetI = (value - 1) / 3;
        int targetJ = (value - 1) % 3;
        return Math.abs(i - targetI) + Math.abs(j - targetJ);
    }

    public boolean isGoal() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (blocks[i][j] != goalValue(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private int goalValue(int i, int j) {
        if (i == size - 1 && j == size - 1) {
            return 0;
        }
        return 1 + size * i + j;
    }

    public Board twin() {
        int[][] twinBlocks = new int[size][size];
        copy(blocks, twinBlocks, size);
        if (blocks[0][0] != 0 && blocks[0][1] != 0) {
            swap(twinBlocks, 0, 0, 0, 1);
        }
        if (blocks[0][0] != 0 && blocks[0][2] != 0) {
            swap(twinBlocks, 0, 0, 0, 2);
        }
        return new Board(twinBlocks);
    }

    private void swap(int[][] array, int i1, int j1, int i2, int j2) {
        int temp = array[i1][j1];
        array[i1][j1] = array[i2][j2];
        array[i2][j2] = temp;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Board that = (Board) other;
        return that.toString().equals(this.toString());
    }

    private enum Move {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    private Board makeNeighborBoard(Move move, int blankRow, int blankCol) {
        int[][] neighborBlocks = new int[size][size];
        copy(blocks, neighborBlocks, size);
        switch (move) {
            case LEFT:
                if (blankCol == size - 1) return null;
                swap(neighborBlocks, blankRow, blankCol, blankRow, blankCol + 1);
                break;
            case RIGHT:
                if (blankCol == 0) return null;
                swap(neighborBlocks, blankRow, blankCol, blankRow, blankCol - 1);
                break;
            case UP:
                if (blankRow == size - 1) return null;
                swap(neighborBlocks, blankRow, blankCol, blankRow + 1, blankCol);
                break;
            case DOWN:
                if (blankRow == 0) return null;
                swap(neighborBlocks, blankRow, blankCol, blankRow - 1, blankCol);
                break;
        }
        return new Board(neighborBlocks);
    }

    public Iterable<Board> neighbors() {
        Stack<Board> result = new Stack<>();
        int blankRow = 0;
        int blankCol = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (blocks[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                }
            }
        }
        for (Move move : Move.values()) {
            Board board = makeNeighborBoard(move, blankRow, blankCol);
            if (board != null) {
                result.push(board);
            }
        }
        return result;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(size + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}
