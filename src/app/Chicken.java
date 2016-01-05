/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.view.Location;

import java.util.Random;

/**
 *
 * @author Rémy Kaloustian
 * @author Abdelkarim Andolerzak
 */
public class Chicken extends LivingBeing
{
    public Chicken(Location location, Field field)
    {
        super(location,field);
        Random rand = new Random();
        //Probability to be sick in the beggining
        if(rand.nextDouble()<=0.50) {
            this.virus = new H1N1();
            this.state= States.SICK;
        }
    }//Chicken()

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
                setDead();
        }
    }


}//class Chicken
