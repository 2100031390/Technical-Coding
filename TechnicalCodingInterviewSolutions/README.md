# Technical Coding Interview Solutions

This project contains Java solutions for a set of technical coding interview problems, including test cases and a comprehensive test suite.

## Problems Included and Explanation

1. **Sudoku Validator With Custom Zones**  
   Validates a 9x9 Sudoku board including custom zones for unique digits 1-9.  
   **Explanation:**  
   The solution checks each row, column, and 3x3 sub-box to ensure no repeated digits 1-9. Additionally, it validates custom zones defined by the user, which are arbitrary groups of 9 cells that must also contain unique digits. The validation uses sets to track duplicates efficiently.  
   **Example:**  
   Input: A partially filled Sudoku board with zeros for empty cells and two custom zones defined as the top-left and bottom-right 3x3 boxes.  
   Output: `true` if the board is valid according to standard and custom zone rules, otherwise `false`.

2. **Alien Dictionary**  
   Determines the character order of an alien language given a sorted list of words.  
   **Explanation:**  
   The code builds a directed graph representing character precedence by comparing adjacent words and finding the first differing character. It then performs a topological sort (Kahn's algorithm) to determine a valid character order. Cycles or invalid inputs return an empty string.  
   **Example:**  
   Input: `["wrt", "wrf", "er", "ett", "rftt"]`  
   Output: `"wertf"`

3. **Knights and Portals**  
   Finds the shortest path in a grid with one allowed teleport between empty cells.  
   **Explanation:**  
   Uses a breadth-first search (BFS) with state tracking to record whether the teleport has been used. From each cell, it explores adjacent empty cells and, if teleport is unused, can jump to any other empty cell once. The shortest path length is returned or -1 if unreachable.  
   **Example:**  
   Input:  
   ```
   {
     {0,0,1,0},
     {1,0,1,0},
     {1,0,0,0},
     {1,1,1,0}
   }
   ```  
   Output: `5`

4. **Bitwise Matching Pattern**  
   Finds the next larger integer with the same number of binary 1s as a given integer.  
   **Explanation:**  
   Uses bit manipulation to find the next integer greater than the input that has the same count of set bits (1s) in its binary representation. If no such number exists, returns -1.  
   **Example:**  
   Input: `6` (binary `110`)  
   Output: `9` (binary `1001`)

5. **Matrix Islands with Diagonals**  
   Counts the number of islands in a matrix considering diagonal connections.  
   **Explanation:**  
   Uses depth-first search (DFS) to traverse connected components of 1s in the matrix, considering horizontal, vertical, and diagonal neighbors as connected. Returns the count of such islands.  
   **Example:**  
   Input:  
   ```
   {
     {1,1,0,0},
     {0,1,0,1},
     {1,0,0,1},
     {0,0,1,0}
   }
   ```  
   Output: `3`

6. **Bonus Challenge: Mini Interpreter**  
   A simple interpreter for let variable declarations and if conditions.  
   **Explanation:**  
   Parses input strings representing variable declarations and conditional blocks, evaluates conditions, and maintains variable states accordingly. Supports basic integer variables and if conditions.  
   **Example:**  
   Input:  
   ```
   let x = 5;
   if (x > 3) { let y = 10; }
   if (x < 3) { let z = 20; }
   let a = 7;
   ```  
   Output: Variables `x=5`, `y=10`, `a=7` are set; `z` is not set because condition is false.

## How to Run

Compile all Java files:

```bash
javac TechnicalCodingInterviewSolutions/*.java
```

Run the comprehensive test suite:

```bash
java TechnicalCodingInterviewSolutions.TestAllProblems
```

Each problem file also contains a `main` method with sample test cases that can be run individually.

## Notes

- Ensure Java 8 or higher is installed.
- The project is organized in the `TechnicalCodingInterviewSolutions` directory.

## Contact

For any questions or issues, please contact the submitter.
