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
public interface areaRelationshipsInterface {
    public areaRelationshipsDTO createAreaRelationship (areaRelationshipsDTO dto);
    public areaRelationshipsDTO deleteAreaRelationship (areaRelationshipsDTO dto);
    public  areaRelationshipsDTO    uploadAndEdit (areaRelationshipsDTO dto);
}
