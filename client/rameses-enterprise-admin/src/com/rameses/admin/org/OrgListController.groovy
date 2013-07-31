package com.rameses.admin.org;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;

class OrgListController extends BasicListModel implements ExplorerNodeViewer {

    @Service('OrgAdminService') 
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

    public def open() {
        def stype = 'org';
        if (node.item.type) stype += '-'+node.item.type;
        stype += ':open';
       
       return InvokerUtil.lookupOpener(stype, [
            entity: svc.open(selectedEntity)
        ]); 
    } 

    public def create() {
        def stype = 'org';
        if (node.item.type) stype += '-'+node.item.type;

        stype += ':create';
        return InvokerUtil.lookupOpener(stype, [
            entity: [usergroupid: node.item.usergroupid] 
        ]); 
    }
}