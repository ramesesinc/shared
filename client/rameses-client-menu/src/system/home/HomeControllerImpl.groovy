package system.home;

import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.util.*;
import com.rameses.osiris2.*;
import com.rameses.osiris2.client.*;

public class HomeControllerImpl {

    def formActions;
    
    void init(){
        formActions = InvokerUtil.lookupActions("home.action");
        formActions?.each{
            if (!it.icon) it.icon = 'images/home-icon.png';
        }
    }

}
