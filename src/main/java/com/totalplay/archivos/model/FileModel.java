package com.totalplay.archivos.model;
import java.io.Serializable;
import lombok.Data;

@Data
public class FileModel {

    private String name;
    private String url;
    private String typeFile;

   
    public FileModel(String name, String url, String typeFile) {
        this.name = name;
        this.url = url;
        this.typeFile = typeFile;
    }

}
