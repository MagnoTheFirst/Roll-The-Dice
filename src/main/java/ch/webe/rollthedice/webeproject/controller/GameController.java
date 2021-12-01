package ch.webe.rollthedice.webeproject.controller;

import ch.webe.rollthedice.webeproject.model.Dice;
import ch.webe.rollthedice.webeproject.model.Score;
import ch.webe.rollthedice.webeproject.model.Session;
import ch.webe.rollthedice.webeproject.model.User;
import ch.webe.rollthedice.webeproject.services.DiceService;
import ch.webe.rollthedice.webeproject.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class GameController {

    private final SessionService sessionService;

    private final DiceService diceService;

    private Dice dice;

    Session currentSession;

    public GameController(DiceService diceService, SessionService sessionService) {

        this.sessionService = sessionService;
        this.diceService = diceService;
        this.dice  = new Dice();
    }

    @GetMapping("api/v1/get-dice-value")
    public String rollTheDice(Model model){
        model.addAttribute("dice", diceService.getDice());
        return "dice-value";
    }


    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/diceValue")
    public ResponseEntity<Dice> getDiceValue(Model model){
        this.dice.roll_the_dice();
        return new ResponseEntity<Dice>(this.dice, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/rollTheDice")
    public String rollTheDice(){
        return "rollTheDice";
    }

    @GetMapping("/api/v1/session/currentSession")
    public ResponseEntity<Session> getCurrentSession(){
        Session current = this.currentSession;
        System.out.println(current.getUser2().getFirstname() + " getCurrentSession");
        return new ResponseEntity<Session>(current, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/rollTheDice/{sessionId}")
    public String rollTheDice2(@PathVariable UUID sessionId, Model model){
        model.addAttribute("session", sessionService.getSession(sessionId));
        this.currentSession = sessionService.getSession(sessionId);
        System.out.println("++++++++++++++++"+ sessionId + "rollTheDice2");
        sessionService.getSession(sessionId);
        return new String("rollTheDice");
    }
//------------------------------------Neuer Abschnitt bis hier funktioniert alles ------------------------


    //TODO[] optimize score counting
    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/sessionDiceValue/{sessionId}")
    public ResponseEntity<Dice> setDiceValueToSession(@PathVariable UUID sessionId, Model model){
        this.dice.roll_the_dice();
        Session session = sessionService.getActiveSession(sessionId);
        if(session.getScore().getUserTurn() == true){
            Score score = session.getScore();
            score.setScore_player1(dice.getDice_value());
            session.setScore(score);
            session.getScore().setUserTurn(false);
            sessionService.getActiveSessions().set(sessionService.getActiveSessionIndex(sessionId), session);
        }
        else{
            Score score = session.getScore();
            score.setScore_player2(dice.getDice_value());
            session.setScore(score);
            session.getScore().setUserTurn(true);
            sessionService.getActiveSessions().set(sessionService.getActiveSessionIndex(sessionId), session);
        }
        return new ResponseEntity<Dice>(this.dice, HttpStatus.OK);
    }

    //TODO[] optimize score counting
    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/activeSession/{sessionId}")
    public ResponseEntity<Session> activeSession(@PathVariable UUID sessionId, Model model){
        Session session = sessionService.getActiveSession(sessionId);
        return new ResponseEntity<Session>(session, HttpStatus.OK);
    }



}
