# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'cultist.rb'
require_relative 'player.rb'

module NapakalakiGame
  class CultistPlayer < Player
    @@totalCultistPlayers = 0

    def initialize (p, c)
      super(p.name)
      super.copia(p)
      @myCultistCard=c
      @@totalCultistPlayer+=1
    end
    def self.totalCultistPlayers
      return @@totalCultistPlayers
    end
    def getCombatLevel
      uno = super.getCombatLevel
      dos = super.getCombatLevel/5
      tres = @myCultistCard.getGainedLevels
      lvl = (uno+dos+tres)*@@totalCultistPlayers
      return lvl
    end
    def getOponentLevel (m)
      return m.getCombatLevelAgainstCultistPlayer
    end
    def shouldConvert
      return false
    end
    def to_s
      super + ", #{@myCultistCard.name}"
    end
    protected :getCombatLevel, :getOponentLevel, :shouldConvert
  end
end
