# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'card_dealer.rb'
require_relative 'bad_consequence.rb'
require_relative 'treasure.rb'
require_relative 'treasure_kind.rb'
require_relative 'dice.rb'

module NapakalakiGame
  class Player
    attr_reader :name
    attr_accessor :dead, :level, :visibleTreasures, :hiddenTreasures, :pendingBadConsequence, :enemy, :canISteal
    @MAXLEVEL = 10
    def initialize (n)
      @name = n
      @dead = true
      @level = 1
      @visibleTreasures = Array.new
      @hiddenTreasures = Array.new
      @pendingBadConsequence = NumericBadConsequence.new("", 0, 0, 0)
      @enemy=nil
      @canISteal=true
    end
    def getName
      return @name
    end
    def getVisibleTreasures
      return @visibleTreasures
    end
    def getHiddenTreasures
      return @hiddenTreasures
    end
    def copia(p)
      @name=p.name
      @level=p.level
      @dead=p.dead
      @visibleTreasures=p.visibleTreasures
      @hiddenTreasures=p.hiddenTreasures
      @enemy=p.enemy
      @canISteal=p.canISteal
      @pendingBadConsequence=p.pendingBadConsequence
    end
    def self.MAXLEVEL
      return @MAXLEVEL
    end
    def bringToLife
      @dead = false
    end
    def getCombatLevel
      suma = @level
      @visibleTreasures.each do |element|
        suma += element.bonus
      end
      return suma
    end
    def getOponentLevel (m)
      return m.combatLevel
    end    
    def canISteal
      return @canISteal
    end
    def shouldConvert
      return Dice.instance.nextNumber == 1
    end
    def incrementLevels (i)
      @level += i
    end
    def setEnemy(e)
      @enemy=e
    end
    def decrementLevels (i)
      if (@level - i) <= 1
        @level = 1
      else
        @level -= i
      end
    end
    def setPendingBadConsequence (b)
      @pendingBadConsequence = b
    end
    def applyPrize (m)
      nLevels = m.getLevelsGained
      incrementLevels(nLevels)
      nTreasures = m.getTreasuresGained

      if nTreasures > 0
        dealer = CardDealer.instance

        for i in 1..nTreasures do
          treasure = dealer.nextTreasure
          @hiddenTreasures << treasure
        end
      end
    end
    def applyBadConsequence (m)
      badConsequence = m.badConsequence
      nLevels = badConsequence.level
      decrementLevels(nLevels)
      pendingBad = badConsequence.adjustToFitTreasureLists(@visibleTreasures, @hiddenTreasures)
      setPendingBadConsequence(pendingBad)
    end
    def canMakeTreasureVisible (t)
      puede=false
      if t.type == TreasureKind::ONEHAND
        if howManyVisibleTreasures(TreasureKind::ONEHAND)<2 && howManyVisibleTreasures(TreasureKind::BOTHHANDS)==0
          puede=true
        end
      end
      if t.type == TreasureKind::BOTHHANDS
        if howManyVisibleTreasures(TreasureKind::ONEHAND)==0 && howManyVisibleTreasures(TreasureKind::BOTHHANDS)==0
          puede=true
        end
      end
      if t.type == TreasureKind::HELMET
        if howManyVisibleTreasures(TreasureKind::HELMET)==0
          puede=true
        end
      end
      if t.type == TreasureKind::SHOES
        if howManyVisibleTreasures(TreasureKind::SHOES)==0
          puede=true
        end
      end
      if t.type == TreasureKind::ARMOR
        if howManyVisibleTreasures(TreasureKind::ARMOR)==0
          puede=true
        end
      end
      return puede
    end
    def howManyVisibleTreasures (tKind)
      suma = 0
      @visibleTreasures.each do |t|
        if t.type == t
          suma += 1
        end
      end
      return suma
    end
    def dieIfNoTreasures
      if @visibleTreasures.empty? && @hiddenTreasures.empty?
        @dead = true
      end
    end
    def combat (m)
      myLevel = getCombatLevel
      monsterLevel = getOponentLevel(m)
      if myLevel > monsterLevel
        applyPrize(m)
        if @level >= 10
          combatResult = CombatResult::WINGAME
        else
          combatResult = CombatResult::WIN
        end
      else
        applyBadConsequence(m)
        if shouldConvert
          combatResult = CombatResult::LOSEANDCONVERT
        else
          combatResult = CombatResult::LOSE
        end
      end
    end
    def makeTreasureVisible (t)
      canI = canMakeTreasureVisible(t)
      if canI
        @visibleTreasures << t
        @hiddenTreasures.delete(t)
      end
    end
    def discardVisibleTreasure (t)
      @visibleTreasures.delete(t)
      CardDealer.instance.giveTreasureBack(t)
      if @pendingBadConsequence!=nil && !@pendingBadConsequence.isEmpty
        @pendingBadConsequence.substractVisibleTreasure(t)
      end
      dieIfNoTreasures
    end
    def discardHiddenTreasure (t)
      @hiddenTreasures.delete(t)
      CardDealer.instance.giveTreasureBack(t)
      if @pendingBadConsequence!=nil && !@pendingBadConsequence.isEmpty
        @pendingBadConsequence.substractHiddenTreasure(t)
      end

      dieIfNoTreasures
    end
    def validState
      @pendingBadConsequence.isEmpty && (@hiddenTreasures.length <= 4)
    end
    def initTreasures
      dealer=CardDealer.instance
      dice=Dice.instance
      bringToLife
      treasure=dealer.nextTreasure
      @hiddenTreasures << treasure
      num = dice.nextNumber
      if num==6
        treasure =dealer.nextTreasure
        @hiddenTreasures << treasure
        treasure =dealer.nextTreasure
        @hiddenTreasures << treasure
      end
      if num>1 && num<6
        treasure =dealer.nextTreasure
        @hiddenTreasures << treasure
      end
    end
    def to_s
      "#{@name}"
    end
    def stealTreasure
      canI=canISteal
      treasure=nil
      if canI==true
        canYou=canYouGiveMeATreasure
        if canYou==true
          treasure = @enemy.giveMeATreasure
          @hiddenTreasures << treasure
          haveStolen
        end
      end
      return treasure
    end
     def giveMeATreasure
      num=@hiddenTreasures.size
      n=rand(num)
      return @hiddenTreasures[n]
    end
    def canYouGiveMeATreasure
      tiene=false
      if @hiddenTreasures.size >0
        tiene=true
      end
      return tiene
    end
    def haveStolen
       @canISteal=false
    end
    def discardAllTreasures
      auxVisible = Array.new(@visibleTreasures)
      auxHidden = Array.new(@hiddenTreasures)

      auxVisible.each do |t|
        discardVisibleTreasure(t)
      end

      auxHidden.each do |t|
        discardHiddenTreasure(t)
      end
    end
    protected :getCombatLevel, :getOponentLevel, :shouldConvert
    private :bringToLife, :incrementLevels, :decrementLevels, :setPendingBadConsequence, :applyPrize, :applyBadConsequence, :canMakeTreasureVisible, :howManyVisibleTreasures, :dieIfNoTreasures
  end
end
