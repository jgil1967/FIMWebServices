/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.documentAndFolder;

import com.uas.document.DocumentDTO;

/**
 *
 * @author jonathangil
 */
public class DocumentAndFolderDTO {
    DocumentDTO document;
    DocumentDTO folder;

    public DocumentAndFolderDTO() {
document = new DocumentDTO();
folder = new DocumentDTO();
    }

    public DocumentDTO getDocument() {
        return document;
    }

    public void setDocument(DocumentDTO document) {
        this.document = document;
    }
    
}
