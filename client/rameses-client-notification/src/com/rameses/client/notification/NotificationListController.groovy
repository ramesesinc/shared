package com.rameses.client.notification;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.rcp.framework.ClientContext;

class NotificationListController extends ExplorerViewController 
{
    @Notification
    def notifier;
    
    String serviceName  = 'NotificationService';
    String entityName   = '';
    boolean allowSearch = false;
    
    private List groups = [];
    
    void init() {
        println 'notifier-> ' + notifier;
        
        Inv.lookup('notification-group').each{
            def groupname = it.properties.group;
            if (groupname) {
                def caption = (it.caption == null? groupname: it.caption);
                groups << [name: groupname.toUpperCase(), caption:caption, type:'group'];
            } 
        } 
    }
    
    public void beforeFetchList(Map params) {
        params.userid = ClientContext.currentContext.headers.USERID;
        //params.groupnames = groupnames;
    } 
    
    protected List getNodes(Map params) {
        if (params.root == true) return [];
        
        def nodes = [
            [name:'user', caption:'My Notifications', type:'user'] 
        ];
        nodes.addAll(groups);
        return nodes; 
    }
} 
