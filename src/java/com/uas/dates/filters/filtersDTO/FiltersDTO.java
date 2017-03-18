/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.dates.filters.filtersDTO;

import com.uas.dates.DatesDTO;
import com.uas.keyword.KeywordDTO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jonathangil
 */
public class FiltersDTO  implements Serializable{
   DatesDTO dates;
   List<KeywordDTO> keywords;
   String filterQuery = ""; 

    public String getFilterQuery() {
        return filterQuery;
    }

   
    public void setFilterQuery(String filterQuery) {
        this.filterQuery = filterQuery;
    }

   
    public DatesDTO getDates() {
        return dates;
    }

    public void setDates(DatesDTO dates) {
        this.dates = dates;
    }

    public List<KeywordDTO> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<KeywordDTO> keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "FiltersDTO{" + "dates=" + dates + ", keywords=" + keywords + ", filterQuery=" + filterQuery + '}';
    }
    
   
}
