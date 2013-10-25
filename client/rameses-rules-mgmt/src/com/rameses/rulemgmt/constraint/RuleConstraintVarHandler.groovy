package com.rameses.rulemgmt.constraint;

import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*

public class RuleConstraintVarHandler  extends RuleConstraintHandler {

    @Service("RuleMgmtService")
    def service;

    def varList;

    def operatorList = [
        [caption:"equals", symbol:"=="],
        [caption:"is null", symbol:"== null"],
        [caption:"not null", symbol:"!= null"],
    ];

    void init() {
        varList = service.findAllVarsByType( [ruleid:condition.parentid, datatype:field.vardatatype, pos: condition.pos ] ).collect{  
            [objid: it.objid, name: it.name]
        };
    }

}