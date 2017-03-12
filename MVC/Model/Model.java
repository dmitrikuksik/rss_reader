package MVC.Model;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 07.01.2017.
 * Klasa modeli
 */
public class Model {
    /**Lista kanalow*/
    private List<MessageList> messageList = new ArrayList<>();
    /**Parser glowny*/
    private ParserXML parser;
    /**Lista modeli do tablicy*/
    private List<NewsTableModel> modelsList = new ArrayList<>();

    /**
     * Ustawia URL do kanalu
     * @param URL
     * @throws MalformedURLException
     */
    public void setURL(String URL) throws MalformedURLException {
        parser = new ParserXML(URL);
    }

    /**
     * Zwraca wszystkie dodane kanaly
     * @return
     */
    public List<MessageList> getMessageList() {
        return messageList;
    }

    /**
     * Ustawia kanaly
     * @param messageList
     */
    public void setMessageList(List<MessageList> messageList) {
        this.messageList = messageList;
    }

    /**
     * Zwraca parser
     * @return
     */
    public ParserXML getParser() {
        return parser;
    }

    /**
     * Ustawia parser
     * @param parser
     */
    public void setParser(ParserXML parser) {
        this.parser = parser;
    }

    /**
     * Zwraca liste modeli
     * @return
     */
    public List<NewsTableModel> getModelsList() {
        return modelsList;
    }

    /**
     * UStawia liste modeli
     */
    public void setModelsList() {

        for (int i = 0; i < messageList.size(); i++) {
            NewsTableModel newsTableModel = new NewsTableModel();
            newsTableModel.setNewsDataList(messageList.get(i).getChannel());
            modelsList.add(newsTableModel);
        }
    }

    /**
     * Eksportuje kanaly do pliku TXT
     */
    public void exportToTxt() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(".\\RSS.txt"));

            for (int i = 0; i < messageList.size(); i++) {
                writer.write("###");
                writer.newLine();
                writer.write(messageList.get(i).getUrl());
                writer.newLine();
                writer.write(messageList.get(i).getTitle());
                writer.newLine();
                writer.write(messageList.get(i).getLink());
                writer.newLine();
                writer.write(messageList.get(i).getDescription());
                writer.newLine();
                writer.write(messageList.get(i).getCopyright());
                writer.newLine();
                writer.write(messageList.get(i).getLanguage());
                writer.newLine();
                writer.write("***");
                writer.newLine();
                for (int j = 0; j < messageList.get(i).getChannel().size(); j++) {
                    writer.write(messageList.get(i).getChannel().get(j).getTitle());
                    writer.newLine();
                    writer.write(messageList.get(i).getChannel().get(j).getSeen());
                    writer.newLine();
                    writer.write(messageList.get(i).getChannel().get(j).getDescription());
                    writer.newLine();
                    writer.write(messageList.get(i).getChannel().get(j).getPubdate());
                    writer.newLine();
                    writer.write(messageList.get(i).getChannel().get(j).getLink());
                    writer.newLine();
                }
                writer.write("###");
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

