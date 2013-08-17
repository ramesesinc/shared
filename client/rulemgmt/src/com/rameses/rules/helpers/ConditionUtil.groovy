package com.rameses.rules.helpers;

import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*

class ConditionUtil {

    //this validates a constraint at the same time, builds the displayText.
    public static void validate( def constraint ) {
        def varname = constraint.varname;
        def operator = constraint.operator;
        def value = constraint.value;    

        if( !constraint.fieldname )throw new Exception("Please select a field");
        if( constraint.datatype == "dynamic" && !varname )
            throw new Exception("Please specify a variable name when using a dynamic field");
        if( !varname && !operator?.symbol )
            throw new Exception("Assign a variable or add a constraint"); 

        if(operator) {
            if(!value)
                throw new Exception("Field Value must be specified");
        } 
    }

    public static String buildText( def constraint ) {
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

}