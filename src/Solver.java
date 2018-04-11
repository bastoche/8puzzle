import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Solver {

    private boolean isSolvable;
    private int moves;
    private final Stack<Board> solution;

    private static class SearchNode {
        final Board board;
        final int moves;
        final SearchNode previousNode;

        SearchNode(Board board, SearchNode previousNode) {
            this.board = board;
            this.previousNode = previousNode;
            if (previousNode == null) {
                moves = 0;
            } else {
                moves = previousNode.moves + 1;
            }
        }

        SearchNode(Board board) {
            this(board, null);
        }
    }

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        isSolvable = false;
        solution = new Stack<>();

        MinPQ<SearchNode> priorityQueue = initializePriorityQueue(initial);
        MinPQ<SearchNode> twinPriorityQueue = initializePriorityQueue(initial.twin());

        findSolution(priorityQueue, twinPriorityQueue);
    }

    private MinPQ<SearchNode> initializePriorityQueue(Board board) {
        MinPQ<SearchNode> priorityQueue = new MinPQ<>(Comparator.comparingInt(node -> node.moves + node.board.manhattan()));
        SearchNode initialNode = new SearchNode(board);
        priorityQueue.insert(initialNode);
        return priorityQueue;
    }


    private void findSolution(MinPQ<SearchNode> priorityQueue, MinPQ<SearchNode> twinPriorityQueue) {
        while (!priorityQueue.isEmpty() && !twinPriorityQueue.isEmpty()) {
            SearchNode node = findSolution(priorityQueue);
            if (node != null) {
                isSolvable = true;
                moves = node.moves;
                buildSolution(node);
                break;
            }

            if (findSolution(twinPriorityQueue) != null) {
                break;
            }
        }
    }

    private SearchNode findSolution(MinPQ<SearchNode> priorityQueue) {
        SearchNode node = priorityQueue.delMin();
        if (node.board.isGoal()) {
            return node;
        }

        Iterable<Board> neighbors = node.board.neighbors();
        for (Board neighbor : neighbors) {
            if (node.previousNode == null || !node.previousNode.board.equals(neighbor)) {
                priorityQueue.insert(new SearchNode(neighbor, node));
            }
        }
        return null;
    }

    private void buildSolution(SearchNode node) {
        SearchNode currentNode = node;
        while (currentNode != null) {
            solution.push(currentNode.board);
            currentNode = currentNode.previousNode;
        }
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        if (!isSolvable) {
            return -1;
        }
        return moves;
    }

    public Iterable<Board> solution() {
        if (!isSolvable) {
            return null;
        }
        return solution;
    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}