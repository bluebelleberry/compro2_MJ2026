public class Player{

    private String username;
    private String password;
    private int score;

    public Player(String username,String password){
        this.username=username;
        this.password=password;
        this.score=0;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score=score;
    }

    public void addScore(int points){
        score+=points;
        if(score<0){
            score=0;
        }
    }
}