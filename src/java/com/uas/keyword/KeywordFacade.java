/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.keyword;

import com.uas.document.DocumentDTO;
import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public class KeywordFacade implements KeywordInterface{
KeywordInterface kDao = null;

    public KeywordFacade() {
        kDao = new KeywordDAO();
        
    }



    @Override
    public KeywordDTO createKeyword(KeywordDTO dDto) {
    return kDao.createKeyword(dDto); }

    @Override
    public ArrayList<KeywordDTO> getKeywords() {
    return kDao.getKeywords();  }

    @Override
    public ArrayList<KeywordDTO> getKeywordsByDocument(DocumentDTO dDto) {
    return kDao.getKeywordsByDocument(dDto);
    }

    @Override
    public KeywordDTO updateKeyword(KeywordDTO dDto) {
        return kDao.updateKeyword(dDto);
    }
    
}
