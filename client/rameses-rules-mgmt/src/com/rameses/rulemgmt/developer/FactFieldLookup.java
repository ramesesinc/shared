/*
 * FactInfo.java
 *
 * Created on October 7, 2013, 10:04 PM
 */

package com.rameses.rulemgmt.developer;

import com.rameses.rcp.ui.annotations.StyleSheet;

/**
 *
 * @author  Elmo
 */
public class FactFieldLookup extends javax.swing.JPanel {
    
    /** Creates new form FactInfo */
    public FactFieldLookup() {
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
        xCheckBox1 = new com.rameses.rcp.control.XCheckBox();
        xCheckBox2 = new com.rameses.rcp.control.XCheckBox();
        xTextField3 = new com.rameses.rcp.control.XTextField();
        jLabel2 = new javax.swing.JLabel();
        xFormPanel1 = new com.rameses.rcp.control.XFormPanel();
        xTextField6 = new com.rameses.rcp.control.XTextField();
        xTextField5 = new com.rameses.rcp.control.XTextField();
        xTextField8 = new com.rameses.rcp.control.XTextField();
        xCheckBox3 = new com.rameses.rcp.control.XCheckBox();

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Lookup Info");
        setBorder(xTitledBorder1);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Variable Support", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11)));
        xCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        xCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xCheckBox1.setText("Can be assigned as variable");

        xCheckBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        xCheckBox2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xCheckBox2.setText("Can reference variable");

        xTextField3.setName("field.vardatatype");
        xTextField3.setSpaceChar('_');
        xTextField3.setTextCase(com.rameses.rcp.constant.TextCase.NONE);

        jLabel2.setText("Use variable data type");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(xCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(xCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(xTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        xFormPanel1.setCaptionWidth(120);
        xTextField6.setCaption("Lookup Handler");
        xTextField6.setName("field.lookuphandler");
        xTextField6.setPreferredSize(new java.awt.Dimension(150, 20));
        xTextField6.setSpaceChar('_');
        xTextField6.setTextCase(com.rameses.rcp.constant.TextCase.NONE);
        xFormPanel1.add(xTextField6);

        xTextField5.setCaption("Lookup Key");
        xTextField5.setName("field.lookupkey");
        xTextField5.setPreferredSize(new java.awt.Dimension(150, 20));
        xTextField5.setSpaceChar('_');
        xTextField5.setTextCase(com.rameses.rcp.constant.TextCase.NONE);
        xFormPanel1.add(xTextField5);

        xTextField8.setCaption("Lookup Value");
        xTextField8.setName("field.lookupvalue");
        xTextField8.setPreferredSize(new java.awt.Dimension(150, 20));
        xTextField8.setSpaceChar('_');
        xTextField8.setTextCase(com.rameses.rcp.constant.TextCase.NONE);
        xFormPanel1.add(xTextField8);

        xCheckBox3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        xCheckBox3.setCaption("");
        xCheckBox3.setCheckValue(1);
        xCheckBox3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xCheckBox3.setName("field.multivalued");
        xCheckBox3.setPreferredSize(new java.awt.Dimension(0, 15));
        xCheckBox3.setText("Source field is multi value");
        xCheckBox3.setUncheckValue(0);
        xFormPanel1.add(xCheckBox3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xFormPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(324, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xFormPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private com.rameses.rcp.control.XCheckBox xCheckBox1;
    private com.rameses.rcp.control.XCheckBox xCheckBox2;
    private com.rameses.rcp.control.XCheckBox xCheckBox3;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XTextField xTextField3;
    private com.rameses.rcp.control.XTextField xTextField5;
    private com.rameses.rcp.control.XTextField xTextField6;
    private com.rameses.rcp.control.XTextField xTextField8;
    // End of variables declaration//GEN-END:variables
    
}
