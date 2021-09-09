package Idle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Explosion {
    private int frame, x, y;
    private static final BufferedImage[] explosion = new BufferedImage[1];
    public boolean done;

    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
        this.frame = 0;
        this.done = false;
    }

    public void tick() {
        frame++;
        if (frame >= 44) done = true;
    }

    public static void load() throws IOException {
        explosion[0] = ImageIO.read(ClassLoader.getSystemResource("images/explosion.png"));
        /*for (int i = 0; i < 44; i++) {
            explosion[i] = ImageIO.read(Game.class.getResource("Assets/explosion.png")).getSubimage((i % 8) * (2048 / 8), (i / 8) * (1488 / 6), 2048 / 8, 1488 / 6);
        }*/

    }

    public void draw(Graphics g) {
        if (done) return;
        BufferedImage img = explosion[0].getSubimage((frame % 8) * (2048 / 8), (frame / 8) * (1488 / 6), 2048 / 8, 1488 / 6);
        g.drawImage(img/*explosion[frame]*/, x - /*explosion[frame]*/img.getWidth() / 2, y - /*explosion[frame]*/img.getHeight() / 2, null);
    }
}
