package MVC.Model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.*;

/**
 * Created by User on 08.01.2017.
 */
public class NewsTableModel implements TableModel {
/**Ustawia sluchacza na tablice*/
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    /**Informacja*/
    private ArrayList<String []> dataArrayList;
    /**Inforamcja modeli - lista wiadomosci*/
    private List<NewsMessage> newsDataList;
    /**Kopiuje informcaje do innej listy- lista pomocnicza*/
    private List<NewsMessage> copyData;
    /**ilosc kolumn*/
    private int columnCount = 4;
    /***Konstruktor modeli tablicy*/
    public NewsTableModel(){
        //dataArrayList = new ArrayList<String []>();
        newsDataList = new ArrayList<>();
        for (int i = 0; i < newsDataList.size(); i++){
            newsDataList.add(new NewsMessage());
        }
    }

    /**
     * Dodanie sluchacza do modeli
     * @param listener
     */
    public void addTableModelListener(TableModelListener listener){
        listeners.add(listener);
    }

    /**
     * Zwraca klas wybranego elementu tablicy
     * @param columnIndex
     * @return
     */
    public Class<?> getColumnClass(int columnIndex){
        return String.class;
    }

    /**
     * Zwraca ilosc kolumn w modeli
     * @return
     */
    public int getColumnCount(){
        return columnCount;
    }

    /***
     * Zwraca tytuly kolumn
     * @param columnIndex
     * @return
     */
    public String getColumnName(int columnIndex){
        switch (columnIndex){
            case 0:
                return "Title";
            case 1:
                return "Date";
            case 2:
                return "Author";
            case 3:
                return "Unread";
        }
        return "";
    }

    /**
     * Zwraca ilosc rzedow
     * @return
     */
    public int getRowCount(){
        return newsDataList.size();
    }

    /**
     * Zwraca wartosc wybranego elementu
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    public Object getValueAt(int rowIndex, int columnIndex){
        NewsMessage message = newsDataList.get(rowIndex);
        switch (columnIndex){
            case 0:
                return message.getTitle();
            case 1:
                return message.getPubdate();
            case 2:
                return message.getAuthor();
            case 3:
                return message.getSeen();
        }
        return "";

    }

    /**
     * Ustawia wartosc
     * @param value
     * @param rowIndex
     * @param columnIndex
     */
    public void setValueAt(Object value,int rowIndex, int columnIndex){
        NewsMessage message = newsDataList.get(rowIndex);
        switch (columnIndex){
            case 0:
                 message.setTitle(value.toString());
                 break;
            case 1:
                 message.setPubdate(value.toString());
                break;
            case 2:
                message.setAuthor(value.toString());
                break;
            case 3:
                message.setSeen(value.toString());
        }}

    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }

    /**
     * Usuwa sluchaca z tablicy
     * @param listener
     */
    public void removeTableModelListener (TableModelListener listener){
        listeners.remove(listener);
    }

    /**
     * Dodaje 1 wiadomosc do listy informacji
     * @param message
     */
    public void addData(NewsMessage message){
        newsDataList.add(message);
    }

    /**
     * Zwraca cala informacje modeli
     * @return
     */
    public List<NewsMessage> getNewsDataList() {
        return newsDataList;
    }

    /**
     * Ustawia informacje modeli
     * @param newsDataList
     */
    public void setNewsDataList(List<NewsMessage> newsDataList) {
        this.newsDataList = newsDataList;
    }

    /**
     * Zwraca 1 wiadomosc
     * @param row
     * @return
     */
    public NewsMessage getOneMessage(int row){
        return newsDataList.get(row);
    }

    /**
     * Kopiuje informacje
     * @return
     */
    public List<NewsMessage> getCopyData() {
        return copyData;
    }

    /**
     * Ustawia informcaje
     * @param copyData
     */
    public void setCopyData(List<NewsMessage> copyData) {
        this.copyData = copyData;
    }

}
