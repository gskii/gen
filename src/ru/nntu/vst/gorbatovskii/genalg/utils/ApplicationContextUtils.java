package ru.nntu.vst.gorbatovskii.genalg.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class ApplicationContextUtils {

    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    private ApplicationContextUtils() {
    }
}
