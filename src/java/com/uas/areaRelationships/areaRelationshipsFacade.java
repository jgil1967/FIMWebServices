/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.areaRelationships;

/**
 *
 * @author jonathangil
 */
public class areaRelationshipsFacade implements areaRelationshipsInterface {
areaRelationshipsInterface aDao = null;
    public areaRelationshipsFacade() {
      aDao  = new areaRelationshipsDAO();
    }

    
    
    @Override
    public areaRelationshipsDTO createAreaRelationship(areaRelationshipsDTO dto) {
  return aDao.createAreaRelationship(dto); }

    @Override
    public areaRelationshipsDTO deleteAreaRelationship(areaRelationshipsDTO dto) {
   return aDao.deleteAreaRelationship(dto); }

    @Override
    public areaRelationshipsDTO uploadAndEdit(areaRelationshipsDTO dto) {
   return aDao.uploadAndEdit(dto);  }
    
}
