package ch.webe.rollthedice.webeproject.services;

import ch.webe.rollthedice.webeproject.model.AppUser;
import ch.webe.rollthedice.webeproject.model.Dice;
import ch.webe.rollthedice.webeproject.model.Session;
import ch.webe.rollthedice.webeproject.model.User;
import ch.webe.rollthedice.webeproject.repos.SessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SessionService {

    //TODO[] remove session from waiting sessions

    public ArrayList<Session> sessions = new ArrayList<>();
    ArrayList<Session> sessions2 = new ArrayList<>();
    Integer sessionsCounter = null;
    public ArrayList<Session> activeSessions = new ArrayList<>(); ;

    public ArrayList<Session> getActiveSessions(){
        return this.activeSessions;
    }

    @Autowired
    public SessionService( ) {
        User demoUser1 = new User("alf", "Müller", "bla@gmail","test12345","magno");
        User demoUser2 = new User("Peter", "Grimm", "blub@gmail","12345test","alfred");
        User demoUser3 = new User("Jessi", "lüthi", "pipapo@bluewin.ch","aaaaa11111bbbbb","jessiro");
        AppUser demoUser4 = new AppUser("Alejandro", "Laneri", "pipapo@bluewin.ch");
        AppUser demoUser5 = new AppUser("Angelo", "Merte", "papipo@bluewin.ch");

        createNewSession(demoUser1);
        createNewSession(demoUser2);

        sessions.get(1).setUser2(demoUser3);
    }


   public Session createNewSession(User user1){
        Session session = new Session(user1);
        sessions.add(session);
        return session;
    }

    public Session createNewSession1(AppUser user1){
        Session session = new Session(user1);
        sessions.add(session);
        return session;
    }

    void deleteSession(UUID sessionId){
        for(int i = 0; i < sessions.size(); i++){
            if(sessionId == sessions.get(i).getSessionId()){
                sessions.remove(i);
            }
        }
    }

    public Session getSession(UUID sessionId){
        Session session = null;
        for(int i = 0; i < sessions.size(); i++) {
            if(sessions.get(i).getSessionId().equals(sessionId)){
                session = sessions.get(i);
                this.sessionsCounter = i;
            }
        }
        return session;
    }

    public Session getActiveSession(UUID sessionId){
        Session session = null;
        for(int i = 0; i < activeSessions.size(); i++) {
            System.out.println(sessions.get(i).getSessionId());
            if(activeSessions.get(i).getSessionId().equals(sessionId)){
                session = activeSessions.get(i);
            }
        }
        return session;
    }

    public Integer getActiveSessionIndex(UUID sessionId){
        Integer index = null;
        for(int i = 0; i < activeSessions.size(); i++){
            if(activeSessions.get(i).getSessionId() == sessionId){
               index = i;
            }
        }
        return index;
    }

    public Integer getSessionIndex(){
        Integer counter = this.sessionsCounter;
        sessionsCounter = null;
        return counter;
    }
    //Diese Methode soll verwendet werden um der Session einen zweiten Spieler hinzuzufügen
    void editSession(UUID sessionId, User user){
        Session session = getSession(sessionId);
        session.setUser2(user);
    }

    void exitSession(UUID sessionId, User user){
        if(getSession(sessionId).getUser1() == user){
            getSession(sessionId).setUser1(null);
        }
        else if(getSession(sessionId).getUser2() == user){
            getSession(sessionId).setUser2(null);
        }
        else{
            System.out.println("no user found");
        }
    }

    //evt nicht ganz richtig richtig nachvollziehen ob nicht direkt null zurück gegeben wird.
    public Session searchSession(){
        int sessionsSize = sessions.size();
        for(int i = 0; i < sessionsSize; i++){
            if(sessions.get(i).isWaitingForParticipant()){
                return sessions.get(i);
            }
        }
        return null;
    }

    //evt nicht ganz richtig richtig nachvollziehen ob nicht direkt null zurück gegeben wird.
    public Session searchSession2(User user){
        int sessionsSize = sessions.size();
        for(int i = 0; i < sessionsSize; i++){
            if(sessions.get(i).isWaitingForParticipant()){
                sessions.get(i).setUser2(user);
                sessions.get(i).setWaitingForParticipant(false);
                return sessions.get(i);
            }
            else{
                return createNewSession(user);
            }
        }
        return null;
    }

    public Session searchSession3(AppUser user){
        int sessionsSize = sessions2.size();
        for(int i = 0; i < sessionsSize; i++){
            if(sessions2.get(i).isWaitingForParticipant()){
                sessions2.get(i).setAppUser2(user);
                sessions2.get(i).setWaitingForParticipant(false);
                return sessions2.get(i);
            }
            else{
                return createNewSession1(user);
            }
        }
        return null;
    }
    public ArrayList<Session> getSessions(){
        return this.sessions;
    }

    public List<Session> getSessions1(){
        return this.sessions;
    }

    public void setActiveSession(Session session){
        this.activeSessions.add(session);
    }

    public void removeActiveSession(UUID sessionId){
        this.activeSessions.remove(getActiveSessionIndex(sessionId));
    }

}
