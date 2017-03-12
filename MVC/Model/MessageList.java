package MVC.Model;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by User on 06.12.2016.
 */

public class MessageList {
    /**Naglowek kanalu*/
    private String title;
    /**Copyright kanalu*/
    private String copyright;
    /**Tresc kanalu*/
    private String description;
    /**Link do kanalu*/
    private String link;
    /**Jezyk kanalu*/
    private String language;
    /**Ostatnia data zmian kanalu*/
    private String lastBuildDate;
    /**Link do kanalu RSS*/
    private String url;
    /**Lista wiadomosci tego kanalu*/
    private List <NewsMessage> channel = new ArrayList<NewsMessage>();


    /**
     * Inicijalizuje zmienne
     * @param title
     * @param copyright
     * @param description
     * @param link
     * @param language
     * @param lastBuildDate
     */
    MessageList(String title,String copyright,String description,String link,String language,String lastBuildDate){
        this.title = title;
        this.copyright = copyright;
        this.description = description;
        this.link = link;
        this.language = language;
        this.lastBuildDate = lastBuildDate;

    }
    MessageList(){}

    /**
     * Zwraca jedna wiadomosc z kanalu o zadanym indeksie
     * @param index
     * @return
     */
    public NewsMessage get(int index){
        return channel.get(index);
    };

    /**
     * Zwraca liste wiadomosci tego kanalu
     * @return
     */
    public List<NewsMessage> getChannel() {
        return channel;
    }

    /**
     * Zwaraca tytul kanalu
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Ustawia tytul
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Zwraca copyright kanalu
     * @return
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Ustawia copyright kanalu
     * @param copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * Zwraca opis kanalu
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Ustawia opis kanalu
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Zwraca link kanalu
     * @return
     */
    public String getLink() {
        return link;
    }

    /**
     * Ustawia link do kanalu
     * @param link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Zwraca jezyk kanalu
     * @return
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Zwraca wiadomosc jednym wierszem
     * @return
     */
    @Override
    public String toString() {
        return "MessageList{" +
                "title='" + title + '\'' +
                ", copyright='" + copyright + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", language='" + language + '\'' +
                ", pubdate='" + lastBuildDate + '\'' +
                '}';
    }

    /**
     * Sortuje wedlug ostatniej daty
     * @param messageList
     */
    public void sort(List<NewsMessage> messageList){
        Collections.sort(messageList, new Comparator<NewsMessage>() {
            @Override
            public int compare(NewsMessage o1, NewsMessage o2) {
                try {
                    o1.convertToDate();
                    o2.convertToDate();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(o1.getPublishDate() == null) return -1;
                if(o2.getPublishDate() == null) return 1;

                return o2.getPublishDate().compareTo(o1.getPublishDate());
            }
        });
    }

    /**
     * Zwraca link do kanulu RSS
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * Ustawia link do kanaulu RSS
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }


}
