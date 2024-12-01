package Model;
import patternStrategy.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import utils.*;

public class SnakeGame extends Game{

    public List<Snake> snakes ;
    public List<Item> items ;
    public MoveStrategy strategy ;
    public InputMap map ;
    public String strat;

    private int sickTurn;
    private int invinciblTurn;



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
        Iterator<Snake> iterator = this.snakes.iterator();
        while(iterator.hasNext()){
            Snake element = iterator.next();
            // pour chaque tour on teste si le snake est malade pour lui enlever sa maladie si les 20 tours sont finis
            if(element.getFeaturesSnake().isSick() && turn-20 == sickTurn)
                 element.getFeaturesSnake().setSick(false);
            

            if(element.getFeaturesSnake().isInvincible() && turn-20 == invinciblTurn)
                element.getFeaturesSnake().setInvincible(false);
            


            boolean moved = this.strategy.move(element, this.items);
            if(!moved){
                if(!element.getFeaturesSnake().isInvincible())
                    iterator.remove();
                this.gameOver();
            }else {
                item_rules(element);
            }
        }
        //snakeElimination();// a chaque tour on teste si un snake elimine un autre
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

    ///////// implementations des regles : aprés chaque movements valide on applique les regles du jeu//

    public void item_rules (Snake snake){
        List<ItemType> liste = new ArrayList<>(List.of(ItemType.BOX, ItemType.INVINCIBILITY_BALL, ItemType.SICK_BALL));
        Random random = new Random();
        // pour ce snake on parcours si il est dans la meme position qu'un item
        // on pourra modifier snake et items, les modifications seront faites
        Iterator<Item> iterator = items.iterator();
        List<Item> toAdd = new ArrayList<>(); // Liste temporaire pour les nouveaux éléments

        while(iterator.hasNext()){
            Item item = iterator.next();
            if(item.getPosition().equals(snake.getheadPosition())){
                ItemType type = item.getFeaturesItem().getItemType();
                switch(type){
                    case APPLE:
                            if(!snake.getFeaturesSnake().isSick())
                            {
                                snake.getFeaturesSnake().addQueu();
                                iterator.remove();
                                // faire apparaitre un objet au hasrd dans al map
                                ItemType randomItemType = liste.get(random.nextInt(liste.size()));
                                FeaturesItem randomFeaturesItem = new FeaturesItem(random.nextInt(this.map.getSizeX()), random.nextInt(this.map.getSizeY()), randomItemType);
                                Item randomItem = new Item(randomFeaturesItem);
                                toAdd.add(randomItem);
                            }
                    break;

                    case SICK_BALL:
                        snake.getFeaturesSnake().setSick(true);
                        iterator.remove();
                        this.sickTurn = turn;
                        System.out.println("snake malade");
                    break;

                    case INVINCIBILITY_BALL:
                            snake.getFeaturesSnake().setInvincible(true);
                            iterator.remove();
                            this.invinciblTurn = turn ;
                            System.out.println("snake invincible");
                    break;

                    default:
                        int randomChoice = random.nextInt(2);
                        if(randomChoice == 0){
                            snake.getFeaturesSnake().setSick(true);
                            this.sickTurn = turn;
                            System.out.println("snake malade");
                        }else {
                            snake.getFeaturesSnake().setInvincible(true);
                            this.invinciblTurn = turn ;
                            System.out.println("snake invincible");
                        }
                        iterator.remove();
                    break;

                }
                
            }
        }
        items.addAll(toAdd);
        return ;
    }


    public void snakeElimination(){
        Iterator<Snake> currentIterator = this.snakes.iterator();
        while(currentIterator.hasNext()){
            Snake currentSnake = currentIterator.next();

            Iterator<Snake> iterator = this.snakes.iterator();
            while(iterator.hasNext()){
                Snake snake = iterator.next();

                // si la téte de deux snake de la méme taille se retrouve dans la méme position, tout les deux éliminé
                if(snake.getheadPosition().equals(currentSnake.getheadPosition()) && snake.getFeaturesSnake().getPositions().size()==currentSnake.getFeaturesSnake().getPositions().size()){
                    iterator.remove();
                    currentIterator.remove();
                }

                // si la tete d'un snake courant est dans la position d'un autre snake et le courant est plus grand on l'elimine
                if(snake.getFeaturesSnake().getPositions().contains(currentSnake.getheadPosition()) && currentSnake.getFeaturesSnake().getPositions().size()>=snake.getFeaturesSnake().getPositions().size()){
                    iterator.remove();
                }
            }
        }
    }

}
