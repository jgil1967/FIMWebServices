/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.postgresql;

/**
 *
 * @author jonathangil
 */
public class PGSQLFacade implements PGSQLInterface {
PGSQLInterface dao = null;

    public PGSQLFacade() {
        dao = new PGSQLDAO();
    }

    @Override
    public void liberarConexiones() {
         dao.liberarConexiones();
    }
    
}
