/*
 * ExplorerViewListController.java
 *
 * Created on July 31, 2013, 4:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.rameses.osiris2.common;

import com.rameses.common.ExpressionResolver;
import com.rameses.common.PropertyResolver;
import com.rameses.osiris2.Invoker;
import com.rameses.osiris2.client.InvokerUtil;
import com.rameses.rcp.common.Action;
import com.rameses.rcp.common.MsgBox;
import com.rameses.rcp.common.Node;
import com.rameses.rcp.common.Opener;
import com.rameses.rcp.common.PopupMenuOpener;
import com.rameses.rcp.framework.Binding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wflores
 */
public class ExplorerViewListController extends ListController implements ExplorerViewHandler {
    
    private Node node; 
    private ExplorerViewService service;
    private List<Invoker> defaultInvokers; 
    private List<Action> formActions = new ArrayList(); 
    private List<Action> nodeActions = new ArrayList();
    
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
    public List getFormActions() { return formActions; }  
    public List getNodeActions() { return nodeActions; } 
    
    public boolean isAllowCreateItems() {
        return true;
    }
        
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" ExplorerViewHandler implementation ">

    private DefaultListService defaultService = new DefaultListService();    
    private ActionsProvider actionsProvider = new ActionsProvider();     
    private ExplorerViewController parentController;
    
    public void setParent(ExplorerViewController parentController) {
        this.parentController = parentController; 
    }
        
    public Node getNode() { return node; } 
    public void setNode(Node node) { this.node = node; }
        
    protected ListService getService() { 
        if (parentController == null) return defaultService;

        return parentController.getService(); 
    }
        
    public void updateView() { 
        buildActions();
        Binding binding = getBinding();
        if (binding != null) {
            binding.refresh("formActions");
        } 
        reload(); 
    }
    
    private void buildActions() {
        formActions.clear();
        nodeActions.clear();
        
        Node node = getNode();
        if (node == null) return;

        String defaultFiletype = parentController.getDefaultFileType();        
        String context = parentController.getContext();        
        String filetype = node.getPropertyString("filetype");
        //load node actions
        if (filetype != null) {
            List<Invoker> list = (List) node.getProperties().get("Invoker.editlist"); 
            if (list == null) {
                String invtype = (filetype + ":edit").toLowerCase();
                list = actionsProvider.getInvokers(node, invtype);
                node.getProperties().put("Invoker.editlist", list); 
            }
            
            if (!list.isEmpty()) { 
                Action a = createAction("edit", "", "images/toolbars/edit.png", "ctrl E", 'e', null, true); 
                nodeActions.add(a);
            } 
        }
                
        if (filetype == null) filetype = defaultFiletype;

        if (isAllowCreate() && filetype != null) { 
            List<Invoker> list = (List) node.getProperties().get("Invoker.createlist"); 
            if (list == null) {
                list = new ArrayList();                
                if (filetype != null) {
                    String invtype = (filetype+":create").toLowerCase();
                    list.addAll(actionsProvider.getInvokers(node, invtype));
                }
                
                String childnodes = node.getPropertyString("childnodes");
                if (childnodes != null && childnodes.trim().length() > 0) {
                    String[] values = childnodes.trim().split(",");
                    for (int i=0; i<values.length; i++) {
                        String invtype = (values[i]+":create").toLowerCase();
                        list.addAll(actionsProvider.getInvokers(node, invtype));
                    }
                }
                
                node.getProperties().put("Invoker.createlist", list);
            } 
            
            if (!list.isEmpty()) { 
                Action a = createAction("create", "New", "images/toolbars/create.png", "ctrl N", 'n', null, true); 
                formActions.add(a); 
            }
        }        
        if (isAllowOpen()) { 
            List<Invoker> list = (List) node.getProperties().get("Invoker.openlist"); 
            if (list == null) {
                list = new ArrayList();
                if (filetype != null) {
                    String invtype = (filetype+":open").toLowerCase();
                    list.addAll(actionsProvider.getInvokers(node, invtype));                
                } 
                
                String childnodes = node.getPropertyString("childnodes"); 
                if (childnodes != null && childnodes.trim().length() > 0) { 
                    String[] values = childnodes.trim().split(","); 
                    for (int i=0; i<values.length; i++) { 
                        String invtype = (values[i]+":open").toLowerCase(); 
                        list.addAll(actionsProvider.getInvokers(node, invtype)); 
                    } 
                } 
                
                node.getProperties().put("Invoker.openlist", list); 
            } 
            
            Action a = createAction("open", "Open", "images/toolbars/open.png", "ctrl O", 'o', "#{selectedEntity != null}", true); 
            a.getProperties().put("depends", "selectedEntity"); 
            formActions.add(a); 
        }
        
        formActions.add(createAction("reload", "Refresh", "images/toolbars/refresh.png", "ctrl R", 'r', null, true)); 
        
        //load generic actions
        if (defaultInvokers == null && defaultFiletype != null) {
            defaultInvokers = actionsProvider.getInvokers(node, defaultFiletype.toLowerCase()+":formActions");
        }        
        if (defaultInvokers != null && !defaultInvokers.isEmpty()) {
            for (Invoker invoker: defaultInvokers) {
                formActions.add(new ActionInvoker(invoker)); 
            }
        }
        //load specific actions
        List<Invoker> list = (List) node.getProperties().get("Invoker.formActions"); 
        if (list == null && filetype != null) {
            list = actionsProvider.getInvokers(node, filetype.toLowerCase()+":formActions");
            node.getProperties().put("Invoker.formActions", list);
        } 
        if (list != null && !list.isEmpty()) {
            for (Invoker invoker: list) {
                formActions.add(new ActionInvoker(invoker)); 
            }
        } 
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
        if (node == null) return null;
        
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
        
        List<Invoker> list = (List) node.getProperties().get("Invoker.openlist");
        if (list == null || list.isEmpty()) {
            MsgBox.alert("No available file type handler");
            return null;
        }
        
        PopupMenuOpener opener = new PopupMenuOpener();        
        for (Invoker invoker: list) {
            HashMap map = new HashMap();
            map.put("node", node);
            map.put("entity", item);
            opener.add(actionsProvider.toOpener(invoker, map, node));
        }
        return opener;
    }
    
    public Opener create() throws Exception {
        Node node = getNode();
        if (node == null) return null;
        
        List<Invoker> list = (List) node.getProperties().get("Invoker.createlist");
        if (list == null || list.isEmpty()) {
            MsgBox.alert("No available file type handler");
            System.out.println("node-item-> " + node.getItem());            
            return null;            
        }
        
        PopupMenuOpener opener = new PopupMenuOpener();        
        for (Invoker invoker: list) {
            HashMap map = new HashMap();
            map.put("node", node);
            map.put("entity", new HashMap());
            opener.add(actionsProvider.toOpener(invoker, map, node));
        }
        return opener;        
    }
    
    public Object edit() throws Exception {
        Node node = getNode();
        if (node == null) return null;

        List<Invoker> list = (List) node.getProperties().get("Invoker.editlist");
        if (list == null || list.isEmpty()) {
            MsgBox.alert("No available file type handler");
            System.out.println("node-item-> " + node.getItem());            
            return null;            
        }
        
        Invoker invoker = list.get(0);
        HashMap map = new HashMap();
        map.put("node", node);
        map.put("entity", node.getItem());
        return actionsProvider.toOpener(invoker, map, node); 
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" ActionsProvider (class) "> 
    
    private class ActionsProvider 
    {
        private ExpressionResolver resolver = ExpressionResolver.getInstance(); 
        
        List<Invoker> getInvokers(Node node, String invokerType) {
            try {                
                return InvokerUtil.lookup(invokerType);
            } catch(Throwable t) {
                return new ArrayList(); 
            }   
        }
        
        Invoker getInvoker(Node node, String invokerType) {
            List<Invoker> list = getInvokers(node, invokerType);
            return (list.isEmpty()? null: list.get(0)); 
        }        
                
        Opener toOpener(Invoker invoker, Map params, Node node) {
            Opener a = InvokerUtil.createOpener(invoker, params); 
            String target = a.getTarget()+"";
            if (!target.matches("window|popup|process|_window|_popup|_process")) {
                a.setTarget("popup"); 
            } 
            return a;
        }
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" ActionInvoker (class) "> 
    
    private class ActionInvoker extends Action 
    {
        ExplorerViewListController root = ExplorerViewListController.this;
        private Invoker invoker;
        
        ActionInvoker(Invoker invoker) {
            this.invoker = invoker; 
            
            this.setName(invoker.getAction()); 
            this.setCaption(invoker.getCaption()); 
            this.setIcon(getString("icon"));
            this.setVisibleWhen(getString("visibleWhen"));            
            this.setMnemonic(getChar("mnemonic"));
            this.setTooltip(getString("tooltip"));
            
            Boolean bool = getBoolean("immediate"); 
            if (bool != null) this.setImmediate(bool.booleanValue());
            
            bool = getBoolean("showCaption"); 
            if (bool != null) this.setShowCaption(bool.booleanValue());
            
            this.getProperties().putAll(invoker.getProperties()); 
        }

        public Object execute() { 
            Node node = root.getNode();
            HashMap map = new HashMap();
            map.put("node", node);
            map.put("entity", (Map) root.getSelectedEntity());
            return actionsProvider.toOpener(invoker, map, node); 
        } 
        
        private String getString(String name) {
            if (invoker == null || invoker.getProperties() == null) return null;
            
            Object ov = invoker.getProperties().get(name); 
            return (ov == null? null: ov.toString()); 
        }
        
        private char getChar(String name) {
            String sv = getString(name);
            if (sv == null || sv.trim().length() == 0) return '\u0000';
            
            return sv.trim().charAt(0); 
        } 
        
        private Boolean getBoolean(String name) {
            String sv = getString(name);
            if (sv == null || sv.length() == 0) return null;
            
            return "true".equalsIgnoreCase(sv); 
        }         
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" DefaultListService (class) "> 
    
    private class DefaultListService implements ListService 
    {
        public List<Map> getColumns(Map params) {
            List<Map> columns = new ArrayList();
            Map map = new HashMap();
            map.put("name", "caption");
            map.put("caption", "Name");
            columns.add(map); 
            return columns;
        }

        public List getList(Map params) {
            return new ArrayList(); 
        }        
    }
    
    // </editor-fold>
}
