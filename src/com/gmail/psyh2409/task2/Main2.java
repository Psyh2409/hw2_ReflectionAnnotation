package com.gmail.psyh2409.task2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main2 {
    public static void main(String[] args) {
        try {
            Class<?> clazz = TextContainer.class;
            TextContainer textContainer = (TextContainer) clazz.newInstance();
            String path = "";
            String text = "";
            Annotation[] annotations = clazz.getDeclaredAnnotations();
            if (annotations.length != 0){
                for (Annotation annotation: annotations) {
                    if (annotation.annotationType().getSimpleName().equals("SaveTo")){
                        SaveTo saveTo = (SaveTo) annotation;
                        path = saveTo.path();
                    }
                }
            }
            Field[] fields = clazz.getDeclaredFields();
            if (fields.length!=0){
                for (Field f: fields) {
                    if (f.getName().equals("string"))
                        if (!f.isAccessible())
                            f.setAccessible(true);
                        text = (String) f.get(textContainer);
                }
            }
            Method[] methods = clazz.getDeclaredMethods();
            if (methods.length != 0) {
                for (Method method: methods) {
                    Annotation[] annotations1 = method.getDeclaredAnnotations();
                    if (annotations1.length != 0) {
                        for (Annotation a : annotations1) {
                            String name = a.annotationType().getSimpleName();
                            if ("Saver".equals(name)){
                                if (!method.isAccessible())
                                    method.setAccessible(true);
                                method.invoke(textContainer, text, path);
                            }
                        }
                    }
                }
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
