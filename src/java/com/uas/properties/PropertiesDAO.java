/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author jonathangil
 */
public class PropertiesDAO implements PropertiesInterface{

    @Override
    public String obtenerValorPropiedad(String property) {
        Properties prop = new Properties();
	InputStream input = null;
	try {
		input = new FileInputStream("config.properties");
		prop.load(input);
		return prop.getProperty(property);

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
        return null;
    }
    
}
