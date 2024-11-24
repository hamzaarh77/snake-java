package Model;
import patternStrategy.*;
import java.util.ArrayList;
import java.util.List;
import utils.*;

public class SnakeGame extends Game{

    public List<Snake> snakes ;
    public List<Item> items ;
    public MoveStrategy strategy ;
    public InputMap map ;
    public String strat;



    public SnakeGame(int maxturn, InputMap map, String strat) {
        super(maxturn);
        this.map = map;
        this.strat=strat;
        switch(strat){
            case "random":
                this.strategy = new RandomMoveStrategy(map);
            break;

            default:
                this.strategy = new RandomMoveStrategy(map);
            break;
        }
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
        String filename = this.map.getFilename();
        this.map = new InputMap(filename);
        initializeGame();
    }


    @Override
    public void takeTurn() {
        
        // effectue une action pour chaque agent
        for(Snake element : this.snakes){
            boolean moved =this.strategy.move(element);
            if(!moved){
                this.snakes.remove(element);
                this.gameOver(); // fin du jeu si plus de snake
            }
        }
        ++turn;
        System.out.println("tour: "+ turn);
        this.notifier();
        
        
    }

    // le jeu se termine qund le nombre de tour max est écoulé ou il y a plus d'agent
    public boolean gameOver(){
        if(turn >= maxturn)
        {
            System.exit(0);
            return true ;
        }
        if(snakes.size()==0)
        {
            System.exit(0);
            return true;
        }
        return false;
    }

    @Override
    public boolean gameContinue() {
        return !gameOver();
    }
    

    public ArrayList<FeaturesItem> getFeaturesItems(){
        ArrayList<FeaturesItem> listeFeaturesItem = new ArrayList<>();
        for(Item item : items){
            listeFeaturesItem.add(item.getFeaturesItem());
        }
        return listeFeaturesItem;
    }

    @Override
    public ArrayList<FeaturesSnake> getFeaturesSnakes() {
        ArrayList<FeaturesSnake> listeFeaturesSnake = new ArrayList<>();
        for(Snake snake : snakes){
            listeFeaturesSnake.add(snake.getFeaturesSnake());
        }
        return listeFeaturesSnake;
    }


    
    

    

}
