/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.Random;

/**
 *
 * @author Rémy Kaloustian
 */
public class Grid 
{
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    private LivingBeing[][] grid;
    
    int humans;
    int pigs;
    int ducks;
    int chickens;
    
    
    
    public Grid(int size, int amountOfHumans, int amountOfPigs, int amountOfDucks, int amountOfChickens)
    {
        grid = new LivingBeing[size][size];
        
        System.out.println("Building the grid, stand by.");
        
        System.out.println("Ok, so we have a grid of  " + grid.length + " by " + grid.length + " which is " + grid.length*grid.length +" tiles");
        
        
        this.setLivingBeings(this.actualNumber(amountOfHumans), this.actualNumber(amountOfPigs), 
                this.actualNumber(amountOfDucks), this.actualNumber(amountOfChickens));
        
        this.addLivingBeings();
        
        
        this.printReminder();
        
        System.out.println(this.toString());
        
                
        
        
    }//Grid()
    
    
    public void setLivingBeings(int humans, int pigs, int ducks, int chickens)
    {
        this.humans = humans;
        this.pigs = pigs;
        this.ducks = ducks;
        this.chickens = chickens;
        
        System.out.println("BEFORE : ");
        System.out.println("Your grid contains : \n " + humans + " humans \n " + pigs + " pigs \n" 
                + ducks + " ducks \n " + chickens + " chickens \n" );
        
        this.palliateRound(humans, pigs, ducks, chickens);
         System.out.println("AFTER : ");
        System.out.println("Your grid contains : \n " + this.humans + " humans \n " + this.pigs + " pigs \n" 
                + this.ducks + " ducks \n " + this.chickens + " chickens \n" );
    }//putLivingBeings()
    
    public void palliateRound(int humans, int pigs, int ducks, int chickens)
    {
        int sum = this.humans + this.pigs + this.ducks + this.chickens;
        System.out.println("Sum before: " + sum);
        
        int tiles = grid.length * grid.length;
        System.out.println("Tiles " + tiles);
        
        while(sum < tiles)
        {
            System.out.println("In while");
            Random Generator=  new Random();
            int random = Generator.nextInt(4);
            if(random == 0)
                this.humans++;
            else if(random == 1)
                this.pigs++;
            else if(random == 2)
                this.ducks++;
            else if(random == 3)
                this.chickens++;
            
            sum++;
        }  
    }//palliateRound()
    
    public int actualNumber(float percentage)
    {
        return (int) Math.floor(this.grid.length * this.grid.length * (percentage/100));
    }//actualNmber()
    
    public void addLivingBeings()
    {
        int itemsAdded = 0;
        
        for (int i = 0; i < grid.length; i++) 
        {
            for (int j = 0; j < grid.length; j++) 
            {
                if(itemsAdded < this.humans)
                {
                    grid[i][j] = new Human();
                }
                
                else if(itemsAdded < this.humans + this.pigs)
                {
                    grid[i][j] = new Pig();
                }
                
                else if(itemsAdded < this.humans +  this.pigs + this.ducks )
                {
                    grid[i][j] = new Duck();
                }
                
                else if(itemsAdded < this.humans + this.pigs + this.ducks + this.chickens)
                {
                    grid[i][j] = new Chicken();
                }
                
                ++itemsAdded;
            }
        }
    }//addLivingBeings()
    
    public void printReminder()
    {
        System.out.println(  "/***************************************************\n"
                + "A tile contains two letters : State + Species. \n"
                + "H = Healthy, S = Sick, R = Recovering, C= Contagious, D = Dead \n"
                + "H = Human, P = Pig, D = Duck, C = Chicken \n"
                +           "/****************************************************\n"        
        );
    }//printReminder()
    
    
    /*
    * Used to display the grid of living beings
    */
    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder("");
        
        for (int i = 0; i < grid.length; i++) 
        {
            for (int j = 0; j < grid.length ; j++) 
            {
                result.append(" | " + grid[i][j].toString());
            }
            result.append("\n");
        }
        
        return result.toString();
    }//toString()
}//class Grid
