package Idle.Comps;

import javax.swing.*;
import java.awt.*;

public abstract class IdleComponent extends JComponent {
    IdleComponent(int w,int h){
        this.setPreferredSize(new Dimension(w,h));
        setBackground(Color.black);
        setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 4));
    }
}
