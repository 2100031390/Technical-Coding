/**
 * Problem 5: Matrix Islands with Diagonals
 * 
 * Count the number of islands in a matrix of 0s and 1s.
 * Islands are formed using horizontal, vertical, or diagonal connections.
 * 
 * Approach:
 * - Use DFS or BFS to traverse connected components including diagonals.
 * - Count the number of distinct islands.
 * 
 * Includes test cases with sample input/output in main method.
 */

import java.util.*;
public class IslandCounter {

    // Directions: 8 ways (up, down, left, right + 4 diagonals)
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    /**
     * Counts how many separate islands (groups of 1s) are in the grid.
     * Diagonal, horizontal, and vertical connections are allowed.
     */
    public static int countIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] seen = new boolean[n][m];
        int count = 0;

        // check every cell
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !seen[i][j]) {
                    // start DFS to mark whole island
                    dfs(grid, seen, i, j, n, m);
                    count++;
                }
            }
        }
        return count;
    }

    // Marks all connected 1s using DFS
    private static void dfs(int[][] grid, boolean[][] seen, int x, int y, int n, int m) {
        seen[x][y] = true;

        for (int d = 0; d < 8; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m &&
                grid[nx][ny] == 1 && !seen[nx][ny]) {
                dfs(grid, seen, nx, ny, n, m);
            }
        }
    }

    // Test cases
    public static void main(String[] args) {
        int[][] g1 = {
            {1, 1, 0, 0},
            {0, 1, 0, 1},
            {1, 0, 0, 1},
            {0, 0, 1, 0}
        };
        int[][] g2 = {
            {1, 0, 0},
            {0, 0, 0},
            {0, 0, 1}
        };
        int[][] g3 = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };

        System.out.println("Islands in g1: " + countIslands(g1)); // 3
        System.out.println("Islands in g2: " + countIslands(g2)); // 2
        System.out.println("Islands in g3: " + countIslands(g3)); // 0
    }
}

