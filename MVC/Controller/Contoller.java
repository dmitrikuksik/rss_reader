package MVC.Controller;

import MVC.Model.MessageList;
import MVC.Model.Model;
import MVC.Model.NewsMessage;
import MVC.Model.NewsTableModel;
import MVC.View.CenterPanel;
import MVC.View.GUI;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.Transient;
import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by User on 07.01.2017.
 */
public class Contoller {
    /**Model programu*/
    private Model model;
    /**Głowny interfejs użytkownika*/
    private GUI view;
    /**Listener do przycisków*/
    private ActionListener actionListener;
    /**Lista dodanych kanalów*/
    private ArrayList<String> channelList = new ArrayList<>();

    /**
     * Inicjalizator pól {@link Contoller#model}, {@link Contoller#view}
     * @see Contoller
     */
    public  Contoller(Model model,GUI view){
        this.model = model;
        this.view = view;
        this.view.getMainPanel().getCenterPanel().getChannelList().updateUI();
    }

    /**
     * Glowny kontroler calej aplikacji.
     * Lacze GUI z Modelem
     */
    public void control(){
            actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==view.getMainPanel().getCenterPanel().getSearchButton()){
                    String url = view.getMainPanel().getCenterPanel().getTextField().getText();
                    if(!isAdded(url)) {
                        createRSSChannel(url);
                        showRSSChannels();
                    }else{
                        JOptionPane.showMessageDialog(view.getFrame(),"Link has been added","Has added",JOptionPane.INFORMATION_MESSAGE);
                    }
                    view.getMainPanel().getCenterPanel().getTextField().setText("");
                }
                if(e.getSource()==view.getMainPanel().getCenterPanel().getGoBrowser()) {openBrowser();}
                if (e.getSource()==view.getSaveMenuItem()){
                    model.exportToTxt();
                }

                          }
        };
        MouseListener tableMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                showOneMessage(e);
           }
        };
        MouseListener channelMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==1) {openChannel();}
                if(e.getClickCount()==2) {updateChannel();}
            }
        };
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_DELETE){
                    deleteChannel();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        view.getMainPanel().getCenterPanel().getSearchButton().addActionListener(actionListener);
        view.getMainPanel().getCenterPanel().getNewsTable().addMouseListener(tableMouseListener);
        view.getMainPanel().getCenterPanel().getGoBrowser().addActionListener(actionListener);
        view.getMainPanel().getCenterPanel().getChannelList().addMouseListener(channelMouseListener);
        view.getSaveMenuItem().addActionListener(actionListener);
        view.getMainPanel().getCenterPanel().getChannelList().addKeyListener(keyListener);
        }
    /**
     *Funkcja zaminia browser na textPane
     */
    private void resetBrowser(){
        //view.getMainPanel().getCenterPanel().getTextandbrowserPanel().remove(view.getMainPanel().getCenterPanel().getBrowserView());
        view.getMainPanel().getCenterPanel().getTextandbrowserPanel().add(view.getMainPanel().getCenterPanel().getGoBrowser(),BorderLayout.AFTER_LAST_LINE);
        view.getMainPanel().getCenterPanel().getTextandbrowserPanel().add(view.getMainPanel().getCenterPanel().getTextScrollPane(),BorderLayout.CENTER);
        view.getMainPanel().getCenterPanel().updateUI();

    }
    /**
     * Wyswetla wybrana nowosc z tablicy
     * @param event
     */
    private void showOneMessage(MouseEvent event){
        int row = view.getMainPanel().getCenterPanel().getNewsTable().getSelectedRow();
        int rowChannel = view.getMainPanel().getCenterPanel().getChannelList().getSelectedIndex();
        resetBrowser();
        view.getMainPanel().getCenterPanel().getTextPane().setText("");
        String title = model.getMessageList().get(rowChannel).getChannel().get(row).getTitle();
        String description = model.getMessageList().get(rowChannel).getChannel().get(row).getDescription();
        String date = model.getMessageList().get(rowChannel).getChannel().get(row).getPubdate();
        String link = model.getMessageList().get(rowChannel).getChannel().get(row).getLink();

        model.getMessageList().get(rowChannel).getChannel().get(row).setSeen("Seen");
        view.getMainPanel().getCenterPanel().styledTextPane(title,description,date,link);
        view.getMainPanel().getTopPanel().getTopLabel().setText(model.getMessageList().get(rowChannel).getChannel().get(row).getTitle());
        view.getMainPanel().getCenterPanel().getNewsTable().updateUI();

    }

    /**
     * Funkcja sprawdza czy kanal o takiej nazwe istnieje
     * @param url
     * @return
     */
    private boolean isAdded(String url){
        boolean booleanFlag = false;
        for(int i = 0; i < channelList.size(); i++){
            if (url.equals(model.getMessageList().get(i).getUrl())){
                return true;

        }}
    return booleanFlag;
    }

    /**
     * Funkcja wyswietla wszytkie dodane kanaly
     */
    private void showRSSChannels(){

        channelList.add(model.getMessageList().get(model.getMessageList().size()-1).getTitle());
        String[] channels = new String[channelList.size()];
        final String[] listChannels = channelList.toArray(channels);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.getMainPanel().getCenterPanel().getChannelList().setListData(listChannels);
                view.getMainPanel().getCenterPanel().getChannelList().setSelectedIndex(listChannels.length-1);
            }
        });
    }

    /**
     * Funkcja tworzy nowy kanal i wstawia wiadomosci do tablicy
     * @param url
     */
    private void createRSSChannel(String url){
        try{
                model.setURL(url);
        }catch (MalformedURLException e){
                view.getOptionPane().showMessageDialog(view.getFrame(),"Incorrect channel link.","Link error",JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException();
        };
        try{
                MessageList channel = model.getParser().parserStAX();
                channel.setUrl(url);
                model.getMessageList().add(channel);
                view.getMainPanel().getCenterPanel().getModel().getNewsDataList().clear();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        for (NewsMessage message : channel.getChannel()){
                            view.getMainPanel().getCenterPanel().getModel().addData(message);
                         }
                        view.getMainPanel().getTopPanel().getTopLabel().setText(view.getMainPanel().getCenterPanel().getModel().getValueAt(0,0).toString());
                        view.getMainPanel().getCenterPanel().getNewsTable().updateUI();
                }});
                model.getModelsList().add(view.getMainPanel().getCenterPanel().getModel());
        }
         catch (IOException exc){
                JOptionPane.showMessageDialog(view.getFrame(),"Incorrect channel link.","Link error",JOptionPane.ERROR_MESSAGE);
        }


    }

    /**
     * Otwiera wybrany kanal
     */
    private void openChannel(){
        int row = view.getMainPanel().getCenterPanel().getChannelList().getSelectedIndex();
        NewsTableModel tableModel = new NewsTableModel();
        tableModel.setNewsDataList(model.getMessageList().get(row).getChannel());
        view.getMainPanel().getCenterPanel().getNewsTable().setModel(tableModel);
        view.getMainPanel().getCenterPanel().getNewsTable().updateUI();
    }

    /**
     * Po wcisnieciu przyciska otwiera przegladarke
     */
    private void openBrowser(){
        int row = view.getMainPanel().getCenterPanel().getNewsTable().getSelectedRow();
        int rowChannel = view.getMainPanel().getCenterPanel().getChannelList().getSelectedIndex();
        String link = model.getMessageList().get(rowChannel).get(row).getLink();
        view.getMainPanel().getCenterPanel().getBrowser().loadURL(link);
        view.getMainPanel().getCenterPanel().getTextandbrowserPanel().remove(view.getMainPanel().getCenterPanel().getGoBrowser());
        view.getMainPanel().getCenterPanel().getTextandbrowserPanel().remove(view.getMainPanel().getCenterPanel().getTextScrollPane());
        view.getMainPanel().getCenterPanel().getTextandbrowserPanel().add(view.getMainPanel().getCenterPanel().getBrowserView());
        view.getMainPanel().getCenterPanel().updateUI();
    }

    /**
     *Odswieza informacje na kanale
     */
    private void updateChannel(){
        int row = view.getMainPanel().getCenterPanel().getChannelList().getSelectedIndex();
        String url = model.getMessageList().get(row).getUrl();
        try{
            model.setURL(url);
        }catch (MalformedURLException exc){
            throw new RuntimeException();
        }
        try {
            MessageList channel = model.getParser().parserStAX();
            model.getMessageList().set(row,channel);
            model.getMessageList().get(row).setUrl(url);
            openChannel();
            view.getMainPanel().getCenterPanel().getNewsTable().updateUI();
        }catch (IOException e){
            throw new RuntimeException();
        }

    }

    /**
     * Funkcja kasuje wybrany kanal po wcisnieciu DELETE
     */
    private void deleteChannel(){
        int row = view.getMainPanel().getCenterPanel().getChannelList().getSelectedIndex();
        channelList.remove(row);
        model.getMessageList().remove(row);
        setToChannelList();
        if(model.getMessageList().size()==0){
            NewsTableModel tableModel = new NewsTableModel();
            view.getMainPanel().getCenterPanel().getNewsTable().setModel(tableModel);
            JOptionPane.showMessageDialog(view.getFrame(),"Channel list is empty.Add channel to your list","Empty list",JOptionPane.INFORMATION_MESSAGE);
            view.getMainPanel().getTopPanel().getTopLabel().setText("");
            view.getMainPanel().getCenterPanel().getTextPane().setText("");
            view.getMainPanel().getCenterPanel().getNewsTable().updateUI();
        }

    }

    /**
     * Funkcja konwertuje {@link Contoller#channelList} w tablice wierszow
     * @see Contoller
     */
    private void setToChannelList(){
        String[] channels = new String[channelList.size()];
        final String[] listChannels = channelList.toArray(channels);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.getMainPanel().getCenterPanel().getChannelList().setListData(listChannels);
            }
        });
    }
}





