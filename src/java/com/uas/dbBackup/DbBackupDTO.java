/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.dbBackup;

/**
 *
 * @author jonathangil
 */
public class DbBackupDTO {
    String postgresqlPath, user, password, databaseName,nombreBackup, email;

    public String getPostgresqlPath() {
        return postgresqlPath;
    }

    public void setPostgresqlPath(String postgresqlPath) {
        this.postgresqlPath = postgresqlPath;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getNombreBackup() {
        return nombreBackup;
    }

    public void setNombreBackup(String nombreBackup) {
        this.nombreBackup = nombreBackup;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
