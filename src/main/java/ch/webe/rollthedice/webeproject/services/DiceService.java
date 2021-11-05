package ch.webe.rollthedice.webeproject.services;

import ch.webe.rollthedice.webeproject.model.Dice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiceService {

    public Dice dice;

    @Autowired
    public DiceService( ) {
        this.dice = new Dice();
        this.dice.dice_value = dice.roll_the_dice();
    }



    public Integer getDiceValue(){
        return this.dice.getDice_value();
    }

    public Dice getDice(){
        this.dice.dice_value = dice.roll_the_dice();
        return this.dice;
    }

}
