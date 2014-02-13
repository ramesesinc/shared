package com.rameses.client.notification;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.rcp.framework.ClientContext;

class NotificationListController extends ListController 
{
    @Notification
    def notifier;
    
    @FormTitle
    def title = 'Notifications';
    
    String serviceName  = 'NotificationService';
    boolean allowSearch = false;
    boolean allowCreate = false;
    
    def categories = [];

    void init() {
        println 'notifier-> ' + notifier;
        
        categories << [name:'user', caption:'My Notifications', type:'user'];        
        Inv.lookup('notification-group').each{
            def groupname = it.properties.group;
            if (groupname) {
                def caption = (it.caption == null? groupname: it.caption);
                categories << [name: groupname.toUpperCase(), caption:caption, type:'group'];
            } 
        } 
    }
    
    public void beforeFetchList(Map params) {
        params.userid = ClientContext.currentContext.headers.USERID;
        if (selectedMenu) { 
            params.putAll(selectedMenu); 
            if (selectedMenu.type == 'user') {
                params.recipientid = params.userid;
            } else if (selectedMenu.type == 'group') { 
                params.groupid = selectedMenu.name; 
            } 
        }
    } 
    
    public def open() {
        def item = selectedEntity;
        if (item != null && item.filetype==null) {
            item.filetype = 'notification-item'; 
        } 
        item.type = selectedMenu?.type;
        return super.open();
    }
    
    def selectedMenu;    
    def menuHandler = [
        getDefaultIcon: {
            return 'Tree.closedIcon'; 
        },         
        getItems: { 
            return categories;
        }, 
        onselect: {o->
            selectedMenu = o;
            reload();
        } 
    ] as ListPaneModel;     
} 
