package patternStrategy;

import java.util.List;
import Model.*;
import utils.*;

public abstract class MoveStrategy {

    private InputMap map ;

    public MoveStrategy(InputMap map) {this.map = map ;}

    public boolean isLegalMove_LastAction(Snake snake, AgentAction action){
        // si le snake n'a pas de corps cela ne s'applique pas
        if(snake.getFeaturesSnake().getPositions().size()==1)
            return true;
        // si le snake a un corps cela s'applique
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
    public InputMap getmap(){return this.map; }
}
