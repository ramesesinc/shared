package com.rameses.enterprise.treasury.cashreceipt.batch; 

import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
        
public abstract class BatchCaptureController  {

    @Binding 
    def binding;    

    def entity;
    def batchItems = [];
    def selectedItem;

    public abstract int getNextSeries();
    public abstract String getNextReceiptNo();
    public abstract void moveNext();
            
    def getLookupPayer() {
        return InvokerUtil.lookupOpener("entity:lookup", [
            onselect: { o->
                selectedItem.payer = o;
                selectedItem.paidby = o.name;
                selectedItem.paidbyaddress = o.address;
            }
        ]);
    }
            
    def getLookupAccount() {
        return InvokerUtil.lookupOpener("cashreceiptitem:lookup", [ 
            "query.collectorid": entity.collector.objid,
            onselect: { o->
                if(selectedItem.items == null ) selectedItem.items = [];
                selectedItem.items.clear();
                selectedItem.items << [account: o];
                selectedItem.acctinfo = o.title;
            }
        ]);
    }
            
    void calculate() {
        def amt = 0.0;
        batchItems.each {
            amt += (it.amount? it.amount: 0.0);
        }
        entity.totalamount = amt;
        binding?.refresh('entity.totalamount'); 
    }
            
    def listModel = [
        fetchList: { o->
            return batchItems;
        },
        createItem: {
            def m  = [:];
            m._filetype = "cashreceipt_batchcapture";
            m.receiptno = getNextReceiptNo();
            m.receiptdate = entity.receiptdate;
            m.series = getNextSeries();
            m.amount = 0.0;
            return m;
        },

        getOpenerParams: {o-> 
            return [
                callerListModel: listModel, 
                calculateHandler: { calculate(); } 
            ]; 
        },

        onAddItem: { o->
            batchItems << o; 
            moveNext();
        },

        onCommitItem:{o-> 
            calculate();
        },

        isColumnEditable:{item, colname-> 
            if (colname != 'amount') return true;
            if (!item.items) return false; 
            return (item.items.size() == 1); 
        },

        onColumnUpdate: {item, colname-> 
            if (colname == 'amount')
                item.items[0].amount = item[colname]; 
        }
    ] as EditorListModel;

}