package MVC.View;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import MVC.Model.NewsTableModel;



/**
 * Created by User on 30.12.2016.
 */
public class GUI {
    /**Glowny frame tego programu*/
    private JFrame frame = new JFrame("RSS Feeder");
    /**Menu bar*/
    private JMenuBar menuBar;
    /**Menu bar*/
    private JMenu menu;
    /**Jeden element menu*/
    private JMenuItem saveMenuItem;
    /**Panel glowna*/
    private MainPanel mainPanel;
    /**Box wiadomosci*/
    private JOptionPane optionPane;

    /**
     * Konstruktor
     */
    public GUI(){
        //newsTable.setColumnModel();
        menuBar = new JMenuBar();

        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        menuBar.add(menu);

        saveMenuItem = new JMenuItem("Save");
        menu.add(saveMenuItem);

        mainPanel = new MainPanel();
        optionPane = new JOptionPane();
        frame.setMinimumSize(new Dimension(950,300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(optionPane);
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Zwraca panel glowny
     * @return
     */
    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    /**
     * Zwraca glowny frame
     * @return
     */
    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JOptionPane getOptionPane() {
        return optionPane;
    }

    public void setOptionPane(JOptionPane optionPane) {
        this.optionPane = optionPane;
    }

    public JMenuItem getSaveMenuItem() {
        return saveMenuItem;
    }

    public void setSaveMenuItem(JMenuItem saveMenuItem) {
        this.saveMenuItem = saveMenuItem;
    }


}
