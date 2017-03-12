package MVC.View;


import javax.swing.*;
import javax.swing.text.*;

import MVC.Model.NewsTableModel;
import java.awt.*;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import java.awt.event.KeyEvent;

/**
 * Created by User on 12.01.2017.
 */
public class CenterPanel extends JPanel {
/**Panel z lewego boku - zawira liste kanalow , pole do wpisywania linku oraz przycisk "Search"*/
    private JPanel leftPanel;
    /**Panel centralny zawiera tablice oraz pole textu oraz browser*/
    private JPanel rightPanel;
    /**Panel z tablica*/
    private JPanel tablePanel;
/**Lista kanalow*/
    private JList<String> channelList;
    private JScrollPane channelScrollPane;
    /**Przysick przejscia po linku*/
    private JButton searchButton;
    /**Pole wpisywania linku*/
    private JTextField textField;

    /**Model tablicy*/
    private NewsTableModel model = new NewsTableModel();
    /**Tablica*/
    private JTable newsTable = new JTable(model);
    private JScrollPane tableScrollPane;

    private JScrollPane textScrollPane;
    /**Pole tekstu,w ktorym wypisuje jedna wybrana wiadomosc*/
    private JTextPane textPane;
    /**Panel browsera*/
    private JPanel textandbrowserPanel;
    /**Przycisk przejdz do przegladarki*/
    private JButton goBrowser;

    /**Przeglandarka*/
    private Browser browser;
    private BrowserView browserView;
    /**Rodzaj tekstu*/
    private Font titleFont = new Font("Times New Roman",Font.BOLD,20) ;
    /**Rodzaj tekstu*/
    private Font descriptionFont = new Font("Verdana",Font.TRUETYPE_FONT,12) ;
    /**Rodzaj tekstu*/
    private Font dateFont = new Font("Calibri",Font.TRUETYPE_FONT,6);

    /**
     * Konstruktor paneli
     */
    public CenterPanel() {

        createLeftPanel();
        createRightPanel();
        this.setLayout(new BorderLayout());
        this.add(leftPanel,BorderLayout.LINE_START);
        this.add(rightPanel,BorderLayout.CENTER);

    }
    private void createLeftPanel(){
        channelList = new JList<>();
        channelList.setToolTipText("Tap two times to update");
        channelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        channelScrollPane = new JScrollPane(channelList);

        searchButton = new JButton("Search");
        textField = new JTextField(20);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.LINE_AXIS));
        textField.setToolTipText("Write RSS channel link");

        buttonPanel.add(textField);
        buttonPanel.add(Box.createHorizontalStrut(5));
        searchButton.setMnemonic(KeyEvent.VK_ENTER);
        buttonPanel.add(searchButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(buttonPanel,BorderLayout.NORTH);
        leftPanel.add(channelScrollPane,BorderLayout.CENTER);
        leftPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("CHANNELS"), BorderFactory.createEmptyBorder(5,5,5,5)));

    }

    private void createRightPanel(){
        newsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        newsTable.setShowGrid(false);
        newsTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        newsTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        newsTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        tableScrollPane = new JScrollPane(newsTable);

        textPane = new JTextPane();
        textPane.setLayout(new BorderLayout());
        textPane.setEditable(false);
        textScrollPane = new JScrollPane(textPane,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        goBrowser = new JButton("Show page");
        textandbrowserPanel = new JPanel(new BorderLayout());
        textandbrowserPanel.add(textScrollPane,BorderLayout.CENTER);
        textandbrowserPanel.add(goBrowser,BorderLayout.AFTER_LAST_LINE);

        /*browser = new Browser();
        browserView = new BrowserView(browser);*/

        tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(tableScrollPane);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,tablePanel,textandbrowserPanel);
        splitPane.setDividerLocation(380);
        splitPane.setOneTouchExpandable(true);

        rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(splitPane);
        rightPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("NEWS"),BorderFactory.createEmptyBorder(5,5,5,5)));

    }
    /**
     * Dostep do lewej paneli
     * @return
     */
    public JPanel getLeftPanel() {
        return leftPanel;
    }

    public void setLeftPanel(JPanel leftPanel) {
        this.leftPanel = leftPanel;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public void setRightPanel(JPanel rightPanel) {
        this.rightPanel = rightPanel;
    }

    public JPanel getTablePanel() {
        return tablePanel;
    }

    public void setTablePanel(JPanel tablePanel) {
        this.tablePanel = tablePanel;
    }

    /**
     * Zwraca liste dodanych kanalow
     * @return
     */
    public JList<String> getChannelList() {
        return channelList;
    }

    /**
     * Dodaje do listy kanaly
     * @param channelList
     */
    public void setChannelList(JList<String> channelList) {
        this.channelList = channelList;
    }

    public JScrollPane getChannelScrollPane() {
        return channelScrollPane;
    }

    public void setChannelScrollPane(JScrollPane channelScrollPane) {
        this.channelScrollPane = channelScrollPane;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(JButton searchButton) {
        this.searchButton = searchButton;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public NewsTableModel getModel() {
        return model;
    }

    public void setModel(NewsTableModel model) {
        this.model = model;
    }

    public JTable getNewsTable() {
        return newsTable;
    }

    public void setNewsTable(JTable newsTable) {
        this.newsTable = newsTable;
    }

    public JScrollPane getTableScrollPane() {
        return tableScrollPane;
    }

    public void setTableScrollPane(JScrollPane tableScrollPane) {
        this.tableScrollPane = tableScrollPane;
    }

    public JScrollPane getTextScrollPane() {
        return textScrollPane;
    }

    public void setTextScrollPane(JScrollPane textScrollPane) {
        this.textScrollPane = textScrollPane;
    }

    public JTextPane getTextPane() {
        return textPane;
    }

    public void setTextPane(JTextPane textPane) {
        this.textPane = textPane;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public BrowserView getBrowserView() {
        return browserView;
    }

    public void setBrowserView(BrowserView browserView) {
        this.browserView = browserView;
    }

    public JPanel getTextandbrowserPanel() {
        return textandbrowserPanel;
    }

    public void setTextandbrowserPanel(JPanel textandbrowserPanel) {
        this.textandbrowserPanel = textandbrowserPanel;
    }

    public JButton getGoBrowser() {
        return goBrowser;
    }

    public void setGoBrowser(JButton goBrowser) {
        this.goBrowser = goBrowser;
    }

    public Font getTitleFont() {
        return titleFont;
    }

    public void setTitleFont(Font titleFont) {
        this.titleFont = titleFont;
    }

    public Font getDescriptionFont() {
        return descriptionFont;
    }

    public void setDescriptionFont(Font descriptionFont) {
        this.descriptionFont = descriptionFont;
    }

    public Font getDateFont() {
        return dateFont;
    }

    public void setDateFont(Font dateFont) {
        this.dateFont = dateFont;
    }

    /**
     * Zwraca Text pane ze zmienionym stylem tekstu
     * @param title
     * @param description
     * @param date
     * @param link
     * @return
     */
    public JTextPane styledTextPane(String title,String description,String date,String link){
        String[] initString = {
                title+"\n\n",
                description+"\n\n\n",
                date+"\n\n",
                link,
        };
        String[] initStyles = {"bold","regular","small","italic"};
        StyledDocument doc = textPane.getStyledDocument();
        addStylesToDocument(doc);
        try{
            for (int i = 0; i < initString.length; i++){
                doc.insertString(doc.getLength(),initString[i],doc.getStyle(initStyles[i]));
            }
        } catch (BadLocationException ble){
            System.err.println("ERROR insert text");
        }
        return textPane;
    }

    /**
     * Dodaje do dokumentu rozne rodzai formotowania tekstu
     * @param doc
     */
    protected void addStylesToDocument(StyledDocument doc){
        Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def, "SansSerif");

        Style s = doc.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);
        StyleConstants.setFontSize(s,10);

        s = doc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);
        StyleConstants.setFontSize(s,20);
        StyleConstants.setForeground(s,Color.blue);

        s = doc.addStyle("small", regular);
        StyleConstants.setFontSize(s, 12);
        StyleConstants.setForeground(s,Color.GRAY);

        s = doc.addStyle("large", regular);
        StyleConstants.setFontSize(s, 16);

    }
}
