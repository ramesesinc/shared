/*
 * AFInventoryQuery.java
 *
 * Created on July 23, 2013, 11:18 AM
 */

package com.rameses.gov.treasury.afcontrol;

import com.rameses.osiris2.themes.CloseOnlyPage;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author  Elmo
 */
@Template(CloseOnlyPage.class)
public class AFControlDetailList extends javax.swing.JPanel {
    
    /** Creates new form AFInventoryQuery */
    public AFControlDetailList() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        xDataTable1 = new com.rameses.rcp.control.XDataTable();

        setPreferredSize(new java.awt.Dimension(804, 346));
        setRequestFocusEnabled(false);
        xDataTable1.setColumns(new com.rameses.rcp.common.Column[]{
            new com.rameses.rcp.common.Column("lineno", "Line No", 50, 0, 0, false, false, true, false, null, new com.rameses.rcp.common.TextColumnHandler()),
            new com.rameses.rcp.common.Column("reftype", "Ref Type", 100, 0, 0, false, true, true, false, null, new com.rameses.rcp.common.TextColumnHandler()),
            new com.rameses.rcp.common.Column("refdate", "Ref Date", 100, 0, 0, false, true, true, false, null, new com.rameses.rcp.common.TextColumnHandler()),
            new com.rameses.rcp.common.Column("collector.name", "Collector", 100, 0, 0, false, true, true, false, null, new com.rameses.rcp.common.TextColumnHandler()),
            new com.rameses.rcp.common.Column("startseries", "Start Series", 100, 0, 0, false, false, true, false, null, new com.rameses.rcp.common.IntegerColumnHandler(null, -1, -1)),
            new com.rameses.rcp.common.Column("endseries", "End Series", 100, 0, 0, false, false, true, false, null, new com.rameses.rcp.common.IntegerColumnHandler(null, -1, -1)),
            new com.rameses.rcp.common.Column("qtyreceived", "Qty Received", 80, 0, 0, false, false, true, false, null, new com.rameses.rcp.common.IntegerColumnHandler(null, -1, -1)),
            new com.rameses.rcp.common.Column("qtybegin", "Qty Beg.", 80, 0, 0, false, false, true, false, null, new com.rameses.rcp.common.IntegerColumnHandler(null, -1, -1)),
            new com.rameses.rcp.common.Column("qtyissued", "Qty Issued", 80, 0, 0, false, false, true, false, null, new com.rameses.rcp.common.IntegerColumnHandler(null, -1, -1)),
            new com.rameses.rcp.common.Column("qtycancelled", "Qty Cancelled", 80, 0, 0, false, false, true, false, null, new com.rameses.rcp.common.IntegerColumnHandler(null, -1, -1)),
            new com.rameses.rcp.common.Column("qtybalance", "Qty Balance", 80, 0, 0, false, false, true, false, null, new com.rameses.rcp.common.IntegerColumnHandler(null, -1, -1))
        });
        xDataTable1.setHandler("listModel");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(xDataTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(24, 24, 24)
                .add(xDataTable1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 301, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XDataTable xDataTable1;
    // End of variables declaration//GEN-END:variables
    
}
