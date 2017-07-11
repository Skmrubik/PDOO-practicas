/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author TOSHIBA
 */
public class CardDealer {
    private ArrayList<Monster> unusedMonster = new ArrayList();
    private ArrayList<Monster> usedMonsters = new ArrayList();
    private ArrayList<Treasure> unusedTreasures = new ArrayList();
    private ArrayList<Treasure> usedTreasures = new ArrayList();
    private ArrayList<Cultist> unusedCultist = new ArrayList();
    private static final CardDealer INSTANCE = new CardDealer();
    private CardDealer() {
    }
    public static CardDealer getInstance() {
        return INSTANCE;
    }  
   public void initTreasureCardDeck(){
       unusedTreasures.add(new Treasure("¡Sí mi amo!",4,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("¡Botas de investigación!",3,TreasureKind.SHOES));
        unusedTreasures.add(new Treasure("Capucha de Cthilhu",3,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("A prueba de babas",2,TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Botas de lluvia ácida",1,TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Casco minero",2,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Ametralladora Thompson",4,TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Camiseta de la UGR",1,TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Clavo de rail ferroviario",3,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Cuchillo de sushi arcano",2,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Fez alópodo",3,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Hacha prehistórica",2,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("El aparato del Pr. Tesla",4,TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Gaita",4,TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Insecticida",2,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Escopeta de 3 cañones",4,TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Garabato Mistico",2,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("La rebeca metálica",2,TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Mazo de los antiguos",3,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necro-playboycon",3,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Lanzallamas",4,TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Necro-comicón",1,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necronomicón",5,TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Linterna a 2 manos",3,TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Necro-gnomicón",2,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necrotelecom",2,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Porra preternatural",2,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Tentácula de pega",2,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Zapato deja-amigos",1,TreasureKind.SHOES));
        unusedTreasures.add(new Treasure("Shogulador",1,TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Varita de atizamiento",3,TreasureKind.ONEHAND));
        shuffleTreasures();
   } 
   private void initMonsterCardDeck(){
        //Monstruo1
        BadConsequence mal_rollo = new SpecificBadConsequence("Pierdes tu armadura visible y otra oculta.",0,
        new ArrayList(Arrays.asList(TreasureKind.ARMOR)),new ArrayList(Arrays.asList(TreasureKind.ARMOR)));
        Prize premio = new Prize(2,1);
        unusedMonster.add(new Monster("Byakhees de bonanza",8,mal_rollo,premio));
        //Monstruo2
        mal_rollo = new SpecificBadConsequence("Embobados con el lindo primigenio te descartas de tu casco visible",0,
        new ArrayList(Arrays.asList(TreasureKind.HELMET)),new ArrayList());
        premio= new Prize(1,1);
        unusedMonster.add(new Monster("Chibithulhu",2,mal_rollo,premio));
        //Monstruo3
        mal_rollo= new SpecificBadConsequence("El primordial bostezo contagioso.Pierdes el calzado visible",0,
        new ArrayList(Arrays.asList(TreasureKind.SHOES)),new ArrayList());
        premio =new Prize(1,1);
        unusedMonster.add(new Monster("El sopor de Dunwich",2,mal_rollo,premio));
        //Monstruo4
        mal_rollo = new SpecificBadConsequence("Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo"
        + "Descarta una mano visible y otra oculta",0,new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),
        new ArrayList(Arrays.asList(TreasureKind.ONEHAND)));
        premio = new Prize(4,1);
        unusedMonster.add(new Monster("Angeles de la noche ibicenca",14,mal_rollo,premio));
        //Monstruo5
        mal_rollo=new NumericBadConsequence("Pierdes todos tus tesoros visibles",0,10,0);
        premio=new Prize(3,1);
        unusedMonster.add(new Monster("El gorron en el umbral",10,mal_rollo,premio));
        //Monstruo6
        mal_rollo=new SpecificBadConsequence("Pierdes la armadura visible",0,new ArrayList(Arrays.asList(TreasureKind.ARMOR)),
        new ArrayList());
        premio=new Prize(2,1);
        unusedMonster.add(new Monster("H.P. Munchcraft",6,mal_rollo,premio));
        //Monstruo7
        mal_rollo=new SpecificBadConsequence("Sientes bichos bajo la ropa. Descarta la armadura visible",0,
        new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList());
        premio=new Prize(1,1);
        unusedMonster.add(new Monster("Bichgooth",2,mal_rollo,premio));
        //Monstruo8
        mal_rollo=new NumericBadConsequence("Pierdes 5 niveles y 3 tesoros visibles",5,3,0);
        premio=new Prize(4,2);
        unusedMonster.add(new Monster("El rey de rosa",13,mal_rollo,premio));
        //Monstruo9
        mal_rollo=new NumericBadConsequence("Toses los pulmones y pierdes 2 niveles",2,0,0);
        premio=new Prize(1,1);
        unusedMonster.add(new Monster("La que redacta en las tinieblas",2,mal_rollo,premio));
        //Monstruo10
        mal_rollo=new DeathBadConsequence("Estos monstruos resultan bastante superficiales y te aburren mortalmente. Estas muerto");
        premio=new Prize(2,1);
        unusedMonster.add(new Monster("Los Hondos",8,mal_rollo,premio));
        //Monstrio11
        mal_rollo=new NumericBadConsequence("Pierdes 2 niveles y 2 tesoros ocultos",2,0,2);
        premio= new Prize(2,1);
        unusedMonster.add(new Monster("Semillas Cthulhu",4,mal_rollo,premio));
        //Monstruo12
        mal_rollo=new SpecificBadConsequence("Te intentas escaquear. Pierdes una mano visible",0,
        new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),new ArrayList());
        premio=new Prize(2,1);
        unusedMonster.add(new Monster("Dameargo",1,mal_rollo,premio));
        //Monstruo13
        mal_rollo=new NumericBadConsequence("Da mucho asquito. Pierdes 3 niveles",3,0,0);
        premio=new Prize(1,1);
        unusedMonster.add(new Monster("Pollipolipo volante",3,mal_rollo,premio));
        //Monstruo14
        mal_rollo=new DeathBadConsequence("No le hace gracia que pronuncien mal su nombre. Estas muerto");
        premio=new Prize(3,1);
        unusedMonster.add(new Monster("Yskhtihyssg-Goth",12,mal_rollo,premio));
        //Monstruo15
        mal_rollo=new DeathBadConsequence("La familia te atrapa. Estas muerto");
        premio=new Prize(4,1);
        unusedMonster.add(new Monster("Familia feliz",1,mal_rollo,premio));
        //Monstruo16
        mal_rollo=new SpecificBadConsequence("La quinta directiva primaria te obliga a perder 2 niveles" + 
                "y un tesoro 2 manos visibles",2,new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS)),
        new ArrayList());
        premio=new Prize(2,1);
        unusedMonster.add(new Monster("Roboggoth",8,mal_rollo,premio));
        //Monstruo17
        mal_rollo=new SpecificBadConsequence("Te asusta en la noche. Pierdes un casco visible",0,
        new ArrayList(Arrays.asList(TreasureKind.HELMET)),new ArrayList());
        premio=new Prize(1,1);
        unusedMonster.add(new Monster("El espia",5,mal_rollo,premio));
        //Monstruo18
        mal_rollo=new NumericBadConsequence("Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles",2,5,0);
        premio=new Prize(1,1);
        unusedMonster.add(new Monster("El lenguas",20,mal_rollo,premio));
        //Monstruo19
        mal_rollo=new SpecificBadConsequence("Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos",3,
        new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS)),new ArrayList());
        unusedMonster.add(new Monster("Bicefalo",20,mal_rollo,premio));
        
        //Monstruos sectarios
        mal_rollo = new SpecificBadConsequence("Pierdes 1 mano visible",0, new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),new ArrayList());
        premio = new Prize(3,1);
        unusedMonster.add(new Monster("El mal indecible impronunciable", 10, mal_rollo, premio,-2));
        
        mal_rollo = new NumericBadConsequence("Pierdes tus tesoros visibles, jajaja",0,10,0);
        premio = new Prize(2,1);
        unusedMonster.add(new Monster("Testigos oculares", 6, mal_rollo, premio, 2));
        
        mal_rollo = new DeathBadConsequence("Hoy no es tu dia de suerte. Mueres");
        premio = new Prize(2,5);
        unusedMonster.add(new Monster("El gran cthulhu", 20, mal_rollo, premio, 4));
        
        mal_rollo = new NumericBadConsequence("Tu gobierno te recorta 2 nuveles",2,0,0);
        premio = new Prize(2,1);
        unusedMonster.add(new Monster("Serpiente politico", 8, mal_rollo, premio, -2));
        
        mal_rollo = new SpecificBadConsequence("Pierdes tu casco y tu armadura visible. Pierdes tus manos ocultas",0,
        new ArrayList(Arrays.asList(TreasureKind.HELMET, TreasureKind.ARMOR )),new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS)));
        premio = new Prize(1,1);
        unusedMonster.add(new Monster("Felpuggoth", 2, mal_rollo, premio, 5));
        
        mal_rollo = new NumericBadConsequence("Pierdes 2 nuveles",2,0,0);
        premio = new Prize(4,2);
        unusedMonster.add(new Monster("Shoggoth", 16, mal_rollo, premio, -4));
        
        mal_rollo = new NumericBadConsequence("Pintalabios negro. Pierdes 2 niveles",2,0,0);
        premio = new Prize(1,1);
        unusedMonster.add(new Monster("Lolitagooth", 2, mal_rollo, premio, 3));
   }
   private void initCultistCardDeck(){
        unusedCultist.add(new Cultist("01",1));
        unusedCultist.add(new Cultist("02",2));
        unusedCultist.add(new Cultist("03",1));
        unusedCultist.add(new Cultist("04",2));
        unusedCultist.add(new Cultist("05",1));
        unusedCultist.add(new Cultist("06",1));
    }
   public Treasure nextTreasure(){
       if(unusedTreasures.isEmpty()==true){
            for(int i=0;i<usedTreasures.size();i++)
                unusedTreasures.add(usedTreasures.get(i));
            shuffleTreasures();
            for(int i=0;i<usedTreasures.size();i++)
                usedTreasures.remove(i);
        }
        Treasure t=unusedTreasures.get(0);
        giveTreasureBack(t);
        unusedTreasures.remove(t);
        return t;
   }
   public Monster nextMonster(){
       if(unusedMonster.isEmpty()==true){
            for(int i=0;i<usedMonsters.size();i++){
                unusedMonster.add(usedMonsters.get(i));
            shuffleMonsters();
            }
            for(int i=0;i<usedMonsters.size();i++)
                usedMonsters.remove(i);
        }
        Monster m = unusedMonster.get(0);
        giveMonsterBack(m);
        unusedMonster.remove(m);
        return m;
   }
   public Cultist nextCultist(){
       Cultist c = unusedCultist.get(0);
       unusedCultist.remove(c);
       return c;
   }
   public void giveTreasureBack(Treasure t){
       usedTreasures.add(t);
   }
   public void giveMonsterBack(Monster m){
       usedMonsters.add(m);
   }
   public void initCards(){
       initTreasureCardDeck();
       initMonsterCardDeck();
       initCultistCardDeck();
       shuffleTreasures();
       shuffleMonsters();
       shuffleCultist();
   }
   private void shuffleTreasures(){
       Collections.shuffle(this.unusedTreasures);
   }
   private void shuffleMonsters(){
       Collections.shuffle(this.unusedMonster);
   }
   private void shuffleCultist(){
       Collections.shuffle(this.unusedCultist);
   }
}
