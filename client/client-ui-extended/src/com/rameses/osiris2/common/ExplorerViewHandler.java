/*
 * ExplorerViewHandler.java
 *
 * Created on August 2, 2013, 11:58 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.rameses.osiris2.common;

import com.rameses.rcp.common.Action;
import com.rameses.rcp.common.Node;
import com.rameses.rcp.common.Opener;
import java.util.List;

/**
 *
 * @author wflores
 */
public interface ExplorerViewHandler {
    
    void setNode(Node node); 
    
    void setNodeActions(List<Action> actions); 
    
    void setOpener(Opener opener); 
    
    void setService(ExplorerViewService service); 
    
    void updateView(); 
    
}
