import Controller.ControllerSimpleGame;
import Controller.abstractController;
import Model.Game;
import Model.SimpleGame;
import Vue.ViewCommand;

public class Test {
    public static void main(String[] args) throws Exception {

        Game game = new SimpleGame(10);
        ViewCommand command = new ViewCommand();
        @SuppressWarnings("unused")
        abstractController a = new ControllerSimpleGame(game, 20,command );
    }
}

