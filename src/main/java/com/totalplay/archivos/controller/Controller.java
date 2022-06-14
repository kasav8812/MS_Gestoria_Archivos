package com.totalplay.archivos.controller;

import com.totalplay.archivos.model.Archivo;
import com.totalplay.archivos.model.FileModel;
import com.totalplay.archivos.service.FileService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Log4j2
@RestController
@CrossOrigin("*")
@RequestMapping("/file")
public class Controller {
        private HttpStatus status;

	@Autowired
	private FileService fileService;
	
	@PostMapping("/delete")
	public ResponseEntity<String> borrar(@PathVariable("nombre") String nom) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.borrarArchivo(nom));
	}
	
	@PostMapping("/upload")
        public ResponseEntity<String> uploadFiles(@RequestParam("files")MultipartFile[] files, @RequestParam("idRequerimiento") String idReq){
            String message = "";
            try{
                List<String> fileNames = new ArrayList<>();

                Arrays.asList(files).stream().forEach(file->{
                    fileService.save(file,idReq);
                    fileNames.add(file.getOriginalFilename());
                });

                message = "Se subieron los archivos correctamente " + fileNames;
                return ResponseEntity.status(HttpStatus.OK).body(message);
            }catch (Exception e){
                message = "Fallo al subir los archivos---"+e;
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
//        @GetMapping("/files")
//        public ResponseEntity<List<FileModel>> getFiles(){
//            List<FileModel> fileInfos = fileService.loadAll().map(path -> {
//              String filename = path.getFileName().toString();
//              String url = MvcUriComponentsBuilder.fromMethodName(Controller.class, "getFile",
//                      path.getFileName().toString()).build().toString();
//              return new FileModel(filename, url);
//            }).collect(Collectors.toList());
//            System.out.println("datos----");
//            System.out.println(fileInfos);
//            return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
//        }
        
        @GetMapping("/filesRequerimiento/{idRequerimiento}")
        public ResponseEntity<ArrayList<FileModel>> getFiles(@PathVariable("idRequerimiento") String req){
            System.out.println("requerimiento _"+req);
            ArrayList<FileModel> fileInfos=new ArrayList<FileModel>();
            for(Archivo archivo: fileService.filesById(req)){
                String url = MvcUriComponentsBuilder.fromMethodName(Controller.class, "getFile",
                      archivo.getNombre()).build().toString();
              fileInfos.add(new FileModel(archivo.getNombre(), url, archivo.getTipo()));
            }
            System.out.println("datos----");
            System.out.println(fileInfos);
            return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
        }
        
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = fileService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\""+file.getFilename() + "\"").body(file);
    }
}
