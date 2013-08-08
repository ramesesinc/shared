package com.rameses.osiris2.common;
import com.rameses.common.MethodResolver;
import com.rameses.osiris2.client.InvokerFilter;
import com.rameses.osiris2.client.InvokerProxy;
import com.rameses.osiris2.client.InvokerUtil;
import com.rameses.rcp.annotations.Binding;
import com.rameses.rcp.annotations.ChangeLog;
import com.rameses.rcp.annotations.Invoker;
import com.rameses.rcp.common.Action;
import com.rameses.rcp.common.MsgBox;
import com.rameses.rcp.common.StyleRule;
import com.rameses.rcp.framework.ChangeLogHandler;
import com.rameses.util.MapEditor;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CRUDController 
{
    public final static String MODE_CREATE = "create";
    public final static String MODE_EDIT = "edit";
    public final static String MODE_READ = "read";
                
    @Invoker
    protected com.rameses.osiris2.Invoker invoker;
    
    @Binding
    protected com.rameses.rcp.framework.Binding binding;

    @ChangeLog
    private com.rameses.rcp.framework.ChangeLog changeLog; 
    
    private ListModelHandler listModelHandler;
    private List formActions; 
    private List navActions;   
    private List extActions;   

    private CRUDServiceProxy serviceProxy; 
    private MapEditor mapEditor;  
    private Map entity = new HashMap();    
    private String mode = MODE_READ;  
    
    protected String styleSource;
        
    public String getServiceName() {
        throw new NullPointerException("Please specify a serviceName"); 
    } 
    
    public String getEntityName() {
        throw new NullPointerException("Please specify an entityName"); 
    }
    
            
    // <editor-fold defaultstate="collapsed" desc=" Getter/Setter "> 
        
    public String getTitle() { 
        String text = (invoker == null? "Title": invoker.getCaption()); 
        if (MODE_CREATE.equals(getMode())) 
            return text + " (New)";
        else if (MODE_EDIT.equals(getMode())) 
            return text + " (Edit)";
        else
            return text; 
    }
    
    public final String getMode() { return mode; }
            
    public Map getEntity() { return entity; }
    
    public void setEntity(Map entity) {
        this.entity = entity;        
        if (entity != null && entity.get("state") == null) {
            entity.put("state", "DRAFT"); 
        } 
    }
    
    public Object getService() 
    {
        String name = getServiceName();
        if (name == null || name.trim().length() == 0)
            throw new NullPointerException("Please specify a serviceName"); 
            
        return InvokerProxy.getInstance().create(name); 
    } 
    
    public final boolean isDirty() { 
        return changeLog.hasChanges(); 
    }
    
    public ListModelHandler getListModel() { return this.listModelHandler; } 
    public void setListModel(ListModelHandler listModelHandler) {
        this.listModelHandler = listModelHandler;
    }
    
    protected Map getChanges() 
    {
        final Map map = new HashMap();
        if (changeLog.hasChanges()) 
        {
            ChangeLogHandler handler = new ChangeLogHandler() 
            {
                private String fldname;

                public void clear(com.rameses.rcp.framework.ChangeLog log) {}
                public void end() {}
                public void endEntity() {}
                public void endField() {}
                public void showChange(Object oldValue, Object newValue) {
                    Map diff = new HashMap();
                    diff.put("oldvalue", oldValue);
                    diff.put("newvalue", newValue);
                    map.put(fldname, diff); 
                }
                public void showHistory(Date timeChanged, Object oldValue, Object newValue) {}
                public void start() {}
                public void startEntity(Object entity) {}
                public void startField(String fieldName) {
                    this.fldname = fieldName; 
                }
            };
            changeLog.scan(handler); 
            //System.out.println("Difference -> " + changeLog.getDifference());
        } 
        return map; 
    }     
    
    // </editor-fold>    
        
    // <editor-fold defaultstate="collapsed" desc=" Options "> 
          
    public boolean isAllowCreate() { return true; } 
    public boolean isAllowOpen() { return true; } 
    public boolean isAllowEdit() { return true; } 
    public boolean isShowFormActions() { return true; } 
    
    protected boolean isShowConfirmOnSave() { return false; }  
    protected boolean isShowNavigationBar() { return true; }
    
    public String getCreateFocusComponent() {  return "entity.code"; }    
    public String getEditFocusComponent() { return "entity.description"; }        
    
    
    /*
     *  use as a prefix value in generating the UID key 
     */
    protected String getPrefixId() { return "MF"; }
    
    public List getStyleRules() {
        List list = new ArrayList();
        list.add(new StyleRule("entity.*", "#{mode=='read'}").add("enabled", false)); 
        list.add(new StyleRule("entity.*", "#{mode!='read'}").add("enabled", true));         
        return list; 
    }    
    
    // </editor-fold>
        
    // <editor-fold defaultstate="collapsed" desc=" Form / Navigation Actions "> 
    
    public List getFormActions() {
        if (formActions == null) 
        {
            if (!isShowFormActions()) return null;
            
            formActions = new ArrayList();
            formActions.add(createAction("close", "Close", "images/toolbars/cancel.png", "ctrl C", 'c', "#{mode=='read'}", true));  
            if (isAllowCreate())
                formActions.add(createAction("init", "New", "images/toolbars/create.png", "ctrl N", 'n', "#{mode=='read'}", true));        
            
            if (isAllowEdit())
            {
                formActions.add(createAction("edit", "Edit", "images/toolbars/edit.png", "ctrl E", 'e',  "#{mode=='read' && entity.state=='DRAFT'}", true)); 
                formActions.add(createAction("delete", "Delete", "images/toolbars/trash.png", null, 'd', "#{mode=='read' && entity.state=='DRAFT'}", true)); 
                formActions.add(createAction("approve", "Approve", "images/toolbars/approve.png", null, 'v', "#{mode=='read' && entity.state=='DRAFT'}", true)); 
            }
            
            if (isAllowCreate() || isAllowEdit())
            {
                formActions.add(createAction("cancel", "Cancel", "images/toolbars/cancel.png", "ctrl C", 'c', "#{mode!='read'}", true)); 
                formActions.add(createAction("save", "Save", "images/toolbars/save.png", "ctrl S", 's', "#{mode!='read'}", false)); 
                formActions.add(createAction("undo", "Undo", "images/toolbars/undo.png", "ctrl Z", 'u', "#{mode=='edit'}", true)); 
            } 
            
            List<Action> xactions = lookupActions("formActions");
            while (!xactions.isEmpty()) {
                formActions.add(xactions.remove(0)); 
            }
        } 
        return formActions;         
    }
    
    public List getNavActions() 
    {
        if (navActions == null) 
        {
            navActions = new ArrayList();
            if (isShowNavigationBar())
            {
                navActions.add(createAction("moveBackRecord", "Move to previous record", "images/toolbars/arrow_up.png", null, '\u0000', "#{mode=='read'}", true));  
                navActions.add(createAction("moveNextRecord", "Move to next record", "images/toolbars/arrow_down.png", null, '\u0000', "#{mode=='read'}", true));  
            }
        }
        return navActions;
    } 
    
    public List getExtActions() 
    {
        if (extActions == null) 
            extActions = lookupActions("extActions");
        
        return extActions;
    }     
        
    protected final List<Action> lookupActions(String type) { 
        List<Action> actions = InvokerUtil.lookupActions(type, new InvokerFilter() {
            public boolean accept(com.rameses.osiris2.Invoker o) { 
                return o.getWorkunitid().equals(invoker.getWorkunitid()); 
            }
        }); 
        
        for (int i=0; i<actions.size(); i++) {
            Action newAction = actions.get(i).clone();
            actions.set(i, newAction);
        }
        return actions;  
    }    
    
    private Action createAction(String name, String caption, String icon, String shortcut, char mnemonic, String visibleWhen, boolean immediate) 
    {
        Action a = new Action(name, caption, icon, mnemonic);
        if( shortcut != null ) a.getProperties().put("shortcut", shortcut);    
        if (visibleWhen != null) a.setVisibleWhen(visibleWhen); 
        
        a.setImmediate( immediate );
        return a;
    }      
    
    // </editor-fold> 
        
    protected Map createEntity() {
        return new LinkedHashMap();
    }
    
    public final void init() { 
        if (!isAllowCreate())
            throw new IllegalStateException("PERMISSION DENIED! Invoking this method is not authorized."); 
                        
        Map data = createEntity();  
        if (data.get("objid") == null) 
            data.put("objid", getPrefixId() + new java.rmi.server.UID());
        if (data.get("state") == null) 
            data.put("state", "DRAFT"); 
        
        setEntity(data);
        mode = MODE_CREATE; 
        focusComponent(getCreateFocusComponent()); 
    }
    
    //this is to support elmo's crud implementation
    public final void create() {
        init();
    }
    
    public Object cancel() {
        if (MODE_EDIT.equals(this.mode)) {
            if (changeLog.hasChanges()) {
                if (!MsgBox.confirm("Changes will be discarded. Continue?")) 
                    return null; 

                changeLog.undoAll(); 
                changeLog.clear();
            }
            
            this.mode = MODE_READ; 
            return null; 
        }
        else {
            return close(); 
        }
    }
    
    public Object close() {
        this.mode = MODE_READ; 
        this.changeLog.clear(); 
        return "_close"; 
    }
    
    protected void onbeforeSave(){}
    protected void onafterSave(){}
    
    public void save(){
        try {
            if (isShowConfirmOnSave()) {
                if (!MsgBox.confirm("You are about to save this record. Continue?")) return;
            }
            
            onbeforeSave();

            if (MODE_CREATE.equals(this.mode)) {
                Map data = getServiceProxy().create(getEntity()); 
                if (data != null) setEntity(data); 
            }             
            else if (MODE_EDIT.equals(this.mode)) {
                Map data = getServiceProxy().update(getEntity()); 
                if (data != null) setEntity(data);  
            } 

            String oldmode = this.mode;
            this.mode = MODE_READ; 
            this.changeLog.clear();
            
            onafterSave(); 
            
            ListModelHandler lm = getListModel();
            if (lm != null) {
                if (MODE_EDIT.equals(oldmode)) 
                    lm.updateItem(getEntity()); 
                else if (MODE_CREATE.equals(oldmode)) 
                    lm.addItem(getEntity());                 
            }
        }
        catch(Exception ex) {
            MsgBox.err(ex); 
        } 
    }
    
    public void open() {
        if (!isAllowOpen())
            throw new IllegalStateException("PERMISSION DENIED! Invoking this method is not authorized."); 
        
        Map data = getServiceProxy().open(getEntity());
        if (data == null) throw new NullPointerException("Record does not exist");
        
        if (data.get("state") == null) data.put("state", "DRAFT"); 
        
        setEntity(data); 
        mode = MODE_READ;                 
    }
    
    public void edit() {
        if (!isAllowEdit())
            throw new IllegalStateException("PERMISSION DENIED! Invoking this method is not authorized."); 
        
        this.mode = MODE_EDIT; 
        focusComponent(getEditFocusComponent()); 
    }
    
    public void undo() {
        changeLog.undo(); 
    }    
    
    public void approve() {
        if (MsgBox.confirm("You are about to approve this document. Continue?")){
            Map data = getEntity();
            getServiceProxy().approve(data); 
            data.put("state", "APPROVED");
        } 
    }
    
    public Object delete(){
        if (MsgBox.confirm("You are about to delete this document. Continue?")){
            Map data = getEntity();
            getServiceProxy().removeEntity(data); 
            
            ListModelHandler lm = getListModel();
            if (lm != null) lm.removeItem(data); 
                
            return close();
        }
        else {
            return null; 
        }
    }    
    
    public void moveBackRecord() {
        ListModelHandler lm = getListModel();
        if (lm != null){
            lm.moveBackRecord(); 
            
            Object data = lm.getSelectedEntity(); 
            if (data == null) return;
            
            setEntity((Map) data); 
            open(); 
            
            if (binding != null) binding.refresh(); 
        }
    }
    
    public void moveNextRecord() {
        ListModelHandler lm = getListModel();
        if (lm != null) {
            lm.moveNextRecord();
            
            Object data = lm.getSelectedEntity(); 
            if (data == null) return;
            
            setEntity((Map) data); 
            open();          
            
            if (binding != null) binding.refresh(); 
        }
    }    
      
    private void focusComponent(String ctrlname){
        if ( binding != null ) binding.focus(ctrlname); 
    }    
    
    // <editor-fold defaultstate="collapsed" desc=" CRUDServiceProxy "> 
    
    private CRUDServiceProxy getServiceProxy() {
        if (serviceProxy == null) 
            serviceProxy = new CRUDServiceProxy(); 
        if (serviceProxy.serviceObj == null) 
            serviceProxy.serviceObj = getService();

        return serviceProxy;
    }
    
    private class CRUDServiceProxy { 
        
        Object serviceObj;   
        MethodResolver resolver = MethodResolver.getInstance();
        
        Map create(Map data) { 
            return (Map) invoke("create", data); 
        } 

        Map open(Map data) {
            return (Map) invoke("open", data); 
        }

        Map update(Map data) {
            return (Map) invoke("update", data);  
        }

        void removeEntity(Map data) {
            invoke("removeEntity", data);  
        }

        Map approve(Map data) {
            return (Map) invoke("approve", data);  
        }     
        
        Object invoke(String methodName, Object data) {
            try { 
                if (serviceObj == null) 
                    throw new NullPointerException("No available service object");
                
                return resolver.invoke(serviceObj, methodName, new Object[]{data}); 
            } catch(RuntimeException re) {
                throw re;
            } catch(Exception re) { 
                throw new RuntimeException(re.getMessage(), re); 
            }
        }
    }
    
    // </editor-fold>
}
