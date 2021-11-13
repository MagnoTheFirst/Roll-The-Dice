package ch.webe.rollthedice.webeproject.services;

import ch.webe.rollthedice.webeproject.model.Dice;
import ch.webe.rollthedice.webeproject.model.Session;
import ch.webe.rollthedice.webeproject.model.User;
import ch.webe.rollthedice.webeproject.repos.SessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SessionService {

    ArrayList<Session> sessions = new ArrayList<>();

    @Autowired
    public SessionService( ) {
        User demoUser1 = new User("alf", "Müller", "bla@gmail","test12345","magno");
        User demoUser2 = new User("Peter", "Grimm", "blub@gmail","12345test","magno");
        createNewSession(demoUser1);
        createNewSession(demoUser2);
    }


   public Session createNewSession(User user1){
        Session session = new Session(user1);
        sessions.add(session);
        return session;
    }

    void deleteSession(Session session){
        for(int i = 0; i < sessions.size(); i++){
            if(session.getSessionId() == sessions.get(i).getSessionId()){
                sessions.remove(i);
            }
        }
    }

    public Session getSession(UUID sessionId){
        Session session = null;
        for(int i = 0; i < sessions.size(); i++) {
            System.out.println(sessions.get(i).getSessionId());
            if(sessions.get(i).getSessionId().equals(sessionId)){
                session = sessions.get(i);
                System.out.println("--------------------"+ session.getSessionId());
            }
        }
        return session;
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
    public UUID searchSession(){
        int sessionsSize = sessions.size();
        for(int i = 0; i < sessionsSize; i++){
            if(sessions.get(i).isWaitingForParticipant()){
                return sessions.get(i).getSessionId();
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

    public ArrayList<Session> getSessions(){
        return this.sessions;
    }

}
