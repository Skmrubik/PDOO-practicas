# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
  class Cultist
    attr_reader :gainedLevels, :name
    def initialize (n, gL)
        @name = n
        @gainedLevels = gL
    end
  end
end