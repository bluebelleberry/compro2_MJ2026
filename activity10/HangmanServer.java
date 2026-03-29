import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class HangmanServer{
    public static void main(String[] args){
        int port=8000;

        try(ServerSocket server=new ServerSocket(port)){

            System.out.println("Waiting for client to connect...");
            Socket client=server.accept();
            System.out.println("Client connected.");

            PrintWriter out=new PrintWriter(client.getOutputStream(),true);
            BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));

            String[] wordBank=loadWordsFromFile("words.txt");

            out.println("WELCOME TO HANGMAN GAME");
            out.println("1. Sign In");
            out.println("2. Sign Up");
            out.println("Enter choice:");
            String option=in.readLine();

            Player player=null;

            if(option.equals("1")){

                out.println("Enter username:");
                String username=in.readLine();

                out.println("Enter password:");
                String password=in.readLine();

                if(validateUser(username,password)){
                    player=new Player(username,password);
                    out.println("Login successful!");
                }else{
                    out.println("Invalid username or password.");
                    out.println("END");
                    client.close();
                    return;
                }

            }else if(option.equals("2")){

                out.println("Create username:");
                String username=in.readLine();

                if(userExists(username)){
                    out.println("Username already exists.");
                    out.println("END");
                    client.close();
                    return;
                }

                out.println("Create password:");
                String password=in.readLine();

                player=new Player(username,password);
                saveUser(player);

                out.println("Account created successfully!");

            }else{
                out.println("Invalid choice.");
                out.println("END");
                client.close();
                return;
            }

            boolean playAgain=true;

            while(playAgain){

                out.println("HANGMAN GAME IS READY");
                int roundScore=playGame(wordBank,in,out);

                player.addScore(roundScore);

                saveScore(player.getUsername(),roundScore,player.getScore());

                out.println("Round finished.");
                out.println("Current total score: "+player.getScore());
                out.println("Do you want to play again? (y/n)");

                String choice=in.readLine();

                while(!choice.equalsIgnoreCase("y")&&!choice.equalsIgnoreCase("n")){
                    out.println("Enter y or n only:");
                    choice=in.readLine();
                }

                playAgain=choice.equalsIgnoreCase("y");
            }

            out.println("LEADERBOARD");
            sendLeaderboard(out);
            out.println("END");

            in.close();
            out.close();
            client.close();

        }catch(IOException e){
            System.out.println("Server error: "+e.getMessage());
        }
    }

    public static int playGame(String[] wordBank,BufferedReader in,PrintWriter out)throws IOException{

        String word=selectRandomWord(wordBank).toLowerCase();
        char[] display=initializeHiddenWord(word);
        char[] guessedLetters=new char[26];

        int incorrect=0;
        int score=0;
        int guessedIndex=0;
        int maxIncorrect=5;
        int maxScore=5;

        while(!isWordFullyGuessed(display)&&incorrect<maxIncorrect){

            out.println("Word: "+new String(display));
            out.println("Guess a letter:");

            String guessInput=in.readLine().toLowerCase();

            if(guessInput.length()!=1){
                out.println("Invalid input. Enter one letter only.");
                continue;
            }

            if(!Character.isLetter(guessInput.charAt(0))){
                out.println("Invalid input. Letters only please");
                continue;
            }

            char guess=guessInput.charAt(0);

            if(letterAlreadyGuessed(guess,guessedLetters,guessedIndex,word)){
                if(isGuessCorrect(word,guess)){
                    out.println(guess+" is already in the word.");
                }else{
                    out.println(guess+" was already guessed but not in the word.");
                }
                continue;
            }

            guessedLetters[guessedIndex]=guess;
            guessedIndex++;

            boolean correctGuess=isGuessCorrect(word,guess);

            if(correctGuess){
                updateHiddenWord(word,display,guess);
                score=awardPointForCorrectLetter(true,score);
                out.println(guess+" is in the word.");
            }else{
                incorrect++;
                score=awardPointForCorrectLetter(false,score);
                out.println(guess+" is not in the word.");
            }

            out.println("Score: "+score+"/"+maxScore);
            out.println("Incorrect guesses: "+incorrect+"/"+maxIncorrect);
        }

        if(isWordFullyGuessed(display)){
            out.println("Congratulations! You guessed the word, yehey!! "+word);
        }else{
            out.println("GAME OVER. The word was "+word);
        }

        return score;
    }

    public static String selectRandomWord(String[] wordBank){
        int randomIndex=(int)(Math.random()*wordBank.length);
        return wordBank[randomIndex];
    }

    public static char[] initializeHiddenWord(String word){
        char[] display=new char[word.length()];
        for(int i=0;i<word.length();i++){
            display[i]='*';
        }
        return display;
    }

    public static boolean letterAlreadyGuessed(char guess,char[] guessedLetters,int count,String word){
        for(int i=0;i<count;i++){
            if(guessedLetters[i]==guess){
                return true;
            }
        }
        return false;
    }

    public static boolean isGuessCorrect(String word,char guess){
        for(int i=0;i<word.length();i++){
            if(word.charAt(i)==guess){
                return true;
            }
        }
        return false;
    }

    public static void updateHiddenWord(String word,char[] display,char guess){
        for(int i=0;i<word.length();i++){
            if(word.charAt(i)==guess){
                display[i]=guess;
            }
        }
    }

    public static boolean isWordFullyGuessed(char[] display){
        for(int i=0;i<display.length;i++){
            if(display[i]=='*'){
                return false;
            }
        }
        return true;
    }

    public static int awardPointForCorrectLetter(boolean correctGuess,int currentScore){
        if(correctGuess){
            currentScore=currentScore+1;
        }else{
            currentScore=currentScore-1;
            if(currentScore<0){
                currentScore=0;
            }
        }
        return currentScore;
    }

    public static String[] loadWordsFromFile(String filename){

        List<String> words=new ArrayList<>();

        try(BufferedReader br=new BufferedReader(new FileReader(filename))){

            String line;

            while((line=br.readLine())!=null){
                words.add(line.trim());
            }

        }catch(IOException e){
            System.out.println("Error reading words file.");
        }

        return words.toArray(new String[0]);
    }

    public static boolean userExists(String username){

        try(BufferedReader br=new BufferedReader(new FileReader("users.json"))){

            String line;

            while((line=br.readLine())!=null){
                String savedUsername=getJsonValue(line,"username");
                if(username.equals(savedUsername)){
                    return true;
                }
            }

        }catch(IOException e){
            return false;
        }

        return false;
    }

    public static boolean validateUser(String username,String password){

        try(BufferedReader br=new BufferedReader(new FileReader("users.json"))){

            String line;

            while((line=br.readLine())!=null){
                String savedUsername=getJsonValue(line,"username");
                String savedPassword=getJsonValue(line,"password");

                if(username.equals(savedUsername)&&password.equals(savedPassword)){
                    return true;
                }
            }

        }catch(IOException e){
            System.out.println("Error reading users file.");
        }

        return false;
    }

    public static void saveUser(Player player){

        try(BufferedWriter bw=new BufferedWriter(new FileWriter("users.json",true))){
            bw.write("{\"username\":\""+player.getUsername()+"\",\"password\":\""+player.getPassword()+"\"}");
            bw.newLine();
        }catch(IOException e){
            System.out.println("Error saving user.");
        }
    }

    public static void saveScore(String username,int roundScore,int totalScore){

        try(BufferedWriter bw=new BufferedWriter(new FileWriter("scores.json",true))){
            bw.write("{\"username\":\""+username+"\",\"roundScore\":"+roundScore+",\"totalScore\":"+totalScore+"}");
            bw.newLine();
        }catch(IOException e){
            System.out.println("Error saving score.");
        }
    }

    public static void sendLeaderboard(PrintWriter out){

        String[] players=new String[100];
        int[] totals=new int[100];
        int count=0;

        try(BufferedReader br=new BufferedReader(new FileReader("scores.json"))){

            String line;

            while((line=br.readLine())!=null){

                String username=getJsonValue(line,"username");
                String totalScoreText=getJsonNumber(line,"totalScore");

                if(username==null||totalScoreText==null){
                    continue;
                }

                int totalScore=Integer.parseInt(totalScoreText);

                players[count]=username;
                totals[count]=totalScore;
                count++;
            }

        }catch(IOException e){
            out.println("No scores yet.");
            return;
        }

        for(int i=0;i<count-1;i++){
            for(int j=i+1;j<count;j++){
                if(totals[j]>totals[i]){

                    int tempScore=totals[i];
                    totals[i]=totals[j];
                    totals[j]=tempScore;

                    String tempName=players[i];
                    players[i]=players[j];
                    players[j]=tempName;
                }
            }
        }

        if(count==0){
            out.println("No scores yet.");
            return;
        }

        for(int i=0;i<count&&i<10;i++){
            out.println(players[i]+" - "+totals[i]+" points");
        }
    }

    public static String getJsonValue(String jsonLine,String key){

        String pattern="\""+key+"\":\"";
        int start=jsonLine.indexOf(pattern);

        if(start==-1){
            return null;
        }

        start=start+pattern.length();
        int end=jsonLine.indexOf("\"",start);

        if(end==-1){
            return null;
        }

        return jsonLine.substring(start,end);
    }

    public static String getJsonNumber(String jsonLine,String key){

        String pattern="\""+key+"\":";
        int start=jsonLine.indexOf(pattern);

        if(start==-1){
            return null;
        }

        start=start+pattern.length();
        int endComma=jsonLine.indexOf(",",start);
        int endBrace=jsonLine.indexOf("}",start);

        int end;

        if(endComma==-1){
            end=endBrace;
        }else if(endBrace==-1){
            end=endComma;
        }else{
            end=Math.min(endComma,endBrace);
        }

        if(end==-1){
            return null;
        }

        return jsonLine.substring(start,end).trim();
    }
}