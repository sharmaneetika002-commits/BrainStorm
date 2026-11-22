
package scoring;

import models.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Leaderboard {

    public List<Player> getRankings(List<Player> players) {

        List<Player> rankings = new ArrayList<>();

        if (players == null) {
            return rankings;
        }

        for (Player player : players) {
            if (player != null) {
                rankings.add(player);
            }
        }

        Collections.sort(rankings,
                Comparator.comparingInt(Player::getScore).reversed());

        return rankings;
    }

    public List<Player> getWinners(List<Player> players) {

        List<Player> rankings = getRankings(players);
        List<Player> winners = new ArrayList<>();

        if (rankings.isEmpty()) {
            return winners;
        }

        int highestScore = rankings.get(0).getScore();

        for (Player player : rankings) {
            if (player.getScore() == highestScore) {
                winners.add(player);
            } else {
                break;
            }
        }

        return winners;
    }

    public Player getWinner(List<Player> players) {

        List<Player> winners = getWinners(players);

        return winners.isEmpty() ? null : winners.get(0);
    }

    public String displayLeaderboard(List<Player> players) {

        List<Player> rankings = getRankings(players);
        StringBuilder result = new StringBuilder();

        result.append("\n========== LEADERBOARD ==========\n");

        if (rankings.isEmpty()) {
            result.append("No players available.\n");
            return result.toString();
        }

        int rank = 1;

        for (int i = 0; i < rankings.size(); i++) {

            Player player = rankings.get(i);

            if (i > 0 &&
                    player.getScore() != rankings.get(i - 1).getScore()) {
                rank = i + 1;
            }

            result.append(rank)
                    .append(". ")
                    .append(player.getName())
                    .append(" - ")
                    .append(player.getScore())
                    .append(" points\n");
        }

        result.append("=================================\n");

        return result.toString();
    }
}
