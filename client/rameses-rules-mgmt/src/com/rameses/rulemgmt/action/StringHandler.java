/*
 * RuleConstraintDecimalHandler.java
 *
 * Created on September 30, 2013, 9:12 AM
 */

package com.rameses.rulemgmt.action;

import com.rameses.rcp.ui.annotations.StyleSheet;

/**
 *
 * @author  Elmo
 */
@StyleSheet
public class StringHandler extends javax.swing.JPanel {
    
    /** Creates new form RuleConstraintDecimalHandler */
    public StringHandler() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        xTextField1 = new com.rameses.rcp.control.XTextField();

        xTextField1.setName("actionParam.stringvalue");
        xTextField1.setTextCase(com.rameses.rcp.constant.TextCase.NONE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(xTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(xTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XTextField xTextField1;
    // End of variables declaration//GEN-END:variables
    
}
