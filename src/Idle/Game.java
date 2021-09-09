package Idle;

import Idle.Comps.*;
import Idle.Comps.Button;
import Idle.Comps.CenterFlowLayout;
import Idle.Comps.TextComponent;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.*;

public class Game implements KeyListener, MouseListener {

    private final TextBatch coreText;

    private final Map<Integer, Boolean> keys;

    private Game(){
        this.coreText = new TextBatch();
        this.keys = new HashMap<>();
        keys.put(KeyEvent.VK_SPACE, false);
    }




    public void initFrame(){
        JFrame f = new JFrame();
        JPanel j = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                draw(g);
            }
        };
        try {
            coreText.load("textBox1.txt");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        j.setPreferredSize(new Dimension(1920,1080));
        j.setBackground(Color.black);
        f.setUndecorated(true);
        f.add(j);
        f.pack();
        j.setLayout(new CenterFlowLayout(CenterFlowLayout.UP_DOWN,CenterFlowLayout.AUTO));
        j.setFocusable(true);
        j.requestFocus();
        j.addKeyListener(this);

        Section s = new Section("",1800,120);
        j.add(s);
        s.setLayout(new CenterFlowLayout(CenterFlowLayout.LEFT_RIGHT,10));

        JPanel j2 = new JPanel();
        j2.setLayout(new CenterFlowLayout(CenterFlowLayout.LEFT_RIGHT,CenterFlowLayout.AUTO));
        j2.setPreferredSize(new Dimension(1920,800));
        j2.setBackground(Color.black);
        TextComponent textComponent = new TextComponent(360,900,true, coreText.getNext());
        j2.add(textComponent);

        ImageComp imageComp = new ImageComp(8 * 128 + 4, 5 * 128 + 4);
        j2.add(imageComp);

        j2.add(new Section("",360,900));
        j.add(j2);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setBackground(Color.black);
        f.setVisible(true);
        f.setResizable(false);
        Map<JComponent, IteratorRunnable> componentActions = new HashMap<>();
        componentActions.put(imageComp, (iterator -> imageComp.tick()));
        componentActions.put(textComponent, (iterator)->{
            textComponent.showChars(1);
            textComponent.repaint();
        });
        new Timer(16, (e)->{
            Iterator<IteratorRunnable> iterator = componentActions.values().iterator();
            while (iterator.hasNext()){
                iterator.next().run(iterator);
            }
            if(keys.get(KeyEvent.VK_SPACE)){
                if (textComponent.allCharsShown()) textComponent.setText(coreText.getNext(), true);
                else textComponent.showText();
            }
        }).start();


}


    public static void main(String[] args){
        try {
            Explosion.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Game()::initFrame);
    }

    private void draw(Graphics g) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE) System.exit(0);
        if (e.getKeyCode()==KeyEvent.VK_SPACE) keys.put(KeyEvent.VK_SPACE, true);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_SPACE) keys.put(KeyEvent.VK_SPACE, false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
