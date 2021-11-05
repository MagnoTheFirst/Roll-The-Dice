package ch.webe.rollthedice.webeproject.model;


import java.util.Random;

public class Dice {

    public Integer dice_value;
    public Integer number_of_used_dice;


    //Number have to be in range 0-2
    public Dice(Integer number_of_used_dice) {
        number_of_used_dice = number_of_used_dice;
    }

    public Dice() {
        this.number_of_used_dice = 1;
        dice_value = roll_the_dice();

    }

    public Integer roll_the_dice(){
        Random random = new Random();

        switch (number_of_used_dice){
            case 0:
                dice_value = random.nextInt(6);
                return dice_value+1;
            case 1:
                dice_value = random.nextInt(6);
                return dice_value+1;
            case 2:
                dice_value = random.nextInt(12);
                return dice_value+1;

            case 3:
                dice_value = random.nextInt(18);
                return dice_value+1;
            case 4:
                dice_value = random.nextInt(24);
                return dice_value+1;
        }
        dice_value = random.nextInt(6);
        return dice_value;
    }

    public int getNumber_of_used_dices() {
        return number_of_used_dice;
    }

    public void setNumber_of_used_dices(int number_of_used_dices) {
        this.number_of_used_dice = number_of_used_dices;
    }

    public Integer getDice_value() {
        return dice_value;
    }

    public void setDice_value(int dice_value) {
        this.dice_value = dice_value;
    }
}
