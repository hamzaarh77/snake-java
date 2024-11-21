package Controller;
import java.util.ArrayList;

import Model.*;
import Vue.PanelSnakeGame;
import Vue.ViewCommand;
import Vue.ViewSnakeGame;
import utils.FeaturesItem;
import utils.FeaturesSnake;
import utils.Item;
import utils.Snake;


public class ControllerSnakeGame extends abstractController{

    public ViewSnakeGame vsg;
    public ViewCommand command ;
    public PanelSnakeGame panel;


    

    
    public ControllerSnakeGame(SnakeGame game ){
        super(game);
        this.panel = new PanelSnakeGame(game.map.getSizeX(),game.map.getSizeY(), game.map.get_walls(), game.map.getStart_snakes(), game.map.getStart_items());
        this.vsg = new ViewSnakeGame(this.panel);
        game.initializeGame();
    }

    public void restart(){
        this.game.restartGame();
        // reinitialisation de la map puis du jeu
        rePaint();
    }
    public void takeTurn(){
        this.game.takeTurn();
        rePaint();
    }

    public void run(){
        while(game.get_isRunning()){
            this.step();

            try {
                Thread.sleep(this.game.get_time());
            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
            this.rePaint();

            if(game.gameOver()){
                game.isRunning = false;
            }
        }
    }
    private void rePaint(){
        // on doit mettre le panel a jour a chaque tour mais ici on a pas acces au pannel
        ArrayList<FeaturesSnake> listeSnakesFeatures = this.game.getFeaturesSnakes();
        ArrayList<FeaturesItem> listeItemsFeatures = this.game.getFeaturesItems();
        
        this.panel.updateInfoGame(listeSnakesFeatures,listeItemsFeatures);
        this.panel.repaint();
    }
}
