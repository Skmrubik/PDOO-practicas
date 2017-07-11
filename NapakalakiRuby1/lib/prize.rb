# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
  class Prize
    attr_reader :treasures, :level
    def initialize (t, l)
      @treasures = t
      @level = l
    end
    def to_s
      "Tesoros = #{@treasures}\nNiveles = #{@level}\n"
    end
  end
end
