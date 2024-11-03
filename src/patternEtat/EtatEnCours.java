package patternEtat;

import Vue.ViewCommand;

public class EtatEnCours implements Etat {
    public ViewCommand viewCommand;

    public EtatEnCours(ViewCommand viewCommand){
        this.viewCommand = viewCommand;
    }


    @Override
    public void handle() {
        viewCommand.boutton_restart.setEnabled(false);
        viewCommand.boutton_pause.setEnabled(true);
        viewCommand.boutton_step.setEnabled(true);
        viewCommand.boutton_play.setEnabled(true);
    }
}
