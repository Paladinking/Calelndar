package Idle.Comps;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Button extends IdleComponent implements MouseListener {
    private String[] btnText;
    private Color color;
    private int[] stringWidth;
    private final ArrayList<Runnable> onPress;

    public Button(int w, int h, String[] btnText, int textSize) {
        super(w, h);
        this.addMouseListener(this);
        this.setBtnText(btnText, textSize);
        this.onPress = new ArrayList<>();
        this.color = Color.BLACK;
    }

    public Button(int w, int h, String... btnText) {
        this(w, h, btnText, 12);
    }

    public Button(int w, int h) {
        this(w, h, "");
    }

    public void setBtnText(String[] btnText, int textSize) {
        this.btnText = btnText;
        this.stringWidth = new int[btnText.length];
        this.setFont(new Font(TextComponent.font.getName(), Font.PLAIN, textSize));
        for (int i = 0; i < btnText.length; i++) {
            this.stringWidth[i] = getFontMetrics(getFont()).stringWidth(this.btnText[i]);
        }
    }

    public Button addAction(Runnable r) {
        this.onPress.add(r);
        return this;
    }

    public void removeAction(Runnable action) {
        onPress.remove(action);
    }

    public void removeAction(int index) {
        onPress.remove(index);
    }

    public void clearActions() {
        onPress.clear();
    }

    public void press() {
        for (Runnable r : onPress) r.run();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        for (int i = 0; i < btnText.length; i++) {
            g.drawString(btnText[i], getWidth() / 2 - stringWidth[i] / 2, getHeight() / 2
                    + g.getFont().getSize() / 2 * btnText.length - (btnText.length-1 - i) * (g.getFont().getSize() + 2));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        setColor(Color.gray);
    }

    private void setColor(Color color) {
        this.color = color;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!this.contains(e.getX(), e.getY())) return;
        setColor(Color.darkGray);
        press();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setColor(Color.darkGray);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setColor(Color.BLACK);

    }
}
