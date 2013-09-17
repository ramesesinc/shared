/*
 * BarangayRIVPage.java
 *
 * Created on July 16, 2013, 7:55 PM
 */

package com.rameses.gov.treasury.af;

import com.rameses.osiris2.themes.CloseOnlyPage;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author  Elmo
 */
@Template(value=CloseOnlyPage.class)
public class IRAFIssueItemPage extends javax.swing.JPanel {
    
    /** Creates new form BarangayRIVPage */
    public IRAFIssueItemPage() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel2 = new javax.swing.JPanel();
        xLabel1 = new com.rameses.rcp.control.XLabel();
        xComboBox1 = new com.rameses.rcp.control.XComboBox();
        xFormPanel3 = new com.rameses.rcp.control.XFormPanel();
        xLabel7 = new com.rameses.rcp.control.XLabel();
        xLabel4 = new com.rameses.rcp.control.XLabel();
        xLabel5 = new com.rameses.rcp.control.XLabel();
        xIntegerField1 = new com.rameses.rcp.control.XIntegerField();
        xDataTable1 = new com.rameses.rcp.control.XDataTable();
        xButton1 = new com.rameses.rcp.control.XButton();

        setPreferredSize(new java.awt.Dimension(580, 390));
        xLabel1.setText("<html><b>Accountable Form</b></html>");
        xLabel1.setUseHtml(true);

        xComboBox1.setExpression("#{item.af}");
        xComboBox1.setItems("afList");
        xComboBox1.setName("selectedItem");

        xFormPanel3.setCaptionWidth(120);
        xLabel7.setCaption("Description");
        xLabel7.setDepends(new String[] {"selectedItem"});
        xLabel7.setExpression("#{selectedItem.description}");
        xLabel7.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel3.add(xLabel7);

        xLabel4.setCaption("Qty Requested");
        xLabel4.setDepends(new String[] {"selectedItem"});
        xLabel4.setExpression("#{selectedItem.qty}");
        xLabel4.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel3.add(xLabel4);

        xLabel5.setCaption("Qty Bal.");
        xLabel5.setDepends(new String[] {"selectedItem"});
        xLabel5.setExpression("#{selectedItem.qtybalance}");
        xLabel5.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel3.add(xLabel5);

        xIntegerField1.setCaption("Qty to Issue");
        xIntegerField1.setDepends(new String[] {"selectedItem"});
        xIntegerField1.setName("qty");
        xFormPanel3.add(xIntegerField1);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(xLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(xComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 197, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xFormPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 278, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(xFormPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, Short.MAX_VALUE)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(xLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(xComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        xDataTable1.setColumns(new com.rameses.rcp.common.Column[]{
            new com.rameses.rcp.common.Column("af", "AF", 100, 0, 0, false, true, true, false, null, new com.rameses.rcp.common.TextColumnHandler()),
            new com.rameses.rcp.common.Column("qty", "Qty ", 100, 0, 0, false, true, true, false, null, new com.rameses.rcp.common.IntegerColumnHandler(null, -1, -1)),
            new com.rameses.rcp.common.Column("startstub", "Start Stub", 100, 0, 0, false, true, true, false, null, new com.rameses.rcp.common.IntegerColumnHandler(null, -1, -1)),
            new com.rameses.rcp.common.Column("endstub", "To Stub", 100, 0, 0, false, true, true, false, null, new com.rameses.rcp.common.IntegerColumnHandler(null, -1, -1)),
            new com.rameses.rcp.common.Column("startseries", "Start Series", 100, 0, 0, false, true, true, false, null, new com.rameses.rcp.common.IntegerColumnHandler(null, -1, -1)),
            new com.rameses.rcp.common.Column("endseries", "To Series", 100, 0, 0, false, true, true, false, null, new com.rameses.rcp.common.IntegerColumnHandler(null, -1, -1)),
            new com.rameses.rcp.common.Column("prefix", "Prefix", 50, 50, 50, false, false, true, false, null, new com.rameses.rcp.common.TextColumnHandler()),
            new com.rameses.rcp.common.Column("suffix", "Suffix", 50, 50, 50, false, false, true, false, null, new com.rameses.rcp.common.TextColumnHandler())
        });
        xDataTable1.setHandler("availableItemsModel");

        xButton1.setName("checkAvailable");
        xButton1.setText("Check Available");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(xButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 138, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(xDataTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                            .addContainerGap())
                        .add(layout.createSequentialGroup()
                            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(24, 24, 24)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xDataTable1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private com.rameses.rcp.control.XButton xButton1;
    private com.rameses.rcp.control.XComboBox xComboBox1;
    private com.rameses.rcp.control.XDataTable xDataTable1;
    private com.rameses.rcp.control.XFormPanel xFormPanel3;
    private com.rameses.rcp.control.XIntegerField xIntegerField1;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XLabel xLabel4;
    private com.rameses.rcp.control.XLabel xLabel5;
    private com.rameses.rcp.control.XLabel xLabel7;
    // End of variables declaration//GEN-END:variables
    
}