
package scoring;

import models.Player;
import models.Question;

public class ScoreManager {

    private static final int POINTS_PER_CORRECT_ANSWER = 10;

    public boolean processAnswer(Player player, Question question, String answer) {

        if (player == null || question == null || answer == null
                || answer.trim().isEmpty()) {
            return false;
        }

        if (question.checkAnswer(answer.trim())) {
            player.addScore(POINTS_PER_CORRECT_ANSWER);
            return true;
        }

        return false;
    }

    public int getScore(Player player) {
        return player == null ? 0 : player.getScore();
    }

    public void resetScore(Player player) {
        if (player != null) {
            player.setScore(0);
        }
    }
}
