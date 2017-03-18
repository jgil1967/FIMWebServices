/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.properties;

/**
 *
 * @author jonathangil
 */
public class PropertiesFacade implements PropertiesInterface{
PropertiesInterface dao = null;

    public PropertiesFacade() {
        dao= new PropertiesDAO();
    }


    @Override
    public String obtenerValorPropiedad(String nombrePropiedad) {
        return dao.obtenerValorPropiedad(nombrePropiedad);
    }
    
}
