import java.io.FileInputStream;
import java.util.Scanner;

/**
 * This class is used to generated text using a Markov Model
 */
public class TextGenerator2 {

    // For testing, we will choose different seeds
    private static long seed;

    // Sets the random number generator seed
    public static void setSeed(long s) {
        seed = s;
    }

    /**
     * Reads in the file and builds the MarkovModel.
     *
     * @param order the order of the Markov Model
     * @param fileName the name of the file to read
     * @param model the Markov Model to build
     * @return the first {@code order} characters of the file to be used as the seed text
     */
    public static String buildModel(int order, String fileName, MarkovModel2 model) {
        // Get ready to parse the file.
        // StringBuffer is used instead of String as appending character to String is slow
        StringBuilder text = new StringBuilder();
        String[] words;

        // Loop through the text
        try {
            // Create a Scanner that points to the file and uses UTF-8 instead of the previous method
            java.io.File file = new java.io.File(fileName);
            Scanner scanner = new Scanner(file, "UTF-8");

            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine()).append(" ");
            }

            scanner.close();

            String temp = text.toString().replaceAll("([\\p{Punct}&&[^’']])", " $1 ");
            words = temp.trim().split("\\s+");

            // Make sure that length of input text is longer than requested Markov order
            if (words.length < order) {
                System.out.println("Text is shorter than specified Markov Order.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Problem reading file " + fileName + ".");
            return null;
        }

        // Build Markov Model of order from text
        model.initializeText(text.toString());

        StringBuilder seedTextBuilder = new StringBuilder();

        for (int i = 0; i < order; i++) {
            seedTextBuilder.append(words[i]);

            if (i < order - 1) { seedTextBuilder.append(" "); }
        }

        return seedTextBuilder.toString();
    }

    /**
     * generateText outputs to stdout text of the specified length based on the specified seedText
     * using the given Markov Model.
     *
     * @param model the Markov Model to use
     * @param seedText the initial kgram used to generate text
     * @param order the order of the Markov Model
     * @param length the length of the text to generate
     */
    public static void generateText(MarkovModel2 model, String seedText, int order, int length) {

        // Initialise a StringBuilder
        StringBuilder output = new StringBuilder(seedText);

        String currentKgram = seedText;

        for (int i = 0; i < length; i++) {
            String next = model.nextWord(currentKgram);

            if (next != MarkovModel2.NOWORD) {
                output.append(" ").append(next);

                currentKgram = updateKgram(currentKgram, next, order);
            } else {
                break;
            }
        }

        // regex was taken from Javadocs
        String clean = output.toString().replaceAll("\\s+([\\p{Punct}&&[^'’]])", "$1");
        clean = clean.replaceAll("\\s*'\\s*", "'");
        System.out.println(clean);
    }

    // Helper method to update the kgram by dropping the first word and adding the next word
    private static String updateKgram(String kgram, String nextWord, int order) {
        if (order <= 0) { return ""; }

        // Split current kgram into words
        String[] words = kgram.split("\\s+");

        // If order is 1, the kgram is just the next word
        if (order == 1) { return nextWord; }

        // Otherwise, drop the first word and add the new one
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < words.length; i++) {
            sb.append(words[i]).append(" ");
        }

        sb.append(nextWord);

        return sb.toString();
    }

    /**
     * The main routine.  Takes 3 arguments:
     * args[0]: the order of the Markov Model
     * args[1]: the length of the text to generate
     * args[2]: the filename for the input text
     */
    public static void main(String[] args) {
        // Check that we have three parameters
        if (args.length != 3) {
            System.out.println("Number of input parameters are wrong.");
        }

        // Get the input
        int order = Integer.parseInt(args[0]);
        int length = Integer.parseInt(args[1]);
        String fileName = args[2];

        // Random seed
        seed = System.currentTimeMillis();
        System.out.println("Random seed: " + seed);

        // Create the model
        MarkovModel2 markovModel2 = new MarkovModel2(order, seed);
        String seedText = buildModel(order, fileName, markovModel2);

        // Generate text, only if seedText built successfully
        if (seedText != null) {
            generateText(markovModel2, seedText, order, length);
        }
    }
}