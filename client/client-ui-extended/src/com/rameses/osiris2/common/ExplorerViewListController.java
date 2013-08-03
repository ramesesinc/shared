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
    private List<Action> formActions; 

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
        if (formActions == null) 
            formActions = new ArrayList(); 
        else 
            formActions.clear();
             
        Node node = getNode();
        if (node == null) return;
                
        String context = parentController.getContext();                
        String filetype = node.getPropertyString("filetype");
        if (filetype == null) filetype = parentController.getDefaultFileType();

        if (isAllowCreate() && filetype != null) { 
            List<Invoker> list = (List) node.getProperties().get("Invoker.createlist"); 
            if (list == null && filetype != null) {
                list = actionsProvider.getInvokers(node, (filetype+":additem").toLowerCase());
                node.getProperties().put("Invoker.createlist", list);
            } 
            
            if (!list.isEmpty()) { 
                Action a = createAction("create", "New", "images/toolbars/create.png", "ctrl N", 'n', null, true); 
                formActions.add(a); 
            }
        }        
        if (isAllowOpen()) { 
            List<Invoker> list = (List) node.getProperties().get("Invoker.openlist"); 
            if (list == null && filetype != null) {
                list = actionsProvider.getInvokers(node, (filetype+":openitem").toLowerCase());
                node.getProperties().put("Invoker.openlist", list);
            } 
            
            Action a = createAction("open", "Open", "images/toolbars/open.png", "ctrl O", 'o', "#{selectedEntity != null}", true); 
            a.getProperties().put("depends", "selectedEntity");
            formActions.add(a); 
        }   
        formActions.add(createAction("reload", "Refresh", "images/toolbars/refresh.png", "ctrl R", 'r', null, true));         
        
//        if (filetype != null) {
//            List<Invoker> list = (List) node.getProperties().get("Invoker.actionlist"); 
//            if (list == null) {
//                list = actionsProvider.getInvokers(node, (filetype+":formActions").toLowerCase());
//                node.getProperties().put("Invoker.actionlist", list);
//            }
//        }
        
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

        Opener opener = null;
        List<Invoker> list = (List) node.getProperties().get("Invoker.openlist");
        if (list != null && !list.isEmpty()) {
            Invoker inv = list.get(0);
            HashMap map = new HashMap();
            map.put("node", node);
            map.put("entity", item);
            opener = actionsProvider.toOpener(inv, map, node); 
        } 
                         
        if (opener == null) {
            MsgBox.alert("No available file type handler");
            System.out.println("node-item-> " + node.getItem());            
            System.out.println("sel-item-> " + item);            
            return null;
        } else {
            return opener;
        }
    }
    
    public Opener create() throws Exception {
        Node node = getNode();
        if (node == null) return null;
        
        Opener opener = null;
        List<Invoker> list = (List) node.getProperties().get("Invoker.createlist");
        if (list != null && !list.isEmpty()) {
            Invoker inv = list.get(0);
            HashMap map = new HashMap();
            map.put("node", node);
            map.put("entity", new HashMap()); 
            opener = actionsProvider.toOpener(inv, map, node);             
        }
                         
        if (opener == null) {
            MsgBox.alert("No available file type handler");
            System.out.println("node-item-> " + node.getItem());            
            return null;
        } else {
            return opener;
        }
    }
    
    private boolean toBoolean(Object value) {
        if (value == null) return false; 
        if (value instanceof Boolean) return ((Boolean) value).booleanValue();
        if ("true".equals(value.toString())) return true; 
        if ("1".equals(value.toString())) return true;
        
        return false; 
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
        
        List<Opener> getOpeners(Node node, String invokerType) {
            try {                
                List<Invoker> invokers = InvokerUtil.lookup(invokerType);
                return toOpeners(invokers, node); 
            } catch(Throwable t) {
                return new ArrayList(); 
            }   
        }
        
        Opener toOpener(Invoker invoker, Map params, Node node) {
            Opener a = InvokerUtil.createOpener(invoker, params); 
            String target = a.getTarget()+"";
            if (!target.matches("window|popup|process|_window|_popup|_process")) {
                a.setTarget("popup"); 
            } 
            return a;
        }
        
        private List<Opener> toOpeners(List<Invoker> invokers, Node node) {
            List<Opener> openers = new ArrayList();
            Object item = node.getItem(); 
            for (Invoker inv: invokers) { 
                Opener a = InvokerUtil.createOpener(inv);
                String expr = (String) a.getProperties().get("expr"); 
                boolean passed = (expr == null || expr.length() == 0); 
                if (!passed) {
                    try {
                        FileType handle = (FileType) a.getHandle();
                        handle.setNode(node);
                        passed = resolver.evalBoolean(expr, item);
                    } catch(Throwable t) {
                        System.out.println("[WARN] failed to eval action expr caused by " + t.getMessage()); 
                        passed = false; 
                    }
                }
                if (passed) {
                    String target = a.getTarget()+"";
                    if (!target.matches("window|popup|process|_window|_popup|_process")) {
                        a.setTarget("popup"); 
                    } 
                    openers.add(a);
                }
            }   
            return openers;
        }
    }
    
    // </editor-fold>
        
    // <editor-fold defaultstate="collapsed" desc=" ActionOpener (class) "> 
    
    private class ActionOpener extends Action
    {
        ExplorerViewListController root = ExplorerViewListController.this;
        private Opener opener;
        
        ActionOpener(Opener opener) {
            this.opener = opener; 
            this.setName(opener.getAction());
            this.setCaption(opener.getCaption());
            
            Map props = opener.getProperties(); 
            this.getProperties().putAll(props);            
            this.setIcon(getString(props, "icon"));
            this.setVisibleWhen(getString(props, "visibleWhen"));
        }
        
        public Object execute() { 
            String target = opener.getTarget()+"";
            if (!target.matches("window|popup|process|_window|_popup|_process"))
                opener.setTarget("popup");
                        
            Node node = getNode();
            FileType handle = (FileType) opener.getHandle(); 
            handle.setNode(node); 
            handle.setEntity((Map) node.getItem()); 
            return opener; 
        } 
        
        private String getString(Map data, String name) {
            Object o = data.get(name);
            return (o == null? null: o.toString()); 
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
