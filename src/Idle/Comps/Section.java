package Idle.Comps;

import javax.swing.*;
import java.awt.*;

public class Section extends JPanel {
    public Section(String title, int w, int h){
        setPreferredSize(new Dimension(w,h));
        setBackground(Color.black);
        setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 4));
    }
}
