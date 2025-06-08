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
public class BitwiseMatch {

    // This function finds the next bigger number with the same number of 1s in binary
    public static int getNext(int num) {
        int temp = num;
        int zeroCount = 0;
        int oneCount = 0;

        // Count trailing 0s
        while (((temp & 1) == 0) && (temp != 0)) {
            zeroCount++;
            temp >>= 1;
        }

        // Count 1s just after trailing 0s
        while ((temp & 1) == 1) {
            oneCount++;
            temp >>= 1;
        }

        // If number is like 111...000 or all 1s at the end, no answer
        if (zeroCount + oneCount == 31 || zeroCount + oneCount == 0) {
            return -1;
        }

        // Position of the first 0 to flip
        int pos = zeroCount + oneCount;

        // Set the 0 at position 'pos' to 1
        num |= (1 << pos);

        // Clear all bits to the right of position 'pos'
        num &= ~((1 << pos) - 1);

        // Add (oneCount - 1) ones to the right
        num |= (1 << (oneCount - 1)) - 1;

        return num;
    }

    public static void main(String[] args) {
        int a = 6;  // binary: 110
        System.out.println("Next for " + a + " is: " + getNext(a));  // Expected: 9 (1001)

        int b = 13; // binary: 1101
        System.out.println("Next for " + b + " is: " + getNext(b));  // Expected: 14 (1110)

        int c = 7;  // binary: 111
        System.out.println("Next for " + c + " is: " + getNext(c));  // Expected: 11 (1011)

        int d = Integer.MAX_VALUE; // All bits are 1 except sign bit
        System.out.println("Next for " + d + " is: " + getNext(d));  // Expected: -1 (no larger number)
    }
}

