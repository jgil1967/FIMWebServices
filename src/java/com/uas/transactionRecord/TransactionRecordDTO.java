/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.transactionRecord;

import com.uas.object.ObjectDTO;
import com.uas.transactionType.TransactionTypeDTO;
import com.uas.usuarios.UsuarioDTO;
import java.util.Date;

/**
 *
 * @author jonathangil
 */
public class TransactionRecordDTO {
    int id;
    UsuarioDTO usuarioDTO;
    Date date;
    TransactionTypeDTO transactionTypeDTO;
ObjectDTO objectDTO;

    public TransactionTypeDTO getTransactionTypeDTO() {
        return transactionTypeDTO;
    }

    public void setTransactionTypeDTO(TransactionTypeDTO transactionTypeDTO) {
        this.transactionTypeDTO = transactionTypeDTO;
    }

    public ObjectDTO getObjectDTO() {
        return objectDTO;
    }

    public void setObjectDTO(ObjectDTO objectDTO) {
        this.objectDTO = objectDTO;
    }


    public TransactionRecordDTO() {
        transactionTypeDTO = new TransactionTypeDTO();
          objectDTO = new ObjectDTO();
          usuarioDTO = new UsuarioDTO();
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
