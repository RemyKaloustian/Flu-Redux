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
 * @author GNING Khadim
 */
public class Human extends LivingBeing
{

    private Field field;
    private boolean vaccinated;

    public Human(boolean vac,Location loc,Field f)
    {
        super(loc,f);
        field = f;
        vaccinated = vac;
    }

    public boolean isvaccinated() {
        return vaccinated;
    }

    @Override
    public void act() {

    }


//    @Override
//    public String toString()
//    {
//        return this.state + "_H";
//    }
}//class Human
