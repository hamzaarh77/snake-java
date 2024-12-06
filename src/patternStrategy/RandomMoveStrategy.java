package patternStrategy;

import Model.InputMap;
import utils.*;

import java.util.List;
import java.util.Random;

public class RandomMoveStrategy extends MoveStrategy {
    private final Random random = new Random(); 

    public RandomMoveStrategy(InputMap map){
        super(map);
    }

    @Override
    public boolean move(Snake snake) {
        AgentAction action = AgentAction.values()[random.nextInt(AgentAction.values().length)];

        int x = snake.getFeaturesSnake().getPositions().get(0).getX();
        int y = snake.getFeaturesSnake().getPositions().get(0).getY();
        System.out.println("position avant: "+x+" "+y);
        switch(action){
            case MOVE_UP   : y--;System.out.println("Haut"); break;
            case MOVE_DOWN : y++;System.out.println("Bas"); break;
            case MOVE_LEFT : x--;System.out.println("Gauche"); break;
            case MOVE_RIGHT: x++;System.out.println("Droite"); break;
        }
        // verification si le mouvement est valide par rapport a last action
        if(this.isLegalMove_LastAction(snake, action)){
            // Vérifier si le mouvement est valide et mettre à jour la position
            if (this.isLegalMove_Wall(snake, action)) {
                snake.setPosition(x, y);
                snake.getFeaturesSnake().setLastAction(action);
                System.out.println("position apres: "+x+" "+y);
                System.out.println("---------------------");
                return true;

            } else {
                //sans mur
                if(!this.getmap().get_walls()[0][0]){
                    if (x < 0) x = getmap().getSizeX() - 1;
                    else if (x >= getmap().getSizeX()) x = 0;
                
                    if (y < 0) y = getmap().getSizeY() - 1;
                    else if (y >= getmap().getSizeY()) y = 0;
                    snake.setPosition(x, y);
                    System.out.println("---------------------");
                    snake.getFeaturesSnake().setLastAction(action);
                    return true;
                }// avec mur
                else {
                    // on elimine l'agent qui a touché le mur
                    System.out.println("agent éliminé");
                    return false;
                }
            }
        }else {
            move(snake);
            return true; // juste pour ne pas supprimer l'element, on repete jusqu'a pas avoir le probleme de lastAction
        }

        


    }

    
    
}
