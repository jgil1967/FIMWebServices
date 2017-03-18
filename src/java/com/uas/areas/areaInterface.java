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
public interface areaInterface {
 areaDTO createArea (areaDTO dto);
 areaDTO updateArea (areaDTO dto);
 ArrayList<areaDTO> getAreas ();
  public ArrayList<areaDTO> getPossibleAreasByArea(areaDTO oDto);
            public ArrayList<areaDTO> getAreasByArea(areaDTO oDto);
            public ArrayList<areaDTO> getAreasByArea2(areaDTO oDto);
            public ArrayList<areaDTO> crearCarpetasAPartirDeAreas(ArrayList<areaDTO> list);
            public areaDTO asignarFolderName (areaDTO dto);
            public areaDTO getAreaByID (areaDTO dto);
            
}
