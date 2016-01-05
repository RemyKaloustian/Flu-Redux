package app.view;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.Timer;


/**
 * Created by GNING on 05/01/2016.
 */
public class HomeView extends JFrame{

    private static final int LARGEUR = 300;
    private static final int HAUTEUR = 200;

    private JFrame frame = new JFrame("Homepage");
    private JLabel error;
    private JLabel neighbLabel;
    private JFormattedTextField speedField;
    private JRadioButton fewNeighbors;
    private JRadioButton manyNeighbors;
    private JSlider slider;
    private JButton okButton;
    private ButtonGroup group;


    private int speed;
    private int neighbourhood;


    public HomeView()
    {
        speed = 500;
        neighbourhood = 4;
        setTitle("Homepage");
        this.setPreferredSize(new Dimension(LARGEUR, HAUTEUR));

        neighbLabel = new JLabel("Neighborhood : ", JLabel.CENTER);
        fewNeighbors = new JRadioButton("4");
        fewNeighbors.addActionListener(((e -> neighbourhood = Integer.parseInt(fewNeighbors.getText()))));
        manyNeighbors = new JRadioButton("8");
        manyNeighbors.addActionListener(((e -> neighbourhood = Integer.parseInt(manyNeighbors.getText()))));
        group = new ButtonGroup();
        group.add(manyNeighbors);
        group.add(fewNeighbors);
        Box box  = Box.createHorizontalBox();
        box.add(neighbLabel);
        box.add(fewNeighbors);
        box.add(manyNeighbors);

        slider=new JSlider();

        slider.setInverted(false);
        slider.setMajorTickSpacing(100);
        slider.setMaximum(1000);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setValue(500);
        slider.addChangeListener(e -> speed = slider.getValue());

        Box box2  = Box.createHorizontalBox();
        JPanel jp = new JPanel();
        jp.add(slider);
        jp.setOpaque(false);
        box2.add(jp);

        Box box3 = Box.createHorizontalBox();
        okButton = new JButton();
        okButton.setText("OK");
        okButton.addActionListener(e -> startSimulation());
        box3.add(okButton);


        Container contents = getContentPane();
        contents.add(box, BorderLayout.NORTH);
        contents.add(box2, BorderLayout.CENTER);
        contents.add(box3, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private void startSimulation() {
        new Simulator(neighbourhood,speed);
        setVisible(false);

    }


    // Un seul evenement nous interesse mais on est oblige de declarer ces 5 methodes
    public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseReleased(MouseEvent e) {
        speedField.setText("");
        speedField.setForeground(Color.BLACK);
    }




}
