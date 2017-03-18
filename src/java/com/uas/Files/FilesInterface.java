/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.Files;

/**
 *
 * @author jonathangil
 */
public interface FilesInterface {
    public Boolean verificaSiExiste (String nombre);
    public String retornaNombreBienParaCarpeta (String nombre);
}
