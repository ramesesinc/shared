package com.rameses.gov.treasury.af; 

import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
        
class  AFUtil  {

    public static int getEndSeries(def afInfo,  def entry ) {
        if( !entry?.qty ) return 0;
        if( !entry?.startseries ) return 0;
        if( !afInfo ) return 0;
        return entry.startseries + (entry.qty * afInfo.unitqty) - 1;
    }

    public static int getEndStub(def afInfo,  def entry ) {
        if( !entry?.qty ) return 0;
        if( !entry?.startstub ) return 0;
        if( !afInfo ) return 0;
        return entry.startstub + entry.qty - 1;
    }

    public static void validate(def afInfo, def entry ) {
        if(afInfo.aftype == "serial")
            checkSerial(afInfo,entry);
        else    
            checkNonSerial(afInfo,entry);
    }

    public static void checkSerial(def afInfo, def entry ) {
        if(!(entry.startseries+"").endsWith("1")) {
            throw new Exception("Last number of start series must be 1");
        }
        if( (entry.startseries+"").length() != (entry.endseries+"").length() ) {
            throw new Exception("Start series must be same length as end series" );
        }
        if( (entry.startseries+"").length() != afInfo.serieslength ) {
            throw new Exception("Series number length must be " + afInfo.serieslength );
        }
        if( ( (entry.startseries-1) % afInfo.unitqty) != 0 ) {
            throw new Exception("Start series must be divisible by " + afInfo.unitqty);
        }
    }

    public static void checkNonSerial(def afInfo, def entry ) {

    }

}