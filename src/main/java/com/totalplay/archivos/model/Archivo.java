/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totalplay.archivos.model;

import java.util.Date;
import lombok.Data;



/**
 *
 * @author Branchbit
 */

@Data
//@Entity
//@Table(name="TPG_ARCHIVO")
//@SequenceGenerator(name = "STPGAID", sequenceName = "STPGAID", allocationSize = 1)
public class Archivo {
//    @GeneratedValue(generator = "STPGAID", strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name="TPGAID") 
    int id;
//    @Column(name="NOM_ARCHIVO")
    String nombre;
//    @Column(name="TIPO_ARCHIVO")
    String tipo;
//    @Column(name="RUTA_ARCHIVO")
    String ruta;
//    @Column(name="TPGREQID")
    String idRequerimiento;
//    @Column(name="ESTATUS")
    int estatus ;
//    @Column(name="FECHA_ALTA")
    Date fechaAlta ;
}
