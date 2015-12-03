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
    //The different states a living being can represent
    static final String healthy = "Healthy";
    static final String sick = "Sick";
    static final String recovering = "Recovering";
    static final String dead = "Dead";  
    static final String contagious = "Contagious";
    
    protected char speciesCode;
    
    protected String state ; //the state of a living being
    
    public LivingBeing()
    {
        this.state = LivingBeing.contagious;
        this.speciesCode = this.getClass().getSimpleName().charAt(0);
    }
    
    public String toString()
    {
        String toReturn = "";
        
        if(this.state.equals(LivingBeing.healthy))
            toReturn += Simulation.ANSI_GREEN + "H" ;
        
        else if(this.state.equals(LivingBeing.sick))
            toReturn +=Simulation.ANSI_PURPLE +  "S"  ;
        
        else if(this.state.equals(LivingBeing.recovering))
            toReturn += Simulation.ANSI_BLUE + "R"  ;
        
        else if(this.state.equals(LivingBeing.contagious))
            toReturn += Simulation.ANSI_YELLOW + "C";
        
        else if(this.state.equals(LivingBeing.dead))
            toReturn += Simulation.ANSI_RED + "D" ;
        
        
        return toReturn + "_" +  this.speciesCode + Simulation.ANSI_RESET;
    }//toString()
    
}//class LivingBeing
