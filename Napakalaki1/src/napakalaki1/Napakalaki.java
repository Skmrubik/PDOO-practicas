/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki1;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author TOSHIBA
 */
public class Napakalaki {
    private ArrayList<Player> players = new ArrayList();
    private static int turno=0;
    private int indexCurrentPlayer = -1;
    private Monster currentMonster;
    private Player currentPlayer;
    private CardDealer dealer = CardDealer.getInstance();
    private static final Napakalaki INSTANCE = new Napakalaki();
    private Napakalaki() {
    }
    public static Napakalaki getInstance() {
        return Napakalaki.INSTANCE;
    }
    private void initPlayers(ArrayList<String> names){
        for (int i=0;i<names.size();i++){
               players.add(new Player(names.get(i)));
        }
    }
    private Player nextPlayer(){
        if(indexCurrentPlayer==-1){
        Random rnd=new Random();
        indexCurrentPlayer=rnd.nextInt(players.size());
        currentPlayer=players.get(indexCurrentPlayer);
        }
        else{
            indexCurrentPlayer=(indexCurrentPlayer+1)%players.size();
            currentPlayer=players.get(indexCurrentPlayer);
        }
        return currentPlayer;
    }
    public boolean nextTurnAllowed(){
        if(currentPlayer==null){
            return true;
        }
        else return currentPlayer.validState();
    }
    //Asignar enemigos a los jugadores
    private void setEnemies(){
      int random = (int) (Math.random()*(players.size()-1)+1);
      players.get(0).setEnemy(players.get(random));
      random = (int) (Math.random()*(players.size()-1));
      if(random==0)
        players.get(1).setEnemy(players.get(0));
      else
         players.get(1).setEnemy(players.get(2)); 
      random = (int) (Math.random()*(players.size()-1));
      players.get(2).setEnemy(players.get(random));
    }
    public CombatResult developCombat(){
        CombatResult combatResult=currentPlayer.combat(currentMonster);
        dealer.giveMonsterBack(currentMonster);
        if(combatResult== CombatResult.LOSEANDCONVERT){
            //Sustituir al player por uno cultuist
            Cultist carta = dealer.nextCultist();
            CultistPlayer cultist;
            cultist= new CultistPlayer(currentPlayer,carta);
            int num = players.indexOf(currentPlayer);
            System.out.print(num);
            players.set(num, cultist);
            currentPlayer = cultist;
        }
        return combatResult;
    }
    public void discardVisibleTreasures(ArrayList <Treasure> treasures){
        for(Treasure treasure : treasures){
            currentPlayer.discardVisibleTreasure(treasure);
            dealer.giveTreasureBack(treasure);
        }
    }
    public void discardHiddenTreasures(ArrayList <Treasure> treasures){
        for(Treasure treasure : treasures){
            currentPlayer.discardHiddenTreasure(treasure);
            dealer.giveTreasureBack(treasure);
        }
    }
    
    public void makeTreasuresVisible(ArrayList<Treasure> treasures){
        for(Treasure treasure : treasures)
            currentPlayer.makeTreasureVisible(treasure);
    }
    public void initGame(ArrayList<String> players){
        initPlayers(players);
        setEnemies();
        dealer.initCards();
        nextTurn();
    }
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    public Monster getCurrentMonster(){
        return currentMonster;
    }
    public boolean nextTurn(){
        boolean stateOK,dead;
        stateOK=nextTurnAllowed();
        if(stateOK==true){
            currentMonster=dealer.nextMonster();
            currentPlayer=nextPlayer();
            dead=currentPlayer.isDead();
            if(dead==true || turno<3){
                currentPlayer.initTreasures();
                turno++;
            }
            
        }
        return stateOK;
    }
    public boolean endOfGame(CombatResult result){
        if(result==CombatResult.WINGAME)
            return true;
        else
            return false;
    }
}
