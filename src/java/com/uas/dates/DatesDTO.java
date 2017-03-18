/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.dates;

import java.io.Serializable;

/**
 *
 * @author jonathangil
 */
public class DatesDTO implements Serializable {
String oldestCreatedOn,newestCreatedOn,oldestFileDate,newestFileDate;   

    public String getOldestCreatedOn() {
        return oldestCreatedOn;
    }

    public void setOldestCreatedOn(String oldestCreatedOn) {
        this.oldestCreatedOn = oldestCreatedOn;
    }

    public String getNewestCreatedOn() {
        return newestCreatedOn;
    }

    public void setNewestCreatedOn(String newestCreatedOn) {
        this.newestCreatedOn = newestCreatedOn;
    }

    public String getOldestFileDate() {
        return oldestFileDate;
    }

    public void setOldestFileDate(String oldestFileDate) {
        this.oldestFileDate = oldestFileDate;
    }

    public String getNewestFileDate() {
        return newestFileDate;
    }

    public void setNewestFileDate(String newestFileDate) {
        this.newestFileDate = newestFileDate;
    }

    @Override
    public String toString() {
        return "DatesDTO{" + "oldestCreatedOn=" + oldestCreatedOn + ", newestCreatedOn=" + newestCreatedOn + ", oldestFileDate=" + oldestFileDate + ", newestFileDate=" + newestFileDate + '}';
    }

}
