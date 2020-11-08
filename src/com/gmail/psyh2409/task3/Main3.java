package com.gmail.psyh2409.task3;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Main3 {
    private static void customSerialize(TestClass object, String annotationName, String fileName){
        String separator = "==============================================================================";
        if (object != null
                && annotationName != null && !annotationName.isEmpty()
                && fileName != null && !fileName.isEmpty()) {
            File file = new File(fileName);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
                bw.write(separator);
                bw.newLine();
                Class<?> clazz = object.getClass();
                Field[] fields = clazz.getDeclaredFields();
                if (fields.length != 0) {
                    for (Field f : fields) {
                        Annotation[] annotations = f.getDeclaredAnnotations();
                        if (annotations.length != 0) {
                            for (Annotation ann : annotations) {
                                String annName = ann.annotationType().getSimpleName();
                                if (annName.equals(annotationName)) {
                                    if (!f.isAccessible())
                                        f.setAccessible(true);
                                    String s = f.toString().concat(separator).concat(String.valueOf(f.get(object)));
                                    bw.write(s);
                                    bw.newLine();
                                }
                            }
                        }
                    }
                }
            } catch (IOException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void customDeSerialize(TestClass object, String fileName){
        String separator = "";
        if (object != null && fileName != null && !fileName.isEmpty()) {
            File file = new File(fileName);
            if (file.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))){
                    separator = br.readLine();
                    while (br.ready()) {
                        String[] strings = br.readLine().split(separator);
                        Class<?> clazz = object.getClass();
                        Field[] fields = clazz.getDeclaredFields();
                        if (fields.length != 0) {
                            for (Field f: fields) {
                                if (f.toString().equals(strings[0])) {
                                    if (!f.isAccessible()) {
                                        f.setAccessible(true);
                                    }
                                    f.set(object,
                                            f.getType() == int.class ? Integer.parseInt(strings[1]) :
                                            f.getType() == double.class ? Double.valueOf(strings[1]) :
                                            f.getType() == long.class ? Long.valueOf(strings[1]) :
                                                    strings[1]); // ¯\_(ツ)_/¯
                                }
                            }
                        }
                    }
                } catch (IOException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        customSerialize(new TestClass(), "Save", "field.txt");
        TestClass test_class = new TestClass(100, 500);
        System.out.println(test_class);
        customDeSerialize(test_class, "field.txt");
        System.out.println(test_class);
    }
}
