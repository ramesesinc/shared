package com.rameses.osiris2.common;

import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;

public class DefaultListController extends ListController {

    private String serviceName;
    private String entityName;
    private String formTarget;
    private String tag;

    String getServiceName() {
        if (serviceName == null) {
            serviceName = controller?.workunit?.workunit?.properties?.serviceName;
        } 
        return serviceName;
    }

    String getEntityName() {
        if (entityName == null) {
            entityName = controller?.workunit?.workunit?.properties?.entityName;

            if (entityName == null) 
                throw new NullPointerException("Please provide an entityName");
        }
        return entityName;
    }  

    String getFormTarget() {
        if (formTarget == null) {
            formTarget = controller?.workunit?.workunit?.properties?.formTarget;
            if (formTarget == null) formTarget = "popup";
        } 
        return formTarget; 
    }  

    String getTag() {
        if (tag == null) {
            tag = controller?.workunit?.workunit?.properties?.tag;
        } 
        return tag; 
    }          
}   