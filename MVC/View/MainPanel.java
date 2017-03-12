package MVC.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by User on 12.01.2017.
 */
public class MainPanel extends JPanel{
    /**Gorna panel*/
    private TopPanel topPanel;
    /**Panel centralny*/
    private CenterPanel centerPanel;

    /**
     * Konstruktor glownego panelu
     */
    public MainPanel(){
        topPanel = new TopPanel();
        centerPanel = new CenterPanel();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.add(topPanel,BorderLayout.NORTH);
        this.add(centerPanel,BorderLayout.CENTER);
    }

    /**
     * Zwraca gorny panel
     * @return
     */
    public TopPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(TopPanel topPanel) {
        this.topPanel = topPanel;
    }

    /**
     * Zwraca centralny panel
     * @return
     */
    public CenterPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(CenterPanel centerPanel) {
        this.centerPanel = centerPanel;
    }
}
