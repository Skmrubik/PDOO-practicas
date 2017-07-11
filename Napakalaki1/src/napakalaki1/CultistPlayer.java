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
public class CultistPlayer extends Player{
    private static int totalCultistPlayers =0;
    Cultist myCultistCard;
    
    public CultistPlayer(Player p, Cultist c){
        super(p);
        myCultistCard = c;
        totalCultistPlayers++;
    }
    @Override
    public int getCombatLevel(){
       int uno = super.getCombatLevel();
       int dos = (super.getCombatLevel()/5);
       int tres = myCultistCard.getGainedLevels();
       int lvl = (uno + dos + tres)*totalCultistPlayers;
       super.cLSectario=lvl;
       return lvl;
    }
    @Override
    public int getOponentLevel( Monster m){
        return m.getCombatLevelAgainstCultistPlayer();
    }
    @Override
    public boolean shouldConvert(){
        return false;
    }
    private Treasure giveMeATreasure(){
        int random = (int) (Math.random()*super.getVisibleTreasures().size());
        return super.getVisibleTreasures().get(random);
    }
    
    private boolean canYouGiveMeATreasure(){
        if(enemy.getVisibleTreasures().size()>0){
            return true;
        }
        else{
            return false;
        }
    }
    public int getTotalCultistPlayers(){
        return totalCultistPlayers;
    }
}
