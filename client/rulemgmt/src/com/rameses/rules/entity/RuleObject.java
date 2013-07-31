/*
 * NewClass.java
 *
 * Created on July 10, 2013, 9:14 AM
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
public class RuleObject {
    
    private String rulename;
    private int salience;
    private String description;
    private String agendagroup;
    private String ruleset;
    
    private List<Condition> conditions = new ArrayList();
    private List<Action> actions = new ArrayList();
    
    /** Creates a new instance of NewClass */
    public RuleObject() {
    }
    
    public void setMap(Map map) {
        rulename = (String)map.get("rulename");
        description = (String)map.get("description");
        agendagroup = (String)map.get("agendagroup");
        ruleset = (String)map.get("ruleset");
        salience =Integer.parseInt(map.get("salience")+"");
        List<Map> clist = (List)map.get("conditions");
        conditions.clear();
        for(Map m:clist) {
            Condition c = new Condition();
            c.setMap(m);
            conditions.add(c);
        }
        List<Map> alist = (List)map.get("actions");
        actions.clear();
        for(Map m: alist) {
            Action a = new Action();
            a.setMap(m);
            actions.add(a);
        }
    }
    
    public Map toMap() {
        Map map = new LinkedHashMap();
        map.put("rulename",rulename);
        map.put("description",description);
        map.put("agendagroup",agendagroup);
        map.put("ruleset",ruleset);
        map.put("salience",salience);
        List clist = new ArrayList();
        for(Condition c: conditions) {
            clist.add(c.toMap());
        }
        map.put("conditions",clist);
        List alist = new ArrayList();
        for(Action a: actions) {
            alist.add(a.toMap());
        }
        map.put("actions",alist);
        return map;
    }
    
}
