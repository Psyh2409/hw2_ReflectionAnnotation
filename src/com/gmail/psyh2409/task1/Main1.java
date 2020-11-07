package com.gmail.psyh2409.task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class Main1 {

    private static Annotation a;

    public static void main(String[] args) {
        try {
            Class<?> clazz = TestClass.class;
            TestClass testClass = (TestClass) clazz.newInstance();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method m : methods) {
                if (m.getDeclaredAnnotations().length != 0) {
                    for (Annotation a : m.getDeclaredAnnotations()) {
                        if (a.annotationType().getSimpleName().equals("Test")){
                            Test arr = m.getAnnotation(Test.class);
                            if (arr != null) {
                                int param = arr.a();
                                int pampam = arr.b();
                                if (!m.isAccessible())
                                    m.setAccessible(true);
                                m.invoke(testClass, param, pampam);
                            }
                        }
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
