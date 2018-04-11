import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    public static Board makeUnsolvableBoard() {
        int[] elements = {1, 2, 3, 4, 5, 6, 8, 7};
        return BoardTest.makeBoard(3, elements);
    }

    @Test
    void constructor_null() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Solver(null);
        });
    }

    @Test
    void isSolvable_true() {
        Solver solver = new Solver(BoardTest.makeGoalBoard());
        assertTrue(solver.isSolvable());
    }

    @Test
    void isSolvable_false() {
        Solver solver = new Solver(makeUnsolvableBoard());
        assertFalse(solver.isSolvable());
    }

    @Test
    void moves_alreadySolved() {
        Solver solver = new Solver(BoardTest.makeGoalBoard());
        assertEquals(0, solver.moves());
    }

    @Test
    void moves_example() {
        int[] elements = {0, 1, 3, 4, 2, 5, 7, 8, 6};
        Board board = BoardTest.makeBoard(3, elements);
        Solver solver = new Solver(board);
        assertEquals(4, solver.moves());
    }

    @Test
    void moves_unsolvable() {
        Solver solver = new Solver(makeUnsolvableBoard());
        assertEquals(-1, solver.moves());
    }

    @Test
    void solution_alreadySolved() {
        Solver solver = new Solver(BoardTest.makeGoalBoard());
        Board board = solver.solution().iterator().next();
        assertEquals(board, BoardTest.makeGoalBoard());
    }

    @Test
    void solution_unsolvable() {
        Solver solver = new Solver(makeUnsolvableBoard());
        assertNull(solver.solution());
    }

}