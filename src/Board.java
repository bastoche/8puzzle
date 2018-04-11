public class Board {


    private final int size;
    private final int[][] blocks;

    public Board(int[][] blocks) {
        size = blocks.length;
        this.blocks = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }
    }

    public int dimension() {
        return size;
    }

    //    public int hamming()                   // number of blocks out of place
//    public int manhattan()                 // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == size - 1 && j == size - 1) {
                    return blocks[i][j] == 0;
                } else if (blocks[i][j] != 1 + size * i + j) {
                    return false;
                }
            }
        }
        return true;
    }

//    public Board twin()                    // a board that is obtained by exchanging any pair of blocks

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Board that = (Board) other;
        return that.toString() == this.toString();
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


//    public static void main(String[] args) // unit tests (not graded)
}
