package application.backCode;

public class Word {
    private String wordTarget;
    private String wordExplain;
    private String wordPronounce;

    /**
     * Constructor 1.
     * @param wordTarget    the word
     * @param wordExplain   explanation of that word
     * @param wordPronounce pronunciation of that word
     */
    public Word(String wordTarget, String wordExplain, String wordPronounce) {
        wordTarget = wordTarget.toLowerCase();

        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        this.wordPronounce = wordPronounce;
    }

    /**
     * Constructor 2.
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

    /** pronunciation getter. */
    public String getWordPronounce() {
        return wordPronounce;
    }

    /** pronunciation setter. */
    public void setWordPronounce(String wordPronounce) {
        this.wordPronounce = wordPronounce;
    }
}
