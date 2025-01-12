package utils;

import java.util.ArrayList;

public class Snake {
    private FeaturesSnake features;

    public Snake(FeaturesSnake f ){
        this.features = f ;
    }

    public FeaturesSnake getFeaturesSnake(){return this.features; }
    public void setFeaturesSnake(FeaturesSnake f ){this.features = f ;}

    // changement de la position d'un snake
    public void setPosition(int x , int y){
        ArrayList<Position> liste = this.features.getPositions();
        liste.add(0, new Position(x,y));
        liste.remove(liste.size()-1);
        this.features.setPositions(liste);
    }

}
