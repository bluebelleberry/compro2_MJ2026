package com.mjaquino.service;

import com.mjaquino.model.GameMove;
import com.mjaquino.model.GameSession;
import com.mjaquino.model.Paper;
import com.mjaquino.model.Player;
import com.mjaquino.model.Rock;
import com.mjaquino.model.Scissors;
// handles the game
public class GameService {

    public GameMove createMove(String input) {
        // convert user input into a move object
        if (input == null) return null;

        return switch (input.trim()) {
            case "0" -> new Rock();
            case "1" -> new Paper();
            case "2" -> new Scissors();
            default -> null;
        };
    }

    public String buildRoundDetails(GameSession session, Player player1, Player player2, GameMove move1, GameMove move2) {
        // play round and show what the player chose
        String roundResult = session.playRound(move1, move2);
        String scoreBoard = session.getScoreBoard();

        return """
                %s
                %s chose %s
                %s chose %s
                %s
                """.formatted(
                roundResult,
                player1.getUsername(), move1.getMoveName(),
                player2.getUsername(), move2.getMoveName(),
                scoreBoard
        );
    }
}