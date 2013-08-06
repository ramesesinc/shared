package com.rameses.admin.controller;

import com.rameses.osiris2.common.*;
import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;

public class UsergroupListController extends ExplorerListViewController {

    String serviceName = "UsergroupAdminService";

    public String getEntityName() {
        if(node.item.type) {
            return node.item.type;   
        }
        else {
            return "usergroup"
        }
    }

    public String getTitle() {
        return node.caption;
    }

    def showSecurityGroup() {
        return InvokerUtil.lookupOpener( "securitygroup:open", [
            usergroupid: node.item.usergroupid
        ]);
    }

}