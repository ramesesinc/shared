/*
 * FormPage.java
 *
 * Created on April 24, 2013, 12:44 PM
 */

package com.rameses.admin.user;

import com.rameses.rcp.control.border.XToolbarBorder;

/**
 *
 * @author  wflores
 */
public class UserSecurityGroupTemplate extends javax.swing.JPanel {
    
    public UserSecurityGroupTemplate() {
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
        xHorizontalPanel1 = new com.rameses.rcp.control.XHorizontalPanel();
        xabFormActions = new com.rameses.rcp.control.XActionBar();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        com.rameses.rcp.control.border.XEtchedBorder xEtchedBorder1 = new com.rameses.rcp.control.border.XEtchedBorder();
        xEtchedBorder1.setHideLeft(true);
        xEtchedBorder1.setHideRight(true);
        xHorizontalPanel1.setBorder(xEtchedBorder1);
        xabFormActions.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        xabFormActions.setDepends(new String[] {"selectedSecuritygroup"});
        xabFormActions.setName("formActions");
        xHorizontalPanel1.add(xabFormActions);

        jPanel1.add(xHorizontalPanel1, java.awt.BorderLayout.SOUTH);

        add(jPanel1, java.awt.BorderLayout.NORTH);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private com.rameses.rcp.control.XHorizontalPanel xHorizontalPanel1;
    private com.rameses.rcp.control.XActionBar xabFormActions;
    // End of variables declaration//GEN-END:variables
    
}
