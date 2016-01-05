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
    protected int daysInfected;
    protected int daysRecovering;
    protected boolean contagious;
    protected Virus virus;

    protected Location location;
    protected Field field;

    public States getState() {
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
        if (rand.nextDouble() >= new H1N1().getInfectionRate()) {
            this.state = States.Sick;
            this.virus = new H1N1();
        }
    }

    public boolean isInfected() {
        return state == States.Sick;
    }

    public void updateDaysInfected() {
        this.daysInfected++;
    }

    public void becomeContagious() {
        if (daysInfected >= new H1N1().getInfectionTimeSpan())
            state = States.Contagious;
    }

    public void beCured() {
        if (daysInfected >= new H1N1().getRecoveringTimeSpan()) {
            Random rand = Randomizer.getRandom();
            if (rand.nextDouble() >= new H1N1().getMortalityRate())
                state = States.Dead;
            else
                state = States.Healthy;
        }
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
        if (location != null)
            field.clear(location);
        this.location = location;
        field.place(this, location);
    }

    public Location getLocation() {
        return this.location;
    }

    public abstract void act();

    public boolean isContagious() {
        return contagious;
    }

    public Virus getVirus() {
        return virus;
    }
}//class LivingBeing
