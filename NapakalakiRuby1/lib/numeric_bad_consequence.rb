# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'bad_consequence.rb'

module NapakalakiGame
  class NumericBadConsequence < BadConsequence
    @MAXTREASURES = 10
    attr_reader :nVisibleTreasures, :nHiddenTreasures
    def initialize (t, l, v, h)
      super(t, l)
      @nVisibleTreasures = v
      @nHiddenTreasures = h
    end
    public_class_method :new
    def self.MAXTREASURES
      return @MAXTREASURES
    end
    def isEmpty
      return (@nVisibleTreasures == 0) && (@nHiddenTreasures == 0)
    end
    def substractVisibleTreasure (t)
      if @nVisibleTreasures > 0
        @nVisibleTreasures -= 1
      end
    end
    def substractHiddenTreasure (t)
      if @nHiddenTreasures > 0
        @nHiddenTreasures -= 1
      end
    end
    def adjustToFitTreasureLists (v, h)
      nVisible = @nVisibleTreasures
      nHidden = @nHiddenTreasures
      if v.size < @nVisibleTreasures
        nVisible = v.size
      end
      if h.size < @nHiddenTreasures
        nHidden = h.size
      end
      bC = NumericBadConsequence.new("", 0, nVisible, nHidden)
    end
    def to_s
      super + "Tesoros visibles = #{@nVisibleTreasures}\nTesoros Ocultos = #{@nHiddenTreasures}\n"
    end
  end
end
