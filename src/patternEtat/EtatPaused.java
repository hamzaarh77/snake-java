package patternEtat;

import Vue.ViewCommand;

public class EtatPaused implements Etat {
    public ViewCommand viewCommand;

    public EtatPaused(ViewCommand viewCommand){
        this.viewCommand = viewCommand;
    }


    @Override
    public void handle() {
        viewCommand.boutton_restart.setEnabled(true);
        viewCommand.boutton_pause.setEnabled(true);
        viewCommand.boutton_step.setEnabled(false);
        viewCommand.boutton_play.setEnabled(false);
    }
}
