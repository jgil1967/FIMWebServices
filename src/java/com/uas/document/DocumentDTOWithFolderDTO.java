/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.document;

/**
 *
 * @author jonathangil
 */
public class DocumentDTOWithFolderDTO extends DocumentDTO {
DocumentDTO folder;
    public DocumentDTOWithFolderDTO() {
    folder = new DocumentDTO();
    }

    public DocumentDTO getFolder() {
        return folder;
    }

    public void setFolder(DocumentDTO folder) {
        this.folder = folder;
    }
    
}
