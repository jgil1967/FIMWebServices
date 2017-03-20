/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.document;

import java.util.List;

/**
 *
 * @author jonathangil
 */
public class DocumentGovernmentDTO extends DocumentDTO {
    List <DocumentDTO> children;
    public DocumentGovernmentDTO() {
        name = "Raiz";
        id = 0;
        idArea = 0;
    }

    public List<DocumentDTO> getChildren() {
        return children;
    }

    public void setChildren(List<DocumentDTO> children) {
        this.children = children;
    }

   

   
}
