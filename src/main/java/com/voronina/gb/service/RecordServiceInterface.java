package com.voronina.gb.service;

import com.voronina.gb.model.Record;

import java.util.List;

public interface RecordServiceInterface {

    List<Record> getAll();
    Record createRecord(Record record);
    Record getRecordById(Long id);
    Record updateRecord(Record record);
    void deleteRecord(Long id);
    List<Record> findRecordByName(String name);
}
