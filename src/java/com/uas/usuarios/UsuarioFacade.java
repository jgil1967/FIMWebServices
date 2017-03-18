/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.usuarios;

import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public class UsuarioFacade implements UsuarioInterface{
UsuarioInterface uDao = null;

    public UsuarioFacade() {
        uDao = new UsuarioDAO();
    }
    @Override
    public UsuarioDTO createUsuario(UsuarioDTO oDto) {
    return uDao.createUsuario(oDto);}

    @Override
    public UsuarioDTO iniciarSesion(UsuarioDTO dto) throws Exception {
        return uDao.iniciarSesion(dto);
    }

    @Override
    public ArrayList<UsuarioDTO> obtenerUsuariosForRoot(UsuarioDTO dto) {
   return uDao.obtenerUsuariosForRoot(dto);}

    @Override
    public UsuarioDTO updateUsuario(UsuarioDTO oDto) {
     return uDao.updateUsuario(oDto);
    }

    @Override
    public UsuarioDTO verificaDisponibilidadUsuario(UsuarioDTO dto) throws Exception {
       return uDao.verificaDisponibilidadUsuario(dto);
    }

    @Override
    public UsuarioDTO getUsuarioByID(UsuarioDTO dto) {
       return uDao.getUsuarioByID(dto);
    }

    @Override
    public ArrayList<UsuarioDTO> obtenerUsuariosForAdministrator() {
       return uDao.obtenerUsuariosForAdministrator();
    }
    
}
