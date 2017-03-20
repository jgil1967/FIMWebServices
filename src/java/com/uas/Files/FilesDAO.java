/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.Files;

import java.io.File;

/**
 *
 * @author jonathangil
 */
public class FilesDAO implements FilesInterface {

    @Override
    public Boolean verificaSiExiste(String nombre) {
        
        File file = new File (nombre);
        return file.exists();
        
    }

    @Override
    public String retornaNombreBienParaCarpeta(String nombre) {
        
       
    
    try {
       Boolean searching = true;
        int count = 1;
while (searching) {
  
   File f = new File(nombre);

    if(f.exists()) {
        count++;
    } else {
        nombre = nombre + "( " + count + ")";
        searching = false;
        return nombre;
    }
} 

//         File f = null;
//    for (int i = 1;1<=100; i++) {
//        f = new File(String.format(nombre, i));
//        if (!f.exists()) {
//            nombre = nombre+"("+i+")";
//        }
//          return nombre;}
    
    
    } catch (Exception e) {
        e.printStackTrace();
    }
    return nombre;
    }
    
}
