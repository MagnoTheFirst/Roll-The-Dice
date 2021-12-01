package ch.webe.rollthedice.webeproject.model;

public class Score {

    public Integer score_player1;
    public Integer score_player2;
    boolean userTurn = true;

    public Score() {
        this.score_player1 = 0;
        this.score_player2 = 0;

    }

    public boolean getUserTurn(){return this.userTurn;}

    public void setUserTurn(boolean userTurn){this.userTurn = userTurn;}

    public Integer getScore_player1() {
        return score_player1;
    }

    public void setScore_player1(Integer score_player1) {
        this.score_player1 = score_player1;
    }

    public Integer getScore_player2() {
        return score_player2;
    }

    public void setScore_player2(Integer score_player2) {
        this.score_player2 = score_player2;
    }
}
