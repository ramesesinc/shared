package com.rameses.osiris2.common;

import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;

public class DefaultListController extends ListController 
{
    String getServiceName() {
        return wuserviceName; 
    }
}   