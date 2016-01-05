/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.People;

import app.view.Field;
import app.view.Location;
import app.view.Randomizer;
import app.view.States;
import app.viruses.H1N1;

import java.util.Random;

/**
 * @author Rémy Kaloustian
 */
public class Duck extends LivingBeing {

    public Duck(Location loc, Field f) {
        super(loc, f);
        Random rand = new Random();
        //Probability to be sick in the beggining
        if(rand.nextDouble()<=0.50) {
            this.virus = new H1N1();
            this.state= States.SICK;
        }
}

    @Override
    public void act() {
        if (this.getState() == States.SICK) {
            updateDaysInfected();
            becomeContagious();
            beCured();

        }

    }

    public void beCured() {
        if (daysInfected >= new H1N1().getRecoveringTimeSpan()) {
            Random rand = Randomizer.getRandom();
            if (rand.nextDouble() >= new H1N1().getMortalityRate())
                state = States.DEAD;
        }
    }

}//class Duck
