package com.rameses.rulemgmt.constraint;

import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*

public class RuleConstraintDynamicLookupHandler extends RuleConstraintHandler {

    @Service("RuleMgmtService")
    def service;

    def operatorList = [
        [caption:"equals", symbol:"=="],
        [caption:"not equals", symbol:"!="],
        [caption:"is null", symbol:"==null"],
        [caption:"not null", symbol:"!=null"],
    ];

    void init() {
       
    }

    def showLookup() {
        if( !field.lookupkey || !field.lookupvalue || !field.lookuphandler )
            throw new Exception( "Please specify a lookup key, value and handler in the definition" )
        return InvokerUtil.lookupOpener( field.lookuphandler, [
            onselect: { o->
                def m = [:];
                m.objid = o[field.lookupkey];
                m.name = o[field.lookupvalue];
                m.datatype = o[field.lookupdatatype];
                constraint.dynamicvar = m;
                constraint.varname = m.name;
                binding.refresh("selection|constraint.varname");
            }
        ]);
    }
    
    String getSelection() {
        if( constraint.dynamicvar != null ) {
            return constraint.dynamicvar.name;
        }
        return "";
    }

}