package ch.webe.rollthedice.webeproject.controller;

import ch.webe.rollthedice.webeproject.model.Dice;
import ch.webe.rollthedice.webeproject.model.Session;
import ch.webe.rollthedice.webeproject.services.DiceService;
import ch.webe.rollthedice.webeproject.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class GameController {

    private final SessionService sessionService;

    private final DiceService diceService;

    private Dice dice;

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
        return "roll-the-dice";
    }

//------------------------------------Neuer Abschnitt bis hier funktioniert alles ------------------------

    //Soll eine Session erstellen sie zur liste hinzufügen und die Session ID zurückgeben
    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/newSession")
    public String createNewSession(){
        return "roll-the-dice";
    }

    //soll angewählt werden um eine Session zu suchen falls null returnt wird dann wähle create session an
    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/searchSession")
    public String searchSession(){
        return "roll-the-dice";
    }

    //Diese Api wird nach jedem Spielzug angewählt um zu prüfen ob beide Spieler noch online sind und um den Spielstand auszuwerten
    //Hierfür muss die Session Id in der URL eingebaut werden.
    @CrossOrigin(origins = "*")
    @GetMapping("api/v1/sessionInfo/{sessionId}")
    public String getSessionInformation(@PathVariable("sessionId") UUID sessionId)
    {
        //return Session with give id
        return "roll-the-dice";

    }








}
