/*
 * LOVLookupController.java
 *
 * Created on June 25, 2013, 7:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.rameses.osiris2.common;

import com.rameses.common.MethodResolver;
import com.rameses.rcp.annotations.FormTitle;
import com.rameses.rcp.common.Column;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Elmo
 */
public class LOVLookupController extends BasicListModel {
    
    private String key;
    private Object selectedEntity;
    private Object onselect;
    private List lovList;
    
    /** Creates a new instance of LOVLookupController */
    public LOVLookupController() {
        
    }
    
    @FormTitle
    public String getTitle() {
        return "List of Values (" + key + ")";
    }
    
    public Column[] getColumns() {
        return new Column[]{
            new Column("title","Title")
        };
    }
    
    private Map createMap(String key, String title) {
        Map map = new HashMap();
        map.put("key", key);
        map.put("title", title);
        return map;
    }
    
    public List fetchList(Map params) {
        if(key==null) return new ArrayList();
        if(lovList==null) {
            lovList = new ArrayList();
            List glist = (List) LOV.get(key);
            for(Object g: glist) {
                Map m = (Map)g;
                String key = (String)m.get("key");
                String title = key;
                if(!key.equals(m.get("value"))) {
                    title += " ("+m.get("value")+")";
                }
                lovList.add( createMap(key,title) );
            }
        }
        return lovList;
    }
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public Object getSelectedEntity() {
        return selectedEntity;
    }
    
    public void setSelectedEntity(Object selectedEntity) {
        this.selectedEntity = selectedEntity;
    }
    
    public String doSelect() throws Exception {
        Object value = getSelectedValue();
        if(onselect!=null && value!=null) 
        {
            Object aresult = null;
            if( value instanceof Map) {
                aresult = ((Map)value).get("key");
            }
            else if(value instanceof List) {
                List list = new ArrayList();
                for( Object k : (List)value) {
                    list.add  (((Map)k).get("key"));
                }
                aresult = list;
            }
            Object result = MethodResolver.getInstance().invoke(onselect,"call", new Object[]{aresult} );
            if(result!=null && (result instanceof String))
                return result.toString();
        }
        return "_close";
    }
    
    public String doCancel() {
        return "_close";
    }

    public Object getOnselect() {
        return onselect;
    }

    public void setOnselect(Object onselect) {
        this.onselect = onselect;
    }
     
    
}
