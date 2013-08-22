/*
 * BasicListModel.java
 *
 * Created on August 20, 2013, 5:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.rameses.osiris2.common;

import com.rameses.osiris2.client.InvokerUtil;
import com.rameses.rcp.common.DataListModel;
import com.rameses.rcp.common.Opener;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wflores
 */
public class BasicListModel extends DataListModel {
    
    public BasicListModel() {
    } 

    protected Object lookupOpener(Object item) {
        if (item == null) return null;

        Map map = (Map) item; 
        String filetype = getString(map, "_filetype"); 
        if (filetype == null || filetype.length() == 0) return null; 
        
        Map params = new HashMap(); 
        params.put("entity", item); 
        String invtype = filetype.toLowerCase()+":open"; 
        try { 
            Opener opener = InvokerUtil.lookupOpener(invtype, params); 
            if (opener != null) { 
                String target = opener.getTarget(); 
                if (target == null) opener.setTarget("popup"); 

            } 
            return opener;
        } 
        catch(Throwable t) {
            System.out.println("[WARN] error caused by " + t.getMessage());
            return null;
        }
    }
        
    private String getString(Map map, String name) {
        Object ov = (map == null? null: map.get(name));
        return (ov == null? null: ov.toString()); 
    }    
}
