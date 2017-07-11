/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JPanel;
import napakalaki1.CultistPlayer;
import napakalaki1.Napakalaki;
import napakalaki1.Player;
import napakalaki1.Treasure;

/**
 *
 * @author TOSHIBA
 */
public class PlayerView2 extends javax.swing.JPanel {

    Player playerModel;
    private Napakalaki napakalakiModel= Napakalaki.getInstance();
    /**
     * Creates new form PlayerView2
     */
    public PlayerView2() {
        initComponents();
    }
    public void setNapakalaki(Napakalaki n){
        napakalakiModel=n;
    }
    public void setPlayer(Player p){
        playerModel=p;
        
        nombre.setText(playerModel.getName());
        nivel.setText(Integer.toString(playerModel.getLevels()));
        enemy.setText(playerModel.nombreEnemigo());
        sectario.setText("NO");
        if(playerModel.esSectario==true)
            sectario.setText("SI");
        else
            sectario.setText("NO"); 
        nivelComabt.setText(Integer.toString(playerModel.getCombatLevel()));
        fillTreasurePanel(visibleTreasures,playerModel.getVisibleTreasures());
        fillTreasurePanel(hiddenTreasures,playerModel.getHiddenTreasures());
        repaint();
        
    }
    private void fillTreasurePanel (JPanel aPanel, ArrayList<Treasure> aList) {
        // Se elimina la información antigua, si la hubiera
        aPanel.removeAll();
        // Se recorre la lista de tesoros construyendo y añadiendo sus vistas
        // al panel
        for (Treasure t : aList) {
            TreasureView2 aTreasureView = new TreasureView2();
            aTreasureView.setTreasure (t);
            aTreasureView.setVisible (true);
            aPanel.add (aTreasureView);
        }
        // Se fuerza la actualización visual del panel
            aPanel.repaint();
        aPanel.revalidate();
    }
    public void stealEnabled(){
        steal.setEnabled(true);
    }
    public void stealMake(){
        makeVisible.setEnabled(true);
    }
    public void stealDiscard(){
        discardTreasure.setEnabled(true);
    }

    public void stealNotEnabled(){
        steal.setEnabled(false);
    }
    public void stealNotMake(){
        makeVisible.setEnabled(false);
    }
    public void stealNotDiscard(){
        discardTreasure.setEnabled(false);
    }

    private ArrayList<Treasure> getSelectedTreasures(JPanel aPanel){
        TreasureView2 tv;
        ArrayList<Treasure> output = new ArrayList();
        for(Component c: aPanel.getComponents()){
            tv=(TreasureView2) c;
            if(tv.isSelected()){
                output.add(tv.getTreasure());
            }
        }
        return output;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        nivel = new javax.swing.JLabel();
        nivelComabt = new javax.swing.JLabel();
        visibleTreasures = new javax.swing.JPanel();
        hiddenTreasures = new javax.swing.JPanel();
        makeVisible = new javax.swing.JButton();
        steal = new javax.swing.JButton();
        discardTreasure = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        enemy = new javax.swing.JLabel();
        sectario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Nivel");

        nombre.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre.setText("jLabel4");

        nivel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nivel.setForeground(new java.awt.Color(0, 0, 204));
        nivel.setText("jLabel4");

        nivelComabt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nivelComabt.setForeground(new java.awt.Color(0, 0, 204));
        nivelComabt.setText("jLabel4");

        visibleTreasures.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        hiddenTreasures.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        makeVisible.setText("Hacer Visible");
        makeVisible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeVisibleActionPerformed(evt);
            }
        });

        steal.setText("Robar Tesoro");
        steal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stealActionPerformed(evt);
            }
        });

        discardTreasure.setText("Descartar Tesoro");
        discardTreasure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardTreasureActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("N. Combate");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Enemigo");

        enemy.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        enemy.setForeground(new java.awt.Color(0, 0, 153));
        enemy.setText("jLabel4");

        sectario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sectario.setForeground(new java.awt.Color(0, 0, 153));
        sectario.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Sectario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nivel, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(nivelComabt, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(enemy, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(sectario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(nombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(visibleTreasures, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
                            .addComponent(hiddenTreasures, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(steal, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(makeVisible, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(discardTreasure, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(visibleTreasures, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hiddenTreasures, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(makeVisible, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(discardTreasure, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(steal, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nivel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nivelComabt)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(enemy))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sectario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(1, 1, 1)))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void discardTreasureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardTreasureActionPerformed
        // TODO add your handling code here:
        ArrayList<Treasure> selHidden = getSelectedTreasures(hiddenTreasures);
        ArrayList<Treasure> selVisible = getSelectedTreasures(visibleTreasures);
        for(Treasure t: selHidden){
            playerModel.discardHiddenTreasure(t);
        }
        for(Treasure t: selVisible){
            playerModel.discardVisibleTreasure(t);
        }
        setPlayer(napakalakiModel.getCurrentPlayer());
        
    }//GEN-LAST:event_discardTreasureActionPerformed

    private void makeVisibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeVisibleActionPerformed
        // TODO add your handling code here:
        ArrayList<Treasure> selHidden = getSelectedTreasures(hiddenTreasures);
        for(Treasure t: selHidden){
            playerModel.makeTreasureVisible(t);
        }
   //     napakalakiModel.makeTreasuresVisible(selHidden);
        setPlayer(napakalakiModel.getCurrentPlayer());
   //     makeVisible.setEnabled(false);
    }//GEN-LAST:event_makeVisibleActionPerformed

    private void stealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stealActionPerformed
        // TODO add your handling code here:
        playerModel.stealTreasure();
        setPlayer(napakalakiModel.getCurrentPlayer());
     //   steal.setEnabled(false);
    }//GEN-LAST:event_stealActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton discardTreasure;
    private javax.swing.JLabel enemy;
    private javax.swing.JPanel hiddenTreasures;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton makeVisible;
    private javax.swing.JLabel nivel;
    private javax.swing.JLabel nivelComabt;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel sectario;
    private javax.swing.JButton steal;
    private javax.swing.JPanel visibleTreasures;
    // End of variables declaration//GEN-END:variables
}