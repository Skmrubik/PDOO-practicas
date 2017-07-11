# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
#encoding: utf-8
require 'singleton'
require_relative 'treasure.rb'
require_relative 'treasure_kind.rb'
require_relative 'death_bad_consequence.rb'
require_relative 'specific_bad_consequence.rb'
require_relative 'prize.rb'
require_relative 'monster.rb'
require_relative 'cultist.rb'

module NapakalakiGame
  class CardDealer
    include Singleton
    def initialize
      @unusedTreasures = Array.new
      @usedTreasures = Array.new
      @unusedMonsters = Array.new
      @usedMonsters = Array.new
      @unusedCultists = Array.new
    end
    def initTreasureCardDeck
      @unusedTreasures << Treasure.new("Si mi amo", 4, TreasureKind::HELMET)
      @unusedTreasures << Treasure.new("Botas de investigacion", 3, TreasureKind::SHOES)
      @unusedTreasures << Treasure.new("Capucha de Cthulhu", 3, TreasureKind::HELMET)
      @unusedTreasures << Treasure.new("A prueba de babas", 2, TreasureKind::ARMOR)
      @unusedTreasures << Treasure.new("Botas de lluvia acida", 1, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new("Casco minero", 2, TreasureKind::HELMET)
      @unusedTreasures << Treasure.new("Ametralladora Thompson", 4, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new("Camiseta de la UGR", 1, TreasureKind::ARMOR)
      @unusedTreasures << Treasure.new("Clavo de rail ferroviario", 3, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Cuchillo de sushi arcano", 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Fez alopodo", 3, TreasureKind::HELMET)
      @unusedTreasures << Treasure.new("Hacha prehistorica", 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("El aparato del Pr. Tesla", 4, TreasureKind::ARMOR)
      @unusedTreasures << Treasure.new("Gaita", 4, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new("Insecticida", 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Escopeta de 3 canones", 4, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new("Garabato mistico", 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("La rebeca metalica", 2, TreasureKind::ARMOR)
      @unusedTreasures << Treasure.new("Lanzallamas", 4, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new("Necrocomicon", 1, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Necronomicon", 5, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new("Linterna a 2 manos", 3, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new("Necrognomicon", 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Necrotelecom", 2, TreasureKind::HELMET)
      @unusedTreasures << Treasure.new("Mazo de los antiguos", 3, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Necroplayboycon", 3, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Porra preternatural", 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Shogulador", 1, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new("Varita de atizamiento", 3, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Tentaculo de pega", 2, TreasureKind::HELMET)
      @unusedTreasures << Treasure.new("Zapato deja-amigos", 1, TreasureKind::SHOES)
      shuffleTreasures
    end
    def initMonsterCardDeck
      badConsequence = SpecificBadConsequence.new("Pierdes tu armadura visible y otra oculta", 0,[TreasureKind::ARMOR], [TreasureKind::ARMOR])
      prize = Prize.new(2, 1)
      @unusedMonsters << Monster.new("3 Byakhees de bonanza", 8, badConsequence, prize, 0)

      badConsequence = SpecificBadConsequence.new("Embobados con el lindo primigenio te descartas de tu casco visible",0, [TreasureKind::HELMET],Array.new)
      prize = Prize.new(1, 1)
      @unusedMonsters << Monster.new("Chibithulhu", 2, badConsequence, prize, 0)

      badConsequence = SpecificBadConsequence.new("El primordial bostezo contagioso. Pierdes el calzado visible", 0,[TreasureKind::SHOES],Array.new)
      prize = Prize.new(1, 1)
      @unusedMonsters << Monster.new("El sopor de Dunwich", 2, badConsequence, prize, 0)

      badConsequence = SpecificBadConsequence.new("Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta", 0,[TreasureKind::ONEHAND], [TreasureKind::ONEHAND])
      prize = Prize.new(4, 1)
      @unusedMonsters << Monster.new("Angeles de la noche ibicenca", 14, badConsequence, prize, 0)

      badConsequence = NumericBadConsequence.new("Pierdes todos tus tesoros visibles", 0, 10, 0)
      prize = Prize.new(3, 1)
      @unusedMonsters << Monster.new("El gorron en el umbral", 10, badConsequence, prize, 0)

      badConsequence = SpecificBadConsequence.new("Pierdes la armadura visible", 0,[TreasureKind::ARMOR], Array.new)
      prize = Prize.new(2, 1)
      @unusedMonsters << Monster.new("H.P. Munchcraft", 6, badConsequence, prize, 0)

      badConsequence = SpecificBadConsequence.new("Sientes bichos bajo la ropa. Descarta la armadura visible", 0,[TreasureKind::ARMOR], Array.new)
      prize = Prize.new(1, 1)
      @unusedMonsters << Monster.new("Bichgooth", 2, badConsequence, prize, 0)

      badConsequence = NumericBadConsequence.new("Pierdes 5 niveles y 3 tesoros visibles", 5, 3, 0)
      prize = Prize.new(4, 2)
      @unusedMonsters << Monster.new("El rey de la rosa", 13, badConsequence, prize, 0)

      badConsequence = NumericBadConsequence.new("Toses los pulmones y pierdes 2 niveles", 2, 0, 0)
      prize = Prize.new(1, 1)
      @unusedMonsters << Monster.new("La que redacta en las tinieblas", 2, badConsequence, prize, 0)

      badConsequence = DeathBadConsequence.new("Estos monstruos resultan bastante superficiales y te aburren mortalmente. Estas muerto")
      prize = Prize.new(2, 1)
      @unusedMonsters << Monster.new("Los hondos", 8, badConsequence, prize, 0)

      badConsequence = NumericBadConsequence.new("Pierdes 2 niveles y 2 tesoros ocultos", 2, 0, 2)
      prize = Prize.new(2, 1)
      @unusedMonsters << Monster.new("Semillas Cthulhu", 4, badConsequence, prize, 0)

      badConsequence = SpecificBadConsequence.new("Te intentas escaquear. Pierdes una mano visible", 0,[TreasureKind::ONEHAND], Array.new)
      prize = Prize.new(2, 1)
      @unusedMonsters << Monster.new("Dameargo", 1, badConsequence, prize, 0)

      badConsequence = NumericBadConsequence.new("Da mucho asquito. Pierdes 3 niveles", 3, 0, 0)
      prize = Prize.new(1, 1)
      @unusedMonsters << Monster.new("Pollipolipo volante", 3, badConsequence, prize, 0)

      badConsequence = DeathBadConsequence.new("No le hace gracia que pronuncien mal su nombre. Estas muerto")
      prize = Prize.new(3, 1)
      @unusedMonsters << Monster.new("Yskhtihyssg-Goth", 12, badConsequence, prize, 0)

      badConsequence = DeathBadConsequence.new("La familia te atrapa. Estas muerto")
      prize = Prize.new(4, 1)
      @unusedMonsters << Monster.new("Familia feliz", 1, badConsequence, prize, 0)

      badConsequence = SpecificBadConsequence.new("La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible", 2,[TreasureKind::BOTHHANDS], Array.new)
      prize = Prize.new(2, 1)
      @unusedMonsters << Monster.new("Roboggoth", 8, badConsequence, prize, 0)

      badConsequence = SpecificBadConsequence.new("Te asusta en la noche. Pierdes un casco visible", 0,[TreasureKind::HELMET], Array.new)
      prize = Prize.new(1, 1)
      @unusedMonsters << Monster.new("El espia", 5, badConsequence, prize, 0)

      badConsequence = NumericBadConsequence.new("Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles", 2, 5, 0)
      prize = Prize.new(1, 1)
      @unusedMonsters << Monster.new("El lenguas", 20, badConsequence, prize, 0)

      badConsequence = SpecificBadConsequence.new("Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos", 3,[TreasureKind::ONEHAND, TreasureKind::ONEHAND, TreasureKind::BOTHHANDS], Array.new)
      prize = Prize.new(1, 1)
      @unusedMonsters << Monster.new("Bicefalo", 20, badConsequence, prize, 0)
      #SECTARIOS
      badConsequence = SpecificBadConsequence.new("Pierdes una mano visible", 0, [TreasureKind::ONEHAND], Array.new)
      prize = Prize.new(3, 1)
      @unusedMonsters << Monster.new("El mal indecible impronuciable", 10, badConsequence, prize, -2)

      badConsequence = NumericBadConsequence.new("Pierdes todos tus tesoros visibles. Jajaja", 0, 10, 0)
      prize = Prize.new(2, 1)
      @unusedMonsters << Monster.new("Testigos Oculares", 6, badConsequence, prize, 2)

      badConsequence = DeathBadConsequence.new("Hoy no es tu dia de suerte. Mueres")
      prize = Prize.new(2, 5)
      @unusedMonsters << Monster.new("El gran cthulhu", 20, badConsequence, prize, 4)

      badConsequence = NumericBadConsequence.new("El gobierno te recorta 2 niveles", 2, 0, 0)
      prize = Prize.new(2, 1)
      @unusedMonsters << Monster.new("Serpiente politico", 8, badConsequence, prize, -2)

      badConsequence = SpecificBadConsequence.new("Pierdes tu casco y tu armadura visible. Pierdes tus manos ocultas", 0, [TreasureKind::HELMET, TreasureKind::ARMOR],[TreasureKind::ONEHAND, TreasureKind::ONEHAND, TreasureKind::BOTHHANDS])
      prize = Prize.new(1, 1)
      @unusedMonsters << Monster.new("Felpuggoth", 2, badConsequence, prize, 5)

      badConsequence = NumericBadConsequence.new("Pierdes 2 niveles", 2, 0, 0)
      prize = Prize.new(4, 2)
      @unusedMonsters << Monster.new("Shoggoth", 16, badConsequence, prize, -4)

      badConsequence = NumericBadConsequence.new("Pintalabios negro. Pierdes 2 niveles", 2, 0, 0)
      prize = Prize.new(1, 1)
      @unusedMonsters << Monster.new("Lolitagooth", 2, badConsequence, prize, 3)
      shuffleMonsters
    end
    def initCultistCardDeck
      @unusedCultists << Cultist.new("Sectario", 1)
      @unusedCultists << Cultist.new("Sectario", 2)
      @unusedCultists << Cultist.new("Sectario", 1)
      @unusedCultists << Cultist.new("Sectario", 2)
      @unusedCultists << Cultist.new("Sectario", 1)
      @unusedCultists << Cultist.new("Sectario", 1)
      shuffleCultists
    end
    def shuffleTreasures
      @unusedTreasures.shuffle!
    end
    def shuffleMonsters
      @unusedMonsters.shuffle!
    end
    def shuffleCultists
      @unusedCultists.shuffle!
    end
    def nextTreasure
      if @unusedTreasures.empty?
        @usedTreasures.each do |t|
          @unusedTreasures << t
          shuffleTreasures
        end
        @usedTreasures.clear
      end
      tre=@unusedTreasures[0]
      giveTreasureBack(tre)
      @unusedTreasures.delete(tre)
      return tre
    end
    def nextMonster
      if @unusedMonsters.empty?
        @usedMonsters.each do |m|
          @unusedMonsters << m
          shuffleMonsters
        end
        @usedMonsters.clear
      end
      mon=@unusedMonsters[0]
      giveMonsterBack(mon)
      @unusedMonsters.delete(mon)
      return mon
    end
    def nextCultist
      cult = @unusedCultists[0]
      @unusedCultists.delete(cult)
      cult
    end
    def giveTreasureBack (t)
      @usedTreasures << t
    end
    def giveMonsterBack (m)
      @usedMonsters << m
    end
    def initCards
      initTreasureCardDeck
      initMonsterCardDeck
      initCultistCardDeck
    end
    private :initTreasureCardDeck, :initMonsterCardDeck, :initCultistCardDeck, :shuffleTreasures, :shuffleMonsters, :shuffleCultists
  end
end
