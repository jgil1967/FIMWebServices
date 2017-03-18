
package com.uas.transactionRecord;

import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public class TransactionRecordFacade implements TransactionRecordInterface{
TransactionRecordInterface tDao;

    public TransactionRecordFacade() {
        tDao = new TransactionRecordDAO();
    }


    @Override
    public TransactionRecordDTO createTransactionRecord(TransactionRecordDTO dto) {
   return tDao.createTransactionRecord(dto);
    }

    @Override
    public ArrayList<TransactionRecordDTO> getTransactionRecords() {
      return tDao.getTransactionRecords();}
    
}
