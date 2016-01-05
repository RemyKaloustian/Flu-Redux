/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.People;

import app.view.Field;
import app.view.States;
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


    public Human(Location loc, Field f,int neigh) {
        super(loc, f,neigh);
        field = f;
        Random rand = new Random();
        if (rand.nextDouble() >= 0.70) {
            vaccinated = true;
        } else
            vaccinated = false;
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


    public void infect(){
        if(isvaccinated()){
            Random rand = new Random();
            if(rand.nextDouble()>=0.50) {
                beInfected();
            }
        }
    }

}//class Human
