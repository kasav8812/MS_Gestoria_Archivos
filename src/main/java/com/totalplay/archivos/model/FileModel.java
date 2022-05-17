package com.totalplay.archivos.model;
import java.io.Serializable;
import lombok.Data;

@Data
public class FileModel {

    private String name;
    private String url;

   
    public FileModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

}
