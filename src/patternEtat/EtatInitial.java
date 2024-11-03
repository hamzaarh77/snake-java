package patternEtat;
import Vue.ViewCommand;

public class EtatInitial implements Etat{

    public ViewCommand viewCommand;

    public EtatInitial(ViewCommand viewCommand){
        this.viewCommand = viewCommand;
    }

    @Override
    public void handle() {
        viewCommand.boutton_restart.setEnabled(false);
        viewCommand.boutton_pause.setEnabled(false);
        viewCommand.boutton_step.setEnabled(true);
        viewCommand.boutton_play.setEnabled(true);
    }
    
    
}
