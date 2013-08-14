package etracs2.bpls.application;

import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*

abstract class AbstractSearchBplsApplication extends AbstractBplsApplication {
    
    def searchtext;

    //this is sample implementation
    public def findApplication(def o) {
        def app = [lobs:[], infos:[] ];
        app.txntype = "RENEW";
        app.controlno = "BP001";
        app.txnmode = "ONLINE";
        app.iyear = 2013;
        app.docstate = "DRAFT";
        app.txndate = "2013-01-01";
        app.permiteename ="NAZARENO, ELMO";
        app.permiteeaddress ="CEBU CITY";
        app.tradename ="RAMESES SYSTEMS INC.";
        app.businessaddress = "CAPITOL, CEBU CITY";
        app.lobs << [objid:'L1', title:'SARI-SARI STORE', assessmenttype:"RENEW"];
        app.lobs <<[objid:'L2', title:'BAKESHOP', assessmenttype:"RENEW"];
        app.infos << [type:'decimal',name:'POLICE FEE',level:0, value:150];
        app.infos << [lobid: 'L1', type:'decimal',name:'CAPITAL',level:0, value:25000];
        app.infos << [lobid: 'L2', type:'integer',name:'NUM_EMPLOYEES',level:0,value:3];
        return app;
    }

    def getLookupApplication() {
        return InvokerUtil.lookupOpener( "businessapplication:lookup",
            [onselect: {o-> 
                application=findApplication(o); 
                binding.refresh();
            }]
        );
    }    


}

