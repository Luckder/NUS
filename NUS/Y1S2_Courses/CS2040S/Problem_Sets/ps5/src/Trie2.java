import java.util.ArrayList;

public class Trie {

    // Wildcards
    final char WILDCARD = '.';

    private static class TrieNode { // IntelliJ recommended this to be static
        // TODO: Create your TrieNode class here.
        TrieNode[] children = new TrieNode[62];
        boolean endOfWord = false;

        TrieNode() {

            for (int i = 0; i < 62; i++) {
                children[i] = null;
            }

        }
    }

    private final TrieNode root; // IntelliJ recommended this to be final

    public Trie() {
        // TODO: Initialise a trie class here.
        root = new TrieNode();
    }

    // Helper function
    private int charToIndex(char c) {

        if (c >= '0' && c <= '9') {
            return c - '0';
        } else if (c >= 'A' && c <= 'Z') {
            return 10 + (c - 'A');
        } else {
            return 36 + (c - 'a');
        }

    }

    /**
     * Inserts string s into the Trie.
     *
     * @param s string to insert into the Trie
     */
    void insert(String s) {
        // TODO
        TrieNode current = root;

        for (int i = 0; i < s.length(); i++) {

            int index = charToIndex(s.charAt(i));

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.endOfWord = true;
    }

    /**
     * Checks whether string s exists inside the Trie or not.
     *
     * @param s string to check for
     * @return whether string s is inside the Trie
     */
    boolean contains(String s) {
        // TODO
        TrieNode current = root;

        for (int i = 0; i < s.length(); i++) {

            int index = charToIndex(s.charAt(i));

            if (current.children[index] == null) {
                return false;
            }

            current = current.children[index];
        }

        return current.endOfWord;
    }

    /**
     * Searches for strings with prefix matching the specified pattern sorted by lexicographical order. This inserts the
     * results into the specified ArrayList. Only returns at most the first limit results.
     *
     * @param s       pattern to match prefixes with
     * @param results array to add the results into
     * @param limit   max number of strings to add into results
     */
    void prefixSearch(String s, ArrayList<String> results, int limit) {
        // TODO
    }


    // Simplifies function call by initializing an empty array to store the results.
    // PLEASE DO NOT CHANGE the implementation for this function as it will be used
    // to run the test cases.
    String[] prefixSearch(String s, int limit) {
        ArrayList<String> results = new ArrayList<String>();
        prefixSearch(s, results, limit);
        return results.toArray(new String[0]);
    }


    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("peter");
        t.insert("piper");
        t.insert("picked");
        t.insert("a");
        t.insert("peck");
        t.insert("of");
        t.insert("pickled");
        t.insert("peppers");
        t.insert("pepppito");
        t.insert("pepi");
        t.insert("pik");

        String[] result1 = t.prefixSearch("pe", 10);
        String[] result2 = t.prefixSearch("pe.", 10);
        // result1 should be:
        // ["peck", "pepi", "peppers", "pepppito", "peter"]
        // result2 should contain the same elements with result1 but may be ordered arbitrarily
    }
}