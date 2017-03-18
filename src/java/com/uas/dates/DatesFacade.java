/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.dates;

/**
 *
 * @author jonathangil
 */
public class DatesFacade implements DatesInterface{
    DatesInterface dDao = null;

    public DatesFacade() {
        dDao = new DatesDAO();
    }

    @Override
    public DatesDTO getDatesDTO() {
        return dDao.getDatesDTO();
    }
     
}
