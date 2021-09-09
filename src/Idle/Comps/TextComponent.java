package Idle.Comps;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class TextComponent extends IdleComponent implements MouseWheelListener {

    private static final int TEXT_MARGIN = 10;

    static final Font font = new Font("Monospaced", Font.PLAIN, 16);
    private List<String> text;
    private boolean scrollable;
    private int scroll=0;
    private int h;
    private int shownChars, totalChars;

    public TextComponent(int w, int h, boolean hideChars, String text) {
        super(w,h);
        this.setFont(TextComponent.font);
        this.setText(text, w, h, hideChars);

    }

    private List<String> fixText(int w, String text) {
        ArrayList<String> strings = new ArrayList<>();
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        for (String s : text.split("\n")) {
            String[] words = s.split(" ");
            StringBuilder newString = new StringBuilder();
            for (String word : words) {
                int sw = fontMetrics.stringWidth(newString + word);
                if (sw >= w - 30) {
                    strings.add(newString.toString());
                    newString = new StringBuilder(word + " ");
                } else {
                    newString.append(word).append(" ");
                }
            }
            strings.add(newString.toString());
            strings.add("");
        }
        return strings;
    }

    public void showChars(int chars){
        shownChars = Math.min(shownChars + chars, totalChars);
    }

    public void showText(){
        this.shownChars = totalChars;
    }

    public void setText(String text, boolean hidden){
        setText(text, getWidth(), this.h , hidden);
    }

    public void setText(String text, int w, int h, boolean hidden){
        this.text = fixText(w, text);
        this.totalChars = 0;
        for (String s : this.text) totalChars+= s.length();
        shownChars = hidden ? 0 : totalChars;
        this.h = getFontMetrics(font).getHeight() * this.text.size();
        if (h > this.h) {
            this.h = h;
            scrollable = false;
            removeMouseWheelListener(this);
        } else {
            scrollable = true;
            addMouseWheelListener(this);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.fillRect(0,0,getWidth(),getHeight());
        g.translate(0, scroll);
        g.setColor(Color.WHITE);
        int shown = 0, fontHeight = g.getFontMetrics().getHeight();
        for (int i = 0; i < text.size(); i++) {
            String s = text.get(i);
            int toShow = Math.min(shownChars - shown, s.length());
            g.drawString(s.substring(0, toShow), TEXT_MARGIN,   fontHeight * (i + 1));
            shown += toShow;
        }
        g.translate(0,-scroll);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll+=e.getScrollAmount()*-e.getWheelRotation()*3;
        if(scroll>0) scroll=0;
        if (scroll< getHeight()-h) scroll = -h+getHeight();
        repaint();
        System.out.println("Scrolled!");
    }

    public boolean allCharsShown() {
        return shownChars >= totalChars;
    }

    public int getShownChars() {
        return shownChars;
    }
}
