package MVC;

import MVC.Controller.Contoller;
import MVC.Model.Model;
import MVC.View.GUI;
import javax.swing.*;

/**
 * Created by User on 08.11.2016.
 * RSS Reader
 * Program wyswitla wiadomosci z kanalu RSS
 */
public class MVC {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI view = new GUI();
                Model model = new Model();
                Contoller contoller = new Contoller(model,view);
                contoller.control();

                 }
        });
       }
}
