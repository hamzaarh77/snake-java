package Vue;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import patternEtat.Etat;
import patternEtat.EtatInitial;
import patternObservateur.*;

public class ViewCommand implements Observateur {

    public Etat etat ;
    public JLabel label ;
    public JSlider slider ;

    // buttons 
    public JButton boutton_pause ;
    public JButton boutton_play ;
    public JButton boutton_restart ;
    public JButton boutton_step ;



    public ViewCommand(){
        JFrame frame = new JFrame();

        frame.setSize(800,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(1000,500);


        JPanel mainPanel = new JPanel(new GridLayout(2,1));

        JPanel topPanel = new JPanel(new GridLayout(1,4));
        
        boutton_pause = new JButton(new ImageIcon(getClass().getResource("/imagesButtons/icon_pause.png")));
        boutton_play = new JButton(new ImageIcon(getClass().getResource("/imagesButtons/icon_play.png")));
        boutton_restart = new JButton(new ImageIcon(getClass().getResource("/imagesButtons/icon_restart.png")));
        boutton_step = new JButton(new ImageIcon(getClass().getResource("/imagesButtons/icon_step.png")));

        topPanel.add(boutton_pause);
        topPanel.add(boutton_play);
        topPanel.add(boutton_restart);
        topPanel.add(boutton_step);

        // bottom panel 
        JPanel bottomPanel = new JPanel(new GridLayout(2,1));

        JPanel sliderPanel = new JPanel(new GridLayout(1,2));

        JLabel label1 = new JLabel("Number of turns per second");
        label = new JLabel("Turn: x");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        slider = new JSlider(JSlider.HORIZONTAL,1 ,10, 1);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);




        sliderPanel.add(slider);
        sliderPanel.add(label);

        bottomPanel.add(label1);
        bottomPanel.add(sliderPanel);


        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);
        frame.add(mainPanel);
        this.etat = new EtatInitial(this);
        etat.handle();
        frame.setVisible(true);

        

    }


    @Override
    public void actualiser(int turn) {
        label.setText("Tour "+ String.valueOf(turn));
    }

    public Etat get_etat(){
        return this.etat ;
    }

    public void set_etat(Etat etat ){
        this.etat = etat ;
    }
    




}
