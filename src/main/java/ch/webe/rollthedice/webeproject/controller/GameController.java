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
    @GetMapping("api/v1/activeSession/{sessionId}")
    public ResponseEntity<Session> activeSession(@PathVariable UUID sessionId, Model model){
        Session session = sessionService.getActiveSession(sessionId);
        return new ResponseEntity<Session>(session, HttpStatus.OK);
    }

    //------------------------------------------------- Works fine ---------------------------------------------------

    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/sessionValues/{sessionId}/{email}")
    public ResponseEntity<Session> getDiceValueFromSessionAndSetUserTurn1(@PathVariable UUID sessionId, @PathVariable String email, Model model){
        //Modified at 15:38
        Dice dice = new Dice();
        dice.roll_the_dice();

        Session session = sessionService.getActiveSession(sessionId);

        Integer roundCounter = session.getRoundCounter();


        if(session.getScore().getUserTurn() == true){
            Score score = session.getScore();
            score.setScore_player1(dice.getDice_value() + 1);

            session.getScore().setUserTurn(false);
            session.setScore(score);
            session.setUserTurn(session.getUser2().getEmail());

            //For highscore view
            Integer playerScore1 = session.getPlayer1_score();
            playerScore1 += dice.getDice_value() + 1;
            session.setPlayer1_score(playerScore1);

            sessionService.getActiveSessions().set(sessionService.getActiveSessionIndex(sessionId), session);
        }
        else{
            Score score = session.getScore();
            score.setScore_player2(dice.getDice_value() + 1);

            session.getScore().setUserTurn(true);
            session.setScore(score);
            session.setUserTurn(session.getUser1().getEmail());

            //For highscore view
            Integer playerScore2 = session.getPlayer2_score();
            playerScore2 += dice.getDice_value() + 1;
            session.setPlayer2_score(playerScore2);

            session.setRoundCounter(roundCounter + 1);
            sessionService.getActiveSessions().set(sessionService.getActiveSessionIndex(sessionId), session);
        }
        return new ResponseEntity<Session>(session, HttpStatus.OK);
    }

    @GetMapping("api/v1/waitingForUser")
    public String getWaiting(Model model){
        return "waiting-for-second-player";
    }
}
