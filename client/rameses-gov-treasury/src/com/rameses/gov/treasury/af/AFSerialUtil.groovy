package com.rameses.gov.treasury.af; 

import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
        
class  AFSerialUtil  {

    public static int getEndSeries(def afInfo,  def entry ) {
        if( !entry?.qty ) return 0;
        if( !entry?.startseries ) return 0;
        if( !afInfo ) return 0;
        return entry.startseries + (entry.qty * afInfo.unitqty) - 1;
    }

    public static void validateQtyReceived(def itemInfo, def entry ) {
       if( itemInfo.qtyrequested < (entry.qty + itemInfo.qtyreceived))
            throw new Exception("Total qty must be less than or equal to qty requested");
    }

    public static int getEndStub(def entry ) {
        if( !entry?.qty ) return 0;
        if( !entry?.startstub ) return 0;
        return entry.startstub + entry.qty - 1;
    }

    public static void validateSeries(def afInfo, def entry ) {
        if(!(entry.startseries+"").endsWith("1")) {
            throw new Exception("Last number of start series must be 1");
        }
        if( (entry.startseries+"").length() != afInfo.serieslength ) {
            throw new Exception("Series number length must be " + afInfo.serieslength );
        }
        if( ( (entry.startseries-1) % afInfo.unitqty) != 0 ) {
            throw new Exception("Start series must be divisible by " + afInfo.unitqty);
        }
    }

    public static void checkConflict( def itemInfo, def entry ) {
        itemInfo.items.each {
            if(entry.startseries >= it.startseries && 
                entry.startseries <= it.endseries )
                throw new Exception("Start series in conflict with other series");    
            if(entry.endseries >= it.startseries && 
                entry.endseries <= it.endseries )
                throw new Exception("End series in conflict with other series");    
        }
    }
}