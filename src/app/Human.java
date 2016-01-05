/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.view.Location;

import java.util.List;
import java.util.Random;

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
        if (this.getState() == States.HEALTHY) {
            neighbourContagious();
            setHumanDestination();
        }
        if (this.getState() == States.SICK) {
            updateDaysInfected();
            becomeContagious();
            beCured();

        }

    }

    private void setHumanDestination(){
        Location newLocation = field.freeAdjacentLocation(this.getLocation());
        if (newLocation != null) {
            field.clear(this.getLocation());
            setLocation(newLocation);
        }
    }

    private void neighbourContagious(){
        List<Location> neighborhoods = field.adjacentLocations(getLocation());
        for (Location loc : neighborhoods) {
            LivingBeing neighbour = (LivingBeing) field.getObjectAt(loc);
            if (neighbour != null) {
                if (neighbour.isContagious()) {
                    infect();
                    this.virus = neighbour.getVirus();
                }
            }
        }
    }

    private void infect(){
        if(isvaccinated()){
            Random rand = new Random();
            if(rand.nextDouble()>=0.50) {
                beInfected();
            }
        }
    }

}//class Human
