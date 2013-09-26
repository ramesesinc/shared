/*
 * NewJPanel.java
 *
 * Created on July 28, 2013, 8:34 PM
 */

package com.rameses.admin.usergroup;

import com.rameses.osiris2.themes.FormPage;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author  Elmo
 */
@Template(FormPage.class)
public class UsergroupPage extends javax.swing.JPanel {
    
    /** Creates new form NewJPanel */
    public UsergroupPage() {
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
        jPanel1 = new javax.swing.JPanel();
        xFormPanel1 = new com.rameses.rcp.control.XFormPanel();
        xLabel1 = new com.rameses.rcp.control.XLabel();
        xLookupField1 = new com.rameses.rcp.control.XLookupField();
        xTextField2 = new com.rameses.rcp.control.XTextField();
        xLookupField2 = new com.rameses.rcp.control.XLookupField();
        xComboBox1 = new com.rameses.rcp.control.XComboBox();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xButton1 = new com.rameses.rcp.control.XButton();
        jPanel3 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("General Information");
        jPanel1.setBorder(xTitledBorder1);

        xFormPanel1.setCaptionWidth(120);
        xLabel1.setCaption("User Group");
        xLabel1.setExpression("#{entity.usergroupid}");
        xFormPanel1.add(xLabel1);

        xLookupField1.setCaption("User");
        xLookupField1.setExpression("#{  (item ) ?  item.lastname + ' ,  ' +  item.firstname: ''  }");
        xLookupField1.setHandler("lookupUser");
        xLookupField1.setIndex(-10);
        xLookupField1.setName("entity.user");
        xLookupField1.setPreferredSize(new java.awt.Dimension(0, 19));
        xLookupField1.setRequired(true);
        xFormPanel1.add(xLookupField1);

        xTextField2.setCaption("Job Title");
        xTextField2.setDepends(new String[] {"entity.user"});
        xTextField2.setName("entity.jobtitle");
        xTextField2.setPreferredSize(new java.awt.Dimension(0, 19));
        xFormPanel1.add(xTextField2);

        xLookupField2.setCaption("Org");
        xLookupField2.setExpression("#{  (item) ? item.name : '' }");
        xLookupField2.setHandler("lookupOrg");
        xLookupField2.setName("entity.org");
        xLookupField2.setPreferredSize(new java.awt.Dimension(0, 19));
        xFormPanel1.add(xLookupField2);

        xComboBox1.setCaption("Security Group");
        xComboBox1.setExpression("#{item.name}");
        xComboBox1.setItemKey("objid");
        xComboBox1.setItems("securityGroups");
        xComboBox1.setName("entity.securitygroupid");
        xComboBox1.setPreferredSize(new java.awt.Dimension(0, 22));
        xComboBox1.setRequired(true);
        xFormPanel1.add(xComboBox1);

        xTextField1.setCaption("User Txn Code");
        xTextField1.setName("entity.usertxncode");
        xTextField1.setPreferredSize(new java.awt.Dimension(0, 19));
        xFormPanel1.add(xTextField1);

        xButton1.setIndex(1);
        xButton1.setName("openSecurityGroup");
        xButton1.setText("v");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(xFormPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 356, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(xFormPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(xButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(36, 36, 36))))
        );

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel3.setName("content");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 15, Short.MAX_VALUE)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 182, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        add(jPanel2, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private com.rameses.rcp.control.XButton xButton1;
    private com.rameses.rcp.control.XComboBox xComboBox1;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XLookupField xLookupField1;
    private com.rameses.rcp.control.XLookupField xLookupField2;
    private com.rameses.rcp.control.XTextField xTextField1;
    private com.rameses.rcp.control.XTextField xTextField2;
    // End of variables declaration//GEN-END:variables
    
}
