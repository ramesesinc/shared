/*
 * BarangayRIVPage.java
 *
 * Created on July 16, 2013, 7:55 PM
 */

package com.rameses.gov.treasury.af.types;

import com.rameses.osiris2.themes.OKCancelPage;
import com.rameses.rcp.common.FormControl;
import com.rameses.rcp.ui.annotations.StyleSheet;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author  Elmo
 */

public class AFRequestCollectionType extends javax.swing.JPanel {
    
    /** Creates new form BarangayRIVPage */
    public AFRequestCollectionType() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        xFormPanel1 = new com.rameses.rcp.control.XFormPanel();
        xLookupField1 = new com.rameses.rcp.control.XLookupField();

        setPreferredSize(new java.awt.Dimension(337, 385));
        jLabel1.setText("AF Receipt Collection Type");

        xLookupField1.setCaption("Requester");
        xLookupField1.setExpression("#{item.lastname}, #{item.firstname}");
        xLookupField1.setHandler("collector:lookup");
        xLookupField1.setName("entity.requester");
        xLookupField1.setPreferredSize(new java.awt.Dimension(0, 19));
        xFormPanel1.add(xLookupField1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(xFormPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 294, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 171, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(15, 15, 15)
                .add(xFormPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(307, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XLookupField xLookupField1;
    // End of variables declaration//GEN-END:variables
    
}
