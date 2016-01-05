package app.view;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.Timer;


/**
 * Created by GNING on 05/01/2016.
 */
public class HomeView extends JFrame implements MouseListener, KeyListener{

    private static final int LARGEUR = 300;
    private static final int HAUTEUR = 200;

    Simulator simul;

    private JFrame frame = new JFrame("Homepage");
    private JLabel error;
    private JLabel neighbLabel;
    private JLabel speedLabel;
    private JFormattedTextField speedField;
    private JRadioButton fewNeighbors;
    private JRadioButton manyNeighbors;

    public HomeView(Simulator sim)
    {
        setTitle("Homepage");
        this.setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
        simul = sim;

        neighbLabel = new JLabel("Neighborhood : ", JLabel.CENTER);
        fewNeighbors = new JRadioButton("4");
        manyNeighbors = new JRadioButton("8");
        Box box  = Box.createHorizontalBox();
        box.add(neighbLabel);
        box.add(fewNeighbors);
        box.add(manyNeighbors);

        speedLabel = new JLabel("Speed (int only) : ", JLabel.CENTER);
        speedField = new JFormattedTextField("speed");
        speedField.setForeground(Color.DARK_GRAY);
        speedField.setColumns(10);

        Box box2  = Box.createHorizontalBox();
        JPanel jp = new JPanel();
        jp.add(speedLabel);
        jp.add(speedField);
        jp.setOpaque(false);
        box2.add(jp);

        Box box3 = Box.createHorizontalBox();
        error = new JLabel("");
        error.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        error.setAlignmentX(Component.CENTER_ALIGNMENT);
        error.setForeground(new Color(200, 0, 0));
        box3.add(error);

        Container contents = getContentPane();
        contents.add(box, BorderLayout.NORTH);
        contents.add(box2, BorderLayout.CENTER);
        contents.add(box3, BorderLayout.SOUTH);

        speedField.addKeyListener(this);
        speedField.addMouseListener(this);

        pack();
        setVisible(true);
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

    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == '\n') {
            String text = speedField.getText().split("\n")[0];
            Pattern p = Pattern.compile("^[0-9]*$");
            if (text != null && !text.trim().equals("") && (p.matcher(text.trim())).matches()) {
                error.setText("");
                if (fewNeighbors.isSelected() && !manyNeighbors.isSelected())
                {
                    try {
                        this.removeAll();
                        simul.simulate(20, Integer.parseInt(text), 4);
                        this.dispose();
                    } catch (Exception exc) {}
                }
                else if (!fewNeighbors.isSelected() && manyNeighbors.isSelected())
                {
                    try {
                        this.removeAll();
                        simul.simulate(20, Integer.parseInt(text), 8);
                        this.dispose();
                    } catch (Exception exc) {}
                }
                else if(!fewNeighbors.isSelected() && !manyNeighbors.isSelected())
                    error.setText("At least one button must be pressed !");
                else
                    error.setText("Only one button must be pressed !");
            }
            else{
                error.setText("The speed must be an Integer !");
            }
        }
    }
}
