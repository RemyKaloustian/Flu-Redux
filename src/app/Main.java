/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.view.*;

/**
 *
 * @author Rémy Kaloustian
 * @author Abdelkarim Andolerzak
 */
public class Main
{
    
    public static void main(String args[])
    {
        //TODO: Appel futur de la vue.
        Simulator simu = new Simulator();
        HomeView home = new HomeView(simu);
        //simu.simulate(20);
    }//main()
    
}//class Main
