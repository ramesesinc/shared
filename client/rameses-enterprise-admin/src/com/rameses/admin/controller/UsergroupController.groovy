package com.rameses.admin.controller;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
        
public class UserGroupController extends CrudFileType {
        
    @Service("SecuritygroupAdminService")
    def sgSvc;

    String serviceName = "UsergroupService"
    
    public String getEntityName() {
        if(node.item.type) {
            return node.item.userclass;   
        }
        else {
            return "usergroup"
        }
    }

    public String getTitle() {
        return entity.usergroupid;
    }

    Map createEntity() {
        return [usergroupid: node.item.usergroupid ];
    } 

    def getSecurityGroups() {
        return sgSvc.getList([usergroupid:entity.usergroupid]);
    }
    
    def getLookupUser() {
        return InvokerUtil.lookupOpener("user:lookup", [
            onselect: { u->
                entity.user = [objid:u.objid, username:u.username, firstname:u.firstname, lastname:u.lastname];
                entity.jobtitle = u.jobtitle;
            }
        ]);
    }

    public String getPrefixId() {
        return "USRGRP";
    }

    def getLookupOrg() {
        if( !node.item.orgclass ) return null;
        return InvokerUtil.lookupOpener( "org:lookup", [
            "query.orgclass":node.item.orgclass,
            onselect: { o->
                entity.org = [objid:o.objid, orgclass:o.orgclass, name:o.name]
            }
        ]);
    }

}