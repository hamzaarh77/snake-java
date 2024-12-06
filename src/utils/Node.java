package utils;

public class Node   {
    private int x ;
    private int y ;
    private Node parent;
    private static int realCost=1 ;
    private float hCoast ;
    private float  totalCoast ;
    private Position target;

    public Node(int x, int y, Node parent, Position target){
        this.x= x ;
        this.y= y;
        this.parent= parent;
        this.target = target;
        this.hCoast = distance(new Position(x,y), target);
        this.totalCoast = realCost+hCoast;
    }

    public Node(int x, int y){
        this.x= x ;
        this.y= y;
        this.parent= null;
        realCost = 0;
        this.hCoast = 0;
    }


    public static int distance(Position p1, Position p2){
        return Math.abs(p2.getX() - p1.getX()) + Math.abs(p2.getY() - p1.getY());
    }

    public int get_realCost(){
        return this.realCost;
    }

    public float get_hCoast(){
        return this.hCoast;
    }

    public float get_totalCoast(){
        return this.totalCoast;
    }

    public Node get_parent(){
        return this.parent;
    }

    public Position get_nodePosition(){
        return new Position(x,y);
    }

    public Position get_target(){
        return this.target;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Node node = (Node) obj;
        return this.x == node.get_nodePosition().getX() && this.y == node.get_nodePosition().getY();
    }


   
}
