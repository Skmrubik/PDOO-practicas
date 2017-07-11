/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki1;
import GUI.Dice;
import java.util.ArrayList;

/**
 *
 * @author TOSHIBA
 */
public class Player {
    static final int  MAXLEVEL= 10;
    private String name;
    private int level;
    private boolean dead=true;
    private boolean canISteal=true;
    private ArrayList<Treasure> hiddenTreasures=new ArrayList();
    private ArrayList<Treasure> visibleTreasures=new ArrayList();
    private BadConsequence pendingBadConsequence;
    public int cLSectario;
    protected Player enemy;
    public boolean esSectario=false;
    
    public Player(String n){
        name=n;
        level=1;
        dead=false;
}
    public Player (Player p){
        this.name = p.name;
        this.level = p.level;
        this.pendingBadConsequence = p.pendingBadConsequence;
        this.dead = p.dead;
        this.hiddenTreasures = p.hiddenTreasures;
        this.visibleTreasures = p.visibleTreasures;
        this.enemy = p.enemy;
        this.canISteal= p.canISteal;
        this.esSectario=p.esSectario;
       
    }
    public BadConsequence getPending(){
        return pendingBadConsequence;
    }
    protected Player getEnemy(){
        return enemy;
    }
    public String nombreEnemigo(){
        return enemy.getName();
    }
    public String getName(){
        return name;
    }
    private  void bringToLife(){
        if(dead==true)
            dead=false;
    }
    public int getOponentLevel(Monster m){
        return m.getCombatLevel();
    }
    public boolean shouldConvert(){
        Dice d = Dice.getInstance();
        int num = d.nextNumber();
        if (num == 1) {
            return true;
        }
        else{
            return false;
        }
    }
    public int getCombatLevel(){
  
        int nivel=level;
        
        for(Treasure v : visibleTreasures){
        nivel=nivel+v.getBonus();  
        }
        return nivel;
        
     }
    
    public int GetCombatLevel(){
        int nivel=level;
        
        for(Treasure v : visibleTreasures){
        nivel=nivel+v.getBonus();  
        }
        return nivel;
    }
    private void incrementLevels(int i){
        level=level+i;
    }
    private void decrementLevels(int i){
        level=level-i;
        if(level<1)
            level=1;
    }
    private void setPendingBadConsequence(BadConsequence b){
        pendingBadConsequence=b;
    }
    private void applyPrize(Monster m){
        int nlevels = m.getLevelsGained();
        incrementLevels(nlevels);
        int ntreasure = m.getTreasuresGained();
        int num= hiddenTreasures.size();
        if(ntreasure>0){
            if(num+ntreasure<5){
            CardDealer dealer = CardDealer.getInstance();
               for(int i=0; i<ntreasure; i++){
                    Treasure treasure = dealer.nextTreasure();
                 hiddenTreasures.add(treasure);            
                 }
            }
            else{
            CardDealer dealer = CardDealer.getInstance();
                for(int i=num; i<4; i++){
                    Treasure treasure = dealer.nextTreasure();
                 hiddenTreasures.add(treasure);            
                 }
            }
        }
    }
    private void applyBadConsequence(Monster m){
        BadConsequence badConsequence = m.getBadConsequence();
         /*   for(Treasure t:visibleTreasures){
                discardVisibleTreasure(t);
            }
            for(Treasure tr:hiddenTreasures){
                discardHiddenTreasure(tr);
            }
        }*/
        int nlevels = badConsequence.getLevels();
        decrementLevels(nlevels);
        BadConsequence pendingBad = badConsequence.adjustToFitTreasureLists(visibleTreasures,hiddenTreasures);
        setPendingBadConsequence(pendingBad);
    }
    private boolean canMakeTreasureVisible(Treasure t){
        boolean puede=true;
        TreasureKind tipo=t.getType();
        if(tipo==TreasureKind.ONEHAND || tipo==TreasureKind.BOTHHANDS){
            int contador_hand=0;
            for(int i=0;i<visibleTreasures.size() && puede;i++){
                if(visibleTreasures.get(i).getType()==TreasureKind.BOTHHANDS){
                    puede=false;
                }
                else if(visibleTreasures.get(i).getType()==TreasureKind.ONEHAND){
                    contador_hand++;
                    if(tipo==TreasureKind.BOTHHANDS) puede=false;
                }
                if(contador_hand==2) puede=false;
            }
        }
        else{
            for(int i=0;i<visibleTreasures.size() && puede;i++){
                if(tipo==visibleTreasures.get(i).getType()){
                    puede=false;
                }
            }
        }
        return puede;
    }
    private int howManyVisibleTreasures(TreasureKind tKind){
        int j=0;
        for(int i=0;i<visibleTreasures.size();i++){
            if(visibleTreasures.get(i).getType()==tKind){
                j++;
            }
        }
        return j;
    }
    private void dieIfNoTreasures(){
        if(hiddenTreasures.isEmpty() && visibleTreasures.isEmpty())
            dead=true;
    }
    public boolean isDead(){
        return dead;
    }
    public ArrayList<Treasure> getHiddenTreasures(){
        
        return hiddenTreasures;
    }
    public ArrayList <Treasure> getVisibleTreasures(){
        return visibleTreasures;
    }
 
    public CombatResult combat(Monster m){
        CombatResult combatResult;
        int myLevel = getCombatLevel();
        int monsterLevel = getOponentLevel(m);
        Napakalaki napakalaki= Napakalaki.getInstance();
        if(myLevel >monsterLevel){
            applyPrize(m);
            if(getLevels()>=MAXLEVEL)
                combatResult = CombatResult.WINGAME;
            else
                combatResult = CombatResult.WIN;
        }
        else{          
            applyBadConsequence(m);
            combatResult = CombatResult.LOSE;
            if(shouldConvert()){
                System.out.println(name);
                combatResult=CombatResult.LOSEANDCONVERT;
                esSectario=true;
            }
        }
        return combatResult;
    }
    public void makeTreasureVisible (Treasure t){
        boolean canI=canMakeTreasureVisible(t);
        if(canI){
            visibleTreasures.add(t);
            hiddenTreasures.remove(t);
        }     
    }
    public void discardVisibleTreasure (Treasure t){
        visibleTreasures.remove(t);
       if(pendingBadConsequence!=null && !pendingBadConsequence.isEmpty()){
           pendingBadConsequence.substractVisibleTreasure(t);
       }
       dieIfNoTreasures();
    }
    public void discardHiddenTreasure (Treasure t){
        hiddenTreasures.remove(t);
        if(pendingBadConsequence!=null && !pendingBadConsequence.isEmpty()){
            pendingBadConsequence.substractHiddenTreasure(t);
        }
        dieIfNoTreasures();
    }
    public boolean validState(){
        //if(/*pendingBadConsequence.isEmpty() &&*/ hiddenTreasures.size()<=4)
        if (pendingBadConsequence == null || (this.pendingBadConsequence.isEmpty() && this.hiddenTreasures.size() <= 4)) 
            return true;
        else
            return false;
    }
    public void initTreasures(){
        CardDealer dealer = CardDealer.getInstance();
        Dice dice = Dice.getInstance();
        bringToLife();
        Treasure treasure = dealer.nextTreasure();
        hiddenTreasures.add(treasure);
        int number = dice.nextNumber();
        
        if(number==6){
            treasure = dealer.nextTreasure();
            hiddenTreasures.add(treasure);
            treasure = dealer.nextTreasure();
            hiddenTreasures.add(treasure);
        }
        if(number>1 && number <6){
            treasure = dealer.nextTreasure();
            hiddenTreasures.add(treasure);
        }
    }
    public int getLevels(){
        return level;
    }
    public Treasure stealTreasure(){
        boolean canI = canISteal();
          Treasure treasure = null;
        if(canI){
            boolean canYou = canYouGiveMeATreasure();
            if(canYou){
                treasure = enemy.giveMeATreasure();
                hiddenTreasures.add(treasure);
                haveStolen();
            }
        }
        return treasure;
    }
    public void setEnemy(Player enemi){
        enemy=enemi;
    }
    private Treasure giveMeATreasure(){
        int random = (int) (Math.random()*hiddenTreasures.size());
        return hiddenTreasures.get(random);
        
    }
    public boolean canISteal(){ 
        if(enemy.hiddenTreasures.size()>0){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean canYouGiveMeATreasure(){
        if(hiddenTreasures.isEmpty())
            return false;
        else
            return true;
    }
    private void haveStolen(){
        canISteal=false;
    }
    public void discardAllTreasures(){     
        for(Treasure t : visibleTreasures)
            discardVisibleTreasure(t);
        for(Treasure tr : hiddenTreasures)
            discardHiddenTreasure(tr);
    }
}
