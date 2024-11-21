package Model;

import java.util.ArrayList;

import utils.FeaturesItem;
import utils.FeaturesSnake;

public class SimpleGame extends Game{


    public SimpleGame(int maxturn) {
        super(maxturn);
        
    }

    @Override
    public void initializeGame() {
        turn = 0 ;
        isRunning = true;
        notifier();
    }

    @Override
    public void takeTurn() {
        ++turn;
        this.notifier();
        System.out.println("Tour: "+turn);
    }

    @Override
    public boolean gameContinue() {
        return !gameOver();
    }

    @Override
    public void restartGame() {
        initializeGame();
    }

    @Override
    public ArrayList<FeaturesItem> getFeaturesItems() {
        return null;
    }

    @Override
    public ArrayList<FeaturesSnake> getFeaturesSnakes() {
        return null;
    }
    




}
