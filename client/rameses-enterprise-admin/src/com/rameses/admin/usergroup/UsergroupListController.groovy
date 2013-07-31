package com.rameses.admin.usergroup;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;

class UsergroupListController extends BasicListModel implements ExplorerNodeViewer {

    @Service('UsergroupAdminService') 
    def svc;

    Node node;
    String title;
    def selectedEntity;

    public void updateView() {
        title = node.caption;
        refresh(true);
    }

    public List getColumnList() {
        return svc.getColumns([:]); 
    } 

    public List fetchList(Map params) { 
        params.putAll( node.item );
        return svc.getList(params);
    } 

    public def showSecurityGroup() {
       return InvokerUtil.lookupOpener('securitygroup:open', [
            usergroupid: node.item.usergroupid
        ]); 
    } 

    public def open() {
        def stype = 'usergroup';
        if (node.item.type) stype += '-'+node.item.type;
        stype += ':open';
       
       return InvokerUtil.lookupOpener(stype, [
            entity: svc.open(selectedEntity)
        ]); 
    } 

    public def create() {
        def stype = 'usergroup';
        if (node.item.type) stype += '-'+node.item.type;

        stype += ':create';
        return InvokerUtil.lookupOpener(stype, [
            entity: [usergroupid: node.item.usergroupid] 
        ]); 
    }
}