/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.List;

/**
 * @author Rémy Kaloustian
 * @author GNING Khadim
 */
public class Human extends LivingBeing {

    private Field field;
    private boolean vaccinated;


    public Human(boolean vac, Location loc, Field f) {
        super(loc, f);
        field = f;
        vaccinated = vac;
    }

    public boolean isvaccinated() {
        return vaccinated;
    }

    @Override
    public void act() {
        if (this.getState() == States.Healthy) {
            List<Location> neighborhoods = field.adjacentLocations(getLocation());
            for (Location loc : neighborhoods) {
                LivingBeing neighbour = (LivingBeing) field.getObjectAt(loc);
                if (neighbour != null) {
                    if (neighbour.isContagious()) {
                        beInfected();
                        this.virus = neighbour.getVirus();
                    }
                }
            }
            Location newLocation = field.freeAdjacentLocation(this.getLocation());
            if (newLocation != null) {
                field.freeLocation(this.getLocation());
                setLocation(newLocation);
            }
        }
        if (this.getState() == States.Sick) {
            updateDaysInfected();
            becomeContagious();
            beCured();

        }

    }


//    @Override
//    public String toString()
//    {
//        return this.state + "_H";
//    }
}//class Human
