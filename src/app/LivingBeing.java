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
public abstract class LivingBeing 
{
    States state;

    protected char speciesCode;

    public LivingBeing()
    {
        this.state = States.Contagious;
        this.speciesCode = this.getClass().getSimpleName().charAt(0);
    }
    
    public String toString()
    {
        String toReturn = "";
        
        if(this.state.equals(States.Healthy))
            toReturn += Simulation.ANSI_GREEN + "H" ;
        
        else if(this.state.equals(States.Sick))
            toReturn +=Simulation.ANSI_PURPLE +  "S"  ;
        
        else if(this.state.equals(States.Recovering))
            toReturn += Simulation.ANSI_BLUE + "R"  ;
        
        else if(this.state.equals(States.Contagious))
            toReturn += Simulation.ANSI_YELLOW + "C";
        
        else if(this.state.equals(States.Dead))
            toReturn += Simulation.ANSI_RED + "D" ;
        
        
        return toReturn + "_" +  this.speciesCode + Simulation.ANSI_RESET;
    }//toString()
    
}//class LivingBeing
