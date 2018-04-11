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
//    public boolean isGoal()                // is this board the goal board?
//    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
//    public boolean equals(Object y)        // does this board equal y?
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
