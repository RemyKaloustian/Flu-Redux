package app;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 * A simple predator-prey simulator, based on a rectangular field containing
 * humans and chickens.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Simulator {
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
    // The probability that a human will be created in any given grid position.
    private static final double HUMAN_CREATION_PROBABILITY = 0.50;
    // The probability that a chicken will be created in any given grid position.
    private static final double CHICKEN_CREATION_PROBABILITY = 0.10;
    // The probability that a pig will be created in any given grid position.
    private static final double PIG_CREATION_PROBABILITY = 0.20;
    // The probability that a duck will be created in any given grid position.
    private static final double DUCK_CREATION_PROBABILITY = 0.10;

    // Lists of animals in the field. Separate lists are kept for ease of
    // iteration.
    private List<Human> humans;
    private List<LivingBeing> livings;

    private List<Chicken> chickens;
    private List<Pig> pigs;
    private List<Duck> ducks;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;

    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }

    /**
     * Create a simulation field with the given size.
     *
     * @param depth
     *            Depth of the field. Must be greater than zero.
     * @param width
     *            Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) {
        if (width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        humans = new ArrayList<>();
        livings = new ArrayList<>();
        chickens = new ArrayList<>();
        pigs=new ArrayList<>();
        ducks=new ArrayList<>();
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);
        view.setColor(Human.class, Color.ORANGE);
        view.setColor(Duck.class, Color.BLUE);
        view.setColor(Pig.class, Color.PINK);
        view.setColor(Chicken.class, Color.YELLOW);

        // Setup a valid starting point.
        reset();
    }

    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void runLongSimulation() {
        simulate(4000);
    }

    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     *
     * @param numSteps
     *            The number of steps to run for.
     */
    public void simulate(int numSteps) {
        for (int step = 1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep();
            try {
                view.showStatus(step, field);
                Thread.sleep(1000);
            }catch (Exception e){}
        }
    }

    /**
     * Run the simulation from its current state for a single step. Iterate over
     * the whole field updating the state of each fox and rabbit.
     */
    public void simulateOneStep() {
        step++;


        // Provide space for newborn humans.
        for (Iterator<LivingBeing> it = livings.iterator(); it.hasNext();) {
            LivingBeing living = it.next();
            living.act();
            if (living.getState().name().equalsIgnoreCase("Dead")) {
                it.remove();
            }
            else if (living.getState().name().equalsIgnoreCase("Sick")){
                living.updateDaysInfected();
            }
            else{
                living.beInfected();
            }
        }



        view.showStatus(step, field);
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        step = 0;
        humans.clear();
        chickens.clear();
        populate();

        // Show the starting state in the view.
        view.showStatus(step, field);
    }

    /**
     * Randomly populate the field with chickens and humans.
     */
    private void populate() {
        Random rand = Randomizer.getRandom();
        field.clear();
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                if (rand.nextDouble() <= HUMAN_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Human human = new Human(false,location,field);
                    humans.add(human);
                } else if (rand.nextDouble() <= DUCK_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Duck duck = new Duck(location,field);
                    ducks.add(duck);
                }
                else if (rand.nextDouble() <= CHICKEN_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Chicken chicken = new Chicken(location,field);
                    chickens.add(chicken);
                }
                else if (rand.nextDouble() <= PIG_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Pig pig = new Pig(location,field);
                    pigs.add(pig);
                }
                // else leave the location empty.
            }
        }
    }
}
