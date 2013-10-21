package com.rameses.enterprise.inventory.stock;

import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import java.rmi.server.*;
import com.rameses.util.*;

public abstract class StockIssueController {

    @Binding
    def binding

    @Service("StockInventoryService")
    def service;
        
    @Service("StockIssueService")
    def issueSvc;

    @Service("StockRequestService")
    def stockReqSvc;

    def entity;
    def request;
    def selectedItem;
    def mode = "initial";

    def create() {
        entity = [items:[]];
        entity.objid = "STKISS"+new UID();
        return "initial";
    }

    def enterQty() {
        def r = stockReqSvc.open([objid: request.objid]);
        entity.request = request;
        entity.reqtype = r.reqtype;
        entity.itemclass = r.itemclass;
        entity.issueto = request.requester;
        r.items.each {
            def o = [:];
            o.item = it.item;
            o.handler = it.handler;
            o.unit = it.unit;
            o.qtyrequested = it.qty;
            o.qtyissued = 0;
            entity.items << o;
        }
        mode = "enterqty";
        title = "Specify Qty to Issue";
        return "enterqty";
    }

    def itemModel = [
        onColumnUpdate: { o,col->
            if(o.qtyissued > o.qtyrequested )
                throw new Exception("Qty issued must be less or equal to qty requested");
        },
        fetchList: { o->
            return entity.items;
        }
    ] as EditorListModel;

    def process() {
        if( !entity.items.find{ it.qtyissued > 0  })
            throw new Exception("Please process at least one item");
        entity = service.getAvailable(entity);   
        itemModel.reload();
        mode = "process";
        return "default";
    }

    def revert() {
        mode = "enterqty";
        return "default";
    }

    void save() {
        if(MsgBox.confirm("You are about to submit this transaction. Continue?")) {
            entity = issueSvc.create(entity);
            mode ="read";
        }
    }

    String getRenderer() {
        if(!selectedItem) return "";
        return TemplateProvider.instance.getResult( "com/rameses/handlers/stockissue/" + selectedItem.handler +".gtpl", [entity:selectedItem] );            
    }
    
    def print() {
        entity.reqno = entity.request.reqno;
        entity.dtfiled = entity.request.dtfiled;
        entity.requester = entity.request.requester;
        entity.items.each { itm -> 
            itm.qty = itm.qtyrequested
            itm.qtyreceived = itm.qtyissued     
            def srs = []
            itm.items.each{
                srs <<  it.startseries.toString().padLeft(7, '0') + " - " + it.endseries.toString().padLeft(7, '0') + " ( " + (it.startstub == it.endstub ? it.startstub+"" : ( it.startstub + " - " + it.endstub )) + " ) "
            }
            itm.series = srs.join(',')
        }   

        return InvokerUtil.lookupOpener("stockrequest:ris", [entity: entity]);
    }
}

        
      