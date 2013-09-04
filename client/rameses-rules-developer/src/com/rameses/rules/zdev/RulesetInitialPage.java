/*
 * RulesetPage.java
 *
 * Created on July 25, 2013, 11:03 AM
 */

package com.rameses.rules.zdev;

import com.rameses.osiris2.themes.FormPage;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author  Elmo
 */
@Template(value=FormPage.class)
public class RulesetInitialPage extends javax.swing.JPanel {
    
    /** Creates new form RulesetPage */
    public RulesetInitialPage() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        xFormPanel1 = new com.rameses.rcp.control.XFormPanel();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xTextField2 = new com.rameses.rcp.control.XTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        xTextArea1 = new com.rameses.rcp.control.XTextArea();
        xTextField3 = new com.rameses.rcp.control.XTextField();

        xTextField1.setCaption("Name");
        xTextField1.setName("entity.name");
        xTextField1.setPreferredSize(new java.awt.Dimension(0, 19));
        xTextField1.setTextCase(com.rameses.rcp.constant.TextCase.LOWER);
        xFormPanel1.add(xTextField1);

        xTextField2.setCaption("Title");
        xTextField2.setName("entity.title");
        xTextField2.setPreferredSize(new java.awt.Dimension(0, 19));
        xFormPanel1.add(xTextField2);

        jScrollPane1.setName("entity.description");
        jScrollPane1.setPreferredSize(new java.awt.Dimension(0, 42));
        xTextArea1.setCaption("Description");
        xTextArea1.setName("entity.description");
        xTextArea1.setPreferredSize(new java.awt.Dimension(0, 40));
        jScrollPane1.setViewportView(xTextArea1);

        xFormPanel1.add(jScrollPane1);

        xTextField3.setCaption("Category");
        xTextField3.setName("entity.category");
        xTextField3.setPreferredSize(new java.awt.Dimension(0, 19));
        xFormPanel1.add(xTextField3);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(xFormPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 417, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(xFormPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 136, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(138, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XTextArea xTextArea1;
    private com.rameses.rcp.control.XTextField xTextField1;
    private com.rameses.rcp.control.XTextField xTextField2;
    private com.rameses.rcp.control.XTextField xTextField3;
    // End of variables declaration//GEN-END:variables
    
}