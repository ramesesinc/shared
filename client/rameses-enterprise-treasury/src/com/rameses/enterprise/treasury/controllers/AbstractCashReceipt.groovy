package com.rameses.enterprise.treasury.controllers; 

import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.reports.*;
import com.rameses.osiris2.common.*
import com.rameses.util.*;
        
public abstract class AbstractCashReceipt {
        
    @Binding
    def binding;
    
    @Service("CashReceiptService")
    def service;
        
    def entity;
   
    String title;
    boolean completed = false;
    
    void init() {
        title = entity.collectiontype.title;
        completed = false;
    }

    void clearAllPayments() {
        entity.totalcash = 0;
        entity.totalnoncash = 0;
        entity.balancedue = 0;
        entity.cashchange = 0;
        entity.totalcredit = 0;
        entity.paymentitems = [];
    }

    public void updateBalances() {
        entity.cashchange = 0; entity.balancedue = 0; entity.totalcredit = 0;
        def amt = entity.amount;
        
        entity.totalnoncash = 0;
        if( entity.paymentitems ) {
            entity.totalnoncash = entity.paymentitems.sum{ it.amount };
        }

        if( entity.totalnoncash > 0 && entity.totalnoncash > amt ) {
            entity.totalcredit = entity.totalnoncash - amt;
        }
        else {
            amt = amt - entity.totalnoncash;
            if( entity.totalcash > 0 && entity.totalcash > amt ) {
                entity.cashchange = entity.totalcash - amt;
            }
            else {
                entity.balancedue = amt - entity.totalcash;
            }
        }
        binding.refresh('entity.(amount|totalcash|totalnoncash|totalcredit|balancedue|cashchange)');
    }

    def paymentListModel = [
        fetchList: { o->
            return entity.paymentitems;
        },
        onRemoveItem: { o->
            if(!MsgBox.confirm("You are about to remove this entry. Proceed?")) 
                return false;
            entity.paymentitems.remove( o );
            updateBalances();
            return true;
        }
    ] as EditorListModel;

    def doCashPayment() {
        def handler = { o->
            entity.totalcash = o.cash;
            entity.cashchange = o.change;
            updateBalances();
        }
        return InvokerUtil.lookupOpener( "cashreceipt:payment-cash",
            [entity: entity, saveHandler: handler ] );
    }

    def doCheckPayment() {
        def handler = { o->
            entity.paymentitems << o;
            updateBalances();
        }
        return InvokerUtil.lookupOpener( "cashreceipt:payment-check",
            [entity: entity, saveHandler: handler ] );
    }


    def getLookupEntity() {
        return InvokerUtil.lookupOpener( "entity:lookup", [
            onselect: { o->
                entity.payer = o;
                entity.paidby = o.name;
                entity.paidbyaddress = o.address;
                binding.refresh("entity.(payer.*|paidby.*)");
            }
        ]);
    }

    //this is overridable bec. some might not follow this convention.
    public void validateBeforePost() {
    }

    def post() {
        if( entity.amount <= 0 ) 
            throw new Exception("Please select at least an item to pay");
        if( entity.totalcash + entity.totalnoncash == 0 )
            throw new Exception("Please make a payment either cash or check");
        if(entity.balancedue > 0)
            throw new Exception("Please ensure that there is no balance unpaid");

        validateBeforePost();
    
        if(MsgBox.confirm("You are about to post this payment. Please ensure entries are correct")) {
            entity = service.post( entity );
            try {
                print();
            }
            catch(e) {
                MsgBox.alert("warning! no form handler found for.  " + entity.formno +". Printout is not handled" );
            }
            completed = true;
            return "completed";
        }
    }

    def findReportOpener(def reportData) {
        //check fist if form handler exists.
        def o = InvokerUtil.lookupOpener( "cashreceipt-form:"+entity.formno, [reportData:reportData] );
        if(!o)
            throw new Exception("Handler not found");
        return o.handle;
    }

    void print() {
        def handle = findReportOpener(entity);
        handle.viewReport();
        ReportUtil.print(handle.report,true);
    }
    
    void reprint() {
        MsgBox.alert( "requires approval" );
        if(1==1) return;
        def handle = findReportOpener(entity);
        handle.viewReport();
        ReportUtil.print(handle.report,true);
    }

    def getInfoHtml() {
        return TemplateProvider.instance.getResult( "com/rameses/enterprise/treasury/cashreceipt/cashreceipt.gtpl", [entity:entity] );
    }

    def doVoid() {
        return InvokerUtil.lookupOpener( "cashreceipt:void", [receipt:entity,
            handler: { o->
                entity.voided = true;
                binding.refresh();
            }
        ]); 
    }
}