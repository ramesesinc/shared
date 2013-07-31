/*
 * LookupController.java
 *
 * Created on May 3, 2013, 9:48 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.rameses.osiris2.common;

import com.rameses.osiris2.client.InvokerFilter;
import com.rameses.osiris2.client.InvokerProxy;
import com.rameses.osiris2.client.InvokerUtil;
import com.rameses.rcp.annotations.Binding;
import com.rameses.rcp.annotations.Invoker;
import com.rameses.rcp.common.AbstractListDataProvider;
import com.rameses.rcp.common.Action;
import com.rameses.rcp.common.Column;
import com.rameses.rcp.common.LookupModel;
import com.rameses.rcp.common.LookupSelector;
import com.rameses.rcp.common.MsgBox;
import com.rameses.rcp.common.Opener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wflores
 */
public abstract class LookupController extends LookupModel 
{
    @Invoker
    protected com.rameses.osiris2.Invoker invoker;
    
    @Binding
    private com.rameses.rcp.framework.Binding binding;
    
    private Object selectedEntity;
    private Object onselect;
    private Object onempty;
    private String tag;
    
    public String getTag() { return tag; } 
    public void setTag(String tag) { this.tag = tag; }
    
    public String getServiceName() { return null; }
    
    public Column[] getColumns() { return null; }

    public List<Map> getColumnList() {
        String name = getServiceName();
        if (name != null && name.length() > 0) {
            Map params = new HashMap();
            String stag = getTag();
            if (stag != null) params.put("_tag", stag);            
            
            return getService().getColumns(params); 
        } else {
            return null; 
        } 
    }
    
    public List fetchList(Map params) { 
        String name = getServiceName();
        if (name != null && name.length() > 0) {
            String stag = getTag();
            if (stag != null) params.put("_tag", stag);
            
            return getService().getList(params); 
        } else {
            return new ArrayList(); 
        }
    }  
    
    public LookupController() {
        setSelector(new LookupSelectorImpl()); 
    }
    
    public void init() {}
    
    // <editor-fold defaultstate="collapsed" desc="  Getters/Setters  ">

    public Object getOnselect() { return onselect; }    
    public void setOnselect(Object onselect) { this.onselect = onselect; }
    
    public Object getOnempty() { return onempty; }    
    public void setOnempty(Object onempty) { this.onempty = onempty; }
    
    public Object getSelectedEntity() { return selectedEntity; } 
    public void setSelectedEntity(Object selectedEntity) { 
        this.selectedEntity = selectedEntity; 
    }
    
    public final AbstractListDataProvider getListHandler() { return this; } 
    
    public com.rameses.rcp.framework.Binding getBinding() { return binding; }  
    
    public Opener getQueryForm() { return null; }        
    
    public List getFormActions() { 
        return new ArrayList(); 
    } 

    public int getRows() { return 10; }
    
    public List getNavActions() { 
        return new ArrayList(); 
    }
       
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Options ">  
    
    protected final List<Action> lookupActions(String type)
    {
        List<Action> actions = InvokerUtil.lookupActions(type, new InvokerFilter() {
            public boolean accept(com.rameses.osiris2.Invoker o) { 
                return o.getWorkunitid().equals(invoker.getWorkunitid()); 
            }
        }); 
        
        for (int i=0; i<actions.size(); i++) 
        {
            Action newAction = actions.get(i).clone();
            actions.set(i, newAction);
        }
        return actions; 
    }
        
    // </editor-fold>    
    
    public void search() {
        load(); 
    }
    
    public Object open() throws Exception { return null; }    
    
    public final Object onOpenItem(Object item, String columnName) 
    {
        try
        {
            setSelectedEntity(item);
            if (item == null) return null;
            
            return open(); 
        }
        catch(Exception ex) 
        {
            MsgBox.err(ex); 
            return null; 
        } 
    }  
    
    public Object doSelect() 
    {
        onselect(getSelectedEntity());        
        return super.select(); 
    }
    
    public Object doCancel() {
        return super.cancel(); 
    }    
    
    protected void onselect(Object item) {} 
    
    // <editor-fold defaultstate="collapsed" desc=" Helper methods for the ListService ">
    
    private ListService oListService;
    
    protected ListService getService()
    {
        if (oListService == null) {
            String name = getServiceName();
            if (name == null || name.length() == 0)
                throw new RuntimeException("No service name specified"); 
            
            oListService = (ListService) InvokerProxy.getInstance().create(name, ListService.class);
        }
        return oListService;
    }     
    
    // </editor-fold>
        
    // <editor-fold defaultstate="collapsed" desc=" LookupSelectorImpl (class) ">  
    
    private class LookupSelectorImpl implements LookupSelector
    {
        public void select(Object o) {
            invokeCallbackHandler(getOnselect(), o);
        }

        public void cancelSelection() {
        }
        
        private void invokeCallbackHandler(Object callback, Object item)
        {
            if (callback == null) return;

            Method method = null;         
            Class clazz = callback.getClass();
            try { method = clazz.getMethod("call", new Class[]{Object.class}); }catch(Exception ign){;} 

            try {
                if (method != null) 
                    method.invoke(callback, new Object[]{item}); 
            } catch(RuntimeException re) {
                throw re;
            } catch(Exception ex) {
                throw new IllegalStateException(ex.getMessage(), ex); 
            }
        }         
    }
    
    // </editor-fold>
}
