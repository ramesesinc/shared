/*
 * FileType.java
 *
 * Created on August 2, 2013, 10:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.rameses.osiris2.common;

import com.rameses.rcp.common.Node;
import java.util.Map;

/**
 *
 * @author wflores
 */
public interface FileType {

    String getFileType();
    String getIcon();    
    
    Node getNode(); 
    void setNode(Node node); 
    
    void setSelectedItem(Map item); 
    
    void open(); 
    void create(); 
} 
