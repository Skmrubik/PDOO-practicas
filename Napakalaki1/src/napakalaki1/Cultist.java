/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki1;

/**
 *
 * @author TOSHIBA
 */
public class Cultist {
    private String name;
    private int gainedLevels;
    
    public Cultist(String n, int gL){
        name=n;
        gainedLevels= gL;
    }
    public int getGainedLevels(){
        return gainedLevels;
    }
}
