package ch.webe.rollthedice.webeproject.controller;

import ch.webe.rollthedice.webeproject.model.Dice;
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

}
