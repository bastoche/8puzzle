import edu.princeton.cs.algs4.StdOut;

public class Board {


    private final int size;
    private final int[][] blocks;

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
        int result = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (blocks[i][j] != 0) {
                    result += manhattan(blocks[i][j], i, j);
                }
            }
        }
        return result;
    }

    private int manhattan(int value, int i, int j) {
        int targetI = (value  - 1) / 3;
        int targetJ = (value  - 1) % 3;
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
            swap(twinBlocks, 0, 0, 1);
        }
        if (blocks[0][0] != 0 && blocks[0][2] != 0) {
            swap(twinBlocks, 0, 0, 2);
        }
        return new Board(twinBlocks);
    }

    private void swap(int[][] array, int i, int j, int k) {
        int temp = array[i][j];
        array[i][j] = array[i][k];
        array[i][k] = temp;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Board that = (Board) other;
        return that.toString().equals(this.toString());
    }

//    public Iterable<Board> neighbors()     // all neighboring boards

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
