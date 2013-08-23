package com.rameses.osiris2.common;

import com.rameses.osiris2.client.InvokerProxy;
import com.rameses.osiris2.client.InvokerUtil;
import com.rameses.rcp.common.Action;
import com.rameses.rcp.common.Column;
import com.rameses.rcp.common.ListItem;
import com.rameses.rcp.common.MsgBox;
import com.rameses.rcp.common.Opener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ListController extends BasicListController implements PageListModelHandler 
{     
    private ListService service;
    private List formActions;
    private List navActions;
    private List<Map> contextMenuActions;
    private Map query = new HashMap(); 
    private String tag;
    private String formName;
    private int defaultRowSize = 20;
    
    public abstract String getServiceName();
    
    public String getEntityName() {
        throw new RuntimeException("Please provide an entityName");
    }
       
    // <editor-fold defaultstate="collapsed" desc=" Getter/Setter ">        
            
    public String getTag() { return tag; } 
    public void setTag(String tag) { this.tag = tag; }    
    
    public String getFormTarget() { return "popup"; }    
    
    public String getFormName() { 
        Map wuprops = getControllerProperties();
        Object oval = wuprops.get("formName");
        return (oval == null? this.formName: oval.toString()); 
    } 
    
    public void setFormName(String formName) { 
        this.formName = formName; 
    }
    
    public int getRows() {
        Map wuprops = getControllerProperties();
        Object oval = wuprops.get("rows");
        if (oval != null) {
            try {
                return Integer.parseInt(oval.toString()); 
            } catch(Throwable t) {;}
        }
        return defaultRowSize; 
    }
        
    public Map getQuery() { return query; }
            
    public Opener getQueryForm() 
    {
        if (isAllowSearch()) { 
            Opener o = new Opener();
            o.setOutcome("queryform");
            return o;
        } else {
            return null; 
        } 
    } 

    protected void beforeGetColumns(Map params){}
        
    public Column[] getColumns() { return null; }
        
    public List<Map> getColumnList() {
        Map params = new HashMap();
        String stag = getTag();
        if (stag != null) params.put("_tag", stag);
    
        beforeGetColumns(params); 
        return getService().getColumns(params); 
    }
    
    public List<Map> getContextMenu(Object item, String columnName) {
        if (contextMenuActions == null) {
            contextMenuActions = new ArrayList();
            List<Action> actions = lookupActions("contextMenu"); 
            while (!actions.isEmpty()) {
                Action a = (Action) actions.remove(0);                
                Map map = new HashMap();
                map.put("value", a.getCaption()); 
                map.put("action", a);
                contextMenuActions.add(map);
            }
        }
        return contextMenuActions;
    }

    public Object callContextMenu(Object item, Object menuItem) {
        if (menuItem instanceof Map) {
            Map map = (Map) menuItem;
            Object o = map.get("action"); 
            if (o instanceof Action || o instanceof Opener || o instanceof String) 
                return o;
        }
        return null; 
    } 
    
    // </editor-fold>    
 
    // <editor-fold defaultstate="collapsed" desc=" Options ">    
    
    public boolean isShowNavActions() { return true; } 
    public boolean isShowFormActions() { return true; } 
    
    public boolean isAllowClose() { 
        Map wuprops = getControllerProperties();
        Object oval = wuprops.get("allowClose");
        if (oval == null) return true; 
        
        return "true".equals(oval.toString()); 
    } 
    
    public boolean isAllowCreate() { 
        Map wuprops = getControllerProperties();
        Object oval = wuprops.get("allowCreate");
        if (oval == null) return true; 
        
        return "true".equals(oval.toString());     
    } 
    
    public boolean isAllowOpen() { 
        Map wuprops = getControllerProperties();
        Object oval = wuprops.get("allowOpen");
        if (oval == null) return true; 
        
        return "true".equals(oval.toString());     
    } 
    
    public boolean isAllowSearch() { 
        Map wuprops = getControllerProperties();
        Object oval = wuprops.get("allowSearch");
        if (oval == null) return true; 
        
        return "true".equals(oval.toString());         
    } 
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Action Methods ">        
    
    protected boolean onOpen(Map params) { return true; }
    protected boolean onCreate(Map params) { return true; }
    
    public Opener create() throws Exception 
    {
        Map params = createOpenerParams();
        if (!onCreate(params)) return null; 
        
        Opener opener = null; 
        try { 
            opener = InvokerUtil.lookupOpener(getEntityName()+":create", params);
        } catch(Throwable t) {
            System.out.println("[WARN] error lookup opener caused by " + t.getMessage());
            MsgBox.alert("No access privilege for this item. Please contact your administrator.");
        } 
        
        if (opener == null) return null;
        
        String target = opener.getTarget()+"";
        if (!target.matches("window|popup|process|_window|_popup|_process")) { 
            opener.setTarget(getFormTarget()); 
        } 
        return opener;
    }
    
    public Object open() throws Exception 
    {
        Map data = (Map) getSelectedEntity(); 
        if (data == null) return null;
        
        Map params = createOpenerParams();
        String filetype = (data.get("filetype")==null? null: data.get("filetype").toString());
        if (filetype != null && filetype.length() > 0) { 
            params.put("listModelHandler", new ListModelHandlerProxy()); 
        } else {
            filetype = getEntityName();
        }
        
        params.put("entity", data); 
        if (!onOpen(params)) return null; 
        
        Opener opener = null; 
        try {
            opener = InvokerUtil.lookupOpener(filetype+":open", params);
        } catch(Throwable t) {
            System.out.println("[WARN] error lookup opener caused by " + t.getMessage());
            MsgBox.alert("No access privilege for this item. Please contact your administrator.");            
        }
        
        if (opener == null) return null;
        
        String target = opener.getTarget()+"";
        if (!target.matches("window|popup|process|_window|_popup|_process")) { 
            opener.setTarget(getFormTarget()); 
        } 
        return opener;
    }    
    
    public Object close() {
        return "_close"; 
    }
    
    public void reset() 
    {
        query.clear(); 
        search(); 
    }
    
    public void search() {
        load(); 
    }    
    
    protected Map createOpenerParams() 
    {
        Map params = new HashMap();
        params.put("listModelHandler", this);
        return params;
    }
          
    // </editor-fold>
        
    // <editor-fold defaultstate="collapsed" desc=" Form and Navigation Actions ">  
    
    public List getNavActions() {
        if (navActions == null) 
        {
            if (!isShowNavActions()) return null;
            
            navActions = new ArrayList();
            navActions.add(createAction("moveFirstPage", "", "images/navbar/first.png", null, '\u0000', null, true)); 
            navActions.add(createAction("moveBackPage",  "", "images/navbar/previous.png", null, '\u0000', null, true)); 
            navActions.add(createAction("moveNextPage",  "", "images/navbar/next.png", null, '\u0000', null, true)); 
            navActions.add(createAction("moveLastPage",  "", "images/navbar/last.png", null, '\u0000', null, true)); 
        } 
        return navActions; 
    }
    
    public List getFormActions() {
        if (formActions == null) {
            if (!isShowFormActions()) return null;
            
            formActions = new ArrayList();
            if (isAllowClose()) 
                formActions.add(createAction("close", "Close", "images/toolbars/cancel.png", "ctrl C", 'c', null, true)); 
            
            if (isAllowCreate()) 
                formActions.add(createAction("create", "New", "images/toolbars/create.png", "ctrl N", 'n', null, true));       
            
            if (isAllowOpen()) { 
                Action a = createAction("open", "Open", "images/toolbars/open.png", "ctrl O", 'o', "#{selectedEntity != null}", true);
                a.getProperties().put("depends", "selectedEntity");
                formActions.add(a); 
            }
            
            formActions.add(createAction("reload", "Refresh", "images/toolbars/refresh.png", "ctrl R", 'r', null, true)); 
            
            List extActions = lookupActions("formActions");
            formActions.addAll(extActions);
            extActions.clear(); 
        } 
        return formActions; 
    }
    
    protected Action createAction(String name, String caption, String icon, String shortcut, char mnemonic, String visibleWhen, boolean immediate) 
    {
        Action a = new Action(name, caption, icon, mnemonic);
        if (visibleWhen != null) a.setVisibleWhen(visibleWhen); 
        if (shortcut != null) a.getProperties().put("shortcut", shortcut);    
        
        a.setImmediate( immediate );
        a.setShowCaption(true); 
        return a;
    } 
        
    // </editor-fold>
    
    protected void onbeforeFetchList(Map params) {}
    protected void onafterFetchList(List list) {}
    
    public List fetchList(Map m) {
        String stag = getTag();
        if (stag != null) m.put("_tag", stag);
        
        Map qrymap = getQuery();
        if (qrymap != null) m.putAll(qrymap); 
               
        List list = getService().getList(m); 
        return list; 
    }
    
    protected ListService getService(){
        String name = getServiceName();
        if (name == null || name.trim().length() == 0)
            throw new RuntimeException("No service name specified"); 
            
        if (service == null) {
            service = (ListService) InvokerProxy.getInstance().create(name, ListService.class);
        }
        return service;
    } 

    /*
     *  ListControllerHandler implementation
     */ 
    // <editor-fold defaultstate="collapsed" desc=" ListControllerHandler implementation "> 
    
    public void addItem(Object data) 
    {
        if (data == null) return;
        
        int idx = -1; 
        try { 
            idx = getListItems().indexOf(getSelectedItem()); 
        } catch(Exception ign) {;} 
        
        List dataList = getDataList();
        if (idx >= 0 && idx < dataList.size()) { 
            dataList.add(idx, data); 
        } 
        else {
            dataList.add(data); 
            idx = dataList.size()-1;
        }
        
        refresh(); 
        setSelectedItem(idx); 
    }
    
    public void updateItem(Object data) 
    {
        ListItem item = getSelectedItem(); 
        if (item == null) return;
        if (data != null) 
        {
            List dataList = getDataList();
            if (item.getIndex() >= 0 && item.getIndex() < dataList.size()) 
            {
                dataList.set(item.getIndex(), data); 
                item.loadItem(data);
                refreshSelectedItem(); 
            } 
        }
    }

    public void removeItem(Object data) 
    {
        if (getSelectedEntity() == null) return;
        
        int idx = getListItems().indexOf(getSelectedItem()); 
        if (idx >= 0) 
        {
            getDataList().remove(idx); 
            refresh();
        }
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" ListModelHandlerProxy (class) "> 
    
    private class ListModelHandlerProxy implements ListModelHandler {
        
        ListController root = ListController.this; 
        
        public Object getSelectedEntity() { 
            return root.getSelectedEntity(); 
        }

        public void addItem(Object data) { 
            root.addItem(data); 
        }

        public void updateItem(Object data) {
            root.updateItem(data); 
        }

        public void removeItem(Object data) {
            root.removeItem(data); 
        } 
    }
    
    // </editor-fold>    
    
}
