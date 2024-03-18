package com.voronina.gb.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Aspect
@Component
public class MyLoggingAspect {
    private final Logger logger = Logger.getLogger(MyLoggingAspect.class.getName());

    public MyLoggingAspect() {
        try {
            FileHandler fileHandler = new FileHandler("myApp.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occur in FileHandler.", e);
        }
    }


    @AfterReturning(value = "@annotation(TrackUserAction))", returning = "returnedValue")
    public void log(JoinPoint joinPoint, Object returnedValue) {
        // Информация о методе
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();
        Object[] methodArgs = joinPoint.getArgs();


        // Вывод информации в лог
        logger.info("Информация логирования: ");
        logger.info("Вызванный метод: " + methodName);
        logger.info("Класс: " + className);
        logger.info("Аргументы: " + Arrays.toString(methodArgs));
        logger.info("Метод отработал и вернул значение: " + returnedValue);
        logger.info("Конец блока логирования. \n");
    }

}
