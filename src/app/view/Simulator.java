package app.view;

import app.People.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A simple predator-prey simulator, based on a rectangular field containing
 * humans and chickens.
 *
 * @author David J. Barnes and Michael Kölling
 * @author Abdelkarim Andolerzak
 * @version 2011.07.31
 */
public class Simulator implements Runnable {
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 7;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 7;
    // The probability that a human will be created in any given grid position.
    private static final double HUMAN_CREATION_PROBABILITY = 0.1;
    // The probability that a chicken will be created in any given grid position.
    private static final double CHICKEN_CREATION_PROBABILITY = 0.30;
    // The probability that a pig will be created in any given grid position.
    private static final double PIG_CREATION_PROBABILITY = 0.30;
    // The probability that a duck will be created in any given grid position.
    private static final double DUCK_CREATION_PROBABILITY = 0.10;

    private int speedStep;
    private int neighbourhood;

    // Lists of animals in the field. Separate lists are kept for ease of
    // iteration.
    private List<LivingBeing> livings;

    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private final int step = 10;
    // A graphical views of the simulation.
    private List<SimulatorView> views;

    /**
     * Construct a simulation field with default size.
     */
    public Simulator(int neighbourhood, int speed) {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH, neighbourhood, speed);
    }

    /**
     * Create a simulation field with the given size.
     *
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width, int neighbourhood, int speed) {
        if (width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        livings = new ArrayList<>();
        field = new Field(depth, width);
        this.neighbourhood = neighbourhood;
        this.speedStep = speed;

        // Create a views of the state of each location in the field.
        views = new ArrayList<SimulatorView>();
        SimulatorView grid = new GridView(depth, width);
        grid.setColor(Human.class, Color.BLUE);
        grid.setColor(Duck.class, Color.YELLOW);
        grid.setColor(Pig.class, Color.PINK);
        grid.setColor(Chicken.class, Color.RED);
        views.add(grid);

        //Adding the view with the graphs
        SimulatorView graph = new GraphView(500, 80, 500);
        graph.setColor(Human.class, Color.BLUE);
        graph.setColor(Duck.class, Color.YELLOW);
        graph.setColor(Pig.class, Color.PINK);
        graph.setColor(Chicken.class, Color.RED);
        views.add(graph);

        // Setup a valid starting point.
        reset();
        new Thread(this).start();
    }


    /**
     * Run the simulation from its current state for a single step. Iterate over
     * the whole field updating the state of each fox and rabbit.
     */
    public void simulateOneStep() {
        // Provide space for newborn humans.
        for (Iterator<LivingBeing> it = livings.iterator(); it.hasNext(); ) {
            LivingBeing living = it.next();
            living.act();
            if (living.getState() == States.DEAD) {
                it.remove();
            }
        }
        updateViews();
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
                    Human human = new Human(location, field, neighbourhood);
                    livings.add(human);
                } else if (rand.nextDouble() <= DUCK_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Duck duck = new Duck(location, field, neighbourhood);
                    livings.add(duck);
                } else if (rand.nextDouble() <= CHICKEN_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Chicken chicken = new Chicken(location, field, neighbourhood);
                    livings.add(chicken);
                } else if (rand.nextDouble() <= PIG_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Pig pig = new Pig(location, field, neighbourhood);
                    livings.add(pig);
                }
                // else leave the location empty.
            }
        }
    }


    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        livings.clear();
        for (SimulatorView view : views) {
            view.reset();
        }

        populate();
        updateViews();
    }

    /**
     * Update all existing views.
     */
    private void updateViews() {
        for (SimulatorView view : views) {
            view.showStatus(step, field);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i <= step; i++) {
            try {
                simulateOneStep();
                Thread.sleep(speedStep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
