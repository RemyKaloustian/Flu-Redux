/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author Rémy Kaloustian
 */
public class Human extends LivingBeing
{


    private boolean vaccinated;

    public Human(boolean vac,Location loc,Field f)
    {
        super(loc,f);
        vaccinated = vac;
    }

    public boolean isvaccinated() {
        return vaccinated;
    }


//    @Override
//    public String toString()
//    {
//        return this.state + "_H";
//    }
}//class Human
