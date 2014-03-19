/*
 * DynamicLookupModel.java
 *
 * Created on March 19, 2014, 2:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.rameses.osiris2.common;

import com.rameses.rcp.common.LookupDataSource;
import com.rameses.rcp.common.LookupSelector;

/**
 *
 * @author wflores
 */
public class DynamicLookupModel extends BasicListModel implements LookupDataSource 
{
    private LookupSelector selector; 
    private String returnItemKey;
    private String returnItemValue;
    private String returnFields; 
    
    public DynamicLookupModel() {
        
    }

    // <editor-fold defaultstate="collapsed" desc=" LookupDataSource implementation ">  

    public LookupSelector getSelector() { return selector; }    
    public void setSelector(LookupSelector selector) { 
        this.selector = selector; 
    }
    
    public String getReturnItemKey() { return returnItemKey; }    
    public void setReturnItemKey(String returnItemKey) { 
        this.returnItemKey = returnItemKey; 
    }

    public String getReturnItemValue() { return returnItemValue; }    
    public void setReturnItemValue(String returnItemValue) { 
        this.returnItemValue = returnItemValue; 
    } 
    
    public String getReturnFields() { return returnFields; }    
    public void setReturnFields(String returnFields) { 
        this.returnFields = returnFields; 
    }   
    
    public Object getValue() { 
        //This method needs to be overidden by the implementors 
        return null; 
    } 
    
    public boolean show(String searchtext) { 
        //This method is invoked by the XLookupField control before showing the popup form. 
        //return true will open the popup dialog 
        return true; 
    } 
      
    // </editor-fold>    
    
    // <editor-fold defaultstate="collapsed" desc=" helper/owner methods ">
        
    protected void onselect(Object item) {} 
    protected void oncancel() {}
    
    public Object select() { 
        Object value = getValue(); 
        return select(value); 
    } 
    
    public Object select(Object value) {
        onselect(value); 
        
        LookupSelector selector = getSelector();
        if (selector != null) selector.select(value); 
        
        return "_close";
    }
    
    public Object cancel() { 
        LookupSelector selector = getSelector();
        if (selector != null) selector.cancelSelection(); 
        
        return "_close"; 
    } 
    
    protected void onfinalize() throws Throwable {
        selector = null;
    }    
    
    // </editor-fold>    
}
