package com.rameses.enterprise.treasury.cashreceipt; 

import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
        
public abstract class AbstractCashReceipt {
        
    @Binding
    def binding;
            
    def entity;
   
    String title;
    boolean completed = false;
        
    void init() {
        entity.items = [];
        title = entity.collectiontype.title;
        clearAllPayments();
        entity.amount = 0;
        completed = false;
    }

    void clearAllPayments() {
        entity.totalcash = 0;
        entity.totalnoncash = 0;
        entity.balancedue = 0;
        entity.change = 0;
        entity.totalcredit = 0;
        entity.paymentitems = [];
    }

    public void updateBalances() {
        entity.change = 0; entity.balancedue = 0; entity.totalcredit = 0;
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
                entity.change = entity.totalcash - amt;
            }
            else {
                entity.balancedue = amt - entity.totalcash;
            }
        }
        binding.refresh('entity.(amount|totalcash|totalnoncash|totalcredit|balancedue|change)');
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

    void validateBeforePost() {

    }

    def post() {
        if( entity.amount > 0 ) 
            throw new Exception("Please select at least an item to pay");
        if( entity.totalcash == 0 || entity.totalnoncash == 0 )
            throw new Exception("Please make a payment either cash or check");

        validateBeforePost();
    
        if(MsgBox.confirm("You are about to post this payment. Please ensure entries are correct")) {
            completed = true;
            return "completed";
        }
    }

    def print() {
        MsgBox.alert("Form No is " + entity.formno);
    }

    void addAccount() {
        MsgBox.alert( entity.items );
    }

}