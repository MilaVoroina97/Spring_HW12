package com.voronina.gb.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;


/**
 * Интерфейс интеграционного шлюза (Messaging Gateway)
 */
@MessagingGateway(defaultRequestChannel = "textInputChanel")
public interface CustomFileGateway {

    // метод для записи данных в файл
    void saveDataToFile(@Header(FileHeaders.FILENAME) String fileName, String content);
}