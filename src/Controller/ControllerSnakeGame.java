package Controller;
import Model.*;
import Vue.ViewCommand;
import Vue.ViewSnakeGame;


public class ControllerSnakeGame extends abstractController{

    public ViewSnakeGame vsg;
    public ViewCommand command ;
    

    
    public ControllerSnakeGame(SnakeGame game ){
        super(game);
        this.vsg = new ViewSnakeGame(this.game.getpanel());
        game.initializeGame();
    }

    public void restart(){
        this.game.restartGame();
    }
}
