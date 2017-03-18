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
public interface documentKeywordRelationshipInterface {
        documentKeywordRelationshipDTO createdocumentKeywordRelationshipDTO(documentKeywordRelationshipDTO dDto);
        documentKeywordRelationshipDTO deleteDocumentKeywordRelationshipDTO(documentKeywordRelationshipDTO dDto);
   DocumentDTO deleteDocumentKeywordRelationshipsByDocument(DocumentDTO dDto);

}
