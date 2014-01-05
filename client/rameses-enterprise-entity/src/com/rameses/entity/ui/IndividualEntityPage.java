/*
 * IndividualEntityPage.java
 *
 * Created on August 14, 2013, 2:17 PM
 */

package com.rameses.entity.ui;

import com.rameses.osiris2.themes.FormPage;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author  wflores
 */
@Template(FormPage.class)
public class IndividualEntityPage extends javax.swing.JPanel {
    
    public IndividualEntityPage() {
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
        xLabel1 = new com.rameses.rcp.control.XLabel();
        xTextField3 = new com.rameses.rcp.control.XTextField();
        xTextField2 = new com.rameses.rcp.control.XTextField();
        xTextField5 = new com.rameses.rcp.control.XTextField();
        xTextField10 = new com.rameses.rcp.control.XTextField();
        xDateField1 = new com.rameses.rcp.control.XDateField();
        xTextField4 = new com.rameses.rcp.control.XTextField();
        xTextField6 = new com.rameses.rcp.control.XTextField();
        xComboBox1 = new com.rameses.rcp.control.XComboBox();
        xComboBox2 = new com.rameses.rcp.control.XComboBox();
        xTextField7 = new com.rameses.rcp.control.XTextField();
        xTextField8 = new com.rameses.rcp.control.XTextField();
        xTextField9 = new com.rameses.rcp.control.XTextField();
        jPanel1 = new javax.swing.JPanel();
        xDataTable1 = new com.rameses.rcp.control.XDataTable();

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setPadding(new java.awt.Insets(30, 15, 15, 15));
        xTitledBorder1.setTitle("General Information");
        xFormPanel1.setBorder(xTitledBorder1);
        xLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        xLabel1.setCaption("Acct No");
        xLabel1.setCaptionWidth(100);
        xLabel1.setExpression("#{entity.entityno}");
        xLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        xLabel1.setPreferredSize(new java.awt.Dimension(200, 20));
        xFormPanel1.add(xLabel1);

        xTextField3.setCaption("Last Name");
        xTextField3.setCaptionWidth(100);
        xTextField3.setName("entity.lastname");
        xTextField3.setPreferredSize(new java.awt.Dimension(0, 20));
        xTextField3.setRequired(true);
        xFormPanel1.add(xTextField3);

        xTextField2.setCaption("First Name");
        xTextField2.setCaptionWidth(100);
        xTextField2.setName("entity.firstname");
        xTextField2.setPreferredSize(new java.awt.Dimension(0, 20));
        xTextField2.setRequired(true);
        xFormPanel1.add(xTextField2);

        xTextField5.setCaption("Middle Name");
        xTextField5.setCaptionWidth(100);
        xTextField5.setName("entity.middlename");
        xTextField5.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField5);

        xTextField10.setCaption("Address");
        xTextField10.setCaptionWidth(100);
        xTextField10.setName("entity.address");
        xTextField10.setPreferredSize(new java.awt.Dimension(0, 20));
        xTextField10.setRequired(true);
        xFormPanel1.add(xTextField10);

        xDateField1.setCaption("Birth Date");
        xDateField1.setCaptionWidth(100);
        xDateField1.setName("entity.birthdate");
        xDateField1.setPreferredSize(new java.awt.Dimension(100, 19));
        xFormPanel1.add(xDateField1);

        xTextField4.setCaption("Birth Place");
        xTextField4.setCaptionWidth(100);
        xTextField4.setName("entity.birthplace");
        xTextField4.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField4);

        xTextField6.setCaption("Citizenship");
        xTextField6.setCaptionWidth(100);
        xTextField6.setName("entity.citizenship");
        xTextField6.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField6);

        xComboBox1.setCaption("Gender");
        xComboBox1.setCaptionWidth(100);
        xComboBox1.setExpression("#{item.caption}");
        xComboBox1.setItemKey("name");
        xComboBox1.setItems("genderList");
        xComboBox1.setName("entity.gender");
        xComboBox1.setPreferredSize(new java.awt.Dimension(150, 22));
        xFormPanel1.add(xComboBox1);

        xComboBox2.setCaption("Civil Status");
        xComboBox2.setCaptionWidth(100);
        xComboBox2.setExpression("#{item.caption}");
        xComboBox2.setItemKey("name");
        xComboBox2.setItems("civilStatusList");
        xComboBox2.setName("entity.civilstatus");
        xComboBox2.setPreferredSize(new java.awt.Dimension(150, 22));
        xFormPanel1.add(xComboBox2);

        xTextField7.setCaption("Profession");
        xTextField7.setCaptionWidth(100);
        xTextField7.setCellPadding(new java.awt.Insets(10, 0, 0, 0));
        xTextField7.setName("entity.profession");
        xTextField7.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField7);

        xTextField8.setCaption("TIN");
        xTextField8.setCaptionWidth(100);
        xTextField8.setName("entity.tin");
        xTextField8.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField8);

        xTextField9.setCaption("SSS");
        xTextField9.setCaptionWidth(100);
        xTextField9.setName("entity.sss");
        xTextField9.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField9);

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder2 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder2.setTitle("Contact Information");
        jPanel1.setBorder(xTitledBorder2);
        xDataTable1.setColumns(new com.rameses.rcp.common.Column[]{
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "contacttype"}
                , new Object[]{"caption", "Contact Type"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 200}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", true}
                , new Object[]{"editableWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.UPPER}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.ComboBoxColumnHandler("contactTypes", "key", "#{item.value}")}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "contact"}
                , new Object[]{"caption", "Contact Value"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", true}
                , new Object[]{"editableWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.TextColumnHandler()}
            })
        });
        xDataTable1.setHandler("contactListHandler");
        xDataTable1.setIndex(1);
        xDataTable1.setName("selectedContact");
        xDataTable1.setReadonlyWhen("#{mode == 'read'}");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(xDataTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(xDataTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, xFormPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(xFormPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private com.rameses.rcp.control.XComboBox xComboBox1;
    private com.rameses.rcp.control.XComboBox xComboBox2;
    private com.rameses.rcp.control.XDataTable xDataTable1;
    private com.rameses.rcp.control.XDateField xDateField1;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XTextField xTextField10;
    private com.rameses.rcp.control.XTextField xTextField2;
    private com.rameses.rcp.control.XTextField xTextField3;
    private com.rameses.rcp.control.XTextField xTextField4;
    private com.rameses.rcp.control.XTextField xTextField5;
    private com.rameses.rcp.control.XTextField xTextField6;
    private com.rameses.rcp.control.XTextField xTextField7;
    private com.rameses.rcp.control.XTextField xTextField8;
    private com.rameses.rcp.control.XTextField xTextField9;
    // End of variables declaration//GEN-END:variables
    
}
