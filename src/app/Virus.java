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
public abstract class Virus 
{
    private float infectionRate;
    private float mortalityRate;
    private int infectionTimeSpan;
    private int recoveringTimeSpan;


    public Virus(float infectionRate, int recoveringTimeSpan, int infectionTimeSpan, float mortalityRate) {
        this.infectionRate = infectionRate;
        this.recoveringTimeSpan = recoveringTimeSpan;
        this.infectionTimeSpan = infectionTimeSpan;
        this.mortalityRate = mortalityRate;
    }
}//class Virus
