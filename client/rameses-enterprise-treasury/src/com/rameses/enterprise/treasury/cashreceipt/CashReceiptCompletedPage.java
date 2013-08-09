/*
 * CashReceiptPage.java
 *
 * Created on August 2, 2013, 2:19 PM
 */

package com.rameses.enterprise.treasury.cashreceipt;

import com.rameses.osiris2.themes.FormPage;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author  compaq
 */
@Template(FormPage.class)
public class CashReceiptCompletedPage extends javax.swing.JPanel {
    
    /** Creates new form CashReceiptPage */
    public CashReceiptCompletedPage() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        xLabel1 = new com.rameses.rcp.control.XLabel();

        setLayout(new java.awt.BorderLayout());

        xLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        xLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        xLabel1.setBackground(new java.awt.Color(255, 255, 255));
        xLabel1.setExpression("#{receiptInfo}");
        xLabel1.setOpaque(true);
        xLabel1.setUseHtml(true);
        add(xLabel1, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XLabel xLabel1;
    // End of variables declaration//GEN-END:variables
    
}
