package Idle.Comps;

import Idle.Explosion;
import Idle.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class ImageComp extends IdleComponent implements MouseListener {
    private static final BufferedImage earth;
    private boolean mouseDown,mouseReleased;
    private final Point mouseLocation = new Point(0,0);

    private final ArrayList<Explosion> explosions;
    static {
        BufferedImage b;
        try {
            b = ImageIO.read(ClassLoader.getSystemResource("images/earth.png"));

        } catch (IOException e) {
            b = null;
        }

        earth = b;
    }

    public ImageComp(int w, int h) {
        super(w, h);
        addMouseListener(this);
        explosions = new ArrayList<>();
        mouseReleased = true;
        repaint();
    }
    public void tick(){
        if (mouseReleased&&mouseDown){
            explosions.add(new Explosion(mouseLocation.x,mouseLocation.y));
            mouseReleased = false;
        }
        for (int i=0;i<explosions.size();i++){
            Explosion e = explosions.get(i);
            e.tick();
            if(e.done){
                explosions.remove(i);
                i--;
            }
        }
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(earth,0,0,null);
        for (int i=0;i<explosions.size();i++){
            explosions.get(i).draw(g);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseDown = true;
        mouseLocation.x = e.getX();
        mouseLocation.y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
        mouseReleased = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
