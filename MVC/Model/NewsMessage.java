package MVC.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by User on 06.12.2016.
 */
public class NewsMessage {
    /**Tytul wiadomosci*/
    private String title;
    /**Tresc wiadomosci*/
    private String description;
    /**Data pulikacji wiadomosci*/
    private String pubdate;
    /**Autor ktory napisal artykul*/
    private String author;
    /**Link do wiadomosci*/
    private String link;
    /**Link do wiadomosci*/
    private String guid;
    /**Data publikacji*/
    private Date publishDate;
    /**Sprawdza czy wiadomosc juz przegladana*/
    private String seen = "*";

    /**
     * Zwraca wiersz wiadomosci
     * @return
     */
    @Override
    public String toString() {
        return "NewsMessage{" +
                "tittle=" + title +
                ", description='" + description + '\'' +
                ", pubdate='" + pubdate + '\'' +
                ", author='" + author + '\'' +
                ", link='" + link + '\'' +
                ", guid='" + guid + '\'' +
                '}';
    }

    /**
     * Ustawia tresc
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Zwraca tresc wiadomosci
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Ustawia autora
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Zwraca autora
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Zwraca date publikacji
     * @return
     */
    public String getPubdate() {
        return pubdate;
    }

    /**
     * Ustawia date publikacji
     * @param pubdate
     */

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    /**
     * Ustawia link wiadomosci
     * @param link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Zwraca link wiadomosci
     * @return
     */
    public String getLink() {
        return link;
    }

    /**
     * Ustawia link
     * @param guid
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * Zwraca guid wiadomosci
     * @return
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Zwraca tytul
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
     * Zwraca date publikacji
     * @return
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * Ustawia date publikaci
     * @param publishDate
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * Konwertuje date z String w Date
     * @throws ParseException
     */
    public void convertToDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        this.publishDate = formatter.parse(this.pubdate);

    }

    /**
     * Zwraca czy byla ogladana wiadomocs
     * @return
     */
    public String getSeen() {
        return seen;
    }

    /**
     * Ustawia seen
     * @param seen
     */
    public void setSeen(String seen) {
        this.seen = seen;
    }
}
