/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templatesjo
 * and open the template in the editor.
 */
package napakalaki1;

/**
 *
 * @author TOSHIBA
 */
import GUI.Dice;
import GUI.NapakalakiView;
import GUI.PlayerNamesCapture;
import Test.GameTester;
import java.util.ArrayList;

public class EjemploMain {

    public static void main(String[] args) {
      
     /* Napakalaki game = Napakalaki.getInstance();
      GameTester test = GameTester.getInstance();
      
     

       test.play(game, 3); 
      */
       Napakalaki game= Napakalaki.getInstance();
        NapakalakiView napakalakiView = new NapakalakiView();
        Dice.createInstance (napakalakiView);
        
        PlayerNamesCapture namesCapture = new PlayerNamesCapture(napakalakiView, true);
        ArrayList<String> names = new ArrayList();
         names = namesCapture.getNames();
         game.initGame(names);
         
        
        napakalakiView.setNapakalaki(game);
        napakalakiView.setVisible (true);
              
    }
}