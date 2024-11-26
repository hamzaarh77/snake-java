package patternStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import Model.*;
import utils.*;

public abstract class MoveStrategy {

    private InputMap map ;

    public MoveStrategy(InputMap map) {this.map = map ;}

    public boolean isLegalMove_LastAction(Snake snake, AgentAction action){
        switch (action) {
            case MOVE_UP: 
                if(snake.getFeaturesSnake().getLastAction() != AgentAction.MOVE_DOWN)
                    return true;
            break;

            case MOVE_DOWN: 
                if(snake.getFeaturesSnake().getLastAction() != AgentAction.MOVE_UP)
                    return true;
            break;

            case MOVE_LEFT:
                if(snake.getFeaturesSnake().getLastAction() != AgentAction.MOVE_RIGHT)
                    return true;
            break;

            case MOVE_RIGHT:
                if(snake.getFeaturesSnake().getLastAction() != AgentAction.MOVE_LEFT)
                    return true;
            break;
        }
        return false;
    }

    public boolean isLegalMove_Wall(Snake snake, AgentAction action){
        int x = snake.getFeaturesSnake().getPositions().get(0).getX();
        int y = snake.getFeaturesSnake().getPositions().get(0).getY();

        switch (action) {
            case MOVE_UP: 
                y--;
            break;

            case MOVE_DOWN: 
                y++; 
            break;

            case MOVE_LEFT:
                x--;
            break;

            case MOVE_RIGHT:
                x++;
            break;
        }

        int width  = this.map.getSizeX();
        int height = this.map.getSizeY();

        // Vérifier si les nouvelles coordonnées sont dans les limites du plateau
        if(!this.map.get_walls()[0][0]){
            return x > 0 && x < width && y > 0 && y < height;
        }
        return x > 0 && x < width-1 && y > 0 && y < height-1;
    }

    public abstract boolean move(Snake snake, List<Item> items);
    public void rules (Snake snake, List<Item> items){
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
                        snake.getFeaturesSnake().addQueu();
                        iterator.remove();
                        // faire apparaitre un objet au hasrd dans al map
                        ItemType randomItemType = liste.get(random.nextInt(liste.size()));
                        FeaturesItem randomFeaturesItem = new FeaturesItem(random.nextInt(this.map.getSizeX()), random.nextInt(this.map.getSizeY()), randomItemType);
                        Item randomItem = new Item(randomFeaturesItem);
                        toAdd.add(randomItem);
                        System.out.println("pomme***********************");
                    break;

                    default:
                    System.out.println("autre");
                    break;

                }
                
            }
        }
        items.addAll(toAdd);
        return ;
    }
    public InputMap getmap(){return this.map; }
}
