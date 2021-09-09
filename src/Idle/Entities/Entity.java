package Idle.Entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/** @noinspection WeakerAccess*/
public abstract class Entity {
    protected double x, y;
    protected double dx, dy;
    protected final int tileWidth;
    protected HashMap<Integer, Entity> entityMap;
    protected int weight =0,width,heig;

    protected Entity(int x, int y, int tileWidth){
        this.x = x;
        this.y = y;
        this.tileWidth = tileWidth;
    }


    protected abstract BufferedImage getImage();

    public void draw(Graphics g){
        BufferedImage b = getImage();
        int x = (int)this.x;
        int y = (int)this.y;
        g.drawImage(b,x-b.getWidth()/2,y-b.getHeight()/2,null);
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public abstract void tick(int force, double direction);

    protected void move(int force, double direction){
        this.dx = force*Math.cos(direction);
        this.dy = force*Math.sin(direction);
        int dx = (int) this.dx,dy = (int) this.dy;
        int dMax;
        if(Math.abs(dx)>Math.abs(dy)){
            dMax = Math.abs(dx);
        } else {
            dMax = Math.abs(dy);
        }
        double xStep = (dx/(double)dMax);
        double yStep = (dy/(double)dMax);

        for(int i=0;i<dMax;i++){
            x+=xStep;
            y+=yStep;

        }
    }

    public void setMap(HashMap<Integer, Entity> entityMap){
        this.entityMap = entityMap;
    }
}
