import java.util.*; // Wildcard used here as I plan to use either ArrayList, or HashMap
/**
 * This is the main class for your Markov Model.
 *
 * Assume that the text will contain ASCII characters in the range [1,255].
 * ASCII character 0 (the NULL character) will be treated as a non-character.
 *
 * Any such NULL characters in the original text should be ignored.
 */
public class MarkovModel {

	// Use this to generate random numbers as needed
	private Random generator = new Random();

	// This is a special symbol to indicate no character
	public static final char NOCHARACTER = (char) 0;

	private int order;
	private Map<String, int[]> table;

	/**
	 * Constructor for MarkovModel class.
	 *
	 * @param order the number of characters to identify for the Markov Model sequence
	 * @param seed the seed used by the random number generator
	 */
	public MarkovModel(int order, long seed) {
		// Initialize your class here
		this.order = order;
		this.table = new HashMap<>();

		// Initialize the random number generator
		this.generator.setSeed(seed);
	}

	/**
	 * Builds the Markov Model based on the specified text string.
	 */
	public void initializeText(String text) {
		// Build the Markov model here
		String curr = "";
		char next = 'a';

		for (int i = 0; i < text.length() - this.order; i++) {

			curr = text.substring(i, i + this.order);
			next = text.charAt(i + this.order);

			int[] counts = table.computeIfAbsent(curr, k -> new int[256]); // From the pdf
			counts[(int) next]++; // Index is the ASCII, value is the count

		}

	}

	/**
	 * Returns the number of times the specified kgram appeared in the text.
	 */
	public int getFrequency(String kgram) {
		int[] freq = table.get(kgram);

		if (freq == null) { return 0; }

		int count = 0;

		for (int num : freq) { count += num; }

		return count;
	}

	/**
	 * Returns the number of times the character c appears immediately after the specified kgram.
	 */
	public int getFrequency(String kgram, char c) {
		int[] freq = table.get(kgram);

		if (freq == null) { return 0; }

		return freq[(int) c];
	}

	/**
	 * Generates the next character from the Markov Model.
	 * Return NOCHARACTER if the kgram is not in the table, or if there is no
	 * valid character following the kgram.
	 */
	public char nextCharacter(String kgram) {
		// See the problem set description for details
		// on how to make the random selection.

		float bottom = (float) getFrequency(kgram);

		int[] freq = table.get(kgram);

		if (freq == null) { return NOCHARACTER; }

		int total = 0;

		for (int num : freq) { // O(n)
			if (num > 0) { total += num; }
		}

		if (total == 0) { return NOCHARACTER; }

		int rng = this.generator.nextInt(total);
		int sum = 0;

		for (int index = 0; index < freq.length; index++) { // O(2n) is still O(n)
			if (freq[index] > 0) {
				sum += freq[index];

				if (rng < sum) {
					return (char) index;
				}
			}
		}

		return NOCHARACTER;
	}
}
