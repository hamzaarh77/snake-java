
import Model.*;


import Controller.ControllerSnakeGame;
import Model.SnakeGame;

public class Test2 {
    public static void main(String[] args) throws Exception {
        InputMap map = new InputMap("layouts/arenaNoWall.lay");
        SnakeGame game = new SnakeGame(30, map);
        @SuppressWarnings("unused")
        ControllerSnakeGame c = new ControllerSnakeGame(game);

                

       
    }

}