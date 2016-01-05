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
public class Chicken extends LivingBeing
{
    public Chicken(Location location, Field field)
    {
        super(location,field);
        //this.speciesCode = this.getClass().getSimpleName();
    }//Chicken()

    @Override
    public void act() {

    }

    //    @Override
//    public String toString()
//    {
//        return this.state + "_C";
//    }
}//class Chicken
