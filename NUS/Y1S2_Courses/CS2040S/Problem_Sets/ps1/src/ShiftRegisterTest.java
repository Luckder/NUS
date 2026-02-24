import static org.junit.Assert.*;

import org.junit.Test;

/**
 * ShiftRegisterTest
 * @author dcsslg
 * Description: set of tests for a shift register implementation
 */
public class ShiftRegisterTest {
    /**
     * Returns a shift register to test.
     * @param size
     * @param tap
     * @return a new shift register
     */
    ILFShiftRegister getRegister(int size, int tap) {
        return new ShiftRegister(size, tap);
    }

    /**
     * Tests shift with simple example.
     */
    @Test
    public void testShift1() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
        r.setSeed(seed);
        int[] expected = { 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], r.shift());
        }
    }

    /**
     * Tests generate with simple example.
     */
    @Test
    public void testGenerate1() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
        r.setSeed(seed);
        int[] expected = { 6, 1, 7, 2, 2, 1, 6, 6, 2, 3 };
        for (int i = 0; i < 10; i++) {
            assertEquals("GenerateTest", expected[i], r.generate(3));
        }
    }

    /**
     * Tests register of length 1.
     */
    @Test
    public void testOneLength() {
        ILFShiftRegister r = getRegister(1, 0);
        int[] seed = { 1 };
        r.setSeed(seed);
        int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], r.generate(3));
        }
    }

    /**
     * Tests with erroneous seed.
     */
    @Test
    public void testError() {
        ILFShiftRegister r = getRegister(4, 1);
        int[] seed = { 1, 0, 0, 0, 1, 1, 0 };
        r.setSeed(seed);
        r.shift();
        r.generate(4);
    }

    /**
     * Throw an error/exception immediately, so that the code immediately stops. try and catch can be used
     * so if any error is caught, the code will cease immediately. To properly test this, ensure all parameters
     * line up with the contract of the question, ie. size of seed does not exceed specified register.
     */

    /**
     * Scenario Test: Tests with a smaller register (size 4) and a different tap (index 2).
     */
    @Test
    public void testCustomScenario() {
        ILFShiftRegister r = getRegister(4, 2);
        // Seed 1010 (LSB at index 0)
        int[] seed = {0, 1, 0, 1};
        r.setSeed(seed);

        int[] expectedShifts = {1, 1};
        for (int i = 0; i < 2; i++) {
            assertEquals(expectedShifts[i], r.shift());
        }
    }

    /**
     * Corner Case: Tests register of length 1 with a seed of 0.
     * With size 1 and tap 0, feedback is 0 ^ 0 = 0.
     */
    @Test
    public void testOneLengthZeroSeed() {
        ILFShiftRegister r = getRegister(1, 0);
        int[] seed = { 0 };
        r.setSeed(seed);
        // Since seed is 0 and feedback is always (0^0), it should stay 0.
        for (int i = 0; i < 5; i++) {
            assertEquals(0, r.shift());
        }
    }

    /**
     * Tests generate(k) where k is 1.
     * The result should be the same as the bit returned by shift().
     */
    @Test
    public void testGenerateOneBit() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
        r.setSeed(seed);
        // First shift returns 1, so generate(1) should return 1.
        assertEquals(1, r.generate(1));
        // Second shift returns 1, so generate(1) should return 1.
        assertEquals(1, r.generate(1));
        // Third shift returns 0, so generate(1) should return 0.
        assertEquals(0, r.generate(1));
    }

    /**
     * Tests the shift function with the 13-bit seed of baby yoda.
     * Seed: 0111011001011
     */
    @Test
    public void testMysteryShift() {
        ILFShiftRegister r = getRegister(13, 7);
        int[] seed = {1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0};
        r.setSeed(seed);
        assertEquals(1, r.shift());
        assertEquals(0, r.shift());
    }

    /**
     * Tests that the register remains unchanged if the seed is all zeros.
     */
    @Test
    public void testAllZeros() {
        ILFShiftRegister r = getRegister(4, 1);
        int[] seed = {0, 0, 0, 0};
        r.setSeed(seed);

        for (int i = 0; i < 10; i++) {
            assertEquals(0, r.shift());
        }
    }
}