
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

public class TeleportPath {

    // Small helper class to keep track of a cell, travel distance, and teleport status
    static class Node {
        int r, c;       // row and col
        boolean tp;     // true if teleport already used
        int dist;       // steps taken so far

        Node(int r, int c, boolean tp, int dist) {
            this.r = r;
            this.c = c;
            this.tp = tp;
            this.dist = dist;
        }
    }

    /**
     * Finds the shortest route from (0,0) to (R-1,C-1) in a grid.
     * You may “jump” (teleport) once between any two empty cells (value 0).
     * Returns -1 if the goal is impossible to reach.
     */
    public static int shortest(int[][] grid) {
        int R = grid.length, C = grid[0].length;

        // Store every empty square so we know where we can teleport
        List<int[]> empties = new ArrayList<>();
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                if (grid[i][j] == 0) empties.add(new int[]{i, j});

        // visited[row][col][tpState]   tpState = 0 (not used) or 1 (used)
        boolean[][][] vis = new boolean[R][C][2];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, false, 0));
        vis[0][0][0] = true;

        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};   // down, up, right, left

        while (!q.isEmpty()) {
            Node cur = q.poll();

            // reached goal
            if (cur.r == R - 1 && cur.c == C - 1) return cur.dist;

            // move to 4 neighbours
            for (int[] d : dir) {
                int nr = cur.r + d[0], nc = cur.c + d[1];
                if (nr >= 0 && nr < R && nc >= 0 && nc < C && grid[nr][nc] == 0) {
                    int t = cur.tp ? 1 : 0;
                    if (!vis[nr][nc][t]) {
                        vis[nr][nc][t] = true;
                        q.add(new Node(nr, nc, cur.tp, cur.dist + 1));
                    }
                }
            }

            // try the one-time teleport
            if (!cur.tp) {
                for (int[] cell : empties) {
                    int tr = cell[0], tc = cell[1];
                    if (tr == cur.r && tc == cur.c) continue;   // already here
                    if (!vis[tr][tc][1]) {
                        vis[tr][tc][1] = true;
                        q.add(new Node(tr, tc, true, cur.dist + 1));
                    }
                }
            }
        }
        return -1;   // goal never reached
    }

    // Simple tests
    public static void main(String[] args) {
        int[][] g1 = {
                {0, 0, 1, 0},
                {1, 0, 1, 0},
                {1, 0, 0, 0},
                {1, 1, 1, 0}
        };
        int[][] g2 = {
                {0, 1, 1},
                {1, 1, 1},
                {1, 1, 0}
        };
        int[][] g3 = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        System.out.println(shortest(g1)); // 5
        System.out.println(shortest(g2)); // -1
        System.out.println(shortest(g3)); // 4
    }
}
