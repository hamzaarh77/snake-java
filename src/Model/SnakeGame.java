package Model;
import patternStrategy.*;
import java.util.ArrayList;
import java.util.List;

import Vue.PanelSnakeGame;
import utils.*;

public class SnakeGame extends Game{

    public List<Snake> snakes ;
    public List<Item> items ;
    public InputMap map ;
    public MoveStrategy strategy ;
    public PanelSnakeGame panel;


    public SnakeGame(int maxturn, InputMap map) {
        super(maxturn);
        this.map = map;
        this.panel = new PanelSnakeGame(map.getSizeX(), map.getSizeY(), map.get_walls(), map.getStart_snakes(), map.getStart_items());

        strategy = new RandomMoveStrategy(map);
    }

    @Override
    public void initializeGame() {
        // initialiser le jeu
        turn =0;
        isRunning = true ;
        this.snakes = new ArrayList<>();
        this.items = new ArrayList<>();
        // creation des snakes dans le jeu à partir du plateau
        for(FeaturesSnake snake : this.map.getStart_snakes()){
            Snake s =new Snake(snake);
            this.snakes.add(s);
        }

        // creation des items dans le jeu a partir du plateau
        for(FeaturesItem item : this.map.getStart_items()){
            this.items.add(new Item(item));
        }
        this.notifier();
    }

    public void restartGame(){
        // reinitialisation de la map puis du jeu
        String filename = this.map.getFilename();
        this.map = new InputMap(filename);
        initializeGame();
    }


    @Override
    public void takeTurn() {
        // effectue une action pour chaque agent
        for(Snake element : this.snakes){
            this.strategy.move(element);
        }
        ++turn;
        System.out.println("tour: "+ turn);
        this.notifier();
        // on doit mettre le panel a jour a chaque tour mais ici on a pas acces au pannel
        //this.panel.updateInfoGame(null, null);
    }

    @Override
    public boolean gameContinue() {
        return !gameOver();
    }

    public PanelSnakeGame getpanel(){
        return this.panel;
    }

    

}
