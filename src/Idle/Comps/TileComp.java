package Idle.Comps;

import Idle.Entities.Box;
import Idle.Entities.Entity;

import java.awt.*;
import java.util.HashMap;

public class TileComp extends IdleComponent {

    private final int[][] mapKey;
    private HashMap<Integer,Entity> entityMap;
    private final int tileWidth;

    public TileComp(int w, int h, int tileWidth) {
        super(w*tileWidth+4, h*tileWidth+4);
        this.tileWidth = tileWidth;
        entityMap = new HashMap<>();
        mapKey = new int[w/tileWidth][h/tileWidth];
        for(int i=0;i<mapKey.length;i++){
            for(int j=0;j<mapKey[i].length;j++){
                mapKey[i][j] = i*mapKey[i].length+j;
            }
        }
        //addEntity(new Box(100,100,tileWidth));
    }
    private void addEntity(Entity e){
        entityMap.put(mapKey[(int)(e.getX()/tileWidth)][ (int) (e.getY()/tileWidth)],e);
        e.setMap(entityMap);
    }

    public void tick() {
        double gravity = 12;
        double angle = Math.PI/2;
        for (int key: entityMap.keySet()) entityMap.get(key).tick((int)gravity,angle);
        repaint();
    }
    @Override
    public void paintComponent(Graphics g){
        for (int key: entityMap.keySet()) entityMap.get(key).draw(g);
    }
}
