/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totalplay.archivos.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author Branchbit
 */
@Data
public class ComentariosModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int idUser;
    private String usuario;
    private String comentario;
    private Date fechaCreacion;
    private int idRequerimiento;
    private int idComentarioReply;
}
