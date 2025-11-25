package com.example.class_roster.services;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceInterface<T> {

    List<T> getAllRecords();
    ResponseEntity<T> getRecord(int recordID);
    T addRecord(T record);
    ResponseEntity<Void> updateRecord(int id, T record);
    ResponseEntity<Void> deleteRecord(int recordID);

}
