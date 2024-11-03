package Vue;

import javax.swing.*;

public class ViewSnakeGame {

    public PanelSnakeGame jlabel ;


    public ViewSnakeGame(PanelSnakeGame jlabel){
        this.jlabel = jlabel ;

        JFrame frame = new JFrame();
        frame.setSize(800,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(1000,500);

        frame.add(jlabel);
        frame.setVisible(true);

        
    }
    
    
}
