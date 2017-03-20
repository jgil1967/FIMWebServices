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
public class DocumentFacade implements DocumentInterface {
DocumentInterface dDao = null;

    public DocumentFacade() {
dDao = new DocumentDAO ();
    }

    @Override
    public DocumentDTOWithFolderDTO getDocument(DocumentDTOWithFolderDTO dDto) {
  return dDao.getDocument(dDto) ; }

    @Override
    public ArrayList<DocumentDTOWithFolderDTO> getDocuments() {
        return dDao.getDocuments();
    }

    @Override
    public DocumentDTOWithFolderDTO createDocument(DocumentDTOWithFolderDTO dDto) {
        return dDao.createDocument(dDto);
    }

//    @Override
//    public DocumentDTO updateDocument(DocumentDTO dDto) {
//return dDao.updateDocument (dDto);
//    }

    @Override
    public ArrayList<DocumentDTO> searchDocuments(DocumentDTO oDto) {
   return dDao.searchDocuments (oDto);
    }

    @Override
    public DocumentDTOWithFolderDTO updateDocument(DocumentDTOWithFolderDTO dDto) {
        return dDao.updateDocument(dDto);
    }

    @Override
    public ArrayList<DocumentDTOWithFolderDTO> getDocuments(FiltersDTO dto) {
   return dDao.getDocuments(dto);
    }

    @Override
    public ArrayList<DocumentDTO> getDocumentsOnlyEnabled(ArrayList<areaDTO> areas) {
      return dDao.getDocumentsOnlyEnabled(areas);
    }

    @Override
    public ArrayList<DocumentDTO> getDocumentsByUser(UsuarioDTO dto) {
    return dDao.getDocumentsByUser(dto); }

    @Override
    public ArrayList<DocumentDTOWithFolderDTO> getDocumentsByFolder(DocumentDTOWithFolderDTO dto) {
    return dDao.getDocumentsByFolder(dto); }

    @Override
    public DocumentDTOWithFolderDTO createFolder(DocumentDTOWithFolderDTO dDto) {
        return dDao.createFolder(dDto);
    }

    @Override
    public DocumentDTOWithFolderDTO createDocument2(DocumentDTOWithFolderDTO dDto) {
        return dDao.createDocument2(dDto);
    }

    @Override
    public ArrayList<DocumentDTO> getFolders() {
        return dDao.getFolders();
    }

    @Override
    public DocumentGovernmentDTO getDocumentGovernment() {
      return dDao.getDocumentGovernment();
    }

    @Override
    public ArrayList<DocumentDTO> getFoldersChildren(DocumentDTO dto) {
        return dDao.getFoldersChildren(dto);
    }

    @Override
    public String moveDocuments(ArrayList<DocumentDTOWithFolderDTO> documents) {
        return dDao.moveDocuments(documents);
    }

    @Override
    public Boolean verificaSiEsDescendiente(ArrayList<DocumentDTOWithFolderDTO> documents) {
           return dDao.verificaSiEsDescendiente(documents);
    }
    
}
