package com.example.class_roster.dao;

import java.util.List;

public interface DaoInterface<T> {

    T addRecord(T record);
    List<T> getAllRecords();
    T getRecordByID(int recordID);
    boolean updateRecord(T record);
    boolean deleteRecord(int recordID);

}
