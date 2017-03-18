/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.documentRelationship;

/**
 *
 * @author jonathangil
 */
public class DocumentRelationshipDTO {
    int id, idDocumentChild, idDocumentParent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDocumentChild() {
        return idDocumentChild;
    }

    public void setIdDocumentChild(int idDocumentChild) {
        this.idDocumentChild = idDocumentChild;
    }

    public int getIdDocumentParent() {
        return idDocumentParent;
    }

    public void setIdDocumentParent(int idDocumentParent) {
        this.idDocumentParent = idDocumentParent;
    }
    
}
