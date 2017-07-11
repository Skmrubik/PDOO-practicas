# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
  class BadConsequence
    attr_reader :text, :level
    def initialize (t, l)
      @text = t
      @level = l
    end
    private_class_method :new
    def isEmpty
      raise NotImplementdError.new
    end
    def substractVisibleTreasure (t)
      raise NotImplementdError.new
    end
    def substractHiddenTreasure (t)
      raise NotImplementdError.new
    end
    def adjustToFitTreasureLists (v, h)
      raise NotImplementdError.new
    end
    def to_s
      "Texto = #{@text}\nNiveles = #{@level}\n"
    end
  end
end
