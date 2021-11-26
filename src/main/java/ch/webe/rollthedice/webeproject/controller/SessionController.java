package ch.webe.rollthedice.webeproject.controller;

import ch.webe.rollthedice.webeproject.model.Session;
import ch.webe.rollthedice.webeproject.model.User;
import ch.webe.rollthedice.webeproject.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    SessionController(SessionService sessionService){
        this.sessionService = sessionService;
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


}
