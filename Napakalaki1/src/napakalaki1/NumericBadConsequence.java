/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki1;

import java.util.ArrayList;

/**
 *
 * @author TOSHIBA
 */

public class NumericBadConsequence extends BadConsequence{
    static final int MAXTREASURES = 10;
    protected int nVisibleTreasures;
    protected int nHiddenTreasures;   
    public NumericBadConsequence(String t, int l, int nV, int nH){
        super(t, l);
        nVisibleTreasures = nV;
        nHiddenTreasures = nH;
    }    
    public int getVisible(){
        return nVisibleTreasures;
    }    
    public int getHidden(){
        return nHiddenTreasures;
    }    
    @Override
    public boolean isEmpty(){
        return (nVisibleTreasures ==0) && (nHiddenTreasures== 0);
    }
    
    @Override
    public void substractVisibleTreasure(Treasure t){
        if (nVisibleTreasures >0){
            nVisibleTreasures-=1;
        }
    }    
    @Override
    public void substractHiddenTreasure(Treasure t){
        if (nVisibleTreasures > 0){
            nVisibleTreasures-=1;
        }
    }
    @Override
    public NumericBadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h){
        int visible =nVisibleTreasures;
        int hidden =nHiddenTreasures;       
        if (v.size()< nVisibleTreasures)
            visible= v.size();
        if (h.size() <nHiddenTreasures)
            hidden =h.size();
        NumericBadConsequence badC= new NumericBadConsequence("", 0, visible, hidden);
        return badC;
    }
    
    @Override
    public String toString(){
        return super.toString();
    }
}
