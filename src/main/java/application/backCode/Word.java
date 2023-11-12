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

    /** target word getter. */
    public String getWordTarget() {
        return wordTarget;
    }

    /** definition getter. */
    public String getWordExplain() {
        return wordExplain;
    }

    /** target word setter. */
    public void setWordTarget(String wordTarget) {
        wordTarget = wordTarget.toLowerCase();
        this.wordTarget = wordTarget;
    }

    /** definition setter. */
    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

}
