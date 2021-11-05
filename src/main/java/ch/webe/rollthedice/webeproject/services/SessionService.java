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

    Map<UUID, Session>sessions = new HashMap<>();
    ArrayList<Session> sessions2 = new ArrayList<>();

    @Autowired
    public SessionService( ) {

    }


    Session createNewSession(User user1){
        Session session = new Session(user1);
        sessions.put(session.getSessionId(), session);
        sessions2.add(session);
        return session;
    }

    void deleteSession(Session session){
        sessions.remove(session.getSessionId());
    }

    Session getSession(UUID sessionId){
        Session session = sessions.get(sessionId);
        return session;
    }

    //Diese Methode soll verwendet werden um der Session einen zweiten Spieler hinzuzufügen
    void editSession(UUID sessionId, User user){
        sessions.get(sessionId).setUser2(user);
    }

    void exitSession(UUID sessionId, User user){
        if(sessions.get(sessionId).getUser1() == user){
            sessions.get(sessionId).setUser1(null);
        }
        else{
            sessions.get(sessionId).setUser2(null);
        }
    }

    void searchSession(){

    }

}
