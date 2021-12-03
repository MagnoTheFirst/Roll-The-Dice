package ch.webe.rollthedice.webeproject.controller;

import ch.webe.rollthedice.webeproject.model.AppUser;
import ch.webe.rollthedice.webeproject.model.Session;
import ch.webe.rollthedice.webeproject.model.User;
import ch.webe.rollthedice.webeproject.services.AppUserService;
import ch.webe.rollthedice.webeproject.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class SessionController {

    private final SessionService sessionService;
    AppUserService appUserService;
    AppUser currentUser;
    ArrayList<Session> activeSessions;

    @Autowired
    SessionController(SessionService sessionService, AppUserService appUserService){

        this.sessionService = sessionService;
        this.appUserService = appUserService;
    }


    //---------------------------OK----------------------------------------

    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/sessions")
    public ArrayList<Session> getSessions()
    {
        return sessionService.getSessions();
    }


    //---------------------------OK----------------------------------------

    //Soll eine Session erstellen sie zur liste hinzufügen und die Session ID zurückgeben
    @CrossOrigin(origins = "*")
    @PostMapping("api/v1/newSession")
    public ResponseEntity<Session> createNewSession(@RequestBody User user){
        Session session = sessionService.createNewSession(user);
        return new ResponseEntity<Session>(session, HttpStatus.OK);
    }

    //---------------------------OK----------------------------------------
    //Kann ergänzt werden um einen User auf der DB zu erstellen
    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/createuser")
    public ResponseEntity<User> createUser()
    {
        User user = new User("ale", "laneri", "bla@gmail", "test12345", "magno");
        return new ResponseEntity<User>(user, HttpStatus.OK);

    }


    //---------------------------OK----------------------------------------

    //soll angewählt werden um eine Session zu suchen falls null returnt wird dann wähle create session an
    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/searchSession")
    public ResponseEntity<Session> searchSession(){

        return new ResponseEntity<Session>(sessionService.searchSession(), HttpStatus.OK);
    }

    //soll angewählt werden um eine Session zu suchen falls null returnt wird dann wähle create session an
    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/sessionId/{sessionId}")
    public ResponseEntity<Session> getSession(@PathVariable UUID sessionId){

        return new ResponseEntity<Session>(sessionService.getSession(sessionId), HttpStatus.OK);
    }
    //----------------------------------NOK-----------------------------------

    //muss angepasst werden
    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/{sessionId}/scoreUser1")
    public ResponseEntity<Session> getScoreUser1(@PathVariable UUID sessionId){

        return new ResponseEntity<Session>(sessionService.getSession(sessionId), HttpStatus.OK);
    }

    //muss angepasst werden.
 //   @CrossOrigin(origins = "*")
   // @GetMapping("api/v1/{sessionId}/scoreUser2")
    //public ResponseEntity<UUID> getScoreUser2(@PathVariable UUID sessionId){

      //  return new ResponseEntity<UUID>(sessionService.searchSession(), HttpStatus.OK);
    //}

    @CrossOrigin(origins = "*")
    @PostMapping("api/v1/searchSession2")
    public ResponseEntity<Session> searchSession2(@RequestBody User user){
        Session session = sessionService.searchSession2(user);
        return new ResponseEntity<Session>(session, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("api/v1/searchSession3")
    public ResponseEntity<Session> searchSession3(@RequestBody AppUser user){
        Session session = sessionService.searchSession3(user);
        return new ResponseEntity<Session>(session, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("api/v1/joinSession/{email}/{sessionId}")
    public HttpStatus joinSession(@PathVariable UUID sessionId, @PathVariable String email){
        System.out.println(sessionService.getSessionIndex());
        AppUser appUser = appUserService.getByEmail(email).getBody();
        System.out.println(appUser.getEmail());
        User user = new User(appUser.getFirstname(), appUser.getLastname(), appUser.getEmail());
        Session session = sessionService.getSession(sessionId);
        session.setUser2(user);
        sessionService.sessions.set(sessionService.getSessionIndex(), session);
        return HttpStatus.OK;
    }


    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/joinSession1/{email}/{sessionId}")
    public HttpStatus joinSession1(@PathVariable UUID sessionId, @PathVariable String email){
        System.out.println(sessionService.getSessionIndex());
        this.currentUser = appUserService.getByEmail(email).getBody();
        AppUser appUser = appUserService.getByEmail(email).getBody();
        System.out.println("************************" + appUser.getEmail());
        User user = new User(appUser.getFirstname(), appUser.getLastname(), appUser.getEmail());
        Session session = sessionService.getSession(sessionId);
        session.setUser2(user);
        sessionService.activeSessions.add(session);

        //TODO[] remove session from waiting sessions
        sessionService.sessions.remove(sessionService.getSessionIndex());
        return HttpStatus.OK;
    }

    //Soll eine Session erstellen sie zur liste hinzufügen und die Session ID zurückgeben
    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/newSession/{email}")
    public ResponseEntity<Session> createNewSession1(@PathVariable String email){
        System.out.println("newSession" + email);
        AppUser appUser = appUserService.getByEmail(email).getBody();
        this.currentUser = appUser;
        User user = new User(appUser.getFirstname(), appUser.getLastname(), appUser.getEmail());

        Session session = sessionService.createNewSession(user);
        System.out.println("newSession" + session.getSessionId());
        return new ResponseEntity<Session>(session, HttpStatus.OK);
    }

}
