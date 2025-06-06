/**
 * Problem 4: Bitwise Matching Pattern
 * 
 * Given an integer n, return the next larger integer with the same number of
 * binary 1s as n.
 * 
 * Approach:
 * - Use bit manipulation to find the next number with the same count of 1 bits.
 * - This is a known problem often solved using Brian Kernighan's algorithm or
 * bit tricks.
 * 
 * Includes test cases with sample input/output in main method.
 */

public class BitwiseMatchingPattern {

    /**
     * Finds the next larger integer with the same number of 1 bits as n.
     * 
     * @param n input integer
     * @return next larger integer with same number of 1 bits, or -1 if none exists
     */
    public static int nextLargerWithSameOnes(int n) {
        int c = n;
        int c0 = 0; // count of trailing zeros
        int c1 = 0; // count of ones right after trailing zeros

        // Count trailing zeros
        while (((c & 1) == 0) && (c != 0)) {
            c0++;
            c >>= 1;
        }

        // Count ones
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }

        // If n is like 111...0000 or all ones, no bigger number with same ones
        if (c0 + c1 == 31 || c0 + c1 == 0) {
            return -1;
        }

        int p = c0 + c1; // position of rightmost non-trailing zero

        // Flip rightmost non-trailing zero
        n |= (1 << p);

        // Clear all bits to the right of p
        n &= ~((1 << p) - 1);

        // Insert (c1-1) ones on the right
        n |= (1 << (c1 - 1)) - 1;

        return n;
    }

    // Sample test cases
    public static void main(String[] args) {
        int n1 = 6; // binary 110
        System.out.println("Next larger with same ones for " + n1 + ": " + nextLargerWithSameOnes(n1)); // Expected: 9
                                                                                                        // (1001)

        int n2 = 13; // binary 1101
        System.out.println("Next larger with same ones for " + n2 + ": " + nextLargerWithSameOnes(n2)); // Expected: 14
                                                                                                        // (1110)

        int n3 = 7; // binary 111
        System.out.println("Next larger with same ones for " + n3 + ": " + nextLargerWithSameOnes(n3)); // Expected: 11
                                                                                                        // (1011)

        int n4 = Integer.MAX_VALUE; // all ones except sign bit
        System.out.println("Next larger with same ones for " + n4 + ": " + nextLargerWithSameOnes(n4)); // Expected: -1
                                                                                                        // (no larger)
    }
}
