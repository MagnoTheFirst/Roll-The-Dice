package ch.webe.rollthedice.webeproject.controller;

import ch.webe.rollthedice.webeproject.model.Session;
import ch.webe.rollthedice.webeproject.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class SessionObjectController {
    private final SessionService sessionService;

    public SessionObjectController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    //TODO[] muss richtig gestellt werden
    //Diese Api wird nach jedem Spielzug angewählt um zu prüfen ob beide Spieler noch online sind und um den Spielstand auszuwerten
    //Hierfür muss die Session Id in der URL eingebaut werden.
    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/{sessionId}")
    public ResponseEntity<Session> getSessionInformation(@PathVariable UUID sessionId)
    {
        Session session = sessionService.getSession(sessionId);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ session.getSessionId());
        return new ResponseEntity<>(session, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/session/{sessionId}")
    public ResponseEntity<Session> getSessionSide(@PathVariable UUID sessionId)
    {
        Session session = sessionService.getSession(sessionId);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ session.getSessionId());
        return new ResponseEntity<>(session, HttpStatus.OK);
    }


    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/session23/{sessionId}")
    public String getHTML(@PathVariable UUID sessionId, Model model)
    {
        return "rollTheDice";
    }

}
