# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
  class Monster
    attr_reader :name, :combatLevel, :badConsequence

    def initialize (n, l, bC, p, lC)
      @name = n
      @combatLevel = l
      @badConsequence = bC
      @prize = p
      @levelChangeAgainstCultistPlayer = lC
    end
    def getLevelsGained
      return @prize.level
    end
    def getTreasuresGained
      return @prize.treasures
    end
    def getCombatLevelAgainstCultistPlayer
      return @combatLevel + @levelChangeAgainstCultistPlayer
    end
    def to_s
      "Nombre = #{@name}\nNivel = #{@combatLevel}\nMala rollo:\n" + @badConsequence.to_s + "Premio:\n" + @prize.to_s + "\n"
    end
  end
end