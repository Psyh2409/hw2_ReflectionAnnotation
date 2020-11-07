package com.gmail.psyh2409.task2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@SaveTo(path = "text.txt")
public class TextContainer {
    private String string;

    public TextContainer(String string) {
        this.string = string;
    }

    public TextContainer(){
        super();
        string = "STRING";
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
    @Saver
    private void saveText(String text, String filePath){
        if (text != null & (filePath != null & !"".equals(filePath))) {
            File file = new File(filePath);
            if (!file.exists()){
                try {
                    file.createNewFile();
                    try (OutputStream os = new FileOutputStream(file)) {
                        byte[] arr = text.getBytes();
                        os.write(arr);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
