/*
 * Constraint.java
 *
 * Created on July 10, 2013, 9:19 AM
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
public class Constraint {
    
    private String name;
    private String datatype;
    private String operator;
    private boolean usevar;
    private String varname;
    private Object value;
    private Object valueText;
    
    /** Creates a new instance of Constraint */
    public Constraint() {
    }
    
    
    public void setMap(Map map) {
        
    }
    
    public Map toMap() {
        Map map = new LinkedHashMap();
        
        return map;
    }
}
