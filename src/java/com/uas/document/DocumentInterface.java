/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.document;

import com.uas.areas.areaDTO;
import com.uas.dates.filters.filtersDTO.FiltersDTO;
import com.uas.usuarios.UsuarioDTO;
import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public interface DocumentInterface {
     DocumentDTOWithFolderDTO getDocument(DocumentDTOWithFolderDTO dDto);
     DocumentDTOWithFolderDTO createFolder(DocumentDTOWithFolderDTO dDto);
     DocumentDTOWithFolderDTO createDocument2(DocumentDTOWithFolderDTO dDto);
       ArrayList<DocumentDTOWithFolderDTO> getDocuments();
       ArrayList<DocumentDTO> getFolders();
       ArrayList<DocumentDTO> getFoldersChildren(DocumentDTO dto);
       Boolean verificaSiEsDescendiente(ArrayList<DocumentDTOWithFolderDTO> documents);
       ArrayList<DocumentDTOWithFolderDTO> getDocumentsByFolder(DocumentDTOWithFolderDTO dto);
       ArrayList<DocumentDTO> getDocumentsOnlyEnabled(ArrayList<areaDTO> areas);
       ArrayList<DocumentDTOWithFolderDTO> getDocuments(FiltersDTO filters);
       DocumentDTOWithFolderDTO createDocument(DocumentDTOWithFolderDTO dDto);
     DocumentDTOWithFolderDTO updateDocument(DocumentDTOWithFolderDTO dDto);
       ArrayList <DocumentDTO> searchDocuments (DocumentDTO oDto);
        ArrayList<DocumentDTO> getDocumentsByUser(UsuarioDTO dto) ;
        DocumentGovernmentDTO getDocumentGovernment ();
        String moveDocuments (ArrayList<DocumentDTOWithFolderDTO> documents);
}
