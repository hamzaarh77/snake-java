package Vue;
import javax.swing.JFrame;
import javax.swing.JLabel;

import patternObservateur.*;

import java.awt.BorderLayout;



public class ViewSimpleGame implements Observateur {
    public JLabel label ;

    @Override
    public void actualiser(int turn) {
        label.setText("Tour "+ String.valueOf(turn));
    }


    public ViewSimpleGame(){
        JFrame frame = new JFrame("Ma fenetre");

        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(100,100);

        label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        
        frame.setLayout(new BorderLayout());
        frame.add(label, BorderLayout.CENTER);

        frame.setVisible(true);


    }   
}
