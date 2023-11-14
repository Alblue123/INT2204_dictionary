package application.Game;

import java.util.HashMap;
import java.util.Map;

public class Scorer {
    private static final Map<Character, Integer> POINTS_MAP = createPointsMap();

    private static Map<Character, Integer> createPointsMap() {
        Map<Character, Integer> pointsMap = new HashMap<>();
        pointsMap.put('A', 1);
        pointsMap.put('E', 1);
        pointsMap.put('I', 1);
        pointsMap.put('O', 1);
        pointsMap.put('N', 2);
        pointsMap.put('R', 2);
        pointsMap.put('S', 2);
        pointsMap.put('T', 2);
        pointsMap.put('D', 3);
        pointsMap.put('G', 3);
        pointsMap.put('L', 3);
        pointsMap.put('B', 4);
        pointsMap.put('H', 4);
        pointsMap.put('P', 4);
        pointsMap.put('M', 4);
        pointsMap.put('U', 4);
        pointsMap.put('Y', 4);
        pointsMap.put('C', 5);
        pointsMap.put('F', 5);
        pointsMap.put('V', 5);
        pointsMap.put('W', 5);
        pointsMap.put('K', 6);
        pointsMap.put('J', 7);
        pointsMap.put('X', 7);
        pointsMap.put('Q', 8);
        pointsMap.put('Z', 8);
        return pointsMap;
    }

    public int calculateScore(String word) {
        int score = 0;

        for (char letter : word.toUpperCase().toCharArray()) {
            if (POINTS_MAP.containsKey(letter)) {
                score += POINTS_MAP.get(letter);
            }
        }

        return score;
    }
}
