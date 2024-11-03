package Controller;

import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;
import java.awt.event.ActionEvent;

import Model.*;
import Vue.ViewCommand;
import Vue.ViewSimpleGame;
import patternEtat.EtatEnCours;
import patternEtat.EtatInitial;
import patternEtat.EtatOnPlay;
import patternEtat.EtatPaused;

public class ControllerSimpleGame extends abstractController{

    public ViewCommand command ;
    public ViewSimpleGame viewGame ;
    public int maxturn;

    public ControllerSimpleGame(Game game, int maxturn, ViewCommand command ) {
        this.game= game;
        viewGame = new ViewSimpleGame();
        this.command= command;

        game.enregistrerObservateur(command);
        game.enregistrerObservateur(viewGame);


        command.boutton_pause.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e ){
                pause();
                if(!game.get_isRunning())
                    command.set_etat(new EtatPaused(command));
                else 
                    command.set_etat(new EtatEnCours(command));

                command.get_etat().handle();
            }
        });

        command.boutton_play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                CompletableFuture.runAsync(()-> play());
                command.set_etat(new EtatOnPlay(command));
                command.get_etat().handle();
            }
        });

        command.boutton_restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                restart();
                command.set_etat(new EtatInitial(command));
                command.get_etat().handle();
            }
        });

        command.boutton_step.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                step();
                command.set_etat(new EtatEnCours(command));
                command.get_etat().handle();
            }
        });

        command.slider.addChangeListener(e -> {
            int value = command.slider.getValue();
            System.out.println("speed changed to "+ value);
            this.game.set_speed(value*1000);
        });



    }
    






    
}
