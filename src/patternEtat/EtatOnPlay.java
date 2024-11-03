package patternEtat;

import Vue.ViewCommand;

public class EtatOnPlay implements Etat{

    public ViewCommand viewCommand;

    public EtatOnPlay(ViewCommand viewCommand){
        this.viewCommand = viewCommand;
    }


    @Override
    public void handle() {
        viewCommand.boutton_restart.setEnabled(false);
        viewCommand.boutton_pause.setEnabled(true);
        viewCommand.boutton_step.setEnabled(false);
        viewCommand.boutton_play.setEnabled(false);
    }
}
