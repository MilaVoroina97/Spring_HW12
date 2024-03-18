package com.voronina.gb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
public class CustomIntegrationConfig {

    // методы для объявления двух каналов сообщений типа DirectChannel,
    // которые используются для передачи сообщений между компонентами интеграции.
    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel outputChannel() {
        return new DirectChannel();
    }

    /**
     * Метод, который определяет трансформер для преобразования сообщений.
     * @return возвращает GenericTransformer<String, String> - преобразованное сообщение типа String
     */
    @Bean
    @Transformer(inputChannel = "inputChannel", outputChannel = "outputChannel")
    public GenericTransformer<String, String> customTransformer() {
        return text -> text.toUpperCase();
    }

    /**
     * Метод определяет обработчик сообщений для записи файлов.
     * Этот метод создает FileWritingMessageHandler, который записывает входящие сообщения в файл в указанном месте.
     * @return экземпляр обработчика сообщений для записи файлов - FileWritingMessageHandler.
     */
    @Bean
    @ServiceActivator(inputChannel = "outputChannel")
    public FileWritingMessageHandler fileWriter() {

        // обработчик создает экземпляр FileWritingMessageHandler с указанием папки, в которую будут записываться файлы.
        FileWritingMessageHandler handler =
                new FileWritingMessageHandler(new File("src/main/resources"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);

        return handler;
    }
}
