/*
 * ActionParam.java
 *
 * Created on July 10, 2013, 9:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.rameses.rules.entity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Elmo
 */
public class ActionParam {
    
    private String name;
    
    /** Creates a new instance of ActionParam */
    public ActionParam() {
    }
    
    public void setMap(Map map) {
        name = (String)map.get("name");
    }
    
    public Map toMap() {
        Map map = new LinkedHashMap();
        map.put("name",name);
        return map;
    }
}
