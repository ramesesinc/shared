/*
 * ExplorerListViewController.java
 *
 * Created on July 31, 2013, 4:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.rameses.osiris2.common;

import com.rameses.rcp.common.Action;
import com.rameses.rcp.common.Column;
import com.rameses.rcp.common.Node;
import com.rameses.rcp.common.Opener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wflores
 */
public class ExplorerListViewController extends ListController implements ExplorerNodeViewer {
    
    private Node node;
    private List<Action> formActions; 
    
    public ExplorerListViewController() {
    }
    
    
    // <editor-fold defaultstate="collapsed" desc=" Getters/Setters ">
    
    public String getServiceName() {
        throw new IllegalStateException("Please specify a serviceName");
    }
    
    public String getEntityName() {
        throw new IllegalStateException("Please specify an entityName");
    }
    
    public Opener getQueryForm() { return null; }

    public boolean isAllowChildren() {
        Node node = getNode();
        if (node == null) return false; 

        boolean passed = (node.getNodemask() & Node.ALLOW_CHILDREN)>0; 
        //System.out.println("caption=" + node.getCaption() + ", nodemask="+node.getNodemask() + ", allowChildren="+passed);
        return passed; 
    }
    
    public boolean isDynamic() {
        Node node = getNode();
        if (node == null) return false; 
        
        return (node.getNodemask() & Node.DYNAMIC)>0; 
    }    
    
    public List getFormActions() {
        if (formActions == null) {
            formActions = new ArrayList(); 
            
            if (isAllowCreate())
                formActions.add(createAction("create", "New", "images/toolbars/create.png", "ctrl N", 'n', "#{allowChildren == true}", true)); 
            
            if (isAllowOpen()) { 
                Action a = createAction("open", "Open", "images/toolbars/open.png", "ctrl O", 'o', "#{selectedEntity != null}", true); 
                a.getProperties().put("depends", "selectedEntity");
                formActions.add(a); 
            }
            
            formActions.add(createAction("reload", "Refresh", "images/toolbars/refresh.png", "ctrl R", 'r', null, true));             
        }
        return formActions; 
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
    
    public Column[] getColumns() { 
        Node node = getNode(); 
        if (node != null && !node.isLeaf()) {
            return new Column[]{
                new Column("caption", "Name") 
            };
        } else { 
            return super.getColumns(); 
        }
    }
    
    protected void onfetchList(Map params) {
        Node node = getNode(); 
        Object item = (node == null? null: node.getItem()); 
        if (item instanceof Map) params.putAll((Map) item); 
    }
    
    public List fetchList(Map params) {
        Node node = getNode(); 
        if (node != null && !node.isLeaf()) {
            if (!node.hasItems()) node.reloadItems();
            
            return node.getItems();
        } 
        else {
            return super.fetchList(params); 
        }
    }    
    
    public Object open() throws Exception {
        Object o = getSelectedEntity(); 
        if (o instanceof Node) { 
            Node childNode = (Node) o;
            childNode.open();
            return null; 
        }   
        else {
            return super.open(); 
        }
    }
    
    // </editor-fold>

}
