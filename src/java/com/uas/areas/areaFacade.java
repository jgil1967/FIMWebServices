/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.areas;

import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public class areaFacade implements areaInterface{
areaInterface dao = null;

    public areaFacade() {
        dao = new areaDAO();
    }


    @Override
    public areaDTO createArea(areaDTO dto) {
  return dao.createArea(dto);}

    @Override
    public ArrayList<areaDTO> getAreas() {
   return dao.getAreas(); }

    @Override
    public ArrayList<areaDTO> getPossibleAreasByArea(areaDTO oDto) {
       return dao.getPossibleAreasByArea(oDto); 
    }

    @Override
    public ArrayList<areaDTO> getAreasByArea(areaDTO oDto) {
    return dao.getAreasByArea(oDto); 
    }

    @Override
    public ArrayList<areaDTO> getAreasByArea2(areaDTO oDto) {
       return dao.getAreasByArea2(oDto);
    }

    @Override
    public areaDTO updateArea(areaDTO dto) {
      return dao.updateArea(dto);
    }

    @Override
    public ArrayList<areaDTO> crearCarpetasAPartirDeAreas(ArrayList<areaDTO> list) {
        return dao.crearCarpetasAPartirDeAreas(list);
    }

    @Override
    public areaDTO asignarFolderName(areaDTO dto) {
        return dao.asignarFolderName(dto);
    }

    @Override
    public areaDTO getAreaByID(areaDTO dto) {
        return dao.getAreaByID(dto);
    }
    
}
