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
public class DocumentRelationshipFacade implements DocumentRelationshipInterface{
DocumentRelationshipInterface dao = null;

    public DocumentRelationshipFacade() {
        dao = new DocumentRelationshipDAO();
    }

    @Override
    public DocumentRelationshipDTO createDocumentRelationship(DocumentRelationshipDTO dto) {
    return dao.createDocumentRelationship(dto);
    }

    @Override
    public DocumentRelationshipDTO deleteDocumentRelationship(DocumentRelationshipDTO dto) {
       return dao.deleteDocumentRelationship(dto);
    }

  
    
}
