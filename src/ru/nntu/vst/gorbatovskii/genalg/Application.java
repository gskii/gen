package ru.nntu.vst.gorbatovskii.genalg;

import ru.nntu.vst.gorbatovskii.genalg.model.Edge;
import ru.nntu.vst.gorbatovskii.genalg.utils.ApplicationContextUtils;

public class Application {

    public static void main(String[] args) {
        System.out.println(ApplicationContextUtils.getApplicationContext().getBeansOfType(Edge.class));
    }
}
