/*
 * tPreviewPage.java
 *
 * Created on January 13, 2011, 3:09 PM
 */

package com.rameses.osiris2.common.ui;

import com.rameses.osiris2.themes.FormPage;
import com.rameses.rcp.ui.annotations.Template;

@Template(FormPage.class)
public class ReportPreviewPage extends javax.swing.JPanel {
    
    /** Creates new form tPreviewPage */
    public ReportPreviewPage() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        xReportPanel1 = new com.rameses.osiris2.reports.ui.XReportPanel();

        setLayout(new java.awt.BorderLayout());

        setPreferredSize(new java.awt.Dimension(771, 439));
        xReportPanel1.setName("report");
        org.jdesktop.layout.GroupLayout xReportPanel1Layout = new org.jdesktop.layout.GroupLayout(xReportPanel1);
        xReportPanel1.setLayout(xReportPanel1Layout);
        xReportPanel1Layout.setHorizontalGroup(
            xReportPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 771, Short.MAX_VALUE)
        );
        xReportPanel1Layout.setVerticalGroup(
            xReportPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 439, Short.MAX_VALUE)
        );
        add(xReportPanel1, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.osiris2.reports.ui.XReportPanel xReportPanel1;
    // End of variables declaration//GEN-END:variables
    
}