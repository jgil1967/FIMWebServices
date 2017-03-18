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
public interface UsuarioInterface {
     UsuarioDTO createUsuario (UsuarioDTO oDto);
     UsuarioDTO getUsuarioByID (UsuarioDTO dto);
     UsuarioDTO updateUsuario (UsuarioDTO oDto);
     ArrayList <UsuarioDTO> obtenerUsuariosForRoot (UsuarioDTO dto);
     ArrayList <UsuarioDTO> obtenerUsuariosForAdministrator ();
     public UsuarioDTO iniciarSesion(UsuarioDTO dto) throws Exception;
     public UsuarioDTO verificaDisponibilidadUsuario(UsuarioDTO dto) throws Exception;
}
