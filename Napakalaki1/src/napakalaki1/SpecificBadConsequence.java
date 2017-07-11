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

public class SpecificBadConsequence extends BadConsequence{
    private ArrayList<TreasureKind> specificVisibleTreasures;
    private ArrayList<TreasureKind> specificHiddenTreasures;
    
    public SpecificBadConsequence(String t, int l, ArrayList<TreasureKind> tV, 
                            ArrayList<TreasureKind> tH){
        super(t, l);
        specificVisibleTreasures = new ArrayList(tV);
        specificHiddenTreasures = new ArrayList(tH);
    }
    @Override
    public boolean isEmpty() {
        return specificVisibleTreasures.isEmpty() && specificHiddenTreasures.isEmpty();
    }
    @Override
    public void substractVisibleTreasure(Treasure t) {
        int num = specificVisibleTreasures.size();
        for (int i=0; i<num; i++){
            if (t.getType() == specificVisibleTreasures.get(i)){
                specificVisibleTreasures.remove(i);
                break;
            }
        }
    }
    @Override
    public void substractHiddenTreasure(Treasure t) {
        int num = specificHiddenTreasures.size();
        for (int i=0; i<num; i++){
            if (t.getType() == specificHiddenTreasures.get(i)){
                specificHiddenTreasures.remove(i);
                break;
            }
        }
    }
    @Override
    public SpecificBadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h) {
        int size;
        ArrayList<TreasureKind> visible = new ArrayList(specificVisibleTreasures);
        ArrayList<TreasureKind> aux =new ArrayList(visible);
        for (Treasure treasure: v){
            size = aux.size();
            for (int i=0; i<size; i++){
                if (aux.get(i)== treasure.getType()){
                    aux.remove(i);
                    break;
                }
            }
        }       
        for (TreasureKind tKind : aux){
            visible.remove(tKind);
        }
        ArrayList<TreasureKind> hidden = new ArrayList(specificHiddenTreasures);
        aux = new ArrayList(hidden);
        for (Treasure treasure : v){
            size = aux.size();
            for (int i=0; i<size; i++){
                if (aux.get(i) ==treasure.getType()){
                    aux.remove(i);
                    break;
                }
            }
        }  
        for (TreasureKind tKind : aux){
            hidden.remove(tKind);
        }
        SpecificBadConsequence newBadCons= new SpecificBadConsequence("", 0, visible, hidden);
        return newBadCons;
    }
    @Override
    public String toString(){
        return super.toString();
    }
}