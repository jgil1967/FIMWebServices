/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.usuarios;

import com.uas.areas.areaDTO;
import com.uas.object.ObjectDTO;

import java.io.Serializable;

/**
 *
 * @author jonathangil
 */
public class UsuarioDTO extends ObjectDTO implements Serializable {
    
   public String contrasena,contrasenaVerify;
   public  Boolean verified, isAdministrator,enabled,availability, root;
public int idArea;
public areaDTO area;

    public UsuarioDTO() {
       isAdministrator = false;
       verified = false;
       enabled = false;
       root = false;
       availability = true;
       area = new areaDTO();
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

   
    public Boolean getIsAdministrator() {
        return isAdministrator;
    }

    public void setIsAdministrator(Boolean isAdministrator) {
        this.isAdministrator = isAdministrator;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

  
    
    
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    
 

    public String getcontrasena() {
        return contrasena;
    }

    public void setcontrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public String getContrasenaVerify() {
        return contrasenaVerify;
    }

    public void setContrasenaVerify(String contrasenaVerify) {
        this.contrasenaVerify = contrasenaVerify;
    }

    public areaDTO getArea() {
        return area;
    }

    public void setArea(areaDTO area) {
        this.area = area;
    }

    public Boolean getRoot() {
        return root;
    }

    public void setRoot(Boolean root) {
        this.root = root;
    }

   
  
    
            
}
