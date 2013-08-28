package com.rameses.rules.helpers;

import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*

class ActionUtil {

    public static String buildText( def action ) {
        def s = new StringBuilder();
        s.append('<html>');
        s.append('<b>'+action.name+'</b>');
        def v = action.params?.expr?.expr;
        if (v) {
            s.append('<i> using the formula </i>');  
            s.append('<b>' + v + '</b>'); 
        }
        s.append('</html>');
        return s.toString(); 
    }

}