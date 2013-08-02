/*
 * ExplorerViewListController.java
 *
 * Created on July 31, 2013, 4:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.rameses.osiris2.common;

import com.rameses.common.PropertyResolver;
import com.rameses.rcp.common.Action;
import com.rameses.rcp.common.MsgBox;
import com.rameses.rcp.common.Node;
import com.rameses.rcp.common.Opener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wflores
 */
public class ExplorerViewListController extends ListController implements ExplorerViewHandler {
    
    private Node node; 
    private Opener opener;
    private ExplorerViewService service;
    private List<Action> formActions; 

    private List<Action> nodeActions; 
    private List<Action> oldActions; 
    
    public ExplorerViewListController() {
    }
        
    // <editor-fold defaultstate="collapsed" desc=" Getters/Setters ">
    
    public String getTitle() {
        Node node = getNode();
        return (node == null? null: node.getCaption()); 
    }
    
    public String getServiceName() {
        throw new IllegalStateException("Please specify a serviceName");
    }
    
    public String getEntityName() {
        throw new IllegalStateException("Please specify an entityName");
    }
    
    public Opener getQueryForm() { return null; }
    
    public List getFormActions() {
        if (formActions == null) {
            formActions = new ArrayList(); 
            
            if (isAllowCreate())
                formActions.add(createAction("create", "New", "images/toolbars/create.png", "ctrl N", 'n', "#{allowCreateItems == true}", true)); 
            
            if (isAllowOpen()) { 
                Action a = createAction("open", "Open", "images/toolbars/open.png", "ctrl O", 'o', "#{selectedEntity != null}", true); 
                a.getProperties().put("depends", "selectedEntity");
                formActions.add(a); 
            }
            
            formActions.add(createAction("reload", "Refresh", "images/toolbars/refresh.png", "ctrl R", 'r', null, true)); 
        }
        return formActions; 
    }  
    
    public boolean isAllowCreateItems() {
        Node node = getNode();
        String childftypes = (node == null? null: node.getPropertyString("childfiletypes"));
        return (childftypes != null && childftypes.length() > 0);
    }
        
    // </editor-fold>
        
    // <editor-fold defaultstate="collapsed" desc=" ExplorerViewHandler implementation ">
    
    public Node getNode() { return node; } 
    public void setNode(Node node) { this.node = node; }
    
    public void setNodeActions(List<Action> nodeActions) {
        this.oldActions = this.nodeActions; 
        this.nodeActions = nodeActions; 
    }
    
    public void setOpener(Opener opener) { this.opener = opener; } 
    
    protected ListService getService() { return service; }    
    public void setService(ExplorerViewService service) { 
        this.service = service; 
    }
    
    public void updateView() { 
        getFormActions();
        if (oldActions != null) {
            for (Action a: oldActions) formActions.remove(a); 
            
            oldActions.clear(); 
            oldActions = null; 
        }
        if (nodeActions != null) {
            for (Action a: nodeActions) formActions.add(a); 
        }
        reload(); 
    }
    
    // </editor-fold>
        
    // <editor-fold defaultstate="collapsed" desc=" overrides/helper/utility methods ">
        
    protected void onbeforeFetchList(Map params) {
        Node node = getNode(); 
        Object item = (node == null? null: node.getItem()); 
        if (item instanceof Map) params.putAll((Map) item); 
    }
    
    public Object open() throws Exception {
        Map item = (Map) getSelectedEntity(); 
        if (item == null) return null;
        
        Node node = getNode();
        if (node != null) {
            if (!node.hasItems()) node.reloadItems(); 
            
            Node selNode = null;      
            Object srcid = item.get("objid");
            PropertyResolver resolver = PropertyResolver.getInstance();
            List<Node> nodes = node.getItems();
            for (Node cnode: nodes) {
                Object citem = cnode.getItem();
                if (!(citem instanceof Map)) continue;
                
                Object oid = ((Map) citem).get("objid");
                if (oid != null && srcid != null && oid.equals(srcid)) {
                    selNode = cnode;
                    break; 
                }
            }
            
            if (selNode != null) {
                selNode.open(); 
                return null; 
            }
        }
        
        if (opener == null) {
            MsgBox.alert("No available file type handler");
            System.out.println("node-item-> " + node.getItem());            
            System.out.println("sel-item-> " + item);            
            return null;
        }
        
        FileType handle = (FileType) opener.getHandle(); 
        handle.setNode(getNode()); 
        handle.setSelectedItem(item); 
        handle.open(); 
        return opener; 
    }
    
    public Opener create() throws Exception {
        if (opener == null) {
            MsgBox.alert("No available file type handler");
            return null;
        }
        
        Map item = (Map) getSelectedEntity();        
        FileType handle = (FileType) opener.getHandle(); 
        handle.setNode(getNode()); 
        handle.setSelectedItem(item); 
        handle.create(); 
        return opener;       
    }
    
    private boolean toBoolean(Object value) {
        if (value == null) return false; 
        if (value instanceof Boolean) return ((Boolean) value).booleanValue();
        if ("true".equals(value.toString())) return true; 
        if ("1".equals(value.toString())) return true;
        
        return false; 
    }
    
    // </editor-fold>

}
