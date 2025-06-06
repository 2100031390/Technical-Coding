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

public class MatrixIslands {

    private static final int[] ROW_DIRS = { -1, -1, -1, 0, 0, 1, 1, 1 };
    private static final int[] COL_DIRS = { -1, 0, 1, -1, 1, -1, 0, 1 };

    public static int countIslands(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int count = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 1 && !visited[r][c]) {
                    dfs(matrix, visited, r, c, rows, cols);
                    count++;
                }
            }
        }

        return count;
    }

    private static void dfs(int[][] matrix, boolean[][] visited, int r, int c, int rows, int cols) {
        visited[r][c] = true;
        for (int i = 0; i < 8; i++) {
            int nr = r + ROW_DIRS[i];
            int nc = c + COL_DIRS[i];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                if (matrix[nr][nc] == 1 && !visited[nr][nc]) {
                    dfs(matrix, visited, nr, nc, rows, cols);
                }
            }
        }
    }

    // Sample test cases
    public static void main(String[] args) {
        int[][] matrix1 = {
                { 1, 1, 0, 0 },
                { 0, 1, 0, 1 },
                { 1, 0, 0, 1 },
                { 0, 0, 1, 0 }
        };
        System.out.println("Number of islands in matrix1: " + countIslands(matrix1)); // Expected: 3

        int[][] matrix2 = {
                { 1, 0, 0 },
                { 0, 0, 0 },
                { 0, 0, 1 }
        };
        System.out.println("Number of islands in matrix2: " + countIslands(matrix2)); // Expected: 2

        int[][] matrix3 = {
                { 0, 0, 0 },
                { 0, 0, 0 },
                { 0, 0, 0 }
        };
        System.out.println("Number of islands in matrix3: " + countIslands(matrix3)); // Expected: 0
    }
}
