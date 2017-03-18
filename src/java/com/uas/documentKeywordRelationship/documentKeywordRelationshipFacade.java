/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.documentKeywordRelationship;

import com.uas.document.DocumentDTO;

/**
 *
 * @author jonathangil
 */
public class documentKeywordRelationshipFacade implements documentKeywordRelationshipInterface {
documentKeywordRelationshipInterface dDao = null;

    public documentKeywordRelationshipFacade() {
        dDao = new documentKeywordRelationshipDAO();
    }


    @Override
    public documentKeywordRelationshipDTO createdocumentKeywordRelationshipDTO(documentKeywordRelationshipDTO dDto) {
    return dDao.createdocumentKeywordRelationshipDTO(dDto);
    }

    @Override
    public documentKeywordRelationshipDTO deleteDocumentKeywordRelationshipDTO(documentKeywordRelationshipDTO dDto) {
    return dDao.deleteDocumentKeywordRelationshipDTO(dDto);
    }

    @Override
    public DocumentDTO deleteDocumentKeywordRelationshipsByDocument(DocumentDTO dDto) {
     return dDao.deleteDocumentKeywordRelationshipsByDocument(dDto); }
    
}
