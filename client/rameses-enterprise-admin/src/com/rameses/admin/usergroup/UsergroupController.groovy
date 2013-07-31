package com.rameses.admin.usergroup;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
        
class UserGroupController {
        
    @Service("SecuritygroupAdminService")
    def sgSvc;

    @Service("UsergroupAdminService")
    def adminSvc;

    def entity;
    def mode = "create";

    def title;

    void open() {
        title = entity.usergroupid;
        mode = "update";
    } 

    void create() {
        title = entity.usergroupid;
        mode = "create";
    } 

    def getSecurityGroups() {
        return sgSvc.getList([usergroupid:entity.usergroupid]);
    }

    def save() {
        if(mode=="create")
            adminSvc.create(entity);
        else
            adminSvc.update(entity);
        return "_close";    
    }

}