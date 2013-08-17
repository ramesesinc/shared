/*
 * RemittancePage.java
 *
 * Created on May 2, 2011, 3:41 PM
 */

package com.rameses.gov.treasury.remittance;

import com.rameses.rcp.ui.annotations.StyleSheet;

/**
 *
 * @author  alvin
 */
public class ImportRemittancePage extends javax.swing.JPanel {
    
    /** Creates new form RemittancePage */
    public ImportRemittancePage() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        xButton2 = new com.rameses.rcp.control.XButton();
        xSubFormPanel1 = new com.rameses.rcp.control.XSubFormPanel();
        xActionBar1 = new com.rameses.rcp.control.XActionBar();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        formPanel3 = new com.rameses.rcp.util.FormPanel();
        xTextField2 = new com.rameses.rcp.control.XTextField();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xTextField3 = new com.rameses.rcp.control.XTextField();
        xDateField1 = new com.rameses.rcp.control.XDateField();
        formPanel2 = new com.rameses.rcp.util.FormPanel();
        xNumberField9 = new com.rameses.rcp.control.XNumberField();
        xNumberField10 = new com.rameses.rcp.control.XNumberField();
        xNumberField11 = new com.rameses.rcp.control.XNumberField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        xSubFormPanel2 = new com.rameses.rcp.control.XSubFormPanel();
        xDataTable3 = new com.rameses.rcp.control.XDataTable();
        xDataTable1 = new com.rameses.rcp.control.XDataTable();
        xDataTable2 = new com.rameses.rcp.control.XDataTable();

        xButton2.setMnemonic('d');
        xButton2.setText("Check Details");
        xButton2.setFont(new java.awt.Font("Arial", 1, 12));
        xSubFormPanel1.setHandler("cashBreakdownHandler");
        xSubFormPanel1.setPreferredSize(new java.awt.Dimension(259, 289));

        setLayout(new java.awt.BorderLayout());

        setPreferredSize(new java.awt.Dimension(643, 466));
        xActionBar1.setBorder(new com.rameses.rcp.control.border.XUnderlineBorder());
        xActionBar1.setName("formActions");
        add(xActionBar1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(null);

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Remittance Info");
        jPanel2.setBorder(xTitledBorder1);

        xTextField2.setCaption("Doc No.");
        xTextField2.setCaptionFont(new java.awt.Font("Arial", 0, 12));
        xTextField2.setCaptionWidth(120);
        xTextField2.setFont(new java.awt.Font("Arial", 1, 12));
        xTextField2.setName("entity.info.txnno");
        xTextField2.setPreferredSize(new java.awt.Dimension(200, 21));
        xTextField2.setReadonly(true);
        formPanel3.add(xTextField2);

        xTextField1.setCaption("Collector");
        xTextField1.setCaptionFont(new java.awt.Font("Arial", 0, 12));
        xTextField1.setCaptionWidth(120);
        xTextField1.setFont(new java.awt.Font("Arial", 1, 12));
        xTextField1.setName("entity.info.collectorname");
        xTextField1.setPreferredSize(new java.awt.Dimension(450, 21));
        xTextField1.setReadonly(true);
        formPanel3.add(xTextField1);

        xTextField3.setCaption("Liquidating Officer");
        xTextField3.setCaptionFont(new java.awt.Font("Arial", 0, 12));
        xTextField3.setCaptionWidth(120);
        xTextField3.setFont(new java.awt.Font("Arial", 1, 12));
        xTextField3.setName("entity.info.liquidatingofficername");
        xTextField3.setPreferredSize(new java.awt.Dimension(450, 21));
        xTextField3.setReadonly(true);
        formPanel3.add(xTextField3);

        xDateField1.setCaption("Date");
        xDateField1.setCaptionFont(new java.awt.Font("Arial", 0, 12));
        xDateField1.setCaptionWidth(120);
        xDateField1.setFont(new java.awt.Font("Arial", 1, 12));
        xDateField1.setInputFormat("MM-dd-yyyy");
        xDateField1.setName("entity.info.txndate");
        xDateField1.setOutputFormat("MM-dd-yyyy");
        xDateField1.setPreferredSize(new java.awt.Dimension(200, 21));
        xDateField1.setReadonly(true);
        formPanel3.add(xDateField1);

        jPanel2.add(formPanel3);
        formPanel3.setBounds(10, 20, 460, 112);

        formPanel2.setCaptionFont(new java.awt.Font("Arial", 0, 12));
        formPanel2.setCaptionVAlignment(com.rameses.rcp.constant.UIConstants.CENTER);
        xNumberField9.setEditable(false);
        xNumberField9.setCaption("Total Cash");
        xNumberField9.setCaptionWidth(140);
        xNumberField9.setEnabled(false);
        xNumberField9.setFont(new java.awt.Font("Arial", 1, 14));
        xNumberField9.setName("entity.info.totalcash");
        xNumberField9.setPreferredSize(new java.awt.Dimension(0, 25));
        xNumberField9.setReadonly(true);
        formPanel2.add(xNumberField9);

        xNumberField10.setEditable(false);
        xNumberField10.setCaption("Non-Cash Payments");
        xNumberField10.setCaptionWidth(140);
        xNumberField10.setEnabled(false);
        xNumberField10.setFont(new java.awt.Font("Arial", 1, 14));
        xNumberField10.setName("entity.info.totalotherpayment");
        xNumberField10.setPreferredSize(new java.awt.Dimension(0, 25));
        xNumberField10.setReadonly(true);
        formPanel2.add(xNumberField10);

        xNumberField11.setEditable(false);
        xNumberField11.setCaption("Remittance Amount");
        xNumberField11.setCaptionWidth(140);
        xNumberField11.setEnabled(false);
        xNumberField11.setFont(new java.awt.Font("Arial", 1, 14));
        xNumberField11.setName("entity.info.amount");
        xNumberField11.setPreferredSize(new java.awt.Dimension(0, 25));
        xNumberField11.setReadonly(true);
        formPanel2.add(xNumberField11);

        jPanel2.add(formPanel2);
        formPanel2.setBounds(480, 20, 296, 100);

        jPanel4.setLayout(new java.awt.BorderLayout());

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder2 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder2.setTitle("Cash Breakdown");
        jPanel4.setBorder(xTitledBorder2);
        xSubFormPanel2.setDynamic(true);
        xSubFormPanel2.setHandler("denominationOpener");
        jPanel4.add(xSubFormPanel2, java.awt.BorderLayout.CENTER);

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder3 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder3.setTitle("Non-Cash Payments");
        xDataTable3.setBorder(xTitledBorder3);
        xDataTable3.setHandler("noncashListHandler");
        xDataTable3.setShowRowHeader(true);

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 319, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xDataTable3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, xDataTable3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
                .addContainerGap())
        );
        jTabbedPane1.addTab("Cash and Non-Cash Summary", jPanel5);

        xDataTable1.setHandler("remittedFormsListHandler");
        xDataTable1.setShowRowHeader(true);
        jTabbedPane1.addTab("Remitted Forms", xDataTable1);

        xDataTable2.setHandler("remittedReceiptsListHandler");
        xDataTable2.setShowRowHeader(true);
        jTabbedPane1.addTab("Remitted Receipts", xDataTable2);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 136, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .add(15, 15, 15))
        );
        add(jPanel1, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel2;
    private com.rameses.rcp.util.FormPanel formPanel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.rameses.rcp.control.XActionBar xActionBar1;
    private com.rameses.rcp.control.XButton xButton2;
    private com.rameses.rcp.control.XDataTable xDataTable1;
    private com.rameses.rcp.control.XDataTable xDataTable2;
    private com.rameses.rcp.control.XDataTable xDataTable3;
    private com.rameses.rcp.control.XDateField xDateField1;
    private com.rameses.rcp.control.XNumberField xNumberField10;
    private com.rameses.rcp.control.XNumberField xNumberField11;
    private com.rameses.rcp.control.XNumberField xNumberField9;
    private com.rameses.rcp.control.XSubFormPanel xSubFormPanel1;
    private com.rameses.rcp.control.XSubFormPanel xSubFormPanel2;
    private com.rameses.rcp.control.XTextField xTextField1;
    private com.rameses.rcp.control.XTextField xTextField2;
    private com.rameses.rcp.control.XTextField xTextField3;
    // End of variables declaration//GEN-END:variables
    
}
