package Idle.Comps;

import java.awt.*;
import java.io.Serializable;

public class CenterFlowLayout implements LayoutManager {
    public static final int LEFT_RIGHT = 0, RIGHT_LEFT = 1, UP_DOWN = 2, DOWN_UP = 3, AUTO = -1;
    private int flow, margin, altMargin;

    public CenterFlowLayout(int flow, int margin) {
        this.flow = flow;
        this.margin = margin >= -1 ? margin : 0;
        this.altMargin = Math.max(margin, 0);
    }

    public CenterFlowLayout(int flow, int margin, int altMargin) {
        this.flow = flow;
        this.margin = margin >= -1 ? margin : 0;
        this.altMargin = Math.max(altMargin, 0);
    }

    private void calculateMargins(Container parent) {
        Component[] c = parent.getComponents();
        int w = parent.getWidth();
        int h = parent.getHeight();
        switch (flow) {
            case LEFT_RIGHT:
            case RIGHT_LEFT:
                int cWidth = 0;
                double maxH = 0;
                for (Component component : c) {
                    cWidth += component.getPreferredSize().getWidth();
                    if (component.getPreferredSize().getHeight() > maxH)
                        maxH = component.getPreferredSize().getHeight();
                }
                margin = (w - cWidth) / (c.length + 1);
                if (maxH > h) maxH = h;
                altMargin = (int) ((h - maxH) / 2);
                break;
            case UP_DOWN:
            case DOWN_UP:
                int cHeight = 0;
                double maxW = 0;
                for (Component component : c) {
                    cHeight += component.getPreferredSize().getHeight();
                    if (component.getPreferredSize().getWidth() > maxW) maxW = component.getPreferredSize().getWidth();
                }
                margin = (h - cHeight) / (c.length + 1);
                if (maxW > w) maxW = w;
                altMargin = (int) ((w - maxW) / 2);
                break;
            default:
                margin = 0;
                altMargin = 0;
                break;
        }


    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        System.out.println("nope");
    }

    @Override
    public void removeLayoutComponent(Component comp) {

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return null;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return null;
    }

    @Override
    public void layoutContainer(Container parent) {
        Component[] c = parent.getComponents();
        int w = parent.getWidth();
        int h = parent.getHeight();
        if (margin == AUTO) calculateMargins(parent);
        switch (flow) {
            case (LEFT_RIGHT):
                for (int i = 0, x = margin; i < c.length; i++) {
                    Component co = c[i];
                    Dimension ps = co.getPreferredSize();
                    int wi = ps.width;
                    int he = ps.height;
                    if (he > h - altMargin * 2) he = h - altMargin * 2;
                    co.setBounds(x, h / 2 - he / 2, wi, he);
                    x += (margin + wi);
                }
                break;
            case (RIGHT_LEFT):
                for (int i = 0, x = w - margin; i < c.length; i++) {
                    Component co = c[i];
                    Dimension ps = co.getPreferredSize();
                    int wi = ps.width;
                    int he = ps.height;
                    if (he > h - margin * 2) he = h - margin * 2;
                    co.setBounds(x - wi, h / 2 - he / 2, wi, he);
                    x -= (margin + wi);
                }
                break;
            case (UP_DOWN):
                for (int i = 0, y = margin; i < c.length; i++) {
                    Component co = c[i];
                    Dimension ps = co.getPreferredSize();
                    int wi = ps.width;
                    int he = ps.height;
                    if (wi > w - altMargin * 2) wi = w - altMargin * 2;
                    co.setBounds(w / 2 - wi / 2, y, wi, he);
                    y += (margin + he);
                }
                break;
            case (DOWN_UP):
                for (int i = 0, y = h - margin; i < c.length; i++) {
                    Component co = c[i];
                    Dimension ps = co.getPreferredSize();
                    int wi = ps.width;
                    int he = ps.height;
                    if (he > h - altMargin * 2) he = h - altMargin * 2;
                    co.setBounds(w / 2 - wi / 2, y - he, wi, he);
                    y -= (margin + he);
                }
                break;
        }
    }
}
