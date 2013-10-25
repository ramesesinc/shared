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
        ClientContext.currentContext.platform.logoff();
    }

    public void restart() {
        logoffUser();
        ClientContext.currentContext.platform.logoff();
    }

    public def shutdown() {
        logoffUser();
        ClientContext.currentContext.platform.shutdown();
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
