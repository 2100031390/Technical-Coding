
/**
 * Problem 3: Knights and Portals
 * 
 * Given a grid, find the shortest path from the top-left to bottom-right.
 * You may teleport between any two empty cells exactly once.
 * 
 * Approach:
 * - Use BFS with state tracking whether teleport has been used.
 * - From each cell, move to adjacent empty cells.
 * - If teleport not used, can teleport to any other empty cell once.
 * - Return shortest path length or -1 if no path.
 * 
 * Includes test cases with sample input/output in main method.
 */

import java.util.*;

public class KnightsAndPortals {

    // State class to represent position, teleport usage, and distance traveled
    static class State {
        int row, col;
        boolean teleportUsed;
        int distance;

        State(int row, int col, boolean teleportUsed, int distance) {
            this.row = row;
            this.col = col;
            this.teleportUsed = teleportUsed;
            this.distance = distance;
        }
    }

    /**
     * Finds the shortest path from top-left to bottom-right in the grid,
     * with the ability to teleport once between any two empty cells.
     * 
     * @param grid 2D integer grid where 0 = empty cell, 1 = blocked cell
     * @return shortest path length or -1 if no path exists
     */
    public static int shortestPath(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Collect all empty cells for possible teleportation
        List<int[]> emptyCells = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    emptyCells.add(new int[] { r, c });
                }
            }
        }

        boolean[][][] visited = new boolean[rows][cols][2];
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(0, 0, false, 0));
        visited[0][0][0] = true;

        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        while (!queue.isEmpty()) {
            State current = queue.poll();
            int r = current.row, c = current.col;

            if (r == rows - 1 && c == cols - 1) {
                return current.distance;
            }

            // Move to adjacent cells
            for (int[] d : directions) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 0) {
                    int teleState = current.teleportUsed ? 1 : 0;
                    if (!visited[nr][nc][teleState]) {
                        visited[nr][nc][teleState] = true;
                        queue.offer(new State(nr, nc, current.teleportUsed, current.distance + 1));
                    }
                }
            }

            // Teleport if not used
            if (!current.teleportUsed) {
                for (int[] cell : emptyCells) {
                    int tr = cell[0], tc = cell[1];
                    if (tr == r && tc == c)
                        continue; // skip current cell
                    if (!visited[tr][tc][1]) {
                        visited[tr][tc][1] = true;
                        queue.offer(new State(tr, tc, true, current.distance + 1));
                    }
                }
            }
        }

        return -1; // no path found
    }

    // Sample test cases
    public static void main(String[] args) {
        int[][] grid1 = {
                { 0, 0, 1, 0 },
                { 1, 0, 1, 0 },
                { 1, 0, 0, 0 },
                { 1, 1, 1, 0 }
        };
        System.out.println("Shortest path grid1: " + shortestPath(grid1)); // Expected: 5

        int[][] grid2 = {
                { 0, 1, 1 },
                { 1, 1, 1 },
                { 1, 1, 0 }
        };
        System.out.println("Shortest path grid2: " + shortestPath(grid2)); // Expected: -1 (no path)

        int[][] grid3 = {
                { 0, 0, 0 },
                { 0, 1, 0 },
                { 0, 0, 0 }
        };
        System.out.println("Shortest path grid3: " + shortestPath(grid3)); // Expected: 4
    }
}
