package com.rameses.enterprise.treasury.controllers; 

import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
        
public class BasicCashReceipt extends AbstractCashReceipt {
        
    void init() {
        super.init();
    }

    def itemListModel = [
        fetchList: { o->
            return entity.items;
        },
        onAddItem: {o-> 
            entity.items << o;
            entity.amount += o.amount;
            updateBalances();
        },
        onColumnUpdate: {o,name-> 
            if(entity.items) {
                entity.amount = entity.items.sum{ it.amount };
                updateBalances();
            }
        },
        onRemoveItem: { o->
            if(!MsgBox.confirm("You are about to remove this entry. Proceed?")) 
                return false;
            entity.items.remove( o );
            if( entity.items ) {
                entity.amount = entity.items.sum{ it.amount };
            }
            else {
                entity.amount = 0;
            }
            updateBalances();
            return true;
        }
    ] as EditorListModel;
            

    def selectedItem;
    def getLookupItems() {
        return InvokerUtil.lookupOpener("revenueitem:lookup",[
            "query.txntype" : "cashreceipt",
            onselect:{ o->
                if(!o.fund.objid) 
                    throw new Exception("The item selected must be associated with a fund");

                if(!o.cashbookid) 
                    throw new Exception("There is no associated cashbook for this item. Please contact the treasury");

                if( entity.items.find{ it.item.objid == o.objid }!=null )
                    throw new Exception("This item has already been added");
                selectedItem.item = o;
            }
        ]); 
    }
     
    //this is overridable bec. some might not follow this convention.
    public void validateBeforePost() {
        if(entity.totalcredit > 0)
            throw new Exception("Credit is not allowed for this transaction");
    }
       
}