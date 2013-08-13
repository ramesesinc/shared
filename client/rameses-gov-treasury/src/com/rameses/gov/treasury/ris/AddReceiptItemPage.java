/*
 * BarangayRIVPage.java
 *
 * Created on July 16, 2013, 7:55 PM
 */

package com.rameses.gov.treasury.ris;

import com.rameses.osiris2.themes.CloseOnlyPage;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author  Elmo
 */
@Template(value=CloseOnlyPage.class)
public class AddReceiptItemPage extends javax.swing.JPanel {
    
    /** Creates new form BarangayRIVPage */
    public AddReceiptItemPage() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        xFormPanel2 = new com.rameses.rcp.control.XFormPanel();
        xComboBox1 = new com.rameses.rcp.control.XComboBox();
        xIntegerField5 = new com.rameses.rcp.control.XIntegerField();
        xIntegerField3 = new com.rameses.rcp.control.XIntegerField();
        xIntegerField2 = new com.rameses.rcp.control.XIntegerField();
        xIntegerField4 = new com.rameses.rcp.control.XIntegerField();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xTextField3 = new com.rameses.rcp.control.XTextField();
        xButton1 = new com.rameses.rcp.control.XButton();
        xButton2 = new com.rameses.rcp.control.XButton();

        setPreferredSize(new java.awt.Dimension(423, 318));
        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Item Info");
        jPanel1.setBorder(xTitledBorder1);

        xFormPanel2.setCaptionWidth(150);
        xComboBox1.setCaption("Accountable Form");
        xComboBox1.setExpression("#{item.item.code}");
        xComboBox1.setItems("afList");
        xComboBox1.setName("selectedItem");
        xComboBox1.setPreferredSize(new java.awt.Dimension(0, 22));
        xFormPanel2.add(xComboBox1);

        xIntegerField5.setCaption("Qty Req. Balance");
        xIntegerField5.setDepends(new String[] {"selectedItem"});
        xIntegerField5.setEnabled(false);
        xIntegerField5.setName("selectedItem.qtybalance");
        xIntegerField5.setPreferredSize(new java.awt.Dimension(50, 19));
        xIntegerField5.setRequired(true);
        xFormPanel2.add(xIntegerField5);

        xIntegerField3.setCaption("Qty Received");
        xIntegerField3.setDepends(new String[] {"selectedItem"});
        xIntegerField3.setName("entity.qty");
        xIntegerField3.setPreferredSize(new java.awt.Dimension(50, 19));
        xIntegerField3.setRequired(true);
        xFormPanel2.add(xIntegerField3);

        xIntegerField2.setCaption("Start Series");
        xIntegerField2.setDepends(new String[] {"selectedItem"});
        xIntegerField2.setName("entity.startseries");
        xIntegerField2.setPreferredSize(new java.awt.Dimension(0, 19));
        xIntegerField2.setRequired(true);
        xFormPanel2.add(xIntegerField2);

        xIntegerField4.setCaption("Start Stub No");
        xIntegerField4.setDepends(new String[] {"selectedItem"});
        xIntegerField4.setName("entity.startstub");
        xIntegerField4.setPreferredSize(new java.awt.Dimension(0, 19));
        xIntegerField4.setRequired(true);
        xFormPanel2.add(xIntegerField4);

        xTextField1.setCaption("Prefix");
        xTextField1.setDepends(new String[] {"selectedItem"});
        xTextField1.setName("entity.prefix");
        xTextField1.setPreferredSize(new java.awt.Dimension(0, 19));
        xFormPanel2.add(xTextField1);

        xTextField3.setCaption("Suffix");
        xTextField3.setDepends(new String[] {"selectedItem"});
        xTextField3.setName("entity.suffix");
        xTextField3.setPreferredSize(new java.awt.Dimension(0, 19));
        xFormPanel2.add(xTextField3);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(xFormPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 302, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(297, 297, 297))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(xFormPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );

        xButton1.setName("addAndContinue");
        xButton1.setText("Add and Continue");

        xButton2.setName("addAndClose");
        xButton2.setText("Add and Close");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(xButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 142, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(xButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 350, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(27, 27, 27)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(xButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(xButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private com.rameses.rcp.control.XButton xButton1;
    private com.rameses.rcp.control.XButton xButton2;
    private com.rameses.rcp.control.XComboBox xComboBox1;
    private com.rameses.rcp.control.XFormPanel xFormPanel2;
    private com.rameses.rcp.control.XIntegerField xIntegerField2;
    private com.rameses.rcp.control.XIntegerField xIntegerField3;
    private com.rameses.rcp.control.XIntegerField xIntegerField4;
    private com.rameses.rcp.control.XIntegerField xIntegerField5;
    private com.rameses.rcp.control.XTextField xTextField1;
    private com.rameses.rcp.control.XTextField xTextField3;
    // End of variables declaration//GEN-END:variables
    
}
