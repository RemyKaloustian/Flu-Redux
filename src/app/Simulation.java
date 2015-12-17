/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.Scanner;

/**
 *
 * @author Rémy Kaloustian
 */
public class Simulation 
{
    private Grid grid;
    
    private int humans, pigs, ducks, chickens;
    
    public Simulation()
    {
        this.humans = 0;
        this.pigs = 0;
        this.ducks =0;
        this.chickens = 0;
                
        this.simulate();
        
    }//Simulation()
    
    public void simulate()
    {
        this.printWelcome();
        
        int size = this.enterGridSize();
        this.humans = this.enterHumans();
        if(this.humans < 100)
        {
            pigs = this.enterPigs();
            
            if(this.humans + this.pigs < 100)
            {
                ducks = this .enterDucks(); 
                if(this.humans + this.pigs + this.ducks < 100)
                    chickens = this.enterChickens();
            }
           
        }
       
        this.grid = new Grid(size, humans, pigs, ducks, chickens);
        
        
    }//simulate()

    
    public void printWelcome()
    {
        System.out.println("Welcome to this wonderful FLU SIMULATOR. You're gonna be asked to enter some input.\n "
                + "Please do not mess with the input and enter correct values.\n"
                + "(Anyway, since the developer is a nice guy, this program can deal with incorrect input). ");
    }//printWelcome()
    
    public int enterGridSize()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n Please enter a grid size (between 2 and 100):");
        int result = 0;
        
        try
        {
            result = sc.nextInt();   
        
            while((result < 2) || (result > 100))
            {
                System.out.println("Incorrect Input !"); 
                System.out.println("\n Please enter a grid size (between 2 and 100):");
                sc = new Scanner(System.in);
                result = sc.nextInt();

            }
        }
        catch(Exception e)
        {
            System.out.println("You think you're a hacker ? ");
            this.enterGridSize();
        }
        
        return result; 
                
    }//enterGridSize()
    
    public int enterHumans()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n Please enter the percentage of humans (between 0 and 100):");
        int result = 0;
        
        try
        {
            result = sc.nextInt();
        
            while((result < 0.0f) || (result > 100.0f))
            {
                System.out.println("Incorrect Input !"); 
                System.out.println("\n Please enter the percentage of humans (between 0 and 100):");
                sc = new Scanner(System.in);
                result = sc.nextInt();

            }
        }
        catch(Exception e)
        {
            System.out.println("You think you're a hacker ? ");
            this.enterHumans();
        }
        
        return result; 
    }//enterHumans()
    
    
    
    public int enterPigs()
    {
        Scanner sc = new Scanner(System.in);
        int allowed = 100 - this.humans;
        System.out.println("\n Please enter the percentage of pigs (between 0 and "+ allowed+"):");
        int result = 0;
        
        try
        {
            result = sc.nextInt();
        
            while((result < 0) || (result > 100) || (result > allowed))
            {
                System.out.println("Incorrect Input !"); 
                System.out.println("\n Please enter the percentage of pigs (between 0 and "+ allowed +"):");
                sc = new Scanner(System.in);
                result = sc.nextInt();

            }
        }
        catch(Exception e)
        {
            System.out.println("You think you're a hacker ? ");
            this.enterPigs();
        }
        
        return result; 
    }//enterPigs()
    
    
    public int enterDucks()
    {
        Scanner sc = new Scanner(System.in);
        int allowed = 100 - this.humans - this.pigs;
        System.out.println("\n Please enter the percentage of ducks (between 0 and "+ allowed+"):");
        int result = 0;
        
        try
        {
            result = sc.nextInt();
        
            while((result < 0) || (result > 100) || (result > allowed))
            {
                System.out.println("Incorrect Input !"); 
                System.out.println("\n Please enter the percentage of ducks (between 0 and "+ allowed +"):");
                sc = new Scanner(System.in);
                result = sc.nextInt();

            }
        }
        catch(Exception e)
        {
            System.out.println("You think you're a hacker ? ");
            this.enterDucks();
        }
        
        return result; 
    }//enterDucks()
    
    
    public int enterChickens()
    {
        Scanner sc = new Scanner(System.in);
        int allowed = 100 - this.humans - this.pigs- this.ducks;
        System.out.println("\n Please enter the percentage of chickens (between 0 and "+ allowed+"):");
        int result = 0;
        
        try
        {
            result = sc.nextInt();
        
            while((result < 0) || (result > 100) || (result > allowed))
            {
                System.out.println("Incorrect Input !"); 
                System.out.println("\n Please enter the percentage of chickens (between 0 and "+ allowed +"):");
                sc = new Scanner(System.in);
                result = sc.nextInt();

            }
        }
        catch(Exception e)
        {
            System.out.println("You think you're a hacker ? ");
            this.enterChickens();
        }
        
        return result; 
        
    }//enterChickens()  
    
    
}//class Simulation
