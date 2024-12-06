package patternStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.*;
import Model.InputMap;


public class AStar extends MoveStrategy  {

    Position target;
    List<Item> items;
    List<Node> visitedNodes;
    List<Node> nodesToVisit;

    public AStar(InputMap map, List<Item> items){
        super(map);
        this.items = items;
        this.target = null; 
    }

    public Node bestNode(List<Node> nodesToVist){
        if (nodesToVisit.isEmpty())
            return null;
        float ct =100;
        Node nodeToReturn = null;
        for (Node node : nodesToVist){
            if(node.get_totalCoast()<ct){
                ct = node.get_totalCoast();
                nodeToReturn = node;
            }
        }
        return nodeToReturn;
    }


    @Override
    public boolean move(Snake snake) {
        // determiner la target
        Item closestApple = null;
        int minDistance = Integer.MAX_VALUE; // Distance minimale initiale
            
        // Parcours de la liste des items pour trouver la pomme la plus proche
        for (Item item : items) {
            if (item.getFeaturesItem().getItemType().equals(ItemType.APPLE)) { // Vérifie si l'item est une pomme
                int distance = Node.distance(snake.getheadPosition(), item.getPosition());
                if (distance < minDistance) {
                    minDistance = distance;
                    closestApple = item;
                }
            }
        }
        this.target = closestApple.getPosition();

        // declaration
        visitedNodes = new ArrayList<>();
        visitedNodes.add(new Node(snake.getheadPosition().getX(), snake.getheadPosition().getY()));
        List<AgentAction> agentActions= Arrays.asList(AgentAction.values());
        nodesToVisit = new ArrayList<>();

        // initialisation
        for (AgentAction agentAction : agentActions) {
            if(isLegalMove_LastAction(snake, agentAction) && isLegalMove_Wall(snake, agentAction)){
                // creation du noeud a visiter
                int x =0; int y=0;
                switch(agentAction){
                    case MOVE_UP:
                        x = snake.getheadPosition().getX();
                        y = snake.getheadPosition().getY()-1;
                    break;

                    case MOVE_DOWN:
                        x = snake.getheadPosition().getX();
                        y = snake.getheadPosition().getY()+1;
                    break;

                    case MOVE_LEFT:
                        x = snake.getheadPosition().getX()-1;
                        y = snake.getheadPosition().getY();
                    break;

                    case MOVE_RIGHT:
                        x = snake.getheadPosition().getX()+1;
                        y = snake.getheadPosition().getY();
                    break;
                }
                Node parent = visitedNodes.get(visitedNodes.size()-1);
                Position target = this.target; // permettera de calculer CH directement dans le constructeur
                nodesToVisit.add(new Node(x,y,parent, target));
            }
        }

        Node bestNode = bestNode(nodesToVisit);
        while(bestNode != null){
            if(!visitedNodes.contains(bestNode)){
                visitedNodes.add(bestNode);
                snake.setPosition(bestNode.get_nodePosition().getX(), bestNode.get_nodePosition().getY());
                return true;
            }
            nodesToVisit.remove(bestNode);
            bestNode = bestNode(nodesToVisit);
        }
        return false;
    }
    
}
