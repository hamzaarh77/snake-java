package Controller;
import Model.* ;

abstract public class abstractController {
    public Game game ;

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
        game.initializeGame();
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
