package application.backCode;

public class Word {
    private String wordTarget;
    private String wordExplain;

    /**
     * Constructor 1.
     * @param wordTarget   the word
     * @param wordExplain  explanation of that word
     */
    public Word(String wordTarget, String wordExplain) {
        wordTarget = wordTarget.toLowerCase();

        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }

}
