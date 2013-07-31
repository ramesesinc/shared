package system.explorer;

import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;

public class ManagementTreeExplorer extends AbstractTreeExplorer {
    
    @Controller
    def controller;

    @Invoker
    def invoker;

    public void init() {
        def root = [id: '/mgmt/' + invoker.properties.root, caption:controller.title];
        doInit( root );
    }
}

