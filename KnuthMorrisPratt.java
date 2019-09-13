/**
 * KMP Algorithm - I wanted to learn it.
 * Feel free to use this code if you want.
 *
 * @Author Philipp Smponias
 */
public class KnuthMorrisPratt {
    private char[] word;
    private char[] prefix;
    private int[] prefixValues;
    private int prefixIndex = 0;
    private int wordIndex = 0;

    private boolean notFound;
    private int prefixLength;

    public KnuthMorrisPratt(String input) {
        this.word = input.toCharArray();
    }


    public void init(String prefix) {
        notFound = true;
        prefixLength = prefix.length();
        this.prefixValues = new int[prefixLength];
        this.prefix = prefix.toCharArray();
        createPrefixTable();
    }

    private void createPrefixTable() {
        prefixValues[0] = 0;
        for (int i = 1; i < prefix.length; i++) {
            if (prefix[i] == prefix[prefixValues[i - 1]])
                prefixValues[i] = prefixValues[i - 1] + 1;
            else if (prefix[i] == prefix[0])
                prefixValues[i] = 1;
            else prefixValues[i] = 0;
        }
    }


    private void checkCharacters() throws Exception {
        while (notFound) {
            if (prefix[prefixIndex] == word[wordIndex]) {
                increasePrefixcounter();
            } else missMatchedCharacters();

        }
    }

    private void increasePrefixcounter() {
        if (prefixIndex + 1 >= prefixLength) {
            notFound = false;
        } else {
            prefixIndex++;
            wordIndex++;
        }
    }

    private void missMatchedCharacters() throws Exception {

        if (prefixIndex - 1 >= 0) {
            prefixIndex = prefixValues[prefixIndex - 1];
        } else {
            checkIfMatchIsPossible();
            wordIndex++;
        }
    }

    private void checkIfMatchIsPossible() throws Exception {
        if (!(word.length - wordIndex > prefixLength)) {
            throw new NoSuchPrefixException();
        }
    }

    public int checkPrefix(String prefix) throws Exception {
        init(prefix);
        if (word == null)
            throw new NullPointerException("Your object needs a value, idiot.");
        if (prefix.length() < word.length) {
            checkCharacters();
        }

        //We want the beginning not the end
        return wordIndex - prefixIndex;
    }
}
