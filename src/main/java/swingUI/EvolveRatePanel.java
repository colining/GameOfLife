package swingUI;

import javax.swing.*;
import java.awt.*;

public class EvolveRatePanel  extends JPanel{
    public EvolveRatePanel(){
        this.setLayout(new GridLayout(2,1));
        this.add(new JButton("开始"));
        this.add(new JButton("暂停"));
    }
}
