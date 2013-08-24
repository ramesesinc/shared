/*
 * InboxController.java
 *
 * Created on August 23, 2013, 1:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.rameses.osiris2.common;

import com.rameses.common.ExpressionResolver;
import com.rameses.common.PropertyResolver;
import com.rameses.osiris2.Invoker;
import com.rameses.osiris2.client.InvokerProxy;
import com.rameses.osiris2.client.InvokerUtil;
import com.rameses.rcp.common.Action;
import com.rameses.rcp.common.MsgBox;
import com.rameses.rcp.common.Node;
import com.rameses.rcp.common.Opener;
import com.rameses.rcp.common.PopupMenuOpener;
import com.rameses.rcp.common.TreeNodeModel;
import com.rameses.rcp.framework.Binding;
import com.rameses.rcp.util.ControlSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wflores
 */
public class InboxController extends ListController {
    
    private String context; 
    private String defaultFileType;
    private String serviceName;
    
    private TreeNodeModel nodeModel; 
    private Node selectedNode;   
    private ExplorerViewService service;
    
    private List<Action> formActions; 
    private ActionsProvider actionsProvider;
    private Helper helper; 
    
    public InboxController() {
        actionsProvider = new ActionsProvider(); 
        helper = new Helper();                
        formActions = new ArrayList(); 
        init();        
    }

    public void init() {
        formActions.clear();
        formActions.add(createAction("reload", "Refresh", "images/toolbars/refresh.png", "ctrl R", 'r', null, true)); 
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Getters/Settters ">
    
    public String getContext() { 
        if (context == null) {
            Object o = getWorkunitProperties().get("context");
            context = (o == null? "explorer": o.toString()); 
        } 
        return context; 
    }
    
    public String getDefaultFileType() { 
        if (defaultFileType == null) {
            Object o = getWorkunitProperties().get("defaultFileType");
            defaultFileType = (o == null? null: o.toString()); 
        } 
        return defaultFileType;     
    } 
    
    public String getServiceName() { 
        if (serviceName == null) {
            Object o = getWorkunitProperties().get("serviceName");
            serviceName = (o == null? null: o.toString()); 
        }
        return serviceName; 
    }
    
    public String getEntityName() { 
        if (serviceName == null) {
            Object o = getWorkunitProperties().get("entityName");
            serviceName = (o == null? null: o.toString()); 
        }
        return serviceName; 
    }  
    
    private Object _dynamicColumns;
    public boolean isDynamicColumns() {
        if (_dynamicColumns == null) {
            Object o = getWorkunitProperties().get("dynamicColumns");
            _dynamicColumns = (o == null? "true": "true".equals(o.toString())); 
        } 
        return "true".equals(_dynamicColumns+"");
    }

    public int getRows() { return 20; } 
    public List getFormActions() { return formActions; }  
    
    public Node getSelectedNode() { return selectedNode; } 
    public void setSelectedNode(Node selectedNode) {
        this.selectedNode = selectedNode; 
    }
    
    public TreeNodeModel getNodeModel() { 
        if (nodeModel == null) {
            nodeModel = new TreeNodeModelImpl(); 
        }
        return nodeModel;
    }
    
    public ExplorerViewService getService() 
    {
        String name = getServiceName();
        if (name == null || name.trim().length() == 0)
            throw new IllegalStateException("Please specify a serviceName"); 
            
        if (service == null) {
            service = (ExplorerViewService) InvokerProxy.getInstance().create(name, ExplorerViewService.class);
        }
        return service;
    }     
        
    public boolean isRootVisible() { return false; } 
    public String getIcon() { return null; }
      
    // </editor-fold>     
    
    // <editor-fold defaultstate="collapsed" desc=" TreeNodeModel helper and utilities ">
      
    private class TreeNodeModelImpl extends TreeNodeModel 
    {
        InboxController root = InboxController.this;
        
        public boolean isRootVisible() { 
            return root.isRootVisible(); 
        } 
        
        public String getIcon() { 
            String icon = root.getIcon();
            if (icon == null || icon.length() == 0) 
                return "Tree.closedIcon"; 
            else 
                return icon; 
        }
        
        public List<Map> getNodeList(Node node) {
            Map params = new HashMap(); 
            Object item = node.getItem(); 
            if (item instanceof Map) 
                params.putAll((Map) item); 
            else 
                params.put("item", node.getItem()); 
            
            params.put("root", (node.getParent() == null));
            params.put("caption", node.getCaption()); 
            List<Map> nodes = getService().getNodes(params); 
            if (nodes == null) nodes = new ArrayList();
            
            return nodes; 
        }
        
        public void initChildNodes(Node[] nodes) {
            if (nodes == null) return;
            
            for (Node node: nodes) {
                if (node.getIcon() != null) continue;
                
                String icon = node.getPropertyString("filetype");
                if (icon == null) icon = "default_folder"; 
                
                String path = "images/explorer/"+root.getContext();
                node.getProperties().put("iconpath", path); 
                
                String res = path+"/"+icon.toLowerCase()+".png";
                if (ControlSupport.isResourceExist(res)) { 
                    node.setIcon(res);  
                } else {
                    node.setIcon(getIcon());
                }
            }
        }
        
        public Object openFolder(Node node) {
            return openNodeImpl(node);
        }

        public Object openLeaf(Node node) {
            return openNodeImpl(node);
        }
        
        private Object openNodeImpl(Node node) {
            if (node == null) return null;

            System.out.println("openNode-> " + node.getItem());  
            updateView();
            return null; 
        }
    }
    
    // </editor-fold>        
    
    // <editor-fold defaultstate="collapsed" desc=" ListController override methods ">
    
    public boolean isAllowSearch() { return true; }     
    
    public final boolean isOpenButtonVisible() {
        Node node = getSelectedNode();
        if (node != null) {
            List invokers = node.getPropertyList("Invoker.openlist"); 
            if (invokers != null && !invokers.isEmpty()) return true; 
        }
                
        Object item = getSelectedEntity();
        if (item instanceof Map) {
            String filetype = helper.getFiletype((Map)item);
            if (filetype != null && filetype.length() > 0) return true; 
        }
        
        return false; 
    }
    
    protected Map createOpenerParams() {
        Map params = super.createOpenerParams(); 
        params.put("listModelHandler", null); 
        return params; 
    } 
    
    protected void beforeGetColumns(Map params) {
        Node node = getSelectedNode();
        Object item = (node == null? null: node.getItem()); 
        if (item instanceof Map) params.putAll((Map) item);         
    }
    
    protected void onbeforeFetchList(Map params) {
        Node node = getSelectedNode();
        Object item = (node == null? null: node.getItem());             
        if (item instanceof Map) params.putAll((Map) item); 
    } 
    
    public Object open() throws Exception {
        Node node = getSelectedNode();
        if (node == null) return null;
        
        Map item = (Map) getSelectedEntity(); 
        if (item == null) return null;
                
        /*
         *  if the selected item has a filetype, then use this as our primary handler 
         */
        String itemfiletype = helper.getString(item, "filetype"); 
        if (itemfiletype == null || itemfiletype.length() == 0)  
            itemfiletype = helper.getString(item, "_filetype");             
        if (itemfiletype != null && itemfiletype.length() > 0) {
            String invtype = itemfiletype.toLowerCase()+":open";
            Invoker invoker = actionsProvider.getInvoker(node, invtype);  
            if (invoker == null) { 
                System.out.println("No available file type handler ("+invtype+")");
                MsgBox.alert("No access privilege for this item. Please contact your administrator.");
                return null; 
            } 
            
            Map map = createOpenerParams(); 
            map.put("node", node.getItem()); 
            map.put("entity", item); 
            return actionsProvider.toOpener(invoker, map, node); 
        }
        
        /*
         *  return a dropdown menu if there are several actions that were hooked.
         */
        List<Invoker> list = node.getPropertyList("Invoker.openlist");
        if (list != null && !list.isEmpty()) {
            Map map = createOpenerParams();
            map.put("node", node.getItem());
            map.put("entity", item); 
            return actionsProvider.toOpener(list.get(0), map, node); 
        }
        
        /*
         *  use the default file type handler if there is...
         */
        String dfiletype = getDefaultFileType();        
        Invoker invoker = (Invoker) node.getProperty("Invoker.defaultOpen"); 
        if (invoker == null && dfiletype != null && dfiletype.length() > 0) {
            String invtype = dfiletype.toLowerCase()+":open";
            invoker = actionsProvider.getInvoker(node, invtype);  
            if (invoker == null) { 
                System.out.println("No available file type handler ("+invtype+")");
                MsgBox.alert("No access privilege for this item. Please contact your administrator.");
                return null; 
            } 
            node.setProperty("Invoker.defaultOpen", invoker);
        } 
        if (invoker != null) {
            Map map = createOpenerParams();
            map.put("node", node.getItem()); 
            map.put("entity", item);
            return actionsProvider.toOpener(invoker, map, node); 
        }
        
        //always return a null value for this line
        return null; 
    }    
    
    public Opener create() throws Exception {
        Node node = getSelectedNode(); 
        if (node == null) return null; 
        
        List<Invoker> list = node.getPropertyList("Invoker.createlist");
        if (list == null || list.isEmpty()) {
            System.out.println("No available file type handler"); 
            return null; 
        }
        
        PopupMenuOpener opener = new PopupMenuOpener();  
        opener.setExecuteOnSingleResult(true); 
        for (Invoker invoker: list) {
            Map map = createOpenerParams();
            map.put("node", node.getItem());
            
            Object fileTypeObject = invoker.getProperties().get("Node.fileTypeObject"); 
            if (fileTypeObject != null) map.put("filetype", fileTypeObject); 
            
            opener.add(actionsProvider.toOpener(invoker, map, node));
        }
        return opener;        
    }    

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" override / helper methods ">
    
    private Map wuprops;
    protected final Map getWorkunitProperties() {
        if (wuprops == null) { 
            try {
                PropertyResolver resolver = PropertyResolver.getInstance();
                wuprops = (Map) resolver.getProperty(controller, "workunit.workunit.properties"); 
            } catch(Throwable t) {;}
            
            if (wuprops == null) wuprops = new HashMap(); 
        } 
        return wuprops;
    }
        
    private void updateView() { 
        getQuery().clear(); 
        
        buildActions(); 
        Binding obinding = getBinding();
        if (obinding != null) obinding.refresh("formActions"); 
        
        if (isDynamicColumns())
            reloadAll(); 
        else 
            reload(); 
    } 
        
    private void buildActions() {
        formActions.clear();
        
        Node node = getSelectedNode();
        if (node == null) return;
                
        String filetype = helper.getFiletype(node); 
        List children = node.getPropertyList("children");
        if (children == null) children = new ArrayList(); 
        
        if (isAllowCreate()) { 
            List<Invoker> list = node.getPropertyList("Invoker.createlist"); 
            if (list == null) {
                list = new ArrayList();                 
                if (!children.isEmpty()) {
                    for (Object o: children) {
                        if (!(o instanceof Map)) continue;
                        
                        Map citem = (Map) o;
                        String cfiletype = helper.getFiletype(citem); 
                        if (cfiletype == null || cfiletype.length() == 0) continue;
                        
                        String invtype = cfiletype.toLowerCase() + ":create";
                        Invoker invoker = actionsProvider.getInvoker(node, invtype); 
                        if (invoker != null) {
                            invoker = invoker.clone(); 
                            invoker.getProperties().put("Node.fileTypeObject", citem);
                            
                            String scaption = helper.getString(citem, "caption");
                            if (scaption != null) invoker.setCaption(scaption);
                            
                            list.add(invoker);
                        }
                    }
                }                
                node.setProperty("Invoker.createlist", list);
            } 
            
            if (!list.isEmpty()) { 
                Action a = createAction("create", "New", "images/toolbars/create.png", "ctrl N", 'n', null, true); 
                formActions.add(a); 
            }
        }   
        
        if (isAllowOpen()) { 
            List<Invoker> list = node.getPropertyList("Invoker.openlist"); 
            if (list == null) {
                list = new ArrayList();
                if (!children.isEmpty()) {
                    for (Object o: children) {
                        if (!(o instanceof Map)) continue;
                        
                        Map citem = (Map) o;
                        String cfiletype = helper.getFiletype(citem); 
                        if (cfiletype == null || cfiletype.trim().length() == 0) continue;
                        
                        String invtype = cfiletype.toLowerCase() + ":open";
                        Invoker invoker = actionsProvider.getInvoker(node, invtype); 
                        if (invoker != null) {
                            invoker = invoker.clone(); 
                            invoker.getProperties().put("Node.fileTypeObject", citem);
                            
                            String scaption = helper.getString(citem, "caption");
                            if (scaption != null) invoker.setCaption(scaption);
                            
                            list.add(invoker);
                        }
                    }
                } 
                node.setProperty("Invoker.openlist", list); 
            } 
            
            Action a = createAction("open", "Open", "images/toolbars/open.png", "ctrl O", 'o', "#{selectedEntity != null && openButtonVisible==true}", true); 
            a.getProperties().put("depends", "selectedEntity"); 
            formActions.add(a); 
        }
        
        formActions.add(createAction("reload", "Refresh", "images/toolbars/refresh.png", "ctrl R", 'r', null, true)); 
        
        //load extended actions for the node
        List<Invoker> list = node.getPropertyList("Invoker.formActions"); 
        if (list == null && filetype != null) {
            String invtype = filetype.toLowerCase() + ":formActions";
            list = actionsProvider.getInvokers(node, invtype);
            node.setProperty("Invoker.formActions", list);
        } 
        if (list != null && !list.isEmpty()) {
            for (Invoker invoker: list) { 
                formActions.add(new ActionInvoker(invoker)); 
            } 
        } 
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
                System.out.println("[WARN] error lookup '"+invokerType+"' caused by " + t.getMessage());
                return new ArrayList(); 
            }   
        }
        
        Invoker getInvoker(Node node, String invokerType) {
            List<Invoker> list = getInvokers(node, invokerType);
            return (list.isEmpty()? null: list.get(0)); 
        }        
                
        Opener toOpener(Invoker invoker, Map params, Node node) {
            Object nodeTitle = invoker.getProperties().get("formTitle");
            if (nodeTitle != null) params.put("nodeTitle", nodeTitle); 
            
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
        InboxController root = InboxController.this;
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
            Node node = root.getSelectedNode(); 
            Map map = root.createOpenerParams();
            map.put("node", node.getItem());
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
    
    // <editor-fold defaultstate="collapsed" desc=" Helper (class) "> 
    
    private class Helper {
        
        private String getString(Map data, String name) {
            Object o = (data == null? null: data.get(name));
            return (o == null? null: o.toString()); 
        }        
        
        private String getFiletype(Map data) {
            if (data == null) return null;
            
            String filetype = getString(data, "filetype"); 
            if (filetype == null || filetype.length() == 0) 
                filetype = getString(data, "_filetype"); 
            
            return filetype; 
        }
        
        private String getFiletype(Node node) {
            if (node == null) return null;
            
            String filetype = node.getPropertyString("filetype");
            if (filetype == null || filetype.length() == 0) 
                filetype = node.getPropertyString("_filetype");
            
            return filetype; 
        }        
    }
    
    // </editor-fold>
}
