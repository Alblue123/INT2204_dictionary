package application.backCode;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class Dictionary {
    /** words getter. */
    public abstract ArrayList<Word> getWords();

    /** definition getter. */
    public abstract ArrayList<String> getTargetWords();

    /**
     * Inert new word.
     * @param wordTarget  the target word
     * @param wordExplain the definition of that word
     */
    public abstract boolean insert(String wordTarget, String wordExplain);

    /**
     * Delete a word.
     * @param wordTarget  that word
     */
    public abstract void delete(String wordTarget);

    /**
     * Find a word
     * @param wordTarget that word
     * @return that word
     */
    public abstract String search(String wordTarget);

    public abstract boolean edit(String target, String definition);

    public void init() throws SQLException {}

    public void updateFavorite(String target, boolean favorite) {
    }

    public boolean checkFavorite(String target) {
        return true;
    }

    public Set<String> getFavoriteWords() {
        return null;
    }
}
