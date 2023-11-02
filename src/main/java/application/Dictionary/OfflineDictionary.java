package application.Dictionary;

import application.backCode.Dictionary;
import application.backCode.Trie;
import application.backCode.Word;

import java.util.ArrayList;
import java.util.List;

public class OfflineDictionary extends Dictionary {
    private static ArrayList<Word> words = new ArrayList<>();

    @Override
    public ArrayList<Word> getWords() {
        return words;
    }

    @Override
    public ArrayList<String> getTargetWords() {
        ArrayList<String> definedWords = new ArrayList<>();
        for (Word word : words) {
            String tar = word.getWordTarget();
            definedWords.add(tar);
        }
        return definedWords;
    }

    @Override
    public void insert(String target, String explain) {
        for (Word word : words) {
            if (word.getWordTarget().equals(target)) {
                return;
            }
        }

        Word word = new Word(target, explain, "");
        words.add(word);
        Trie.add(target);
    }

    @Override
    public void delete(String target) {
        for (Word word : words) {
            if (word.getWordTarget().equals(target)) {
                words.remove(word);
                Trie.erase(target);
                break;
            }
        }
    }

    @Override
    public String search(String wordTarget) {
        for (Word word : words) {
            if (word.getWordTarget().equals(wordTarget)) {
                return word.getWordExplain();
            }
        }
        return "Error: Word not found";
    }

    /**
     * update a word.
     * @param target     the current word
     * @param definition the new definition
     */
    public void edit(String target, String definition) {
        for (Word word : words) {
            if (word.getWordTarget().equals(target)) {
                word.setWordExplain(definition);
                Trie.update(target, definition);
                break;
            }
        }
    }
}
