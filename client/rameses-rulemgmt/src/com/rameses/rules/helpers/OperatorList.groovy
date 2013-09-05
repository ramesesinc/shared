package com.rameses.rules.helpers;

import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*

class OperatorList {

    private static def map = [

        number: [ 
           [caption:"equals",  symbol:"=="], 
           [caption:"greater than",symbol: ">"],
           [caption:"greater or equal to",symbol: ">="],
           [caption:"less than",symbol: "<"],
           [caption:"less or equal to",symbol: "<="],
           [caption:"not equal to",symbol: "!="],
        ],

        string : [
            [caption:"matches", symbol:"matches"], 
            [caption:"not matches", symbol:"not matches"],
            [caption:"equals", symbol:"=="], 
            [caption:"not equals", symbol:"!="] 
        ],

        object : [
            [caption:"equals", symbol:"=="], 
            [caption:"contains", symbol:"matches", multi:true], 
            [caption:"not equals", symbol:"!="],
            [caption:"not contains", symbol:"not matches", multi:true ],
        ],

        reqvar : [
            [caption: "equals", symbol:"=="]
        ]
    ];

    public static List getOperator(def constraint, def mfield) {
        println 'getOperator: constraint=' + constraint;
        if( !constraint.fieldname ) return [];
        String usetype = constraint.fieldtype;
        println 'getOperator: usetype=' + usetype;
        if(usetype == "dynamic" ) {
            usetype = "reqvar";
        }
        else if(mfield.usevar) {
            usetype = "reqvar";
        }
        else if(usetype.matches("decimal|integer")) {
            usetype = "number";
        }
        else if( !usetype.matches("string|list") ) {
            usetype = "object";
        }
        println 'usetype='+usetype;
        return map[usetype];        
    }

}
