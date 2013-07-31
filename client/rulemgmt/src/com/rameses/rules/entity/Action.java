/*
 * Action.java
 *
 * Created on July 10, 2013, 9:16 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.rameses.rules.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Elmo
 */
public class Action {
    
    private String name;
    private List<ActionParam> params = new ArrayList();
    
    /** Creates a new instance of Action */
    public Action() {
    }
    
    public void setMap(Map map) {
        name = (String)map.get( "name" );
        List<Map> plist = (List)map.get("params");
        params.clear();
        for(Map m: plist) {
            ActionParam ap = new ActionParam();
            ap.setMap(m);
            params.add(ap);
        }
    }
    
    public Map toMap() {
        Map map = new LinkedHashMap();
        map.put( "name", name);
        List list = new ArrayList();
        for(ActionParam ap: params) {
            list.add(ap.toMap());
        }
        map.put("params", list);
        return map;
    }
    
}
