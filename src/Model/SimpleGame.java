package Model;

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




}
