/*
 * InitialPage.java
 *
 * Created on April 30, 2013, 6:08 PM
 */

package etracs2.bpls.application;

import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author  Elmo
 */
@Template(com.rameses.osiris2.themes.FormPage.class)
public class NewApplicationPage extends javax.swing.JPanel {
    
    /** Creates new form InitialPage */
    public NewApplicationPage() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel3 = new javax.swing.JPanel();
        xFormPanel3 = new com.rameses.rcp.control.XFormPanel();
        xComboBox1 = new com.rameses.rcp.control.XComboBox();
        xLookupField3 = new com.rameses.rcp.control.XLookupField();
        xComboBox2 = new com.rameses.rcp.control.XComboBox();
        xTextField5 = new com.rameses.rcp.control.XTextField();
        xTextField10 = new com.rameses.rcp.control.XTextField();
        xFormPanel5 = new com.rameses.rcp.control.XFormPanel();
        xTextField8 = new com.rameses.rcp.control.XTextField();
        xTextField9 = new com.rameses.rcp.control.XTextField();
        xFormPanel2 = new com.rameses.rcp.control.XFormPanel();
        xLookupField2 = new com.rameses.rcp.control.XLookupField();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xTextField11 = new com.rameses.rcp.control.XTextField();
        xTextField2 = new com.rameses.rcp.control.XTextField();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Business Detail");
        jPanel3.setBorder(xTitledBorder1);

        xFormPanel3.setCaptionWidth(100);
        xComboBox1.setCaption("Organization");
        xComboBox1.setCaptionWidth(130);
        xComboBox1.setExpression("#{value}");
        xComboBox1.setItemKey("key");
        xComboBox1.setItems("orgtypes");
        xComboBox1.setName("application.orgtype");
        xComboBox1.setPreferredSize(new java.awt.Dimension(0, 22));
        xComboBox1.setRequired(true);
        xFormPanel3.add(xComboBox1);

        xLookupField3.setCaption("Barangay");
        xLookupField3.setCaptionWidth(130);
        xLookupField3.setExpression("#{application.barangay.name}");
        xLookupField3.setHandler("barangay:lookup");
        xLookupField3.setName("application.barangay");
        xLookupField3.setPreferredSize(new java.awt.Dimension(0, 19));
        xLookupField3.setRequired(true);
        xFormPanel3.add(xLookupField3);

        xComboBox2.setCaption("Office Type");
        xComboBox2.setCaptionWidth(130);
        xComboBox2.setItems("officetypes");
        xComboBox2.setName("application.officetype");
        xComboBox2.setPreferredSize(new java.awt.Dimension(0, 22));
        xComboBox2.setRequired(true);
        xFormPanel3.add(xComboBox2);

        xTextField5.setCaption("TIN");
        xTextField5.setCaptionWidth(130);
        xTextField5.setName("application.tin");
        xTextField5.setPreferredSize(new java.awt.Dimension(0, 19));
        xFormPanel3.add(xTextField5);

        xTextField10.setCaption("Contact No.");
        xTextField10.setCaptionWidth(130);
        xTextField10.setName("application.contactno");
        xTextField10.setPreferredSize(new java.awt.Dimension(0, 19));
        xFormPanel3.add(xTextField10);

        xTextField8.setCaption("Administrator");
        xTextField8.setCaptionWidth(130);
        xTextField8.setName("application.administratorname");
        xTextField8.setPreferredSize(new java.awt.Dimension(0, 19));
        xFormPanel5.add(xTextField8);

        xTextField9.setCaption("Address");
        xTextField9.setCaptionWidth(130);
        xTextField9.setName("application.administratoraddress");
        xTextField9.setPreferredSize(new java.awt.Dimension(0, 19));
        xFormPanel5.add(xTextField9);

        xFormPanel2.setCaptionWidth(120);
        xFormPanel2.setPreferredSize(new java.awt.Dimension(0, 50));
        xFormPanel2.setShowCaption(false);
        xLookupField2.setCaption("Permitee Name");
        xLookupField2.setCaptionWidth(130);
        xLookupField2.setExpression("#{application.permitee.name}");
        xLookupField2.setHandler("individual:lookup");
        xLookupField2.setName("application.permitee");
        xLookupField2.setPreferredSize(new java.awt.Dimension(0, 19));
        xLookupField2.setRequired(true);
        xFormPanel2.add(xLookupField2);

        xTextField1.setCaption("Permitee Address");
        xTextField1.setCaptionWidth(130);
        xTextField1.setDepends(new String[] {"application.permitee"});
        xTextField1.setName("application.permitee.address");
        xTextField1.setPreferredSize(new java.awt.Dimension(0, 19));
        xTextField1.setRequired(true);
        xFormPanel2.add(xTextField1);

        xTextField11.setCaption("Trade Name");
        xTextField11.setCaptionWidth(130);
        xTextField11.setName("application.tradename");
        xTextField11.setPreferredSize(new java.awt.Dimension(0, 19));
        xTextField11.setRequired(true);
        xFormPanel2.add(xTextField11);

        xTextField2.setCaption("Business Address");
        xTextField2.setCaptionWidth(130);
        xTextField2.setName("application.businessaddress");
        xTextField2.setPreferredSize(new java.awt.Dimension(0, 19));
        xTextField2.setRequired(true);
        xFormPanel2.add(xTextField2);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(9, 9, 9)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(xFormPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 300, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(xFormPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 600, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
            .add(jPanel3Layout.createSequentialGroup()
                .add(9, 9, 9)
                .add(xFormPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 600, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(99, 99, 99)
                        .add(xFormPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(xFormPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(11, 11, 11)
                .add(xFormPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 665, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(164, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 349, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel3;
    private com.rameses.rcp.control.XComboBox xComboBox1;
    private com.rameses.rcp.control.XComboBox xComboBox2;
    private com.rameses.rcp.control.XFormPanel xFormPanel2;
    private com.rameses.rcp.control.XFormPanel xFormPanel3;
    private com.rameses.rcp.control.XFormPanel xFormPanel5;
    private com.rameses.rcp.control.XLookupField xLookupField2;
    private com.rameses.rcp.control.XLookupField xLookupField3;
    private com.rameses.rcp.control.XTextField xTextField1;
    private com.rameses.rcp.control.XTextField xTextField10;
    private com.rameses.rcp.control.XTextField xTextField11;
    private com.rameses.rcp.control.XTextField xTextField2;
    private com.rameses.rcp.control.XTextField xTextField5;
    private com.rameses.rcp.control.XTextField xTextField8;
    private com.rameses.rcp.control.XTextField xTextField9;
    // End of variables declaration//GEN-END:variables
    
}
