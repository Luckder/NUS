///////////////////////////////////
// This is the main shift register class.
// Notice that it implements the ILFShiftRegister interface.
// You will need to fill in the functionality.
///////////////////////////////////

/**
 * class ShiftRegister
 * @author David
 * Description: implements the ILFShiftRegister interface.
 */
public class ShiftRegister implements ILFShiftRegister {
    ///////////////////////////////////
    // Create your class variables here
    ///////////////////////////////////
    // TODO:
    private int[] register;
    private int tap;
    private int size;

    ///////////////////////////////////
    // Create your constructor here:
    ///////////////////////////////////
    ShiftRegister(int size, int tap) {
        // TODO:
        this.size = size;
        this.tap = tap;
        this.register = new int[size];
    }

    ///////////////////////////////////
    // Create your class methods here:
    ///////////////////////////////////
    /**
     * setSeed
     * @param seed
     * Description: Sets the shift register to the specified initial seed.
     */
    @Override
    public void setSeed(int[] seed) {
        // TODO:
        // Assume seed.length == size as per instructions.
        // index 0 is the least significant bit.
        for (int i = 0; i < size; i++) {
            this.register[i] = seed[i];
        }
    }

    /**
     * shift
     * @return
     * Description: Executes one shift step. Drops MSB, shifts left, and inserts feedback at index 0.
     */
    @Override
    public int shift() {
        // TODO:
        // Save the bit at the most significant position (size - 1)
        int droppedMSB = register[size - 1];

        // Calculate the feedback bit: XOR of the dropped MSB and the tap bit
        int feedbackBit = droppedMSB ^ register[tap];

        // Every bit other than the MSB moves one index to the left (upwards)
        for (int i = size - 1; i > 0; i--) {
            register[i] = register[i - 1];
        }

        // The index 0 is now the feedback bit
        register[0] = feedbackBit;

        // Return the feedback bit
        return feedbackBit;
    }

    /**
     * generate
     * @param k
     * @return
     * Description: Extracts k bits and converts them to decimal.
     */
    @Override
    public int generate(int k) {
        // TODO:
        int decimalValue = 0;
        for (int i = 0; i < k; i++) {
            int bit = shift();
            // If shifts return 1, then 1, then 0, generate returns 6 (110 in binary).
            // This implies the first bit shifted out is the most significant bit of the result.
            decimalValue = (decimalValue * 2) + bit;
        }
        return decimalValue;
    }

    /**
     * Returns the integer representation for a binary int array.
     * @param array
     * @return
     */
    private int toDecimal(int[] array) {
        // TODO:
        int val = 0;
        for (int bit : array) {
            val = (val * 2) + bit;
        }
        return val;
    }
}