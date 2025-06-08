
/**
 * Problem 1: Sudoku Validator With Custom Zones
 * 
 * Validate a 9x9 Sudoku board. In addition to standard validation, check custom zones 
 * (each with 9 unique cells) for digits 1–9 without repetition.
 * 
 * Approach:
 * - Validate rows, columns, and 3x3 sub-boxes for digits 1-9 without repetition.
 * - Validate custom zones (each zone is a list of 9 unique cell coordinates) for digits 1-9 without repetition.
 * - Return true if all validations pass, else false.
 * 
 * Includes test cases with sample input/output in main method.
 */
import java.util.*;

public class SudokuValidator {

    // Checks if a group of cells contains unique digits 1-9 (ignoring zeros)
    private static boolean isValidGroup(List<Integer> cells) {
        boolean[] seen = new boolean[10]; // indices 1 to 9
        for (int num : cells) {
            if (num != 0) {
                if (num < 1 || num > 9 || seen[num]) {
                    return false;
                }
                seen[num] = true;
            }
        }
        return true;
    }

    // Validates the Sudoku board including custom zones
    public static boolean isValidSudoku(int[][] board, List<List<int[]>> customZones) {
        // Validate rows
        for (int r = 0; r < 9; r++) {
            List<Integer> row = new ArrayList<>(9);
            for (int c = 0; c < 9; c++) {
                row.add(board[r][c]);
            }
            if (!isValidGroup(row)) return false;
        }

        // Validate columns
        for (int c = 0; c < 9; c++) {
            List<Integer> col = new ArrayList<>(9);
            for (int r = 0; r < 9; r++) {
                col.add(board[r][c]);
            }
            if (!isValidGroup(col)) return false;
        }

        // Validate standard 3x3 sub-boxes
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                List<Integer> box = new ArrayList<>(9);
                for (int r = boxRow * 3; r < boxRow * 3 + 3; r++) {
                    for (int c = boxCol * 3; c < boxCol * 3 + 3; c++) {
                        box.add(board[r][c]);
                    }
                }
                if (!isValidGroup(box)) return false;
            }
        }

        // Validate custom zones
        for (List<int[]> zone : customZones) {
            List<Integer> zoneCells = new ArrayList<>(zone.size());
            for (int[] cell : zone) {
                int r = cell[0], c = cell[1];
                zoneCells.add(board[r][c]);
            }
            if (!isValidGroup(zoneCells)) return false;
        }

        return true;
    }

    // Sample test cases
    public static void main(String[] args) {
        int[][] validBoard = {
            { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
            { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
            { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
            { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
            { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
            { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
            { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
            { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
            { 0, 0, 0, 0, 8, 0, 0, 7, 9 }
        };

        // Custom zones example: two zones, each with 9 unique cells
        List<List<int[]>> customZones = new ArrayList<>();

        List<int[]> zone1 = Arrays.asList(
            new int[] { 0, 0 }, new int[] { 0, 1 }, new int[] { 0, 2 },
            new int[] { 1, 0 }, new int[] { 1, 1 }, new int[] { 1, 2 },
            new int[] { 2, 0 }, new int[] { 2, 1 }, new int[] { 2, 2 }
        );

        List<int[]> zone2 = Arrays.asList(
            new int[] { 6, 6 }, new int[] { 6, 7 }, new int[] { 6, 8 },
            new int[] { 7, 6 }, new int[] { 7, 7 }, new int[] { 7, 8 },
            new int[] { 8, 6 }, new int[] { 8, 7 }, new int[] { 8, 8 }
        );

        customZones.add(zone1);
        customZones.add(zone2);

        System.out.println("Valid board with custom zones: " + isValidSudoku(validBoard, customZones)); // true

        int[][] invalidBoard = {
            { 5, 3, 5, 0, 7, 0, 0, 0, 0 },
            { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
            { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
            { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
            { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
            { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
            { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
            { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
            { 0, 0, 0, 0, 8, 0, 0, 7, 9 }
        };

        System.out.println("Invalid board with custom zones: " + isValidSudoku(invalidBoard, customZones)); // false
    }
}

