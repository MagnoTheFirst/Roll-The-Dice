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
        System.out.println("api/v1/diceValue - triggereds");
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
        System.out.println("api/v1/sessionDiceValue - triggereds");
        this.dice.roll_the_dice();
        Session session = sessionService.getActiveSession(sessionId);
        if(session.getScore().getUserTurn() == true){
            Score score = session.getScore();
            score.setScore_player1(this.dice.getDice_value() + 1);
            session.getScore().setUserTurn(false);
            session.setScore(score);
            sessionService.getActiveSessions().set(sessionService.getActiveSessionIndex(sessionId), session);
        }
        else{
            Score score = session.getScore();
            score.setScore_player2(this.dice.getDice_value() + 1);
            session.getScore().setUserTurn(true);
            session.setScore(score);
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


    //TODO[] optimize score counting
    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/sessionValues/{sessionId}")
    public ResponseEntity<Session> getDiceValueFromSession(@PathVariable UUID sessionId, Model model){
        System.out.println("api/v1/sessionDiceValue - triggereds");
        this.dice.roll_the_dice();
        Session session = sessionService.getActiveSession(sessionId);
        if(session.getScore().getUserTurn() == true){
            Score score = session.getScore();
            score.setScore_player1(this.dice.getDice_value() + 1);
            session.getScore().setUserTurn(false);
            session.setScore(score);
            sessionService.getActiveSessions().set(sessionService.getActiveSessionIndex(sessionId), session);
        }
        else{
            Score score = session.getScore();
            score.setScore_player2(this.dice.getDice_value() + 1);
            session.getScore().setUserTurn(true);
            session.setScore(score);
            sessionService.getActiveSessions().set(sessionService.getActiveSessionIndex(sessionId), session);
        }
        return new ResponseEntity<Session>(session, HttpStatus.OK);
    }

    //------------------------------------------------- Works fine ---------------------------------------------------

    //Everything works fine if the 1 is removed
    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/sessionValues1/{sessionId}/{email}")
    public ResponseEntity<Session> getDiceValueFromSessionAndSetUserTurn(@PathVariable UUID sessionId, @PathVariable String email, Model model){
        System.out.println("api/v1/sessionDiceValue - triggereds");
        this.dice.roll_the_dice();
        Session session = sessionService.getActiveSession(sessionId);
        session.setUserTurn(email);
        if(session.getScore().getUserTurn() == true){
            Score score = session.getScore();
            score.setScore_player1(this.dice.getDice_value() + 1);
            session.getScore().setUserTurn(false);
            session.setScore(score);
            sessionService.getActiveSessions().set(sessionService.getActiveSessionIndex(sessionId), session);
        }
        else{
            Score score = session.getScore();
            score.setScore_player2(this.dice.getDice_value() + 1);
            session.getScore().setUserTurn(true);
            session.setScore(score);
            sessionService.getActiveSessions().set(sessionService.getActiveSessionIndex(sessionId), session);
        }
        return new ResponseEntity<Session>(session, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/sessionValues/{sessionId}/{email}")
    public ResponseEntity<Session> getDiceValueFromSessionAndSetUserTurn1(@PathVariable UUID sessionId, @PathVariable String email, Model model){
        System.out.println("api/v1/sessionDiceValue - triggereds");
        this.dice.roll_the_dice();
        Session session = sessionService.getActiveSession(sessionId);

        if(session.getScore().getUserTurn() == true){
            Score score = session.getScore();
            score.setScore_player1(this.dice.getDice_value() + 1);
            session.getScore().setUserTurn(false);
            session.setScore(score);
            session.setUserTurn(session.getUser2().getEmail());
            sessionService.getActiveSessions().set(sessionService.getActiveSessionIndex(sessionId), session);
        }
        else{
            Score score = session.getScore();
            score.setScore_player2(this.dice.getDice_value() + 1);
            session.getScore().setUserTurn(true);
            session.setScore(score);
            session.setUserTurn(session.getUser1().getEmail());
            sessionService.getActiveSessions().set(sessionService.getActiveSessionIndex(sessionId), session);
        }
        return new ResponseEntity<Session>(session, HttpStatus.OK);
    }

}
