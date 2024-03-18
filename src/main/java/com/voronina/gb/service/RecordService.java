package com.voronina.gb.service;

import com.voronina.gb.aspects.TrackUserAction;
import com.voronina.gb.model.Record;
import com.voronina.gb.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService implements RecordServiceInterface{

    private final RecordRepository recordRepository;


    /**
     * Метод получения списка всех записей
     * @return список всех записей
     */
    @TrackUserAction
    @Override
    public List<Record> getAll() {
        return recordRepository.findAll();
    }

    /**
     * Метод сохранения записи
     * @param record новая запись
     * @return сохраненная запись
     */
    @TrackUserAction
    @Override
    public Record createRecord(Record record) {
        return recordRepository.save(record);
    }

    /**
     * Метод получения записи по ее id
     * @param id id записи
     * @return найденная запись
     */
    @TrackUserAction
    @Override
    public Record getRecordById(Long id) {
        return recordRepository.findById(id).orElseThrow(null);
    }

    /**
     * Метод изменения записи по ее id
     * @return обновленная запись
     */
    @TrackUserAction
    @Override
    public Record updateRecord(Record record) {

        Record recordById = getRecordById(record.getId());

        recordById.setName(record.getName());
        recordById.setContent(record.getContent());

        return recordRepository.save(recordById);
    }

    /**
     * Метод удаления записи по ее id
     * @param id записи
     */
    @TrackUserAction
    @Override
    public void deleteRecord(Long id) {

        Record recordById = getRecordById(id);
        recordRepository.delete(recordById);
    }

    @TrackUserAction
    @Override
    public List<Record> findRecordByName(String name) {
        return recordRepository.findRecordByName(name);
    }
}