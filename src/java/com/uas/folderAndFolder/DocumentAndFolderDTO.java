/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.folderAndFolder;

import com.uas.documentAndFolder.*;
import com.uas.document.DocumentDTO;

/**
 *
 * @author jonathangil
 */
public class DocumentAndFolderDTO {
    DocumentDTO folderParent;
    DocumentDTO folderChild;

    public DocumentAndFolderDTO() {
folderParent = new DocumentDTO();
folderChild = new DocumentDTO();
    }

    public DocumentDTO getFolderParent() {
        return folderParent;
    }

    public void setFolderParent(DocumentDTO folderParent) {
        this.folderParent = folderParent;
    }

    public DocumentDTO getFolderChild() {
        return folderChild;
    }

    public void setFolderChild(DocumentDTO folderChild) {
        this.folderChild = folderChild;
    }

    
    
}
