package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    public static void main(String[] args) {
        randomizedTest();
    }
    public static void testThreeAddThreeRemove() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();

        for (int i = 4; i < 7; i += 1) {
            buggy.addLast(i);
            L.addLast(i);
        }

        for (int i = 0; i < 3; i += 1) {
            assertEquals(buggy.removeLast(), L.removeLast());
        }
    }

    public static void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                buggy.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() > 0 & buggy.size() > 0) {
                    int ret = buggy.getLast();
                    int ret2 = L.getLast();
                    System.out.println("getLast(" + ret + ")" + " getLast(" + ret2 + ")");
                    assertEquals(ret, ret2);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() > 0 & buggy.size() > 0) {
                    int ret = buggy.removeLast();
                    int ret2 = L.removeLast();
                    System.out.println("removeLast(" + ret + ")" + " removeLast(" + ret2 + ")");
                    assertEquals(ret, ret2);
                }
            }
        }
    }
}
