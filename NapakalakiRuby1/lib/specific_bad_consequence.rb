# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'bad_consequence.rb'

module NapakalakiGame
  class SpecificBadConsequence < BadConsequence
    attr_reader :specificVisibleTreasures, :specificHiddenTreasures
    def initialize (t, l, sV, sH)
      super(t, l)
      @specificVisibleTreasures = Array.new(sV)
      @specificHiddenTreasures = Array.new(sH)
    end
    public_class_method :new
    def isEmpty
      return (@specificVisibleTreasures.empty?) && (@specificHiddenTreasures.empty?)
    end
    def substractVisibleTreasure (t)
      for i in 0..@specificVisibleTreasures.length-1 do
        if t.type == @specificVisibleTreasures[i]
          @specificVisibleTreasures.delete_at(i)
          break
        end
      end
    end
    def substractHiddenTreasure (t)
      for i in 0..@specificHiddenTreasures.length-1 do
        if t.type == @specificHiddenTreasures[i]
          @specificHiddenTreasures.delete_at(i)
          break
        end
      end
    end
    def adjustToFitTreasureLists (v, h)
      visible = Array.new(@specificVisibleTreasures)
      aux = Array.new(visible)
      v.each do |t|
        for i in 0..aux.length-1 do
          if aux[i] == t.type
            aux.delete_at(i)
            break
          end
        end
      end
      aux.each do |tK|
        #visible.delete_at(visible.find_index(tK))
        visible.delete(tK)
      end
      hidden = Array.new(@specificHiddenTreasures)
      aux = Array.new(hidden)
      h.each do |t|
        for i in 0..aux.length-1 do
          if aux[i] == t.type
            aux.delete_at(i)
            break
          end
        end
      end
      aux.each do |tK|
        #hidden.delete_at(hidden.find_index(tK))
        hidden.delete(tK)
      end
      bC = SpecificBadConsequence.new("", 0, visible, hidden)
    end
    def to_s
      super + "Tesoros Visibles = " + @specificVisibleTreasures.to_s + "\nTesoros Ocultos = " + @specificHiddenTreasures.to_s + "\n"
    end
  end
end
