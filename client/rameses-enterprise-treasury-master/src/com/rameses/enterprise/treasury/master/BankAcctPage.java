/*
 * AccountPage.java
 *
 * Created on February 27, 2011, 12:48 PM
 */

package com.rameses.enterprise.treasury.master;


import com.rameses.osiris2.themes.FormPage;
import com.rameses.rcp.ui.annotations.StyleSheet;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author  jzamss
 */
@StyleSheet
@Template(FormPage.class)
public class BankAcctPage extends javax.swing.JPanel {
    
    /** Creates new form AccountPage */
    public BankAcctPage() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        formPanel1 = new com.rameses.rcp.util.FormPanel();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xTextField3 = new com.rameses.rcp.control.XTextField();
        xComboBox1 = new com.rameses.rcp.control.XComboBox();
        xComboBox4 = new com.rameses.rcp.control.XComboBox();
        xComboBox3 = new com.rameses.rcp.control.XComboBox();
        xLookupField1 = new com.rameses.rcp.control.XLookupField();
        xTextField4 = new com.rameses.rcp.control.XTextField();
        xTextField5 = new com.rameses.rcp.control.XTextField();
        formPanel2 = new com.rameses.rcp.util.FormPanel();
        xTextField12 = new com.rameses.rcp.control.XTextField();
        xTextField13 = new com.rameses.rcp.control.XTextField();
        xTextField14 = new com.rameses.rcp.control.XTextField();

        setPreferredSize(new java.awt.Dimension(542, 498));

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("General Information");
        formPanel1.setBorder(xTitledBorder1);
        xTextField1.setCaption("Account No.");
        xTextField1.setCaptionWidth(100);
        xTextField1.setName("entity.acctno");
        xTextField1.setPreferredSize(new java.awt.Dimension(0, 19));
        xTextField1.setRequired(true);
        formPanel1.add(xTextField1);

        xTextField3.setCaption("Account Name");
        xTextField3.setCaptionWidth(100);
        xTextField3.setName("entity.name");
        xTextField3.setPreferredSize(new java.awt.Dimension(0, 19));
        xTextField3.setRequired(true);
        formPanel1.add(xTextField3);

        xComboBox1.setCaption("Account Type");
        xComboBox1.setCaptionWidth(100);
        xComboBox1.setItems("accttypes");
        xComboBox1.setName("entity.accttype");
        xComboBox1.setPreferredSize(new java.awt.Dimension(0, 22));
        xComboBox1.setRequired(true);
        formPanel1.add(xComboBox1);

        xComboBox4.setCaption("Fund");
        xComboBox4.setCaptionWidth(100);
        xComboBox4.setExpression("#{fundname}");
        xComboBox4.setImmediate(true);
        xComboBox4.setItems("funds");
        xComboBox4.setName("fund");
        xComboBox4.setPreferredSize(new java.awt.Dimension(0, 22));
        xComboBox4.setRequired(true);
        formPanel1.add(xComboBox4);

        xComboBox3.setCaption("Currency");
        xComboBox3.setCaptionWidth(100);
        xComboBox3.setItems("currencylist");
        xComboBox3.setName("entity.currency");
        xComboBox3.setPreferredSize(new java.awt.Dimension(0, 22));
        xComboBox3.setRequired(true);
        formPanel1.add(xComboBox3);

        xLookupField1.setCaption("Bank");
        xLookupField1.setCaptionWidth(100);
        xLookupField1.setExpression("#{bank.code}");
        xLookupField1.setHandler("lookupBank");
        xLookupField1.setName("bank");
        xLookupField1.setPreferredSize(new java.awt.Dimension(0, 19));
        formPanel1.add(xLookupField1);

        xTextField4.setCaption("Bank Name");
        xTextField4.setCaptionWidth(100);
        xTextField4.setName("bank.name");
        xTextField4.setPreferredSize(new java.awt.Dimension(0, 19));
        xTextField4.setReadonly(true);
        formPanel1.add(xTextField4);

        xTextField5.setCaption("Branch Name");
        xTextField5.setCaptionWidth(100);
        xTextField5.setName("bank.branchname");
        xTextField5.setPreferredSize(new java.awt.Dimension(0, 19));
        xTextField5.setReadonly(true);
        formPanel1.add(xTextField5);

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder2 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder2.setTitle("Deposit Slip Report Format");
        formPanel2.setBorder(xTitledBorder2);
        xTextField12.setText("entity.cashreport");
        xTextField12.setCaption("Cash");
        xTextField12.setCaptionWidth(120);
        xTextField12.setName("entity.cashreport");
        xTextField12.setPreferredSize(new java.awt.Dimension(0, 19));
        formPanel2.add(xTextField12);

        xTextField13.setText("entity.cashbreakdownreport");
        xTextField13.setCaption("Cash Breakdown");
        xTextField13.setCaptionWidth(120);
        xTextField13.setName("entity.cashbreakdownreport");
        xTextField13.setPreferredSize(new java.awt.Dimension(0, 19));
        formPanel2.add(xTextField13);

        xTextField14.setText("entity.checkreport");
        xTextField14.setCaption("Check");
        xTextField14.setCaptionWidth(120);
        xTextField14.setName("entity.checkreport");
        xTextField14.setPreferredSize(new java.awt.Dimension(0, 19));
        formPanel2.add(xTextField14);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(formPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 496, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(formPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 496, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(formPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(formPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 154, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(298, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel1;
    private com.rameses.rcp.util.FormPanel formPanel2;
    private com.rameses.rcp.control.XComboBox xComboBox1;
    private com.rameses.rcp.control.XComboBox xComboBox3;
    private com.rameses.rcp.control.XComboBox xComboBox4;
    private com.rameses.rcp.control.XLookupField xLookupField1;
    private com.rameses.rcp.control.XTextField xTextField1;
    private com.rameses.rcp.control.XTextField xTextField12;
    private com.rameses.rcp.control.XTextField xTextField13;
    private com.rameses.rcp.control.XTextField xTextField14;
    private com.rameses.rcp.control.XTextField xTextField3;
    private com.rameses.rcp.control.XTextField xTextField4;
    private com.rameses.rcp.control.XTextField xTextField5;
    // End of variables declaration//GEN-END:variables
    
}
