package etracs2.bpls.application;

import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*

abstract class AbstractBplsApplication extends PageFlowController {

    def lobAssessmentTypes = LOV.LOB_ASSESSMENT_TYPES*.key
    def orgtypes = LOV.BUSINESS_ORG_TYPES;
    def officetypes = LOV.BUSINESS_OFFICE_TYPES*.key; 
    
    def application;
    boolean hasMoreInfo = true;
    def maxLevel = 0;
    def selectedLob;

    def assessmentTypes = [ "NEW","RENEW", "RETIRE" ];

    def getAddLob() {
        return InvokerUtil.lookupOpener( "lob:lookup",
            [onselect: {o-> 
                if(o) {
                    if(!application.lobs.find{ it.objid == o.objid }) {
                        o.assessmenttype = "NEW";
                        application.lobs.add( o ); 
                        binding.refresh("selectedLob");
                    }
                }
            }]
        );
    }    

    void removeLob() {
        if(!selectedLob) return;
        application.lobs.remove( selectedLob );
        lobList.refresh();
    }


    def editableInfos = [];

    def colrender = { o->
        return "<html><table width=100><tr><td>"+o.caption+"</td><td width=20 align=right>Data</td></tr></table></html>";
    };


    def formPanelModel = [
            getFormControls: {
                def list = [];
                int i = 0;
                def lobid = null;
                editableInfos.sort{ (it.lobid)?it.lobid:"" };
                editableInfos.each {
                    def ctype = it.datatype;
                    def props = [:];
                    props.name = "editableInfos["+(i++)+"].value";
                    props.caption = colrender(it);
                    if(it.editable) props.editable = it.editable;
                    if(it.readonly) props.readonly = it.readonly;
                    list << new FormControl( ctype, props, it.lobid );
                }
                return list;
            },
            getCategory: { lobid->
                def o = application.lobs.find{ it.objid == lobid };
                return (o) ? o.name : "FOR ESTABLISHMENT INFO:";    
            }
        ] as FormPanelModel;

        public boolean loadMoreInfo() {
            return false;
        }

        void addMoreInfo() {
            hasMoreInfo = loadMoreInfo();
            maxLevel = editableInfos.level.max();
            if(hasMoreInfo) {
               editableInfos.findAll { it.level < maxLevel }.each {it.readonly = true };
            }
        }

        void resetPreviousInfo() {
            maxLevel = editableInfos.level.max();
            editableInfos.removeAll( editableInfos.findAll{ it.level == maxLevel } );
            editableInfos.findAll { it.level == (maxLevel-1) }.each{ it.readonly = false }; 
            maxLevel = editableInfos.level.max();
        }

        void resetInfos() {
            editableInfos.removeAll( editableInfos.findAll{ it.level > 0} );
            editableInfos.each { it.readonly = false;}
            maxLevel = 0;
        }


}