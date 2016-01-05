/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.People;


import app.viruses.H1N1;
import app.view.Randomizer;
import app.view.States;
import app.viruses.Virus;
import app.view.Field;
import app.view.Location;

import java.util.List;
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
        this.state = States.HEALTHY;
        this.speciesCode = this.getClass().getSimpleName().charAt(0);
        field = f;
        this.daysInfected = 0;
        setLocation(loc);
    }


    public void beInfected() {
        Random rand = Randomizer.getRandom();
        if (rand.nextDouble() >= new H1N1().getInfectionRate()) {
            this.state = States.SICK;
            this.virus = new H1N1();
        }
    }

    public boolean isInfected() {
        return state == States.SICK;
    }

    public void updateDaysInfected() {
        this.daysInfected++;
    }

    public void becomeContagious() {
        if (daysInfected >= new H1N1().getInfectionTimeSpan())
            state = States.CONTAGIOUS;
    }

    public void beCured() {
        if (daysInfected >= new H1N1().getRecoveringTimeSpan()) {
            Random rand = Randomizer.getRandom();
            if (rand.nextDouble() >= new H1N1().getMortalityRate())
                setDead();
            else
                state = States.HEALTHY;
        }
    }


    public String toString() {
        String toReturn = "";

        if (this.state.equals(States.HEALTHY))
            toReturn += "H";

        else if (this.state.equals(States.SICK))
            toReturn += "S";

        else if (this.state.equals(States.RECOVERING))
            toReturn += "R";

        else if (this.state.equals(States.CONTAGIOUS))
            toReturn += "C";

        else if (this.state.equals(States.DEAD))
            toReturn += "D";


        return toReturn + "_" + this.speciesCode;
    }//toString()

    public void setLocation(Location location) {
        if (location != null)
            field.clear(location);
        this.location = location;
        field.place(this, location);
    }

    /**
     * @author David J. Barnes and Michael KÃ¶lling
     */
    public void setDead() {
        this.state = States.DEAD;
        if (location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    public void neighbourContagious(){
        List<Location> neighborhoods = field.adjacentLocations(getLocation());
        for (Location loc : neighborhoods) {
            LivingBeing neighbour = (LivingBeing) field.getObjectAt(loc);
            if (neighbour != null) {
                if (neighbour.isContagious() && (this.getClass() == neighbour.getClass() || neighbourMayInfect(neighbour))){
                    infect();
                    this.virus = neighbour.getVirus();
                }
            }
        }
    }

    public boolean neighbourMayInfect(LivingBeing n){
        if((this.getClass() == Chicken.class && n.getClass() == Duck.class) ||(this.getClass() == Duck.class && n.getClass() == Chicken.class))
            return true;
        return  false;
    }

    public Location getLocation() {
        return this.location;
    }

    public abstract void act();

    public  abstract  void infect();

    public boolean isContagious() {
        return contagious;
    }

    public Virus getVirus() {
        return virus;
    }
}//class LivingBeing
