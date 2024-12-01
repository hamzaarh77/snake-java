package utils;
import java.util.ArrayList;

public class Snake {

    private FeaturesSnake features;

    public Snake(FeaturesSnake f ){
        this.features = f ;
    }

    // getters and setters
    public FeaturesSnake getFeaturesSnake(){return this.features; }
    public void setFeaturesSnake(FeaturesSnake f ){this.features = f ;}

    // changement de la position d'un snake
    public void setPosition(int x , int y){
        ArrayList<Position> liste = this.features.getPositions();
        liste.add(0, new Position(x,y));
        liste.remove(liste.size()-1);
        this.features.setPositions(liste);
    }

    public Position getheadPosition(){
		return this.getFeaturesSnake().getPositions().get(0);
	}

    public ArrayList<Position> getSnakeBody(){
        return  new ArrayList<Position>(this.getFeaturesSnake().getPositions().subList(1, this.getFeaturesSnake().getPositions().size())); 
    }

}
