/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.transactionRecord;

import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public interface TransactionRecordInterface {
    TransactionRecordDTO createTransactionRecord (TransactionRecordDTO dto);
    ArrayList <TransactionRecordDTO> getTransactionRecords ();
}
