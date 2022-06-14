package com.totalplay.archivos.service;

import com.totalplay.archivos.dao.SelectDao;
import com.totalplay.archivos.model.Archivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@Service
@Transactional
public class FileService {
    private final Path root = Paths.get("uploads");
	
        @Autowired
        SelectDao archivoRepo;
	
        public String borrarArchivo(String nombre) {
        	archivoRepo.deleteArchivo(nombre);
        	return "Exito";
        }
	
        public String saveFileDb(Archivo model){
            archivoRepo.setArchivo(model);
            return "Exito";
        }
        
        public ArrayList<Archivo> filesById(String req){
            return archivoRepo.filesById(req);
        }
        
        
        public String save(MultipartFile file,String requerimiento) {
            System.out.println("Estos es file...");
            System.out.println(this.root.resolve(file.getOriginalFilename()));
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getContentType());
            Archivo archivo=new Archivo();
            archivo.setNombre(file.getOriginalFilename());
            archivo.setRuta(this.root.resolve(file.getOriginalFilename()).toString());
            archivo.setTipo(file.getContentType());
            archivo.setEstatus(1);
            archivo.setFechaAlta(new Date());
            archivo.setIdRequerimiento(requerimiento);
            System.out.println(archivo);
            System.out.println(archivo.toString());
            try {
                //copy (que queremos copiar, a donde queremos copiar)
                Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
                return saveFileDb(archivo);
            } catch (IOException e) {
                throw new RuntimeException("No se puede guardar el archivo. Error " + e.getMessage());
            }
        }
     
        
        public Stream<Path> loadAll(){
            System.out.println(this.root);
            try{
                return Files.walk(this.root,1).filter(path -> !path.equals(this.root))
                        .map(this.root::relativize);
            }catch (RuntimeException | IOException e){
                throw new RuntimeException("No se pueden cargar los archivos ");
            }
        }
        public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("No se puede leer el archivo ");
            }

        }catch (MalformedURLException e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    

}
