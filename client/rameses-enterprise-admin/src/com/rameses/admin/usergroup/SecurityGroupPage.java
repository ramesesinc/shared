/*
 * SecurityGroupPage.java
 *
 * Created on July 29, 2013, 7:30 PM
 */

package com.rameses.admin.usergroup;

import com.rameses.osiris2.themes.OKCancelPage;
import com.rameses.rcp.ui.annotations.StyleSheet;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author  Elmo
 */
@Template(value=OKCancelPage.class)
@StyleSheet
public class SecurityGroupPage extends javax.swing.JPanel {
    
    /** Creates new form SecurityGroupPage */
    public SecurityGroupPage() {
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
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        xList1 = new com.rameses.rcp.control.XList();
        xDataTable1 = new com.rameses.rcp.control.XDataTable();
        xFormPanel1 = new com.rameses.rcp.control.XFormPanel();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xTextField2 = new com.rameses.rcp.control.XTextField();

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Object Permissions");
        jPanel1.setBorder(xTitledBorder1);
        jSplitPane1.setDividerLocation(150);
        xList1.setExpression("#{item}");
        xList1.setItems("objectList");
        xList1.setName("selectedObject");
        jScrollPane2.setViewportView(xList1);

        jSplitPane1.setLeftComponent(jScrollPane2);

        xDataTable1.setColumns(new com.rameses.rcp.common.Column[]{
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "checked"}
                , new Object[]{"caption", null}
                , new Object[]{"width", 20}
                , new Object[]{"minWidth", 20}
                , new Object[]{"maxWidth", 20}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", true}
                , new Object[]{"editableWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.UPPER}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.CheckBoxColumnHandler(java.lang.Boolean.class, true, false)}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "permission"}
                , new Object[]{"caption", "Permission"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", false}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.UPPER}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.TextColumnHandler()}
            })
        });
        xDataTable1.setDepends(new String[] {"selectedObject"});
        xDataTable1.setDynamic(true);
        xDataTable1.setHandler("permissionListModel");
        jSplitPane1.setRightComponent(xDataTable1);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
        );

        xFormPanel1.setCaptionWidth(120);
        xTextField1.setCaption("Security Group Name");
        xTextField1.setEnabled(false);
        xTextField1.setName("securitygroupname");
        xTextField1.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField1);

        xTextField2.setCaption("User Group Name");
        xTextField2.setEnabled(false);
        xTextField2.setName("usergroup.objid");
        xTextField2.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, xFormPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(xFormPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private com.rameses.rcp.control.XDataTable xDataTable1;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XList xList1;
    private com.rameses.rcp.control.XTextField xTextField1;
    private com.rameses.rcp.control.XTextField xTextField2;
    // End of variables declaration//GEN-END:variables
    
}
