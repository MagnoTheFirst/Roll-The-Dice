package ch.webe.rollthedice.webeproject.model;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class Session {

    UUID sessionId;
    AppUser appUser1;
    AppUser appUser2;
    User user1;
    User user2;
    boolean waitingForParticipant;
    String userTurn; //TODO[] optimize for cleancode

    Score score;
    Integer player1_score;
    Integer player2_score;

    Integer roundCounter;
    String winnerRound = "waiting for winner";
    String winnerMatch = "waiting for winner";

    public String getWinnerRound() {
        return winnerRound;
    }

    // Winner per round for different modi
    public void setWinnerRound(String email) {
        this.winnerRound = winnerRound;
    }

    public String getWinnerMatch() {
        return winnerMatch;
    }

    public void CheckWhoIsTheWinnerOfThisRound(){
        if(score.getScore_player1() < score.getScore_player2()){
            this.winnerRound = "The winner of this round is " +user1.getEmail();
        }
        else if(score.getScore_player1() > score.getScore_player2()){
            this.winnerRound = "The winner of this round is " +user2.getEmail();
        }
        else if(score.getScore_player1() == null || score.getScore_player2() == null || this.winnerRound == null){
            this.winnerRound = "seams that there was some problem";
        }
        else{
            this.winnerRound = "its a draw";
        }
    }

    public void checkIfMatchFinished(){
        if(roundCounter == 5) {
            if (player1_score < player2_score) {
                this.winnerMatch = "The winner of this Match is " +user1.getEmail();
            } else if (player1_score > player2_score) {
                this.winnerMatch = "The winner of this round is " +user2.getEmail();
            }
            else if(player1_score == null || player2_score == null || this.winnerMatch == null){
                this.winnerRound = "seams that there was some problem";
            }else {
                this.winnerMatch = "its a draw. Sorry no winner, try to do it better next time";
            }
        }
    }

    //After 5 Rounds check who has the higher score and set Winners email
    public void setWinnerMatch(String winnerMatch) {
        this.winnerMatch = winnerMatch;
    }

    public Session(User user1) {
        this.sessionId = UUID.randomUUID();
        this.user1 = user1;
        this.user2 = null;
        this.waitingForParticipant = true;
        this.userTurn = user1.getEmail();
        this.roundCounter = 0;
        this.score = new Score();
        this.player1_score = 0;
        this.player2_score = 0;
        this.winnerMatch = null;
        this.winnerRound = null;
    }

    public Integer getPlayer1_score() {
        return player1_score;
    }

    public void setPlayer1_score(Integer player1_score) {
        this.player1_score = player1_score;
    }

    public Integer getPlayer2_score() {
        return player2_score;
    }

    public void setPlayer2_score(Integer player2_score) {
        this.player2_score = player2_score;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public AppUser getAppUser1() {
        return appUser1;
    }

    public void setAppUser1(AppUser appUser1) {
        this.appUser1 = appUser1;
    }

    public AppUser getAppUser2() {
        return appUser2;
    }

    public void setAppUser2(AppUser appUser2) {
        this.appUser2 = appUser2;
    }

    public Session(AppUser user1) {
        this.sessionId = UUID.randomUUID();
        this.appUser1 = user1;
        this.appUser2 = null;
        this.waitingForParticipant = true;
        this.userTurn = user1.getEmail();
        this.roundCounter = 0;
        this.score = new Score();
        this.player1_score = 1;
        this.player2_score = 1;
    }

    public Score getScore(){
        return this.score;
    }

    public void setScore(Score score){
        this.score = score;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.waitingForParticipant = false;
        this.user2 = user2;
    }

    public boolean isWaitingForParticipant() {
        return waitingForParticipant;
    }

    public void setWaitingForParticipant(boolean waitingForParticipant) {
        this.waitingForParticipant = waitingForParticipant;
    }

    public String getUserTurn() {
        return userTurn;
    }

    public void setUserTurn(String userTurn) {
        this.userTurn = userTurn;
    }

    public Integer getRoundCounter() {
        return roundCounter;
    }

    public void setRoundCounter(Integer roundCounter) {
        this.roundCounter = roundCounter;
    }


}
