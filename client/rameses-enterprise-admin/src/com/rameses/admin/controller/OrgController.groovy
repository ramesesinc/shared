package com.rameses.admin.controller;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
        
public class OrgController extends CRUDController {
        
    def node;
    def nodeTitle;
    def fileType;

    public String getServiceName() {
        return "OrgAdminService";
    }
    
    public String getEntityName() {
        return node.item.orgclass;
    }

    public String getTitle() {
        return (nodeTitle? nodeTitle: node.item.orgclass);
    }

    public String getPrefixId() {
        return "ORG";
    }

    Map createEntity() {
        def e = [:];
        e.orgclass  = node.item.orgclass;
        return e;
    } 

}