/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.viruses;

/**
 *
 * @author Rémy Kaloustian
 */
public abstract class Virus 
{
    private double infectionRate;
    private double mortalityRate;
    private int infectionTimeSpan;
    private int recoveringTimeSpan;


    public Virus(double infectionRate, int recoveringTimeSpan, int infectionTimeSpan, double mortalityRate) {
        this.infectionRate = infectionRate;
        this.recoveringTimeSpan = recoveringTimeSpan;
        this.infectionTimeSpan = infectionTimeSpan;
        this.mortalityRate = mortalityRate;
    }

    public double getInfectionRate() {
        return infectionRate;
    }

    public double getMortalityRate() {
        return mortalityRate;
    }

    public int getInfectionTimeSpan() {
        return infectionTimeSpan;
    }

    public int getRecoveringTimeSpan() {
        return recoveringTimeSpan;
    }
}//class Virus
