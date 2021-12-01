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
    boolean user1Turn;
    Integer roundCounter;
    Score score;

    public Session(User user1) {
        this.sessionId = UUID.randomUUID();
        this.user1 = user1;
        this.user2 = null;
        this.waitingForParticipant = true;
        this.user1Turn = true;
        this.roundCounter = 0;
        this.score = new Score();
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
        this.user1Turn = true;
        this.roundCounter = 0;
        this.score = new Score();
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

    public boolean isUser1Turn() {
        return user1Turn;
    }

    public void setUser1Turn(boolean user1Turn) {
        this.user1Turn = user1Turn;
    }

    public Integer getRoundCounter() {
        return roundCounter;
    }

    public void setRoundCounter(Integer roundCounter) {
        this.roundCounter = roundCounter;
    }


}
