/*
 * RuleConstraintDecimalHandler.java
 *
 * Created on September 30, 2013, 9:12 AM
 */

package com.rameses.rulemgmt.constraint;

import com.rameses.rcp.ui.annotations.StyleSheet;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author  Elmo
 */
@Template(value=HandlerTemplate.class)
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
        xComboBox1 = new com.rameses.rcp.control.XComboBox();
        xFormPanel1 = new com.rameses.rcp.control.XFormPanel();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xComboBox2 = new com.rameses.rcp.control.XComboBox();
        xCheckBox1 = new com.rameses.rcp.control.XCheckBox();

        xComboBox1.setCaption("Operator");
        xComboBox1.setExpression("#{item.caption}");
        xComboBox1.setItems("operatorList");
        xComboBox1.setName("constraint.operator");
        xComboBox1.setPreferredSize(new java.awt.Dimension(80, 22));

        xFormPanel1.setCaptionBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        xFormPanel1.setShowCaption(false);
        xTextField1.setCaption("Value");
        xTextField1.setDepends(new String[] {"constraint.usevar", "constraint.operator"});
        xTextField1.setName("constraint.stringvalue");
        xTextField1.setPreferredSize(new java.awt.Dimension(0, 20));
        xTextField1.setShowCaption(false);
        xFormPanel1.add(xTextField1);

        xComboBox2.setCaption("Variable");
        xComboBox2.setDepends(new String[] {"constraint.usevar", "constraint.operator"});
        xComboBox2.setDynamic(true);
        xComboBox2.setExpression("#{item.name}");
        xComboBox2.setItems("varList");
        xComboBox2.setName("constraint.var");
        xComboBox2.setPreferredSize(new java.awt.Dimension(150, 22));
        xComboBox2.setRequired(true);
        xComboBox2.setShowCaption(false);
        xFormPanel1.add(xComboBox2);

        xCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        xCheckBox1.setCheckValue(1);
        xCheckBox1.setDepends(new String[] {"constraint.operator"});
        xCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xCheckBox1.setName("constraint.usevar");
        xCheckBox1.setText("Use Var");
        xCheckBox1.setUncheckValue(0);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(xComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xFormPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(xComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(xCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(xFormPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XCheckBox xCheckBox1;
    private com.rameses.rcp.control.XComboBox xComboBox1;
    private com.rameses.rcp.control.XComboBox xComboBox2;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XTextField xTextField1;
    // End of variables declaration//GEN-END:variables
    
}
