/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.Random;

/**
 *
 * @author Rémy Kaloustian
 */
public class Chicken extends LivingBeing
{
    public Chicken(Location location, Field field)
    {
        super(location,field);
        Random rand = new Random();
        if(rand.nextDouble()<=0.80) {
            this.virus = new H1N1();
            this.state= States.Sick;
        }
    }//Chicken()

    @Override
    public void act() {
        if (this.getState() == States.Sick) {
            updateDaysInfected();
            becomeContagious();
            beCured();

        }

    }

    //    @Override
//    public String toString()
//    {
//        return this.state + "_C";
//    }
}//class Chicken
