package ch.webe.rollthedice.webeproject.model;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class Session {

    UUID sessionId;
    User user1;
    User user2;
    boolean waitingForParticipant;
    boolean user1Turn;
    Integer roundCounter;

    public Session(User user1) {
        this.sessionId = UUID.randomUUID();
        this.user1 = user1;
        this.user2 = null;
        this.waitingForParticipant = true;
        this.user1Turn = true;
        this.roundCounter = 0;
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
