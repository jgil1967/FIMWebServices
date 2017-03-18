/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.object;

import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public class ObjectFacade implements ObjectInterface {
ObjectInterface gDao = null;
    public ObjectFacade() {
   gDao  = new ObjectDAO();
    }

    
    @Override
    public ObjectDTO createObject(ObjectDTO oDto) {
   return gDao.createObject(oDto);
    }

    @Override
    public ObjectDTO updateObject(ObjectDTO oDto) {
     return gDao.updateObject(oDto);}

    @Override
    public ArrayList<ObjectDTO> getObjects() {
    return gDao.getObjects();
            
            }

    

}

