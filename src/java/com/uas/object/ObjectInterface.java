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
public interface ObjectInterface {
   ArrayList <ObjectDTO> getObjects ();
        ObjectDTO createObject (ObjectDTO oDto);
             ObjectDTO updateObject(ObjectDTO oDto) ;
             
}
