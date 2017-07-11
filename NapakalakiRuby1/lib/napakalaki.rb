# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require 'singleton'
require_relative 'card_dealer.rb'
require_relative 'cultist_player.rb'
require_relative 'combat_result.rb'

module NapakalakiGame
  class Napakalaki
    include Singleton
    attr_reader :currentPlayer, :currentMonster
    def initialize
      @currentPlayer = nil
      @currentMonster = nil
      @currentPlayerIndex = -1
      @players = Array.new
      @dealer = CardDealer.instance
    end
    def getCurrentPlayer
      return @currentPlayer
    end
    def getCurrentMonster
      return @currentMonster
    end
    def initPlayers (names)
      names.each do |string|
        @players << Player.new(string)
      end
    end
    def nextPlayer
      if @currentPlayerIndex < 0
        @currentPlayerIndex = rand(@players.length)
      elsif @currentPlayerIndex == @players.length-1
        @currentPlayerIndex = 0
      else
        @currentPlayerIndex = @currentPlayerIndex+1
      end
      @currentPlayer = @players[@currentPlayerIndex]
      return @currentPlayer
    end
    def nextTurnAllowed
      return @currentPlayer == nil || @currentPlayer.validState
    end
    def setEnemies
      num=rand(2)
      @players[2].setEnemy(@players[num])
      num=rand(2)+1
      @players[0].setEnemy(@players[num])
      num=rand(2)
      if num==0
        @players[1].setEnemy(@players[0])
      else
        @players[1].setEnemy(@players[2])
      end
    end  
    def developCombat
      combatResult = @currentPlayer.combat(@currentMonster)
      @dealer.giveMonsterBack(@currentMonster)
      if combatResult == CombatResult::LOSEANDCONVERT
        cultCard = @dealer.nextCultist
        cultPlayer = CultistPlayer.new(@currentPlayer, cultCard)
        @players[@currentPlayerIndex] = cultPlayer
        @currentPlayer = cultPlayer
      end
      return combatResult
    end
    def discardVisibleTreasures (treasures)
      treasures.each do |t|
        @currentPlayer.discardVisibleTreasure(t)
      end
    end
    def discardHiddenTreasures (treasures)
      treasures.each do |t|
        @currentPlayer.discardHiddenTreasure(t)
      end
    end
    def makeTreasuresVisibles (treasures)
      treasures.each do |t|
        @currentPlayer.makeTreasureVisible(t)
      end
    end        
  
    def initGame (players)
      initPlayers(players)
      setEnemies
      @dealer.initCards
      nextTurn
    end
    def nextTurn
      stateOK = true
      if @currentPlayer != nil
        stateOK = nextTurnAllowed
      end
      if stateOK
        @currentMonster = @dealer.nextMonster
        @currentPlayer = nextPlayer
        dead = @currentPlayer.dead
        if dead
          @currentPlayer.initTreasures
        end
      end
      return stateOK
    end
    def endOfGame (result)
      return result == CombatResult::WINGAME
    end
    private :initPlayers, :nextPlayer, :nextTurnAllowed
  end
end
