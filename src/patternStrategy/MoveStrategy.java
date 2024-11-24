package patternStrategy;

import Model.*;
import utils.*;

public abstract class MoveStrategy {

    private InputMap map ;

    public MoveStrategy(InputMap map) {this.map = map ;}

    public boolean isLegalMove(Snake snake, AgentAction action){
        int x = snake.getFeaturesSnake().getPositions().get(0).getX();
        int y = snake.getFeaturesSnake().getPositions().get(0).getY();

        switch (action) {
            case MOVE_UP: y--; break;
            case MOVE_DOWN: y++; break;
            case MOVE_LEFT: x--; break;
            case MOVE_RIGHT: x++; break;
        }

        int width  = this.map.getSizeX();
        int height = this.map.getSizeY();

        // Vérifier si les nouvelles coordonnées sont dans les limites du plateau
        if(!this.map.get_walls()[0][0]){
            return x > 0 && x < width && y > 0 && y < height;
        }
        return x > 0 && x < width-1 && y > 0 && y < height-1;
    }

    public abstract boolean move(Snake snake);
    public InputMap getmap(){return this.map; }
}
