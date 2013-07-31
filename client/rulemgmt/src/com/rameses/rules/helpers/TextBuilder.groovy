package com.rameses.rules.helpers;

import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*

class TextBuilder {


    public static String buildRule(def rule) {
        def builder = new StringBuilder();
        return builder.toString();
    }

    public static String buildCondition(def condition) {
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

    public static String buildConstraint(def constraint) {
        def varname = constraint.varname;
        def operator = constraint.operator;
        def valueText = constraint.valueText;
        def usevar = constraint.usevar;
        def value = constraint.value;

        def s = new StringBuilder();
        if( varname ) {
            s.append( varname + " : " ); 
        }

        s.append( constraint.fieldname ); 
        if( operator ) {    
            s.append( " " + operator.caption + " " );
            if(valueText) 
                s.append( valueText ); 
            else {
                def v = value;
                if( constraint.datatype == "string" && !usevar) v = "'"+v+"'";    
                s.append( v ); 
            }    
        }
        return s.toString();  
    }

    public static String buildAction(def actionDef, def action) {
        def s = new StringBuilder();
        s.append( a.description + " with the ff. parameters:");
        actionDef.params.eachWithIndex{ a,i->
            s.append( a.name + " = " );
            
        }
        return s.toString();
    }


}