package Model;
import java.lang.Runnable;
import java.util.ArrayList;
import patternObservateur.*;
import utils.FeaturesItem;
import utils.FeaturesSnake;


abstract public class Game implements Runnable, Sujet {

    public static int turn ;
    public int maxturn ;
    public boolean isRunning ;
    Thread thread;
    long time ; // speed
    ArrayList<Observateur> observateurs;


    public Game(int maxturn ){
        this.maxturn = maxturn;
        this.isRunning = true ;
        this.time = 1000;
        observateurs = new ArrayList<>();
    }

    

    // les methodes abstraites :
    abstract public void initializeGame();
    abstract public void takeTurn();
    abstract public boolean gameContinue();
    abstract public void restartGame();
    abstract public ArrayList<FeaturesItem> getFeaturesItems();
    abstract public ArrayList<FeaturesSnake> getFeaturesSnakes();





    // le jeu se termine quand le nb de tour max se termine
    public boolean gameOver() {
        if(turn >= maxturn)
            {
                System.exit(0);
                return true ;
            }
        return false;
    }



    public void launch(){
        this.isRunning = true ;
        this.thread = new Thread(this);
        thread.start(); // start lancera automatique la methode run de l'objet Game
    }



    // initialiser le jeu le jeu
    public void init(){
        turn = 0;
        this.isRunning = true ;
        launch();
    }




    // apelle la methode takeTurn si le jeu n'est pas terminé 
    // on sait que le jeu n'est pas terminé avec 2 condition: la methode gameContinue et le nombre de tour qui reste
    public void step(){
        if(gameContinue()){
            if(turn < maxturn ){
                takeTurn();
            }else {
                isRunning = false ;
                gameOver();
            }}
        }



    public void pause(){
        this.isRunning = false;
    }



    public void run(){
        while(isRunning){
            this.step();

            try {
                Thread.sleep(this.time);
            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }


            if(gameOver()){
                isRunning = false;
            }
        }
    }
    

    


// pattern observateur //////////////////////////////////
    @Override
    public void enregistrerObservateur(Observateur o) {
        observateurs.add(o);
    }

    @Override
    public void supprimerObservateur(Observateur o) {
        observateurs.remove(o);
    }

    @Override
    public void notifier() {
        for (Observateur o : observateurs){
            o.actualiser(turn);
        }
    }


//getters et setters ////////////////////////

    public Boolean get_isRunning(){
        return this.isRunning;
    }

    public long get_time(){
        return this.time ;
    }

    public void set_isRunning(Boolean b){
        this.isRunning = b ;
    }


    public void set_speed( long t){
        this.time = t ;
    }

    /////////////////////////prblemes:


}


