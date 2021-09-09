package Idle.Entities;

import Idle.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Box extends Entity {

    private final static BufferedImage[] images = new BufferedImage[1];
    static {
        try {
            images[0] = ImageIO.read(Game.class.getResource("Assets/box.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Box(int x, int y,int tileWidth) {
        super(x, y, tileWidth);
        this.weight = 10;
    }



    @Override
    protected BufferedImage getImage() {
        return images[0];
    }

    @Override
    public void tick(int force, double direction) {
        super.move(force,direction);
    }
}
