package com.rameses.admin.org;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
        
class OrgController {
        
    @Service("OrgAdminService")
    def sgSvc;

    def entity;
    def mode = "create";

    def title;

    void open() {
        title = entity.orgtype;
        mode = "update";
    } 

    void create() {
        title = entity.orgtype;
        mode = "create";
    } 

    def save() {
        if(mode=="create")
            adminSvc.create(entity);
        else
            adminSvc.update(entity);
        return "_close";    
    }

}