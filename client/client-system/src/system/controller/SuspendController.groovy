package system.controller;

import com.rameses.platform.interfaces.*;
import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.rcp.framework.ClientContext;
import com.rameses.osiris2.client.*;
import com.rameses.util.*;

public class SuspendController 
{
    def pwd;

    @Script("User")
    def user;

    boolean canclose;

    void init() {
        ClientContext.currentContext.platform.lock();
    }

    @Close
    public boolean canClose() {
        return canclose;
    }

    def resetHandler;

    public def resume() {
        user.checkPwd( pwd );
        ClientContext.currentContext.platform.unlock();
        if (resetHandler) resetHandler();
        
        canclose = true;
        return "_exit";
    }

    public void logoff() {
        canclose = true;
        logoffUser();
        def ctx = ClientContext.currentContext;
        ctx.platform.logoff();
        ctx.headers.clear(); 
        ctx.taskManager.stop();
    }

    public void exit() {
        canclose = true;
        logoffUser();
        def ctx = ClientContext.currentContext;
        ctx.platform.shutdown();
        ctx.headers.clear();        
    }
    
    private def handler;    
    private void logoffUser() {
        if (user.sessionId == null) return; 
        
        if (handler == null) {
            def service = InvokerProxy.instance.create("LogoutService"); 
            handler = new LogoutHandler(service: service, user: user); 
            OsirisContext.mainWindowListener.add(handler); 
        } 
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
