package Controller;
import Model.*;
import Vue.ViewCommand;
import Vue.ViewSimpleGame;


public class ControllerSimpleGame extends abstractController{

    public ViewCommand command ;
    public ViewSimpleGame viewGame ;
    public int maxturn;

    public ControllerSimpleGame(Game game, int maxturn, ViewCommand command ) {
        super(game);

        this.game= game;
        viewGame = new ViewSimpleGame();
        this.command= command;

        game.enregistrerObservateur(command);
        game.enregistrerObservateur(viewGame);

    }
    






    
}
