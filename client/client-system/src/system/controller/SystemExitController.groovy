package system.controller;

import com.rameses.platform.interfaces.*;
import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.rcp.framework.ClientContext;
import com.rameses.osiris2.client.*;
import com.rameses.util.*;

public class SystemExitController 
{
    @Script("User")
    def user;
    
    public void logoff() {
        logoffUser();
        def ctx = ClientContext.currentContext;
        ctx.platform.logoff();
        ctx.headers.clear();
    }

    public void restart() {
        logoffUser();
        def ctx = ClientContext.currentContext;
        ctx.platform.logoff();
        ctx.headers.clear();
    }

    public def shutdown() {
        logoffUser();
        def ctx = ClientContext.currentContext;
        ctx.platform.shutdown();
        ctx.headers.clear();
    }
    
    
    def service = null;
    
    private void logoffUser() {
        if (user.sessionId == null) return; 
        
        service = InvokerProxy.instance.create("LogoutService"); 
        def handler = new LogoutHandler(service: service, user: user);
        OsirisContext.mainWindowListener.add(handler); 
    }
}

public class LogoutHandler implements MainWindowListener 
{
    def service;
    def user;

    public Object onEvent(String eventName, Object evt) {
        return null;
    }

    public boolean onClose() 
    {
        if (MsgBox.confirm("Are you sure you want to logout this application?")) {
            if (service) service.logout(user.env);

            return true;
        }
        return false;
    }
}
