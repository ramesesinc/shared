package com.rameses.rules.helpers;

import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*

class RuleUtil {

    //returns a list of variables based on a position
    public static getVars( def rule, def position ) { 
        def vars = [];
        if(position<0) position = rule.conditions.size();
        rule.conditions.eachWithIndex{ c,i->
            if(i < position) {
                if(c.varname) vars << [name:c.varname, datatype:c.factclass];
                c.constraints.eachWithIndex{ ct,j->
                    if(ct.varname) {
                        def dtype = ct.datatype;
                        if( dtype == "dynamic") {
                           dtype = ct.value?.datatype;
                        } 
                        vars << [name:ct.varname, datatype:dtype];
                    }
                }
            }
        };
        return vars;
    }

    public static String buildText( def condition ) {
        def builder = new StringBuilder();
        if( condition.varname ) builder.append( condition.varname + " :" );
        builder.append( condition.name +"(" );
                
        condition.constraints.eachWithIndex { c,i->
            if(i>0) builder.append(",");
            builder.append( " " + c.displaytext + " " );
        }
        builder.append(")");
        return builder.toString(); 
    }
    

    //search all field names that have referenced or usevar is true.
    public static void checkReferences( def factList, def condition ) {
        def fact = factList.find{it.name == condition.name};
        def reqflds = fact.fields.findAll{ it.usevar == true }*.name;
        def flds = [];
        reqflds.each {r->
            def zlist = condition.constraints.findAll{
                it.fieldname == r && it.usevar == true;
            };
            if(!zlist)flds << r;
        }    
        if(flds) {
            throw new Exception( flds.join(",") + " field(s) require a reference variable");
        }                
    }

    
    public static void checkVars( def factList, def condition ) {
        checkReferences( factList, condition, false );
    }


    //check for conflicting var names
    public static void checkVars( def rule, def condition, def position ) {
        checkVars( rule, condition, position, false );
    }

    public static void checkVars( def rule, def condition, def position, boolean deleted ) {
        def list = [];
        if(position<0) position = rule.conditions.size();    

        //for testing unique var names
        def testUnique = { o, txt->
            if(o) {
                if(list.contains(o)) {
                    throw new Exception( "Variable conflicting name " + o + "." + txt );
                }   
                list.add(o);
            }    
        }

        //for testing if constraint with use variable reference exists
        def testReference = { ct, txt->
            if(ct.usevar && !list.contains(ct.value))
                throw new Exception("Reference " + ct.value + " is used but does not exist. " + txt );
        }

        def testCondition = { c,pos->
            testUnique( c.varname, " condition [" + pos + "]" );
            c.constraints.eachWithIndex { ct,j->
                def msg = "condition["+pos+"] constraint ["+j+"]";
                testUnique( ct.varname,msg );
                testReference( ct, msg );
            }
        }

        rule.conditions.eachWithIndex{ c,i->
            if(position != i ) {
                testCondition( c, i );
            }
            else if(!deleted) {
                testCondition( condition, position );
            }
        }
        if( position == rule.conditions.size()  && !deleted ) {
            testCondition(condition,position);
        }
    }

}

