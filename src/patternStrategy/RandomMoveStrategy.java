package patternStrategy;

import Model.InputMap;
import utils.*;
import java.util.Random;

public class RandomMoveStrategy extends MoveStrategy {
    private final Random random = new Random(); 

    public RandomMoveStrategy(InputMap map){
        super(map);
    }

    @Override
    public void move(Snake snake) {
        AgentAction action = AgentAction.values()[random.nextInt(AgentAction.values().length)];

        int x = snake.getFeaturesSnake().getPositions().get(0).getX();
        int y = snake.getFeaturesSnake().getPositions().get(0).getY();
        System.out.println(x+" "+y);
        switch(action){
            case MOVE_UP   : y--; break;
            case MOVE_DOWN : y++; break;
            case MOVE_LEFT : x--; break;
            case MOVE_RIGHT: x++; break;
        }

        // Vérifier si le mouvement est valide et mettre à jour la position
        if (this.isLegalMove(snake, action)) {
            snake.setPosition(x, y);
            System.out.println(x+" "+y);

        } else {
            // if (x < 0) x = getmap().getSizeX() - 1;
            // else if (x >= getmap().getSizeX()) x = 0;
        
            // if (y < 0) y = getmap().getSizeY() - 1;
            // else if (y >= getmap().getSizeY()) y = 0;
        
            System.out.println("mur");
        }
        System.out.println("-------------------------------");


    }

    
    
}
