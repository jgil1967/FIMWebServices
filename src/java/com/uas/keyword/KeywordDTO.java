/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.keyword;

import com.uas.object.ObjectDTO;
import java.io.Serializable;

/**
 *
 * @author jonathangil
 */
public class KeywordDTO extends ObjectDTO implements Serializable {

    
    public KeywordDTO() {
        this.setKind("keyword");
    }

    @Override
    public String toString() {
        return "KeywordDTO{" + '}';
    }
    
     
}
