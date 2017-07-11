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
public class Monster {
    private String name;
    private int CombatLevel;
    private Prize p;
    private BadConsequence bad;
    private int levelChangeAgainstCultistPlayer=0;
    public Monster(String n, int level, BadConsequence bc, Prize price){
        name=n;
        CombatLevel=level;
        p=price;
        bad=bc;
    }
    public Monster(String n, int level, BadConsequence bc, Prize price, int lC){
        name=n;
        CombatLevel=level;
        p=price;
        bad=bc;
        levelChangeAgainstCultistPlayer=lC;
    }
    public int getCombatLevelAgainstCultistPlayer(){
        return CombatLevel+levelChangeAgainstCultistPlayer;
    }
    public String getName(){
                return name;
            }
    public int getCombatLevel(){
                return CombatLevel;
    }
    public Prize getPrize(){
        return p;
    }
    public BadConsequence getBadConsequence(){
        return bad;
    }
    public int getTreasuresGained(){
        return p.getTreasures();
    }
    public int getLevelsGained(){
        return p.getLevel();
    }
    public String toString(){
        return "Name = "+name+ "\n"+ "Combat Level = "+Integer.toString(CombatLevel)+ "\n" + "Mal rollo: " + bad.toString()+ "\n" + "Premio: " + p.toString()+"\n";
    }
}
