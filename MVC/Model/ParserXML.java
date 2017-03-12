package MVC.Model;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by User on 07.12.2016.
 */
public class ParserXML {


    static final String ITEM = "item";
    static final String DESCRIPTION = "description";
    static final String PUBDATE = "pubDate";
    static final String COPYRIGHT = "copyright";
    static final String TITLE = "title";
    static final String LANGUAGE = "language";
    static final String AUTHOR = "author";
    static final String LINK = "link";
    static final String GUID = "guid";
    static final String LASTBUILDDATE = "lastBuildDate";
    final URL url;


    /**
     * Konstruktor parsera
     * @param url
     * @throws MalformedURLException
     */
    public ParserXML(String url) throws MalformedURLException {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            throw new MalformedURLException();
        }
    }

    /**
     * Wczytuje strumienem xml plik
     * @return
     * @throws IOException
     */
    private InputStream read() throws IOException {
        try {
            return this.url.openStream();
        } catch (IOException exp) {
            throw new IOException();
        }
    }

    /**
     * Parser - Zwraca kanal z wiadomosciami, zrealizowany metoda StAX
     * @return
     * @throws IOException
     */
    public MessageList parserStAX() throws IOException {
        boolean isFirstItem = true;
        MessageList feed = null;
        String title = "";
        String copyright = "";
        String description = "";
        String link = "";
        String language = "";
        String pubdate = "";
        String lastbuilddate = "";
        String author = "";
        String guid = "";

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            InputStream in = read();
            XMLEventReader eventReader = factory.createXMLEventReader(in);

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName().getLocalPart();
                    switch (localPart) {
                        case ITEM:
                            if (isFirstItem) {
                                feed = new MessageList(title, copyright, description, link, language, lastbuilddate);

                            }

                            isFirstItem = false;
                            break;
                        case DESCRIPTION:
                            description = getData(eventReader,event);
                            break;
                        case TITLE:
                            title = getData(eventReader,event);
                            break;
                        case LINK:
                            link = getData(eventReader,event);
                            break;
                        case PUBDATE:
                            pubdate = getData(eventReader,event);
                            break;
                        case AUTHOR:
                            author = getData(eventReader,event);
                            break;
                        case COPYRIGHT:
                            copyright = getData(eventReader,event);
                            break;
                        case LANGUAGE:
                            language = getData(eventReader,event);
                            break;
                        case LASTBUILDDATE:
                            lastbuilddate = getData(eventReader,event);
                            break;
                        case GUID:
                            guid = getData(eventReader,event);
                            break;

                    }
                } else if(event.isEndElement()){
                    if(event.asEndElement().getName().getLocalPart() == (ITEM)){
                        NewsMessage message = new NewsMessage();
                        message.setDescription(description);
                        message.setAuthor(author);
                        message.setPubdate(pubdate);
                        message.setLink(link);
                        message.setGuid(guid);
                        message.setTitle(title);
                        feed.getChannel().add(message);
                        event = eventReader.nextEvent();
                    }
                }
            }
        } catch (XMLStreamException exc){
            throw new IOException();
        }
        feed.sort(feed.getChannel());
    return feed;
    }

    /**
     * Zwraca informacje z pliku xml
     * @param eventReader
     * @param event
     * @return
     * @throws XMLStreamException
     */
    private String getData(XMLEventReader eventReader,XMLEvent event) throws XMLStreamException{

            event = eventReader.nextEvent();
            String information = "";
            if (event instanceof Characters){
                information = event.asCharacters().getData();
                   }
        return information;
        }

    /**
     * Zraca link, ktory byl przekazany do parsowania
     * @return
     */
    public URL getUrl() {
        return url;
    }
}



