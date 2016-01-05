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
public class Pig extends LivingBeing
{
    Field field;

    public Pig(Location location, Field f)
    {
        super(location,f);
        field = f;
        //this.speciesCode = 'Pig';
    }

    @Override
    public void act() {

    }
//    @Override
//    public String toString()
//    {
//        return this.state + "_P";
//    }
}//class Pig
