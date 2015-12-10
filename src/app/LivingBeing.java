/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 * @author Rémy Kaloustian
 */
public abstract class LivingBeing {
    protected char speciesCode;
    States state;
    private int daysInfected;
    private int daysRecovering;


    public LivingBeing() {
        this.state = States.Healthy;
        this.speciesCode = this.getClass().getSimpleName().charAt(0);
    }

    public void beInfected() {
        //TODO : Random to know if infected or no
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
            toReturn += Simulation.ANSI_GREEN + "H";

        else if (this.state.equals(States.Sick))
            toReturn += Simulation.ANSI_PURPLE + "S";

        else if (this.state.equals(States.Recovering))
            toReturn += Simulation.ANSI_BLUE + "R";

        else if (this.state.equals(States.Contagious))
            toReturn += Simulation.ANSI_YELLOW + "C";

        else if (this.state.equals(States.Dead))
            toReturn += Simulation.ANSI_RED + "D";


        return toReturn + "_" + this.speciesCode + Simulation.ANSI_RESET;
    }//toString()

}//class LivingBeing
