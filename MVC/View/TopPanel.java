package MVC.View;

import com.sun.prism.*;
import com.sun.prism.paint.Gradient;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

/**
 * Created by User on 12.01.2017.
 */
public class TopPanel extends JPanel {
    /**Obrazek na tlo*/
    private Image background;
    /**Wyswietla tytul wiadomosci*/
    private JLabel topLabel;
    private Font font = new Font("Verdana",Font.BOLD,20) ;

    public TopPanel()  {
        ImageIcon backgroundIcon = new ImageIcon(this.getClass().getResource("/resources/images/Rss.jpg"));
        background = backgroundIcon.getImage();
        topLabel = new JLabel();
        topLabel.setFont(font);
        topLabel.setText("RSSReader");
        this.setLayout(new BorderLayout());
        this.add(topLabel,BorderLayout.LINE_START);
        this.add(Box.createVerticalStrut(80));

    }

    /**
     * Rysuje tlo tego panelu
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background,0,0,1400,80,this);
        }

    /**
     * Zwraca lable
     * @return
     */
    public JLabel getTopLabel() {
        return topLabel;
    }

    public void setTopLabel(JLabel topLabel) {
        this.topLabel = topLabel;
    }
}
