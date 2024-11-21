
import Model.*;
import patternStrategy.MoveStrategy;
import patternStrategy.RandomMoveStrategy;
import Controller.ControllerSnakeGame;

public class Test2 {
    public static void main(String[] args) throws Exception {
        InputMap map = new InputMap("layouts/arenaNoWall.lay");
        SnakeGame game = new SnakeGame(100, map, "random");
        @SuppressWarnings("unused")
        ControllerSnakeGame c = new ControllerSnakeGame(game);  
    }
}