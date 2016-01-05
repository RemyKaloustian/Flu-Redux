package app.view;

import app.People.*;

import javax.management.timer.TimerMBean;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Simulator {
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

    // Lists of animals in the field. Separate lists are kept for ease of
    // iteration.
    private List<LivingBeing> livings;

    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical views of the simulation.
    private List<SimulatorView> views;

    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }

    /**
     * Create a simulation field with the given size.
     *
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) {
        if (width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        livings = new ArrayList<>();
        field = new Field(depth, width);

        // Create a views of the state of each location in the field.
        views = new ArrayList<SimulatorView>();
        SimulatorView grid = new GridView(depth, width);
        grid.setColor(Human.class, Color.BLUE);
        grid.setColor(Duck.class, Color.YELLOW);
        grid.setColor(Pig.class, Color.PINK);
        grid.setColor(Chicken.class, Color.RED);
        views.add(grid);

//        SimulatorView graph = new GraphView(500, 80, 500);
//        graph.setColor(Human.class, Color.BLUE);
//        graph.setColor(Duck.class, Color.YELLOW);
//        graph.setColor(Pig.class, Color.PINK);
//        graph.setColor(Chicken.class, Color.RED);
//        views.add(graph);

        // Setup a valid starting point.
        reset();
    }

    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void runLongSimulation() {
        simulate(4000, 100, 8);
    }

    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     *
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps, int speed, int neighbors) {
        for (step = 1; step <= numSteps && views.get(0).isViable(field); step++) {
            Timer timer = new Timer(speed, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                        simulateOneStep();

                }
            });
            timer.start();
        }
    }


    /**
     * Run the simulation from its current state for a single step. Iterate over
     * the whole field updating the state of each fox and rabbit.
     */
    public void simulateOneStep() {
        step++;
        // Provide space for newborn humans.
        for (Iterator<LivingBeing> it = livings.iterator(); it.hasNext(); ) {
            LivingBeing living = it.next();
            living.act();
            if (living.getState()==States.DEAD) {
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
                    Human human = new Human(false, location, field);
                    livings.add(human);
                } else if (rand.nextDouble() <= DUCK_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Duck duck = new Duck(location, field);
                    livings.add(duck);
                } else if (rand.nextDouble() <= CHICKEN_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Chicken chicken = new Chicken(location, field);
                    livings.add(chicken);
                } else if (rand.nextDouble() <= PIG_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Pig pig = new Pig(location, field);
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
        step = 0;
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
}
