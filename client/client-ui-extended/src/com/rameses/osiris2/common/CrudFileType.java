/*
 * CrudFileType.java
 *
 * Created on August 3, 2013, 7:45 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.rameses.osiris2.common;

import com.rameses.rcp.common.Node;
import java.util.Map;

/**
 *
 * @author Elmo
 */
public abstract class CrudFileType extends CRUDController implements FileType {
    
    private Node node;
    
    /** Creates a new instance of CrudFileType */
   
    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setSelectedItem(Map item) {
        super.setEntity(item);
    }

    
}
