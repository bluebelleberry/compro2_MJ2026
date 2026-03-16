import java.util.Scanner;

public class HangmanGame {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // the words to guess
        
        String[] players = new String[50];
        int[] scores = new int[50];
        int playerCount = 0;
        int round = 1;
        boolean morePlayers;

        do {
            String playerName = getPlayerName(input, round); // ask the user to enter their name
            int score = playGame(wordBank, 5, 5, input); // one full game

            players[playerCount] = playerName;
            scores[playerCount] = score;
            playerCount++;

            round++;
            morePlayers = anotherPlayer(input);

        } while (round <= 50 && morePlayers); // a round or play is up to 50 as long as the user said y

        // the leaderboard
        for (int i = 0; i < playerCount - 1; i++) {
            for (int j = i + 1; j < playerCount; j++) {
                if (scores[j] > scores[i]) {

                    int temporaryScore = scores[i];
                    scores[i] = scores[j];
                    scores[j] = temporaryScore;

                    String temporaryName = players[i];
                    players[i] = players[j];
                    players[j] = temporaryName;
                }
            }
        }
        // display the leaderboard
        System.out.println("\n==== LEADERBOARD ====");
        for (int i = 0; i < playerCount; i++) {
            System.out.println(players[i] + " - " + scores[i] + " points");
        }
        input.close();
    }

    // ask the user to enter their name
    public static String getPlayerName(Scanner input, int round) {
        System.out.print("Player Name: ");
        return input.nextLine();
    }

    // ask the next player if he wants to play
    public static boolean anotherPlayer(Scanner input) {
        System.out.print("Another Player? Enter y or n: ");
        String choice = input.nextLine().toLowerCase();

        // validating the choice for another game
        while (choice.length() != 1 || !Character.isLetter(choice.charAt(0)) || (!choice.equals("y") && !choice.equals("n"))) {
            // ask the user to input again their answer
            System.out.print("Invalid input. Please enter y or n only: ");
            choice = input.nextLine().toLowerCase();
        }

        System.out.print("\n");
        return choice.equals("y");
    }

    // Runs one full game and returns final score
    public static int playGame(String[] wordBank, int maxIncorrect, int maxScore, Scanner input) {

        String word = selectRandomWord(wordBank).toLowerCase();// choose random word from our word bank
        char[] display = initializeHiddenWord(word); // hides each letter of the word
        char[] guessedLetters = new char[26];

        int incorrect = 0;
        int score = 0;
        int guessedIndex = 0;

        /**
         * the game continue as long as the player has not reach maximum
         * incorrect guess and has not been fully guessed
         */
        while (!isWordFullyGuessed(display) && incorrect < maxIncorrect) {

            System.out.print("\nEnter a letter in word " + new String(display) + " > ");
            String guessInput = input.next().toLowerCase();
            // validating the input guess
            if (guessInput.length() != 1) {
                System.out.println("Invalid input Enter one letter lang po.");
                continue;
            }

            if (!Character.isLetter(guessInput.charAt(0))) {
                System.out.println("Invalid input. Letters only po.");
                continue;
            }

            char guess = guessInput.charAt(0);

            if (letterAlreadyGuessed(guess, guessedLetters, guessedIndex, word)) {
                continue;
            }

            updateGuessedLetters(guess, guessedLetters, guessedIndex);
            guessedIndex++;

            // checks if guessed letter exist in the word
            boolean correctGuess = isGuessCorrect(word, guess);

            if (correctGuess) {
                updateHiddenWord(word, display, guess);// reveal the correct guess letter
                score = awardPointForCorrectLetter(true, score);// add 1 point
                System.out.println(guess + " is in the word");
            } else {
                incorrect++;
                score = awardPointForCorrectLetter(false, score);// deduct 1 point
                System.out.println(guess + " is not in the word");
            }

        }
        //shows if the user completed the guess or if its game over
        if (isWordFullyGuessed(display)) {
            System.out.println("\nCongratulations! You guessed the word " + word + "\n");
        } else {
            System.out.println("\nGAME OVER\n\nThe word was " + word + "\n");
        }

        // track scores and incorrect guess
        System.out.println("\nScore: " + score + "/" + maxScore);
        System.out.println("Incorrect guesses: " + incorrect + "/" + maxIncorrect);

        return score;
    }

    // select one random word in our word bank
    public static String selectRandomWord(String[] wordBank) {
        int randomIndex = (int) (Math.random() * wordBank.length);
        return wordBank[randomIndex];
    }

    // hide each letter of the random word using "*"
    public static char[] initializeHiddenWord(String word) {
        char[] display = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            display[i] = '*';
        }
        return display;
    }

    // check if letter already been guessed/kung naulit
    public static boolean letterAlreadyGuessed(char guess, char[] guessedLetters, int count, String word) {
        for (int i = 0; i < count; i++) {
            if (guessedLetters[i] == guess) {
                if (isGuessCorrect(word, guess)) {
                    System.out.println(guess + " is already in the word");
                } else {
                    System.out.println(guess + " was already guessed but not in the word");
                }
                return true;
            }
        }
        return false;
    }

    // it updates the guessed letter in every correct guess
    public static void updateGuessedLetters(char guess, char[] guessedLetters, int index) {
        guessedLetters[index] = guess;
    }

    // check if the letter is in the word
    public static boolean isGuessCorrect(String word, char guess) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {// compares the guessed letter with each letter in the word
                return true;
            }
        }
        return false;
    }

    // reveal correct letters
    public static void updateHiddenWord(String word, char[] display, char guess) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                display[i] = guess; // replacing "*" with correct letter guess
            }
        }
    }

    // check if word fully guessed
    public static boolean isWordFullyGuessed(char[] display) {
        for (int i = 0; i < display.length; i++) {
            if (display[i] == '*') {
                return false;
            }
        }
        return true;
    }

    public static int awardPointForCorrectLetter(boolean correctGuess, int currentScore) {
        if (correctGuess) {
            currentScore = currentScore + 1;// if guess is correct plus 1 to the score
        } else {
            currentScore = currentScore - 1;// if aincorrect guess - 1
            if (currentScore < 0) {
                currentScore = 0;// prevent negative scores
            }
        }
        return currentScore;
    }
}