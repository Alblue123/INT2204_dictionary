package application.backCode;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private HashMap<Character, TrieNode> children = new HashMap<>();

    private boolean checkEndOfWord;

    private String content;

    /**
     * Constructor 1.
     */
    public TrieNode() {
        this.checkEndOfWord = false;
    }

    /** content getter. */
    public String getContent() {
        return content;
    }

    /** content setter. */
    public void setContent(String content) {
        this.content = content;
    }

    /** checkEndOfWord getter. */
    public boolean isCheckEndOfWord() {
        return checkEndOfWord;
    }

    /** checkEndOfWord setter. */
    public void setCheckEndOfWord(boolean checkEndOfWord) {
        this.checkEndOfWord = checkEndOfWord;
    }

    /** children getter. */
    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }

    /** children setter. */
    public void setChildren(HashMap<Character, TrieNode> children) {
        this.children = children;
    }

}
