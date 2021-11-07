package ch.webe.rollthedice.webeproject.controller;

import ch.webe.rollthedice.webeproject.model.Session;
import ch.webe.rollthedice.webeproject.model.User;
import ch.webe.rollthedice.webeproject.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
public class SessionController {

    private final SessionService sessionService;

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
    public ResponseEntity<UUID> searchSession(){

        return new ResponseEntity<UUID>(sessionService.searchSession(), HttpStatus.OK);
    }

    //----------------------------------NOK-----------------------------------
    //TODO[] muss richtig gestellt werden
    //Diese Api wird nach jedem Spielzug angewählt um zu prüfen ob beide Spieler noch online sind und um den Spielstand auszuwerten
    //Hierfür muss die Session Id in der URL eingebaut werden.
    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/sessionInfo/{sessionId}")
    public String getSessionInformation(@PathVariable("sessionId") UUID sessionId)
    {
        return "GameSession";
    }






}