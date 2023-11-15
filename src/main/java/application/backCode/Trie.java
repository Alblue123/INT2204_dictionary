package application.backCode;

import java.util.ArrayList;
import java.util.Comparator;

public class Trie {
    private static TrieNode root = new TrieNode();

    /**
     * insert new word in to the structure.
     * @param word the target
     */
    public static void add(String word) {
        TrieNode cur = root;

        for (int i = 0; i < word.length(); ++i) {
            char x = word.charAt(i);

            if (cur.getChildren().get(x) == null) {
                cur.getChildren().put(x, new TrieNode());
            }

            cur = cur.getChildren().get(x);
        }

        cur.setCheckEndOfWord(true);
    }

    /**
     * get all words with curNode as super.
     * @param searchList list of searching
     * @param curNode    the current node
     * @param target     the current word
     */
    private static void search(ArrayList<String> searchList, TrieNode curNode, String target) {
        if (curNode.isCheckEndOfWord()) {
            searchList.add(target);
        }

        for (char index : curNode.getChildren().keySet()) {
            if (curNode.getChildren().get(index) != null) {
                search(searchList, curNode.getChildren().get(index), target + index);
            }
        }
    }

    /**
     * search a word from the structure with such prefix.
     * @param prefix the target
     */
    public static ArrayList<String> searchPrefix(String prefix) {
        ArrayList<String> searchList = new ArrayList<>();
        TrieNode cur = root;

        for (int i = 0; i < prefix.length(); ++i) {
            char x = prefix.charAt(i);

            if (cur.getChildren().get(x) == null) {
                return searchList;
            }

            cur = cur.getChildren().get(x);
        }

        search(searchList, cur, prefix);

        return searchList;
    }

    /**
     * delete target word from trie.
     * @param target the target word
     * @return true or false
     */
    public static boolean erase(String target) {
        int length = target.length();
        TrieNode cur = root;

        for (int i = 0; i < length; ++i) {
            char x = target.charAt(i);

            if (cur.getChildren().get(x) == null) {
                return false;
            }
            cur = cur.getChildren().get(x);
        }

        if (!cur.isCheckEndOfWord()) {
            return false;
        }

        cur.setCheckEndOfWord(false);
        return true;
    }

    /**
     * update the definition of the word.
     * @param target  the word you want
     * @param meaning new definition
     */
    public static boolean update(String target, String meaning) {
        int length = target.length();
        TrieNode cur = root;

        for (int i = 0; i < length; ++i) {
            char x = target.charAt(i);

            if (cur.getChildren().get(x) == null) {
                return false;
            }
            cur = cur.getChildren().get(x);
        }

        if (!cur.isCheckEndOfWord()) {
            return false;
        }

        cur.setContent(meaning);
        return true;
    }
}
