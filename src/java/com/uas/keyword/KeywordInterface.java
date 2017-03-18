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
public interface KeywordInterface {
    KeywordDTO createKeyword(KeywordDTO dDto);
    KeywordDTO updateKeyword(KeywordDTO dDto);
    ArrayList<KeywordDTO> getKeywords();
    ArrayList<KeywordDTO> getKeywordsByDocument(DocumentDTO dDto);
}
