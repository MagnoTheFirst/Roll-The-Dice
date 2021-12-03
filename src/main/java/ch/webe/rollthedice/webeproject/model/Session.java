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
    Integer roundCounter;
    Score score;
    Integer player1_score;
    Integer player2_score;


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
