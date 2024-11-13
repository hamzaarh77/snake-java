package Controller;
import java.util.concurrent.CompletableFuture;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.* ;
import Vue.ViewCommand;
import patternEtat.*;


abstract public class abstractController {
    public Game game ;
    public ViewCommand command ;


    public abstractController(Game game){
        this.game = game;


        command = new ViewCommand();
        this.game.enregistrerObservateur(command);
        //game.initializeGame();

        command.boutton_pause.addActionListener((ActionListener) new ActionListener() {
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

        // ici on doit remettre a jour la map
        command.boutton_restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                restart();
                
                command.set_etat(new EtatInitial(command));
                command.get_etat().handle();
            }
        });

        command.boutton_step.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(game.gameContinue())
                    game.takeTurn();
                else
                    game.gameOver();
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

    // passage manuelle d'une etape
    public void step(){
        if(game.get_isRunning())
            game.step();
    }

    // play est une fonction asynchrone donc il faut l'appeler dans un thread
    public void play(){
        game.run();
    }

    // reinitialiser le jeu
    public void restart(){
        game.restartGame();
    }

    // si un jeu est en cours on le mets en pause sinon on le relance 
    public void pause(){
        if(game.get_isRunning())
            game.set_isRunning(false);
        else 
            game.set_isRunning(true);
    }

    // reglage de la vitesse du jeu
    public void setSpeed(long l){
        this.game.set_speed(l);
    }



}
