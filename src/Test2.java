
import Model.*;


import Controller.ControllerSnakeGame;
import Model.SnakeGame;

public class Test2 {
    public static void main(String[] args) throws Exception {
        InputMap map = new InputMap("layouts/smallArena.lay");
        SnakeGame game = new SnakeGame(10, map);
        ControllerSnakeGame c = new ControllerSnakeGame(game);

                

       
    }

}