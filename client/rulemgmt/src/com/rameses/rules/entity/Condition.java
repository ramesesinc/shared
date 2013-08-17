/*
 * Condition.java
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
public class Condition {
    
    private String name;
    private String factclass;
    private String varname;
    private List<Constraint> constraints = new ArrayList();
    
    /** Creates a new instance of Condition */
    public Condition() {
    }
    
    public void setMap(Map map) {
        name = (String)map.get("name");
        factclass = (String)map.get("factclass");
        varname = (String)map.get("varname");
        List<Map> clist = (List)map.get("constraints");
        constraints.clear();
        for(Map m: clist) {
            Constraint c = new Constraint();
            c.setMap(m);
            constraints.add(c);
        }
    }
    
    public Map toMap() {
        Map map = new LinkedHashMap();
        map.put("factname", name);
        if(varname!=null) map.put("varname",varname);
        List list = new ArrayList();
        for(Constraint c: constraints) {
            list.add( c.toMap() );
        }
        map.put("constraints",list);
        return map;
        
        
    }
    
}
