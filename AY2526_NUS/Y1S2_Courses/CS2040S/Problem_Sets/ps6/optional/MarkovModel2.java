import java.util.*; // Wildcard used here as I plan to use either ArrayList, or HashMap
/**
 * This is the main class for your Markov Model.
 *
 * Assume that the text will contain ASCII characters in the range [1,255].
 * ASCII character 0 (the NULL character) will be treated as a non-character.
 *
 * Any such NULL characters in the original text should be ignored.
 */
public class MarkovModel2 {

	// Use this to generate random numbers as needed
	private Random generator = new Random();

	// This is a special symbol to indicate no word
	public static final String NOWORD = null;

	// This is the private order variable for the k in kgram
	private int order;

	// This is the HashMap data structure
	// Two Maps instead of any Lists as there is a very large number of words in the English lexicon
	private Map<String, Map<String, Integer>> table;

	/**
	 * Constructor for MarkovModel class.
	 *
	 * @param order the number of words to identify for the Markov Model sequence
	 * @param seed the seed used by the random number generator
	 */
	public MarkovModel2(int order, long seed) {
		// Initialize your class here
		this.order = order;

		// HashMap is used for the data structure
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
		String next = "";

		// Adds space around punctuations for later splitting except apostrophes
		// $1 means the first captured group by regex, refer to Java docs
		String temp = text.replaceAll("([\\p{Punct}&&[^'’]])", " $1 ");

		// Trim leading and trailing spaces, then
		// Splits the text into an array of words separated by spaces
		String[] words = temp.trim().split("\\s+");

		// Loops through the word array, grouped by 'order' amount of words
		for (int i = 0; i < words.length - this.order; i++) {

			// Stringbuilder to avoid O(n) time complexity of String concatenation
			StringBuilder currBuilder = new StringBuilder();

			for (int j = 0; j < this.order; j++) {
				// Appends the word to the StringBuilder
				currBuilder.append(words[i + j]);

				// Adds space between words, but not after the last word in the kgram
				if (j < this.order - 1) { currBuilder.append(" "); }
			}

			// Assigns the kgram to curr, and the next word to next
			curr = currBuilder.toString();
			next = words[i + this.order];

			// Nested map logic: the outer map's key is the kgram,
			// its value is another map that maps the next word to its frequency
			Map<String, Integer> nextWords = table.computeIfAbsent(curr, k -> new HashMap<>());
			nextWords.put(next, nextWords.getOrDefault(next, 0) + 1);
		}

	}

	/**
	 * Returns the number of times the specified kgram appeared in the text.
	 */
	public int getFrequency(String kgram) {
		// Same logic as the method in the previous MarkovModel, but adapted for the new data structure

		// Get the map of next words and their frequencies for the given kgram
		Map<String, Integer> freq = table.get(kgram);

		// If the kgram is not in the table, return 0
		if (freq == null) { return 0; }

		int count = 0;

		// Loop through the frequencies of the next words for the given kgram,
		// sum them up to get the total frequency of the kgram
		for (int num : freq.values()) { count += num; }

		return count;
	}

	/**
	 * Returns the number of times the word w appears immediately after the specified kgram.
	 */
	public int getFrequency(String kgram, String w) {
		// Same logic as the method in the previous MarkovModel, but look up the frequency of the word w instead

		// Get the map of next words and their frequencies for the given kgram
		Map<String, Integer> freq = table.get(kgram);

		// If the kgram is not in the table, return 0
		if (freq == null) { return 0; }

		// Return the frequency of the word w following the kgram, or 0 if w is not a valid next word
		return freq.getOrDefault(w, 0);
	}

	/**
	 * Generates the next character from the Markov Model.
	 * Return NOWORD if the kgram is not in the table, or if there is no
	 * valid character following the kgram.
	 */
	public String nextWord(String kgram) {
		// See the problem set description for details
		// on how to make the random selection.

		// Same logic as nextCharacter method in the previous MarkovModel, but adapted for the new data structure

		int total = getFrequency(kgram);

		// If the kgram is not in the table, or if there is no valid word following the kgram, return NOWORD
		if (total == 0) { return NOWORD; }

		// rng calculates a random integer in the range [0, total)
		int rng = this.generator.nextInt(total);
		int sum = 0;

		// Get the map of next words and their frequencies for the given kgram
		Map<String, Integer> freq = table.get(kgram);

		// Loop through the next words and their frequencies,
		// return the first word where the cumulative frequency exceeds rng
		for (Map.Entry<String, Integer> entry : freq.entrySet()) {
			sum += entry.getValue();

			if (rng < sum) { return entry.getKey(); }
		}

		return NOWORD;
	}
}
