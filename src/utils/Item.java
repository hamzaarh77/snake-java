package utils;

public class Item {
    private FeaturesItem features;

    public Item(FeaturesItem f ){
        this.features = f ;
    }

    public FeaturesItem getFeaturesItem(){return this.features; }
    public void setFeaturesItem(FeaturesItem f ){this.features = f ;}

}
