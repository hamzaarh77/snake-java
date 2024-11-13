package Controller;

import java.util.ArrayList;


import Model.*;
import Vue.PanelSnakeGame;
import Vue.ViewSnakeGame;
import utils.*;

public class ControllerSnakeGame extends abstractController{

    public String path ;
    public InputMap map ;
    


    public ControllerSnakeGame(String path ){
        this.game = new SnakeGame(20);
        this.path = path ;
        
        try {
            map = new InputMap(path);
             // Dimensions du plateau
        int sizeX = 10;
        int sizeY = 10;

        // Création des murs (tableau 2D)
        boolean[][] walls = new boolean[sizeX][sizeY];
        walls[2][3] = true; // Exemple de mur à la position (2, 3)

        // Création de la liste de positions pour le serpent
        ArrayList<utils.Position> snakePositions = new ArrayList<>();
      

        // Création d'un serpent avec des attributs spécifiques
        FeaturesSnake snake1 = new FeaturesSnake(snakePositions, AgentAction.MOVE_UP, ColorSnake.Green, false, false);
        snakePositions.add(new utils.Position(5, 5));
        snakePositions.add(new utils.Position(5, 6));
        snakePositions.add(new utils.Position(5, 7));
        ArrayList<FeaturesSnake> featuresSnakes = new ArrayList<>();
        featuresSnakes.add(snake1);

        // Création de la liste d'items (pommes, etc.)
        ArrayList<FeaturesItem> featuresItems = new ArrayList<>();
        featuresItems.add(new FeaturesItem(7, 7, ItemType.APPLE));

        // Création d'une instance de PanelSnakeGame
        PanelSnakeGame panel = new PanelSnakeGame(map.getSizeX(), map.getSizeY(), map.get_walls(), map.getStart_snakes(), map.getStart_items());

        ViewSnakeGame vsg = new ViewSnakeGame(panel);


        } catch (Exception e) {
            e.printStackTrace();
        }


        
    }
}
