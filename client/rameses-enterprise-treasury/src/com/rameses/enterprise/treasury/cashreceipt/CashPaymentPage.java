/*
 * CashPayment.java
 *
 * Created on August 3, 2013, 5:20 PM
 */

package com.rameses.enterprise.treasury.cashreceipt;

import com.rameses.osiris2.themes.OKCancelPage;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author  compaq
 */
@Template(OKCancelPage.class)
public class CashPaymentPage extends javax.swing.JPanel {
    
    /** Creates new form CashPayment */
    public CashPaymentPage() {
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
        xDecimalField1 = new com.rameses.rcp.control.XDecimalField();
        xDecimalField2 = new com.rameses.rcp.control.XDecimalField();

        setPreferredSize(new java.awt.Dimension(418, 191));

        xFormPanel1.setCaptionFont(new java.awt.Font("Tahoma", 0, 18));
        xFormPanel1.setCaptionWidth(150);
        xDecimalField1.setCaption("Cash Tendered");
        xDecimalField1.setFont(new java.awt.Font("Tahoma", 0, 18));
        xDecimalField1.setName("cash");
        xDecimalField1.setPreferredSize(new java.awt.Dimension(0, 27));
        xFormPanel1.add(xDecimalField1);

        xDecimalField2.setEditable(false);
        xDecimalField2.setCaption("Change");
        xDecimalField2.setDepends(new String[] {"cash"});
        xDecimalField2.setFont(new java.awt.Font("Tahoma", 0, 18));
        xDecimalField2.setName("change");
        xDecimalField2.setPreferredSize(new java.awt.Dimension(0, 27));
        xFormPanel1.add(xDecimalField2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(21, 21, 21)
                .add(xFormPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 301, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(20, 20, 20)
                .add(xFormPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 98, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XDecimalField xDecimalField1;
    private com.rameses.rcp.control.XDecimalField xDecimalField2;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    // End of variables declaration//GEN-END:variables
    
}
