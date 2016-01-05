/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.Random;

/**
 * @author Rémy Kaloustian
 */
public abstract class LivingBeing {
    protected char speciesCode;
    protected States state;
    private int daysInfected;
    private int daysRecovering;
    private boolean isInfected;

    private  Location location;
    private  Field field;

    public States getState(){
        return state;
    }

    public LivingBeing(Location loc, Field f) {
        this.state = States.Healthy;
        this.speciesCode = this.getClass().getSimpleName().charAt(0);
        field = f;
        this.daysInfected = 0;
        setLocation(loc);
    }



    public void beInfected() {
        Random rand = Randomizer.getRandom();
        if (rand.nextDouble() >= 0.0){
            isInfected = true;
            this.state = States.Sick;
        }
    }

    public boolean isInfected() {
        return isInfected;
    }

    public void updateDaysInfected(){
        this.daysInfected++;
    }

    public boolean becomeContagious() {
        if (daysInfected >= 2)
            return true;
        //TODO : Random pour savoir si contagieux
        return true;

    }


    public String toString() {
        String toReturn = "";

        if (this.state.equals(States.Healthy))
            toReturn += "H";

        else if (this.state.equals(States.Sick))
            toReturn += "S";

        else if (this.state.equals(States.Recovering))
            toReturn += "R";

        else if (this.state.equals(States.Contagious))
            toReturn += "C";

        else if (this.state.equals(States.Dead))
            toReturn += "D";


        return toReturn + "_" + this.speciesCode;
    }//toString()

    public void setLocation(Location location) {
        if(location != null)
            field.clear(location);
        this.location = location;
        field.place(this,location);
    }

    public  Location getLocation(){
        return  this.location;
    }

    public abstract void act();
}//class LivingBeing
