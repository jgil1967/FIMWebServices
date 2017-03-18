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
public class FilesFacade implements FilesInterface {
FilesInterface dao = null;

    public FilesFacade() {
        dao = new FilesDAO();
    }


    @Override
    public Boolean verificaSiExiste(String nombre) {
        return dao.verificaSiExiste(nombre);
    }

    @Override
    public String retornaNombreBienParaCarpeta(String nombre) {
        return dao.retornaNombreBienParaCarpeta(nombre);
    }
    
}
