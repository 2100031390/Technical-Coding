
/**
 * Comprehensive test suite for all problems in TechnicalCodingInterviewSolutions.
 * 
 * Includes edge cases, error handling, and performance tests where applicable.
 */

import java.util.*;

public class TestAllProblems {

        public static void testSudokuValidator() {
                System.out.println("Testing SudokuValidator...");

                // Valid board with empty cells and custom zones
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

                List<List<int[]>> customZones = new ArrayList<>();
                List<int[]> zone1 = Arrays.asList(
                                new int[] { 0, 0 }, new int[] { 0, 1 }, new int[] { 0, 2 },
                                new int[] { 1, 0 }, new int[] { 1, 1 }, new int[] { 1, 2 },
                                new int[] { 2, 0 }, new int[] { 2, 1 }, new int[] { 2, 2 });
                List<int[]> zone2 = Arrays.asList(
                                new int[] { 6, 6 }, new int[] { 6, 7 }, new int[] { 6, 8 },
                                new int[] { 7, 6 }, new int[] { 7, 7 }, new int[] { 7, 8 },
                                new int[] { 8, 6 }, new int[] { 8, 7 }, new int[] { 8, 8 });
                customZones.add(zone1);
                customZones.add(zone2);

                assert SudokuValidator.isValidSudoku(validBoard, customZones) : "Valid board failed validation";

                // Invalid board with duplicate in row
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
                assert !SudokuValidator.isValidSudoku(invalidBoard, customZones) : "Invalid board passed validation";

                System.out.println("SudokuValidator tests passed.");
        }

        public static void testAlienDictionary() {
                System.out.println("Testing AlienDictionary...");

                String[] words1 = { "wrt", "wrf", "er", "ett", "rftt" };
                assert AlienDictionary.alienOrder(words1).equals("wertf") : "Test case 1 failed";

                String[] words2 = { "z", "x" };
                assert AlienDictionary.alienOrder(words2).equals("zx") : "Test case 2 failed";

                String[] words3 = { "z", "x", "z" };
                assert AlienDictionary.alienOrder(words3).equals("") : "Test case 3 failed";

                String[] words4 = { "abc", "ab" };
                assert AlienDictionary.alienOrder(words4).equals("") : "Test case 4 failed";

                // Edge case: single word
                String[] words5 = { "abc" };
                assert AlienDictionary.alienOrder(words5).equals("abc") : "Test case 5 failed";

                System.out.println("AlienDictionary tests passed.");
        }

        public static void testKnightsAndPortals() {
                System.out.println("Testing KnightsAndPortals...");

                int[][] grid1 = {
                                { 0, 0, 1, 0 },
                                { 1, 0, 1, 0 },
                                { 1, 0, 0, 0 },
                                { 1, 1, 1, 0 }
                };
                assert KnightsAndPortals.shortestPath(grid1) == 5 : "Test case 1 failed";

                int[][] grid2 = {
                                { 0, 1, 1 },
                                { 1, 1, 1 },
                                { 1, 1, 0 }
                };
                assert KnightsAndPortals.shortestPath(grid2) == -1 : "Test case 2 failed";

                int[][] grid3 = {
                                { 0, 0, 0 },
                                { 0, 1, 0 },
                                { 0, 0, 0 }
                };
                assert KnightsAndPortals.shortestPath(grid3) == 4 : "Test case 3 failed";

                // Edge case: 1x1 grid
                int[][] grid4 = { { 0 } };
                assert KnightsAndPortals.shortestPath(grid4) == 0 : "Test case 4 failed";

                System.out.println("KnightsAndPortals tests passed.");
        }

        public static void testBitwiseMatchingPattern() {
                System.out.println("Testing BitwiseMatchingPattern...");

                assert BitwiseMatchingPattern.nextLargerWithSameOnes(6) == 9 : "Test case 1 failed";
                assert BitwiseMatchingPattern.nextLargerWithSameOnes(13) == 14 : "Test case 2 failed";
                assert BitwiseMatchingPattern.nextLargerWithSameOnes(7) == 11 : "Test case 3 failed";
                assert BitwiseMatchingPattern.nextLargerWithSameOnes(Integer.MAX_VALUE) == -1 : "Test case 4 failed";

                // Edge case: number with no larger number with same ones
                assert BitwiseMatchingPattern.nextLargerWithSameOnes(0) == -1 : "Test case 5 failed";

                System.out.println("BitwiseMatchingPattern tests passed.");
        }

        public static void testMatrixIslands() {
                System.out.println("Testing MatrixIslands...");

                int[][] matrix1 = {
                                { 1, 1, 0, 0 },
                                { 0, 1, 0, 1 },
                                { 1, 0, 0, 1 },
                                { 0, 0, 1, 0 }
                };
                assert MatrixIslands.countIslands(matrix1) == 3 : "Test case 1 failed";

                int[][] matrix2 = {
                                { 1, 0, 0 },
                                { 0, 0, 0 },
                                { 0, 0, 1 }
                };
                assert MatrixIslands.countIslands(matrix2) == 2 : "Test case 2 failed";

                int[][] matrix3 = {
                                { 0, 0, 0 },
                                { 0, 0, 0 },
                                { 0, 0, 0 }
                };
                assert MatrixIslands.countIslands(matrix3) == 0 : "Test case 3 failed";

                System.out.println("MatrixIslands tests passed.");
        }

        public static void testMiniInterpreter() {
                System.out.println("Testing MiniInterpreter...");

                MiniInterpreter interpreter = new MiniInterpreter();

                List<String> program1 = Arrays.asList(
                                "let x = 5;",
                                "if (x > 3) { let y = 10; }",
                                "if (x < 3) { let z = 20; }",
                                "let a = 7;");
                interpreter.interpret(program1);

                // Additional tests can be added here for more complex scenarios

                System.out.println("MiniInterpreter tests executed (manual verification required).");
        }

        public static void main(String[] args) {
                testSudokuValidator();
                testAlienDictionary();
                testKnightsAndPortals();
                testBitwiseMatchingPattern();
                testMatrixIslands();
                testMiniInterpreter();

                System.out.println("All tests completed.");
        }
}
