/*
 * ExplorerListViewController.java
 *
 * Created on July 31, 2013, 4:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.rameses.osiris2.common;

import com.rameses.rcp.common.Node;
import com.rameses.rcp.common.Opener;
import java.util.Map;

/**
 *
 * @author wflores
 */
public class ExplorerListViewController extends ListController implements ExplorerNodeViewer {
    
    private Node node;
    private Opener queryForm;
    
    public ExplorerListViewController() {
    }
    
    public Opener getQueryForm() {
        return queryForm;
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Getters/Setters ">
    
    public String getServiceName() {
        throw new IllegalStateException("Please specify a serviceName");
    }
    
    public String getEntityName() {
        throw new IllegalStateException("Please specify an entityName");
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" ExplorerNodeViewer implementation ">
    
    public Node getNode() { return this.node; }
    public void setNode(Node node) {
        this.node = node;
    }
    
    public void updateView() {
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" overrides/helper/utility methods ">
    
    protected boolean onCreate(Map params) {
        params.put("node", getNode());
        return true;
    }
    
    protected boolean onOpen(Map params) {
        params.put("node", getNode());
        return true;
    }
    
    protected void onfetchList(Map params) {
        Node node = getNode();
        Object item = (node == null? null: node.getItem());
        if (item instanceof Map) params.putAll((Map) item);
    }
    
    // </editor-fold>
    
    
    
}
